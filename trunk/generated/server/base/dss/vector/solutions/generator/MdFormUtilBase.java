package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = -416580903)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MdFormUtil.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MdFormUtilBase extends com.runwaysdk.business.Util implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.MdFormUtil";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -416580903;
  
  public MdFormUtilBase()
  {
    super();
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.generator.MdFormUtil.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MdFormUtil get(String id)
  {
    return (MdFormUtil) com.runwaysdk.business.Util.get(id);
  }
  
  public static void addToGroup(java.lang.String groupId, java.lang.String fieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebForm apply(com.runwaysdk.system.metadata.MdWebForm mdForm)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebForm clone(com.runwaysdk.system.metadata.MdWebForm mdForm, com.runwaysdk.system.metadata.MdClass mdClass, com.runwaysdk.system.metadata.MdWebField[] mdFields, com.runwaysdk.system.metadata.MdAttributeConcrete[] mdAttrs, java.lang.String oldMdForm)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void confirmDeleteCompositeField(java.lang.String mdCompositeFieldId, java.lang.String mdFieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void confirmDeleteForm(java.lang.String mdFormId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void confirmDeleteMdField(java.lang.String mdFormId, java.lang.String mdFieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void createCondition(java.lang.String mdFieldId, com.runwaysdk.system.metadata.FieldCondition condition)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebPrimitive createFieldForComposite(com.runwaysdk.system.metadata.MdWebPrimitive mdField, java.lang.String mdCompositeFieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebGeo createGeoField(com.runwaysdk.system.metadata.MdWebGeo mdField, java.lang.String mdFormId, dss.vector.solutions.geo.GeoField geoField, java.lang.String[] extraUniversals)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdField createMdField(com.runwaysdk.system.metadata.MdField mdField, java.lang.String mdFormId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdAttributeConcrete[] definesAttributes(com.runwaysdk.system.metadata.MdRelationship mdRelationship)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void delete(com.runwaysdk.system.metadata.MdWebForm mdForm)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void deleteAll(com.runwaysdk.business.Business criteria, java.lang.String type)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void deleteCompositeField(com.runwaysdk.system.metadata.MdWebPrimitive mdField)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void deleteCondition(java.lang.String mdFieldId, java.lang.String conditionId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void deleteField(com.runwaysdk.system.metadata.MdWebForm mdForm, com.runwaysdk.system.metadata.MdWebField mdField)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void deleteGroup(java.lang.String groupId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream excelExport(java.lang.String type)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream excelImport(java.io.InputStream stream, java.lang.String type)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void exportDataset(java.lang.String mdFormId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream exportDefinition(java.lang.String mdFormId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebField[] getAllFields(com.runwaysdk.system.metadata.MdWebForm form)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebForm[] getAllForms()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebForm[] getAllFormsForDisease()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.form.MdFieldTypeQuery getAvailableCompositeFields()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.form.MdFieldTypeQuery getAvailableFields()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebPrimitive[] getCompositeFields(java.lang.String compositeFieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.FieldCondition[] getConditions(java.lang.String mdFieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebField[] getFields(com.runwaysdk.system.metadata.MdWebForm form)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebField[] getFieldsById(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getFieldsForComposite(java.lang.String compositeFieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdField[] getFieldsForConditions(java.lang.String mdFieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebForm getForm(java.lang.String type)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getFormByKey(java.lang.String formKey)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getFormTree(java.lang.String mdFormId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebField[] getGroupFields(java.lang.String groupId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdBusiness getMdBusinessByType(java.lang.String bizType)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdRelationship getMdRelationship(com.runwaysdk.system.metadata.MdWebField field)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.ontology.TermViewQuery getTermsForMultiTermField(com.runwaysdk.system.metadata.MdWebMultipleTerm mdWebMultipleTerm, java.lang.String parentId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void importDefinition(java.io.InputStream definition)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void isAvailable(java.lang.String type, java.lang.String oid)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.business.Business persistObject(com.runwaysdk.business.Business busObj, java.lang.String multipleTermJSON, java.lang.String singleTermGridJSON)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void removeFromGroup(java.lang.String groupId, java.lang.String fieldId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void reorderFields(java.lang.String[] ids)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.query.GeneratedComponentQuery searchObject(com.runwaysdk.business.Business criteria, java.lang.String type, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.query.ValueQuery searchObjectVQ(com.runwaysdk.business.Business criteria, java.lang.String type, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebPrimitive updateFieldForComposite(com.runwaysdk.system.metadata.MdWebPrimitive mdField)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdWebGeo updateGeoField(com.runwaysdk.system.metadata.MdWebGeo mdField, dss.vector.solutions.geo.GeoField geoField, java.lang.String[] extraUniversals)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.metadata.MdField updateMdField(com.runwaysdk.system.metadata.MdField mdField)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
