package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -1988678991)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideNotFoundException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InsecticideNotFoundExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InsecticideNotFoundException";
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String ID = "id";
  public static java.lang.String UNITS = "units";
  private static final long serialVersionUID = -1988678991;
  
  public InsecticideNotFoundExceptionBase()
  {
    super();
  }
  
  public InsecticideNotFoundExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InsecticideNotFoundExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InsecticideNotFoundExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public dss.vector.solutions.ontology.Term getActiveIngredient()
  {
    if (getValue(ACTIVEINGREDIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(ACTIVEINGREDIENT));
    }
  }
  
  public String getActiveIngredientId()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void validateActiveIngredient()
  {
    this.validateAttribute(ACTIVEINGREDIENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getActiveIngredientMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InsecticideNotFoundException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(dss.vector.solutions.ontology.Term value)
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
  
  public Double getAmount()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(AMOUNT));
  }
  
  public void validateAmount()
  {
    this.validateAttribute(AMOUNT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getAmountMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InsecticideNotFoundException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(AMOUNT);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InsecticideNotFoundException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.ontology.Term getUnits()
  {
    if (getValue(UNITS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(UNITS));
    }
  }
  
  public String getUnitsId()
  {
    return getValue(UNITS);
  }
  
  public void validateUnits()
  {
    this.validateAttribute(UNITS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getUnitsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InsecticideNotFoundException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(UNITS);
  }
  
  public void setUnits(dss.vector.solutions.ontology.Term value)
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{activeIngredient}", this.getActiveIngredient());
    message = replace(message, "{amount}", this.getAmount());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{units}", this.getUnits());
    return message;
  }
  
}