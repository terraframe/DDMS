package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 569630973)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to FontStyle.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class FontStyleBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.FontStyle";
  public static java.lang.String PRIORITY = "priority";
  private static final long serialVersionUID = 569630973;
  
  public FontStyleBase()
  {
    super();
  }
  
  public Integer getPriority()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PRIORITY));
  }
  
  public void validatePriority()
  {
    this.validateAttribute(PRIORITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getPriorityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.FontStyle.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(PRIORITY);
  }
  
  public void setPriority(Integer value)
  {
    if(value == null)
    {
      setValue(PRIORITY, "");
    }
    else
    {
      setValue(PRIORITY, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static FontStyleQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    FontStyleQuery query = new FontStyleQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static FontStyle get(String id)
  {
    return (FontStyle) com.runwaysdk.business.Business.get(id);
  }
  
  public static FontStyle getByKey(String key)
  {
    return (FontStyle) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static FontStyle getEnumeration(String enumName)
  {
    return (FontStyle) com.runwaysdk.business.Business.getEnumeration(dss.vector.solutions.query.FontStyle.CLASS ,enumName);
  }
  
  public static FontStyle lock(java.lang.String id)
  {
    FontStyle _instance = FontStyle.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static FontStyle unlock(java.lang.String id)
  {
    FontStyle _instance = FontStyle.get(id);
    _instance.unlock();
    
    return _instance;
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
