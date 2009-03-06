package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidAgeProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidAgeProblemBase extends com.terraframe.mojo.business.Problem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeProblem";
  public static java.lang.String AGE = "age";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1236360383197L;
  
  public InvalidAgeProblemBase()
  {
    super();
  }
  
  public InvalidAgeProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
  }
  
  public void validateAge()
  {
    this.validateAttribute(AGE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeProblem.CLASS);
    return mdClassIF.definesAttribute(AGE);
  }
  
  public void setAge(Integer value)
  {
    if(value == null)
    {
      setValue(AGE, "");
    }
    else
    {
      setValue(AGE, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeProblem.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("csu.mrc.ivcc.mdss.entomology.assay.InvalidAgeProblem", locale);
      
      message = replace(message, "{age}", this.getAge());
      message = replace(message, "{id}", this.getId());
      
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
      return "New: Invalid Age Exception";
    }
    else
    {
      return super.toString();
    }
  }
}
