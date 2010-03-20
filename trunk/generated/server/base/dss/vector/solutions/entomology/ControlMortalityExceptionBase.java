package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -267700634)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ControlMortalityException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ControlMortalityExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.ControlMortalityException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -267700634;
  
  public ControlMortalityExceptionBase()
  {
    super();
  }
  
  public ControlMortalityExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ControlMortalityExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ControlMortalityExceptionBase(java.lang.Throwable cause)
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ControlMortalityException.CLASS);
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
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.entomology.ControlMortalityException", locale);
      return this.localize(locale, message);
    }
    catch (java.io.IOException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (com.runwaysdk.util.LocalizeException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
  }
  
  protected java.lang.String localize(java.util.Locale locale, java.lang.String message)
  {
    message = super.localize(locale, message);
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
