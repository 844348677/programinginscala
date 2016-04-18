package impli

/**
  * Created by liuh on 2016/4/18.
  */
object MaxListUpBound {
  def maxListUpBound[T <: Ordered[T]](element:List[T]):T=
    element match{
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListUpBound(rest)
        if(x > maxRest) x
        else maxRest
    }
  def maxListImpParm[T](elements:List[T])(implicit ordered:T => Ordered[T]):T=
  elements match{
    case List() =>
      throw new IllegalArgumentException("empty list!")
    case List(x) => x
    case x :: rest =>
      val maxRest = maxListImpParm(rest)(ordered)
      if(ordered(x) > maxRest) x
      else maxRest
  }
  //def maxListPoorStyle[T](elements:List[T])(implicit ordered:(T,T) => Boolean):T

  def maxList[T](elements: List[T])(implicit ordered:T => Ordered[T]):T=
    elements match{
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest)
        if(x > maxRest) x
        else maxRest
    }
  def maxList[T <% Ordered[T]](elements:List[T]):T =
    elements match{
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest)
        if(x > maxRest) x
        else maxRest
    }
}
