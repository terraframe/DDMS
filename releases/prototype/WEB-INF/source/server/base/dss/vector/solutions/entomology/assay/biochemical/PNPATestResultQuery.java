package dss.vector.solutions.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PNPATestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class PNPATestResultQuery extends dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1238826355481L;

  public PNPATestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.assay.biochemical.PNPATestResult";
  }
  public com.terraframe.mojo.query.AttributeBooleanIF getTestResult()
  {
    return getTestResult(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getTestResult(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.biochemical.PNPATestResult.TESTRESULT, "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends PNPATestResult> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<PNPATestResult>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface PNPATestResultQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultQuery.MetabolicAssayTestResultQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeBooleanIF getTestResult();
    public com.terraframe.mojo.query.AttributeBooleanIF getTestResult(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.biochemical.PNPATestResult pNPATestResult);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.biochemical.PNPATestResult pNPATestResult);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class PNPATestResultQueryReference extends dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultQuery.MetabolicAssayTestResultQueryReference
 implements PNPATestResultQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1238826355635L;

  public PNPATestResultQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.biochemical.PNPATestResult pNPATestResult)
    {
      return this.EQ(pNPATestResult.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.biochemical.PNPATestResult pNPATestResult)
    {
      return this.NE(pNPATestResult.getId());
    }

  public com.terraframe.mojo.query.AttributeBooleanIF getTestResult()
  {
    return getTestResult(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getTestResult(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.attributeFactory("testResult", "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias);

  }
  }
}
