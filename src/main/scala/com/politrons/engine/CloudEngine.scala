package com.politrons.engine

import com.politrons.sprite.Cloud

import java.awt.Color
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
    Future {
      while (true) {
        if (cloud.x <= -150) {
          cloud.x = 800
          cloud.y = Random.between(0,500)
        } else {
          cloud.x -= 5
        }
        Thread.sleep(50)
      }
    }
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    setLocation(cloud.x, cloud.y)
  }
}