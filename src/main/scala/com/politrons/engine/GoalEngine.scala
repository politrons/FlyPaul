package com.politrons.engine

import com.politrons.sprite.Goal

import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.ExecutionContext


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

}