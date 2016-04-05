package bobsdelights

/**
  * Created by liuh on 2016/4/5.
  */
abstract class Fruit (
  val name:String,
  val color:String
)
object Fruits{
  object  Apple extends Fruit("apple","red")
  object Orange extends Fruit("orange","orange")
  object Pear extends Fruit("pear","yellowish")
  val menu = List(Apple,Orange,Pear)
  import java.util.regex
  val pat = regex.Pattern.compile("a*b")
  //scala 的import更为灵活
  //可以出现在任何地方
  //可以指的是 对象及包
  //可以重命名或隐藏一些被引用的成员
  import Fruits.{Apple,Orange}
  import Fruits.{Apple => McIntosh,Orange}
  import Fruits.{Pear=>_,_} //引入除Pear意外的所有，意思是，把Pear命名为 _ 就是隐藏他是

  import java.lang._ //java.lang 包的所有东西
  import scala._ //scala包的所有东西
  import Predef._ //Predef对象的所有东西

  //出现在靠后位置的引用将覆盖靠前的引用
}
class Outer{
  class Inner{
    private def f(){println("f")}
    class InnerMost{
      f()
    }
  }
  //(new Inner).f()  与java不同，调用不到f() 外部类也访问不到内部类的私有成员
}

package p{
  class Super{
    protected def f(){ println("f") }
  }
  class Sub extends Super{
    f()
  }
  class Other{
    //(new Super).f()
  }
  //scala中，保护成员只在定义了成员的类的子类中可以被访问到。而java 还允许同一个包的其他类中进行访问
}

/*package bobsrockets2{
  package navigation{
    private[bobstrockets] class Navigator{
      protected[navigation] def useStarChart(){}
      class LegOfJourney{
        private[Navigator] val distence = 100
      }
      private[this] var speed = 200
    }
  }
  package launch{
    import navigation._
    object Vechicle{
      private[launch] val guide = new Navigator
    }
  }
}*/
//private[X] 这个类对包含在X包里的所有的类和对象可见
//protected[X]修饰符允许C的所有子类及修饰符所属的包、类和对象X访问带有次标记的定义
//更强的约束private[this]仅能在包含了定义的同一个对象中被访问，对象私有，仅在同一个对象中可以访问
// val other = new Navigator ; other.speed 也是不允许的
//在scala伴生对象中，修饰符同样适用
//类的所有访问全校都对半生对象开放，反过来也如此
//伴生对象的protected完全没有意义，因为单例对象没有任何子类