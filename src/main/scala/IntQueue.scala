import scala.collection.mutable.ArrayBuffer

/**
  * Created by liuh on 2016/4/5.
  */
abstract class IntQueue {
  def get():Int
  def put(x:Int)
}
class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int] //定义数组缓存
  def get() = buf.remove(0)
  def put(x:Int): Unit ={
    buf += x
  }
}
trait Doubling extends IntQueue{ //trait定义了超类IntQueue，这意味这trait只能混入扩展了IntQueue的类中
  //super调用，在普通类中是非法的，但是对于特质是成功的，因为特质里的super调用是动态绑定的，混入另一个特质和类之后，有具体的方法定义才工作

  abstract override def put(x:Int): Unit ={
    super.put(2*x)
  } //必须打上abstract override的标志，这种标志组合仅在特质成员的定义中被认可，在类中则不行，
    //  表示特质必须被混入某个具有期待方法的具体定义的类中

}
trait Incrementing extends IntQueue{
  abstract override def put(x:Int): Unit ={
    super.put(x+1)
  }
}
trait Filtering extends IntQueue{
  abstract override def put(x:Int): Unit ={
    if(x >= 0) super.put(x)
  }
}
object IntQueue{
  def main(args: Array[String]) {
    val queue = (new BasicIntQueue with Incrementing with Filtering)
    val queue2 = (new BasicIntQueue with Filtering with Incrementing)
    //混入的次序很重要，最右边的先混入，然后依次向左混入，意思是先调用最右边的方法
  }
}
//trait不同于多重继承，多重继承的时候，super调用导致的方法调用可以在调用的地方明确，但是特质如上，被混入的时候决定，
//混入到类的特质的线性化，导致trait可堆叠的行为
