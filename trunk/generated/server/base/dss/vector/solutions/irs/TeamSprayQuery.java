package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 717835059)
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
private static final long serialVersionUID = 717835059;

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
  public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex()
  {
    return getSprayDateForIndex(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.SPRAYDATEFORINDEX, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.SPRAYDATEFORINDEX, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex()
  {
    return getSprayMethodForIndex(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.SPRAYMETHODFORINDEX, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.SPRAYMETHODFORINDEX, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.TARGET, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek()
  {
    return getTeamSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.TEAMSPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.irs.TeamSpray.TEAMSPRAYWEEK, alias, displayLabel);

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
    public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex();
    public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex(String alias);
    public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex();
    public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex(String alias);
    public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex(String alias, String displayLabel);
    public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam();
    public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias);
    public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableInteger getTarget();
    public com.terraframe.mojo.query.SelectableInteger getTarget(String alias);
    public com.terraframe.mojo.query.SelectableInteger getTarget(String alias, String displayLabel);
    public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader();
    public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias);
    public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek();
    public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias);
    public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias, String displayLabel);

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
private static final long serialVersionUID = 715087189;

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
  public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex()
  {
    return getSprayDateForIndex(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYDATEFORINDEX, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getSprayDateForIndex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYDATEFORINDEX, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex()
  {
    return getSprayMethodForIndex(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYMETHODFORINDEX, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSprayMethodForIndex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.get(dss.vector.solutions.irs.TeamSpray.SPRAYMETHODFORINDEX, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.get(dss.vector.solutions.irs.TeamSpray.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.get(dss.vector.solutions.irs.TeamSpray.TARGET, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek()
  {
    return getTeamSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.get(dss.vector.solutions.irs.TeamSpray.TEAMSPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.get(dss.vector.solutions.irs.TeamSpray.TEAMSPRAYWEEK, alias, displayLabel);

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
