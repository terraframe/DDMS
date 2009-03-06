package csu.mrc.ivcc.mdss;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PropertyValidationFailedException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class PropertyValidationFailedExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.PropertyValidationFailedException";
  public static java.lang.String ID = "id";
  public static java.lang.String VALIDVALUES = "validValues";
  private static final long serialVersionUID = 1236360377751L;
  
  public PropertyValidationFailedExceptionBase()
  {
    super();
  }
  
  public PropertyValidationFailedExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public PropertyValidationFailedExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public PropertyValidationFailedExceptionBase(java.lang.Throwable cause)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.PropertyValidationFailedException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getValidValues()
  {
    return getValue(VALIDVALUES);
  }
  
  public void validateValidValues()
  {
    this.validateAttribute(VALIDVALUES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getValidValuesMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.PropertyValidationFailedException.CLASS);
    return mdClassIF.definesAttribute(VALIDVALUES);
  }
  
  public void setValidValues(String value)
  {
    if(value == null)
    {
      setValue(VALIDVALUES, "");
    }
    else
    {
      setValue(VALIDVALUES, value);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("csu.mrc.ivcc.mdss.PropertyValidationFailedException", locale);
      
      message = replace(message, "{id}", this.getId());
      message = replace(message, "{validValues}", this.getValidValues());
      
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
      return "New: Property Validation Failed";
    }
    else
    {
      return super.toString();
    }
  }
}
