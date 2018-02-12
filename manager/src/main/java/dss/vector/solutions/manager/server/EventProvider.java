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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EventProvider
{
  private List<IServerListener> listeners;

  public EventProvider()
  {
    this.listeners = Collections.synchronizedList(new ArrayList<IServerListener>());
  }

  public void addListener(IServerListener listener)
  {
    listeners.add(listener);
  }

  public void removeListener(IServerListener listener)
  {
    listeners.remove(listener);
  }

  protected void fireErrorEvent(final String msg)
  {
    for (IServerListener listener : listeners)
    {
      listener.error(msg);
    }
  }
  
  protected void fireErrorEvent(Throwable t)
  {
    for (IServerListener listener : listeners)
    {
      listener.error(t);
    }
  }
  
  public void fireServerChange(ServerStatus status)
  {
    for (IServerListener listener : listeners)
    {
      listener.serverStateChange(status);
    }
  }



}
