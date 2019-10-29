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
package com.runwaysdk.query;
import java.util.Map;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdTableClassIF;

/**
 * This class exists as a way to hack around protected method permissions in the query API.
 */
public class QueryHacker
{
  public static TableClassQuery getTableClassQuery(GeneratedTableClassQuery gtcq)
  {
    return gtcq.getComponentQuery();
  }

  public static Map<String, ? extends MdAttributeDAOIF> getAttributeMap(TableClassQuery tcq)
  {
    return tcq.mdAttributeMap;
  }
  
  public static Map<String, ColumnInfo> getColumnInfoMap(TableClassQuery tcq)
  {
    return tcq.columnInfoMap;
  }
  
  public static Attribute attributeFactory(ComponentQuery rootComponentQuery, Selectable selectable, MdTableClassIF definingTableClassIF, String definingTableName, String definingTableAlias, MdAttributeConcreteDAOIF mdAttributeIF, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    return ValueQuery.attributeFactory(rootComponentQuery, selectable, definingTableClassIF, definingTableName, definingTableAlias, mdAttributeIF, userDefinedAlias, userDefinedDisplayLabel);
  }
}
