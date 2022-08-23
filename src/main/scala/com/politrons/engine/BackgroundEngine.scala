package com.politrons.engine

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{Font, Graphics}
import javax.swing._

/**
 * Engine where we set the background image and we control the [playedTimeInMin]
 * that means the played time in minutes.
 * After 5 minutes we show the goal sprite which means the
 */
class BackgroundEngine(implicit  goalEngine: GoalEngine) extends JLabel with ActionListener {

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