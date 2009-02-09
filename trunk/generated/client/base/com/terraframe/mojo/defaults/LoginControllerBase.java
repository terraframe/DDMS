package com.terraframe.mojo.defaults;

public class LoginControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String CLASS = "com.terraframe.mojo.defaults.LoginController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  
  private static final long serialVersionUID = 1234203359578L;
  
  public LoginControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
  {
    this.req = req;
    this.resp = resp;
  }
  
  public javax.servlet.http.HttpServletRequest getRequest()
  {
    return this.req;
  }
  
  public javax.servlet.http.HttpServletResponse getResponse()
  {
    return this.resp;
  }
  
  public com.terraframe.mojo.constants.ClientRequestIF getClientRequest()
  {
    return (com.terraframe.mojo.constants.ClientRequestIF) req.getAttribute(com.terraframe.mojo.constants.ClientConstants.CLIENTREQUEST);
  }
  
  public com.terraframe.mojo.ClientSession getClientSession()
  {
    return (com.terraframe.mojo.ClientSession) req.getSession().getAttribute(com.terraframe.mojo.constants.ClientConstants.CLIENTSESSION);
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void logout() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.terraframe.mojo.defaults.LoginController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "com.terraframe.mojo.defaults.LoginController.logout");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="", post=false)
  public void failLogout() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.terraframe.mojo.defaults.LoginController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "com.terraframe.mojo.defaults.LoginController.failLogout");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:username, java.lang.String:password", post=true)
  public void login(java.lang.String username, java.lang.String password) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.terraframe.mojo.defaults.LoginController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "com.terraframe.mojo.defaults.LoginController.login");
  }
  
  @com.terraframe.mojo.controller.ActionParameters(parameters="java.lang.String:username, java.lang.String:password", post=true)
  public void failLogin(java.lang.String username, java.lang.String password) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.terraframe.mojo.defaults.LoginController.java";
    throw new com.terraframe.mojo.controller.UndefinedControllerActionException(msg, req.getLocale(), "com.terraframe.mojo.defaults.LoginController.failLogin");
  }
  
}
