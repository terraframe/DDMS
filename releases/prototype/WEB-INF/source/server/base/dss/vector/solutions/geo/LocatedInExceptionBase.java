package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LocatedInException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LocatedInExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.LocatedInException";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTDISPLAYLABEL = "parentDisplayLabel";
  private static final long serialVersionUID = 1237314873980L;
  
  public LocatedInExceptionBase()
  {
    super();
  }
  
  public LocatedInExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public LocatedInExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public LocatedInExceptionBase(java.lang.Throwable cause)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.LocatedInException.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.LocatedInException.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.LocatedInException.CLASS);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.LocatedInException", locale);
      
      message = replace(message, "{entityName}", this.getEntityName());
      message = replace(message, "{id}", this.getId());
      message = replace(message, "{parentDisplayLabel}", this.getParentDisplayLabel());
      
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
      return "New: Located In Exception";
    }
    else
    {
      return super.toString();
    }
  }
}
