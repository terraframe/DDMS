package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidFedQuantityProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidFedQuantityProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem";
  public static java.lang.String FED = "fed";
  private static final long serialVersionUID = 1239517515642L;
  
  public InvalidFedQuantityProblemBase()
  {
    super();
  }
  
  public InvalidFedQuantityProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getFed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FED));
  }
  
  public void validateFed()
  {
    this.validateAttribute(FED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(FED);
  }
  
  public void setFed(Integer value)
  {
    if(value == null)
    {
      setValue(FED, "");
    }
    else
    {
      setValue(FED, java.lang.Integer.toString(value));
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem", locale);
      
      message = replace(message, "{fed}", this.getFed());
      
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
