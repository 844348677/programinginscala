/**
  * Created by liuh on 2016/3/31.
  */
object StringIndex {
  def main(args: Array[String]) {
    val s = "Hello, world"
    println(s indexOf 'o') //地o的char在哪
    println(s indexOf ('o',5)) //从s的第五个字符串开始之后o的位置
    //四种不恩能够当做前缀操作符用的标识符   *
    s toLowerCase  //后缀操作符 写法很随意

    //&& 和 || 会出现短路情况

    def salt() ={ println("salt");false }
    def pepper() ={ println("pepper");true}

    val a = pepper && salt
    val b = pepper || salt

    //scala中的 == 与java大不相同
    //1 == 2 //true
   // List(1,2,3) == List(1,2,3) //true
    //1 == 1.0 //true
    //List(1,2,3) == null
    //null == List(1,2,3)
    // == 在scala中被加工了，左边部位null则调用左边数的 equal方法。
    //== 在java中用来比较对象引用和基本类型比较。
    //scala中用 eq ne
    //2 << 2 + 2
    //2 + 2 << 2
    //scala 中 < 在 + 之后操作  等号的优先级最低
    //var x = 1
    //var y = 1
    //x *= y + 1
    //任何 以 : 结尾的操作符  都是调用其右操作符的方法
    // a ::: b == b.:::(a)
    // a:::b  {val x=a;b.:::(x)}   a比b之前评估
    //永远是左边操作符先评估
  }
}
