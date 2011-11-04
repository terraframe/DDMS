package dss.vector.solutions.form.business;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdFormDAOIF;
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
import dss.vector.solutions.generator.FormContextBuilder;
import dss.vector.solutions.generator.FormHouseholdContextBuilder;
import dss.vector.solutions.generator.FormImportFilter;
import dss.vector.solutions.generator.FormPersonContextBuilder;
import dss.vector.solutions.generator.FormSurveyColumnFactory;
import dss.vector.solutions.generator.FormSurveyImportFilter;
import dss.vector.solutions.generator.HouseholdReferenceContextBuilder;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.generator.MultiTermListener;
import dss.vector.solutions.generator.UnsupportedImportTypeException;
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
    if (this.getSurveyId() != null && this.getSurveyId().length() > 0)
    {
      return this.getSurveyId();
    }

    return super.buildKey();
  }

  public static InputStream excelExport()
  {
    MdFormDAOIF formSurvey = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    MdFormDAOIF formHousehold = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE);
    MdFormDAOIF formBedNet = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
    MdFormDAOIF formPerson = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE);

    ExcelExporter exporter = new FormExcelExporter(new FormSurveyImportFilter(), new FormSurveyColumnFactory());
    exporter.addTemplate(FormSurvey.FORM_TYPE, FormSurvey.getExportListeners(formSurvey));
    exporter.addTemplate(FormHousehold.FORM_TYPE, FormSurvey.getExportListeners(formHousehold));
    exporter.addTemplate(FormBedNet.FORM_TYPE, FormSurvey.getExportListeners(formBedNet));
    exporter.addTemplate(FormPerson.FORM_TYPE, FormSurvey.getExportListeners(formPerson));

    return new ByteArrayInputStream(exporter.write());
  }

  private static List<ExcelExportListener> getExportListeners(MdFormDAOIF mdForm)
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
    MdFormDAOIF formSurvey = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    MdFormDAOIF formBedNet = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);

    ContextBuilderFacade builder = new ContextBuilderFacade();
    builder.add(FormSurvey.CLASS, new FormContextBuilder(formSurvey, new FormImportFilter()));
    builder.add(FormHousehold.CLASS, new FormHouseholdContextBuilder());
    builder.add(FormBedNet.CLASS, new HouseholdReferenceContextBuilder(formBedNet));
    builder.add(FormPerson.CLASS, new FormPersonContextBuilder());

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
    MdFormDAOIF mdForm = FormSurvey.getMdForm(mdClassType);
    List<ImportListener> listeners = new LinkedList<ImportListener>();

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

    return listeners;
  }

  private static MdFormDAOIF getMdForm(String mdClassType)
  {
    if (mdClassType.equals(FormSurvey.CLASS))
    {
      return (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    }
    else if (mdClassType.equals(FormHousehold.CLASS))
    {
      return (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE);
    }
    else if (mdClassType.equals(FormBedNet.CLASS))
    {
      return (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
    }
    else if (mdClassType.equals(FormPerson.CLASS))
    {
      return (MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE);
    }

    UnsupportedImportTypeException e = new UnsupportedImportTypeException();
    e.setClassType(mdClassType);
    e.apply();

    throw e;
  }

  public static FormSurvey getBySurveyId(String surveyId)
  {
    FormSurveyQuery query = new FormSurveyQuery(new QueryFactory());
    query.WHERE(query.getSurveyId().EQ(surveyId));

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
