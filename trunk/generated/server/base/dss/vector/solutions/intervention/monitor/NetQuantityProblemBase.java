package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -16084)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to NetQuantityProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class NetQuantityProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.NetQuantityProblem";
  public static java.lang.String NETCOUNT = "netCount";
  public static java.lang.String QUANTITY = "quantity";
  private static final long serialVersionUID = -16084;
  
  public NetQuantityProblemBase()
  {
    super();
  }
  
  public NetQuantityProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getNetCount()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETCOUNT));
  }
  
  public void validateNetCount()
  {
    this.validateAttribute(NETCOUNT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNetCountMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.NetQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(NETCOUNT);
  }
  
  public void setNetCount(Integer value)
  {
    if(value == null)
    {
      setValue(NETCOUNT, "");
    }
    else
    {
      setValue(NETCOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantity()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void validateQuantity()
  {
    this.validateAttribute(QUANTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.NetQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(QUANTITY);
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.intervention.monitor.NetQuantityProblem", locale);
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
    message = replace(message, "{netCount}", this.getNetCount());
    message = replace(message, "{quantity}", this.getQuantity());
    return message;
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
