package dss.vector.solutions.generator;

import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.UnexpectedTypeException;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilder;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.io.excel.MdFieldFilter;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class FormContextBuilder extends ContextBuilder implements ContextBuilderIF, Reloadable
{
  private MdFormDAOIF   mdForm;

  private MdFieldFilter filter;

  public FormContextBuilder(MdFormDAOIF mdForm, MdFieldFilter filter)
  {
    this.mdForm = mdForm;
    this.filter = filter;
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

    return new FormImportContext(sheet, sheetName, error, mdClass);
  }

  protected List<? extends MdAttributeDAOIF> getAttributes(ImportContext currentContext)
  {
    List<? extends MdFieldDAOIF> mdFields = this.mdForm.getSortedFields();
    List<MdAttributeDAOIF> mdAttributes = new LinkedList<MdAttributeDAOIF>();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (this.filter.accept(mdField))
      {
        MdWebAttributeDAOIF mdWebPrimitive = (MdWebAttributeDAOIF) mdField;

        mdAttributes.add(mdWebPrimitive.getDefiningMdAttribute());
      }
    }

    return mdAttributes;
  }

  protected void buildAttributeColumn(ImportContext context, MdAttributeDAOIF mdAttribute)
  {
    if (mdAttribute instanceof MdAttributeReferenceDAOIF)
    {
      MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
      MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

      if (mdBusiness.definesType().equals(Term.CLASS))
      {
        context.addExpectedColumn(new TermColumn(mdAttributeReference));
      }
    }
    else
    {
      super.buildAttributeColumn(context, mdAttribute);
    }
  }
}
