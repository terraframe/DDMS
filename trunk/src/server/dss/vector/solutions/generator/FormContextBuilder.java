package dss.vector.solutions.generator;

import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
import com.runwaysdk.dataaccess.io.excel.ImportApplyListener;
import com.runwaysdk.dataaccess.io.excel.MdFieldFilter;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.ontology.Term;

public class FormContextBuilder extends ContextBuilder implements ContextBuilderIF, Reloadable
{
  private MdFormDAOIF   mdForm;

  private MdFieldFilter filter;
  
  protected ExcelImportManager  manager;

  public FormContextBuilder(MdFormDAOIF mdForm, MdFieldFilter filter, ExcelImportManager manager)
  {
    this.mdForm = mdForm;
    this.filter = filter;
    this.manager = manager;
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
    
    FormImportContext context = new FormImportContext(sheet, sheetName, error, mdClass, manager);
    
    List<DynamicGeoColumnListener> geoListeners = MdFormUtil.getGeoListeners(mdForm, manager);
    List<MultiTermListener> multiTermListeners = MdFormUtil.getMultiTermListeners(mdForm);
    List<ImportApplyListener> applyListeners = MdFormUtil.getImportApplyListeners(mdForm);
    
    for (DynamicGeoColumnListener listener : geoListeners) 
    {
      context.addListener(listener);
    }

    for (MultiTermListener listener : multiTermListeners)
    {
      context.addListener(listener);
    }
    
    for (ImportApplyListener listener : applyListeners)
    {
      context.addListener(listener);
    }

    // Add the context listener which sets the disease for a entity
    context.addListener(new DiseaseAndValidationImportListener(mdForm));

    return context;
  }

  protected List<? extends MdAttributeDAOIF> getAttributes(ImportContext currentContext)
  {
    List<? extends MdFieldDAOIF> mdFields = this.mdForm.getOrderedMdFields();
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
