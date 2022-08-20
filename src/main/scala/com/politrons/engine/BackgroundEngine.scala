package com.politrons.engine

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{Font, Graphics}
import javax.swing._

/**
 * We set the background, and we set the JLabel of the [CharacterEngine]
 */
class BackgroundEngine() extends JLabel  with ActionListener {

  var playedTime=0

  val imageIcon = new ImageIcon("src/main/resources/background.jpg")
  this.setIcon(imageIcon)

  setFrameDelay()

  private def setFrameDelay(): Unit = {
    val DELAY = 1000
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    playedTime+=1
  }

  override protected def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    g.setFont(new Font("Verdana", Font.CENTER_BASELINE, 18));
    g.drawString(s"TIME:$playedTime ", 20, 20)
  }
}