package com.politrons.engine

import com.politrons.sprite.Heart

import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}


class HeartEngine(var xPos: Integer,
                  var yPos: Integer) extends JLabel {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val heart = new Heart(xPos, yPos)

  init()

  private def init(): Unit = {
    setIcon(heart.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(heart.x, heart.y)
  }

  def removeHeart(): Unit = {
    Future{
      0 to 25 foreach { _ =>
        setIcon(null)
        Thread.sleep(20)
        setIcon(heart.imageIcon)
        Thread.sleep(20)
        setIcon(null)
      }
    }
  }
}