package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -1077348047)
public abstract class InsecticideNotFoundExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InsecticideNotFoundException";
  private static final long serialVersionUID = -1077348047;
  
  public InsecticideNotFoundExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected InsecticideNotFoundExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InsecticideNotFoundExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InsecticideNotFoundExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InsecticideNotFoundExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InsecticideNotFoundExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InsecticideNotFoundExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InsecticideNotFoundExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String ID = "id";
  public static java.lang.String UNITS = "units";
  public dss.vector.solutions.ontology.TermDTO getActiveIngredient()
  {
    if(getValue(ACTIVEINGREDIENT) == null || getValue(ACTIVEINGREDIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ACTIVEINGREDIENT));
    }
  }
  
  public String getActiveIngredientId()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ACTIVEINGREDIENT, "");
    }
    else
    {
      setValue(ACTIVEINGREDIENT, value.getId());
    }
  }
  
  public boolean isActiveIngredientWritable()
  {
    return isWritable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientReadable()
  {
    return isReadable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientModified()
  {
    return isModified(ACTIVEINGREDIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getActiveIngredientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
  public Double getAmount()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(AMOUNT));
  }
  
  public void setAmount(Double value)
  {
    if(value == null)
    {
      setValue(AMOUNT, "");
    }
    else
    {
      setValue(AMOUNT, java.lang.Double.toString(value));
    }
  }
  
  public boolean isAmountWritable()
  {
    return isWritable(AMOUNT);
  }
  
  public boolean isAmountReadable()
  {
    return isReadable(AMOUNT);
  }
  
  public boolean isAmountModified()
  {
    return isModified(AMOUNT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getAmountMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(AMOUNT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getUnits()
  {
    if(getValue(UNITS) == null || getValue(UNITS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(UNITS));
    }
  }
  
  public String getUnitsId()
  {
    return getValue(UNITS);
  }
  
  public void setUnits(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(UNITS, "");
    }
    else
    {
      setValue(UNITS, value.getId());
    }
  }
  
  public boolean isUnitsWritable()
  {
    return isWritable(UNITS);
  }
  
  public boolean isUnitsReadable()
  {
    return isReadable(UNITS);
  }
  
  public boolean isUnitsModified()
  {
    return isModified(UNITS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getUnitsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(UNITS).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{activeIngredient}", this.getActiveIngredient().toString());
    template = template.replace("{amount}", this.getAmount().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{units}", this.getUnits().toString());
    
    return template;
  }
  
}
