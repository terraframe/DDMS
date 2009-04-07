package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidAgeRangeProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidAgeRangeProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidAgeRangeProblem";
  public static java.lang.String ENDPOINT = "endPoint";
  public static java.lang.String STARTPOINT = "startPoint";
  private static final long serialVersionUID = 1239075012755L;
  
  public InvalidAgeRangeProblemBase()
  {
    super();
  }
  
  public InvalidAgeRangeProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getEndPoint()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ENDPOINT));
  }
  
  public void validateEndPoint()
  {
    this.validateAttribute(ENDPOINT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEndPointMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidAgeRangeProblem.CLASS);
    return mdClassIF.definesAttribute(ENDPOINT);
  }
  
  public void setEndPoint(Integer value)
  {
    if(value == null)
    {
      setValue(ENDPOINT, "");
    }
    else
    {
      setValue(ENDPOINT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getStartPoint()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STARTPOINT));
  }
  
  public void validateStartPoint()
  {
    this.validateAttribute(STARTPOINT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStartPointMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidAgeRangeProblem.CLASS);
    return mdClassIF.definesAttribute(STARTPOINT);
  }
  
  public void setStartPoint(Integer value)
  {
    if(value == null)
    {
      setValue(STARTPOINT, "");
    }
    else
    {
      setValue(STARTPOINT, java.lang.Integer.toString(value));
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.entomology.assay.InvalidAgeRangeProblem", locale);
      
      message = replace(message, "{endPoint}", this.getEndPoint());
      message = replace(message, "{startPoint}", this.getStartPoint());
      
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
      return "New: Invalid Age Range Exception";
    }
    else
    {
      return super.toString();
    }
  }
}
