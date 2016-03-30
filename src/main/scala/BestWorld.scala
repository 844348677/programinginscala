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

    def formatArgs(args:Array[String]) = args.mkString("\n")
    val res = formatArgs(Array("zero","one","two"))
    assert(res == "zero\none\ntwo") //断言
    import scala.io.Source
    val filename = "README.md"
    for(line <- Source.fromFile(filename).getLines()) //枚举器，循环之后消失
      println(line.length+" "+line)
    val lines = Source.fromFile(filename).toList
    def widthOfLength(s:String) = s.length.toString.length
    var maxWidth = 0
    for(line <- lines){
      //maxWidth = maxWidth.max(widthOfLength(line))
    }

    class ChecksumAccumulator {
      private var sum=0
      def add(b:Byte):Unit = {
        sum += b
      }
      def checksum(): Int = {
        return ~(sum & 0xFF) + 1 // ？？？？？
      }
    }
    val acc = new ChecksumAccumulator
    val csa = new ChecksumAccumulator //指向的对象不可变，但对象里里面的属性sum是可变的

    def add(b:Byte):Unit = {
      //b = 1  传进方法里面的参数 都是val类型的，不能进行赋值 方法去掉等号之后 所有的返回值都为 Unit
    }


  }

}
