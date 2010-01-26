package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1254165105)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TeamSpray.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class TeamSprayQuery extends dss.vector.solutions.irs.AbstractSprayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1254165105;

  public TeamSprayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public TeamSprayQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = new com.terraframe.mojo.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.irs.TeamSpray.CLASS;
  }
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex()
  {
    return getBrandForIndex(null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("brandForIndex");

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.TeamSpray.BRANDFORINDEX, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("brandForIndex");

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.TeamSpray.BRANDFORINDEX, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex()
  {
    return getGeoEntityForIndex(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntityForIndex");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.TeamSpray.GEOENTITYFORINDEX, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntityForIndex");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.TeamSpray.GEOENTITYFORINDEX, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getReceived()
  {
    return getReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.RECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.RECEIVED, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getRefills()
  {
    return getRefills(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.REFILLS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.REFILLS, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getReturned()
  {
    return getReturned(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.RETURNED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.RETURNED, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex()
  {
    return getSprayDateForIndex(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.SPRAYDATEFORINDEX, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.SPRAYDATEFORINDEX, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex()
  {
    return getSprayMethodForIndex(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.SPRAYMETHODFORINDEX, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.SPRAYMETHODFORINDEX, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam()
  {
    return getSprayTeam(null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sprayTeam");

    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.TeamSpray.SPRAYTEAM, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sprayTeam");

    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.TeamSpray.SPRAYTEAM, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.TARGET, alias, displayLabel);

  }
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader()
  {
    return getTeamLeader(null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("teamLeader");

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.TeamSpray.TEAMLEADER, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("teamLeader");

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.TeamSpray.TEAMLEADER, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek()
  {
    return getTeamSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.TEAMSPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.TEAMSPRAYWEEK, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getUsed()
  {
    return getUsed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.USED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.USED, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("brandForIndex")) 
    {
       return new dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("geoEntityForIndex")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("sprayTeam")) 
    {
       return new dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("teamLeader")) 
    {
       return new dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends TeamSpray> getIterator()
  {
    this.checkNotUsedInValueQuery();
    String sqlStmt;
    if (_limit != null && _skip != null)
    {
      sqlStmt = this.getComponentQuery().getSQL(_limit, _skip);
    }
    else
    {
      sqlStmt = this.getComponentQuery().getSQL();
    }
    java.util.Map<String, com.terraframe.mojo.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.terraframe.mojo.dataaccess.database.Database.query(sqlStmt);
    return new com.terraframe.mojo.business.BusinessIterator<TeamSpray>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface TeamSprayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.irs.AbstractSprayQuery.AbstractSprayQueryReferenceIF
  {

    public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex();
    public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias);
    public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias, String displayLabel);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getReceived();
    public com.terraframe.mojo.query.AttributeInteger getReceived(String alias);
    public com.terraframe.mojo.query.AttributeInteger getReceived(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getRefills();
    public com.terraframe.mojo.query.AttributeInteger getRefills(String alias);
    public com.terraframe.mojo.query.AttributeInteger getRefills(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getReturned();
    public com.terraframe.mojo.query.AttributeInteger getReturned(String alias);
    public com.terraframe.mojo.query.AttributeInteger getReturned(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex();
    public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex(String alias);
    public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex();
    public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex(String alias);
    public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex(String alias, String displayLabel);
    public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam();
    public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias);
    public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getTarget();
    public com.terraframe.mojo.query.AttributeInteger getTarget(String alias);
    public com.terraframe.mojo.query.AttributeInteger getTarget(String alias, String displayLabel);
    public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader();
    public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias);
    public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek();
    public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek(String alias);
    public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getUsed();
    public com.terraframe.mojo.query.AttributeInteger getUsed(String alias);
    public com.terraframe.mojo.query.AttributeInteger getUsed(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.irs.TeamSpray teamSpray);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.irs.TeamSpray teamSpray);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class TeamSprayQueryReference extends dss.vector.solutions.irs.AbstractSprayQuery.AbstractSprayQueryReference
 implements TeamSprayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 61084175;

  public TeamSprayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.irs.TeamSpray teamSpray)
    {
      return this.EQ(teamSpray.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.irs.TeamSpray teamSpray)
    {
      return this.NE(teamSpray.getId());
    }

  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex()
  {
    return getBrandForIndex(null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias)
  {
    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.get(dss.vector.solutions.irs.TeamSpray.BRANDFORINDEX, alias, null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias, String displayLabel)
  {
    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.get(dss.vector.solutions.irs.TeamSpray.BRANDFORINDEX,  alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex()
  {
    return getGeoEntityForIndex(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.irs.TeamSpray.GEOENTITYFORINDEX, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias, String displayLabel)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.irs.TeamSpray.GEOENTITYFORINDEX,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getReceived()
  {
    return getReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.RECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.RECEIVED, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getRefills()
  {
    return getRefills(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.REFILLS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.REFILLS, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getReturned()
  {
    return getReturned(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.RETURNED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.RETURNED, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex()
  {
    return getSprayDateForIndex(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYDATEFORINDEX, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getSprayDateForIndex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYDATEFORINDEX, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex()
  {
    return getSprayMethodForIndex(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYMETHODFORINDEX, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSprayMethodForIndex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYMETHODFORINDEX, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam()
  {
    return getSprayTeam(null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias)
  {
    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYTEAM, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias, String displayLabel)
  {
    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYTEAM,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.TARGET, alias, displayLabel);

  }
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader()
  {
    return getTeamLeader(null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias)
  {
    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.get(dss.vector.solutions.irs.TeamSpray.TEAMLEADER, alias, null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias, String displayLabel)
  {
    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.get(dss.vector.solutions.irs.TeamSpray.TEAMLEADER,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek()
  {
    return getTeamSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.TEAMSPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.TEAMSPRAYWEEK, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getUsed()
  {
    return getUsed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.USED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.irs.TeamSpray.USED, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("brandForIndex")) 
    {
       return new dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("geoEntityForIndex")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("sprayTeam")) 
    {
       return new dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("teamLeader")) 
    {
       return new dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
