package dss.vector.solutions.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TargetSiteAssayTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class TargetSiteAssayTestResultQuery extends dss.vector.solutions.entomology.assay.AssayTestResultQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1238826382144L;

  public TargetSiteAssayTestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult";
  }
  public dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("testMethod");

    return (dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult.TESTMETHOD, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("testMethod")) 
    {
       return new dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends TargetSiteAssayTestResult> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<TargetSiteAssayTestResult>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface TargetSiteAssayTestResultQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.AssayTestResultQuery.AssayTestResultQueryReferenceIF
  {

    public dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod();
    public dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult targetSiteAssayTestResult);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult targetSiteAssayTestResult);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class TargetSiteAssayTestResultQueryReference extends dss.vector.solutions.entomology.assay.AssayTestResultQuery.AssayTestResultQueryReference
 implements TargetSiteAssayTestResultQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1238826382294L;

  public TargetSiteAssayTestResultQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult targetSiteAssayTestResult)
    {
      return this.EQ(targetSiteAssayTestResult.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult targetSiteAssayTestResult)
    {
      return this.NE(targetSiteAssayTestResult.getId());
    }

  public dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF getTestMethod(String alias)
  {
    return (dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReferenceIF)this.attributeFactory("testMethod", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("testMethod")) 
    {
       return new dss.vector.solutions.mo.InsecticideMethodologyQuery.InsecticideMethodologyQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
