package com.jomolangma.app.scala.akka

import akka.actor.{ActorSystem, Props}
import org.apache.hadoop.yarn.webapp.example.HelloWorld.Hello

/**
  * Created by ZhangLijun on 4/22/16.
  */
object ActorTest {
  def main(args: Array[String]) {
    val actorSystem = ActorSystem("ActorSystem")

    val helloActor = actorSystem.actorOf(Props[HelloActor],"HelloActor")

    helloActor ! "Hello"
    helloActor ! "hello"

    Thread.sleep(1000)
    actorSystem.stop(helloActor)
  }

}
