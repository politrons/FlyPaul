package com.politrons.engine

import java.awt.Graphics
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing._

class BackgroundEngine(goalEngine: GoalEngine) extends JLabel with ActionListener {

  val imageIcon = new ImageIcon("src/main/resources/background.jpg")
  this.setIcon(imageIcon)

  setFrameDelay()

  private def setFrameDelay(): Unit = {
    val DELAY = 60000 * 5 // 5 minutes duration level
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    goalEngine.finishLevel()
  }

  override protected def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
  }
}