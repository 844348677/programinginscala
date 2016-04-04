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
class ArrayElement2(conts:Array[String]) extends Element{
  val contents:Array[String] = conts
  //scala里面字段和方法属于相同的命名空间
  //从一个方法变成一个字段，无需修改抽象方法定义
  //scala中禁止 在用一个类里面使用同样的名称定义字段和方法
  //java四个命名空间（字段，犯法，类型，包
  //scala两个明明空间 值（字段，方法，单利对象） 类型（类和特质）
  //scala吧字段和方法放进统一明明空间理由明确，可以实现使用val重写无参数方法
}
class ArrayElemet3(val contents:Array[String]) extends Element //单一的参数化字段
//contents参数的前缀是val，这是同事定义同名参数和字段的一个简写方式

object test{
  def main(args: Array[String]) {
    val ae = new ArrayElement(Array("hello","world"))
    //子类型化 是指子类的值可以在任何需要其超累的值得地方使用
    println(ae.width)
  }
}