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
package dss.vector.solutions.util;

import java.util.HashMap;

import com.runwaysdk.constants.MetadataInfo;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;

public class SelectableSQLKey extends SelectableSQLCharacter
{
  public SelectableSQLKey(boolean isAggregate, ValueQuery rootQuery, String attributeName, String sql)
  {
    super(isAggregate, rootQuery, attributeName, sql);
  }

  public SelectableSQLKey(boolean isAggregate, ValueQuery rootQuery, String attributeName, String sql, MdAttributeReferenceDAOIF mdAttribute)
  {
    super(isAggregate, rootQuery, attributeName, sql);

    HashMap<String, Object> data = new HashMap<String, Object>();
    data.put(MetadataInfo.CLASS, mdAttribute);

    this.setData(data);
  }
}
