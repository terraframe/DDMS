package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -163812970)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ResponseMaster.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class ResponseMasterQuery extends com.terraframe.mojo.system.EnumerationMasterQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -163812970;

  public ResponseMasterQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public ResponseMasterQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.ResponseMaster.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends ResponseMaster> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<ResponseMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_RefusedResponse()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.RefusedResponse"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_RefusedResponse()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.RefusedResponse"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition enum_Response()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.Response"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.terraframe.mojo.query.Condition notEnum_Response()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery("com.terraframe.mojo.system.metadata.EnumerationAttributeItem");

    com.terraframe.mojo.business.BusinessQuery businessQuery = queryFactory.businessQuery("com.terraframe.mojo.system.metadata.MdEnumeration");
    com.terraframe.mojo.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.terraframe.mojo.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO("dss.vector.solutions.Response"); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface ResponseMasterQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.ResponseMaster responseMaster);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.ResponseMaster responseMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class ResponseMasterQueryReference extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements ResponseMasterQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 283956024;

  public ResponseMasterQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.ResponseMaster responseMaster)
    {
      return this.EQ(responseMaster.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.ResponseMaster responseMaster)
    {
      return this.NE(responseMaster.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface ResponseMasterQueryEnumerationIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {


  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class ResponseMasterQueryEnumeration extends com.terraframe.mojo.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements ResponseMasterQueryEnumerationIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 915150790;

  public ResponseMasterQueryEnumeration(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.RefusedResponse.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface RefusedResponseQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, ResponseMasterQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.RefusedResponse ... refusedResponse);
    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.RefusedResponse ... refusedResponse);
    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.RefusedResponse ... refusedResponse);
    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.RefusedResponse ... refusedResponse);
    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.RefusedResponse ... refusedResponse);
  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.Response.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface ResponseQueryIF extends com.terraframe.mojo.generation.loader.Reloadable, ResponseMasterQueryEnumerationIF  {

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.Response ... response);
    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.Response ... response);
    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.Response ... response);
    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.Response ... response);
    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.Response ... response);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.RefusedResponse.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class RefusedResponseQuery extends ResponseMasterQueryEnumeration implements  RefusedResponseQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public RefusedResponseQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.RefusedResponse ... refusedResponse)  {

      String[] enumIdArray = new String[refusedResponse.length]; 

      for (int i=0; i<refusedResponse.length; i++)
      {
        enumIdArray[i] = refusedResponse[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.RefusedResponse ... refusedResponse)  {

      String[] enumIdArray = new String[refusedResponse.length]; 

      for (int i=0; i<refusedResponse.length; i++)
      {
        enumIdArray[i] = refusedResponse[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.RefusedResponse ... refusedResponse)  {

      String[] enumIdArray = new String[refusedResponse.length]; 

      for (int i=0; i<refusedResponse.length; i++)
      {
        enumIdArray[i] = refusedResponse[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.RefusedResponse ... refusedResponse)  {

      String[] enumIdArray = new String[refusedResponse.length]; 

      for (int i=0; i<refusedResponse.length; i++)
      {
        enumIdArray[i] = refusedResponse[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.RefusedResponse ... refusedResponse)  {

      String[] enumIdArray = new String[refusedResponse.length]; 

      for (int i=0; i<refusedResponse.length; i++)
      {
        enumIdArray[i] = refusedResponse[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }
/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.Response.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class ResponseQuery extends ResponseMasterQueryEnumeration implements  ResponseQueryIF, com.terraframe.mojo.generation.loader.Reloadable
  {
  public ResponseQuery(com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.terraframe.mojo.query.Condition containsAny(dss.vector.solutions.Response ... response)  {

      String[] enumIdArray = new String[response.length]; 

      for (int i=0; i<response.length; i++)
      {
        enumIdArray[i] = response[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAny(dss.vector.solutions.Response ... response)  {

      String[] enumIdArray = new String[response.length]; 

      for (int i=0; i<response.length; i++)
      {
        enumIdArray[i] = response[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsAll(dss.vector.solutions.Response ... response)  {

      String[] enumIdArray = new String[response.length]; 

      for (int i=0; i<response.length; i++)
      {
        enumIdArray[i] = response[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition notContainsAll(dss.vector.solutions.Response ... response)  {

      String[] enumIdArray = new String[response.length]; 

      for (int i=0; i<response.length; i++)
      {
        enumIdArray[i] = response[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.terraframe.mojo.query.Condition containsExactly(dss.vector.solutions.Response ... response)  {

      String[] enumIdArray = new String[response.length]; 

      for (int i=0; i<response.length; i++)
      {
        enumIdArray[i] = response[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
