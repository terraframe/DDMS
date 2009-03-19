package dss.vector.solutions.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BEsteraseTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class BEsteraseTestResultQuery extends dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResultQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237423122129L;

  public BEsteraseTestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult";
  }
  public com.terraframe.mojo.query.AttributeIntegerIF getTestResult()
  {
    return getTestResult(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getTestResult(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult.TESTRESULT, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends BEsteraseTestResult> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<BEsteraseTestResult>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface BEsteraseTestResultQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResultQuery.BiochemicalAssayTestResultQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeIntegerIF getTestResult();
    public com.terraframe.mojo.query.AttributeIntegerIF getTestResult(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult bEsteraseTestResult);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult bEsteraseTestResult);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class BEsteraseTestResultQueryReference extends dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResultQuery.BiochemicalAssayTestResultQueryReference
 implements BEsteraseTestResultQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237423122225L;

  public BEsteraseTestResultQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult bEsteraseTestResult)
    {
      return this.EQ(bEsteraseTestResult.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult bEsteraseTestResult)
    {
      return this.NE(bEsteraseTestResult.getId());
    }

  public com.terraframe.mojo.query.AttributeIntegerIF getTestResult()
  {
    return getTestResult(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getTestResult(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("testResult", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  }
}
