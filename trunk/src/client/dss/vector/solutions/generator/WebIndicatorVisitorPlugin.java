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
