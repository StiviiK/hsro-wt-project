
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/d/Project/hsro-wt-project/Backend/conf/routes
// @DATE:Mon Jan 08 11:35:32 GMT 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
