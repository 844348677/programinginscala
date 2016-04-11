package bda

/**
  * Created by liuh on 2016/4/11.
  */
object TestMethod {
  def main(args: Array[String]) {
/*    val bda2 =List.range(1,10) flatMap (i => List.range(1,i) map (j => i+" "+j ))
    bda2.foreach(println(_))*/
    val testArray = Array(1,2,3,4,5,6) //3行2列的矩阵
    val matrix = Array.ofDim[Int](3,2)
    //for(matrix)

/*    val bda2 =List.range(2,139) flatMap (i => List.range(2,i) map (j => i+" "+j ))
   bda2.foreach(println)*/

    val bda4 = for(i <- List.range(2,139+1); j <- List.range(2,i); k <- List.range(2,j);l <- List.range(2,k)) yield i+" "+j+" "+k+" "+l

    for(i <- 0 to 500)
      println(bda4(i))
  }
}
