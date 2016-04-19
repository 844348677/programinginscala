package loop

/**
  * Created by liuh on 2016/4/19.
  */
object Twice {
  def apply(s:String):String= s+s
  def unapply(s:String):Option[String]={
    val length = s.length /2
    val half = s.substring(0,length)
    if(half == s.substring(length)) Some(half) else None
  }
}
object UpperCase{
  def unapply(s:String):Boolean = s.toUpperCase == s
}