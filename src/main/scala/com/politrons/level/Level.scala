package com.politrons.level

import com.politrons.engine.{BirdEngine, BlockEngine}

import java.util.concurrent.Executors
import javax.swing.{JFrame, SwingUtilities}
import scala.concurrent.{ExecutionContext, Future}

class Level extends JFrame {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  private val birdEngine = new BirdEngine(250, 250)

  private val blockEngine1 = new BlockEngine(500, 50)
  private val blockEngine2 = new BlockEngine(700, 100)
  private val blockEngine3 = new BlockEngine(900, 150)

  initGame()

  private def initGame(): Unit = {
    this.add(birdEngine)
    this.add(blockEngine1)
    this.add(blockEngine2)
    this.add(blockEngine3)
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
