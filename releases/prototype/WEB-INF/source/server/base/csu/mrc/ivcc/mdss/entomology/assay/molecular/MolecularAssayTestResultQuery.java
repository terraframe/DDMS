package csu.mrc.ivcc.mdss.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MolecularAssayTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class MolecularAssayTestResultQuery extends csu.mrc.ivcc.mdss.entomology.assay.AssayTestResultQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237219376960L;

  public MolecularAssayTestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult";
  }
  public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult.TESTMETHOD, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("testMethod")) 
    {
       return new csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends MolecularAssayTestResult> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<MolecularAssayTestResult>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface MolecularAssayTestResultQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.entomology.assay.AssayTestResultQuery.AssayTestResultQueryReferenceIF
  {

    public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod();
    public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult molecularAssayTestResult);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult molecularAssayTestResult);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class MolecularAssayTestResultQueryReference extends csu.mrc.ivcc.mdss.entomology.assay.AssayTestResultQuery.AssayTestResultQueryReference
 implements MolecularAssayTestResultQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237219377058L;

  public MolecularAssayTestResultQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult molecularAssayTestResult)
    {
      return this.EQ(molecularAssayTestResult.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult molecularAssayTestResult)
    {
      return this.NE(molecularAssayTestResult.getId());
    }

  public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod(String alias)
  {
    return (csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF)this.attributeFactory("testMethod", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("testMethod")) 
    {
       return new csu.mrc.ivcc.mdss.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
