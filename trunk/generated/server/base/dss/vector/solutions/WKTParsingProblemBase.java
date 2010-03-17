package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 1436126248)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to WKTParsingProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class WKTParsingProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.WKTParsingProblem";
  public static java.lang.String REASON = "reason";
  private static final long serialVersionUID = 1436126248;
  
  public WKTParsingProblemBase()
  {
    super();
  }
  
  public WKTParsingProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getReason()
  {
    return getValue(REASON);
  }
  
  public void validateReason()
  {
    this.validateAttribute(REASON);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReasonMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.WKTParsingProblem.CLASS);
    return mdClassIF.definesAttribute(REASON);
  }
  
  public void setReason(String value)
  {
    if(value == null)
    {
      setValue(REASON, "");
    }
    else
    {
      setValue(REASON, value);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.WKTParsingProblem", locale);
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
    message = replace(message, "{reason}", this.getReason());
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
