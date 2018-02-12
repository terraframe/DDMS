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
package dss.vector.solutions.irs;

import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

public class SprayTeamView extends SprayTeamViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2088238935;
  
  public SprayTeamView()
  {
    super();
  }
 
  public static SprayTeamViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    SprayTeamViewQuery query = new SprayTeamViewQuery(new QueryFactory());
    
    if(sortAttribute == null)
    {
      sortAttribute = SprayTeamView.TEAMID;
    }

    Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (sortAttribute.equalsIgnoreCase(SprayTeamView.SPRAYZONE))
    {
      attribute = ( (AttributeReference) attribute ).aCharacter("entityName");
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) attribute, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) attribute, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }
}
