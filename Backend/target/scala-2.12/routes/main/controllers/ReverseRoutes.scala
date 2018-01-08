
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/d/Project/hsro-wt-project/Backend/conf/routes
// @DATE:Mon Jan 08 11:35:32 GMT 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:9
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:22
  class ReverseSocketController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def hostSocket(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Chat/Host")
    }
  
    // @LINE:22
    def connectWith(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Chat")
    }
  
  }

  // @LINE:13
  class ReverseUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def delete(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "Users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:19
    def login(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Login")
    }
  
    // @LINE:13
    def create(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "Users")
    }
  
    // @LINE:14
    def update(id:Long): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "Users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:21
    def checkLogin(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Check")
    }
  
    // @LINE:18
    def get(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:17
    def list(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Users")
    }
  
    // @LINE:20
    def logout(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Logout")
    }
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:33
  class ReverseForumPostController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:34
    def update(id:Long, pid:Long): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "Forum/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Post/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("pid", pid)))
    }
  
    // @LINE:36
    def list(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Forum/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Post")
    }
  
    // @LINE:35
    def delete(id:Long, pid:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "Forum/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Post/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("pid", pid)))
    }
  
    // @LINE:37
    def get(id:Long, pid:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Forum/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Post/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("pid", pid)))
    }
  
    // @LINE:33
    def create(id:Long): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "Forum/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/Post")
    }
  
  }

  // @LINE:25
  class ReverseForumController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:27
    def delete(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "Forum/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:25
    def create(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "Forum")
    }
  
    // @LINE:26
    def update(id:Long): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "Forum/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:29
    def get(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Forum/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:28
    def list(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "Forum")
    }
  
  }


}
