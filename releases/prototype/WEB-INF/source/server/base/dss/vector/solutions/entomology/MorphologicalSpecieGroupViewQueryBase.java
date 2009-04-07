package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MorphologicalSpecieGroupView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class MorphologicalSpecieGroupViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239075022908L;

  public MorphologicalSpecieGroupViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MorphologicalSpecieGroupViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return "dss.vector.solutions.entomology.MorphologicalSpecieGroupView";
  }
  public dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF getCollection()
  {
    return getCollection(null);

  }
 
  public dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF getCollection(String alias)
  {

    return (dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.COLLECTION, alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getDateCollected()
  {
    return getDateCollected(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getDateCollected(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.DATECOLLECTED, alias);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.GEOENTITY, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getGroupId()
  {
    return getGroupId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGroupId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.GROUPID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.ID, alias);

  }
 
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod()
  {
    return getIdentificationMethod(null);

  }
 
  public dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF getIdentificationMethod(String alias)
  {

    return (dss.vector.solutions.mo.IdentificationMethodQuery.IdentificationMethodQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.IDENTIFICATIONMETHOD, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.QUANTITY, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityFemale()
  {
    return getQuantityFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.QUANTITYFEMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityMale()
  {
    return getQuantityMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.QUANTITYMALE, alias);

  }
 
  public dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie()
  {
    return getSpecie(null);

  }
 
  public dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF getSpecie(String alias)
  {

    return (dss.vector.solutions.mo.SpecieQuery.SpecieQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MorphologicalSpecieGroupView.SPECIE, alias);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends MorphologicalSpecieGroupView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<MorphologicalSpecieGroupView>(this.getMdClassIF(), valueIterator);
  }

}
