package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = 991845618)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ReadableAttributeView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ReadableAttributeViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.util.ReadableAttributeView";
  public static java.lang.String ATTRIBUTEDESCRIPTION = "attributeDescription";
  public static java.lang.String ATTRIBUTENAME = "attributeName";
  public static java.lang.String ATTRIBUTEREQUIRED = "attributeRequired";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String READPERMISSION = "readPermission";
  private static final long serialVersionUID = 991845618;
  
  public ReadableAttributeViewBase()
  {
    super();
  }
  
  public String getAttributeDescription()
  {
    return getValue(ATTRIBUTEDESCRIPTION);
  }
  
  public void validateAttributeDescription()
  {
    this.validateAttribute(ATTRIBUTEDESCRIPTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAttributeDescriptionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.ReadableAttributeView.CLASS);
    return mdClassIF.definesAttribute(ATTRIBUTEDESCRIPTION);
  }
  
  public void setAttributeDescription(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEDESCRIPTION, "");
    }
    else
    {
      setValue(ATTRIBUTEDESCRIPTION, value);
    }
  }
  
  public String getAttributeName()
  {
    return getValue(ATTRIBUTENAME);
  }
  
  public void validateAttributeName()
  {
    this.validateAttribute(ATTRIBUTENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAttributeNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.ReadableAttributeView.CLASS);
    return mdClassIF.definesAttribute(ATTRIBUTENAME);
  }
  
  public void setAttributeName(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTENAME, "");
    }
    else
    {
      setValue(ATTRIBUTENAME, value);
    }
  }
  
  public Boolean getAttributeRequired()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ATTRIBUTEREQUIRED));
  }
  
  public void validateAttributeRequired()
  {
    this.validateAttribute(ATTRIBUTEREQUIRED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAttributeRequiredMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.ReadableAttributeView.CLASS);
    return mdClassIF.definesAttribute(ATTRIBUTEREQUIRED);
  }
  
  public void setAttributeRequired(Boolean value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEREQUIRED, "");
    }
    else
    {
      setValue(ATTRIBUTEREQUIRED, java.lang.Boolean.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.ReadableAttributeView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.ReadableAttributeView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Boolean getReadPermission()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(READPERMISSION));
  }
  
  public void validateReadPermission()
  {
    this.validateAttribute(READPERMISSION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getReadPermissionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.ReadableAttributeView.CLASS);
    return mdClassIF.definesAttribute(READPERMISSION);
  }
  
  public void setReadPermission(Boolean value)
  {
    if(value == null)
    {
      setValue(READPERMISSION, "");
    }
    else
    {
      setValue(READPERMISSION, java.lang.Boolean.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ReadableAttributeView get(String id)
  {
    return (ReadableAttributeView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.util.ReadableAttributeView[] getActorAttributes(java.lang.String universal, java.lang.String actorName)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.util.ReadableAttributeView[] getReadableAttributes(java.lang.String qualifiedClass)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void setActorAttributes(java.lang.String universal, java.lang.String actorName, dss.vector.solutions.util.ReadableAttributeView[] attributeViews)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.ReadableAttributeView.java";
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
