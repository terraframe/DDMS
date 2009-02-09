package mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GenerationMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class GenerationMasterQuery extends com.terraframe.mojo.system.EnumerationMasterQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1234203359713L;

  public GenerationMasterQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "mdss.entomology.GenerationMaster";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends GenerationMaster> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<GenerationMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_Generation()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("mdss.entomology.Generation"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_Generation()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("mdss.entomology.Generation"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface GenerationMasterQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(mdss.entomology.GenerationMaster generationMaster);

    public com.terraframe.mojo.query.BasicCondition NE(mdss.entomology.GenerationMaster generationMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class GenerationMasterQueryReference extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements GenerationMasterQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1234203359860L;

  public GenerationMasterQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(mdss.entomology.GenerationMaster generationMaster)
    {
      return this.EQ(generationMaster.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(mdss.entomology.GenerationMaster generationMaster)
    {
      return this.NE(generationMaster.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface GenerationMasterQueryEnumerationIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {


  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class GenerationMasterQueryEnumeration extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements GenerationMasterQueryEnumerationIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1234203359951L;

  public GenerationMasterQueryEnumeration(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias);

  }

  }

/**
 * Specifies type safe query methods for the enumeration mdss.entomology.Generation.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface GenerationQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, GenerationMasterQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(mdss.entomology.Generation ... generation);
    public com.terraframe.mojo.query.Condition notContainsAny(mdss.entomology.Generation ... generation);
    public com.terraframe.mojo.query.Condition containsAll(mdss.entomology.Generation ... generation);
    public com.terraframe.mojo.query.Condition notContainsAll(mdss.entomology.Generation ... generation);
    public com.terraframe.mojo.query.Condition containsExactly(mdss.entomology.Generation ... generation);
  }

/**
 * Implements type safe query methods for the enumeration mdss.entomology.Generation.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class GenerationQuery extends GenerationMasterQueryEnumeration implements  GenerationQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public GenerationQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias);

  }

    public com.terraframe.mojo.query.Condition containsAny(mdss.entomology.Generation ... generation)  {

      String[] enumIdArray = new String[generation.length]; 

      for (int i=0; i<generation.length; i++)
      {
        enumIdArray[i] = generation[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(mdss.entomology.Generation ... generation)  {

      String[] enumIdArray = new String[generation.length]; 

      for (int i=0; i<generation.length; i++)
      {
        enumIdArray[i] = generation[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(mdss.entomology.Generation ... generation)  {

      String[] enumIdArray = new String[generation.length]; 

      for (int i=0; i<generation.length; i++)
      {
        enumIdArray[i] = generation[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(mdss.entomology.Generation ... generation)  {

      String[] enumIdArray = new String[generation.length]; 

      for (int i=0; i<generation.length; i++)
      {
        enumIdArray[i] = generation[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(mdss.entomology.Generation ... generation)  {

      String[] enumIdArray = new String[generation.length]; 

      for (int i=0; i<generation.length; i++)
      {
        enumIdArray[i] = generation[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
