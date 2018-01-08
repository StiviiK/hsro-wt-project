
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/d/Project/hsro-wt-project/Backend/conf/routes
// @DATE:Mon Jan 08 11:35:32 GMT 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_1: controllers.HomeController,
  // @LINE:9
  Assets_4: controllers.Assets,
  // @LINE:13
  UserController_3: controllers.UserController,
  // @LINE:22
  SocketController_2: controllers.SocketController,
  // @LINE:25
  ForumController_0: controllers.ForumController,
  // @LINE:33
  ForumPostController_5: controllers.ForumPostController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_1: controllers.HomeController,
    // @LINE:9
    Assets_4: controllers.Assets,
    // @LINE:13
    UserController_3: controllers.UserController,
    // @LINE:22
    SocketController_2: controllers.SocketController,
    // @LINE:25
    ForumController_0: controllers.ForumController,
    // @LINE:33
    ForumPostController_5: controllers.ForumPostController
  ) = this(errorHandler, HomeController_1, Assets_4, UserController_3, SocketController_2, ForumController_0, ForumPostController_5, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, Assets_4, UserController_3, SocketController_2, ForumController_0, ForumPostController_5, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users""", """controllers.UserController.create()"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users/""" + "$" + """id<[^/]+>""", """controllers.UserController.update(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users/""" + "$" + """id<[^/]+>""", """controllers.UserController.delete(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users""", """controllers.UserController.list()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users/""" + "$" + """id<[^/]+>""", """controllers.UserController.get(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users/""" + "$" + """id<[^/]+>/Login""", """controllers.UserController.login(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users/""" + "$" + """id<[^/]+>/Logout""", """controllers.UserController.logout(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users/""" + "$" + """id<[^/]+>/Check""", """controllers.UserController.checkLogin(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Users/""" + "$" + """id<[^/]+>/Chat""", """controllers.SocketController.connectWith(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum""", """controllers.ForumController.create()"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum/""" + "$" + """id<[^/]+>""", """controllers.ForumController.update(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum/""" + "$" + """id<[^/]+>""", """controllers.ForumController.delete(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum""", """controllers.ForumController.list()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum/""" + "$" + """id<[^/]+>""", """controllers.ForumController.get(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum/""" + "$" + """id<[^/]+>/Post""", """controllers.ForumPostController.create(id:Long)"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum/""" + "$" + """id<[^/]+>/Post/""" + "$" + """pid<[^/]+>""", """controllers.ForumPostController.update(id:Long, pid:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum/""" + "$" + """id<[^/]+>/Post/""" + "$" + """pid<[^/]+>""", """controllers.ForumPostController.delete(id:Long, pid:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum/""" + "$" + """id<[^/]+>/Post""", """controllers.ForumPostController.list(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Forum/""" + "$" + """id<[^/]+>/Post/""" + "$" + """pid<[^/]+>""", """controllers.ForumPostController.get(id:Long, pid:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Chat/Host""", """controllers.SocketController.hostSocket()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_4.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_UserController_create2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users")))
  )
  private[this] lazy val controllers_UserController_create2_invoker = createInvoker(
    UserController_3.create(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "create",
      Nil,
      "POST",
      this.prefix + """Users""",
      """UserRoutes""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:14
  private[this] lazy val controllers_UserController_update3_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserController_update3_invoker = createInvoker(
    UserController_3.update(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "update",
      Seq(classOf[Long]),
      "PATCH",
      this.prefix + """Users/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_UserController_delete4_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserController_delete4_invoker = createInvoker(
    UserController_3.delete(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "delete",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """Users/""" + "$" + """id<[^/]+>""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:17
  private[this] lazy val controllers_UserController_list5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users")))
  )
  private[this] lazy val controllers_UserController_list5_invoker = createInvoker(
    UserController_3.list(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "list",
      Nil,
      "GET",
      this.prefix + """Users""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_UserController_get6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserController_get6_invoker = createInvoker(
    UserController_3.get(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "get",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """Users/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_UserController_login7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Login")))
  )
  private[this] lazy val controllers_UserController_login7_invoker = createInvoker(
    UserController_3.login(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "login",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """Users/""" + "$" + """id<[^/]+>/Login""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_UserController_logout8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Logout")))
  )
  private[this] lazy val controllers_UserController_logout8_invoker = createInvoker(
    UserController_3.logout(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "logout",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """Users/""" + "$" + """id<[^/]+>/Logout""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_UserController_checkLogin9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Check")))
  )
  private[this] lazy val controllers_UserController_checkLogin9_invoker = createInvoker(
    UserController_3.checkLogin(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "checkLogin",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """Users/""" + "$" + """id<[^/]+>/Check""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_SocketController_connectWith10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Users/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Chat")))
  )
  private[this] lazy val controllers_SocketController_connectWith10_invoker = createInvoker(
    SocketController_2.connectWith(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SocketController",
      "connectWith",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """Users/""" + "$" + """id<[^/]+>/Chat""",
      """""",
      Seq()
    )
  )

  // @LINE:25
  private[this] lazy val controllers_ForumController_create11_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum")))
  )
  private[this] lazy val controllers_ForumController_create11_invoker = createInvoker(
    ForumController_0.create(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "create",
      Nil,
      "POST",
      this.prefix + """Forum""",
      """ForumRoutes""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:26
  private[this] lazy val controllers_ForumController_update12_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ForumController_update12_invoker = createInvoker(
    ForumController_0.update(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "update",
      Seq(classOf[Long]),
      "PATCH",
      this.prefix + """Forum/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:27
  private[this] lazy val controllers_ForumController_delete13_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ForumController_delete13_invoker = createInvoker(
    ForumController_0.delete(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "delete",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """Forum/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:28
  private[this] lazy val controllers_ForumController_list14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum")))
  )
  private[this] lazy val controllers_ForumController_list14_invoker = createInvoker(
    ForumController_0.list(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "list",
      Nil,
      "GET",
      this.prefix + """Forum""",
      """""",
      Seq()
    )
  )

  // @LINE:29
  private[this] lazy val controllers_ForumController_get15_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ForumController_get15_invoker = createInvoker(
    ForumController_0.get(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumController",
      "get",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """Forum/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:33
  private[this] lazy val controllers_ForumPostController_create16_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Post")))
  )
  private[this] lazy val controllers_ForumPostController_create16_invoker = createInvoker(
    ForumPostController_5.create(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumPostController",
      "create",
      Seq(classOf[Long]),
      "POST",
      this.prefix + """Forum/""" + "$" + """id<[^/]+>/Post""",
      """PostRoutes""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:34
  private[this] lazy val controllers_ForumPostController_update17_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Post/"), DynamicPart("pid", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ForumPostController_update17_invoker = createInvoker(
    ForumPostController_5.update(fakeValue[Long], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumPostController",
      "update",
      Seq(classOf[Long], classOf[Long]),
      "PATCH",
      this.prefix + """Forum/""" + "$" + """id<[^/]+>/Post/""" + "$" + """pid<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:35
  private[this] lazy val controllers_ForumPostController_delete18_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Post/"), DynamicPart("pid", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ForumPostController_delete18_invoker = createInvoker(
    ForumPostController_5.delete(fakeValue[Long], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumPostController",
      "delete",
      Seq(classOf[Long], classOf[Long]),
      "DELETE",
      this.prefix + """Forum/""" + "$" + """id<[^/]+>/Post/""" + "$" + """pid<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:36
  private[this] lazy val controllers_ForumPostController_list19_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Post")))
  )
  private[this] lazy val controllers_ForumPostController_list19_invoker = createInvoker(
    ForumPostController_5.list(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumPostController",
      "list",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """Forum/""" + "$" + """id<[^/]+>/Post""",
      """""",
      Seq()
    )
  )

  // @LINE:37
  private[this] lazy val controllers_ForumPostController_get20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Forum/"), DynamicPart("id", """[^/]+""",true), StaticPart("/Post/"), DynamicPart("pid", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ForumPostController_get20_invoker = createInvoker(
    ForumPostController_5.get(fakeValue[Long], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ForumPostController",
      "get",
      Seq(classOf[Long], classOf[Long]),
      "GET",
      this.prefix + """Forum/""" + "$" + """id<[^/]+>/Post/""" + "$" + """pid<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:40
  private[this] lazy val controllers_SocketController_hostSocket21_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Chat/Host")))
  )
  private[this] lazy val controllers_SocketController_hostSocket21_invoker = createInvoker(
    SocketController_2.hostSocket(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SocketController",
      "hostSocket",
      Nil,
      "GET",
      this.prefix + """Chat/Host""",
      """ChatRoutes""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_1.index)
      }
  
    // @LINE:9
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_4.versioned(path, file))
      }
  
    // @LINE:13
    case controllers_UserController_create2_route(params@_) =>
      call { 
        controllers_UserController_create2_invoker.call(UserController_3.create())
      }
  
    // @LINE:14
    case controllers_UserController_update3_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_update3_invoker.call(UserController_3.update(id))
      }
  
    // @LINE:16
    case controllers_UserController_delete4_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_delete4_invoker.call(UserController_3.delete(id))
      }
  
    // @LINE:17
    case controllers_UserController_list5_route(params@_) =>
      call { 
        controllers_UserController_list5_invoker.call(UserController_3.list())
      }
  
    // @LINE:18
    case controllers_UserController_get6_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_get6_invoker.call(UserController_3.get(id))
      }
  
    // @LINE:19
    case controllers_UserController_login7_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_login7_invoker.call(UserController_3.login(id))
      }
  
    // @LINE:20
    case controllers_UserController_logout8_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_logout8_invoker.call(UserController_3.logout(id))
      }
  
    // @LINE:21
    case controllers_UserController_checkLogin9_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_checkLogin9_invoker.call(UserController_3.checkLogin(id))
      }
  
    // @LINE:22
    case controllers_SocketController_connectWith10_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_SocketController_connectWith10_invoker.call(SocketController_2.connectWith(id))
      }
  
    // @LINE:25
    case controllers_ForumController_create11_route(params@_) =>
      call { 
        controllers_ForumController_create11_invoker.call(ForumController_0.create())
      }
  
    // @LINE:26
    case controllers_ForumController_update12_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ForumController_update12_invoker.call(ForumController_0.update(id))
      }
  
    // @LINE:27
    case controllers_ForumController_delete13_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ForumController_delete13_invoker.call(ForumController_0.delete(id))
      }
  
    // @LINE:28
    case controllers_ForumController_list14_route(params@_) =>
      call { 
        controllers_ForumController_list14_invoker.call(ForumController_0.list())
      }
  
    // @LINE:29
    case controllers_ForumController_get15_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ForumController_get15_invoker.call(ForumController_0.get(id))
      }
  
    // @LINE:33
    case controllers_ForumPostController_create16_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ForumPostController_create16_invoker.call(ForumPostController_5.create(id))
      }
  
    // @LINE:34
    case controllers_ForumPostController_update17_route(params@_) =>
      call(params.fromPath[Long]("id", None), params.fromPath[Long]("pid", None)) { (id, pid) =>
        controllers_ForumPostController_update17_invoker.call(ForumPostController_5.update(id, pid))
      }
  
    // @LINE:35
    case controllers_ForumPostController_delete18_route(params@_) =>
      call(params.fromPath[Long]("id", None), params.fromPath[Long]("pid", None)) { (id, pid) =>
        controllers_ForumPostController_delete18_invoker.call(ForumPostController_5.delete(id, pid))
      }
  
    // @LINE:36
    case controllers_ForumPostController_list19_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ForumPostController_list19_invoker.call(ForumPostController_5.list(id))
      }
  
    // @LINE:37
    case controllers_ForumPostController_get20_route(params@_) =>
      call(params.fromPath[Long]("id", None), params.fromPath[Long]("pid", None)) { (id, pid) =>
        controllers_ForumPostController_get20_invoker.call(ForumPostController_5.get(id, pid))
      }
  
    // @LINE:40
    case controllers_SocketController_hostSocket21_route(params@_) =>
      call { 
        controllers_SocketController_hostSocket21_invoker.call(SocketController_2.hostSocket())
      }
  }
}
