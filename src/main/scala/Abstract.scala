/**
  * Created by liuhang on 2016/4/10.
  */
trait Abstract {
  type T
  def transform(x:T) :T
  val initial:T
  var current:T
}
class Conrete extends Abstract{
  type T =String
  def transform(x:String) = x+x
  val initial = "hi"
  var current = initial
}
