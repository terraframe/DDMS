package dss.vector.solutions.standalone;

@com.terraframe.mojo.business.ClassSignature(hash = -1031790746)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ExportSequenceException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ExportSequenceExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.standalone.ExportSequenceException";
  public static java.lang.String ENDSEQUENCE = "endSequence";
  public static java.lang.String ID = "id";
  public static java.lang.String STARTSEQUENCE = "startSequence";
  private static final long serialVersionUID = -1031790746;
  
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
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(ENDSEQUENCE));
  }
  
  public void validateEndSequence()
  {
    this.validateAttribute(ENDSEQUENCE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEndSequenceMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.standalone.ExportSequenceException.CLASS);
    return mdClassIF.definesAttribute(ENDSEQUENCE);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.standalone.ExportSequenceException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Long getStartSequence()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(STARTSEQUENCE));
  }
  
  public void validateStartSequence()
  {
    this.validateAttribute(STARTSEQUENCE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStartSequenceMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.standalone.ExportSequenceException.CLASS);
    return mdClassIF.definesAttribute(STARTSEQUENCE);
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
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.standalone.ExportSequenceException", locale);
      return this.localize(locale, message);
    }
    catch (java.io.IOException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (com.terraframe.mojo.util.LocalizeException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
  }
  
  protected java.lang.String localize(java.util.Locale locale, java.lang.String message)
  {
    message = super.localize(locale, message);
    message = replace(message, "{endSequence}", this.getEndSequence());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{startSequence}", this.getStartSequence());
    return message;
  }
  
}
