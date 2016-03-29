/**
  * Created by liuh on 2016/3/29.
  */
object BestWorld {
  def main(args: Array[String]) {
    val big = new java.math.BigInteger("12345")
    val greetingStrings = new Array[String](3)
    greetingStrings(0) = "hello" // greetingStrings.update(0,"hello")
    greetingStrings(1) = ","
    greetingStrings(2) = "world!\n"

    for( i <- 0 to  2){ //循环to Array里面所有数组  循环until除最后一个 如果方法只有一个参数， 则可以 1 to 2
      print(greetingStrings(i)) // greetingString.apply(i)
    }
    val numNames = Array("zero","one","two")
    val numNames2 = Array.apply("zero","one","two")
    //val oneTwoThree = List(1,2,3) //List所有都是不可变的

    val oneTwo = List(1,2)
    val threeFour = List(3,4)
    val oneTwoThreeFour = oneTwo ::: threeFour
    println(oneTwo)
    println(threeFour)
    println(oneTwoThreeFour)

    val twoThree = List(2,3)
    val oneTwoThree = 1 :: twoThree //:: 是twoThree里面的方法，是右操作符调用 不同与 1+2
    println(oneTwoThree)

    val testNil = 1 :: 2 :: 3 :: Nil
    println(testNil)

    val pair = (99,"Luftballons") //tuple 不能用(i) 因为apply方法始终返回同样的类型
    println(pair._1)
    println(pair._2)

    var jetSet = Set("Boeing","Airbus")
    jetSet += "Lear" //set分为可变和不可变，可变是同一个set，不可变是生成新set
    println(jetSet.contains("Cessna"))

    import scala.collection.mutable.Set

    val movieSet = Set("hitch","poltergeist")
    movieSet += "Shark"
    println(movieSet)

    import scala.collection.mutable.Map
    val treasureMap = Map[Int,String]()
    treasureMap += (1->"go to island")
    treasureMap += (2->"find big x on ground")
    treasureMap += (3->"big")
    println(treasureMap)

  }
}
