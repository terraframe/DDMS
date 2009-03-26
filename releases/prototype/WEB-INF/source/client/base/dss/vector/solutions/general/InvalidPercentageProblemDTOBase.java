package dss.vector.solutions.general;

public abstract class InvalidPercentageProblemDTOBase extends com.terraframe.mojo.business.ProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.InvalidPercentageProblem";
  public InvalidPercentageProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidPercentageProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String INVALIDPERCENT = "invalidPercent";
  public Integer getInvalidPercent()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INVALIDPERCENT));
  }
  
  public void setInvalidPercent(Integer value)
  {
    if(value == null)
    {
      setValue(INVALIDPERCENT, "");
    }
    else
    {
      setValue(INVALIDPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInvalidPercentWritable()
  {
    return isWritable(INVALIDPERCENT);
  }
  
  public boolean isInvalidPercentReadable()
  {
    return isReadable(INVALIDPERCENT);
  }
  
  public boolean isInvalidPercentModified()
  {
    return isModified(INVALIDPERCENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInvalidPercentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("invalidPercent").getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.InvalidPercentageProblem", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{invalidPercent}", this.getInvalidPercent().toString());
      
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
