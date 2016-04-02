import java.io.{File, PrintWriter}
import java.util.Date

/**
  * Created by liuhang on 2016/4/2.
  */
object InHome {
  def main(args: Array[String]) {
    def twice(op:Double => Double,x:Double) = op(op(x)) //Double=>Double 带一个Double做参数并返回另一个Double的函数
    println(twice(_ + 1,5))
    println(twice(x=>x+1,5))

    def withPrintWriter(file:File,op:PrintWriter=>Unit): Unit ={
      val writer = new PrintWriter(file)
      try{
        op(writer)
      }finally{
         writer.close
      }
    }
    withPrintWriter(
      new File("date.txt"),
      writer=>writer.println(new Date())
    ) //由withPrintWriter而并非用户代码，确认文件在结尾被关闭
    println("hello,world")
    println{"hello,world"}
    //使用花括号代替小括号 包围 println 参数  仅仅传一个参数时有效！

    def withPrintWriter2(file:File)(op:PrintWriter=>Unit)={
      val writer = new PrintWriter(file)
      try{
        op(writer)
      }finally{
        writer.close
      }
    }
    val file2 = new File("date.txt")
    withPrintWriter2(file2){
      writer => writer.println(new Date())
    }
  }
}
