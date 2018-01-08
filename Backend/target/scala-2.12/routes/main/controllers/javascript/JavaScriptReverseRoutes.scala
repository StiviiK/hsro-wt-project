
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/d/Project/hsro-wt-project/Backend/conf/routes
// @DATE:Mon Jan 08 11:35:32 GMT 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:9
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:22
  class ReverseSocketController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def hostSocket: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SocketController.hostSocket",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Chat/Host"})
        }
      """
    )
  
    // @LINE:22
    def connectWith: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SocketController.connectWith",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Chat"})
        }
      """
    )
  
  }

  // @LINE:13
  class ReverseUserController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.delete",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "Users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:19
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.login",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Login"})
        }
      """
    )
  
    // @LINE:13
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "Users"})
        }
      """
    )
  
    // @LINE:14
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.update",
      """
        function(id0) {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "Users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:21
    def checkLogin: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.checkLogin",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Check"})
        }
      """
    )
  
    // @LINE:18
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.get",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:17
    def list: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.list",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Users"})
        }
      """
    )
  
    // @LINE:20
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.logout",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Logout"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:33
  class ReverseForumPostController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:34
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumPostController.update",
      """
        function(id0,pid1) {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Post/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("pid", pid1))})
        }
      """
    )
  
    // @LINE:36
    def list: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumPostController.list",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Post"})
        }
      """
    )
  
    // @LINE:35
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumPostController.delete",
      """
        function(id0,pid1) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Post/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("pid", pid1))})
        }
      """
    )
  
    // @LINE:37
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumPostController.get",
      """
        function(id0,pid1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Post/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("pid", pid1))})
        }
      """
    )
  
    // @LINE:33
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumPostController.create",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/Post"})
        }
      """
    )
  
  }

  // @LINE:25
  class ReverseForumController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:27
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.delete",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:25
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum"})
        }
      """
    )
  
    // @LINE:26
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.update",
      """
        function(id0) {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:29
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.get",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:28
    def list: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.list",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Forum"})
        }
      """
    )
  
  }


}
