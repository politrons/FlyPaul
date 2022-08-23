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

  /**
   * Task responsible to move the clouds from right to left with an incremental speed
   * Every [incrementalTime] we increase cloud speed
   */
  def startHorizon(): Unit = {
    val incrementalTime = 10000
    var time = System.currentTimeMillis() + incrementalTime
    Future {
      while (true) {
        if (time < System.currentTimeMillis()) {
          time = increaseSpeed(incrementalTime)
        }
        moveClouds()
        Thread.sleep(20)
      }
    }
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    setLocation(cloud.x, cloud.y)
  }

  def moveClouds(): Unit = {
    if (cloud.x <= -150) {
      cloud.x = 800
      cloud.y = Random.between(0, 500)
    } else {
      cloud.x -= cloudSpeed
    }
  }

  private val increaseSpeed: Int => Long = incrementalTime => {
    cloudSpeed += 1
    System.currentTimeMillis() + incrementalTime
  }

}