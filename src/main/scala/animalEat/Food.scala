package animalEat

/**
  * Created by liuhang on 2016/4/10.
  */
class Food
abstract class Animal{
  type SuitableFoot <: Food
  def eat(food:SuitableFoot)
}
class Grass extends Food
class Cow extends Animal {
   type SuitableFoot = Grass

  override def eat(food: Grass){}
}
