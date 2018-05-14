package dss.vector.solutions.export;

import java.util.Map;

import com.runwaysdk.ComponentIF;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.generation.loader.Reloadable;

public class GeoExcelColumn extends ExcelColumn implements Reloadable
{
  private String baseAttribute;

  public GeoExcelColumn(String baseAttribute, String attributeName, String displayLabel)
  {
    super(attributeName, displayLabel);

    this.baseAttribute = baseAttribute;
  }

  public String getBaseAttribute()
  {
    return baseAttribute;
  }

  public String getValue(ComponentIF component, Map<String, String> overrides)
  {
    String attributeName = this.getAttributeName();

    if (overrides.containsKey(attributeName))
    {
      return overrides.get(attributeName);
    }

    return "";
  }
}
