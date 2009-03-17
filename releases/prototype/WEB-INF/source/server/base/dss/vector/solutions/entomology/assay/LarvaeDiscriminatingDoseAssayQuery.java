package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class LarvaeDiscriminatingDoseAssayQuery extends dss.vector.solutions.entomology.assay.DiscriminatingDoseAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237311434764L;

  public LarvaeDiscriminatingDoseAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay";
  }
  public dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReferenceIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReferenceIF getAgeRange(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("ageRange");

    return (dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.AGERANGE, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("ageRange")) 
    {
       return new dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends LarvaeDiscriminatingDoseAssay> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<LarvaeDiscriminatingDoseAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface LarvaeDiscriminatingDoseAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.DiscriminatingDoseAssayQuery.DiscriminatingDoseAssayQueryReferenceIF
  {

    public dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReferenceIF getAgeRange();
    public dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReferenceIF getAgeRange(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class LarvaeDiscriminatingDoseAssayQueryReference extends dss.vector.solutions.entomology.assay.DiscriminatingDoseAssayQuery.DiscriminatingDoseAssayQueryReference
 implements LarvaeDiscriminatingDoseAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237311434878L;

  public LarvaeDiscriminatingDoseAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay)
    {
      return this.EQ(larvaeDiscriminatingDoseAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay)
    {
      return this.NE(larvaeDiscriminatingDoseAssay.getId());
    }

  public dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReferenceIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReferenceIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReferenceIF)this.attributeFactory("ageRange", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("ageRange")) 
    {
       return new dss.vector.solutions.entomology.assay.LarvaeAgeRangeQuery.LarvaeAgeRangeQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
