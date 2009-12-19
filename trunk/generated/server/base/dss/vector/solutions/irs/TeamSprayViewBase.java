package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 750165847)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TeamSprayView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class TeamSprayViewBase extends dss.vector.solutions.irs.ActorSprayView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TeamSprayView";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  private static final long serialVersionUID = 750165847;
  
  public TeamSprayViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.irs.SprayTeam getSprayTeam()
  {
    if (getValue(SPRAYTEAM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayTeam.get(getValue(SPRAYTEAM));
    }
  }
  
  public void validateSprayTeam()
  {
    this.validateAttribute(SPRAYTEAM);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayTeamMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.TeamSprayView.CLASS);
    return mdClassIF.definesAttribute(SPRAYTEAM);
  }
  
  public void setSprayTeam(dss.vector.solutions.irs.SprayTeam value)
  {
    if(value == null)
    {
      setValue(SPRAYTEAM, "");
    }
    else
    {
      setValue(SPRAYTEAM, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TeamSprayView get(String id)
  {
    return (TeamSprayView) com.terraframe.mojo.business.View.get(id);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.TeamSprayView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    TeamSprayView _instance = TeamSprayView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.irs.OperatorSprayStatusView[] getStatus()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.TeamSprayView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.irs.OperatorSprayStatusView[] getStatus(java.lang.String id)
  {
    TeamSprayView _instance = TeamSprayView.get(id);
    return _instance.getStatus();
  }
  
  public static dss.vector.solutions.irs.TeamSprayView searchBySprayData(java.lang.String geoId, java.util.Date sprayDate, dss.vector.solutions.irs.SprayMethod sprayMethod, dss.vector.solutions.irs.InsecticideBrand brand, java.lang.String teamId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.TeamSprayView.java";
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
