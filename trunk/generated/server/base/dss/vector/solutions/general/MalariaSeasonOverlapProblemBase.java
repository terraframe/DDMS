package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = -1411305111)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MalariaSeasonOverlapProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MalariaSeasonOverlapProblemBase extends com.terraframe.mojo.business.Problem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.MalariaSeasonOverlapProblem";
  public static java.lang.String ID = "id";
  public static java.lang.String OVERLAP = "overlap";
  private static final long serialVersionUID = -1411305111;
  
  public MalariaSeasonOverlapProblemBase()
  {
    super();
  }
  
  public MalariaSeasonOverlapProblemBase(java.lang.String developerMessage)
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.MalariaSeasonOverlapProblem.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getOverlap()
  {
    return getValue(OVERLAP);
  }
  
  public void validateOverlap()
  {
    this.validateAttribute(OVERLAP);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOverlapMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.MalariaSeasonOverlapProblem.CLASS);
    return mdClassIF.definesAttribute(OVERLAP);
  }
  
  public void setOverlap(String value)
  {
    if(value == null)
    {
      setValue(OVERLAP, "");
    }
    else
    {
      setValue(OVERLAP, value);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.MalariaSeasonOverlapProblem", locale);
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
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{overlap}", this.getOverlap());
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
