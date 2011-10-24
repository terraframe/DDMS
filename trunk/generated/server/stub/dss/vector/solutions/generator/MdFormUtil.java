package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.Business;
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
import com.runwaysdk.system.metadata.MdWebAttributeQuery;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebFieldQuery;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebFormQuery;
import com.runwaysdk.system.metadata.MdWebGroup;
import com.runwaysdk.system.metadata.MdWebReference;
import com.runwaysdk.system.metadata.WebGroupFieldQuery;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.form.ConfirmDeleteMdFieldException;
import dss.vector.solutions.form.ConfirmDeleteMdFormException;
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
  @Authenticate
  public static MdFieldTypeQuery getAvailableFields()
  {
    QueryFactory f = new QueryFactory();
    MdFieldTypeQuery q = new MdFieldTypeQuery(f);
    return q;
  }
  
  /**
   * Returns all fields that are candidates for conditions that this 
   * field will reference. For example, the given field is not allowed 
   * in the list to avoid circular references.
   */
  public static MdField[] getFieldsForConditions(String mdFieldId)
  {
    MdWebField field = MdWebField.get(mdFieldId);
    
    QueryFactory f = new QueryFactory();
    MdWebAttributeQuery q = new MdWebAttributeQuery(f);
    
    // exclude the given MdField and references
    q.WHERE(q.id().NE(field.getId()));
    q.AND(q.getType().NE(MdWebReference.CLASS)); // FIXME add this as a possible condition target?
    q.AND(q.getDefiningMdForm().EQ(field.getDefiningMdForm()));
    
    q.ORDER_BY_ASC(q.getFieldOrder());
    
    OIterator<? extends MdField> i =  q.getIterator();
    
    try
    {
      List<? extends MdField> fields = i.getAll();
      return fields.toArray(new MdField[fields.size()]);
    }
    finally
    {
      i.close();
    }
  }
  
  /**
   * Adds the MdField to the MdWebGroup and adds the
   * field as the last child of the group.
   * 
   * @param groupId
   * @param fieldId
   */
  @Transaction
  public static void addToGroup(String groupId, String fieldId)
  {
    MdWebGroup group = MdWebGroup.get(groupId);
    MdWebField field = MdWebField.get(fieldId);
    
    // ignore this transaction if the field already exists in the group.
    QueryFactory f = new QueryFactory();
    MdWebFieldQuery q = new MdWebFieldQuery(f);
    WebGroupFieldQuery relQ = new WebGroupFieldQuery(f);
    
    relQ.WHERE(relQ.parentId().EQ(groupId));
    q.WHERE(q.groupFields(relQ));
    q.AND(q.getId().EQ(fieldId));
    
    if(q.getCount() > 0)
    {
      return;
    }
    
    /*
     * Set the order to one higher than the group if it is the first
     * field or add it to the highest order within the group's fields.
     */
    Integer order = getHighestOrder(group);
    field.appLock();
    field.setFieldOrder(order);
    field.apply();
    
    field.addGroupFields(group).apply();
    
    
  }
  
  /**
   * Returns the next highest Order number relative to the object with the given
   * id, which can be an MdForm or MdWebField.
   * 
   * @param id
   * @return
   */
  public static Integer getHighestOrder(Business obj)
  {
    QueryFactory f = new QueryFactory();
    MdWebFieldQuery q = new MdWebFieldQuery(f);
    
    if(obj instanceof MdWebForm)
    {
      q.WHERE(q.getDefiningMdForm().EQ(obj.getId()));
    }
    else if(obj instanceof MdWebGroup)
    {
      WebGroupFieldQuery relQ = new WebGroupFieldQuery(f);
      relQ.WHERE(relQ.parentId().EQ(obj.getId()));
      q.WHERE(q.groupFields(relQ));
    }
    else
    {
      // we should never land here.
      throw new ProgrammingErrorException("The object ["+obj+"] is not of type ["+MdWebForm.CLASS+"] or ["+MdWebGroup.CLASS+"] to retrieve the field order.");
    }
    
    q.ORDER_BY_DESC(q.getFieldOrder("fieldOrder"));
    q.restrictRows(1, 1);
    
    OIterator<? extends MdWebField> iter = q.getIterator();
    
    try
    {
      if(iter.hasNext())
      {
        Integer last = iter.next().getFieldOrder();
        return ++last;
      }
      else
      {
        return 0;
      }
    }
    finally
    {
      iter.close();
    }
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
  @Authenticate
  public static com.runwaysdk.system.metadata.MdField createMdField(com.runwaysdk.system.metadata.MdField mdField, java.lang.String mdFormId)
  {
    DDMSFieldBuilders.create(mdField, mdFormId);

    return mdField;
  }

  @Transaction
  @Authenticate
  public static MdField updateMdField(MdField mdField)
  {
    DDMSFieldBuilders.update(mdField);

    return mdField;
  }

  public static MdWebForm[] getAllForms()
  {
    MdWebFormQuery query = new MdWebFormQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getDisplayLabel().localize());
       
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
  
  private static void addFieldsToTree(JSONObject parent, MdWebField[] fields) throws JSONException
  {
    JSONArray fieldsArr = new JSONArray();
    parent.put("fields", fieldsArr);
    
    for(MdWebField field : fields)
    {
      JSONObject fieldJSON = new JSONObject();
      fieldsArr.put(fieldJSON);

      fieldJSON.put("label", field.toString());
      fieldJSON.put("id", field.getId());
      
      
      if(field instanceof MdWebGroup)
      {
        fieldJSON.put("nodeType", "groupNode");
        
        MdWebField[] gFields = getGroupFields(field.getId());
        addFieldsToTree(fieldJSON, gFields);
      }
      else
      {
        fieldJSON.put("nodeType", "fieldNode");
      }
    }    
  }
  
  /**
   * Returns a JSON string representing the tree structure of the form
   * and its fields.
   * 
   * @param mdFormId
   * @return
   */
  public static String getFormTree(String mdFormId)
  {
    JSONObject formJSON = new JSONObject();
    MdWebForm form = MdWebForm.get(mdFormId);
    try
    {
      formJSON.put("label", form.getDisplayLabel().getValue());
      formJSON.put("id", form.getId());
      formJSON.put("nodeType", "formNode");
      
      MdWebField[] fields = getFields(form);
      addFieldsToTree(formJSON, fields);
      
      return formJSON.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException("Could not get the JSON tree for an MdForm.", e);
    }
  }
  
  /**
   * Returns all fields in order for the MdWebGroup.
   * 
   * @param groupId
   * @return
   */
  public static MdWebField[] getGroupFields(String groupId)
  {
    QueryFactory f = new QueryFactory();
    MdWebFieldQuery q = new MdWebFieldQuery(f);
    WebGroupFieldQuery relQ = new WebGroupFieldQuery(f);
    
    relQ.WHERE(relQ.parentId().EQ(groupId));
    q.WHERE(q.groupFields(relQ));
    
    q.ORDER_BY_ASC(q.getFieldOrder());
    
    OIterator<? extends MdWebField> iterator = q.getIterator();

    try
    {
      List<? extends MdWebField> fields = iterator.getAll();
      return fields.toArray(new MdWebField[fields.size()]);
    }
    finally
    {
      iterator.close();
    }
  }
  
  /**
   * Returns all fields in order for the MdWebForm.
   * 
   * @param form
   * @return
   */
  public static MdWebField[] getFields(MdWebForm form)
  {
    QueryFactory f = new QueryFactory();
    MdWebFieldQuery q = new MdWebFieldQuery(f);
    MdWebFieldQuery q1 = new MdWebFieldQuery(f);
    WebGroupFieldQuery relQ = new WebGroupFieldQuery(f);
    
    // exclude fields that are directly beneath a group
    relQ.WHERE(relQ.childId().EQ(q1.getId()));
    q.AND(q.SUBSELECT_NOT_IN_groupFields(relQ));
    

    q.WHERE(q.getDefiningMdForm().EQ(form));
    q.ORDER_BY_ASC(q.getFieldOrder());
    
    
    OIterator<? extends MdWebField> iterator = q.getIterator();

    try
    {
      List<? extends MdWebField> fields = iterator.getAll();
      return fields.toArray(new MdWebField[fields.size()]);
    }
    finally
    {
      iterator.close();
    }
  }
  
  public static MdWebField[] getFieldsById(String id)
  {
    MdWebForm form = MdWebForm.get(id);
    return getFields(form);
  }
  
  @Transaction
  public static void reorderFields(String[] ids)
  {
    for (int i = 0; i < ids.length; i++)
    {
      String id = ids[i];
      MdWebField field = MdWebField.get(id);
      field.appLock();
      field.setFieldOrder(i);
      field.apply();
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
      String typeName = GeoHierarchy.getSystemName(mdForm.getFormName(), "Business", true);
      String label = mdForm.getDisplayLabel().getValue();
      if (label.length() == 0)
      {
        label = mdForm.getFormName();
        mdForm.getDisplayLabel().setValue(label);
      }
      
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
  
  public static void confirmDeleteForm(String mdFormId)
  {
    MdWebForm mdForm = MdWebForm.get(mdFormId);
    ConfirmDeleteMdFormException ex = new ConfirmDeleteMdFormException();
    ex.setMdFormName(mdForm.getFormName());
    throw ex;
  }
  
  public static void confirmDeleteMdField(String mdFormId, String mdFieldId)
  {
    MdWebForm mdForm = MdWebForm.get(mdFormId);
    MdWebField[] fields = getFields(mdForm);
    MdWebField mdField = null;
    for (MdWebField field : fields)
    {
      if (field.getId().equals(mdFieldId))
      {
        mdField = field;
        break;
      }
    } 
    ConfirmDeleteMdFieldException ex = new ConfirmDeleteMdFieldException();
    ex.setMdFormName(mdForm.getFormName());
    ex.setMdFieldName(mdField.getFieldName());
    throw ex;
  }
  
  @Transaction
  @Authenticate
  public static void deleteField(MdWebForm mdForm, MdWebField mdField)
  {
    try
    {
      if (mdField instanceof MdWebAttribute)
      {
        MdWebAttribute attr = (MdWebAttribute) mdField;
        MdAttribute definingAttr = attr.getDefiningMdAttribute();
        attr.delete();
        definingAttr.delete();
      }
      else
      {
        mdField.delete();
      }
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
        context.addListener(new DiseaseAndValidationImportListener(mdForm));
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
