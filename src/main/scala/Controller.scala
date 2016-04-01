import java.io.{IOException, FileNotFoundException, FileReader}
import java.net.URL

import scala.xml.MalformedAttributeException

/**
  * Created by liuh on 2016/3/31.
  */
object Controller {
  def main(args: Array[String]) {
    val filename =
      if(!args.isEmpty) args(0)
      else "default.txt"
    def gcdLoop(x:Long,y:Long):Long ={
      var a =x
      var b = y
      while(a!=0){
        val temp = a
        a = b %a
        b = temp

      }
      b
    }
    def gcd(x:Long,y:Long):Long =
      if(y==0) x else gcd(y,x%y)
    def greet(){println("hi")} //方法体之前没有 = 等号，所以方法 被定义为结果类型为unit的过程
     //println(greet()==())
    //var 再赋值等式本身也是unit值，这是另一种与此类似的架构。
    var line = ""
    //while((line=readLine()) != "")
      //println("Read: "+line)

    val fileHere = (new java.io.File(".")).listFiles
    for(file<-fileHere)
      println(file)  //打印当前目录所有文件文件夹名字
    // for <- 对任何种类的集合类型都有效
    // 1 to 10  1到10有上边界
    // 1 until 10 1到10 没有上边界10
    //for循环千万不要写  length-1 ！！！数组越界 增加变量 i

    val filesHere = (new java.io.File(".")).listFiles
    for(file <- filesHere if file.getName.endsWith(".scala")) //for循环同时过滤
      println(file)

    for(file <- filesHere if file.isFile;if file.getName.endsWith(".scala"))
      println(file)

    def fileLines(file:java.io.File) = scala.io.Source.fromFile(file).getLines.toList
    def grep(pattern:String) =   //双层嵌套循环 外层循环枚举 filesHere，内层循环所有.scala文件的fileLines(file)
      for(
        file <- filesHere
        if file.getName.endsWith(".scala");
        line <- fileLines(file)
        if line.trim.matches(pattern)
      ) println(file+ " : "+line.trim)
    grep(".*gcd.*")
    val arrayInArray =  Array.ofDim[Int](2,3)
    arrayInArray(0) = Array(1,2,3)
    arrayInArray(1) = Array(2,3,4)
    for(outArray <- arrayInArray; inArray <- outArray) //双层循环二维数组
      println(inArray)

    def grep2(pattern:String) =
      for(
        file <- filesHere
        if file.getName.endsWith(".scala");
        line <- fileLines(file);
        trimmed = line.trim
        if trimmed.matches(pattern) //trimmed被引入到了for循环中
      ) println(file + ": "+trimmed)
    grep(".*gcd.*")

    def scalaFiles =
      for(
        file <- filesHere //filesHere 是什么泛型的 对应生成的数组就是 什么泛型的
        if file.getName.endsWith(".scala")
      ) yield file //构成新的数组
    // for{} yield {}

    val forLineLengths =
      for(
        file <- filesHere
        if file.getName.endsWith(".scala");
        line <- fileLines(file);
        trimmed = line.trim
        if trimmed.matches(".*for.*")
      ) yield trimmed.length
    //使用for循环吧 Array[File] 转变成Array[Int]
    val half = (n:Int) =>
      if (n %2 == 0)
        n/2
      else
          throw new RuntimeException("n must be even")

    try{
      val  f = new FileReader("input.txt")
    }catch{
      case ex: FileNotFoundException => println() //处理丢失的文件
      case ex: IOException => println() //处理其他I/O错误
    }

    val file = new FileReader("input.txt")
    try{
    }finally{
      file.close()
    }

    def urlFor(path:String) =
      try{
        new URL(path)
      }catch{
        case e:MalformedAttributeException =>
          new URL("http://www.scala-lang.org")
      }

    def f():Int = try(return 1 ) finally(return 2) //返回值为2
    def g():Int = try(1)finally(2) //返回值为1

    val firstArg = if(args.length>0) args(0) else " "
    firstArg match{
      case "salt" => println("pepper")
      case "chips" => println("salsa")
      case "eggs" => println("bacon")
      case _ => println("huh?")
    }
    //取消了break和continue 和函数式字面量（口齿）合不好

    var i = 0
    var foundIt = false
    while(i<args.length && !foundIt){
      if(!args(i).startsWith("-")){
        if(args(i).endsWith(".scala"))
          foundIt = true
      }
      i = i +1
    }

    def searcheFrom(i:Int):Int =
      if(i >= args.length) -1
      else if(args(i).startsWith("-")) searcheFrom(i+1)
      else if(args(i).endsWith(".scala")) i
      else searcheFrom(i+1)
    var i = searcheFrom(0)
    val a = 1
    // val a = 2
    println(a)

    val b = 1;
    {
      val b = 2
      println(b)
    }
    println(b)
    //在解释器中 可以进行 val a = 1 ; val a = 1 解释器一行行，一直是嵌套形式的


  }

  def printMultiTable(){
    var i = 1
    while(i<=10){
      var j = 1
      while(j<=1){
        val prod = (i*j).toString
        var k = prod.length
        while(k<4){
          print(" ")
          k +=1
        }
        print(prod)
        j *= 1
      }
      println()
      i += 1
    }
  }

  //以序列形式返回一行乘法表
  def makeRowSeq(row:Int)=
    for(col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4-prod.length)
      padding + prod
    }
  //以字符串形式返回一行乘法表
  def makeRow(row:Int) = makeRowSeq(row).mkString
  //以字符串形式返回乘法表  每行记录占一行字符串
  def multiTable() = {
    val tableSeq = // 行记录字符串的序列
      for(row <- 1 to 10)
        yield makeRow(row)
    tableSeq.mkString("\n")
  }

}
