package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = 157205108)
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
  private static final long serialVersionUID = 157205108;
  
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.generator.MdFormUtil.CLASS);
    return mdClassIF.definesAttribute(ID);
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
  
  public static com.runwaysdk.system.metadata.MdWebForm[] getAllForms()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.generator.MdFormUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.form.MdFieldTypeQuery getAvailableFields()
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
  
  public static com.runwaysdk.system.metadata.MdRelationship getMdRelationship(com.runwaysdk.system.metadata.MdWebField field)
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
