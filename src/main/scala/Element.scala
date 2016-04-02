/**
  * Created by liuhang on 2016/4/3.
  */
abstract class Element {
  def contents : Array[String] //没有实现的方法,是类的抽象成员，具有抽象成员的类，必是抽象类
  def height:Int = contents.length
  def width:Int = if(height==0) 0 else contents(0).length
  //方法不带参数列表，（）都没有 ()是空括号方法  尽量去实现无参方法 ，def可以替换成val，
  //客户代码不应由属性是通过字段实现还是方法实现而受到影响
  //访问字段 比访问方法略快，因为字段在类初始化的时候被预计算
  //方法在每次调用的时候都要计算
  //可以用空括号方法重写无参数方法，如果方法进行了某些事情时，建议使用空括号
}
class ArrayElement(conts:Array[String]) extends Element{
  def contents:Array[String] = conts
  //scala 隐式的继承了scala.AnyRef  与 java.lang.Object相同
}

object test{
  def main(args: Array[String]) {
    val ae = new ArrayElement(Array("hello","world"))
    println(ae.width)
  }
}