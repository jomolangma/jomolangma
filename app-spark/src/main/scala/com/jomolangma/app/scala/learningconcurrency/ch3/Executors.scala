package com.jomolangma.app.scala.learningconcurrency
package ch3

object ExecutorsCreate extends App {
  import scala.concurrent._
  val executor = new java.util.concurrent.ForkJoinPool
  executor.execute(new Runnable {
    def run() = log("This task is run asynchronously.")
  })
}


object ExecutionContextGlobal extends App {
  import scala.concurrent._
  val ectx = ExecutionContext.global
  ectx.execute(new Runnable {
    def run() = log("Running on the execution context.")
  })

  Thread.sleep(1000)
}


object ExecutionContextCreate extends App {
  import scala.concurrent._
  val ectx = ExecutionContext.fromExecutorService(new forkjoin.ForkJoinPool)
  ectx.execute(new Runnable {
    def run() = log("Running on the execution context again.")
  })
}


object ExecutionContextSleep extends App {
  for (i <- 0 until 32) execute {
    Thread.sleep(2000)
    log(s"Task $i completed.")
  }
  Thread.sleep(10000)
}


