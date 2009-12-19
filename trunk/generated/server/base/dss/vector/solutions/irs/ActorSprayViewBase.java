package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -2080337974)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ActorSprayView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ActorSprayViewBase extends dss.vector.solutions.irs.AbstractSprayView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ActorSprayView";
  public static java.lang.String RECEIVED = "received";
  public static java.lang.String REFILLS = "refills";
  public static java.lang.String RETURNED = "returned";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public static java.lang.String TEAMSPRAYWEEK = "teamSprayWeek";
  public static java.lang.String USED = "used";
  private static final long serialVersionUID = -2080337974;
  
  public ActorSprayViewBase()
  {
    super();
  }
  
  public Integer getReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVED));
  }
  
  public void validateReceived()
  {
    this.validateAttribute(RECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ActorSprayView.CLASS);
    return mdClassIF.definesAttribute(RECEIVED);
  }
  
  public void setReceived(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVED, "");
    }
    else
    {
      setValue(RECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getRefills()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFILLS));
  }
  
  public void validateRefills()
  {
    this.validateAttribute(REFILLS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRefillsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ActorSprayView.CLASS);
    return mdClassIF.definesAttribute(REFILLS);
  }
  
  public void setRefills(Integer value)
  {
    if(value == null)
    {
      setValue(REFILLS, "");
    }
    else
    {
      setValue(REFILLS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getReturned()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RETURNED));
  }
  
  public void validateReturned()
  {
    this.validateAttribute(RETURNED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReturnedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ActorSprayView.CLASS);
    return mdClassIF.definesAttribute(RETURNED);
  }
  
  public void setReturned(Integer value)
  {
    if(value == null)
    {
      setValue(RETURNED, "");
    }
    else
    {
      setValue(RETURNED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getTarget()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
  }
  
  public void validateTarget()
  {
    this.validateAttribute(TARGET);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTargetMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ActorSprayView.CLASS);
    return mdClassIF.definesAttribute(TARGET);
  }
  
  public void setTarget(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, java.lang.Integer.toString(value));
    }
  }
  
  public dss.vector.solutions.irs.SprayOperator getTeamLeader()
  {
    if (getValue(TEAMLEADER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayOperator.get(getValue(TEAMLEADER));
    }
  }
  
  public void validateTeamLeader()
  {
    this.validateAttribute(TEAMLEADER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTeamLeaderMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ActorSprayView.CLASS);
    return mdClassIF.definesAttribute(TEAMLEADER);
  }
  
  public void setTeamLeader(dss.vector.solutions.irs.SprayOperator value)
  {
    if(value == null)
    {
      setValue(TEAMLEADER, "");
    }
    else
    {
      setValue(TEAMLEADER, value.getId());
    }
  }
  
  public Integer getTeamSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMSPRAYWEEK));
  }
  
  public void validateTeamSprayWeek()
  {
    this.validateAttribute(TEAMSPRAYWEEK);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTeamSprayWeekMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ActorSprayView.CLASS);
    return mdClassIF.definesAttribute(TEAMSPRAYWEEK);
  }
  
  public void setTeamSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMSPRAYWEEK, "");
    }
    else
    {
      setValue(TEAMSPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USED));
  }
  
  public void validateUsed()
  {
    this.validateAttribute(USED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUsedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ActorSprayView.CLASS);
    return mdClassIF.definesAttribute(USED);
  }
  
  public void setUsed(Integer value)
  {
    if(value == null)
    {
      setValue(USED, "");
    }
    else
    {
      setValue(USED, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ActorSprayView get(String id)
  {
    return (ActorSprayView) com.terraframe.mojo.business.View.get(id);
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
