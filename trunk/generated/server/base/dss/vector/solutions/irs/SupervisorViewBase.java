package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 1220251765)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SupervisorView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SupervisorViewBase extends dss.vector.solutions.PersonView implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SupervisorView";
  public static java.lang.String SUPERVISORID = "supervisorId";
  private static final long serialVersionUID = 1220251765;
  
  public SupervisorViewBase()
  {
    super();
  }
  
  public String getSupervisorId()
  {
    return getValue(SUPERVISORID);
  }
  
  public void validateSupervisorId()
  {
    this.validateAttribute(SUPERVISORID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSupervisorIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.SupervisorView.CLASS);
    return mdClassIF.definesAttribute(SUPERVISORID);
  }
  
  public void setSupervisorId(String value)
  {
    if(value == null)
    {
      setValue(SUPERVISORID, "");
    }
    else
    {
      setValue(SUPERVISORID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SupervisorView get(String id)
  {
    return (SupervisorView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.irs.SupervisorView[] getSupervisors()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SupervisorView.java";
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
