import java.io.{File, PrintWriter}

import breeze.numerics.pow
import org.apache.spark.{SparkContext, SparkConf}

import scala.collection.mutable.ListBuffer


object NewMethod {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("NewMethod").setMaster("spark://10.0.0.151:7077").setJars(List("/home/liuh/workspace/programinginscala/out/artifacts/programinginscala_jar/programinginscala.jar")).set("spark.executor.memory", "12g").set("spark.executor.cores","8")
    val sc = new SparkContext(conf)

    val colmSize :Int =  1566
    val rowSize :Int = 139

    //定义 lb 手机random列
    val lb = new ListBuffer[String]

    for(i <- 1.to(5000)){ //循环多少次收集多少次
      val randomArray = new Array[Int](16) // 收集所有139列中的16列
      for(i<-1.to(16)){
        val ii = Math.random().*(rowSize-2).toInt+2 //列号2-139
        //testSet.add(ii)
        randomArray(i-1) = ii
      }
      val distArray = randomArray.sorted.distinct //排序，去重复
      val sb = new StringBuilder
      for(value <- distArray)
        sb.append(value+" ")
      lb.append(sb.toString)
    }
    val result = lb.toList
    result.foreach(println(_))
    //println(result.toString)

    val lines = sc.parallelize(result)  // 这就是 random initial index
    //lines.repartition(1).saveAsTextFile("hdfs://liuh-pc:9000/output/random1") 得到随机行组合
    val matrix = sc.textFile("hdfs://10.0.0.151:9000/input/train01rmstruct.txt").flatMap(_.split("\t")).map(_.toInt)

    //val result1 = matrix.map(_.split(" ")).collect

    val localArray = matrix.collect //所有数据组成一个大Array OopsOOM！！！！
    System.out.println("size: "+localArray.length)

    for(i <- 1 to 100)
      println(i+"  "+localArray(i))

    //val arrayMatrix = new Array[Array[Int]](139)
    val arrayMatrix = Array.ofDim[Int](rowSize,colmSize) //构造二维数组
    for(i <- 0 to rowSize-1){
      for(j <- 0 to colmSize-1){
        arrayMatrix(i)(j) = localArray(i*(colmSize)+j)
      }
    }
    val arrayY = new Array[Int](colmSize) //构造Y值数组
    for(i <- 0 to colmSize-1){
      arrayY(i) = localArray(i)
    }

    val barY = arrayY.sum.toDouble./(arrayY.length)///arrayY.length ///arrayY.length
    println("平均值："+barY)
    //测试文件
/*    val printWriter = new PrintWriter(new File("text.txt"))
    for(i <- arrayMatrix){
      for(j <- i){
        printWriter.print(j+" ")
      }
      printWriter.println()
    }
    printWriter.close()*/
    //println("result: "+arrayMatrix) 测试测试
/*    var index = 0;
    for(i <- arrayMatrix){
      for(j <- i){
        println(index+"  "+j + "  "+localArray(index)) //对比矩阵，相等就对了 表示二维数组构造完成
        index += 1
      }
    }*/
    //测试测试
/*    var index2 = 0
    for(i<-arrayY){
      println(index2 + "  "+ i+ "  "+localArray(index2))
      index2 += 1
    }*/

    //函数，根据随机的index和arrayMatrix构造选中属性的矩阵 index 1-139 所以要见减去1
    val pickRow = (index :Array[Int]) => {
      val returnResult = Array.ofDim[Int](index.length,colmSize)
      for(i <- 0.to(index.length-1))
        returnResult(i) = arrayMatrix(index(i)-1)
      returnResult
    }
    //测试方法
/*    val text1 = pickRow(1.to(139).toArray)

    val printWriter = new PrintWriter(new File("text2.txt"))
    for(i <- text1){
      for(j <- i){
        printWriter.print(j+" ")
      }
      printWriter.println()
    }
    printWriter.close()*/
    //lines RDD[Array]

    val string2int = (stringArray:Array[String]) =>{
      val intArray = new Array[Int](stringArray.length)
      for(i <- 0.to(stringArray.length-1))
        intArray(i) = stringArray(i).toInt
      intArray
    }
    //随机选取列的矩阵
    val matrix2string = (matrixs:Array[Array[Int]]) =>{
      val sb1 = new StringBuilder
      for(i <- matrixs){
        for(j <- i){
          sb1.append(j).append(" ")
        }
      }
      sb1.toString()
    }
    //

    //我真是服了！！！！！！！
    val step1 = lines.map(_.split(" ")).map(string2int(_)).map(x=>(x,pickRow(x)))//.map(matrix2string(_))转成tuple记录random列index和对应矩阵
    val stepNew = lines.map(_.split(" ")).map(string2int(_))

    val result2 = step1.collect()
/*    val printWriter = new PrintWriter(new File("text3.txt"))
    for(i <- result2(99)){
      for(j <- i){
        printWriter.print(j+" ")
      }
      printWriter.println()
    }
    printWriter.close()*/
/*    for(i <- result2){
      for(j <- i)
        print(j+",")
      println()
    }*/
    //step1.repartition(1).saveAsTextFile("hdfs://liuh-pc:9000/output/random4")

    //ok啦，现在已经得到了RDD[Array[Array[Int]]],进行Iscore分数计算
    println("2^4:"+pow(2,4))
    val testInitArray = new Array[Int](20)

    val computeIscore = (matrixs:Array[Array[Int]]) =>{
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
    //testInitArray.foreach(print(_))
    //step1.map(x=>computeIscore(x._1,x._2)).collect().foreach(println(_))
    //val step2 = step1.map(x=>(x._1,computeIscore(x._2))) //x._2的矩阵可以继续优化
    //step2.collect.foreach(x=>println(x._2))
    //result3.collect.foreach(println(_))
    //iscore计算结束

/*    val newMatrix = pickRow(Array(10,24,36,82,106,114))  //经过检验iscore算法正确 接下来需要实现 求出最大的
    println("score:"+computeIscore(newMatrix))*/
    //newMatrix(0).foreach(println(_))

    //求出一个选中属性列的数组，是否可以继续back_ward,删掉一个可以有更好的得分
    val dropOne = (index:Array[Int],matrix:Array[Array[Int]],score:Double) =>{
        var bestScore:Double = score;  //最佳得分
        var bestArray = index //最佳数组
        //println("index.length:"+index.length)
        for(i <- 0.to(index.length-1)){
          val indexBuffer = index.toBuffer
          indexBuffer.remove(i)  //对目前的index数组删除一个
          val newIndex =indexBuffer.toArray
          val newMatrix = pickRow(newIndex)

          
          val newScore = computeIscore(newMatrix) //重新计算分数
          //println(i+" "+newScore)
          if(newScore >= bestScore){
            bestScore = newScore
            bestArray = newIndex
          }
        }
        Tuple2(bestArray,bestScore) //以tuple的类型转换成最佳index数组和bestScore
    }
    //一个属性组，删除一个 的最佳得分  和不删的得分 接下来需要迭代
    val bestInArray = (index:Array[Int],matrix:Array[Array[Int]],score:Double) =>{
      //第一次16个
      //println("index:长度 "+index.length)
      var tuple2 = new Tuple2(index,score) //new Tuple2(Array(0),0)  //第一次运行完的，index数组和分数
      var newTupleArraySize = 0 //标记大小
      var loopId = 0
      while(tuple2._1.length != newTupleArraySize && tuple2._1.length != 1){  //没有index中任何元素可删除 删除元素值变小，或者就剩一个了

        newTupleArraySize = tuple2._1.length  //16个  标记大小等于穿进来的个数
        tuple2 = dropOne(tuple2._1,null,tuple2._2) // 15个 能不能继续删除index数组中的元素
        //println(newTupleArraySize+" "+tuple2._1.length)
        loopId += 1
        //println("loopId:"+loopId+" score: "+tuple2._2)
      }
        tuple2
    }
    val newTextArray = Array(2,42,56,64,67,68,70,82,102,104,107,113,128,131,134)
    //println(computeIscore(pickRow(newTextArray)))
    //println(dropOne(newTextArray,null,1555.0)) //测试基本完毕
    //println(dropOne(newTextArray,null,1555.0)._1.length)
    println("下一步保存")
    println("111111:"+bestInArray(newTextArray,null,23.6047197062744))
    println("1222222:"+bestInArray(newTextArray,null,23.6047197062744)._1.length)

    val step2 = step1.map(x=>(x._1,computeIscore(x._2))) //x._2的矩阵可以继续优化
    //step2.collect.foreach(x=>println(x._1.length+" "+x._2))
    //val test2 = step2.map(x=>dropOne(x._1,null,x._2))
    //test2.collect.foreach(x=>println(x._1.length+" "+x._2))
    //val step2Array = step2.collect().foreach(x=>println(x._1.length))   0.0
    //写一个 Array2String的 方法


    val step3 = step2.map(x=>bestInArray(x._1,null,0.0))
/*    val result4 = step3.collect
    for(i <- 1.to(result4.length-1)){
      print(result4(i)._1.foreach(x=>print(x+",")))
      print("  "+result4(i)._2)
      println
    }*/

    //step3.repartition(1).saveAsTextFile("hdfs://10.0.0.151:9000/output/loop12")

    //val result3 = step2.collect()
    //for(i <- 1.to(result3.length-1)) {  4,24,96,122,138  4,24,96,138
    //  println("!!!!!!"+i)
    //  println(result3(i)._1.foreach(x=>print(x+",")))

     // println(bestInArray(result3(i)._1, null, result3(i)._2))
    //  println(bestInArray(result3(i)._1, null, result3(i)._2)._1.length)
    //}
    val testArray1 = Array(4,24,96,122,138)
    val testArray2 = Array(4,24,96,138)
    //println(computeIscore(pickRow(testArray1)))
    //println(computeIscore(pickRow(testArray2)))

    val *&**&**& = (arrayInt:Array[Int])=>{
      val sb = new StringBuilder
      for(intValue <- arrayInt)
        sb.append(intValue).append(",")
      sb.toString
    }

    val step4 = step3
    //step5.repartition(1).saveAsTextFile("hdfs://10.0.0.151:9000/output/loop15")

  }
}
