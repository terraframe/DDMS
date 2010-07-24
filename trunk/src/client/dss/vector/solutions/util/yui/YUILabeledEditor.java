package dss.vector.solutions.util.yui;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeMdDTO;

import dss.vector.solutions.LabeledDTO;
import dss.vector.solutions.util.Halp;

public class YUILabeledEditor extends YUIEditor implements Reloadable
{
  private String type;

  private String method;

  private String key;
  
  private Boolean includeBlank;

  public YUILabeledEditor(AttributeMdDTO attribute, ColumnSetup setup, String key)
  {
    this.type = attribute.getJavaType().getName();
    this.method = "getAllActive";
    this.key = key;
    this.includeBlank = (setup.getIncludeBlank() != null ? setup.getIncludeBlank() : false);

    if (setup.getType() != null)
    {
      this.type = setup.getType();
    }

    if (setup.getMethod() != null)
    {
      this.method = setup.getMethod();
    }
  }

  @Override
  public List<String> getOptions()
  {
    List<String> options = new LinkedList<String>();

    options.add("dropdownOptions:" + key + "Options");

    return options;
  }

  @Override
  public String getType()
  {
    return DROPDOWN_EDITOR;
  }

  @Override
  public String getValue(Object object)
  {
    LabeledDTO labeled = (LabeledDTO) object;

    return Halp.getLabeledValue(labeled.getLabel(), labeled.getOptionId());
  }

  @Override
  public String getDefaultValue(Object value)
  {
    LabeledDTO labeled = (LabeledDTO) value;

    return "{'value':'" + labeled.getOptionId() + "','label':'" + labeled.getLabel() + "'}";
  }

  public String getDropdownOptions(ClientRequestIF request)
  {
    try
    {
      JSONArray options = new JSONArray();
      
      if(this.includeBlank)
      {
        options.put(this.getLabeledPair("", ""));
      }

      Class<?> clazz = LoaderDecorator.load(type);

      LabeledDTO[] terms = (LabeledDTO[]) clazz.getMethod(method, new Class[] { ClientRequestIF.class }).invoke(null, request);

      for (LabeledDTO term : terms)
      {
        options.put(this.getLabeledPair(term.getLabel(), term.getOptionId()));
      }
            
      return key + "Options = " + options.toString() + ";";
    }
    catch (Exception e)
    {
      return key + "Options = [];";
    }
  }

  private JSONObject getLabeledPair(String label, String value)
  {
    JSONObject object = new JSONObject();
    try
    {
      object.put("label", label);
      object.put("value", value);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }

    return object;
  }

}
