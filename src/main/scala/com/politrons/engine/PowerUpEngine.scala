package com.politrons.engine

import com.politrons.sprite.PowerUp

import java.awt.event.{ActionEvent, ActionListener}
import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}

class PowerUpEngine(var xPos: Integer,
                    var yPos: Integer) extends JLabel with ActionListener {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))
  private val random = scala.util.Random

  val powerUp = new PowerUp(xPos, yPos)
  init()

  private def init(): Unit = {
    setVisible(false)
    setIcon(powerUp.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(powerUp.x, powerUp.y)
    setFrameDelay()
  }

  private def setFrameDelay(): Unit = {
    val DELAY = 10000
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  /**
   * Every N time we run a random tp see if we can offer the powerUp icon.
   * @param e
   */
  override def actionPerformed(e: ActionEvent): Unit = {
    val i = random.nextInt(100)
    if (i < 30) {
      setVisible(true)
      movePowerUp()
    }
  }

  /**
   * Future to Show the power up and move forward in the screen.
   * Once dureation ends this thread and task die.
   */
  def movePowerUp(): Unit = {
    powerUp.x = 750
    powerUp.y = 100
    setLocation(powerUp.x, powerUp.y)
    Future {
      val duration = 10000
      val time = System.currentTimeMillis() + duration
      while (time > System.currentTimeMillis()) {
        println(s"Power X:${powerUp.x} Y:${powerUp.y}")
        setLocation(powerUp.x, powerUp.y)
        powerUp.x -= 20
        Thread.sleep(200)
      }
    }
  }


}