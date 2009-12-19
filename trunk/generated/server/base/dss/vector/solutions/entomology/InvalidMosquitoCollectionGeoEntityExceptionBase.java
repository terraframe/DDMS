package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -1974371954)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidMosquitoCollectionGeoEntityException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidMosquitoCollectionGeoEntityExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InvalidMosquitoCollectionGeoEntityException";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -1974371954;
  
  public InvalidMosquitoCollectionGeoEntityExceptionBase()
  {
    super();
  }
  
  public InvalidMosquitoCollectionGeoEntityExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidMosquitoCollectionGeoEntityExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidMosquitoCollectionGeoEntityExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void validateGeoId()
  {
    this.validateAttribute(GEOID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InvalidMosquitoCollectionGeoEntityException.CLASS);
    return mdClassIF.definesAttribute(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InvalidMosquitoCollectionGeoEntityException.CLASS);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.entomology.InvalidMosquitoCollectionGeoEntityException", locale);
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
    message = replace(message, "{geoId}", this.getGeoId());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
