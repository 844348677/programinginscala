/**
  * Created by liuh on 2016/4/5.
  */
package launch{
  class Booster3
}
package bobsrockets{
  package launch{
    class Booster2
  }
  package navigation{
    package launch{
      class Booster1
    }
    class MissionControl{
      val booster1 = new launch.Booster1
      //val booster2 = new bobsrockets.
      val bosster3 = new _root_.launch.Booster3
      //scala在所有用户可创建的包之外提供了名为_root_的包，任何你能写出来的顶层爆，都被当做是_root_包的成员
    }
  }

}
