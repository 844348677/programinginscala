package loop

/**
  * Created by liuh on 2016/4/18.
  */
case class Book(title:String,authors:String*)
object Book{
  def main(args: Array[String]) {
    val books:List[Book] =
      List(
        Book(
          "Structure and Interpretation of Computer Programs",
          "Abelson,Harold","Sussman,Gerald J."
        ),
        Book(
          "Principles of Compiler Desion",
          "Aho,Alfred","Ullman,Jeffrey"
        ),
        Book(
          "Programing in Module-2",
          "Wirth,Niklaus"
        ),
        Book(
          "Elements of ML Programming",
          "Ullman,Jeffrey"
        ),
        Book(
          "The Java Language Specification","Goslling,James",
          "Joy,Bill","Steel,Guy","Bracha,Gilad"
        )
      )
    for(b <- books;a <- b.authors if a startsWith "Gosling") yield b.title
    for(b <- books if (b.title indexOf "Program") >= 0) yield b.title
    for(b1 <- books;b2 <- books if b1 != b2; a1 <- b1.authors;a2 <- b2.authors if a1 == a2) yield a1
    def removeDuplicates[A](xs:List[A]):List[A] ={
      if(xs.isEmpty) xs
      else
        xs.head :: removeDuplicates(
          xs.tail filter (x => x != xs.head)
          //for(x <- xs.tail if x != xs.head) yield x
        )
    }
    books flatMap (b1 =>
      books filter (b2 => b1 != b2 ) flatMap( b2 =>
        b1.authors flatMap(a1 =>
          b2.authors filter (a2 => a1 == a2) map (a2 =>
            a1)
          )
        )
      )


  }
}
