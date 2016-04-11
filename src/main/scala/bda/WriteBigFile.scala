package bda

import java.io.{File, PrintWriter}

/**
  * Created by liuh on 2016/4/11.
  */
object WriteBigFile {
  def main(args: Array[String]) {
    val pw = new PrintWriter(new File("bda6.txt"))
    val bda4 = for(i <- List.range(2,139+1); j <- List.range(2,i); k <- List.range(2,j);l <- List.range(2,k);m <- List.range(2,l);n <- List.range(2,m) ){
      pw.write(i+" "+j+" "+k+" "+l+" "+m+" "+n)
      pw.write("\r\n")
    }

    pw.close()

  }
}
