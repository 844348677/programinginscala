/**
  * Created by liuh on 2016/4/1.
  */
object FindLongLines {
  def main(args: Array[String]) {
    val width = args(0).toInt
    for(arg <- args.drop(1))
      LongLines.processFile(arg,width)
    //scala FindLongLines 45 LongLines.scala
    //程序应该 被解构成若干小 的函数，每块实现一个定义完备的任务，每块都非常小

    var increase = (x:Int) => x+1
    increase(10)
    increase = (x:Int) => x+ 9999
    increase(10)
    increase = (x:Int) =>{
      println("We")
      println("are")
      println("here!")
      x + 1
    }
    increase(10)
    val someNumbers = List(-11,-10,-5,0,5,10)
    someNumbers.foreach((x:Int)=>println(x))
    someNumbers.filter((x:Int)=>x>0)
    someNumbers.filter((x)=>x>0)
    someNumbers.filter(_>0)

    val f = (_:Int) + (_:Int)
    f(5,10)
    someNumbers.foreach(println _)
    def sum(a:Int,b:Int,c:Int) = a + b + c
    sum(1,2,3)
    val a = sum _ //再将sum传给a
    a(1,2,3)
    //实际发生的事情:名为a的变量指向一个函数值对象。这个函数值是由scala编译器依照部分应用函数表达式sum_,自动产生的一个实例。编译器产生的类有一个
    //apply方法 代三个参数。是因为sum_表达式缺少的参数数量为3.
    //编译器把表达式a(1,2,3)翻译成对函数值apply方法的调用，穿参1,2,3
    a.apply(1,2,3)

    val b = sum(1,_:Int,3)
    b(2)
    someNumbers.foreach(println)

    //(x:Int) => x + more
    var more = 1
    val addMore = (x:Int)=>x+more  //x+1在编写的时候就封闭了，但是more在运行时期创建的函数需要捕获对自由变量more的绑定
    //因此得到的函数值讲指向捕获的more变量的索引
    addMore(10)
    //函数字面量在运行时创建的函数值（对象）被称为闭包：closure
    more = 9999
    addMore(10)

    var summ = 0
    someNumbers.foreach(summ += _)
    println(summ)
    //使用的实例是那个在闭包被创建的时候活跃的
    def makeIncreaser(more:Int)=(x:Int)=>x+more
    val inc1 = makeIncreaser(1)
    val inc2 = makeIncreaser(2)
    inc1(10)
    inc2(10)
    //调用makeIncreser(1）捕获1 当做more的绑定的闭包 被创建返回  返回有一个参数x 回来的结果依赖于闭包被创建时more是如何定义的

    def echo(args:String*) =
      for(arg<-args) println(arg)
    echo("a","b","c")
    val arr = Array("a","b","c")
    //echo(arr)
    echo(arr:_*) //告诉编译器吧arr的每个元素当做参数，而不是当做单一的参数传给echo

/*    def approximate(guess:Double):Double =
      if(isGoodEnough(guess)) guess
      else approximate(improve(guess))*/  //递归调用
/*    def aprroximateLoop(initialGuess:Double):Double={
      var guess = initialGuess
      while(!isGoodEnough(guess))
        guess = improve(guess)
      guess         //循环
    }*/

    def boom(x:Int):Int =
      if(x==0) throw new Exception("boom!")
      else boom(x-1) + 1
    def bang(x:Int):Int =
      if(x==0) throw new Exception("bang!")
      else bang(x-1)
    boom(3)
    bang(5) //-g:notailcalls

    def isEven(x:Int):Boolean =
      if(x==0) true else isOdd(x-1)
    def isOdd(x:Int):Boolean =
      if(x==0) false else isEven(x-1)

    val funValue = nestedFun _
    def nestedFun(x:Int){
      if(x!=0){
        println(x);funValue(x-1)
      }
    }

  }
  import scala.io.Source
  object LongLines{
    def processFile(filename:String,width:Int): Unit ={
      def processLine(line:String): Unit ={
        if(line.length > width)
          println(filename+" : "+line)
      }
      val source = Source.fromFile(filename)
      for(line <- source.getLines)
        processLine(line)
    }
  }
}
