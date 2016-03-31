import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by liuh on 2016/3/31.
  */
object TupleTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("TupleTest").setMaster("spark://10.0.0.151:7077").setJars(List("/home/liuh/workspace/programinginscala/out/artifacts/programinginscala_jar/programinginscala.jar")).set("spark.executor.memory", "12g").set("spark.executor.cores","8")
    val sc = new SparkContext(conf)

    val testArray = new Array[Tuple2[Array[Int],Double]](5)
    testArray(0)= (Array(1,2,3),1.0)
    testArray(1)=(Array(1,2,3),1.0)
    testArray(2)=(Array(3,4,5),8.0)
    testArray(3)=(Array(2,3,4),5.0)
    testArray(4)=(Array(0),0.0)

    val step1 = sc.parallelize(testArray)
    val Array2String = (arrayValue:Array[Int])=>{
      val sb = new StringBuilder
      for(intValue<-arrayValue)
        sb.append(intValue).append(",")
      sb.toString()

    }
    //val test = step1.sortByKey(false,0)
    val result = step1.map(x=>(Array2String(x._1),x._2)).map(x=>(x,1)).reduceByKey(_+_).map(x=>(x._1._2,x._1._1+x._2)).sortByKey(false)
    result.collect.foreach(println)

  }
}
