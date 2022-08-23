package com.politrons.sprite

import com.politrons.sprite.SpriteUtils.scaleImage

import javax.swing.ImageIcon

class Bird(var x:Integer, var y:Integer) {

  private var frame=1


  val birdImages = Map(
     1 -> new ImageIcon(scaleImage(new ImageIcon("src/main/resources/bird1.png").getImage, 50,50)),
     2 -> new ImageIcon(scaleImage(new ImageIcon("src/main/resources/bird2.png").getImage, 50,50))
  )

  val superBirdImages = Map(
    1 -> new ImageIcon(scaleImage(new ImageIcon("src/main/resources/superBird1.png").getImage, 50,50)),
    2 -> new ImageIcon(scaleImage(new ImageIcon("src/main/resources/superBird2.png").getImage, 50,50))
  )

  var birdImagesMap:Map[Int, ImageIcon] =  birdImages

  var imageIcon:ImageIcon = birdImages(1)

  def changeFrame(): Unit = {
    if (frame == 2) frame = 1
    else frame += 1
    imageIcon = birdImagesMap(frame)
  }


}