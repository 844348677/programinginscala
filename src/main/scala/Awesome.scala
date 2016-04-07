/**
  * Created by liuh on 2016/4/6.
  */
class Awesome {
  val size:Int = 10
  val intArray:Array[Int] = new Array[Int](size)
}
class NewClass extends  Awesome{
  override val size:Int= 2
}
class NewClass2 extends {override val size = 2} with Awesome
object Awesome{
  def main(args: Array[String]) {
    val testAwesome = new NewClass
    println(testAwesome.intArray.size)
    println(testAwesome.size)
    //子构造器在做它自己的构造器之前，调用父构造器
    //父构造器字段设置为10
    //初始化数组  调用size（）取值机器
    //方法被重写
    //返回为0
    //设置为0的数组
    //字段设置为2


    //解决方案   val设置为final
    //超类中lazy
    //提前定义



  }
}
