package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeRefDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.MdWebGeoDAOIF;
import com.runwaysdk.dataaccess.MdWebMultipleTermDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.FormExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdRelationshipDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdField;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.form.DDMSFieldBuilders;
import dss.vector.solutions.form.MdFieldTypeQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.util.HierarchyBuilder;

public class MdFormUtil extends MdFormUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  static class FieldComparator implements Reloadable, Comparator<MdWebField>
  {
    @Override
    public int compare(MdWebField field1, MdWebField field2)
    {
      Integer order1 = field1.getFieldOrder();
      Integer order2 = field2.getFieldOrder();

      return order1.compareTo(order2);
    }
  }

  private static final long  serialVersionUID = 1220565977;

  public static final String DISEASE          = "disease";

  public MdFormUtil()
  {
    super();
  }

  /**
   * Returns all available MdField types that can be created in MDSS.
   * 
   * @return
   */
  public static MdFieldTypeQuery getAvailableFields()
  {
    QueryFactory f = new QueryFactory();
    MdFieldTypeQuery q = new MdFieldTypeQuery(f);
    return q;
  }

  /**
   * Creates an MdField and the associated MdAttribute in DDMS. The mapping is
   * one-to-one.
   * 
   * @param mdField
   * @param mdFormId
   * @return
   */
  @Transaction
  public static com.runwaysdk.system.metadata.MdField createMdField(com.runwaysdk.system.metadata.MdField mdField, java.lang.String mdFormId)
  {
    DDMSFieldBuilders.create(mdField, mdFormId);

    return mdField;
  }

  @Transaction
  public static MdField updateMdField(MdField mdField)
  {
    DDMSFieldBuilders.update(mdField);

    return mdField;
  }

  public static MdWebForm[] getAllForms()
  {
    MdWebFormQuery query = new MdWebFormQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getFormName());
    
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

  @Transaction
  @Authenticate
  public static MdWebForm apply(MdWebForm mdForm)
  {
    boolean first = mdForm.isNew() && !mdForm.isAppliedToDB();

    if (first)
    {
      String typeName = GeoHierarchy.getSystemName(mdForm.getFormName());
      String label = mdForm.getDisplayLabel().getValue();
      String description = mdForm.getDescription().getValue();

      MdBusiness mdBusiness = new MdBusiness();
      mdBusiness.setPackageName(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE);
      mdBusiness.setTypeName(typeName);
      mdBusiness.getDisplayLabel().setValue(label);
      mdBusiness.getDescription().setValue(description);
      mdBusiness.setIsAbstract(false);
      mdBusiness.setExtendable(true);
      mdBusiness.setPublish(true);
      mdBusiness.apply();

      // Create the disease attribute
      MdAttributeReference disease = new MdAttributeReference();
      disease.setDefiningMdClass(mdBusiness);
      disease.setAttributeName(MdFormUtil.DISEASE);
      disease.setMdBusiness(MdBusiness.getMdBusiness(Disease.CLASS));
      disease.apply();

      mdForm.setPackageName(MDSSInfo.GENERATED_FORM_PACKAGE);
      mdForm.setTypeName(typeName);
      mdForm.setFormMdClass(mdBusiness);
    }

    mdForm.apply();

    if (first)
    {
      new FormSystemURLBuilder(mdForm).generate();
    }

    return mdForm;
  }

  @Transaction
  @Authenticate
  public static void delete(MdWebForm mdForm)
  {
    new FormSystemURLBuilder(mdForm).delete();

    MdClass mdClass = mdForm.getFormMdClass();

    mdForm.delete();

    mdClass.delete();
  }

  @Transaction
  @Authenticate
  public static void deleteField(MdWebForm mdForm, MdWebField mdField)
  {
    try
    {
      MdWebAttribute attr = (MdWebAttribute) mdField;
      MdAttribute definingAttr = attr.getDefiningMdAttribute();
      mdForm.removeMdFields(attr);
      attr.delete();
      definingAttr.delete();
    }
    catch (Throwable t)
    {
      throw new ProgrammingErrorException(t);
    }
  }

  public static InputStream excelExport(String type)
  {
    MdFormDAOIF mdForm = (MdFormDAOIF) MdFormDAO.getMdTypeDAO(type);

    ExcelExporter exporter = new FormExcelExporter(new FormImportFilter());

    List<DynamicGeoColumnListener> geoListeners = MdFormUtil.getGeoListeners(mdForm);

    for (DynamicGeoColumnListener listener : geoListeners)
    {
      exporter.addListener(listener);
    }

    List<MultiTermListener> multiTermListeners = MdFormUtil.getMultitermListeners(mdForm);

    for (MultiTermListener listener : multiTermListeners)
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
      List<DynamicGeoColumnListener> geoListeners = MdFormUtil.getGeoListeners(mdForm);
      List<MultiTermListener> multiTermListeners = MdFormUtil.getMultitermListeners(mdForm);

      for (ImportContext context : importer.getContexts())
      {
        for (DynamicGeoColumnListener listener : geoListeners)
        {
          context.addListener(listener);
        }

        for (MultiTermListener listener : multiTermListeners)
        {
          context.addListener(listener);
        }

        // Add the context listener which sets the disease for a entity
        context.addListener(new DiseaseImportListener());
      }

      return new ByteArrayInputStream(importer.read());
    }
    finally
    {
      TermRootCache.stop();
      EpiCache.stop();
    }
  }

  private static List<MultiTermListener> getMultitermListeners(MdFormDAOIF mdForm)
  {
    List<MultiTermListener> listeners = new LinkedList<MultiTermListener>();

    MdClassDAOIF mdClass = mdForm.getFormMdClass();
    List<? extends MdFieldDAOIF> mdFields = mdForm.getSortedFields();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (mdField instanceof MdWebMultipleTermDAOIF)
      {
        listeners.add(new MultiTermListener(mdClass, mdField));
      }
    }

    return listeners;
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
