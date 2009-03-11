package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidGenerationProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidGenerationProblemBase extends com.terraframe.mojo.business.Problem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.InvalidGenerationProblem";
  public static java.lang.String ASSAYID = "assayId";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1236803164320L;
  
  public InvalidGenerationProblemBase()
  {
    super();
  }
  
  public InvalidGenerationProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getAssayId()
  {
    return getValue(ASSAYID);
  }
  
  public void validateAssayId()
  {
    this.validateAttribute(ASSAYID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAssayIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.InvalidGenerationProblem.CLASS);
    return mdClassIF.definesAttribute(ASSAYID);
  }
  
  public void setAssayId(String value)
  {
    if(value == null)
    {
      setValue(ASSAYID, "");
    }
    else
    {
      setValue(ASSAYID, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.InvalidGenerationProblem.CLASS);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("csu.mrc.ivcc.mdss.entomology.assay.InvalidGenerationProblem", locale);
      
      message = replace(message, "{assayId}", this.getAssayId());
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
      return "New: Invalid Generation Value";
    }
    else
    {
      return super.toString();
    }
  }
}
