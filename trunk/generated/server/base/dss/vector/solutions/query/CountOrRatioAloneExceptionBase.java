package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 604894913)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CountOrRatioAloneException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class CountOrRatioAloneExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.CountOrRatioAloneException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 604894913;
  
  public CountOrRatioAloneExceptionBase()
  {
    super();
  }
  
  public CountOrRatioAloneExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public CountOrRatioAloneExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public CountOrRatioAloneExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.CountOrRatioAloneException.CLASS);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.query.CountOrRatioAloneException", locale);
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
    return message;
  }
  
}
