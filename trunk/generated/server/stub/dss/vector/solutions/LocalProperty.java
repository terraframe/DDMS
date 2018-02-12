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

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class LocalProperty extends LocalPropertyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID         = 567084590;

  public static final long  SHORT_ID_LENGTH          = 8;

  public static final int   RESERVED_SHORT_ID_SPACES = 23;

  public static final long  MAX_ID                   = (long) Math.pow(30, SHORT_ID_LENGTH);

  public LocalProperty()
  {
    super();
  }

  @Transaction
  @Authenticate
  public static String getNextId()
  {
    return nextId();
  }

  public static LocalProperty getByPackAndName(java.lang.String pkg, java.lang.String name)
  {
    LocalPropertyQuery query = new LocalPropertyQuery(new QueryFactory());

    query.WHERE(query.getPropertyPackage().EQ(pkg));
    query.AND(query.getPropertyName().EQ(name));

    OIterator<? extends LocalProperty> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
    }
    finally
    {
      iterator.close();
    }

    return null;
  }

  private static String nextId()
  {
    synchronized (Object.class)
    {
      LocalProperty counterProperty = LocalProperty.getByPackAndName(PropertyInfo.INSTALL_PACKAGE, LocalPropertyInfo.SHORT_ID_COUNTER);
      counterProperty.appLock();

      Long counter = Long.parseLong(counterProperty.getPropertyValue());

      LocalProperty offsetProperty = LocalProperty.getByPackAndName(PropertyInfo.INSTALL_PACKAGE, LocalPropertyInfo.SHORT_ID_OFFSET);
      int parsedOffset = Integer.parseInt(offsetProperty.getPropertyValue());
      int offset = RESERVED_SHORT_ID_SPACES + parsedOffset;
      int segments = Property.getInt(PropertyInfo.SYSTEM_PACKAGE, PropertyInfo.SHORT_ID_SEGMENTS);

      long totalOffset = ( MAX_ID / segments ) * offset;

      counter++;
      counterProperty.setPropertyValue(counter.toString());
      counterProperty.apply();

      //TODO Perhaps a check that the address space has not been overflowed should be added?
      return Base30.toBase30String(totalOffset + counter, 8);
    }
  }
}
