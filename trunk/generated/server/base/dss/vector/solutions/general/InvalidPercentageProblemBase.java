package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 222953306)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidPercentageProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidPercentageProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.InvalidPercentageProblem";
  public static java.lang.String INVALIDPERCENT = "invalidPercent";
  private static final long serialVersionUID = 222953306;
  
  public InvalidPercentageProblemBase()
  {
    super();
  }
  
  public InvalidPercentageProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getInvalidPercent()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INVALIDPERCENT));
  }
  
  public void validateInvalidPercent()
  {
    this.validateAttribute(INVALIDPERCENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInvalidPercentMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.InvalidPercentageProblem.CLASS);
    return mdClassIF.definesAttribute(INVALIDPERCENT);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.InvalidPercentageProblem", locale);
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
    message = replace(message, "{invalidPercent}", this.getInvalidPercent());
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
