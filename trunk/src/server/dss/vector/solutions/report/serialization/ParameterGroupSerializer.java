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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterGroupDefn;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author rrowlands
 */
public class ParameterGroupSerializer extends ParameterSerializer
{
  /**
   * This value is read by the client javascript to know what type the parameter is so it knows how to read it.
   */
  public static final String PARAMETER_TYPE_PARAMETER_GROUP = "ParameterGroup";
  
  public static final String CONTENTS = "contents";
  
  public static final String GROUP_NAME = "groupName";
  
  private IParameterGroupDefn group;
  
  public ParameterGroupSerializer(IGetParameterDefinitionTask task, IReportRunnable design, IParameterGroupDefn group)
  {
    super(task, design);
    
    this.group = group;
  }

  /**
   * @return the group
   */
  public IParameterGroupDefn getGroup()
  {
    return group;
  }

  /**
   * @param group the group to set
   */
  public void setGroup(IParameterGroupDefn group)
  {
    this.group = group;
  }
  
  public JSONObject toJSON() throws JSONException
  {
    JSONObject json = new JSONObject();
    json.put(ParameterSerializer.PARAMETER_TYPE, PARAMETER_TYPE_PARAMETER_GROUP);
    json.put(GROUP_NAME, group.getName());
    
    JSONArray jsonContents = new JSONArray();
    
    @SuppressWarnings("unchecked")
    ArrayList<IScalarParameterDefn> contents = group.getContents();

    Iterator<IScalarParameterDefn> it = contents.iterator();

    while (it.hasNext())
    {
      IScalarParameterDefn scalar = it.next();

      jsonContents.put(new ScalarParameterSerializer(this.getTask(), this.getDesign(), scalar).toJSON());
    }
    
    json.put(CONTENTS, jsonContents);
    
    return json;
  }
}
