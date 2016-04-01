import scala.io.Source

/**
  * Created by liuh on 2016/4/1.
  */
object LongLines {
  def processFile(filename:String,width:Int){
    val source = Source.fromFile(filename)
    for(line <- source.getLines)
      processLine(filename,width,line)
  }
  private def processLine(filename:String,width:Int,line:String){
    if(line.length > width)
      println(filename + ": "+line.trim)
  }
}
