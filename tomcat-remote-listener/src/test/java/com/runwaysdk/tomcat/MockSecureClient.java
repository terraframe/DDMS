package com.runwaysdk.tomcat;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class MockSecureClient
{
  private MockRemoteLifecycleListener listener;

  public MockSecureClient(int portNum) throws RemoteException, MalformedURLException, NotBoundException
  {
    this.listener = new MockRemoteLifecycleListener();

    Registry registry = LocateRegistry.getRegistry(null, portNum, new SslRMIClientSocketFactory());
    RemoteLifecycleListenerServerIF server = (RemoteLifecycleListenerServerIF) registry.lookup(RemoteLifecycleListenerServer.NAME);
    server.addListener(listener);
  }

  public MockRemoteLifecycleListener getListener()
  {
    return listener;
  }

}
