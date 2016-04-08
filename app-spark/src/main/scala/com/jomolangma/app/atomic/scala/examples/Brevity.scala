package com.jomolangma.app.atomic.scala.examples

/**
  * Created by ZhangLijun on 4/5/16.
  */
object Brevity {
  def filterWithYield(v:Vector[Int]):Vector[Int] =
    for{n <- v;if n<10;if n%2 != 0} yield n

  def main(args: Array[String]) {
    val v = Vector(1,2,3,5,6,7,8,10,13,14,17)

    println(filterWithYield(v))
  }
}
