package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -1173786917)
public abstract class CurrentYearProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.CurrentYearProblem";
  private static final long serialVersionUID = -1173786917;
  
  public CurrentYearProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public CurrentYearProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String YEAROFDATE = "yearOfDate";
  public Integer getYearOfDate()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEAROFDATE));
  }
  
  public void setYearOfDate(Integer value)
  {
    if(value == null)
    {
      setValue(YEAROFDATE, "");
    }
    else
    {
      setValue(YEAROFDATE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearOfDateWritable()
  {
    return isWritable(YEAROFDATE);
  }
  
  public boolean isYearOfDateReadable()
  {
    return isReadable(YEAROFDATE);
  }
  
  public boolean isYearOfDateModified()
  {
    return isModified(YEAROFDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearOfDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEAROFDATE).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.CurrentYearProblem", locale);
      
      message = message.replace("{yearOfDate}", this.getYearOfDate().toString());
      
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
