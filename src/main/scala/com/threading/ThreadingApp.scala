package com.threading

object ThreadingApp extends App {
  System.loadLibrary("threading")
  for (a ← 1 until 20) {
    new Thread().start()
  }

}
