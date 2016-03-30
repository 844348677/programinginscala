/**
  * Created by liuh on 2016/3/30.
  */
import ChecksumAccumulator.calculate
object Summer {

  def main(args: Array[String]) {
    for(arg <- args)
      println(arg+ ": "+calculate(arg))
  }
}
