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

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;

public class CategoryProblem extends FieldValue implements ImportProblemIF, Comparable<ImportProblemIF>, Reloadable
{
  public static final String TYPE = "categories";

  private String             label;

  private String             mdAttributeId;

  private String             attributeLabel;

  private String             categoryId;

  public CategoryProblem(String label, String categoryId, String mdAttributeId, String attributeLabel)
  {
    this.label = label;
    this.categoryId = categoryId;
    this.mdAttributeId = mdAttributeId;
    this.attributeLabel = attributeLabel;
  }

  public String getMdAttributeId()
  {
    return mdAttributeId;
  }

  public String getKey()
  {
    return this.categoryId + "-" + String.valueOf(this.label).toLowerCase();
  }

  @Override
  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("type", "DOMAIN");
    object.put("label", label);
    object.put("categoryId", categoryId);
    object.put("mdAttributeId", mdAttributeId);
    object.put("attributeLabel", attributeLabel);

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
