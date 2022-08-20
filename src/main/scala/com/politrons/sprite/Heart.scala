package com.politrons.sprite

import com.politrons.sprite.SpriteUtils.scaleImage

import java.awt.Image
import javax.swing.ImageIcon

class Heart(var x:Integer, var y:Integer) {

  val image: Image = scaleImage(new ImageIcon("src/main/resources/heart.png").getImage, 40, 40)
  val imageIcon: ImageIcon = new ImageIcon(image)

}