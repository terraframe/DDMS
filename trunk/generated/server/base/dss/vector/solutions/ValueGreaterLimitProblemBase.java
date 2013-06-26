package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -1464290071)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ValueGreaterLimitProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ValueGreaterLimitProblemBase extends com.runwaysdk.business.Problem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ValueGreaterLimitProblem";
  public static java.lang.String ID = "id";
  public static java.lang.String LIMITATTRIBUTELABEL = "limitAttributeLabel";
  public static java.lang.String VALUEATTRIBUTELABEL = "valueAttributeLabel";
  private static final long serialVersionUID = -1464290071;
  
  public ValueGreaterLimitProblemBase()
  {
    super();
  }
  
  public ValueGreaterLimitProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ValueGreaterLimitProblem.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getLimitAttributeLabel()
  {
    return getValue(LIMITATTRIBUTELABEL);
  }
  
  public void validateLimitAttributeLabel()
  {
    this.validateAttribute(LIMITATTRIBUTELABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLimitAttributeLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ValueGreaterLimitProblem.CLASS);
    return mdClassIF.definesAttribute(LIMITATTRIBUTELABEL);
  }
  
  public void setLimitAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(LIMITATTRIBUTELABEL, "");
    }
    else
    {
      setValue(LIMITATTRIBUTELABEL, value);
    }
  }
  
  public String getValueAttributeLabel()
  {
    return getValue(VALUEATTRIBUTELABEL);
  }
  
  public void validateValueAttributeLabel()
  {
    this.validateAttribute(VALUEATTRIBUTELABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getValueAttributeLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ValueGreaterLimitProblem.CLASS);
    return mdClassIF.definesAttribute(VALUEATTRIBUTELABEL);
  }
  
  public void setValueAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(VALUEATTRIBUTELABEL, "");
    }
    else
    {
      setValue(VALUEATTRIBUTELABEL, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{limitAttributeLabel}", this.getLimitAttributeLabel());
    message = replace(message, "{valueAttributeLabel}", this.getValueAttributeLabel());
    return message;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
