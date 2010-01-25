package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = -1681760144)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PeriodTypeMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class PeriodTypeMasterQuery extends com.terraframe.mojo.system.EnumerationMasterQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1681760144;

  public PeriodTypeMasterQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public PeriodTypeMasterQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.surveillance.PeriodTypeMaster.CLASS;
  }
  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod()
  {
    return getMaximumPeriod(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.PeriodTypeMaster.MAXIMUMPERIOD, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().attributeFactory(dss.vector.solutions.surveillance.PeriodTypeMaster.MAXIMUMPERIOD, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends PeriodTypeMaster> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<PeriodTypeMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_PeriodType()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.terraframe.mojo.system.metadata.EnumerationAttributeItem.CLASS);

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.terraframe.mojo.system.metadata.MdEnumeration.CLASS);
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.surveillance.PeriodType.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_PeriodType()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.terraframe.mojo.system.metadata.EnumerationAttributeItem.CLASS);

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.terraframe.mojo.system.metadata.MdEnumeration.CLASS);
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.surveillance.PeriodType.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface PeriodTypeMasterQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod();
    public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias);
    public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.surveillance.PeriodTypeMaster periodTypeMaster);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.surveillance.PeriodTypeMaster periodTypeMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class PeriodTypeMasterQueryReference extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements PeriodTypeMasterQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1137722510;

  public PeriodTypeMasterQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.surveillance.PeriodTypeMaster periodTypeMaster)
    {
      return this.EQ(periodTypeMaster.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.surveillance.PeriodTypeMaster periodTypeMaster)
    {
      return this.NE(periodTypeMaster.getId());
    }

  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod()
  {
    return getMaximumPeriod(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("maximumPeriod", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("maximumPeriod", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface PeriodTypeMasterQueryEnumerationIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {

    public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod();
    public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias);
    public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias, String displayLabel);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class PeriodTypeMasterQueryEnumeration extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements PeriodTypeMasterQueryEnumerationIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1609047104;

  public PeriodTypeMasterQueryEnumeration(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod()
  {
    return getMaximumPeriod(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("maximumPeriod", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMaximumPeriod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("maximumPeriod", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.surveillance.PeriodType.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface PeriodTypeQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, PeriodTypeMasterQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.surveillance.PeriodType ... periodType);
    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.surveillance.PeriodType ... periodType);
    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.surveillance.PeriodType ... periodType);
    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.surveillance.PeriodType ... periodType);
    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.surveillance.PeriodType ... periodType);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.surveillance.PeriodType.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class PeriodTypeQuery extends PeriodTypeMasterQueryEnumeration implements  PeriodTypeQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public PeriodTypeQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.surveillance.PeriodType ... periodType)  {

      String[] enumIdArray = new String[periodType.length]; 

      for (int i=0; i<periodType.length; i++)
      {
        enumIdArray[i] = periodType[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.surveillance.PeriodType ... periodType)  {

      String[] enumIdArray = new String[periodType.length]; 

      for (int i=0; i<periodType.length; i++)
      {
        enumIdArray[i] = periodType[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.surveillance.PeriodType ... periodType)  {

      String[] enumIdArray = new String[periodType.length]; 

      for (int i=0; i<periodType.length; i++)
      {
        enumIdArray[i] = periodType[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.surveillance.PeriodType ... periodType)  {

      String[] enumIdArray = new String[periodType.length]; 

      for (int i=0; i<periodType.length; i++)
      {
        enumIdArray[i] = periodType[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.surveillance.PeriodType ... periodType)  {

      String[] enumIdArray = new String[periodType.length]; 

      for (int i=0; i<periodType.length; i++)
      {
        enumIdArray[i] = periodType[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
