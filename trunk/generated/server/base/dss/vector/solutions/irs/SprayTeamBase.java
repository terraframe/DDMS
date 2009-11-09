package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 72444939)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SprayTeam.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class SprayTeamBase extends dss.vector.solutions.irs.Targeter implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayTeam";
  public static java.lang.String SPRAYZONE = "sprayZone";
  public static java.lang.String TEAMID = "teamId";
  private static final long serialVersionUID = 72444939;
  
  public SprayTeamBase()
  {
    super();
  }
  
  public dss.vector.solutions.geo.generated.SprayZone getSprayZone()
  {
    if (getValue(SPRAYZONE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.SprayZone.get(getValue(SPRAYZONE));
    }
  }
  
  public void validateSprayZone()
  {
    this.validateAttribute(SPRAYZONE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayZoneMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.SprayTeam.CLASS);
    return mdClassIF.definesAttribute(SPRAYZONE);
  }
  
  public void setSprayZone(dss.vector.solutions.geo.generated.SprayZone value)
  {
    if(value == null)
    {
      setValue(SPRAYZONE, "");
    }
    else
    {
      setValue(SPRAYZONE, value.getId());
    }
  }
  
  public String getTeamId()
  {
    return getValue(TEAMID);
  }
  
  public void validateTeamId()
  {
    this.validateAttribute(TEAMID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTeamIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.SprayTeam.CLASS);
    return mdClassIF.definesAttribute(TEAMID);
  }
  
  public void setTeamId(String value)
  {
    if(value == null)
    {
      setValue(TEAMID, "");
    }
    else
    {
      setValue(TEAMID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SprayTeamQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SprayTeamQuery query = new SprayTeamQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.irs.InTeam addSprayTeamMembers(dss.vector.solutions.irs.SprayOperator sprayOperator)
  {
    return (dss.vector.solutions.irs.InTeam) addChild(sprayOperator, dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  public void removeSprayTeamMembers(dss.vector.solutions.irs.SprayOperator sprayOperator)
  {
    removeAllChildren(sprayOperator, dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.SprayOperator> getAllSprayTeamMembers()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.SprayOperator>) getChildren(dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InTeam> getAllSprayTeamMembersRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InTeam>) getChildRelationships(dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InTeam> getSprayTeamMembersRel(dss.vector.solutions.irs.SprayOperator sprayOperator)
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InTeam>) getRelationshipsWithChild(sprayOperator, dss.vector.solutions.irs.InTeam.CLASS);
  }
  
  public dss.vector.solutions.irs.LeadTeam addTeamLeader(dss.vector.solutions.irs.SprayLeader sprayLeader)
  {
    return (dss.vector.solutions.irs.LeadTeam) addChild(sprayLeader, dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  public void removeTeamLeader(dss.vector.solutions.irs.SprayLeader sprayLeader)
  {
    removeAllChildren(sprayLeader, dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.SprayLeader> getAllTeamLeader()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.SprayLeader>) getChildren(dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.LeadTeam> getAllTeamLeaderRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.LeadTeam>) getChildRelationships(dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.LeadTeam> getTeamLeaderRel(dss.vector.solutions.irs.SprayLeader sprayLeader)
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.LeadTeam>) getRelationshipsWithChild(sprayLeader, dss.vector.solutions.irs.LeadTeam.CLASS);
  }
  
  public static SprayTeam get(String id)
  {
    return (SprayTeam) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static SprayTeam getByKey(String key)
  {
    return (SprayTeam) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public void create(java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeam.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void create(java.lang.String id, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds)
  {
    SprayTeam _instance = SprayTeam.get(id);
    _instance.create(geoId, leaderId, operatorIds);
  }
  
  public void edit(java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds, java.lang.String[] removedIds)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeam.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void edit(java.lang.String id, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds, java.lang.String[] removedIds)
  {
    SprayTeam _instance = SprayTeam.get(id);
    _instance.edit(geoId, leaderId, operatorIds, removedIds);
  }
  
  public static dss.vector.solutions.irs.SprayTeam[] findByLocation(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeam.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.irs.SprayOperatorView[] getTeamMemberViews()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeam.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.irs.SprayOperatorView[] getTeamMemberViews(java.lang.String id)
  {
    SprayTeam _instance = SprayTeam.get(id);
    return _instance.getTeamMemberViews();
  }
  
  public dss.vector.solutions.irs.SprayOperator[] getTeamMembers()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.SprayTeam.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.irs.SprayOperator[] getTeamMembers(java.lang.String id)
  {
    SprayTeam _instance = SprayTeam.get(id);
    return _instance.getTeamMembers();
  }
  
  public static SprayTeam lock(java.lang.String id)
  {
    SprayTeam _instance = SprayTeam.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SprayTeam unlock(java.lang.String id)
  {
    SprayTeam _instance = SprayTeam.get(id);
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
