package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = 2108685406)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ConfirmDeleteHierarchyException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ConfirmDeleteHierarchyExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.ConfirmDeleteHierarchyException";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTDISPLAYLABEL = "parentDisplayLabel";
  private static final long serialVersionUID = 2108685406;
  
  public ConfirmDeleteHierarchyExceptionBase()
  {
    super();
  }
  
  public ConfirmDeleteHierarchyExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ConfirmDeleteHierarchyExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ConfirmDeleteHierarchyExceptionBase(java.lang.Throwable cause)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.ConfirmDeleteHierarchyException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getParentDisplayLabel()
  {
    return getValue(PARENTDISPLAYLABEL);
  }
  
  public void validateParentDisplayLabel()
  {
    this.validateAttribute(PARENTDISPLAYLABEL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getParentDisplayLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.ConfirmDeleteHierarchyException.CLASS);
    return mdClassIF.definesAttribute(PARENTDISPLAYLABEL);
  }
  
  public void setParentDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(PARENTDISPLAYLABEL, "");
    }
    else
    {
      setValue(PARENTDISPLAYLABEL, value);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.ConfirmDeleteHierarchyException", locale);
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
    message = replace(message, "{parentDisplayLabel}", this.getParentDisplayLabel());
    return message;
  }
  
}
