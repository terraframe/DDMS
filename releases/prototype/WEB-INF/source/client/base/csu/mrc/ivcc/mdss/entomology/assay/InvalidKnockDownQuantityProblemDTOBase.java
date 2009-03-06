package csu.mrc.ivcc.mdss.entomology.assay;

public abstract class InvalidKnockDownQuantityProblemDTOBase extends com.terraframe.mojo.business.ProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.InvalidKnockDownQuantityProblem";
  public InvalidKnockDownQuantityProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidKnockDownQuantityProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVALID = "intervalId";
  public static java.lang.String QUANTITYKNOCKDOWN = "quantityKnockDown";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  public String getIntervalId()
  {
    return getValue(INTERVALID);
  }
  
  public void setIntervalId(String value)
  {
    if(value == null)
    {
      setValue(INTERVALID, "");
    }
    else
    {
      setValue(INTERVALID, value);
    }
  }
  
  public boolean isIntervalIdWritable()
  {
    return isWritable(INTERVALID);
  }
  
  public boolean isIntervalIdReadable()
  {
    return isReadable(INTERVALID);
  }
  
  public boolean isIntervalIdModified()
  {
    return isModified(INTERVALID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getIntervalIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("intervalId").getAttributeMdDTO();
  }
  
  public Integer getQuantityKnockDown()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYKNOCKDOWN));
  }
  
  public void setQuantityKnockDown(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYKNOCKDOWN, "");
    }
    else
    {
      setValue(QUANTITYKNOCKDOWN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityKnockDownWritable()
  {
    return isWritable(QUANTITYKNOCKDOWN);
  }
  
  public boolean isQuantityKnockDownReadable()
  {
    return isReadable(QUANTITYKNOCKDOWN);
  }
  
  public boolean isQuantityKnockDownModified()
  {
    return isModified(QUANTITYKNOCKDOWN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuantityKnockDownMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("quantityKnockDown").getAttributeMdDTO();
  }
  
  public Integer getQuantityTested()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
  }
  
  public void setQuantityTested(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYTESTED, "");
    }
    else
    {
      setValue(QUANTITYTESTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityTestedWritable()
  {
    return isWritable(QUANTITYTESTED);
  }
  
  public boolean isQuantityTestedReadable()
  {
    return isReadable(QUANTITYTESTED);
  }
  
  public boolean isQuantityTestedModified()
  {
    return isModified(QUANTITYTESTED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuantityTestedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("quantityTested").getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("csu.mrc.ivcc.mdss.entomology.assay.InvalidKnockDownQuantityProblem", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{intervalId}", this.getIntervalId().toString());
      message = message.replace("{quantityKnockDown}", this.getQuantityKnockDown().toString());
      message = message.replace("{quantityTested}", this.getQuantityTested().toString());
      
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
