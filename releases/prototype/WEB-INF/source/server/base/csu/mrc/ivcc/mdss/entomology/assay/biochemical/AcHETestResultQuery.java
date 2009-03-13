package csu.mrc.ivcc.mdss.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AcHETestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AcHETestResultQuery extends csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236982467444L;

  public AcHETestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResult";
  }
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReferenceIF getTestResult()
  {
    return getTestResult(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReferenceIF getTestResult(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testResult");

    return (csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResult.TESTRESULT, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("testResult")) 
    {
       return new csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends AcHETestResult> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<AcHETestResult>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AcHETestResultQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultQuery.BiochemicalAssayTestResultQueryReferenceIF
  {

    public csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReferenceIF getTestResult();
    public csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReferenceIF getTestResult(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResult acHETestResult);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResult acHETestResult);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AcHETestResultQueryReference extends csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultQuery.BiochemicalAssayTestResultQueryReference
 implements AcHETestResultQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1236982467557L;

  public AcHETestResultQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResult acHETestResult)
    {
      return this.EQ(acHETestResult.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResult acHETestResult)
    {
      return this.NE(acHETestResult.getId());
    }

  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReferenceIF getTestResult()
  {
    return getTestResult(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReferenceIF getTestResult(String alias)
  {
    return (csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReferenceIF)this.attributeFactory("testResult", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("testResult")) 
    {
       return new csu.mrc.ivcc.mdss.mo.MolecularAssayResultQuery.MolecularAssayResultQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
