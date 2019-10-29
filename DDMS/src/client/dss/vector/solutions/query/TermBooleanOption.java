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
package dss.vector.solutions.query;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;

public class TermBooleanOption extends SelectableOption implements Reloadable
{
  private String positiveLabel;

  private String negativeLabel;

  public TermBooleanOption(String attributeName, String displayLabel, String key, String positiveLabel, String negativeLabel)
  {
    super(attributeName, displayLabel, key);

    this.positiveLabel = positiveLabel;
    this.negativeLabel = negativeLabel;
  }

  @Override
  public String getDTOType()
  {
    return "AttributeBooleanDTO";
  }

  @Override
  public String getType()
  {
    return "sqlinteger";
  }
  
  @Override
  protected JSONObject getSerializationMap() throws JSONException
  {
    JSONObject map = super.getSerializationMap();
    
    JSONObject ddMap = new JSONObject();
    ddMap.put("true", positiveLabel);
    ddMap.put("false", negativeLabel);
    
    map.put("dropDownMap", ddMap);
    
    // TODO this old code not only uses ints instead of booleans but the logic appears inverted.
    // Has this always been broken?
//    map.put("dropDownMap", "{'0':'" + positiveLabel + "','1':'" + negativeLabel + "'}");

    return map;
  }
}
