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
package dss.vector.solutions.geo;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.generated.GeoEntity;

public class LocatedIn extends LocatedInBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133821075L;

  public LocatedIn(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public LocatedIn(dss.vector.solutions.geo.generated.GeoEntity parent, dss.vector.solutions.geo.generated.GeoEntity child)
  {
    this(parent.getId(), child.getId());
  }
  
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }

  @Override
  @Transaction
  public void apply()
  {
    super.apply();

    GeoEntity.updateAllPathForGeoEntity(this.getChildId(), this.getParentId());
  }
  
  @Transaction
  public void applyWithoutCreatingAllPaths()
  {
    super.apply();
  }
}
