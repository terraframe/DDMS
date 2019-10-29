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
package dss.vector.solutions.generator;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.form.web.JSONFormVisitor;
import com.runwaysdk.form.web.JSONFormVisitor.PluginIF;
import com.runwaysdk.form.web.JSONFormVisitor.WebFieldToJSON;
import com.runwaysdk.form.web.JSONFormVisitor.WebFormComponentToJSON;
import com.runwaysdk.form.web.WebFormComponent;

import dss.vector.solutions.generator.WebIndicatorPlugin.WebIndicator;

public class WebIndicatorVisitorPlugin implements PluginIF
{
  public static class WebIndicatorToJSON extends WebFieldToJSON
  {
    protected WebIndicatorToJSON(WebIndicator indicator, JSONFormVisitor visitor)
    {
      super(indicator, visitor);
    }
    
    @Override
    protected void initField(JSONObject obj) throws JSONException
    {
      super.initField(obj);
      
      this.getVisitor().put(obj, JSONFormVisitor.WRITABLE, false);
      this.getVisitor().put(obj, JSONFormVisitor.MODIFIED, false);      
    }
  }

  @Override
  public String getModuleIdentifier()
  {
    return "ddms";
  }

  @Override
  public WebFormComponentToJSON getBuilder(WebFormComponent component, JSONFormVisitor visitor)
  {
    if (component instanceof WebIndicator)
    {
      return new WebIndicatorToJSON((WebIndicator) component, visitor);
    }

    return null;

  }

}
