package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1333852662)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SystemAlertDisplayName.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SystemAlertDisplayNameBase extends com.runwaysdk.business.LocalStruct implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.SystemAlertDisplayName";
  public static java.lang.String DEFAULTLOCALE = "defaultLocale";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String SITEMASTER = "siteMaster";
  private static final long serialVersionUID = -1333852662;
  
  public SystemAlertDisplayNameBase()
  {
    super();
  }
  
  public SystemAlertDisplayNameBase(com.runwaysdk.business.MutableWithStructs component, String structName)
  {
    super(component, structName);
  }
  
  public static SystemAlertDisplayName get(String id)
  {
    return (SystemAlertDisplayName) com.runwaysdk.business.Struct.get(id);
  }
  
  public static SystemAlertDisplayName getByKey(String key)
  {
    return (SystemAlertDisplayName) com.runwaysdk.business.Struct.get(CLASS, key);
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlertDisplayName.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlertDisplayName.CLASS);
    return mdClassIF.definesAttribute(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlertDisplayName.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SystemAlertDisplayNameQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SystemAlertDisplayNameQuery query = new SystemAlertDisplayNameQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
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
