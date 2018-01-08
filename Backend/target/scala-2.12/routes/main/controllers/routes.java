
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/d/Project/hsro-wt-project/Backend/conf/routes
// @DATE:Mon Jan 08 11:35:32 GMT 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseSocketController SocketController = new controllers.ReverseSocketController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUserController UserController = new controllers.ReverseUserController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseForumPostController ForumPostController = new controllers.ReverseForumPostController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseForumController ForumController = new controllers.ReverseForumController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseSocketController SocketController = new controllers.javascript.ReverseSocketController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUserController UserController = new controllers.javascript.ReverseUserController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseForumPostController ForumPostController = new controllers.javascript.ReverseForumPostController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseForumController ForumController = new controllers.javascript.ReverseForumController(RoutesPrefix.byNamePrefix());
  }

}
