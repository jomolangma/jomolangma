package com.jomolangma.app.scala.akka

import akka.actor.Actor

/**
  * Created by ZhangLijun on 4/22/16.
  */
class HelloActor extends Actor{

  def receive = {
    case "hello" => println("world")
    case _       => println("huh?")
  }

  override
  def preStart = {
    println("preStart")
  }

  override
  def postStop = {
    println("postStop")
  }
}
