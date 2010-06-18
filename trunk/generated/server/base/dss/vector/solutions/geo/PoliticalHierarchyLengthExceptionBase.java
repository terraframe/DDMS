package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = -1129739046)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PoliticalHierarchyLengthException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PoliticalHierarchyLengthExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.PoliticalHierarchyLengthException";
  public static java.lang.String HIERARCHYLENGTH = "hierarchyLength";
  public static java.lang.String ID = "id";
  public static java.lang.String SLOTS = "slots";
  private static final long serialVersionUID = -1129739046;
  
  public PoliticalHierarchyLengthExceptionBase()
  {
    super();
  }
  
  public PoliticalHierarchyLengthExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public PoliticalHierarchyLengthExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public PoliticalHierarchyLengthExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public Integer getHierarchyLength()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HIERARCHYLENGTH));
  }
  
  public void validateHierarchyLength()
  {
    this.validateAttribute(HIERARCHYLENGTH);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getHierarchyLengthMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.PoliticalHierarchyLengthException.CLASS);
    return mdClassIF.definesAttribute(HIERARCHYLENGTH);
  }
  
  public void setHierarchyLength(Integer value)
  {
    if(value == null)
    {
      setValue(HIERARCHYLENGTH, "");
    }
    else
    {
      setValue(HIERARCHYLENGTH, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.PoliticalHierarchyLengthException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Integer getSlots()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SLOTS));
  }
  
  public void validateSlots()
  {
    this.validateAttribute(SLOTS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSlotsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.PoliticalHierarchyLengthException.CLASS);
    return mdClassIF.definesAttribute(SLOTS);
  }
  
  public void setSlots(Integer value)
  {
    if(value == null)
    {
      setValue(SLOTS, "");
    }
    else
    {
      setValue(SLOTS, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{hierarchyLength}", this.getHierarchyLength());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{slots}", this.getSlots());
    return message;
  }
  
}
