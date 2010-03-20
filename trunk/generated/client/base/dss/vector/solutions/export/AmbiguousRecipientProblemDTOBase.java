package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -555894522)
public abstract class AmbiguousRecipientProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.AmbiguousRecipientProblem";
  private static final long serialVersionUID = -555894522;
  
  public AmbiguousRecipientProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public AmbiguousRecipientProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DOB = "dob";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String LASTNAME = "lastName";
  public java.util.Date getDob()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DOB));
  }
  
  public void setDob(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DOB, "");
    }
    else
    {
      setValue(DOB, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDobWritable()
  {
    return isWritable(DOB);
  }
  
  public boolean isDobReadable()
  {
    return isReadable(DOB);
  }
  
  public boolean isDobModified()
  {
    return isModified(DOB);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDobMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DOB).getAttributeMdDTO();
  }
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void setFirstName(String value)
  {
    if(value == null)
    {
      setValue(FIRSTNAME, "");
    }
    else
    {
      setValue(FIRSTNAME, value);
    }
  }
  
  public boolean isFirstNameWritable()
  {
    return isWritable(FIRSTNAME);
  }
  
  public boolean isFirstNameReadable()
  {
    return isReadable(FIRSTNAME);
  }
  
  public boolean isFirstNameModified()
  {
    return isModified(FIRSTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFirstNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTNAME).getAttributeMdDTO();
  }
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void setLastName(String value)
  {
    if(value == null)
    {
      setValue(LASTNAME, "");
    }
    else
    {
      setValue(LASTNAME, value);
    }
  }
  
  public boolean isLastNameWritable()
  {
    return isWritable(LASTNAME);
  }
  
  public boolean isLastNameReadable()
  {
    return isReadable(LASTNAME);
  }
  
  public boolean isLastNameModified()
  {
    return isModified(LASTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLastNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LASTNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    if (this.getLocale() != null)
    {
      return this.localize(this.getLocale());
    }
    else
    {
      return localizedMessage;
    }
  }
  private java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.export.AmbiguousRecipientProblem", locale);
      
      message = message.replace("{dob}", this.getDob().toString());
      message = message.replace("{firstName}", this.getFirstName().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{lastName}", this.getLastName().toString());
      
      return message;
    }
    catch (java.io.IOException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (com.runwaysdk.util.LocalizeException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
  }
  
}
