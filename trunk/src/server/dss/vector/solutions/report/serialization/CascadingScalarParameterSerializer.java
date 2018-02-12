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
package dss.vector.solutions.report.serialization;

import java.util.Collection;
import java.util.List;

import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterSelectionChoice;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CascadingScalarParameterSerializer extends ScalarParameterSerializer
{
  private List<Object> cascadingValues;
  
  private String groupName;
  
  public CascadingScalarParameterSerializer(IGetParameterDefinitionTask task, IReportRunnable design, IScalarParameterDefn scalar, List<Object> cascadingValues, String groupName)
  {
    super(task, design, scalar);
    
    this.cascadingValues = cascadingValues;
    this.groupName = groupName;
  }
  
  @Override
  public JSONArray optionsToJSON(Object defaultValue, String convertedDefaultValue) throws JSONException
  {
    JSONArray options = new JSONArray();
    
    @SuppressWarnings("unchecked")
    Collection<IParameterSelectionChoice> sList = this.getTask().getSelectionListForCascadingGroup(groupName, cascadingValues.toArray());

    boolean didAddCascaded = false;
    for (IParameterSelectionChoice sI : sList)
    {
      String label = sI.getLabel();
      Object value = sI.getValue();

      JSONObject object = new JSONObject();
      object.put("label", label);
      object.put("value", value);
      options.put(object);
      
      if (!didAddCascaded)
      {
        if (defaultValue != null)
        {
          cascadingValues.add(defaultValue);
        }
        else
        {
          cascadingValues.add(value);
        }
        didAddCascaded = true;
      }
    }

    return options;
  }
}
