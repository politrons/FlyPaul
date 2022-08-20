package com.politrons.level

import com.politrons.engine.{BirdEngine, CloudEngine}

import java.util.concurrent.Executors
import javax.swing.{JFrame, SwingUtilities}
import scala.concurrent.{ExecutionContext, Future}

class Level extends JFrame {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  private val birdEngine = new BirdEngine(250, 250)

  private val blockEngine1 = new CloudEngine(900, 0)
  private val blockEngine2 = new CloudEngine(500, 150)
  private val blockEngine3 = new CloudEngine(700, 300)
  private val blockEngine4 = new CloudEngine(900, 500)

  initGame()

  private def initGame(): Unit = {
    this.add(birdEngine)
    this.add(blockEngine1)
    this.add(blockEngine2)
    this.add(blockEngine3)
    this.add(blockEngine4)
    this.add(new Background)
    this.setResizable(false)
    this.pack()
    this.setVisible(true)
    setTitle("FlyPaul")
    setLocationRelativeTo(null)
    setResizable(false)
    setDefaultCloseOperation(3)
  }

}
