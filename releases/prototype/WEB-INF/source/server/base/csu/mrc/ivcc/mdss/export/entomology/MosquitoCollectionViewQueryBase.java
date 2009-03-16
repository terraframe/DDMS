package csu.mrc.ivcc.mdss.export.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class MosquitoCollectionViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237219384180L;

  public MosquitoCollectionViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MosquitoCollectionViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return "csu.mrc.ivcc.mdss.export.entomology.MosquitoCollectionView";
  }
  public com.terraframe.mojo.query.AttributeCharIF getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getCollectionMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(csu.mrc.ivcc.mdss.export.entomology.MosquitoCollectionView.COLLECTIONMETHOD, alias);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getDateCollected()
  {
    return getDateCollected(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getDateCollected(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getSelectable(csu.mrc.ivcc.mdss.export.entomology.MosquitoCollectionView.DATECOLLECTED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGeoEntity(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(csu.mrc.ivcc.mdss.export.entomology.MosquitoCollectionView.GEOENTITY, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(csu.mrc.ivcc.mdss.export.entomology.MosquitoCollectionView.ID, alias);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends MosquitoCollectionView> getIterator()
  {
    com.terraframe.mojo.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.terraframe.mojo.query.ViewIterator<MosquitoCollectionView>(this.getMdClassIF(), valueIterator);
  }

}
