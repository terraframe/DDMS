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
package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;


public class LocationProblem implements ImportProblemIF, Comparable<ImportProblemIF>, Reloadable
{
  public static final String TYPE = "locations";

  private String             label;

  private List<JSONObject>   context;

  private GeoEntity          parent;

  private GeoHierarchy       universal;

  public LocationProblem(String label, List<JSONObject> context, GeoEntity parent, GeoHierarchy universal)
  {
    this.label = label;
    this.context = context;
    this.parent = parent;
    this.universal = universal;
  }

  public String getKey()
  {
    return this.parent.getId() + "-" + this.label;
  }

  @Override
  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("type", "LOCATION");
    object.put("label", label);
    object.put("parentId", parent.getId());
    object.put("universalId", universal.getGeoEntityClass().definesType()); // If you use `univeral.getId()` here (which works for creating a new location) then it screws up the autocomplete.
    object.put("universalLabel", universal.getDisplayLabel());
    object.put("context", new JSONArray(context));

    return object;
  }

  @Override
  public int compareTo(ImportProblemIF problem)
  {
    return this.getKey().compareTo(problem.getKey());
  }

  @Override
  public String getType()
  {
    return TYPE;
  }
}
