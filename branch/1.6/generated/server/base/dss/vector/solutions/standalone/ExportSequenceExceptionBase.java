package dss.vector.solutions.standalone;

@com.runwaysdk.business.ClassSignature(hash = -1113482484)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ExportSequenceException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ExportSequenceExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.standalone.ExportSequenceException";
  public static java.lang.String ENDSEQUENCE = "endSequence";
  public static java.lang.String ID = "id";
  public static java.lang.String STARTSEQUENCE = "startSequence";
  private static final long serialVersionUID = -1113482484;
  
  public ExportSequenceExceptionBase()
  {
    super();
  }
  
  public ExportSequenceExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ExportSequenceExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ExportSequenceExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public Long getEndSequence()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(ENDSEQUENCE));
  }
  
  public void validateEndSequence()
  {
    this.validateAttribute(ENDSEQUENCE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLongDAOIF getEndSequenceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.standalone.ExportSequenceException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(ENDSEQUENCE);
  }
  
  public void setEndSequence(Long value)
  {
    if(value == null)
    {
      setValue(ENDSEQUENCE, "");
    }
    else
    {
      setValue(ENDSEQUENCE, java.lang.Long.toString(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.standalone.ExportSequenceException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Long getStartSequence()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(STARTSEQUENCE));
  }
  
  public void validateStartSequence()
  {
    this.validateAttribute(STARTSEQUENCE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLongDAOIF getStartSequenceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.standalone.ExportSequenceException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(STARTSEQUENCE);
  }
  
  public void setStartSequence(Long value)
  {
    if(value == null)
    {
      setValue(STARTSEQUENCE, "");
    }
    else
    {
      setValue(STARTSEQUENCE, java.lang.Long.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{endSequence}", this.getEndSequence());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{startSequence}", this.getStartSequence());
    return message;
  }
  
}
