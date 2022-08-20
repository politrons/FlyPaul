package com.politrons.level

import javax.swing._

/**
 * We set the background, and we set the JLabel of the [CharacterEngine]
 */
class Background() extends JLabel {
  val imageIcon = new ImageIcon("src/main/resources/background.jpg")
  this.setIcon(imageIcon)


}