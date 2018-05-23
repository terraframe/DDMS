package dss.vector.solutions.odk;

import java.util.Map;

import com.runwaysdk.ComponentIF;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.generation.loader.Reloadable;

public class UUIDExcelColum extends ExcelColumn implements Reloadable
{
  public UUIDExcelColum()
  {
    super("_UUID_", "UUID");
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
