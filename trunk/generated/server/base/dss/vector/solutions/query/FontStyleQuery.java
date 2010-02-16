package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1011170390)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to FontStyle.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class FontStyleQuery extends com.terraframe.mojo.system.EnumerationMasterQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1011170390;

  public FontStyleQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public FontStyleQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.query.FontStyle.CLASS;
  }
  public com.terraframe.mojo.query.SelectableInteger getPriority()
  {
    return getPriority(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPriority(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPriority(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends FontStyle> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<FontStyle>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_FontStyles()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.terraframe.mojo.system.metadata.EnumerationAttributeItem.CLASS);

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.terraframe.mojo.system.metadata.MdEnumeration.CLASS);
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.query.FontStyles.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_FontStyles()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.terraframe.mojo.system.metadata.EnumerationAttributeItem.CLASS);

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.terraframe.mojo.system.metadata.MdEnumeration.CLASS);
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.query.FontStyles.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface FontStyleQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {

    public com.terraframe.mojo.query.SelectableInteger getPriority();
    public com.terraframe.mojo.query.SelectableInteger getPriority(String alias);
    public com.terraframe.mojo.query.SelectableInteger getPriority(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.FontStyle fontStyle);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.FontStyle fontStyle);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class FontStyleQueryReference extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements FontStyleQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1995445876;

  public FontStyleQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.FontStyle fontStyle)
    {
      return this.EQ(fontStyle.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.FontStyle fontStyle)
    {
      return this.NE(fontStyle.getId());
    }

  public com.terraframe.mojo.query.SelectableInteger getPriority()
  {
    return getPriority(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPriority(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPriority(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, displayLabel);

  }
  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface FontStyleQueryEnumerationIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {

    public com.terraframe.mojo.query.SelectableInteger getPriority();
    public com.terraframe.mojo.query.SelectableInteger getPriority(String alias);
    public com.terraframe.mojo.query.SelectableInteger getPriority(String alias, String displayLabel);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class FontStyleQueryEnumeration extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements FontStyleQueryEnumerationIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1724970970;

  public FontStyleQueryEnumeration(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  public com.terraframe.mojo.query.SelectableInteger getPriority()
  {
    return getPriority(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPriority(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPriority(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.get(dss.vector.solutions.query.FontStyle.PRIORITY, alias, displayLabel);

  }
  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.query.FontStyles.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface FontStylesQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, FontStyleQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.query.FontStyles ... fontStyles);
    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.query.FontStyles ... fontStyles);
    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.query.FontStyles ... fontStyles);
    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.query.FontStyles ... fontStyles);
    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.query.FontStyles ... fontStyles);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.query.FontStyles.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class FontStylesQuery extends FontStyleQueryEnumeration implements  FontStylesQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public FontStylesQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.query.FontStyles ... fontStyles)  {

      String[] enumIdArray = new String[fontStyles.length]; 

      for (int i=0; i<fontStyles.length; i++)
      {
        enumIdArray[i] = fontStyles[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
