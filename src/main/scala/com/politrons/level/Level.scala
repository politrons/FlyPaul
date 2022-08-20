package com.politrons.level

import com.politrons.engine.{BackgroundEngine, BirdEngine, CloudEngine}

import java.util.concurrent.Executors
import javax.swing.JFrame
import scala.concurrent.ExecutionContext

class Level extends JFrame {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  private val cloudEngine1 = new CloudEngine(900, 0)
  private val cloudEngine2 = new CloudEngine(500, 150)
  private val cloudEngine3 = new CloudEngine(700, 300)
  private val cloudEngine4 = new CloudEngine(900, 500)
  private val backgroundEngine:BackgroundEngine = new BackgroundEngine()
  private val birdEngine = new BirdEngine(backgroundEngine,250, 250, List(cloudEngine1, cloudEngine2, cloudEngine3, cloudEngine4))
  initGame()

  private def initGame(): Unit = {
    this.add(birdEngine)
    this.add(cloudEngine1)
    this.add(cloudEngine2)
    this.add(cloudEngine3)
    this.add(cloudEngine4)
    this.add(backgroundEngine)
    this.setResizable(false)
    this.pack()
    this.setVisible(true)
    setTitle("FlyPaul")
    setLocationRelativeTo(null)
    setResizable(false)
    setDefaultCloseOperation(3)
  }
}
