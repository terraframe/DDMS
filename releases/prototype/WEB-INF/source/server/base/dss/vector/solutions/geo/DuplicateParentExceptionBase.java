package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateParentException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class DuplicateParentExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateParentException";
  public static java.lang.String CHILDENTITYNAME = "childEntityName";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTENTITYNAME = "parentEntityName";
  private static final long serialVersionUID = 1239572455895L;
  
  public DuplicateParentExceptionBase()
  {
    super();
  }
  
  public DuplicateParentExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateParentExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateParentExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getChildEntityName()
  {
    return getValue(CHILDENTITYNAME);
  }
  
  public void validateChildEntityName()
  {
    this.validateAttribute(CHILDENTITYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getChildEntityNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateParentException.CLASS);
    return mdClassIF.definesAttribute(CHILDENTITYNAME);
  }
  
  public void setChildEntityName(String value)
  {
    if(value == null)
    {
      setValue(CHILDENTITYNAME, "");
    }
    else
    {
      setValue(CHILDENTITYNAME, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateParentException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getParentEntityName()
  {
    return getValue(PARENTENTITYNAME);
  }
  
  public void validateParentEntityName()
  {
    this.validateAttribute(PARENTENTITYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getParentEntityNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateParentException.CLASS);
    return mdClassIF.definesAttribute(PARENTENTITYNAME);
  }
  
  public void setParentEntityName(String value)
  {
    if(value == null)
    {
      setValue(PARENTENTITYNAME, "");
    }
    else
    {
      setValue(PARENTENTITYNAME, value);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.DuplicateParentException", locale);
      
      message = replace(message, "{childEntityName}", this.getChildEntityName());
      message = replace(message, "{id}", this.getId());
      message = replace(message, "{parentEntityName}", this.getParentEntityName());
      
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
