package bda

import breeze.numerics._
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by liuh on 2016/4/11.
  */
object BDA3 {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("NewMethod").setMaster("spark://10.0.0.151:7077").setJars(List("D:\\workspace4scala\\rbda\\out\\artifacts\\rbda_jar\\rbda.jar")).set("spark.executor.memory", "12g").set("spark.executor.cores","8")
    val sc = new SparkContext(conf)

    val colmSize :Int =  1566
    val rowSize :Int = 139
    //生成bda3随机数列 2-rowSize,穷举,第一行为Y值 ！！后面有加1 rowSize+1
    val bda3 = for(i <- List.range(2,rowSize+1); j <- List.range(2,i); k <- List.range(2,j)) yield i+" "+j+" "+k
    val lines = sc.parallelize(bda3)
    val matrix = sc.textFile("hdfs://10.0.0.151:9000/input/train01rmstruct.txt").flatMap(_.split("\t")).map(_.toInt)

    val localArray = matrix.collect //所有数据组成一个大Array OopsOOM！！！！
    val arrayMatrix = Array.ofDim[Int](rowSize,colmSize) //构造二维数组  代码写得很难看，待修改
    for(i <- 0 to rowSize-1){
      for(j <- 0 to colmSize-1){
        arrayMatrix(i)(j) = localArray(i*(colmSize)+j)
      }
    }

    val arrayY =arrayMatrix(0)      //矩阵第一行为Y值
    val barY = arrayY.sum.toDouble./(arrayY.length)///arrayY.length ///arrayY.length
    //println("平均值："+barY)
    def pickRow(index :Array[Int]) = for(i <- index) yield arrayMatrix(i-1)
    def string2int(stringArray:Array[String]) =   for(i <- stringArray) yield i.toInt  //Array[String] => Array[Int]

    val computeIscore = (matrixs:Array[Array[Int]]) =>{ //很难看，待重写
      val peopleKind = pow(2,matrixs.length) //因为01 所以有 2^属性个数 这么多种人
      val kindYSum = new Array[Int](peopleKind) //用来求该类人的Y值总和
      val kindYSize = new Array[Int](peopleKind) //用来求该类人有多少
      var s:Double = 0.0

      for(i <- 0.to(matrixs(0).length-1)){ //循环样本个数
      var kindIndex:Int = 0
        for(j <- 0.to(matrixs.length-1)){  //循环每个人选中的16个属性
          kindIndex *= 2
          kindIndex += matrixs(j)(i) //转码
          /*
          111 000 100 110 101 011 010 001
          7 00000001 0 00000000 4 00001000 6
           */
        }
        kindYSum(kindIndex) += arrayY(i)  //这种人的 Y值的总和
        kindYSize(kindIndex) += 1 //统计这种人有多少个
      }

      for(i <- 0.to(peopleKind-1)){
        if(kindYSize(i)>0){
          val t = pow(kindYSum(i) - barY.*(kindYSize(i)),2) //总和-Y平均值×该类人个数
          s += t
        }
      }
      s
    }

    val happy = lines.map(_.split(" ")).map(string2int(_)).map(x=>(computeIscore(pickRow(x)),x.mkString(",")))
    val sorted = happy.sortByKey(false)
    val result = sorted.collect()
    for(i <- 0 to 500)
      println(result(i))

  }
}
