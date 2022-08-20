package com.politrons.sprite

import com.politrons.sprite.SpriteUtils.scaleImage

import java.awt.Image
import javax.swing.ImageIcon

class Goal(var x:Integer, var y:Integer) {

  val image: Image = scaleImage(new ImageIcon("src/main/resources/goal.png").getImage, 200, 200)
  val imageIcon: ImageIcon = new ImageIcon(image)

}