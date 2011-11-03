package dss.vector.solutions.generator;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermDAOIF;
import com.runwaysdk.dataaccess.io.excel.ColumnFactory;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;

public class FormColumnFactory extends ColumnFactory
{
  @Override
  public ExcelColumn getColumn(MdWebAttributeDAOIF mdField)
  {
    if (mdField instanceof MdWebSingleTermDAOIF)
    {
      return new TermColumn((MdAttributeReferenceDAOIF) mdField.getDefiningMdAttribute());
    }

    return super.getColumn(mdField);
  }
}
