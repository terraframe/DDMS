package dss.vector.solutions.permission;

@com.runwaysdk.business.ClassSignature(hash = -766331383)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MDSSRoleView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MDSSRoleViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.permission.MDSSRoleView";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String ROLENAME = "roleName";
  private static final long serialVersionUID = -766331383;
  
  public MDSSRoleViewBase()
  {
    super();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.permission.MDSSRoleView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.permission.MDSSRoleView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
    }
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.permission.MDSSRoleView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getRoleName()
  {
    return getValue(ROLENAME);
  }
  
  public void validateRoleName()
  {
    this.validateAttribute(ROLENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getRoleNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.permission.MDSSRoleView.CLASS);
    return mdClassIF.definesAttribute(ROLENAME);
  }
  
  public void setRoleName(String value)
  {
    if(value == null)
    {
      setValue(ROLENAME, "");
    }
    else
    {
      setValue(ROLENAME, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MDSSRoleView get(String id)
  {
    return (MDSSRoleView) com.runwaysdk.business.View.get(id);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.permission.MDSSRoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    MDSSRoleView _instance = MDSSRoleView.get(id);
    _instance.deleteConcrete();
  }
  
  public static dss.vector.solutions.permission.MDSSRoleViewQuery getPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.permission.MDSSRoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.permission.PermissionView[] getPermissions()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.permission.MDSSRoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.permission.PermissionView[] getPermissions(java.lang.String id)
  {
    MDSSRoleView _instance = MDSSRoleView.get(id);
    return _instance.getPermissions();
  }
  
  public dss.vector.solutions.permission.UniversalPermissionView[] getUniversalPermissions()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.permission.MDSSRoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.permission.UniversalPermissionView[] getUniversalPermissions(java.lang.String id)
  {
    MDSSRoleView _instance = MDSSRoleView.get(id);
    return _instance.getUniversalPermissions();
  }
  
  public void setPermissions(dss.vector.solutions.permission.PermissionView[] permissions)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.permission.MDSSRoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void setPermissions(java.lang.String id, dss.vector.solutions.permission.PermissionView[] permissions)
  {
    MDSSRoleView _instance = MDSSRoleView.get(id);
    _instance.setPermissions(permissions);
  }
  
  public void setUniversalPermissions(dss.vector.solutions.permission.UniversalPermissionView[] permissions)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.permission.MDSSRoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void setUniversalPermissions(java.lang.String id, dss.vector.solutions.permission.UniversalPermissionView[] permissions)
  {
    MDSSRoleView _instance = MDSSRoleView.get(id);
    _instance.setUniversalPermissions(permissions);
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
