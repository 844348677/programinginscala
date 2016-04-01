/**
  * Created by liuh on 2016/4/1.
  */
object FileMatcher {
  def main(args: Array[String]) {

  }
  private def filesHere = (new java.io.File(".")).listFiles
  def filesEnding(query:String) =
    for(file <-filesHere;if file.getName.endsWith(query))
        yield file
  def fileContaining(query:String) =
    for(file <- filesHere;if file.getName.contains(query))
      yield file
  def filesRegex(query:String) =
    for(file <- filesHere;if file.getName.matches(query))
      yield file
  def filesMatching(query:String,matcher:(String,String)=>Boolean)={
    for(file<-filesHere;if matcher(file.getName,query))
      yield file
  }

  def filesEnding2(query:String) =
    filesMatching(query,_.endsWith(_))
  def filesContaining2(query:String) =
    filesMatching(query,_.contains(_))
  def filesRegex2(query:String) =
    filesMatching(query,_.contains(_))
  //_.endsWith(_) 的意思
  //(fileName:String,query:String) => fileName.endsWith(query)

  private def filesMatching3(matcher:String => Boolean) =
    for(file <- filesHere;if matcher(file.getName))
      yield file

  def filesEnding3(query:String) =
    filesMatching3(_.endsWith(query))
  def filesContaining3(query:String) =
    filesMatching3(_.endsWith(query))
  def filesRegex3(query:String)=
    filesMatching3(_.endsWith(query))

}
