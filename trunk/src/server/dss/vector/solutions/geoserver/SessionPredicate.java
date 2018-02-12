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
package dss.vector.solutions.geoserver;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.util.Predicate;

public class SessionPredicate implements Predicate<String>, Reloadable
{
  public static final String PREFIX = "lv_";

  public static String getSessionId(String name)
  {
    String[] split = name.split("_");

    if (split != null && split.length == 3)
    {
      String sessionId = split[1];

      return sessionId;
    }

    return null;
  }

  public static String generateId()
  {
    SessionIF session = Session.getCurrentSession();

    if (session != null)
    {
      String sessionId = session.getId();

      // The max length for a postgres table name is 63 characters, and as a
      // result our metadata is set at max length 63
      // as well.

      String vn = PREFIX + sessionId + "_" + IDGenerator.nextID().substring(0, 10);

      return vn;
    }

    return PREFIX + IDGenerator.nextID().substring(0, 10);
  }

  @Override
  public boolean evaulate(String name)
  {
    // We must remove the viewName from the list if the session is still active.
    // Thus it will not be in the list of views to delete.
    String sessionId = SessionPredicate.getSessionId(name);

    if (sessionId != null)
    {
      return SessionFacade.containsSession(sessionId);
    }

    // By default remove all viewName
    return true;
  }
}
