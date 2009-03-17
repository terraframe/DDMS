package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SexMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class SexMasterQuery extends com.terraframe.mojo.system.EnumerationMasterQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237311443575L;

  public SexMasterQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.SexMaster";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends SexMaster> getIterator()
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

    java.sql.ResultSet results = com.terraframe.mojo.dataaccess.database.Database.query(sqlStmt);
    return new com.terraframe.mojo.business.BusinessIterator<SexMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_Sex()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.entomology.Sex"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_Sex()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.entomology.Sex"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_AssaySex()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.entomology.AssaySex"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_AssaySex()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.entomology.AssaySex"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface SexMasterQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.SexMaster sexMaster);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.SexMaster sexMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class SexMasterQueryReference extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements SexMasterQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237311443675L;

  public SexMasterQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.SexMaster sexMaster)
    {
      return this.EQ(sexMaster.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.SexMaster sexMaster)
    {
      return this.NE(sexMaster.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface SexMasterQueryEnumerationIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {


  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class SexMasterQueryEnumeration extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements SexMasterQueryEnumerationIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237311443734L;

  public SexMasterQueryEnumeration(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias);

  }

  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.entomology.Sex.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface SexQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, SexMasterQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.entomology.Sex ... sex);
    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.entomology.Sex ... sex);
    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.entomology.Sex ... sex);
    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.entomology.Sex ... sex);
    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.entomology.Sex ... sex);
  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.entomology.AssaySex.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface AssaySexQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, SexMasterQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.entomology.AssaySex ... assaySex);
    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.entomology.AssaySex ... assaySex);
    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.entomology.AssaySex ... assaySex);
    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.entomology.AssaySex ... assaySex);
    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.entomology.AssaySex ... assaySex);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.entomology.Sex.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class SexQuery extends SexMasterQueryEnumeration implements  SexQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public SexQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias);

  }

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.entomology.Sex ... sex)  {

      String[] enumIdArray = new String[sex.length]; 

      for (int i=0; i<sex.length; i++)
      {
        enumIdArray[i] = sex[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.entomology.Sex ... sex)  {

      String[] enumIdArray = new String[sex.length]; 

      for (int i=0; i<sex.length; i++)
      {
        enumIdArray[i] = sex[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.entomology.Sex ... sex)  {

      String[] enumIdArray = new String[sex.length]; 

      for (int i=0; i<sex.length; i++)
      {
        enumIdArray[i] = sex[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.entomology.Sex ... sex)  {

      String[] enumIdArray = new String[sex.length]; 

      for (int i=0; i<sex.length; i++)
      {
        enumIdArray[i] = sex[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.entomology.Sex ... sex)  {

      String[] enumIdArray = new String[sex.length]; 

      for (int i=0; i<sex.length; i++)
      {
        enumIdArray[i] = sex[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }
/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.entomology.AssaySex.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class AssaySexQuery extends SexMasterQueryEnumeration implements  AssaySexQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public AssaySexQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias);

  }

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.entomology.AssaySex ... assaySex)  {

      String[] enumIdArray = new String[assaySex.length]; 

      for (int i=0; i<assaySex.length; i++)
      {
        enumIdArray[i] = assaySex[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.entomology.AssaySex ... assaySex)  {

      String[] enumIdArray = new String[assaySex.length]; 

      for (int i=0; i<assaySex.length; i++)
      {
        enumIdArray[i] = assaySex[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.entomology.AssaySex ... assaySex)  {

      String[] enumIdArray = new String[assaySex.length]; 

      for (int i=0; i<assaySex.length; i++)
      {
        enumIdArray[i] = assaySex[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.entomology.AssaySex ... assaySex)  {

      String[] enumIdArray = new String[assaySex.length]; 

      for (int i=0; i<assaySex.length; i++)
      {
        enumIdArray[i] = assaySex[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.entomology.AssaySex ... assaySex)  {

      String[] enumIdArray = new String[assaySex.length]; 

      for (int i=0; i<assaySex.length; i++)
      {
        enumIdArray[i] = assaySex[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
