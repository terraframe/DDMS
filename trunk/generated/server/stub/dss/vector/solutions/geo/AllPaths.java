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

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.GeoEntityAllPathBuilder;

public class AllPaths extends AllPathsBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245657919728L;

  public AllPaths()
  {
    super();
  }

  /**
   * @param args
   */
  @Request
  public static void main(String[] args)
  {
    System.out.println(System.currentTimeMillis());
    try
    {
      GeoEntity.buildAllPathsFast();
    }
    finally
    {
      CacheShutdown.shutdown();
    }
    System.out.println(System.currentTimeMillis());
  }

  public static void deleteAllTableRecords()
  {
    MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    mdBusiness.deleteAllTableRecords();
  }

  public static void regeneratePaths()
  {
    GeoEntityAllPathBuilder.main(new String[] {});
  }

  public static boolean containsValues()
  {
    QueryFactory f = new QueryFactory();
    ValueQuery geoVQ = new ValueQuery(f);
    AllPathsQuery geoAP = new AllPathsQuery(geoVQ);
    geoVQ.SELECT(geoAP.getId());

    return ( geoVQ.getCount() > 0 );
  }

  @Override
  protected String buildKey()
  {
    // TODO: Naifeh needs to define this key
    return this.getId();
  }
}
