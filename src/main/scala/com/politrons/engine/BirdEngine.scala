package com.politrons.engine

import com.politrons.sprite.Bird

import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}
import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}

class BirdEngine(val backgroundEngine: BackgroundEngine,
                 var xPos: Integer,
                 var yPos: Integer,
                 var cloudEngines: List[CloudEngine]) extends JLabel with ActionListener {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val bird = new Bird(xPos, yPos)

  init()

  private def init(): Unit = {
    addKeyListener(new KeyListener)
    setFocusable(true)
    setIcon(bird.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(bird.x, bird.y)
    setFrameDelay()
    startGravity()
    outOfLevelSchedule()
  }

  private def setFrameDelay(): Unit = {
    val DELAY = 15
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    setIcon(bird.imageIcon)
    setLocation(bird.x, bird.y)
  }

  private class KeyListener() extends KeyAdapter {

    override def keyPressed(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case KeyEvent.VK_SPACE =>
          bird.changeFrame()
          bird.y -= 35
        case _ =>
          println("Key not implemented")
      }
    }
  }

  def startGravity(): Unit = {
    Future {
      while (true) {
        bird.y += 10
        Thread.sleep(100)
        println(s"Bird X:${bird.x} Y:${bird.y}")
      }
    }
  }

  /**
   * Function to check if the character collision with an enemy.
   * In case of collision we reduce one heart in the level, and we set
   * the character like dead.
   * In case we lose all hearts the game is over.
   */
  private def outOfLevelSchedule() = {
    Future {
      while (true) {
        Thread.sleep(100)
        if(bird.y <= -50 || bird.y >= 600){
          println("##### Game Over ######")
          resetGame()
          birdDeadAnimation()
        }
      }
    }
  }

  private def resetGame(): Unit = {
    cloudEngines.foreach(cloudEngine => {
      cloudEngine.cloudSpeed = 3
    })
    backgroundEngine.playedTime = 0
  }

  /**
   * Move character to the initial position and make an effect of reset
   */
  def birdDeadAnimation(): Unit = {
    Future{
      bird.x=xPos
      bird.y=yPos
      0 to 50 foreach { _ =>
        setIcon(null)
        Thread.sleep(10)
        setIcon(bird.imageIcon)
        Thread.sleep(10)
      }
    }
  }


}