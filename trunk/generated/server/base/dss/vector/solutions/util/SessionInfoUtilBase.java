package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = 1864028483)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SessionInfoUtil.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SessionInfoUtilBase extends com.runwaysdk.business.Util implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.util.SessionInfoUtil";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1864028483;
  
  public SessionInfoUtilBase()
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.util.SessionInfoUtil.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SessionInfoUtil get(String id)
  {
    return (SessionInfoUtil) com.runwaysdk.business.Util.get(id);
  }
  
  public static java.lang.String refreshSession()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.util.SessionInfoUtil.java";
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
