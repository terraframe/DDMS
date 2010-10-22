package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -10461294)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OutbreakCalculationMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class OutbreakCalculationMasterQuery extends com.runwaysdk.system.EnumerationMasterQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -10461294;

  public OutbreakCalculationMasterQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public OutbreakCalculationMasterQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.general.OutbreakCalculationMaster.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends OutbreakCalculationMaster> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<OutbreakCalculationMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.runwaysdk.query.Condition enum_OutbreakCalculation()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.runwaysdk.system.metadata.EnumerationAttributeItem.CLASS);

    com.runwaysdk.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.runwaysdk.system.metadata.MdEnumeration.CLASS);
    com.runwaysdk.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.runwaysdk.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.general.OutbreakCalculation.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.runwaysdk.query.Condition notEnum_OutbreakCalculation()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.runwaysdk.system.metadata.EnumerationAttributeItem.CLASS);

    com.runwaysdk.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.runwaysdk.system.metadata.MdEnumeration.CLASS);
    com.runwaysdk.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.runwaysdk.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.general.OutbreakCalculation.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface OutbreakCalculationMasterQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.general.OutbreakCalculationMaster outbreakCalculationMaster);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.general.OutbreakCalculationMaster outbreakCalculationMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class OutbreakCalculationMasterQueryReference extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements OutbreakCalculationMasterQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1335432016;

  public OutbreakCalculationMasterQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.general.OutbreakCalculationMaster outbreakCalculationMaster)
    {
      if(outbreakCalculationMaster == null) return this.EQ((java.lang.String)null);
      return this.EQ(outbreakCalculationMaster.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.general.OutbreakCalculationMaster outbreakCalculationMaster)
    {
      if(outbreakCalculationMaster == null) return this.NE((java.lang.String)null);
      return this.NE(outbreakCalculationMaster.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface OutbreakCalculationMasterQueryEnumerationIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {


  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class OutbreakCalculationMasterQueryEnumeration extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements OutbreakCalculationMasterQueryEnumerationIF, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = 2112713570;

  public OutbreakCalculationMasterQueryEnumeration(com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.runwaysdk.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.general.OutbreakCalculation.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface OutbreakCalculationQueryIF extends com.runwaysdk.generation.loader.Reloadable, OutbreakCalculationMasterQueryEnumerationIF  {

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.general.OutbreakCalculation.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class OutbreakCalculationQuery extends OutbreakCalculationMasterQueryEnumeration implements  OutbreakCalculationQueryIF, com.runwaysdk.generation.loader.Reloadable
  {
  public OutbreakCalculationQuery(com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.runwaysdk.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation)  {

      String[] enumIdArray = new String[outbreakCalculation.length]; 

      for (int i=0; i<outbreakCalculation.length; i++)
      {
        enumIdArray[i] = outbreakCalculation[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation)  {

      String[] enumIdArray = new String[outbreakCalculation.length]; 

      for (int i=0; i<outbreakCalculation.length; i++)
      {
        enumIdArray[i] = outbreakCalculation[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation)  {

      String[] enumIdArray = new String[outbreakCalculation.length]; 

      for (int i=0; i<outbreakCalculation.length; i++)
      {
        enumIdArray[i] = outbreakCalculation[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation)  {

      String[] enumIdArray = new String[outbreakCalculation.length]; 

      for (int i=0; i<outbreakCalculation.length; i++)
      {
        enumIdArray[i] = outbreakCalculation[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.general.OutbreakCalculation ... outbreakCalculation)  {

      String[] enumIdArray = new String[outbreakCalculation.length]; 

      for (int i=0; i<outbreakCalculation.length; i++)
      {
        enumIdArray[i] = outbreakCalculation[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
