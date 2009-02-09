package com.terraframe.mojo.defaults;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.web.WebClientSession;

public class LoginController extends LoginControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1233704136713L;
  
  public LoginController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
  {
    super(req, resp);
  }
  
  @Override
  public void login(String username, String password) throws java.io.IOException, javax.servlet.ServletException
  {
    WebClientSession clientSession = WebClientSession.createUserSession(username, password, req.getLocale());
    ClientRequestIF clientRequest = clientSession.getRequest();

    req.getSession().setAttribute(ClientConstants.CLIENTSESSION, clientSession);
    req.setAttribute(ClientConstants.CLIENTREQUEST, clientRequest);
    
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }
  
  @Override
  public void logout() throws java.io.IOException, javax.servlet.ServletException
  {
    // process which logs the user out.
    ClientSession clientSession = super.getClientSession();

    if(clientSession != null)
    {
      clientSession.logout();
    }
    
    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }
}