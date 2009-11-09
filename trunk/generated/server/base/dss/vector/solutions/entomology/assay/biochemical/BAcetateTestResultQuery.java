package dss.vector.solutions.entomology.assay.biochemical;

@com.terraframe.mojo.business.ClassSignature(hash = -1164793598)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BAcetateTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class BAcetateTestResultQuery extends dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1164793598;

  public BAcetateTestResultQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return dss.vector.solutions.entomology.assay.biochemical.BAcetateTestResult.CLASS;
  }
  public com.terraframe.mojo.query.AttributeBoolean getTestResult()
  {
    return getTestResult(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getTestResult(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.biochemical.BAcetateTestResult.TESTRESULT, "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getTestResult(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.biochemical.BAcetateTestResult.TESTRESULT, "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends BAcetateTestResult> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<BAcetateTestResult>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface BAcetateTestResultQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultQuery.MetabolicAssayTestResultQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeBoolean getTestResult();
    public com.terraframe.mojo.query.AttributeBoolean getTestResult(String alias);
    public com.terraframe.mojo.query.AttributeBoolean getTestResult(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.biochemical.BAcetateTestResult bAcetateTestResult);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.biochemical.BAcetateTestResult bAcetateTestResult);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class BAcetateTestResultQueryReference extends dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultQuery.MetabolicAssayTestResultQueryReference
 implements BAcetateTestResultQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1207906980;

  public BAcetateTestResultQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.biochemical.BAcetateTestResult bAcetateTestResult)
    {
      return this.EQ(bAcetateTestResult.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.biochemical.BAcetateTestResult bAcetateTestResult)
    {
      return this.NE(bAcetateTestResult.getId());
    }

  public com.terraframe.mojo.query.AttributeBoolean getTestResult()
  {
    return getTestResult(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getTestResult(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.attributeFactory("testResult", "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getTestResult(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.attributeFactory("testResult", "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias, displayLabel);

  }
  }
}
