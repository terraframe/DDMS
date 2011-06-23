package dss.vector.solutions.manager.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.runwaysdk.tomcat.RemoteLifecycleListenerIF;

public class RemoteLifecycleListener extends UnicastRemoteObject implements RemoteLifecycleListenerIF
{

  /**
   * 
   */
  private static final long serialVersionUID = 71683538706136154L;

  private Server            server;

  protected RemoteLifecycleListener(Server server) throws RemoteException
  {
    super();

    this.server = server;
  }

  public void lifecycleEvent(String type) throws RemoteException
  {
    ServerStatus status = ServerStatus.getStatus(type);

    this.server.fireServerChange(status);
  }
}