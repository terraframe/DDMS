package dss.vector.solutions.general;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UndefinedLethalTimePropertyException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class UndefinedLethalTimePropertyExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.UndefinedLethalTimePropertyException";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDE = "insecticide";
  private static final long serialVersionUID = 1239517534680L;
  
  public UndefinedLethalTimePropertyExceptionBase()
  {
    super();
  }
  
  public UndefinedLethalTimePropertyExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UndefinedLethalTimePropertyExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UndefinedLethalTimePropertyExceptionBase(java.lang.Throwable cause)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.UndefinedLethalTimePropertyException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.general.Insecticide getInsecticide()
  {
    try
    {
      return dss.vector.solutions.general.Insecticide.get(getValue(INSECTICIDE));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateInsecticide()
  {
    this.validateAttribute(INSECTICIDE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInsecticideMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.UndefinedLethalTimePropertyException.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDE);
  }
  
  public void setInsecticide(dss.vector.solutions.general.Insecticide value)
  {
    if(value == null)
    {
      setValue(INSECTICIDE, "");
    }
    else
    {
      setValue(INSECTICIDE, value.getId());
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.UndefinedLethalTimePropertyException", locale);
      
      message = replace(message, "{id}", this.getId());
      message = replace(message, "{insecticide}", this.getInsecticide());
      
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
  
}
