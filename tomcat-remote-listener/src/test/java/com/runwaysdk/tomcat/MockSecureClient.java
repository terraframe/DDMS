/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
