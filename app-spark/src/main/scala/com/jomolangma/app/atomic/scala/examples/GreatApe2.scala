class GreatApe(val weight: Double, val age: Int)

class Bonobo(weight: Double, age: Int)
  extends GreatApe(weight, age)

class Chimpanzee(weight: Double, age: Int)
  extends GreatApe(weight, age)

class BonoboB(weight: Double, age: Int)
  extends Bonobo(weight, age)

object GreatApe2 {
  def display(ape: GreatApe) =
    s"weight: ${ape.weight} age: ${ape.age}"

  def main(args: Array[String]) {
    println(display(new GreatApe(100, 12)))
    println(display(new Bonobo(100, 12)))
    println(display(new Chimpanzee(100, 12)))
    println(display(new BonoboB(100, 12)))
  }
}
