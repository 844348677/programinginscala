/**
  * Created by liuh on 2016/4/5.
  */
object ScalaAny {
  // final def ==(that:Any):Boolean
  // final def !=(that:Any):Boolean
  //def equals(that:Any):Boolean
  //def hashCode:Int
  //def toString:String
  //scala中每个类都继承自通用的名为Any的超累 Null和Nothing扮演通用的子类，Nothing是所有其他类的子类

  //Any的两个子类  AnyVal和AnyRef
  //AnyVal是scala里每个内建值的父类  8个java基本类型 + Unit
  //他们的值在运行时表示成java的基本类型的值
  //这些值类都被定义既是抽象的又是final的
  //Unit对应的void类型，Unit只有一个实例值 ()

  //值类都是AnyVal的子类，但是不是其他类的子类，需要的时候  其之间可以进行隐式转化
  //Int中没有的函数，转成RichInt
  //AnyRef是所有的引用类的基类，类似java中的Object
  //还有一个ScalaObject 特别的记号特质，只有一个方法$tag，在内部使用以加速模式匹配
  /*
  boolean isEqual(int x,int y){
    return x == y;
  }
  boolean isEqual(Integer x,Integer y){
    return x==y;
  }
   */
  def isEqual(x:Int,y:Int) = x==y
  def isEqual2(x:Any,y:Any) = x==y

  //scala == 被设计为对类型表达透明 对之类型就是自然相等，对于引用类型，==被视为继承Object的equals方法的别名
  //==在引用类型中初始定义为引用相等，但是许多子类中重写实现了 自然理论上的相等性 字符串内容相等，不会像java那样
  //eq ne 不能被重写并且实现为引用相等
  def main(args: Array[String]) {
    val x = "abcd".substring(2)
    val y = "abcd".substring(2)
    println(x==y)
  }
}
