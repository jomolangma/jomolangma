package com.jomolangma.app.scala

/**
 * Created by zhanglijun on 5/10/15.
 */
class OOPInScala {

}

//class Person{
//  var name:String = _
//  var age = 27
//  private[this] val gender = "male"
//}

class Person(val name:String,val age:Int){
  println("This is the primary constructor!!!")
  var gender:String = _

  def this(name:String,age:Int,gender:String){
    this(name,age)
    this.gender = gender
  }
}

object OOPInScala{
  def main (args: Array[String]) {
    val p = new Person("Rockey",27,"male")
    println(p.name + ":" + p.gender)
  }
}
