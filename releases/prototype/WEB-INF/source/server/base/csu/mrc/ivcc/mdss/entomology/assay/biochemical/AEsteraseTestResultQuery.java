package csu.mrc.ivcc.mdss.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AEsteraseTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AEsteraseTestResultQuery extends csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236360385065L;

  public AEsteraseTestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResult";
  }
  public com.terraframe.mojo.query.AttributeIntegerIF getTestResult()
  {
    return getTestResult(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getTestResult(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResult.TESTRESULT, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends AEsteraseTestResult> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<AEsteraseTestResult>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AEsteraseTestResultQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultQuery.BiochemicalAssayTestResultQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeIntegerIF getTestResult();
    public com.terraframe.mojo.query.AttributeIntegerIF getTestResult(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResult aEsteraseTestResult);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResult aEsteraseTestResult);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AEsteraseTestResultQueryReference extends csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultQuery.BiochemicalAssayTestResultQueryReference
 implements AEsteraseTestResultQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1236360385164L;

  public AEsteraseTestResultQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResult aEsteraseTestResult)
    {
      return this.EQ(aEsteraseTestResult.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResult aEsteraseTestResult)
    {
      return this.NE(aEsteraseTestResult.getId());
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
