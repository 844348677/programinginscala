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


  }
}
