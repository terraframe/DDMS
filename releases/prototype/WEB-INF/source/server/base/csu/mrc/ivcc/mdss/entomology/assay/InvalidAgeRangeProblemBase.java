package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidAgeRangeProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidAgeRangeProblemBase extends com.terraframe.mojo.business.Problem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeRangeProblem";
  public static java.lang.String ENDPOINT = "endPoint";
  public static java.lang.String ID = "id";
  public static java.lang.String STARTPOINT = "startPoint";
  private static final long serialVersionUID = 1236982481895L;
  
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeRangeProblem.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeRangeProblem.CLASS);
    return mdClassIF.definesAttribute(ID);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeRangeProblem.CLASS);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeRangeProblem", locale);
      
      message = replace(message, "{endPoint}", this.getEndPoint());
      message = replace(message, "{id}", this.getId());
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
