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

import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.MDSSUserViewQuery.NonAdminUserViewBuilder;

public class MDSSUserView extends MDSSUserViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242427875661L;
  
  public MDSSUserView()
  {
    super();
  }
  
  public static MDSSUserViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    QueryFactory factory = new QueryFactory();
    MDSSUserViewQuery query = new MDSSUserViewQuery(factory, new NonAdminUserViewBuilder(factory));

    if (sortAttribute==null)
    {
      sortAttribute = USERNAME;
    }
    
    SelectablePrimitive selectable = (SelectablePrimitive)query.getComponentQuery().getSelectableRef(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC(selectable, sortAttribute);
    }
    
    if (pageSize != 0 && pageNumber != 0)
    {
       query.restrictRows(pageSize, pageNumber);
    }
    
    return query;
  }
}
