package com.politrons.level

import com.politrons.engine.{BackgroundEngine, BirdEngine, CloudEngine, GoalEngine, HeartEngine, PowerUpEngine}

import java.util.concurrent.Executors
import javax.swing.JFrame
import scala.concurrent.ExecutionContext

class Level extends JFrame {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  private val heart1Engine = new HeartEngine(100, 10)
  private val heart2Engine = new HeartEngine(70, 10)
  private val heart3Engine = new HeartEngine(40, 10)
  private val cloudEngine1 = new CloudEngine(900, 0)
  private val cloudEngine2 = new CloudEngine(500, 150)
  private val cloudEngine3 = new CloudEngine(700, 300)
  private val cloudEngine4 = new CloudEngine(900, 500)
  implicit private val goalEngine: GoalEngine = new GoalEngine(550, 200)
  private val powerUpEngine: PowerUpEngine = new PowerUpEngine(550, 100)
  private val backgroundEngine: BackgroundEngine = new BackgroundEngine()
  private val birdEngine = new BirdEngine(
    250,
    250,
    List(cloudEngine1, cloudEngine2, cloudEngine3, cloudEngine4),
    List(heart1Engine, heart2Engine, heart3Engine),
    powerUpEngine
  )
  initGame()

  private def initGame(): Unit = {
    this.add(birdEngine)
    this.add(heart1Engine)
    this.add(heart2Engine)
    this.add(heart3Engine)
    this.add(cloudEngine1)
    this.add(cloudEngine2)
    this.add(cloudEngine3)
    this.add(cloudEngine4)
    this.add(goalEngine)
    this.add(powerUpEngine)
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
