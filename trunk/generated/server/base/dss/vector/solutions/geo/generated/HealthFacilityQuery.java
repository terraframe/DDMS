package dss.vector.solutions.geo.generated;

@com.runwaysdk.business.ClassSignature(hash = 2066173795)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to HealthFacility.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class HealthFacilityQuery extends dss.vector.solutions.geo.generated.GeoEntityQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 2066173795;

  public HealthFacilityQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public HealthFacilityQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.geo.generated.HealthFacility.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends HealthFacility> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<HealthFacility>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface HealthFacilityQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.geo.generated.HealthFacility healthFacility);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.geo.generated.HealthFacility healthFacility);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class HealthFacilityQueryReference extends dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference
 implements HealthFacilityQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1174084095;

  public HealthFacilityQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.geo.generated.HealthFacility healthFacility)
    {
      return this.EQ(healthFacility.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.geo.generated.HealthFacility healthFacility)
    {
      return this.NE(healthFacility.getId());
    }

  }
}
