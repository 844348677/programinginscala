/**
  * Created by liuhang on 2016/4/4.
  */
class Cat {
  val dangerous = false
}

class Tiger1(
            override val dangerous :Boolean,
            private var age : Int
            ) extends Cat

class Tiger(param1:Boolean,param2:Int) extends Cat{
  override val dangerous = param1
  private var age = param2
}