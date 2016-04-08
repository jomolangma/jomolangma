package com.jomolangma.app.atomic.scala.examples

class Problem(val msg:String)
  extends Exception

object DivZero {
  def f(i:Int) =
    if(i == 0)
      throw new Problem("Divide by zero")
    else
      24/i

  def test(n:Int) =
    try {
      f(n)
    } catch {
      case err:Problem =>
        s"Failed: ${err.msg}"
    }

  def main(args: Array[String]) {
    test(4)
    test(5)
    test(6)
    println(test(0))
    test(24)
    test(25)
  }
}
