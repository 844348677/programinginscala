/**
  * Created by liuh on 2016/4/7.
  */
object HelloList {
  def main(args: Array[String]) {
    val fruit = List("apples","orange","pears")
    val nums = List(1,2,3,4)
    val diag3 = List(List(1,0,0),List(0,1,0),List(0,0,1))
    val empty = List()
    //列表是不可变的，不能通过赋值改变列表的元素，列表具有递归结构，数组是连续的
    //列表同质的，所有元素具有相同的类型
    //列表是协变的，如果S是T的自类型，则List[S]是List[T]的子类型
    //空列表的类型为List[Nothing]任何类的子类

    val fruit2 = "apples" :: ("oranges" :: ("pears" :: Nil))
    val nums2 = 1 :: (2 :: (3 :: (4 :: Nil)))
    val diag32 = (1::(0::(0::Nil)))::
      (0::(1::(0::Nil)))::
      (0::(0::(1::Nil)))::Nil
    val empty2 = Nil
    val nums3 = 1::2::3::4::Nil
    //List常用操作  head 返回列表的第一个元素，tail返回除第一个之外的所有元素组成的列表，isEmpty如果为空，返回真
    //head和tail仅能用在非空列表中
    def isort(xs:List[Int]):List[Int] =
      if(xs.isEmpty) Nil
      else insert(xs.head,isort(xs.tail))
    def insert(x:Int,xs:List[Int]):List[Int] =
      if(xs.isEmpty || x <= xs.head) x:: xs
      else xs.head :: insert(x,xs.tail)

    def isort2(xs:List[Int]):List[Int] = xs match{
      case List() => List()
      case x :: xsl => insert2(x,isort(xsl))
    }
    def insert2(x:Int,xs:List[Int]):List[Int] = xs match {
      case List() => List(x)
      case y::ys => if(x<=y) x::xs else y ::insert2(x,ys)
    }
    List(1,2) ::: List(3,4,5) //不想 :: :::的两个操作元都是列表 右结合
    //def append[T](xs:List[T],ys:List[T]):List[T]
    def append[T](xs:List[T],ys:List[T]):List[T]=
      xs match{
        case List() => ys
        case x :: xsl => x :: append(xsl,ys)
      }
    //head tail  成对出现的 last init   前面两个时间是固定的，后面两个时间是按长度变的
    //reverse是List反转的意思，生成新的List，不改变原有List
    def rev[T](xs:List[T]):List[T] = xs match{
      case List() => xs
      case x::xs1 => rev(xs1) ::: List(x)
    }
    // take drop splitAt
    val abcde = List('a','b','c','d','e')
    println(abcde take 2)
    println(abcde drop 2)
    println(abcde splitAt 2)
    println(abcde apply (2)) //时间和n成正比
    abcde.indices
    println(abcde.indices zip abcde) //如果两个长度不一样，不能匹配的元素将被舍弃
    println(abcde.zipWithIndex)
    //List 的mkString
    println(abcde mkString ("[",",","]"))
    println(abcde mkString (","))

    val arr = abcde.toArray
    println(arr.toString)
    println(arr.toList)

    val arr2 = new Array[Int](10)
    List(1,2,3) copyToArray(arr2,3)
    println(arr2.toString)
    val it = abcde.iterator
    println(it.next)
    println(it.next)

    def msort[T](less:(T,T) => Boolean)(xs:List[T]):List[T]={
      def merge(xs:List[T],ys:List[T]):List[T] = (xs,ys) match {
        case (Nil,_) => ys
        case (_,Nil) => xs
        case (x :: xsl,y::ysl) =>
          if(less(x,y)) x :: merge(xsl,ys)
          else y :: merge(xs,ysl)
      }
      val n = xs.length/2
      if(n==0) xs
      else{
        val(ys,zs) = xs splitAt n
        merge(msort(less)(ys),msort(less)(zs))
      }
    }
    val intSort = msort((x:Int,y:Int) => x > y) _
    val reverseIntSort = msort((x:Int,y:Int) => x < y) _

    List(1,2,3) map (_+1)
    val words = List("the","quick","brown","fox")
    words map (_.length)
    words map (_.toList.reverse.mkString)
    words flatMap (_.toList)  //flatMap分得更细了

    List.range(1,5) flatMap (i=>List.range(1,i) map (j=>(i,j)))

    for(i<-List.range(1,5);j<-List.range(1,i)) yield (i,j)



  }
}
