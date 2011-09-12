package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = -832256959)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MdFieldType.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MdFieldTypeBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.form.MdFieldType";
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String MDFIELDID = "mdFieldId";
  private static final long serialVersionUID = -832256959;
  
  public MdFieldTypeBase()
  {
    super();
  }
  
  public String getDescription()
  {
    return getValue(DESCRIPTION);
  }
  
  public void validateDescription()
  {
    this.validateAttribute(DESCRIPTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDescriptionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.MdFieldType.CLASS);
    return mdClassIF.definesAttribute(DESCRIPTION);
  }
  
  public void setDescription(String value)
  {
    if(value == null)
    {
      setValue(DESCRIPTION, "");
    }
    else
    {
      setValue(DESCRIPTION, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.MdFieldType.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.MdFieldType.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getMdFieldId()
  {
    return getValue(MDFIELDID);
  }
  
  public void validateMdFieldId()
  {
    this.validateAttribute(MDFIELDID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMdFieldIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.MdFieldType.CLASS);
    return mdClassIF.definesAttribute(MDFIELDID);
  }
  
  public void setMdFieldId(String value)
  {
    if(value == null)
    {
      setValue(MDFIELDID, "");
    }
    else
    {
      setValue(MDFIELDID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MdFieldType get(String id)
  {
    return (MdFieldType) com.runwaysdk.business.View.get(id);
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
