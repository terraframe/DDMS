package dss.vector.solutions.generator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.UnexpectedTypeException;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormPerson;

public class FormPersonContextBuilder extends HouseholdReferenceContextBuilder implements Reloadable
{
  private BedNetColumn bedNetColumn;

  public FormPersonContextBuilder()
  {
    super((MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE));

    this.bedNetColumn = null;
  }

  @Override
  public void configure(ImportContext currentContext, HSSFRow typeRow, HSSFRow nameRow, HSSFRow labelRow)
  {
    super.configure(currentContext, typeRow, nameRow, labelRow);

    if (this.bedNetColumn != null && this.getSurveyColumn() != null)
    {
      this.bedNetColumn.setSurveyIndex(this.getSurveyColumn().getIndex());
    }
  }

  @Override
  public ImportContext createContext(HSSFSheet sheet, String sheetName, HSSFWorkbook errorWorkbook, String type)
  {
    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(type);

    if (! ( mdClass instanceof MdViewDAO ) && ! ( mdClass instanceof MdBusinessDAO ))
    {
      throw new UnexpectedTypeException("Excel Importer does not support type [" + mdClass.definesType() + "]");
    }

    HSSFSheet error = errorWorkbook.createSheet(sheetName);

    return new FormPersonImportContext(sheet, sheetName, error, mdClass);
  }

  protected void buildAttributeColumn(ImportContext context, MdAttributeDAOIF mdAttribute)
  {
    if (mdAttribute instanceof MdAttributeReferenceDAOIF)
    {
      MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
      MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

      if (mdBusiness.definesType().equals(FormBedNet.CLASS))
      {
        this.bedNetColumn = new BedNetColumn(mdAttributeReference);

        context.addExpectedColumn(this.bedNetColumn);
      }
      else
      {
        super.buildAttributeColumn(context, mdAttribute);
      }
    }
    else
    {
      super.buildAttributeColumn(context, mdAttribute);
    }
  }

}
