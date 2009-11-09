package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 1332975399)
public abstract class CurrentDateProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.CurrentDateProblem";
  private static final long serialVersionUID = 1332975399;
  
  public CurrentDateProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public CurrentDateProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CURRENTDATE = "currentDate";
  public static java.lang.String GIVENDATE = "givenDate";
  public java.util.Date getCurrentDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CURRENTDATE));
  }
  
  public void setCurrentDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(CURRENTDATE, "");
    }
    else
    {
      setValue(CURRENTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCurrentDateWritable()
  {
    return isWritable(CURRENTDATE);
  }
  
  public boolean isCurrentDateReadable()
  {
    return isReadable(CURRENTDATE);
  }
  
  public boolean isCurrentDateModified()
  {
    return isModified(CURRENTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getCurrentDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(CURRENTDATE).getAttributeMdDTO();
  }
  
  public java.util.Date getGivenDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(GIVENDATE));
  }
  
  public void setGivenDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(GIVENDATE, "");
    }
    else
    {
      setValue(GIVENDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isGivenDateWritable()
  {
    return isWritable(GIVENDATE);
  }
  
  public boolean isGivenDateReadable()
  {
    return isReadable(GIVENDATE);
  }
  
  public boolean isGivenDateModified()
  {
    return isModified(GIVENDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getGivenDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(GIVENDATE).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.CurrentDateProblem", locale);
      
      message = message.replace("{currentDate}", this.getCurrentDate().toString());
      message = message.replace("{givenDate}", this.getGivenDate().toString());
      
      return message;
    }
    catch (java.io.IOException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (com.terraframe.mojo.util.LocalizeException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
  }
  
}
