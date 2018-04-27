package dss.vector.solutions.mobile;

@com.runwaysdk.business.ClassSignature(hash = 2024661464)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MobileUtil.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MobileUtilBase extends com.runwaysdk.business.Util implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mobile.MobileUtil";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 2024661464;
  
  public MobileUtilBase()
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mobile.MobileUtil.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MobileUtil get(String id)
  {
    return (MobileUtil) com.runwaysdk.business.Util.get(id);
  }
  
  public static java.lang.String export(java.lang.String mobileType)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.mobile.MobileUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
