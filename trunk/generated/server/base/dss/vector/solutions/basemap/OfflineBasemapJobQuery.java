package dss.vector.solutions.basemap;

@com.runwaysdk.business.ClassSignature(hash = 1402803308)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OfflineBasemapJob.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class OfflineBasemapJobQuery extends com.runwaysdk.system.scheduler.ExecutableJobQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public OfflineBasemapJobQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public OfflineBasemapJobQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.basemap.OfflineBasemapJob.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends OfflineBasemapJob> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<OfflineBasemapJob>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface OfflineBasemapJobQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.scheduler.ExecutableJobQuery.ExecutableJobQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.basemap.OfflineBasemapJob offlineBasemapJob);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.basemap.OfflineBasemapJob offlineBasemapJob);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class OfflineBasemapJobQueryReference extends com.runwaysdk.system.scheduler.ExecutableJobQuery.ExecutableJobQueryReference
 implements OfflineBasemapJobQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public OfflineBasemapJobQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.basemap.OfflineBasemapJob offlineBasemapJob)
    {
      if(offlineBasemapJob == null) return this.EQ((java.lang.String)null);
      return this.EQ(offlineBasemapJob.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.basemap.OfflineBasemapJob offlineBasemapJob)
    {
      if(offlineBasemapJob == null) return this.NE((java.lang.String)null);
      return this.NE(offlineBasemapJob.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface OfflineBasemapJobQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.scheduler.ExecutableJobQuery.ExecutableJobQueryMultiReferenceIF
  {


    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class OfflineBasemapJobQueryMultiReference extends com.runwaysdk.system.scheduler.ExecutableJobQuery.ExecutableJobQueryMultiReference
 implements OfflineBasemapJobQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public OfflineBasemapJobQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob)  {

      String[] itemIdArray = new String[offlineBasemapJob.length]; 

      for (int i=0; i<offlineBasemapJob.length; i++)
      {
        itemIdArray[i] = offlineBasemapJob[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob)  {

      String[] itemIdArray = new String[offlineBasemapJob.length]; 

      for (int i=0; i<offlineBasemapJob.length; i++)
      {
        itemIdArray[i] = offlineBasemapJob[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob)  {

      String[] itemIdArray = new String[offlineBasemapJob.length]; 

      for (int i=0; i<offlineBasemapJob.length; i++)
      {
        itemIdArray[i] = offlineBasemapJob[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob)  {

      String[] itemIdArray = new String[offlineBasemapJob.length]; 

      for (int i=0; i<offlineBasemapJob.length; i++)
      {
        itemIdArray[i] = offlineBasemapJob[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.basemap.OfflineBasemapJob ... offlineBasemapJob)  {

      String[] itemIdArray = new String[offlineBasemapJob.length]; 

      for (int i=0; i<offlineBasemapJob.length; i++)
      {
        itemIdArray[i] = offlineBasemapJob[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  }
}
