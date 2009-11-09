package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = -333249796)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ConfirmParentChangeException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ConfirmParentChangeExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.ConfirmParentChangeException";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -333249796;
  
  public ConfirmParentChangeExceptionBase()
  {
    super();
  }
  
  public ConfirmParentChangeExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ConfirmParentChangeExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ConfirmParentChangeExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getEntityName()
  {
    return getValue(ENTITYNAME);
  }
  
  public void validateEntityName()
  {
    this.validateAttribute(ENTITYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.ConfirmParentChangeException.CLASS);
    return mdClassIF.definesAttribute(ENTITYNAME);
  }
  
  public void setEntityName(String value)
  {
    if(value == null)
    {
      setValue(ENTITYNAME, "");
    }
    else
    {
      setValue(ENTITYNAME, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.ConfirmParentChangeException.CLASS);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.ConfirmParentChangeException", locale);
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
    message = replace(message, "{entityName}", this.getEntityName());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
