package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -573703610)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AmbiguousRecipientProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AmbiguousRecipientProblemBase extends com.terraframe.mojo.business.Problem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.AmbiguousRecipientProblem";
  public static java.lang.String DOB = "dob";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String LASTNAME = "lastName";
  private static final long serialVersionUID = -573703610;
  
  public AmbiguousRecipientProblemBase()
  {
    super();
  }
  
  public AmbiguousRecipientProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public java.util.Date getDob()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DOB));
  }
  
  public void validateDob()
  {
    this.validateAttribute(DOB);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDobMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AmbiguousRecipientProblem.CLASS);
    return mdClassIF.definesAttribute(DOB);
  }
  
  public void setDob(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DOB, "");
    }
    else
    {
      setValue(DOB, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void validateFirstName()
  {
    this.validateAttribute(FIRSTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFirstNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AmbiguousRecipientProblem.CLASS);
    return mdClassIF.definesAttribute(FIRSTNAME);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AmbiguousRecipientProblem.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void validateLastName()
  {
    this.validateAttribute(LASTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AmbiguousRecipientProblem.CLASS);
    return mdClassIF.definesAttribute(LASTNAME);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.export.AmbiguousRecipientProblem", locale);
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
    message = replace(message, "{dob}", this.getDob());
    message = replace(message, "{firstName}", this.getFirstName());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{lastName}", this.getLastName());
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
