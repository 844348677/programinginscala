package loop

/**
  * Created by liuh on 2016/4/18.
  */
object TestFor {
  def main(args: Array[String]) {
    case class Person(name:String,isMale:Boolean,children:Person*)
    val lara = Person("Lara",false)
    val bob = Person("Bob",true)
    val julie = Person("Julie",false,lara,bob)
    val persons = List(lara,bob,julie)
    persons filter (p => !p.isMale) flatMap(p => (p.children map (c=> (p.name,c.name))))
    for(p <- persons; if !p.isMale;c <- p.children) yield (p.name,c.name)
    //所有能够yield产生结果的for表达式都会被编译器转译为高阶方法map、flatMap及filter的组合调用
    //所有不得不带yield的for循环都会被转译为仅对高阶函数filter和foreach的调用

    for(x <- List(1,2);y <- List("one","two")) yield (x,y)
  }
}
