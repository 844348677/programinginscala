/**
  * Created by liuh on 2016/4/1.
  */
object FileMatcher {
  def main(args: Array[String]) {
    def containsNeg(nums:List[Int]):Boolean={
      var exists = false
      for(num <- nums)
        if(num<0)
          exists = true
      exists
    }
    containsNeg(List(1,2,3,4))
    containsNeg(List(1,-2,3,4))
    def containsNeg2(nums:List[Int]) = nums.exists(_<0)
    def containsOdd(nums:List[Int]):Boolean = {
      var exists = false
      for(num <- nums)
        if(num % 2 == 1)
          exists = true
      exists
    }
    def containsOdd2(nums:List[Int]) =  nums.exists(_ % 2 == 1)
    def plainOldSum(x:Int,y:Int) = x + y
    plainOldSum(1,2)

    def curriedSum(x:Int)(y:Int) = x + y
    curriedSum(1)(2)
    //实际上接连调用了两个传统的函数，第一个函数调用带单个的名为ｘ的Ｉｎｔ参数，并返回第二个函数的函数值。
    //第二个函数带Ｉｎｔ参数ｙ
    def first(x:Int) = (y:Int) => x + y

    val second = first(1)
    second(2)
    val onePlus = curriedSum(1) _
    onePlus(2)
    //val twoPlus = curriedSum(_)(2)



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
