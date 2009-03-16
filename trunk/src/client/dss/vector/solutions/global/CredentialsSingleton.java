package dss.vector.solutions.global;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

public class CredentialsSingleton
{
  public static final String GLOBAL_SESSION_ID = "gSessionId";
  
  public static final String GLOBAL_PATH = "/";
  
  private HashSet<String> credentials;
  
  private ReentrantLock credentialsLock;
  
  /**
   * Container class for the singleton.
   */
  private static class Singleton
  {
    private static final CredentialsSingleton INSTANCE = new CredentialsSingleton();
  }
  
  /**
   * Constructor to initialize the credentials data structure.
   */
  private CredentialsSingleton()
  {
    credentials = new HashSet<String>();
    credentialsLock = new ReentrantLock();
  }
  
  /**
   * Sets the session id as a valid global session id.
   * 
   * @param sessionId
   */
  public void setSessionId(String sessionId)
  {
    credentialsLock.lock();
    try
    {
      credentials.add(sessionId);
    }
    finally
    {
      credentialsLock.unlock();
    }
  }
  
  /**
   * Removes the given session id.
   * 
   * @param sessionId
   */
  public void removeSessionId(String sessionId)
  {
    credentialsLock.lock();
    try
    {
      credentials.remove(sessionId);
    }
    finally
    {
      credentialsLock.unlock();
    }
  }
  
  /**
   * Checks if the given session id exists.
   * 
   * @return true if the session id exists or false otherwise.
   */
  public boolean sessionIdExists(String sessionId)
  {
    credentialsLock.lock();
    try
    {
      return credentials.contains(sessionId);
    }
    finally
    {
      credentialsLock.unlock();
    }
  }
  
  /**
   * Returns the singleton instance.
   * 
   * @return
   */
  public static CredentialsSingleton getInstance()
  {
    return Singleton.INSTANCE;
  }
}
