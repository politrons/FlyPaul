package com.politrons.sprite

import com.politrons.sprite.SpriteUtils.scaleImage

import java.awt.Image
import javax.swing.ImageIcon

class PowerUp(var x:Integer, var y:Integer)  {

  val image: Image = scaleImage(new ImageIcon("src/main/resources/powerUp.png").getImage, 50,50)
  val imageIcon = new ImageIcon(image)


}