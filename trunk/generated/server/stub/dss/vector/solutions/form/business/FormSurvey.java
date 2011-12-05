package dss.vector.solutions.form.business;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.FormExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.generator.BedNetValidationImportListener;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DiseaseAndValidationImportListener;
import dss.vector.solutions.generator.FormBedNetContextBuilder;
import dss.vector.solutions.generator.FormContextBuilder;
import dss.vector.solutions.generator.FormHouseholdContextBuilder;
import dss.vector.solutions.generator.FormImportFilter;
import dss.vector.solutions.generator.FormPersonContextBuilder;
import dss.vector.solutions.generator.FormSurveyColumnFactory;
import dss.vector.solutions.generator.FormSurveyImportFilter;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.generator.MultiTermListener;
import dss.vector.solutions.ontology.TermRootCache;

public class FormSurvey extends FormSurveyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 514839149;

  public static final String FORM_TYPE        = "dss.vector.solutions.form.FormSurvey";

  public FormSurvey()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getOid() != null && this.getOid().length() > 0)
    {
      return this.getOid();
    }

    return super.buildKey();
  }

  public static InputStream excelExport()
  {
    MdWebFormDAOIF formSurvey = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    MdWebFormDAOIF formHousehold = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE);
    MdWebFormDAOIF formBedNet = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
    MdWebFormDAOIF formPerson = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE);

    ExcelExporter exporter = new FormExcelExporter(new FormSurveyImportFilter(), new FormSurveyColumnFactory());

    FormSurvey.addForm(exporter, FormSurvey.FORM_TYPE, formSurvey);
    FormSurvey.addForm(exporter, FormHousehold.FORM_TYPE, formHousehold);
    FormSurvey.addForm(exporter, FormBedNet.FORM_TYPE, formBedNet);
    FormSurvey.addForm(exporter, FormPerson.FORM_TYPE, formPerson);

    return new ByteArrayInputStream(exporter.write());
  }

  private static void addForm(ExcelExporter exporter, String type, MdWebFormDAOIF mdForm)
  {
    exporter.addTemplate(type, FormSurvey.getExportListeners(mdForm));

    MdFormUtil.addGridSheets(mdForm, exporter);
  }

  private static List<ExcelExportListener> getExportListeners(MdWebFormDAOIF mdForm)
  {
    List<ExcelExportListener> listeners = new LinkedList<ExcelExportListener>();

    List<DynamicGeoColumnListener> geoListeners = MdFormUtil.getGeoListeners(mdForm);

    for (DynamicGeoColumnListener listener : geoListeners)
    {
      listeners.add(listener);
    }

    List<MultiTermListener> multiTermListeners = MdFormUtil.getMultiTermListeners(mdForm);

    for (MultiTermListener listener : multiTermListeners)
    {
      listeners.add(listener);
    }

    return listeners;
  }

  public static InputStream excelImport(InputStream stream)
  {
    MdWebFormDAOIF formSurvey = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    MdWebFormDAOIF formHousehold = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE);
    MdWebFormDAOIF formBedNet = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
    MdWebFormDAOIF formPerson = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE);

    ContextBuilderFacade builder = new ContextBuilderFacade();

    builder.add(FormSurvey.CLASS, new FormContextBuilder(formSurvey, new FormImportFilter()));
    builder.add(FormHousehold.CLASS, new FormHouseholdContextBuilder());
    builder.add(FormBedNet.CLASS, new FormBedNetContextBuilder());
    builder.add(FormPerson.CLASS, new FormPersonContextBuilder());

    MdFormUtil.addGridContexts(formSurvey, builder);
    MdFormUtil.addGridContexts(formHousehold, builder);
    MdFormUtil.addGridContexts(formBedNet, builder);
    MdFormUtil.addGridContexts(formPerson, builder);

    // Start caching Broswer Roots for this Thread.
    TermRootCache.start();
    EpiCache.start();

    try
    {
      ExcelImporter importer = new ExcelImporter(stream, builder);

      List<ImportContext> contexts = importer.getContexts();

      for (ImportContext context : contexts)
      {
        List<ImportListener> listeners = FormSurvey.getImportListeners(context.getMdClassType());

        for (ImportListener listener : listeners)
        {
          context.addListener(listener);
        }
      }

      return new ByteArrayInputStream(importer.read());
    }
    finally
    {
      TermRootCache.stop();
      EpiCache.stop();
    }
  }

  private static List<ImportListener> getImportListeners(String mdClassType)
  {
    List<ImportListener> listeners = new LinkedList<ImportListener>();

    MdWebFormDAOIF mdForm = FormSurvey.getMdForm(mdClassType);

    if (mdForm != null)
    {

      List<DynamicGeoColumnListener> geoListeners = MdFormUtil.getGeoListeners(mdForm);
      List<MultiTermListener> multiTermListeners = MdFormUtil.getMultiTermListeners(mdForm);

      for (DynamicGeoColumnListener listener : geoListeners)
      {
        listeners.add(listener);
      }

      for (MultiTermListener listener : multiTermListeners)
      {
        listeners.add(listener);
      }

      // Add the context listener which sets the disease for a entity
      listeners.add(new DiseaseAndValidationImportListener(mdForm));

      if (mdClassType.equals(FormPerson.CLASS))
      {
        listeners.add(new BedNetValidationImportListener());
      }
    }

    return listeners;
  }

  private static MdWebFormDAOIF getMdForm(String mdClassType)
  {
    if (mdClassType.equals(FormSurvey.CLASS))
    {
      return (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    }
    else if (mdClassType.equals(FormHousehold.CLASS))
    {
      return (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE);
    }
    else if (mdClassType.equals(FormBedNet.CLASS))
    {
      return (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
    }
    else if (mdClassType.equals(FormPerson.CLASS))
    {
      return (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE);
    }

    return null;
  }

  public static FormSurvey getBySurveyId(String surveyId)
  {
    FormSurveyQuery query = new FormSurveyQuery(new QueryFactory());
    query.WHERE(query.getOid().EQ(surveyId));

    OIterator<? extends FormSurvey> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
    }
    finally
    {
      it.close();
    }

    return null;
  }
}
