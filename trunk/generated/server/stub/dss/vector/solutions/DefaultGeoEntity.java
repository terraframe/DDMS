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
package dss.vector.solutions;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

/**
 * A singleton that represents the default geo entity of the system.
 */
public class DefaultGeoEntity extends DefaultGeoEntityBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 648964684;
  
  public DefaultGeoEntity()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    if(isNew())
    {
      String error = "Only one instance of DefaultGeoEntity can exist."; 
      throw new ProgrammingErrorException(error);
    }
    
    super.apply();
  }
  
  @Override
  public String toString()
  {
    return this.getClassDisplayLabel();
  }
  
  @Override
  public void delete()
  {
    String error = "The singleton object of DefaultGeoEntity cannot be deleted.";
    throw new ProgrammingErrorException(error);
  }
  
  public static DefaultGeoEntity getDefaultGeoEntity()
  {
    QueryFactory f= new QueryFactory();
    DefaultGeoEntityQuery q = new DefaultGeoEntityQuery(f);
    
    OIterator<? extends DefaultGeoEntity> iter = q.getIterator();
    
    try
    {
      return iter.next();
    }
    finally
    {
      iter.close();
    }
    
  }
  
}
