package com.runwaysdk.tomcat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteLifecycleListenerIF extends Remote
{
  public void lifecycleEvent(String type) throws RemoteException;
}
