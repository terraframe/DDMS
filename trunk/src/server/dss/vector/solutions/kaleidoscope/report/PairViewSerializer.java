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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

public class PairViewSerializer implements Reloadable
{

  public String serialize(List<PairView> views)
  {
    try
    {
      JSONArray attributeNames = new JSONArray();
      attributeNames.put(PairView.LABEL);
      attributeNames.put(PairView.VALUE);

      JSONArray results = new JSONArray();

      for (PairView view : views)
      {
        JSONObject result = new JSONObject();
        result.put(PairView.LABEL, view.getLabel());
        result.put(PairView.VALUE, view.getValue());

        results.put(result);
      }

      JSONArray metadata = new JSONArray();
      metadata.put(this.serialize(PairView.getLabelMd()));
      metadata.put(this.serialize(PairView.getValueMd()));

      JSONObject query = new JSONObject();
      query.put("attributeNames", attributeNames);
      query.put("results", results);
      query.put("metadata", metadata);

      return query.toString();
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }

  private JSONObject serialize(MdAttributeConcreteDAOIF mdAttribute) throws JSONException
  {
    AttributeMetadataIF metadata = RemoteQueryBuilder.buildAttributeMetadata(mdAttribute);

    return metadata.toJSON();
  }

}
