package dss.vector.solutions.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import dss.vector.solutions.global.CredentialsSingleton;


/**
 * Object to use in conjunction with a WebClientSession when a session id
 * is being made global. This object should be set and unset at the same time
 * as the WebClientSession in the user's session.
 */
public class GlobalSessionListener implements HttpSessionBindingListener
{
  public static final String GLOBAL_SESSION_LISTENER = "globalSessionListener";
  
  /**
   * The session id to be shared globally.
   */
  private String sessionId;
  
  /**
   * Creates a new GlobalSessionListener with the given session id.
   * 
   * @param sessionId
   */
  public GlobalSessionListener(String sessionId)
  {
    this.sessionId = sessionId;
  }
  
  /**
   * Notifies the {@link CredentialsSingleton} that a new session id
   * is ready to be shared globally.
   */
  public void valueBound(HttpSessionBindingEvent arg0)
  {
    CredentialsSingleton.getInstance().setSessionId(sessionId);
  }

  /**
   * Notifies the {@link CredentialsSingleton} that the session is is
   * no longer shared globally.
   */
  public void valueUnbound(HttpSessionBindingEvent arg0)
  {
    CredentialsSingleton.getInstance().removeSessionId(sessionId);    
  }
  
  /**
   * Sets a cookie for the global session on the given HttpServletReponse
   * 
   * @param res
   */
  public void setCookie(HttpServletResponse res)
  {
    Cookie sessionCookie = new Cookie(CredentialsSingleton.GLOBAL_SESSION_ID, sessionId);
    sessionCookie.setPath(CredentialsSingleton.GLOBAL_PATH);
    res.addCookie(sessionCookie);
  }

}
