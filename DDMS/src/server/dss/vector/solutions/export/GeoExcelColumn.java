package dss.vector.solutions.export;

import java.util.Map;

import com.runwaysdk.ComponentIF;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.generation.loader.Reloadable;

public class GeoExcelColumn extends ExcelColumn implements Reloadable
{
  private String universalId;

  private String baseAttribute;

  public GeoExcelColumn(String universalId, String baseAttribute, String attributeName, String displayLabel)
  {
    super(attributeName, displayLabel);

    this.universalId = universalId;
    this.baseAttribute = baseAttribute;
  }

  public String getUniversalId()
  {
    return universalId;
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

    return null;
  }
}
