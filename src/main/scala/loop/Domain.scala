package loop

/**
  * Created by liuh on 2016/4/19.
  */
object Domain {
  def apply(parts:String*):String = parts.reverse.mkString(".")
  def unapplySeq(whole:String):Option[Seq[String]] = Some(whole.split("\\.").reverse)
}
