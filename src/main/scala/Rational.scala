/**
  * Created by liuh on 2016/3/31.
  */
class Rational(n:Int,d:Int) { // n 和 d 是传入的，只在对象内部才能访问
  def this(n:Int) = this(n,1)
  private val g = gcd(n.abs,d.abs)
  val numer:Int = n / g
  val denom:Int = n / g
  println("Created: "+numer+"/"+denom)
  override def toString = numer + "/" + denom
  require(d!=0) //require为真 正常返回，否则抛出IllegalArgumentException异常
  //require方法定义在scala包的孤立对象Prefer上 Prefer对象的成员都已被自动引入到每个Scala的源文件中了
  def add(that:Rational):Rational =
    new Rational(numer*that.denom + that.numer*denom,denom*that.denom)
  def lessThan(that:Rational) =
    this.numer * that.denom < that.numer * this.denom
  def max(that:Rational) =
    if(this.lessThan(that)) that else this

  def gcd(a:Int,b:Int):Int =
    if(b == 0) a else gcd(b,a%b)
  def +(that:Rational):Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  def *(that:Rational):Rational =
    new Rational(numer * that.numer,denom*that.denom)

  def +(i:Int) =
    new Rational(numer + i*denom,denom)
  def -(that:Rational):Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )
  def -(i:Int):Rational =
    new Rational(numer - i*denom,denom)
  def *(i:Int):Rational =
    new Rational(numer*i,denom)
  def /(that:Rational):Rational =
    new Rational(numer*that.denom,denom*that.numer)
  def /(i:Int):Rational =
    new Rational(numer,denom*i)


}
object Rational{
  def main(args: Array[String]) {
    val x = new Rational(1,3)
    println(x)
    implicit def intToRational(x:Int) = new Rational(x)
    //可以使用 2 * r:Rational
  }
}
