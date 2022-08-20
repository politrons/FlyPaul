package com.politrons.engine

import com.politrons.sprite.Cloud

import java.awt.event.{ActionEvent, ActionListener}
import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random


class CloudEngine(var xPos: Integer,
                  var yPos: Integer,
                  var heartDisable: Boolean = false) extends JLabel with ActionListener {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val cloud = new Cloud(xPos, yPos)
  var cloudSpeed = 3

  init()

  private def init(): Unit = {
    setIcon(cloud.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(cloud.x, cloud.y)
    startHorizon()
    setFrameDelay()
  }

  private def setFrameDelay(): Unit = {
    val DELAY = 15
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  def startHorizon(): Unit = {
    val incrementalTime = 10000
    var time = System.currentTimeMillis() + incrementalTime
    Future {
      while (true) {
        if (time < System.currentTimeMillis()) {
          cloudSpeed += 1
          time = System.currentTimeMillis() + incrementalTime
        }
        if (cloud.x <= -150) {
          cloud.x = 800
          cloud.y = Random.between(0, 500)
        } else {
          cloud.x -= cloudSpeed
        }
        Thread.sleep(20)
      }
    }
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    setLocation(cloud.x, cloud.y)
  }

  /**
   * Function to check if the character collision with an enemy.
   * In case of collision we reduce one heart in the level, and we set
   * the character like dead.
   * In case we lose all hearts the game is over.
   */
  //  private def collisionEngine() = {
  //    Future {
  //      val deviation = 10
  //      while (true) {
  //        val charX = bird.x
  //        val charY = bird.y
  //        val xComp = Math.abs(charX - enemy1.x)
  //        val yComp = Math.abs(charY - enemy1.y)
  //        if (xComp <= deviation && yComp <= deviation) {
  //          characterEngine.live match {
  //            case 3 => heart3Engine.removeHeart()
  //            case 2 => heart2Engine.removeHeart()
  //            case 1 => heart1Engine.removeHeart(); gameOverEngine.setVisible(true)
  //          }
  //          characterEngine.live -= 1
  //          characterEngine.characterDeadAnimation()
  //        }
  //        Thread.sleep(100)
  //      }
  //    }
  //  }


}