/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.kaleidoscope.oda.driver.session;

import com.runwaysdk.ClientSession;
import com.runwaysdk.constants.ClientRequestIF;

public class ClientSessionProxy implements IClientSession
{
  private String        url;

  private String        username;

  private ClientSession session;

  public ClientSessionProxy(String url, String username, ClientSession session)
  {
    this.url = url;
    this.username = username;
    this.session = session;
  }

  public ClientRequestIF getRequest()
  {
    return this.session.getRequest();
  }

  public void logout()
  {
    this.session.logout();
  }

  public String getKey()
  {
    return ClientSessionProxy.buildKey(this.url, this.username);
  }

  public static String buildKey(String url, String username)
  {
    return url + "-" + username;
  }
}
