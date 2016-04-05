package bobsrockets.navigation

/**
  * Created by liuh on 2016/4/5.
  */
class Navigator {

}
package bobsrockets{ //scala中package是可以嵌套语句的，但是java中必须从根开始
  package navigation{
    class Navigator2
    package tests{
      class NavigatorSuite
    }
  }
}
