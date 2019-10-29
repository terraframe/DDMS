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

import java.util.List;

import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.GenericHierarchySearcher;
import dss.vector.solutions.util.SearchableHierarchy;

public class GeoHierarchyView extends GeoHierarchyViewBase implements SearchableHierarchy, com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236279045041L;

  public GeoHierarchyView()
  {
    super();
  }

  @Override
  public int hashCode()
  {

    return this.getGeoHierarchyId().hashCode();

  };

  @Override
  public boolean equals(Object obj)
  {
    boolean equals = super.equals(obj);

    if (!equals && obj instanceof GeoHierarchyView)
    {
      equals = this.getGeoHierarchyId().equals( ( (GeoHierarchyView) obj ).getGeoHierarchyId());
    }

    return equals;
  }

  public List<GeoEntity> searchGeoEntity(String entityName, GeoEntity parent)
  {
    MdBusiness mdBusiness = MdBusiness.get(this.getReferenceId());
    GenericHierarchySearcher searcher = new GenericHierarchySearcher(mdBusiness.definesType());

    return searcher.searchGeoEntity(entityName, parent);
  }

  public String toString()
  {
    return this.getTypeName();
  }

  public String getGeneratedType()
  {
    return MDSSInfo.GENERATED_GEO_PACKAGE + "." + this.getTypeName();
  }

  public static GeoHierarchyView[] getUrbanHierarchies(java.lang.String geoId)
  {
    GeoEntity entity = GeoEntity.get(geoId);
    SearchParameter parameter = new SearchParameter(false, false, false, true, false, false);

    return GeoHierarchy.getGeoHierarchiesByType(entity.getType(), parameter);
  }
}
