package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -875604794)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TeamMember.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class TeamMemberBase extends dss.vector.solutions.irs.Targeter implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TeamMember";
  public static java.lang.String ISSPRAYLEADER = "isSprayLeader";
  public static java.lang.String ISSPRAYOPERATOR = "isSprayOperator";
  public static java.lang.String MEMBERID = "memberId";
  public static java.lang.String PERSON = "person";
  private static final long serialVersionUID = -875604794;
  
  public TeamMemberBase()
  {
    super();
  }
  
  public Boolean getIsSprayLeader()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYLEADER));
  }
  
  public void validateIsSprayLeader()
  {
    this.validateAttribute(ISSPRAYLEADER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsSprayLeaderMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.TeamMember.CLASS);
    return mdClassIF.definesAttribute(ISSPRAYLEADER);
  }
  
  public void setIsSprayLeader(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSPRAYLEADER, "");
    }
    else
    {
      setValue(ISSPRAYLEADER, java.lang.Boolean.toString(value));
    }
  }
  
  public Boolean getIsSprayOperator()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYOPERATOR));
  }
  
  public void validateIsSprayOperator()
  {
    this.validateAttribute(ISSPRAYOPERATOR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsSprayOperatorMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.TeamMember.CLASS);
    return mdClassIF.definesAttribute(ISSPRAYOPERATOR);
  }
  
  public void setIsSprayOperator(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSPRAYOPERATOR, "");
    }
    else
    {
      setValue(ISSPRAYOPERATOR, java.lang.Boolean.toString(value));
    }
  }
  
  public String getMemberId()
  {
    return getValue(MEMBERID);
  }
  
  public void validateMemberId()
  {
    this.validateAttribute(MEMBERID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMemberIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.TeamMember.CLASS);
    return mdClassIF.definesAttribute(MEMBERID);
  }
  
  public void setMemberId(String value)
  {
    if(value == null)
    {
      setValue(MEMBERID, "");
    }
    else
    {
      setValue(MEMBERID, value);
    }
  }
  
  public dss.vector.solutions.Person getPerson()
  {
    if (getValue(PERSON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.Person.get(getValue(PERSON));
    }
  }
  
  public void validatePerson()
  {
    this.validateAttribute(PERSON);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPersonMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.TeamMember.CLASS);
    return mdClassIF.definesAttribute(PERSON);
  }
  
  public void setPerson(dss.vector.solutions.Person value)
  {
    if(value == null)
    {
      setValue(PERSON, "");
    }
    else
    {
      setValue(PERSON, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TeamMemberQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TeamMemberQuery query = new TeamMemberQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.irs.LeadTeam addLeadsTeam(dss.vector.solutions.irs.SprayTeam sprayTeam)
  {
    return (dss.vector.solutions.irs.LeadTeam) addParent(sprayTeam, dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  public void removeLeadsTeam(dss.vector.solutions.irs.SprayTeam sprayTeam)
  {
    removeAllParents(sprayTeam, dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.SprayTeam> getAllLeadsTeam()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.SprayTeam>) getParents(dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.LeadTeam> getAllLeadsTeamRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.LeadTeam>) getParentRelationships(dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.LeadTeam> getLeadsTeamRel(dss.vector.solutions.irs.SprayTeam sprayTeam)
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.LeadTeam>) getRelationshipsWithParent(sprayTeam, dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  public dss.vector.solutions.irs.InTeam addSprayTeam(dss.vector.solutions.irs.SprayTeam sprayTeam)
  {
    return (dss.vector.solutions.irs.InTeam) addParent(sprayTeam, dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  public void removeSprayTeam(dss.vector.solutions.irs.SprayTeam sprayTeam)
  {
    removeAllParents(sprayTeam, dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.SprayTeam> getAllSprayTeam()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.SprayTeam>) getParents(dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InTeam> getAllSprayTeamRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InTeam>) getParentRelationships(dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InTeam> getSprayTeamRel(dss.vector.solutions.irs.SprayTeam sprayTeam)
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InTeam>) getRelationshipsWithParent(sprayTeam, dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  public static TeamMember get(String id)
  {
    return (TeamMember) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static TeamMember getByKey(String key)
  {
    return (TeamMember) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public dss.vector.solutions.irs.TeamMemberView getView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.TeamMember.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.irs.TeamMemberView getView(java.lang.String id)
  {
    TeamMember _instance = TeamMember.get(id);
    return _instance.getView();
  }
  
  public static com.terraframe.mojo.query.ValueQuery searchForLeader(java.lang.String search)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.TeamMember.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static TeamMember lock(java.lang.String id)
  {
    TeamMember _instance = TeamMember.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static TeamMember unlock(java.lang.String id)
  {
    TeamMember _instance = TeamMember.get(id);
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
