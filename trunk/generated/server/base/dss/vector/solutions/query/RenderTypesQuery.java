package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -61898422)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RenderTypes.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class RenderTypesQuery extends com.terraframe.mojo.system.EnumerationMasterQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -61898422;

  public RenderTypesQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public RenderTypesQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.query.RenderTypes.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends RenderTypes> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<RenderTypes>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_AllRenderTypes()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.terraframe.mojo.system.metadata.EnumerationAttributeItem.CLASS);

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.terraframe.mojo.system.metadata.MdEnumeration.CLASS);
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.query.AllRenderTypes.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_AllRenderTypes()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.terraframe.mojo.system.metadata.EnumerationAttributeItem.CLASS);

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.terraframe.mojo.system.metadata.MdEnumeration.CLASS);
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.query.AllRenderTypes.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface RenderTypesQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.RenderTypes renderTypes);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.RenderTypes renderTypes);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class RenderTypesQueryReference extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements RenderTypesQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1435706348;

  public RenderTypesQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.RenderTypes renderTypes)
    {
      return this.EQ(renderTypes.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.RenderTypes renderTypes)
    {
      return this.NE(renderTypes.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface RenderTypesQueryEnumerationIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {


  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class RenderTypesQueryEnumeration extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements RenderTypesQueryEnumerationIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -419690630;

  public RenderTypesQueryEnumeration(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.query.AllRenderTypes.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface AllRenderTypesQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, RenderTypesQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes);
    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes);
    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes);
    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes);
    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.query.AllRenderTypes.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class AllRenderTypesQuery extends RenderTypesQueryEnumeration implements  AllRenderTypesQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public AllRenderTypesQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes)  {

      String[] enumIdArray = new String[allRenderTypes.length]; 

      for (int i=0; i<allRenderTypes.length; i++)
      {
        enumIdArray[i] = allRenderTypes[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes)  {

      String[] enumIdArray = new String[allRenderTypes.length]; 

      for (int i=0; i<allRenderTypes.length; i++)
      {
        enumIdArray[i] = allRenderTypes[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes)  {

      String[] enumIdArray = new String[allRenderTypes.length]; 

      for (int i=0; i<allRenderTypes.length; i++)
      {
        enumIdArray[i] = allRenderTypes[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes)  {

      String[] enumIdArray = new String[allRenderTypes.length]; 

      for (int i=0; i<allRenderTypes.length; i++)
      {
        enumIdArray[i] = allRenderTypes[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.query.AllRenderTypes ... allRenderTypes)  {

      String[] enumIdArray = new String[allRenderTypes.length]; 

      for (int i=0; i<allRenderTypes.length; i++)
      {
        enumIdArray[i] = allRenderTypes[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
