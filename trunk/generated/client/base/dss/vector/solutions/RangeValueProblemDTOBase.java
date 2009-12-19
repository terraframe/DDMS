package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -3449094)
public abstract class RangeValueProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.RangeValueProblem";
  private static final long serialVersionUID = -3449094;
  
  public RangeValueProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public RangeValueProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String INVALIDVALUE = "invalidValue";
  public static java.lang.String LOWERLIMIT = "lowerLimit";
  public static java.lang.String UPPERLIMIT = "upperLimit";
  public Integer getInvalidValue()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INVALIDVALUE));
  }
  
  public void setInvalidValue(Integer value)
  {
    if(value == null)
    {
      setValue(INVALIDVALUE, "");
    }
    else
    {
      setValue(INVALIDVALUE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInvalidValueWritable()
  {
    return isWritable(INVALIDVALUE);
  }
  
  public boolean isInvalidValueReadable()
  {
    return isReadable(INVALIDVALUE);
  }
  
  public boolean isInvalidValueModified()
  {
    return isModified(INVALIDVALUE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInvalidValueMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INVALIDVALUE).getAttributeMdDTO();
  }
  
  public Integer getLowerLimit()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOWERLIMIT));
  }
  
  public void setLowerLimit(Integer value)
  {
    if(value == null)
    {
      setValue(LOWERLIMIT, "");
    }
    else
    {
      setValue(LOWERLIMIT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLowerLimitWritable()
  {
    return isWritable(LOWERLIMIT);
  }
  
  public boolean isLowerLimitReadable()
  {
    return isReadable(LOWERLIMIT);
  }
  
  public boolean isLowerLimitModified()
  {
    return isModified(LOWERLIMIT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getLowerLimitMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOWERLIMIT).getAttributeMdDTO();
  }
  
  public Integer getUpperLimit()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UPPERLIMIT));
  }
  
  public void setUpperLimit(Integer value)
  {
    if(value == null)
    {
      setValue(UPPERLIMIT, "");
    }
    else
    {
      setValue(UPPERLIMIT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUpperLimitWritable()
  {
    return isWritable(UPPERLIMIT);
  }
  
  public boolean isUpperLimitReadable()
  {
    return isReadable(UPPERLIMIT);
  }
  
  public boolean isUpperLimitModified()
  {
    return isModified(UPPERLIMIT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getUpperLimitMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UPPERLIMIT).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.RangeValueProblem", locale);
      
      message = message.replace("{invalidValue}", this.getInvalidValue().toString());
      message = message.replace("{lowerLimit}", this.getLowerLimit().toString());
      message = message.replace("{upperLimit}", this.getUpperLimit().toString());
      
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
