package dss.vector.solutions.geo.generated;

@com.runwaysdk.business.ClassSignature(hash = 167179581)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CollectionSite.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class CollectionSiteQuery extends dss.vector.solutions.geo.generated.GeoEntityQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public CollectionSiteQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public CollectionSiteQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.geo.generated.CollectionSite.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends CollectionSite> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<CollectionSite>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface CollectionSiteQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.geo.generated.CollectionSite collectionSite);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.geo.generated.CollectionSite collectionSite);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class CollectionSiteQueryReference extends dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference
 implements CollectionSiteQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public CollectionSiteQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.geo.generated.CollectionSite collectionSite)
    {
      if(collectionSite == null) return this.EQ((java.lang.String)null);
      return this.EQ(collectionSite.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.geo.generated.CollectionSite collectionSite)
    {
      if(collectionSite == null) return this.NE((java.lang.String)null);
      return this.NE(collectionSite.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface CollectionSiteQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryMultiReferenceIF
  {


    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class CollectionSiteQueryMultiReference extends dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryMultiReference
 implements CollectionSiteQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public CollectionSiteQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite)  {

      String[] itemIdArray = new String[collectionSite.length]; 

      for (int i=0; i<collectionSite.length; i++)
      {
        itemIdArray[i] = collectionSite[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite)  {

      String[] itemIdArray = new String[collectionSite.length]; 

      for (int i=0; i<collectionSite.length; i++)
      {
        itemIdArray[i] = collectionSite[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite)  {

      String[] itemIdArray = new String[collectionSite.length]; 

      for (int i=0; i<collectionSite.length; i++)
      {
        itemIdArray[i] = collectionSite[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite)  {

      String[] itemIdArray = new String[collectionSite.length]; 

      for (int i=0; i<collectionSite.length; i++)
      {
        itemIdArray[i] = collectionSite[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.geo.generated.CollectionSite ... collectionSite)  {

      String[] itemIdArray = new String[collectionSite.length]; 

      for (int i=0; i<collectionSite.length; i++)
      {
        itemIdArray[i] = collectionSite[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  }
}
