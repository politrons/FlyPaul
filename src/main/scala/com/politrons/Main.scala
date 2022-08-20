package com.politrons

import com.politrons.level.Level

import java.awt.EventQueue

object Main extends App {
  EventQueue.invokeLater(() => {
    val ex = new Level()
    ex.setVisible(true)
  })
}

