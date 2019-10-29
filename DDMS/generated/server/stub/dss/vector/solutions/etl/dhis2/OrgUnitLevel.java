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
package dss.vector.solutions.etl.dhis2;

import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class OrgUnitLevel extends OrgUnitLevelBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 787015857;

  public OrgUnitLevel()
  {
    super();
  }

  @Override
  public String buildKey()
  {
    return this.getDhis2Id();
  }

  public static OrgUnitLevel[] getAll()
  {
    OrgUnitLevelQuery query = new OrgUnitLevelQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getLevel());

    OIterator<? extends OrgUnitLevel> it = query.getIterator();

    try
    {
      List<? extends OrgUnitLevel> levels = it.getAll();

      return levels.toArray(new OrgUnitLevel[levels.size()]);
    }
    finally
    {
      it.close();
    }
  }
}
