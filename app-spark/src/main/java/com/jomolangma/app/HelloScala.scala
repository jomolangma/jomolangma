package com.jomolangma.app

/**
 * Created by zhanglijun on 6/25/15.
 */
class HelloScala {

}
object HelloScala {

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
