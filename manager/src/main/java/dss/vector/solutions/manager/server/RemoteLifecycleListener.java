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
