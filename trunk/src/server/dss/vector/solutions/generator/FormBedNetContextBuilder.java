package dss.vector.solutions.generator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.UnexpectedTypeException;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.form.business.FormBedNet;

public class FormBedNetContextBuilder extends HouseholdReferenceContextBuilder implements Reloadable
{
  public FormBedNetContextBuilder(ExcelImportManager manager)
  {
    super((MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE), manager);
  }

  @Override
  public ImportContext createContext(Sheet sheet, String sheetName, Workbook errorWorkbook, String type)
  {
    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(type);

    if (! ( mdClass instanceof MdViewDAO ) && ! ( mdClass instanceof MdBusinessDAO ))
    {
      throw new UnexpectedTypeException("Excel Importer does not support type [" + mdClass.definesType() + "]");
    }

    Sheet error = errorWorkbook.createSheet(sheetName);

    return new FormBedNetImportContext(sheet, sheetName, error, mdClass, this.manager);
  }
}
