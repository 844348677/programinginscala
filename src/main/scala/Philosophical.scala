/**
  * Created by liuh on 2016/4/5.
  */
trait Philosophical {
  def philososphize(): Unit ={
    println("I consume memeory,therefore I am!")
  }
}
class Animal
trait HasLegs

class Frog extends Animal with Philosophical with HasLegs{ //混入特质 使用 extends和with
  override def toString = "green"
  override def philososphize: Unit ={
    println("It is not esay being "+ toString + "!")
  }
}
//trait 可以带有方法的具体实现，可以生命字段和维持状态值，可以用来做类的任何事情，但除这两点
//trait的主构造器不带任何参数，不能有任何类的参数
//trait中的super是动态绑定的，类中的super是固定的，是静态的，但是trait中的super，看其混入的具体类的具体的super，动态绑定

object test1{
  def main(args: Array[String]) {
    val frog = new Frog
    println(frog)
    println(frog.philososphize)
    val phil:Philosophical = frog

  }
}