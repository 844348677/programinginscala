/**
  * Created by liuh on 2016/4/5.
  */
abstract class Expr
//模式匹配和样本类，样本类避免在对象上适用模式匹配时需要大量的固定写法而采用的方式，用来做模式匹配的每个类前加一个case关键字
case class Var(name:String) extends Expr
case class Number(num:Double) extends Expr
case class UnOp(operator:String,args:Expr) extends Expr
case class BinOp(operator:String,left:Expr,right:Expr) extends Expr
//层级包括一个抽象的基类和四个子类
//带有case的类是样本类
object Expr{
  def main(args: Array[String]) {
    val v = Var("x")
    val op = BinOp("+",Number(1),v)
    //case的两个便捷
    //会添加与类名一致的工厂方法，工厂方法嵌套在一起的时候，方法没有new
    //样本类参数列表中的所有参数隐式获得了val前缀，依次被当做字段维护
    println(v.name)
    println(op.left)
    println(op)
    println(op.right==Var("x"))
    //编译器为你的类添加了toString、hashcode和equals的自然实现，打印哈希和比较由类其所有参数组成的整棵数（递归） 结构化的比较

    //UnOp("-",UnOp("-",e)) => e

  }
}
