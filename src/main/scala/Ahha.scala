/**
  * Created by liuh on 2016/4/7.
  */
object Ahha {
  def main(args: Array[String]) {
    val bda2 =List.range(1,10) flatMap (i => List.range(1,i) map (j => (i,j)))
    bda2.foreach(println(_))

    //val newList3 = List.range(1,4) flatMap (i=>List.range(1,i))

    val bda3 = for(i <- List.range(1,101); j <- List.range(1,i); k <- List.range(1,j)) yield(i,j,k)
    bda3.foreach(println(_))
    println(bda3.size)
  }
}
