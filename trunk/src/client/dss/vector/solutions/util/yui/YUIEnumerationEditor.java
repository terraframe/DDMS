package dss.vector.solutions.util.yui;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO;

import dss.vector.solutions.util.Halp;

public class YUIEnumerationEditor extends YUIEditor implements Reloadable
{
  private AttributeEnumerationMdDTO attribute;
  
  private Boolean includeBlank;

  public YUIEnumerationEditor(AttributeEnumerationMdDTO attribute, ColumnSetup setup)
  {
    this.attribute = attribute;
    this.includeBlank = (setup.getIncludeBlank() != null ? setup.getIncludeBlank() : !attribute.isRequired());
  }
  
  @Override
  public List<String> getOptions()
  {
    List<String> options = new LinkedList<String>();

    List<String> dropdownOptions = new LinkedList<String>();

    if(includeBlank)
    {
      dropdownOptions.add("{label:'', value:''}");
    }
    
    for (Map.Entry<String, String> e : attribute.getEnumItems().entrySet())
    {
      dropdownOptions.add("{label:'" + e.getValue() + "', value:'" + e.getKey() + "'}");
    }

    options.add("dropdownOptions:[" + Halp.join(dropdownOptions) + "]");

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
    String value = object.toString();
    
    return value.replaceAll("\\[", "").replaceAll("\\]", "");
  }
  
  @Override
  public String getDefaultValue(Object value)
  {
    return "";
  }

}
