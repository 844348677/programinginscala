package impli

/**
  * Created by liuh on 2016/4/18.
  */
object TestImplic {
  def main(args: Array[String]) {
    implicit def doubleToInt(x:Double) = x.toInt
    val i:Int = 3.5
    println(i)
    val j:Int = doubleToInt(3.5)

  }
}
