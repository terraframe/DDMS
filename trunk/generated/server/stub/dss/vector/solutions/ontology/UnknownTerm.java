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
package dss.vector.solutions.ontology;

import org.json.JSONException;
import org.json.JSONObject;

public class UnknownTerm extends UnknownTermBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -749809791;

  public UnknownTerm()
  {
    super();
  }

  public JSONObject serialize() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put(TERMNAME, this.getTermName());
    object.put(BROWSERCLASS, this.getBrowserClass());
    object.put(BROWSERATTRIBUTE, this.getBrowserAttribute());
    object.put(ATTRIBUTELABEL, this.getAttributeLabel());

    return object;
  }

  public static UnknownTerm deserialize(JSONObject object) throws JSONException
  {
    UnknownTerm uTerm = new UnknownTerm();
    uTerm.setTermName(object.getString(TERMNAME));
    uTerm.setBrowserClass(object.getString(BROWSERCLASS));
    uTerm.setBrowserAttribute(object.getString(BROWSERATTRIBUTE));
    uTerm.setAttributeLabel(object.getString(ATTRIBUTELABEL));

    return uTerm;
  }

}
