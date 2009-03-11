package csu.mrc.ivcc.mdss.entomology.assay.infectivity;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InfectivityAssayTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class InfectivityAssayTestResultQuery extends csu.mrc.ivcc.mdss.entomology.assay.AssayTestResultQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236803166690L;

  public InfectivityAssayTestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResult";
  }
  public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReferenceIF getTestMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResult.TESTMETHOD, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("testMethod")) 
    {
       return new csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends InfectivityAssayTestResult> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<InfectivityAssayTestResult>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface InfectivityAssayTestResultQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.entomology.assay.AssayTestResultQuery.AssayTestResultQueryReferenceIF
  {

    public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReferenceIF getTestMethod();
    public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReferenceIF getTestMethod(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResult infectivityAssayTestResult);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResult infectivityAssayTestResult);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class InfectivityAssayTestResultQueryReference extends csu.mrc.ivcc.mdss.entomology.assay.AssayTestResultQuery.AssayTestResultQueryReference
 implements InfectivityAssayTestResultQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1236803166790L;

  public InfectivityAssayTestResultQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResult infectivityAssayTestResult)
    {
      return this.EQ(infectivityAssayTestResult.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResult infectivityAssayTestResult)
    {
      return this.NE(infectivityAssayTestResult.getId());
    }

  public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReferenceIF getTestMethod(String alias)
  {
    return (csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReferenceIF)this.attributeFactory("testMethod", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("testMethod")) 
    {
       return new csu.mrc.ivcc.mdss.mo.InfectivityMethodologyQuery.InfectivityMethodologyQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
