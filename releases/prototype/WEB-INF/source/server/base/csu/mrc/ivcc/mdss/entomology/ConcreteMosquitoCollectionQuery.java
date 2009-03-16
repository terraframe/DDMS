package csu.mrc.ivcc.mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ConcreteMosquitoCollection.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class ConcreteMosquitoCollectionQuery extends csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237219392564L;

  public ConcreteMosquitoCollectionQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection";
  }
  public com.terraframe.mojo.query.AttributeMomentIF getDateCollected()
  {
    return getDateCollected(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getDateCollected(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection.DATECOLLECTED, "com.terraframe.mojo.system.metadata.MdAttributeDate", alias);

  }
  public csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("geoEntity");

    return (csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection.GEOENTITY, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("geoEntity")) 
    {
       return new csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends ConcreteMosquitoCollection> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<ConcreteMosquitoCollection>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface ConcreteMosquitoCollectionQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionQuery.AbstractMosquitoCollectionQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeMomentIF getDateCollected();
    public com.terraframe.mojo.query.AttributeMomentIF getDateCollected(String alias);
    public csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity();
    public csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection concreteMosquitoCollection);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection concreteMosquitoCollection);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class ConcreteMosquitoCollectionQueryReference extends csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionQuery.AbstractMosquitoCollectionQueryReference
 implements ConcreteMosquitoCollectionQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237219392674L;

  public ConcreteMosquitoCollectionQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection concreteMosquitoCollection)
    {
      return this.EQ(concreteMosquitoCollection.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollection concreteMosquitoCollection)
    {
      return this.NE(concreteMosquitoCollection.getId());
    }

  public com.terraframe.mojo.query.AttributeMomentIF getDateCollected()
  {
    return getDateCollected(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getDateCollected(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.attributeFactory("dateCollected", "com.terraframe.mojo.system.metadata.MdAttributeDate", alias);

  }
  public csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {
    return (csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.attributeFactory("geoEntity", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("geoEntity")) 
    {
       return new csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
