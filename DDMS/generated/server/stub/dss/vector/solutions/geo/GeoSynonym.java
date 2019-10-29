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

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GeoSynonym extends GeoSynonymBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1248321233376L;

  public GeoSynonym()
  {
    super();
  }

  public static void createSynonym(java.lang.String entityName, java.lang.String geoId)
  {
    GeoEntity entity = GeoEntity.searchByGeoId(geoId);
    entity.addSynonym(entityName);
  }
  
  public static GeoSynonym getByNameAndGeo(String geoId, String name)
  {
    QueryFactory qf = new QueryFactory();
    
    GeoEntityQuery geq = new GeoEntityQuery(qf);
    
    GeoSynonymQuery gsq = new GeoSynonymQuery(qf);
    gsq.WHERE(gsq.getEntityName().EQ(name));
    gsq.AND(gsq.geoEntity(geq));
    
    geq.WHERE(geq.getGeoId().EQ(geoId));
    
    OIterator<? extends GeoSynonym> it = gsq.getIterator();
    if (!it.hasNext())
    {
      return null;
    }
    else
    {
      return it.next();
    }
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }
}
