package dss.vector.solutions.generator;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class FormImportContext extends ImportContext implements Reloadable
{

  public FormImportContext(HSSFSheet sheet, String sheetName, HSSFSheet error, MdClassDAOIF mdClass)
  {
    super(sheet, sheetName, error, mdClass);
  }

  @Override
  protected Mutable getMutableForRow(HSSFRow row)
  {
    ExcelColumn column = this.getColumn(MdFormUtil.OID);
    String oid = ExcelUtil.getString(row.getCell(column.getIndex()));

    if (oid != null && oid.length() > 0)
    {
      MdClassDAOIF mdClass = this.getMdClass();

      QueryFactory factory = new QueryFactory();
      BusinessQuery query = factory.businessQuery(mdClass.definesType());
      query.WHERE(query.aCharacter(MdFormUtil.OID).EQ(oid));

      OIterator<Business> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          Business mutable = it.next();
          mutable.lock();

          return mutable;
        }
      }
      finally
      {
        it.close();
      }
    }

    return super.getMutableForRow(row);
  }

  private ExcelColumn getColumn(String columnName)
  {
    List<AttributeColumn> columns = this.getExpectedColumns();

    for (AttributeColumn column : columns)
    {
      if (column.getAttributeName().equals(columnName))
      {
        return column;
      }
    }

    return null;
  }
}
