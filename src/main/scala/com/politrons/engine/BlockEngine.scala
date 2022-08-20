package com.politrons.engine

import com.politrons.sprite.Block

import java.awt.Color
import java.awt.event.{ActionEvent, ActionListener}
import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random


class BlockEngine(var xPos: Integer,
                  var yPos: Integer,
                  var heartDisable: Boolean = false) extends JLabel with ActionListener {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val block = new Block(xPos, yPos)

  init()

  private def init(): Unit = {
    setIcon(block.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(block.x, block.y)
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
        if (block.x <= 0) {
          block.x = xPos
          block.y = Random.between(yPos - 50, yPos)
        } else {
          block.x -= 5
        }
        Thread.sleep(100)
        println(s"Block X:${block.x} Y:${block.y}")
      }
    }
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    setLocation(block.x, block.y)
  }
}