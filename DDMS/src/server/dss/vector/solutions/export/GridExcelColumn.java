package dss.vector.solutions.export;

import java.util.Map;

import com.runwaysdk.ComponentIF;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.generation.loader.Reloadable;

public class GridExcelColumn extends ExcelColumn implements Reloadable
{
  private String gridAttribute;

  private String termId;

  private String subAttribute;

  public GridExcelColumn(String attributeName, String displayLabel, String gridAttribute, String termId)
  {
    this(attributeName, displayLabel, gridAttribute, termId, null);
  }

  public GridExcelColumn(String attributeName, String displayLabel, String gridAttribute, String termId, String subAttribute)
  {
    super(attributeName, displayLabel);

    this.gridAttribute = gridAttribute;
    this.termId = termId;
    this.subAttribute = subAttribute;
  }

  public String getTermId()
  {
    return termId;
  }

  public String getGridAttribute()
  {
    return gridAttribute;
  }

  public String getSubAttribute()
  {
    return subAttribute;
  }

  public boolean isColumn(String gridAttribute, String termId, String subAttribute)
  {
    if (this.subAttribute != null && subAttribute != null)
    {
      return this.gridAttribute.equals(gridAttribute) && this.termId.equals(termId) && this.subAttribute.equals(subAttribute);
    }

    return this.gridAttribute.equals(gridAttribute) && this.termId.equals(termId);
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
