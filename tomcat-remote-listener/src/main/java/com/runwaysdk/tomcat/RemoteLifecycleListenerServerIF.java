package com.runwaysdk.tomcat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteLifecycleListenerServerIF extends Remote
{
  public void addListener(RemoteLifecycleListenerIF listener) throws RemoteException;

  public void removeListener(RemoteLifecycleListenerIF listener) throws RemoteException;

  public String getCurrentState() throws RemoteException;
}
