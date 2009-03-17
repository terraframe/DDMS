package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateEarthException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class DuplicateEarthExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateEarthException";
  public static java.lang.String EARTHNAME = "earthName";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1237311453784L;
  
  public DuplicateEarthExceptionBase()
  {
    super();
  }
  
  public DuplicateEarthExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateEarthExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateEarthExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getEarthName()
  {
    return getValue(EARTHNAME);
  }
  
  public void validateEarthName()
  {
    this.validateAttribute(EARTHNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEarthNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateEarthException.CLASS);
    return mdClassIF.definesAttribute(EARTHNAME);
  }
  
  public void setEarthName(String value)
  {
    if(value == null)
    {
      setValue(EARTHNAME, "");
    }
    else
    {
      setValue(EARTHNAME, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateEarthException.CLASS);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.DuplicateEarthException", locale);
      
      message = replace(message, "{earthName}", this.getEarthName());
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
      return "New: Dupliate Earth Exception";
    }
    else
    {
      return super.toString();
    }
  }
}
