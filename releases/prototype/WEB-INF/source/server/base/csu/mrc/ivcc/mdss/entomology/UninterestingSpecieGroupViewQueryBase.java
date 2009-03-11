package csu.mrc.ivcc.mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UninterestingSpecieGroupView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class UninterestingSpecieGroupViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236803161174L;

  public UninterestingSpecieGroupViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public UninterestingSpecieGroupViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return "csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView";
  }
  public csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionQuery.AbstractMosquitoCollectionQueryReferenceIF getCollection()
  {
    return getCollection(null);

  }
 
  public csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionQuery.AbstractMosquitoCollectionQueryReferenceIF getCollection(String alias)
  {

    return (csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionQuery.AbstractMosquitoCollectionQueryReferenceIF)this.getSelectable(csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView.COLLECTION, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getGroupId()
  {
    return getGroupId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGroupId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView.GROUPID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView.ID, alias);

  }
 
  public csu.mrc.ivcc.mdss.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod(String alias)
  {

    return (csu.mrc.ivcc.mdss.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF)this.getSelectable(csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView.IDENTIFICATIONMETHOD, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView.QUANTITY, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getSampleId()
  {
    return getSampleId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getSampleId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView.SAMPLEID, alias);

  }
 
  public csu.mrc.ivcc.mdss.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie()
  {
    return getSpecie(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie(String alias)
  {

    return (csu.mrc.ivcc.mdss.mo.SpecieQuery.SpecieQueryReferenceIF)this.getSelectable(csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView.SPECIE, alias);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends UninterestingSpecieGroupView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<UninterestingSpecieGroupView>(this.getMdClassIF(), valueIterator);
  }

}
