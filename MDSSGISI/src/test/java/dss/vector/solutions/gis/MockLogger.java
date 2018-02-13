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
package dss.vector.solutions.gis;

import java.util.HashMap;

public class MockLogger implements GISImportLoggerIF
{
  private HashMap<String, Throwable> map;

  private HashMap<String, String> messages;

  public MockLogger()
  {
    this.map = new HashMap<String, Throwable>();
    this.messages = new HashMap<String, String>();
  }

  @Override
  public boolean hasLogged()
  {
    return ( map.size() > 0 || messages.size() > 0);
  }

  @Override
  public void log(String featureId, Throwable t)
  {
    map.put(featureId, t);
  }
  
  @Override
  public void log(String featureId, String message)
  {
    messages.put(featureId, message);
  }


  @Override
  public void close()
  {
  }

  public HashMap<String, Throwable> getMap()
  {
    return map;
  }
  
  public HashMap<String, String> getMessages()
  {
    return messages;
  }
}
