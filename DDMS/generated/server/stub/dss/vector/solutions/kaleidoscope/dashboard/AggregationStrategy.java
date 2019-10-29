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
package dss.vector.solutions.kaleidoscope.dashboard;

import java.util.LinkedList;

import org.json.JSONObject;

import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;

public abstract class AggregationStrategy extends AggregationStrategyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -27686881;

  public AggregationStrategy()
  {
    super();
  }

  public abstract ValueQuery getViewQuery(DashboardThematicLayer layer, LinkedList<Drilldown> drilldowns);

  public abstract JSONObject getJSON();

  public abstract String getCategoryLabel(GeoNode geoNode, String categoryId);

  public abstract AggregationStrategy clone();
}
