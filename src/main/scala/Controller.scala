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
    println(greet()==())
    //var 再赋值等式本身也是unit值，这是另一种与此类似的架构。
    var line = ""
    while((line=readLine()) != "")
      println("Read: "+line)


  }
}
