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

import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.json.JSONException;
import org.json.JSONObject;

abstract public class ParameterSerializer
{
  /**
   * The json key that tells what type of parameter this is;
   */
  public static final String PARAMETER_TYPE = "parameterType";
  
  private IGetParameterDefinitionTask task;
  
  private IReportRunnable design;
  
  public ParameterSerializer(IGetParameterDefinitionTask task, IReportRunnable design)
  {
     this.task = task;
     this.design = design;
  }
  
  /**
   * @return the task
   */
  public IGetParameterDefinitionTask getTask()
  {
    return task;
  }

  /**
   * @param task the task to set
   */
  public void setTask(IGetParameterDefinitionTask task)
  {
    this.task = task;
  }

  /**
   * @return the design
   */
  public IReportRunnable getDesign()
  {
    return design;
  }

  /**
   * @param design the design to set
   */
  public void setDesign(IReportRunnable design)
  {
    this.design = design;
  }
  
  abstract public JSONObject toJSON() throws JSONException;
}
