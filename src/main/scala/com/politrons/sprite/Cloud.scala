package com.politrons.sprite

import com.politrons.sprite.SpriteUtils.scaleImage

import java.awt.Image
import javax.swing.ImageIcon

class Cloud(var x:Integer, var y:Integer)  {

  val image: Image = scaleImage(new ImageIcon("src/main/resources/cloud.png").getImage, 200,150)
  val imageIcon = new ImageIcon(image)


}