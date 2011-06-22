package com.runwaysdk.tomcat;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MockClient
{
  private MockRemoteLifecycleListener listener;

  public MockClient(int portNum) throws RemoteException, MalformedURLException, NotBoundException
  {
    this.listener = new MockRemoteLifecycleListener();

    Registry registry = LocateRegistry.getRegistry(null, portNum);
    RemoteLifecycleListenerServerIF server = (RemoteLifecycleListenerServerIF) registry.lookup(RemoteLifecycleListenerServer.NAME);
    server.addListener(listener);
  }

  public MockRemoteLifecycleListener getListener()
  {
    return listener;
  }
}
