package com.jomolangma.app.scala

package object learningconcurrency {

  def log(msg: String) {
    println(s"${Thread.currentThread.getName}: $msg")
  }

}

