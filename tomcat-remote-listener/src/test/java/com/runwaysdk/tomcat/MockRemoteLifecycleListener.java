package com.runwaysdk.tomcat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MockRemoteLifecycleListener extends UnicastRemoteObject implements RemoteLifecycleListenerIF
{

  /**
   * 
   */
  private static final long serialVersionUID = 71683538706136154L;

  private String    type;

  protected MockRemoteLifecycleListener() throws RemoteException
  {
    super();
  }

  public void lifecycleEvent(String type) throws RemoteException
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }
}
