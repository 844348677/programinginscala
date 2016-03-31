import breeze.numerics._
import org.apache.spark.{SparkContext, SparkConf}

import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer
/**
  * Created by liuh on 2016/3/30.
  */
object Test {



  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("NewMethod").setMaster("spark://10.0.0.151:7077").setJars(List("/home/liuh/workspace/class3/out/artifacts/class3_jar/class3.jar"))
    val sc = new SparkContext(conf)

    val colmSize :Int =  1566
    val rowSize :Int = 139

    val lb = new ListBuffer[String]

    for(i <- 1.to(500000)){
      val randomArray = new Array[Int](16)
      for(i<-1.to(16)){
        val ii = Math.random().*(rowSize-2).toInt+2

        randomArray(i-1) = ii
      }
      val distArray = randomArray.sorted.distinct
      val sb = new StringBuilder
            for(value <- distArray)
              sb.append(value+" ")
      lb.append(sb.toString)
    }
    val result = lb.toList
    //result.foreach(println(_))

    val lines = sc.parallelize(result)

    val matrix = sc.textFile("hdfs://10.0.0.151:9000/input/train01rmstruct.txt").flatMap(_.split("\t")).map(_.toInt)

    val localArray = matrix.collect
    System.out.println("size: "+localArray.length)

        for(i <- 1 to 100)
          println(i+"  "+localArray(i))

    val arrayMatrix = Array.ofDim[Int](rowSize,colmSize)
    for(i <- 0 to rowSize-1){
      for(j <- 0 to colmSize-1){
        arrayMatrix(i)(j) = localArray(i*(colmSize)+j)
      }
    }
    val arrayY = new Array[Int](colmSize)
    for(i <- 0 to colmSize-1){
      arrayY(i) = localArray(i)
    }

    val barY = arrayY.sum.toDouble./(arrayY.length)

    val pickRow = (index :Array[Int]) => {
      val returnResult = Array.ofDim[Int](index.length,colmSize)
      for(i <- 0.to(index.length-1))
        returnResult(i) = arrayMatrix(index(i)-1)
      returnResult
    }
    val string2int = (stringArray:Array[String]) =>{
      val intArray = new Array[Int](stringArray.length)
      for(i <- 0.to(stringArray.length-1))
        intArray(i) = stringArray(i).toInt
      intArray
    }

    val computeIscore = (matrixs:Array[Array[Int]]) =>{
      val peopleKind = pow(2,matrixs.length)
      val kindYSum = new Array[Int](peopleKind)
      val kindYSize = new Array[Int](peopleKind)
      var s:Double = 0.0

      for(i <- 0.to(matrixs(0).length-1)){
        var kindIndex:Int = 0
        for(j <- 0.to(matrixs.length-1)){
          kindIndex *= 2
          kindIndex += matrixs(j)(i)
        }
        kindYSum(kindIndex) += arrayY(i)
        kindYSize(kindIndex) += 1
      }

      for(i <- 0.to(peopleKind-1)){
        if(kindYSize(i)>0){
          val t = pow(kindYSum(i) - barY.*(kindYSize(i)),2)
          s += t
        }
      }
      s
    }

    val dropOne = (index:Array[Int],matrix:Array[Array[Int]],score:Double) =>{
      var bestScore:Double = score;
      var bestArray = index

/*      for(intValue <- index){
        intValue
      }*/
      //HashMap<int,String> aa = new HashMap
      val aa = new HashMap[Int,String]


      for(i <- 0.to(index.length-1)){
        val indexBuffer = index.toBuffer
        indexBuffer.remove(i)
        val newIndex =indexBuffer.toArray
        val newMatrix = pickRow(newIndex)

        val newScore = computeIscore(newMatrix)

        if(newScore >= bestScore){
          bestScore = newScore
          bestArray = newIndex
        }
      }
      Tuple2(bestArray,bestScore)
    }

    val bestInArray = (index:Array[Int],matrix:Array[Array[Int]],score:Double) =>{

      var tuple2 = new Tuple2(index,score)
      var newTupleArraySize = 0
      var loopId = 0
      while(tuple2._1.length != newTupleArraySize && tuple2._1.length != 1){

        newTupleArraySize = tuple2._1.length
        tuple2 = dropOne(tuple2._1,null,tuple2._2)

        loopId += 1
      }

      tuple2
    }

    val happy = lines.map(_.split(" ")).map(string2int(_)).map(x=>bestInArray(x,null,computeIscore(pickRow(x))))
    /*    val result5 = happy.collect
        for(i <- 1.to(result5.length-1)){
          print(result5(i)._1.foreach(x=>print(x+",")))
          print("  "+result5(i)._2)
          println
        }*/
    happy.repartition(1).saveAsTextFile("hdfs://10.0.0.151:9000/output/loop500000")

  }

}
