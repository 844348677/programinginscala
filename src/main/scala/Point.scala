/**
  * Created by liuh on 2016/4/5.
  */
class Point(val x:Int,val y:Int) {
}
class Rectangle(val topLeft:Point,val bottomRight:Point){
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}
abstract class Component{
  def topLeft:Point
  def bottomRight:Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}
trait Rectangular{
  def topLeft : Point
  def bottomRight : Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}
abstract class Component2 extends Rectangular{
}
class Rectangle2(val topLeft:Point,val bottomRight:Point) extends Rectangular{

}

