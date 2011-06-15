package dss.vector.solutions.admin.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.catalina.Lifecycle;

import com.runwaysdk.tomcat.RemoteLifecycleListenerIF;

public class RemoteLifecycleListener extends UnicastRemoteObject implements RemoteLifecycleListenerIF
{

  /**
   * 
   */
  private static final long serialVersionUID = 71683538706136154L;

  private Server    server;

  protected RemoteLifecycleListener(Server server) throws RemoteException
  {
    super();
    
    this.server = server;
  }

  public void lifecycleEvent(String type) throws RemoteException
  {
    if(type.equals(Lifecycle.AFTER_START_EVENT))
    {
      this.server.fireServerChange(true);
    }
    else if(type.equals(Lifecycle.AFTER_STOP_EVENT))
    {
      this.server.fireServerChange(false);
      this.server.closeRemoteServer();
    }
  }
}