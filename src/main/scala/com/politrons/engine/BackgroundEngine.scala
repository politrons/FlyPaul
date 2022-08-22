package com.politrons.engine

import java.awt.Graphics
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing._
import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{Font, Graphics}

class BackgroundEngine(goalEngine: GoalEngine) extends JLabel with ActionListener {

  var playedTimeInMin = 1

  val imageIcon = new ImageIcon("src/main/resources/background.jpg")
  this.setIcon(imageIcon)

  setFrameDelay()

  private def setFrameDelay(): Unit = {
    val DELAY = 60000
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    playedTimeInMin += 1
    if (playedTimeInMin == 5) goalEngine.finishLevel()
  }

  override protected def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    g.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
    g.drawString(s"Time:$playedTimeInMin ", 50, 50)

  }
}