package com.politrons.engine

import com.politrons.sprite.Goal

import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}

class GoalEngine(var xPos: Integer,
                 var yPos: Integer) extends JLabel {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val goal = new Goal(xPos, yPos)

  init()

  private def init(): Unit = {
    setVisible(false)
    setIcon(goal.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(goal.x, goal.y)
  }

  def finishLevel(): Unit = {
    Future{
      setVisible(true)
      0 to 20 foreach (_ => {
        goal.x -= 50
        goal.y = yPos
        setLocation(goal.x, goal.y)
        Thread.sleep(500)
      })
    }
  }

}