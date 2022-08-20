package com.politrons.engine

import com.politrons.sprite.Bird

import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}
import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}


class BirdEngine(var xPos: Integer,
                 var yPos: Integer,
                 var heartDisable:Boolean=false) extends JLabel with ActionListener {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val bird = new Bird(xPos,yPos)

  init()

  private def init(): Unit = {
    addKeyListener(new KeyListener)
    setFocusable(true)
    setIcon(bird.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(bird.x, bird.y)
    setFrameDelay()
    startGravity()
  }

  private def setFrameDelay(): Unit = {
    val DELAY = 15
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    setLocation(bird.x, bird.y)
  }

  private class KeyListener() extends KeyAdapter {

    override def keyPressed(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case KeyEvent.VK_SPACE =>
          bird.y-=35
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

}