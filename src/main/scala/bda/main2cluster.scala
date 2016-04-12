package main.scala.bda

/**
  * Created by liuh on 2016/4/12.
  */
object main2cluster {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("localSpark").setMaster("local")  //本机测试环境
    val sc = new SparkContext(sparkConf)
    val trainFile = sc.textFile("src/main/resources/Train.txt").map(_.split("\t"))//文件读入，并且每行构造一个数组
    def string2double(stringArray:Array[String]):Array[Double] = for(s <- stringArray) yield s.toDouble //Array[String] => Array[Double]

    def loop_clustering(trainArray : Array[Double]) ={ //不重复元素判断
      val orderSet = trainArray.toSet //去重
      orderSet.size match {
        case x if x < 3 => (orderSet.head + orderSet.last)/2 //之后1-2个不同元素
        case _ => isNotBinary(trainArray.sortWith(_<_)) //有三个及以上的不同元素 排好序传过去
      }
    }
    def isNotBinary(trainArray : Array[Double]) ={ //不重复元素个数超过3，进行计算
    //接下来的代码全抄cpp语言
      val size = trainArray.size
      val w = new Array[Double](size - 1)
      for(i <- 0.to(size-2)){
        for(k1 <- 0 to i;k2 <- 0 to k1)
          w(i) = w(i) + (trainArray(k1)-trainArray(k2))*(trainArray(k1)-trainArray(k2))
        for(k1 <- (i+1) to (size-1);k2 <- (i+1) to k1)
          w(i) = w(i) + (trainArray(k1)-trainArray(k2))*(trainArray(k1)-trainArray(k2))
      }
      var minIndex = 0
      for(i <- 0 to (size-3))
        if(w(i) >= w(i+1)) minIndex = i+1
        else w(i+1) = w(i)
      val cutValue = (trainArray(minIndex) + trainArray(minIndex+1))/2
      println(cutValue)
      cutValue
    }
    trainFile.map(string2double(_)).map(loop_clustering(_)).collect().foreach(println)

  }
}
