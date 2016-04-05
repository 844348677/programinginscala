/**
  * Created by liuh on 2016/4/5.
  */
class Animal
trait Furry extends Animal
trait HasLegs extends Animal
trait FourLegged extends HasLegs
class Cat extends Animal with Furry with FourLegged
//Animal 线性化：Animal，AnyRef，Any
//Furry  线性化：Furry，Animal，AnyRef，Any
//FourLegged  线性化：FourLegged，HasLegs，Animal，AnyRed，Any
//HasLegs 线性化：HasLegs，Animal，AnyRef，Any
//Cat 线性化：Cat，FourLegged，HasLegs，Furry，Animal，AnyRef，Any
