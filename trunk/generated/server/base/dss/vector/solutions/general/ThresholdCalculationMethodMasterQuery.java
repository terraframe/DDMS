package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = -605172583)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ThresholdCalculationMethodMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class ThresholdCalculationMethodMasterQuery extends com.terraframe.mojo.system.EnumerationMasterQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -605172583;

  public ThresholdCalculationMethodMasterQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public ThresholdCalculationMethodMasterQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.general.ThresholdCalculationMethodMaster.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends ThresholdCalculationMethodMaster> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<ThresholdCalculationMethodMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_ThresholdCalculationMethod()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.general.ThresholdCalculationMethod"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_ThresholdCalculationMethod()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.general.ThresholdCalculationMethod"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface ThresholdCalculationMethodMasterQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.general.ThresholdCalculationMethodMaster thresholdCalculationMethodMaster);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.general.ThresholdCalculationMethodMaster thresholdCalculationMethodMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class ThresholdCalculationMethodMasterQueryReference extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements ThresholdCalculationMethodMasterQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 2143915963;

  public ThresholdCalculationMethodMasterQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.general.ThresholdCalculationMethodMaster thresholdCalculationMethodMaster)
    {
      return this.EQ(thresholdCalculationMethodMaster.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.general.ThresholdCalculationMethodMaster thresholdCalculationMethodMaster)
    {
      return this.NE(thresholdCalculationMethodMaster.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface ThresholdCalculationMethodMasterQueryEnumerationIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {


  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class ThresholdCalculationMethodMasterQueryEnumeration extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements ThresholdCalculationMethodMasterQueryEnumerationIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 538123977;

  public ThresholdCalculationMethodMasterQueryEnumeration(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.general.ThresholdCalculationMethod.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface ThresholdCalculationMethodQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, ThresholdCalculationMethodMasterQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod);
    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod);
    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod);
    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod);
    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.general.ThresholdCalculationMethod.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class ThresholdCalculationMethodQuery extends ThresholdCalculationMethodMasterQueryEnumeration implements  ThresholdCalculationMethodQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public ThresholdCalculationMethodQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod)  {

      String[] enumIdArray = new String[thresholdCalculationMethod.length]; 

      for (int i=0; i<thresholdCalculationMethod.length; i++)
      {
        enumIdArray[i] = thresholdCalculationMethod[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod)  {

      String[] enumIdArray = new String[thresholdCalculationMethod.length]; 

      for (int i=0; i<thresholdCalculationMethod.length; i++)
      {
        enumIdArray[i] = thresholdCalculationMethod[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod)  {

      String[] enumIdArray = new String[thresholdCalculationMethod.length]; 

      for (int i=0; i<thresholdCalculationMethod.length; i++)
      {
        enumIdArray[i] = thresholdCalculationMethod[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod)  {

      String[] enumIdArray = new String[thresholdCalculationMethod.length]; 

      for (int i=0; i<thresholdCalculationMethod.length; i++)
      {
        enumIdArray[i] = thresholdCalculationMethod[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.general.ThresholdCalculationMethod ... thresholdCalculationMethod)  {

      String[] enumIdArray = new String[thresholdCalculationMethod.length]; 

      for (int i=0; i<thresholdCalculationMethod.length; i++)
      {
        enumIdArray[i] = thresholdCalculationMethod[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
