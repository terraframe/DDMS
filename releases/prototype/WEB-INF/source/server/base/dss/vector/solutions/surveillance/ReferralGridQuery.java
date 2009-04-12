package dss.vector.solutions.surveillance;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ReferralGrid.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class ReferralGridQuery extends dss.vector.solutions.surveillance.AbstractGridQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239517511355L;

  public ReferralGridQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.surveillance.ReferralGrid";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends ReferralGrid> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<ReferralGrid>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


  public com.terraframe.mojo.query.Condition aggregatedCase()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseReferral.CLASS);

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition aggregatedCase(dss.vector.solutions.surveillance.CaseReferralQuery caseReferralQuery)
  {
    return this.getBusinessQuery().isChildIn(caseReferralQuery);
  }

  public com.terraframe.mojo.query.Condition aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseReferral.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseReferralQuery caseReferralQuery)
  {
    caseReferralQuery.AND(caseReferralQuery.hasParent(aggregatedCaseQuery));
    return this.getBusinessQuery().isChildIn(caseReferralQuery);
  }


  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseReferral.CLASS);

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase(dss.vector.solutions.surveillance.CaseReferralQuery caseReferralQuery)
  {
    return this.getBusinessQuery().isNotChildIn(caseReferralQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseReferral.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseReferralQuery caseReferralQuery)
  {
    caseReferralQuery.AND(caseReferralQuery.hasParent(aggregatedCaseQuery));
    return this.getBusinessQuery().isNotChildIn(caseReferralQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface ReferralGridQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.surveillance.AbstractGridQuery.AbstractGridQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.surveillance.ReferralGrid referralGrid);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.surveillance.ReferralGrid referralGrid);


  public com.terraframe.mojo.query.Condition aggregatedCase();
  public com.terraframe.mojo.query.Condition aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery);

  public com.terraframe.mojo.query.Condition aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseReferralQuery caseReferralQuery);


  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase();
  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery);

  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseReferralQuery caseReferralQuery);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class ReferralGridQueryReference extends dss.vector.solutions.surveillance.AbstractGridQuery.AbstractGridQueryReference
 implements ReferralGridQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1239517511638L;

  public ReferralGridQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.surveillance.ReferralGrid referralGrid)
    {
      return this.EQ(referralGrid.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.surveillance.ReferralGrid referralGrid)
    {
      return this.NE(referralGrid.getId());
    }


  public com.terraframe.mojo.query.Condition aggregatedCase()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseReferral.CLASS);

    return this.isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseReferral.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseReferralQuery caseReferralQuery)
  {
    caseReferralQuery.AND(caseReferralQuery.hasParent(aggregatedCaseQuery));
    return this.isChildIn(caseReferralQuery);
  }


  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseReferral.CLASS);

    return this.isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseReferral.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_aggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseReferralQuery caseReferralQuery)
  {
    caseReferralQuery.AND(caseReferralQuery.hasParent(aggregatedCaseQuery));
    return this.isNotChildIn(caseReferralQuery);
  }

  }
}
