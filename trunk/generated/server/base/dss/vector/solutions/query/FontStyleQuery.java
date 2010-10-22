package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1335169396)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to FontStyle.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class FontStyleQuery extends com.runwaysdk.system.EnumerationMasterQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1335169396;

  public FontStyleQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public FontStyleQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.query.FontStyle.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getPriority()
  {
    return getPriority(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriority(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriority(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends FontStyle> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<FontStyle>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.runwaysdk.query.Condition enum_FontStyles()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.runwaysdk.system.metadata.EnumerationAttributeItem.CLASS);

    com.runwaysdk.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.runwaysdk.system.metadata.MdEnumeration.CLASS);
    com.runwaysdk.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.runwaysdk.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.query.FontStyles.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.runwaysdk.query.Condition notEnum_FontStyles()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.runwaysdk.system.metadata.EnumerationAttributeItem.CLASS);

    com.runwaysdk.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.runwaysdk.system.metadata.MdEnumeration.CLASS);
    com.runwaysdk.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.runwaysdk.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.query.FontStyles.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface FontStyleQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {

    public com.runwaysdk.query.SelectableInteger getPriority();
    public com.runwaysdk.query.SelectableInteger getPriority(String alias);
    public com.runwaysdk.query.SelectableInteger getPriority(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.query.FontStyle fontStyle);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.query.FontStyle fontStyle);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class FontStyleQueryReference extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements FontStyleQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = 830298642;

  public FontStyleQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.query.FontStyle fontStyle)
    {
      if(fontStyle == null) return this.EQ((java.lang.String)null);
      return this.EQ(fontStyle.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.query.FontStyle fontStyle)
    {
      if(fontStyle == null) return this.NE((java.lang.String)null);
      return this.NE(fontStyle.getId());
    }

  public com.runwaysdk.query.SelectableInteger getPriority()
  {
    return getPriority(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriority(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriority(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, displayLabel);

  }
  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface FontStyleQueryEnumerationIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {

    public com.runwaysdk.query.SelectableInteger getPriority();
    public com.runwaysdk.query.SelectableInteger getPriority(String alias);
    public com.runwaysdk.query.SelectableInteger getPriority(String alias, String displayLabel);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class FontStyleQueryEnumeration extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements FontStyleQueryEnumerationIF, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = -478676668;

  public FontStyleQueryEnumeration(com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.runwaysdk.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  public com.runwaysdk.query.SelectableInteger getPriority()
  {
    return getPriority(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriority(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPriority(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, displayLabel);

  }
  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.query.FontStyles.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface FontStylesQueryIF extends com.runwaysdk.generation.loader.Reloadable, FontStyleQueryEnumerationIF  {

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.query.FontStyles ... fontStyles);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.query.FontStyles ... fontStyles);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.query.FontStyles ... fontStyles);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.query.FontStyles ... fontStyles);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.query.FontStyles ... fontStyles);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.query.FontStyles.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class FontStylesQuery extends FontStyleQueryEnumeration implements  FontStylesQueryIF, com.runwaysdk.generation.loader.Reloadable
  {
  public FontStylesQuery(com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.runwaysdk.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
