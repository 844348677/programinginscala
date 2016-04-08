/*
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


    val capitals = Map("France" -> "Paris","Japan" -> "Tokyo")
    //capitals get "France" //some(Paris) None对象
    def show(x:Option[String]) = x match{ //分离可选址
        case Some(s) => s
        case None => "?"
    }

    show(capitals get "Japan")
    show(capitals get "France")
    show(capitals get "North Pole")
    //Option的有点很多，而且会经常用到，
    //HashMap[Int,Int]不能返回null以表明没有元素
    //Option[String]类型的变量是可选的String，这比String类型的变量或可能有时是null来说要更为明显
    //之前描述的因为使用可能为null而没有首先检查是否为null的变量产生的变异错误在scala里变为类型错误。
    //如果Option[String]类型的，你打算当做String使用，你的Scala程序就不会编译通过

    val myTuple = (123,"abc")
    val (number,string) = myTuple
    println(number)
    println(string)

    val exp = new BinOp("*",Number(5),Number(1))
    val BinOp(op2,left2,right2) = exp

    val withDefault:Option[Int] => Int = {
      case Some(x) => x
      case None => 0
    }
    println(withDefault(Some(10)))
    println(withDefault(None))
    //下面的额偏函数接受整数列表，返回第二个元素
/*    val second: List[Int] => Int ={
      case x::y::_ => y
    }*/
    //编译器提示 match is not exhaustive
    //second(List(5,6,7)) second(List())
    //要检查一个偏函数有定义，告诉编译器你知道正在使用的是偏函数， List[Int] => Int 不仅包含了偏函数，从整数列表到整数的所有函数。
    //仅包含从整数列表到整数的偏函数，写成PartialFunction[List[Int],Int]
    val second2:PartialFunction[List[Int],Int] ={
      case x :: y :: _ => y
    }
    second2.isDefinedAt(List(5,6,7))
    second2.isDefinedAt(List())
    //偏函数有一个isDefinedAt方法，可以用来测试时候函数对某个特定值有意义，本例函数对至少两个元素的列表有意义
    //带有PartialFunction的时候
    //对模式执行两次翻译  一次是真正函数的实现,另一次是测试函数是否对特定参数有意义的实现
    new PartialFunction[List[Int],Int] {
      def apply(xs:List[Int]) = xs match {
        case x::y::_ =>y
      }
      override def isDefinedAt(xs:List[Int]) = xs match{
        case x::y::_ =>true
        case _ => false
      }
    }
    for((country,city) <- capitals)
      println("the capital of "+country+" is "+city)
    val results = List(Some("apple"),None,Some("orange"))
    for(Some(fruit) <- results)
      println(fruit)

  }
  def simplifyTop(expr:Expr) : Expr = expr match {
    case UnOp("-",UnOp("-",e)) => e
    case BinOp("+",e,Number(0)) => e
    case BinOp("*",e,Number(1)) => e
    case _ => expr
  }
  def describe(x:Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }
  import Math.{E,PI}
  E match {
    case PI => "strange math ? PI "+PI
    case _ => "OK"
  }
  val pi = Math.PI
  E match {
    case pi => "strange math ? PI "+pi
    //使用小写字母开始的简单名当做模式变量；所有其他的引用被认为是常亮
    //pi是变量模式，它可以匹配任意输入
    //仍然有两种手法，给模式常量使用小写字母，this.pi或obj.pi, `pi` 被解释为常量
  }
  E match {
    case `pi` => "strange math ? pi "+pi
    case _ => "OK"
  }
  Thread.`yield`()
  //`符号处理小写字符标识符当作模式匹配常量，处理关键字当作普通的标识符问题

  def deepMatch(expr:Expr) = expr match {
    case BinOp("+",e,Number(0)) => println("a deep Match")
    case _ =>
      //scala支持深度匹配，构造器模式匹配，首先检查对象是该名称样本类的成员，然后检查对象构造器参数是否符合额外提供的模式
  }
  def listMath(list:List[Int]) = list match {
    case List(0,_,_) => "List match"
    case _ =>
  }
  def listMatch2(list:List[Int]) = list match {
    case List(0,_*) => "list match2 "
    case _ =>
  }
  def tupleDemo(expr:Any) = expr match{
    case (a,b,c) => println("matched "+a+b+c)
    case _ =>
  }
  println(tupleDemo(("a ",3,"tuple")))

  //类型模式，类型测试和类型转换的简易替代
  def generalSize(x:Any) = x match {
    case s:String => s.length
    case m:Map[_,_] => m.size
    case _ => -1
      //s:String 是类型匹配，匹配所有的非空String实例，模式变量s指代了字符串
  }
  //可以理解为，传入的参数我也不知道是什么，但是匹配到具体的类型的时候，我可以使用该类型的方法
  println(generalSize("abc"))
  println(generalSize(Map(1->'a',2->'b')))
  println(Math.PI)

  val x = "abc"
  if(x.isInstanceOf[String]){
    val s = x.asInstanceOf[String]
    s.length
  }
  def isIntIntMap(x:Any) = x match {
    //case m:Map[Int,Int] => true //匹配不到Int，Int
    case _ => false
  }
  //泛型擦除，类型参数没有保留到运行期，没办法判断Mpa是两个Int还是其他的，
  //唯一例外是数组，数组的元素类型和数组值保存在一起，可以进行模式匹配
  def isStringArray(x:Any) = x match {
    case a: Array[String] => "yes" //可以匹配到String
    case _ =>
  }

  //变量绑定，
  def valMatch(expr:Expr) = expr match {
    case UnOp("abs",e@UnOp("abs",_)) => e
    case _ =>
  }
  /*def simplifyAdd(e:Expr) = e match {
    case BinOp("+",x,x) => BinOp("*",x,Number(2))
    case _ =>
  }*/
  //scala要求的模式是线性的：模式变量仅允许在模式中出现依次。不过你可以使用模式守卫重新
  def simplifyAdd(e:Expr) = e match {
    case BinOp("+",x,y) if x == y => //if x==y 模式守卫  以if开始，里面是布尔表达式，使用模式中的变量，返回true才匹配
      BinOp("*",x,Number(2))
    case _ => e
      //case n : Int if 0 < n => ..
      //case s : String if s(0) == 'a' => ..
  }
  def simplifyAll(expr:Expr): Expr = expr match {
    case UnOp("-",UnOp("-",e)) =>
      simplifyAll(e) // "-" 是自身的反转
    case BinOp("+",e,Number(0)) =>
      simplifyAll(e) // "0"对于'+'来说不改变结果
    case BinOp("*",e,Number(1)) =>
      simplifyAll(e)
    case UnOp(op,e) =>
      UnOp(op,simplifyAll(e))
    case BinOp(op,l,r) =>
      BinOp(op,simplifyAll(l),simplifyAll(r))
    case _ => expr
  }
  /*
  def simplifyBad(expr:Expr):Expr = expr match{
    case UnOp(op,e) => UnOp(op,simplifyBad(e))
    case UnOp("-",UnOp("-",e)) => e
  }
   */
  def describe(e:Expr):String = (e: @unchecked) match{
    case Number(_) => "a number"
    case Var(_) => "a variable"
  }



}
//match是scala的表达式，scala的备选项表达式永远不会掉到下一个case，如果没有模式匹配，MatchError异常会被抛出

*/
