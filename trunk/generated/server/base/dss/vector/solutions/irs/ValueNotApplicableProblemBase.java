package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 2061022645)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ValueNotApplicableProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ValueNotApplicableProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ValueNotApplicableProblem";
  public static java.lang.String HOUSEHOLDID = "householdId";
  public static java.lang.String STRUCTUREID = "structureId";
  private static final long serialVersionUID = 2061022645;
  
  public ValueNotApplicableProblemBase()
  {
    super();
  }
  
  public ValueNotApplicableProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLDID);
  }
  
  public void validateHouseholdId()
  {
    this.validateAttribute(HOUSEHOLDID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHouseholdIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ValueNotApplicableProblem.CLASS);
    return mdClassIF.definesAttribute(HOUSEHOLDID);
  }
  
  public void setHouseholdId(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDID, "");
    }
    else
    {
      setValue(HOUSEHOLDID, value);
    }
  }
  
  public String getStructureId()
  {
    return getValue(STRUCTUREID);
  }
  
  public void validateStructureId()
  {
    this.validateAttribute(STRUCTUREID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStructureIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ValueNotApplicableProblem.CLASS);
    return mdClassIF.definesAttribute(STRUCTUREID);
  }
  
  public void setStructureId(String value)
  {
    if(value == null)
    {
      setValue(STRUCTUREID, "");
    }
    else
    {
      setValue(STRUCTUREID, value);
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.irs.ValueNotApplicableProblem", locale);
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
    message = replace(message, "{householdId}", this.getHouseholdId());
    message = replace(message, "{structureId}", this.getStructureId());
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
