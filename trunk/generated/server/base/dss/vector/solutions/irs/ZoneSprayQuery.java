package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -705047833)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ZoneSpray.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class ZoneSprayQuery extends dss.vector.solutions.irs.AbstractSprayQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -705047833;

  public ZoneSprayQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public ZoneSprayQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = new com.runwaysdk.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.irs.ZoneSpray.CLASS;
  }
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex()
  {
    return getBrandForIndex(null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("brandForIndex");

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.ZoneSpray.BRANDFORINDEX, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("brandForIndex");

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.ZoneSpray.BRANDFORINDEX, mdAttributeIF, this, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex()
  {
    return getGeoEntityForIndex(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntityForIndex");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.ZoneSpray.GEOENTITYFORINDEX, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntityForIndex");

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.ZoneSpray.GEOENTITYFORINDEX, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getSprayDateForIndex()
  {
    return getSprayDateForIndex(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDateForIndex(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.irs.ZoneSpray.SPRAYDATEFORINDEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDateForIndex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getComponentQuery().get(dss.vector.solutions.irs.ZoneSpray.SPRAYDATEFORINDEX, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSprayMethodForIndex()
  {
    return getSprayMethodForIndex(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayMethodForIndex(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.irs.ZoneSpray.SPRAYMETHODFORINDEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayMethodForIndex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.irs.ZoneSpray.SPRAYMETHODFORINDEX, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getSprayWeek()
  {
    return getSprayWeek(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayWeek(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.irs.ZoneSpray.SPRAYWEEK, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayWeek(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.irs.ZoneSpray.SPRAYWEEK, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor()
  {
    return getSupervisor(null);

  }
 
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("supervisor");

    return (dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.ZoneSpray.SUPERVISOR, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("supervisor");

    return (dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.irs.ZoneSpray.SUPERVISOR, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.irs.ZoneSpray.TARGET, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.irs.ZoneSpray.TARGET, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("brandForIndex")) 
    {
       return new dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("geoEntityForIndex")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("supervisor")) 
    {
       return new dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
  public com.runwaysdk.query.OIterator<? extends ZoneSpray> getIterator()
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
    java.util.Map<String, com.runwaysdk.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.runwaysdk.dataaccess.database.Database.query(sqlStmt);
    return new com.runwaysdk.business.BusinessIterator<ZoneSpray>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface ZoneSprayQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.irs.AbstractSprayQuery.AbstractSprayQueryReferenceIF
  {

    public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex();
    public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias);
    public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias, String displayLabel);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableMoment getSprayDateForIndex();
    public com.runwaysdk.query.SelectableMoment getSprayDateForIndex(String alias);
    public com.runwaysdk.query.SelectableMoment getSprayDateForIndex(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getSprayMethodForIndex();
    public com.runwaysdk.query.SelectableChar getSprayMethodForIndex(String alias);
    public com.runwaysdk.query.SelectableChar getSprayMethodForIndex(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getSprayWeek();
    public com.runwaysdk.query.SelectableInteger getSprayWeek(String alias);
    public com.runwaysdk.query.SelectableInteger getSprayWeek(String alias, String displayLabel);
    public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor();
    public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor(String alias);
    public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getTarget();
    public com.runwaysdk.query.SelectableInteger getTarget(String alias);
    public com.runwaysdk.query.SelectableInteger getTarget(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.irs.ZoneSpray zoneSpray);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.irs.ZoneSpray zoneSpray);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class ZoneSprayQueryReference extends dss.vector.solutions.irs.AbstractSprayQuery.AbstractSprayQueryReference
 implements ZoneSprayQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1791536119;

  public ZoneSprayQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.irs.ZoneSpray zoneSpray)
    {
      return this.EQ(zoneSpray.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.irs.ZoneSpray zoneSpray)
    {
      return this.NE(zoneSpray.getId());
    }

  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex()
  {
    return getBrandForIndex(null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias)
  {
    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.get(dss.vector.solutions.irs.ZoneSpray.BRANDFORINDEX, alias, null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrandForIndex(String alias, String displayLabel)
  {
    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.get(dss.vector.solutions.irs.ZoneSpray.BRANDFORINDEX,  alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex()
  {
    return getGeoEntityForIndex(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.irs.ZoneSpray.GEOENTITYFORINDEX, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntityForIndex(String alias, String displayLabel)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.irs.ZoneSpray.GEOENTITYFORINDEX,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getSprayDateForIndex()
  {
    return getSprayDateForIndex(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDateForIndex(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.irs.ZoneSpray.SPRAYDATEFORINDEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDateForIndex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.get(dss.vector.solutions.irs.ZoneSpray.SPRAYDATEFORINDEX, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSprayMethodForIndex()
  {
    return getSprayMethodForIndex(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayMethodForIndex(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.irs.ZoneSpray.SPRAYMETHODFORINDEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayMethodForIndex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.irs.ZoneSpray.SPRAYMETHODFORINDEX, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getSprayWeek()
  {
    return getSprayWeek(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayWeek(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.irs.ZoneSpray.SPRAYWEEK, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayWeek(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.irs.ZoneSpray.SPRAYWEEK, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor()
  {
    return getSupervisor(null);

  }
 
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor(String alias)
  {
    return (dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF)this.get(dss.vector.solutions.irs.ZoneSpray.SUPERVISOR, alias, null);

  }
 
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor(String alias, String displayLabel)
  {
    return (dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF)this.get(dss.vector.solutions.irs.ZoneSpray.SUPERVISOR,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.irs.ZoneSpray.TARGET, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.irs.ZoneSpray.TARGET, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("brandForIndex")) 
    {
       return new dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("geoEntityForIndex")) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("supervisor")) 
    {
       return new dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
