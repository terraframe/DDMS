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
import com.runwaysdk.system.metadata.MdAttributeBooleanDTO;
import com.runwaysdk.transport.attributes.AttributeBooleanDTO;

public class SelectableBooleanOption extends SelectableAttributeOption implements Reloadable
{
  private String positiveLabel;

  private String negativeLabel;

  public SelectableBooleanOption(MdAttributeBooleanDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);

    this.positiveLabel = mdAttribute.getPositiveDisplayLabel().getValue();
    this.negativeLabel = mdAttribute.getNegativeDisplayLabel().getValue();
  }

  @Override
  public String getDTOType()
  {
    return AttributeBooleanDTO.class.getName();
  }

  @Override
  protected JSONObject getSerializationMap() throws JSONException
  {
    JSONObject map = super.getSerializationMap();
    
    JSONObject ddMap = new JSONObject();
    ddMap.put("true", positiveLabel);
    ddMap.put("false", negativeLabel);
    
    map.put("dropDownMap", ddMap);
    
//    map.put("dropDownMap", "{'true':'" + positiveLabel + "','false':'" + negativeLabel + "'}");

    return map;
  }
}
