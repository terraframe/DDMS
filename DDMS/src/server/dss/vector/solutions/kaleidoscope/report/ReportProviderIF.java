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
package dss.vector.solutions.kaleidoscope.report;

import java.util.List;

import org.json.JSONException;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.kaleidoscope.geo.GeoNode;

public interface ReportProviderIF extends Reloadable
{
  /**
   * Dashboard name
   */
  public static final String DASHBOARD_NAME = "dashboardName";

  /**
   * Dashboard id
   */
  public static final String DASHBOARD_ID   = "dashboardId";

  /**
   * A list of id-label pairing for all of the report queries supported by this provider
   * 
   * @return
   */
  public List<PairView> getSupportedQueryDescriptors();

  /**
   * If the ReportProvider supports the given query
   * 
   * @param queryId
   * @return
   */
  public boolean hasSupport(String queryId);

  /**
   * List of all of the aggregations supported by the given query
   * 
   * @param queryId
   * @return
   */
  public List<PairView> getSupportedAggregation(String queryId);

  /**
   * List of all of the geo nodes supported by the given query
   * 
   * @param queryId
   * @return
   */
  public List<GeoNode> getSupportedGeoNodes(String queryId);

  /**
   * Returns a ValueQuery containing all of the results for the given query based upon the context in which the query is run.
   * 
   * @param queryId
   * @param context
   * @return
   * @throws JSONException
   */
  public ValueQuery getReportQuery(String queryId, String context) throws JSONException;

}
