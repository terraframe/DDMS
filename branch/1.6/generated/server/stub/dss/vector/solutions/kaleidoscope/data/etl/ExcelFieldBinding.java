package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.system.metadata.MdAttribute;

public class ExcelFieldBinding extends ExcelFieldBindingBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1684656210;

  public ExcelFieldBinding()
  {
    super();
  }

  public SourceFieldIF getSourceField()
  {
    MdAttribute mdAttribute = this.getMdAttribute();

    SourceField field = new SourceField();
    field.setAttributeName(mdAttribute.getAttributeName());
    field.setColumnName(this.getColumnHeader());
    field.setLabel(mdAttribute.getDisplayLabel().getValue());
    field.setType(ColumnType.valueOf(this.getColumnType()));

    return field;
  }

}
