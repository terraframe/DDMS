package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeRefDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.MdWebGeoDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.FormExcelExporter;
import com.runwaysdk.dataaccess.io.excel.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdRelationshipDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;

import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.util.HierarchyBuilder;

public class MdFormUtil extends MdFormUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1220565977;

  public MdFormUtil()
  {
    super();
  }

  public static MdWebForm[] getAllForms()
  {
    MdWebFormQuery query = new MdWebFormQuery(new QueryFactory());

    OIterator<? extends MdWebForm> it = query.getIterator();

    try
    {
      List<? extends MdWebForm> forms = it.getAll();

      return forms.toArray(new MdWebForm[forms.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public static MdWebForm getForm(String type)
  {
    MdTypeDAOIF mdTypeDAO = MdFormDAO.getMdTypeDAO(type);

    return MdWebForm.get(mdTypeDAO.getId());
  }

  public static MdWebField[] getFields(MdWebForm form)
  {
    OIterator<? extends MdWebField> iterator = form.getAllMdFields();

    try
    {
      List<? extends MdWebField> fields = iterator.getAll();

      Collections.sort(fields, new FieldComparator());

      return fields.toArray(new MdWebField[fields.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  public static MdRelationship getMdRelationship(MdWebField field)
  {
    String relationshipType = MdFormUtil.getRelationshipType(field);

    MdRelationshipDAOIF mdRelationship = MdRelationshipDAO.getMdRelationshipDAO(relationshipType);

    return MdRelationship.get(mdRelationship.getId());
  }

  private static String getRelationshipType(MdWebField field)
  {
    MdWebForm mdForm = field.getDefiningMdForm();
    MdClass formMdClass = mdForm.getFormMdClass();
    MdTypeDAOIF mdClass = MdClassDAO.get(formMdClass.getId());

    String fieldName = field.getFieldName();
    String relationshipType = mdClass.definesType() + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    return relationshipType;
  }

  public static MdAttributeConcrete[] definesAttributes(MdRelationship mdRelationship)
  {
    List<MdAttributeConcrete> definedAttributes = new LinkedList<MdAttributeConcrete>();
    MdRelationshipDAOIF mdRelationshipDAO = MdRelationshipDAO.get(mdRelationship.getId());

    List<? extends MdAttributeConcreteDAOIF> mdAttributes = mdRelationshipDAO.getAllDefinedMdAttributes();

    for (MdAttributeConcreteDAOIF mdAttribute : mdAttributes)
    {
      if (!mdAttribute.isSystem() && ! ( mdAttribute instanceof MdAttributeRefDAOIF ))
      {
        definedAttributes.add(MdAttributeConcrete.get(mdAttribute.getId()));
      }
    }

    return definedAttributes.toArray(new MdAttributeConcrete[definedAttributes.size()]);
  }

  public static InputStream excelExport(String type)
  {
    MdFormDAOIF mdForm = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(type);

    ExcelExporter exporter = new FormExcelExporter(new FormImportFilter());

    List<DynamicGeoColumnListener> listeners = MdFormUtil.getGeoListeners(mdForm);

    for (DynamicGeoColumnListener listener : listeners)
    {
      exporter.addListener(listener);
    }

    /*
     * IMPORTANT: Before adding a template all of the listeners must be added
     */
    exporter.addTemplate(type);

    return new ByteArrayInputStream(exporter.write());
  }

  public static InputStream excelImport(InputStream stream, String type)
  {
    MdFormDAOIF mdForm = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(type);

    // Start caching Broswer Roots for this Thread.
    TermRootCache.start();
    EpiCache.start();

    try
    {
      ExcelImporter importer = new ExcelImporter(stream, new FormContextBuilder(mdForm, new FormImportFilter()));
      List<DynamicGeoColumnListener> listeners = MdFormUtil.getGeoListeners(mdForm);

      for (ImportContext context : importer.getContexts())
      {

        for (DynamicGeoColumnListener listener : listeners)
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

  private static List<DynamicGeoColumnListener> getGeoListeners(MdFormDAOIF mdForm)
  {
    List<DynamicGeoColumnListener> listeners = new LinkedList<DynamicGeoColumnListener>();

    MdClassDAOIF mdClass = mdForm.getFormMdClass();
    List<? extends MdFieldDAOIF> mdFields = mdForm.getSortedFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      // Setup all of the Geo Fields
      if (mdField instanceof MdWebGeoDAOIF)
      {
        GeoField geoField = GeoField.getGeoField(mdClass.definesType(), mdField.getFieldName());

        if (geoField != null)
        {
          HierarchyBuilder builder = new HierarchyBuilder();

          List<GeoHierarchy> universals = geoField.getUniversals();

          for (GeoHierarchy universal : universals)
          {
            builder.add(universal);
          }

          listeners.add(new DynamicGeoColumnListener(mdClass.definesType(), mdField.getFieldName(), builder));
        }
      }
    }

    return listeners;
  }
}
