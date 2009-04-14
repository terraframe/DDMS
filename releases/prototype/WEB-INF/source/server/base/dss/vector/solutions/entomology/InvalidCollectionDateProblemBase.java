package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidCollectionDateProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidCollectionDateProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InvalidCollectionDateProblem";
  public static java.lang.String COLLECTIONDATE = "collectionDate";
  public static java.lang.String CURRENTDATE = "currentDate";
  private static final long serialVersionUID = 1239670221585L;
  
  public InvalidCollectionDateProblemBase()
  {
    super();
  }
  
  public InvalidCollectionDateProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public java.util.Date getCollectionDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COLLECTIONDATE));
  }
  
  public void validateCollectionDate()
  {
    this.validateAttribute(COLLECTIONDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InvalidCollectionDateProblem.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONDATE);
  }
  
  public void setCollectionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COLLECTIONDATE, "");
    }
    else
    {
      setValue(COLLECTIONDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public java.util.Date getCurrentDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CURRENTDATE));
  }
  
  public void validateCurrentDate()
  {
    this.validateAttribute(CURRENTDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCurrentDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InvalidCollectionDateProblem.CLASS);
    return mdClassIF.definesAttribute(CURRENTDATE);
  }
  
  public void setCurrentDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(CURRENTDATE, "");
    }
    else
    {
      setValue(CURRENTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.entomology.InvalidCollectionDateProblem", locale);
      
      message = replace(message, "{collectionDate}", this.getCollectionDate());
      message = replace(message, "{currentDate}", this.getCurrentDate());
      
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
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
