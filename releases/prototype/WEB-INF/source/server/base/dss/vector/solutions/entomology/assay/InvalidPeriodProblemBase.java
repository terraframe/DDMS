package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidPeriodProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidPeriodProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidPeriodProblem";
  public static java.lang.String PERIOD = "period";
  private static final long serialVersionUID = 1239670282672L;
  
  public InvalidPeriodProblemBase()
  {
    super();
  }
  
  public InvalidPeriodProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void validatePeriod()
  {
    this.validateAttribute(PERIOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeriodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidPeriodProblem.CLASS);
    return mdClassIF.definesAttribute(PERIOD);
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.entomology.assay.InvalidPeriodProblem", locale);
      
      message = replace(message, "{period}", this.getPeriod());
      
      return message;
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
