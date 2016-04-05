/**
  * Created by liuh on 2016/4/5.
  */
class OrderedRational(n:Int,d:Int) {
  val numer = n
  val denom = d
  def < (that:OrderedRational) =
    this.numer * that.denom > that.numer * this.denom
  def > (that:OrderedRational) = that < this
  def <= (that:OrderedRational) = (this<that) || (this == that)
  def >= (that:OrderedRational) = (this>that) || (this == that)
  //这中方式 需要定义一个 <方法，其他三个方法全是用这个构造的
}
//可以使用Ordered 仅仅实现一个方法  compare 则该类具有全套的方法
class OrderedRational2(n:Int,d:Int) extends Ordered[OrderedRational2]{
  val numer = n
  val denom = d

  override def compare(that: OrderedRational2)=
    (this.numer * that.denom) - (that.numer * this.denom)
  //OrderedRational2 混入了Ordered特质，需要定义类型参数 type parameter 混入的是Ordered[C] C是你比较的元素的类
  //compare方法比较两个对象，相等返回一个整数零，小于则是负数，大于正数。
  //在需要排序比较的类的时候，考虑混入Ordered特质
  //但是需要自己定义equals方法，
}

