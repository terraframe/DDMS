package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class LarvaeAssayQuery extends dss.vector.solutions.entomology.assay.CollectionAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1238826350324L;

  public LarvaeAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.assay.LarvaeAssay";
  }
  public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getEndPoint()
  {
    return getEndPoint(null);

  }
 
  public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getEndPoint(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("endPoint");

    return (dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.LarvaeAssay.ENDPOINT, mdAttributeIF, this, alias);

  }
  public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getStartPoint()
  {
    return getStartPoint(null);

  }
 
  public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getStartPoint(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("startPoint");

    return (dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.LarvaeAssay.STARTPOINT, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("endPoint")) 
    {
       return new dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("startPoint")) 
    {
       return new dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends LarvaeAssay> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<LarvaeAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface LarvaeAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.CollectionAssayQuery.CollectionAssayQueryReferenceIF
  {

    public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getEndPoint();
    public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getEndPoint(String alias);
    public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getStartPoint();
    public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getStartPoint(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.LarvaeAssay larvaeAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.LarvaeAssay larvaeAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class LarvaeAssayQueryReference extends dss.vector.solutions.entomology.assay.CollectionAssayQuery.CollectionAssayQueryReference
 implements LarvaeAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1238826350477L;

  public LarvaeAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.LarvaeAssay larvaeAssay)
    {
      return this.EQ(larvaeAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.LarvaeAssay larvaeAssay)
    {
      return this.NE(larvaeAssay.getId());
    }

  public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getEndPoint()
  {
    return getEndPoint(null);

  }
 
  public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getEndPoint(String alias)
  {
    return (dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF)this.attributeFactory("endPoint", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getStartPoint()
  {
    return getStartPoint(null);

  }
 
  public dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF getStartPoint(String alias)
  {
    return (dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReferenceIF)this.attributeFactory("startPoint", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("endPoint")) 
    {
       return new dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else if (name.equals("startPoint")) 
    {
       return new dss.vector.solutions.mo.LarvaeAgeQuery.LarvaeAgeQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
