package dss.vector.solutions.surveillance;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TreatmentGrid.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class TreatmentGridQuery extends dss.vector.solutions.surveillance.AbstractGridQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239572471334L;

  public TreatmentGridQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.surveillance.TreatmentGrid";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends TreatmentGrid> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<TreatmentGrid>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


  public com.terraframe.mojo.query.Condition caseTreatment()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatment.CLASS);

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatment(dss.vector.solutions.surveillance.CaseTreatmentQuery caseTreatmentQuery)
  {
    return this.getBusinessQuery().isChildIn(caseTreatmentQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatment.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentQuery caseTreatmentQuery)
  {
    caseTreatmentQuery.AND(caseTreatmentQuery.hasParent(aggregatedCaseQuery));
    return this.getBusinessQuery().isChildIn(caseTreatmentQuery);
  }


  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatment.CLASS);

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment(dss.vector.solutions.surveillance.CaseTreatmentQuery caseTreatmentQuery)
  {
    return this.getBusinessQuery().isNotChildIn(caseTreatmentQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatment.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentQuery caseTreatmentQuery)
  {
    caseTreatmentQuery.AND(caseTreatmentQuery.hasParent(aggregatedCaseQuery));
    return this.getBusinessQuery().isNotChildIn(caseTreatmentQuery);
  }


  public com.terraframe.mojo.query.Condition caseTreatmentStock()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatmentStock.CLASS);

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatmentStock(dss.vector.solutions.surveillance.CaseTreatmentStockQuery caseTreatmentStockQuery)
  {
    return this.getBusinessQuery().isChildIn(caseTreatmentStockQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatmentStock.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentStockQuery caseTreatmentStockQuery)
  {
    caseTreatmentStockQuery.AND(caseTreatmentStockQuery.hasParent(aggregatedCaseQuery));
    return this.getBusinessQuery().isChildIn(caseTreatmentStockQuery);
  }


  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatmentStock.CLASS);

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock(dss.vector.solutions.surveillance.CaseTreatmentStockQuery caseTreatmentStockQuery)
  {
    return this.getBusinessQuery().isNotChildIn(caseTreatmentStockQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatmentStock.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentStockQuery caseTreatmentStockQuery)
  {
    caseTreatmentStockQuery.AND(caseTreatmentStockQuery.hasParent(aggregatedCaseQuery));
    return this.getBusinessQuery().isNotChildIn(caseTreatmentStockQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface TreatmentGridQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.surveillance.AbstractGridQuery.AbstractGridQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.surveillance.TreatmentGrid treatmentGrid);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.surveillance.TreatmentGrid treatmentGrid);


  public com.terraframe.mojo.query.Condition caseTreatment();
  public com.terraframe.mojo.query.Condition caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery);

  public com.terraframe.mojo.query.Condition caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentQuery caseTreatmentQuery);


  public com.terraframe.mojo.query.Condition caseTreatmentStock();
  public com.terraframe.mojo.query.Condition caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery);

  public com.terraframe.mojo.query.Condition caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentStockQuery caseTreatmentStockQuery);


  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment();
  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery);

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentQuery caseTreatmentQuery);


  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock();
  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery);

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentStockQuery caseTreatmentStockQuery);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class TreatmentGridQueryReference extends dss.vector.solutions.surveillance.AbstractGridQuery.AbstractGridQueryReference
 implements TreatmentGridQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1239572471612L;

  public TreatmentGridQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.surveillance.TreatmentGrid treatmentGrid)
    {
      return this.EQ(treatmentGrid.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.surveillance.TreatmentGrid treatmentGrid)
    {
      return this.NE(treatmentGrid.getId());
    }


  public com.terraframe.mojo.query.Condition caseTreatment()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatment.CLASS);

    return this.isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatment.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentQuery caseTreatmentQuery)
  {
    caseTreatmentQuery.AND(caseTreatmentQuery.hasParent(aggregatedCaseQuery));
    return this.isChildIn(caseTreatmentQuery);
  }


  public com.terraframe.mojo.query.Condition caseTreatmentStock()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatmentStock.CLASS);

    return this.isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatmentStock.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.isChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentStockQuery caseTreatmentStockQuery)
  {
    caseTreatmentStockQuery.AND(caseTreatmentStockQuery.hasParent(aggregatedCaseQuery));
    return this.isChildIn(caseTreatmentStockQuery);
  }


  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatment.CLASS);

    return this.isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatment.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatment(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentQuery caseTreatmentQuery)
  {
    caseTreatmentQuery.AND(caseTreatmentQuery.hasParent(aggregatedCaseQuery));
    return this.isNotChildIn(caseTreatmentQuery);
  }


  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock()
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatmentStock.CLASS);

    return this.isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery)
  {
    com.terraframe.mojo.query.QueryFactory queryFactory = this.getQueryFactory();
    com.terraframe.mojo.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(dss.vector.solutions.surveillance.CaseTreatmentStock.CLASS);
    relationshipQuery.AND(relationshipQuery.hasParent(aggregatedCaseQuery));

    return this.isNotChildIn(relationshipQuery);
  }

  public com.terraframe.mojo.query.Condition NOT_IN_caseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseQuery aggregatedCaseQuery, dss.vector.solutions.surveillance.CaseTreatmentStockQuery caseTreatmentStockQuery)
  {
    caseTreatmentStockQuery.AND(caseTreatmentStockQuery.hasParent(aggregatedCaseQuery));
    return this.isNotChildIn(caseTreatmentStockQuery);
  }

  }
}
