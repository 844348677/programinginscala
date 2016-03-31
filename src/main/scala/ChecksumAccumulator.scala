/**
  * Created by liuh on 2016/3/30.
  */
class ChecksumAccumulator {
  private var sum=0
  def add(b:Byte):Unit = {
    sum += b
  }
  def checksum(): Int = {
    return ~(sum & 0xFF) + 1 // ？？？？？
  }
}
object ChecksumAccumulator {
  private val cache = Map[String,Int]()
  def calculate(s:String):Int =
    if(cache.contains(s))
      cache(s)
    else{
      val acc = new ChecksumAccumulator
      for(c<-s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      //cache += (s->cs)
      cs
    }
}
