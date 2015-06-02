package com.jomolangma.app.scala

import scala.collection.mutable.Map
class ChecksumAccumulator{
  private var sum=0
  def add(b:Byte) :Unit = sum +=b
  def checksum() : Int = ~ (sum & 0xFF) +1
}

object ChecksumAccumulator {
  private val cache = Map [String, Int] ()
  cache += ("test"->100)
  def calculate(s:String) : Int =
    if(cache.contains(s))
      cache(s)
    else {
      val acc=new ChecksumAccumulator
      for( c <- s)
        acc.add(c.toByte)
      val cs=acc.checksum()
      cache += ( s -> cs)

      cache.foreach(e => {
        val (k,v)=e
        println(k+":"+v)
      })

        cache.foreach(e => println(e._1 +":"+e._2))

      cs
    }

  def main (args: Array[String]) {
    println (ChecksumAccumulator.calculate("Welcome to Scala Chinese community"))
  }
}
