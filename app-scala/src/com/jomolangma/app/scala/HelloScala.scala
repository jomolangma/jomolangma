package com.jomolangma.app.scala

/**
 * Created by zhanglijun on 5/10/15.
 */
class HelloScala {

}

object HelloScala{

  def hello(name:String):String = {
    "Hello " + name
  }

  def helloScala(){
    println("Hello Scala,this is rockey")
  }

  def add = (x:Int,y:Int)=>x+y

  val sum = (x:Int,y:Int)=>x+y

  def sum2(x:Int)(y:Int) = x+y

  def variableParameter(s:String*) = {
    s.foreach(x=>println(x))
  }

  def helloDefault(name:String = "Spark"):String = {
    "Hello" + name
  }

  def main (args: Array[String]) {
    println("Hello Scala!!!")
    //println(hello("Scala"))
    //helloScala()
    //println(add(1,2))
    //println(sum2(2)(3))
    //variableParameter("I","Live","Spark")
    //println(helloDefault())

    var (n,r) = (10,0)
    while(n>0){
      r = r + n
      n = n - 1
    }

    println(r)

    for (i<- 1 to 10 if i%2 == 0){
      println(i)
    }
  }
}
