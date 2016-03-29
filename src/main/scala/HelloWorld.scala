/**
  * Created by liuh on 2016/3/29.
  */
object HelloWorld {
  def main(args: Array[String]) {
    println("hello world")

    var capital = Map("US"->"Washington","France"->"Paris")
    capital += ("Japan"->"Tokyo")
    println(capital("France"))
    println(factorial(20))

    val name:String = "aabbcc"
    val nameHasUpperCase = name.exists(_.isUpper)

    args.foreach(arg=>println(arg))
    for(arg<-args)
      println(arg)

  }
  def factorial(x:BigInt) : BigInt = if (x==0) 1 else x * factorial(x-1)

  import java.math.BigInteger
  def factorial(x:BigInteger) : BigInteger =
    if(x==BigInteger.ZERO)
        BigInteger.ONE
    else
        x.multiply(factorial(x.subtract(BigInteger.ONE)))

  class MyClass(index:Int,name:String)

  def max(x:Int,y:Int):Int={
    if(x>y)
      x
    else
      y
  }
  def max2(x:Int,y:Int) = if(x>y) x else y
  def greet() = println("aa bb")

}
