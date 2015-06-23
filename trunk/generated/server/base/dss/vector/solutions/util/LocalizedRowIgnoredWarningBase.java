package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = -778615885)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LocalizedRowIgnoredWarning.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class LocalizedRowIgnoredWarningBase extends com.runwaysdk.business.Warning implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.util.LocalizedRowIgnoredWarning";
  public static java.lang.String ID = "id";
  public static java.lang.String ROW = "row";
  public static java.lang.String SHEET = "sheet";
  private static final long serialVersionUID = -778615885;
  
  public LocalizedRowIgnoredWarningBase()
  {
    super();
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.LocalizedRowIgnoredWarning.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Integer getRow()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROW));
  }
  
  public void validateRow()
  {
    this.validateAttribute(ROW);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getRowMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.LocalizedRowIgnoredWarning.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(ROW);
  }
  
  public void setRow(Integer value)
  {
    if(value == null)
    {
      setValue(ROW, "");
    }
    else
    {
      setValue(ROW, java.lang.Integer.toString(value));
    }
  }
  
  public String getSheet()
  {
    return getValue(SHEET);
  }
  
  public void validateSheet()
  {
    this.validateAttribute(SHEET);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSheetMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.LocalizedRowIgnoredWarning.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SHEET);
  }
  
  public void setSheet(String value)
  {
    if(value == null)
    {
      setValue(SHEET, "");
    }
    else
    {
      setValue(SHEET, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{row}", this.getRow());
    message = replace(message, "{sheet}", this.getSheet());
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
