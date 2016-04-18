package impli

/**
  * Created by liuh on 2016/4/18.
  */
class PreferredPrompt(val preference:String)
class PreferredDrink(val preferred:String)

object Greeter{
  def greet(name:String)(implicit prompt:PreferredPrompt,drink:PreferredDrink): Unit ={
    println("Welcom, "+name+".The system is ready.")
    println("But while you work, ")
    println("why not enjoy a cup of "+drink.preferred+"?")
    println(prompt.preference)
  }
}
object JoesPrefs{
  implicit val prompt =new PreferredPrompt("Yes, master> ")
  implicit val drink = new PreferredDrink("tea")
}
