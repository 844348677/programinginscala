/**
  * Created by liuh on 2016/4/9.
  */
class SlowAppendQueue[T](elems:List[T]) {
  def head = elems.head
  def tail = new SlowAppendQueue(elems.tail)
  def append(x:T) = new SlowAppendQueue(elems ::: List(x))


}

class Queue[T](
  private val leading :List[T],
  private val trailing:List[T]){
  private def mirror =
    if(leading.isEmpty)
      new Queue(trailing.reverse,Nil)
    else
      this
  def head = mirror.leading.head
  def tail ={
    val q = mirror
    new Queue(q.leading.tail,q.trailing)
  }
  def append(x:T) =
    new Queue(leading,x::trailing)

}