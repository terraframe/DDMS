package mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CollectionMethodMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class CollectionMethodMasterQuery extends com.terraframe.mojo.system.EnumerationMasterQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1234294599238L;

  public CollectionMethodMasterQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return "mdss.entomology.CollectionMethodMaster";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends CollectionMethodMaster> getIterator()
  {
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

    java.sql.ResultSet results = com.terraframe.mojo.dataaccess.database.Database.selectEntityAttributes(sqlStmt);
    return new com.terraframe.mojo.business.BusinessIterator<CollectionMethodMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_CollectionMethod()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("mdss.entomology.CollectionMethod"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_CollectionMethod()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("mdss.entomology.CollectionMethod"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface CollectionMethodMasterQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(mdss.entomology.CollectionMethodMaster collectionMethodMaster);

    public com.terraframe.mojo.query.BasicCondition NE(mdss.entomology.CollectionMethodMaster collectionMethodMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class CollectionMethodMasterQueryReference extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements CollectionMethodMasterQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1234294599324L;

  public CollectionMethodMasterQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(mdss.entomology.CollectionMethodMaster collectionMethodMaster)
    {
      return this.EQ(collectionMethodMaster.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(mdss.entomology.CollectionMethodMaster collectionMethodMaster)
    {
      return this.NE(collectionMethodMaster.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface CollectionMethodMasterQueryEnumerationIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {


  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class CollectionMethodMasterQueryEnumeration extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements CollectionMethodMasterQueryEnumerationIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1234294599378L;

  public CollectionMethodMasterQueryEnumeration(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias);

  }

  }

/**
 * Specifies type safe query methods for the enumeration mdss.entomology.CollectionMethod.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface CollectionMethodQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, CollectionMethodMasterQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(mdss.entomology.CollectionMethod ... collectionMethod);
    public com.terraframe.mojo.query.Condition notContainsAny(mdss.entomology.CollectionMethod ... collectionMethod);
    public com.terraframe.mojo.query.Condition containsAll(mdss.entomology.CollectionMethod ... collectionMethod);
    public com.terraframe.mojo.query.Condition notContainsAll(mdss.entomology.CollectionMethod ... collectionMethod);
    public com.terraframe.mojo.query.Condition containsExactly(mdss.entomology.CollectionMethod ... collectionMethod);
  }

/**
 * Implements type safe query methods for the enumeration mdss.entomology.CollectionMethod.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class CollectionMethodQuery extends CollectionMethodMasterQueryEnumeration implements  CollectionMethodQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public CollectionMethodQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias);

  }

    public com.terraframe.mojo.query.Condition containsAny(mdss.entomology.CollectionMethod ... collectionMethod)  {

      String[] enumIdArray = new String[collectionMethod.length]; 

      for (int i=0; i<collectionMethod.length; i++)
      {
        enumIdArray[i] = collectionMethod[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(mdss.entomology.CollectionMethod ... collectionMethod)  {

      String[] enumIdArray = new String[collectionMethod.length]; 

      for (int i=0; i<collectionMethod.length; i++)
      {
        enumIdArray[i] = collectionMethod[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(mdss.entomology.CollectionMethod ... collectionMethod)  {

      String[] enumIdArray = new String[collectionMethod.length]; 

      for (int i=0; i<collectionMethod.length; i++)
      {
        enumIdArray[i] = collectionMethod[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(mdss.entomology.CollectionMethod ... collectionMethod)  {

      String[] enumIdArray = new String[collectionMethod.length]; 

      for (int i=0; i<collectionMethod.length; i++)
      {
        enumIdArray[i] = collectionMethod[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(mdss.entomology.CollectionMethod ... collectionMethod)  {

      String[] enumIdArray = new String[collectionMethod.length]; 

      for (int i=0; i<collectionMethod.length; i++)
      {
        enumIdArray[i] = collectionMethod[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
