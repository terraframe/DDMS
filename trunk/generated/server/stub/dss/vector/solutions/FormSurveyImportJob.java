package dss.vector.solutions;

import java.io.InputStream;
import java.util.List;

import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.generator.FormBedNetContextBuilder;
import dss.vector.solutions.generator.FormContextBuilder;
import dss.vector.solutions.generator.FormHouseholdContextBuilder;
import dss.vector.solutions.generator.FormImportFilter;
import dss.vector.solutions.generator.FormPersonContextBuilder;
import dss.vector.solutions.generator.MdFormUtil;

public class FormSurveyImportJob extends FormSurveyImportJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 842464214;
  
  public FormSurveyImportJob()
  {
    super();
  }
  
  public FormSurveyImportJob(ExcelImportManager manager, InputStream inputStream, String[] params, String fileName)
  {
    super();
    
    this.sharedState = new SharedState();
    this.sharedState.manager = manager;
    this.sharedState.inputStreamIn = inputStream;
    this.sharedState.params = params;
    this.sharedState.fileName = fileName;
  }
  
  @Override
  protected ContextBuilderIF constructContextBuilder()
  {
    MdWebFormDAOIF formSurvey = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    MdWebFormDAOIF formHousehold = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE);
    MdWebFormDAOIF formBedNet = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
    MdWebFormDAOIF formPerson = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE);

    ContextBuilderFacade builder = new ContextBuilderFacade(new DefaultContextBuilder(new String[] {}, this.sharedState.manager), this.sharedState.manager);

    builder.add(FormSurvey.CLASS, new FormContextBuilder(formSurvey, new FormImportFilter(), this.sharedState.manager));
    builder.add(FormHousehold.CLASS, new FormHouseholdContextBuilder(this.sharedState.manager));
    builder.add(FormBedNet.CLASS, new FormBedNetContextBuilder(this.sharedState.manager));
    builder.add(FormPerson.CLASS, new FormPersonContextBuilder(this.sharedState.manager));

    MdFormUtil.addGridContexts(formSurvey, builder);
    MdFormUtil.addGridContexts(formHousehold, builder);
    MdFormUtil.addGridContexts(formBedNet, builder);
    MdFormUtil.addGridContexts(formPerson, builder);
    
    return builder;
  }
  
  @Override
  protected void configureImporter(ExcelImporter importer)
  {
    List<ImportContext> contexts = importer.getContexts();

    for (ImportContext context : contexts)
    {
      List<ImportListener> listeners = FormSurvey.getImportListeners(context.getMdClassType());

      for (ImportListener listener : listeners)
      {
        context.addListener(listener);
      }
    }
  }
}
