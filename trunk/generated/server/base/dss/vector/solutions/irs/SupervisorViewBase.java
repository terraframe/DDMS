package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 576052887)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SupervisorView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class SupervisorViewBase extends dss.vector.solutions.PersonView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SupervisorView";
  public static java.lang.String SUPERVISORID = "supervisorId";
  private static final long serialVersionUID = 576052887;
  
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSupervisorIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.SupervisorView.CLASS);
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
    return (SupervisorView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.irs.SupervisorView[] getSupervisors()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SupervisorView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
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
