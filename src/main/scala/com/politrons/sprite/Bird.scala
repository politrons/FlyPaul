package com.politrons.sprite

import com.politrons.sprite.SpriteUtils.scaleImage

import javax.swing.ImageIcon

class Bird(var x:Integer, var y:Integer) {

  private var frame=1

  val images = Map(
     1 -> new ImageIcon(scaleImage(new ImageIcon("src/main/resources/bird1.png").getImage, 50,50)),
     2 -> new ImageIcon(scaleImage(new ImageIcon("src/main/resources/bird2.png").getImage, 50,50))
  )
  var imageIcon:ImageIcon = images(1)


  def changeFrame(): Unit = {
    if (frame == 2) frame = 1
    else frame += 1
    imageIcon = images(frame)
  }


}