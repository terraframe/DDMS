package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1651570338)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ZoneSprayView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class ZoneSprayViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public ZoneSprayViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ZoneSprayViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.ZoneSprayView.CLASS;
  }
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand()
  {
    return getBrand(null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand(String alias)
  {

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.BRAND, alias, null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.BRAND, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDate()
  {
    return getSprayDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYDATE, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF getSprayMethod()
  {
    return getSprayMethod(null);

  }
 
  public dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF getSprayMethod(String alias)
  {
    return (dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYMETHOD, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF getSprayMethod(String alias, String displayLabel)
  {
    return (dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYMETHOD, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor()
  {
    return getSupervisor(null);

  }
 
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor(String alias)
  {

    return (dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SUPERVISOR, alias, null);

  }
 
  public dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF getSupervisor(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.SupervisorQuery.SupervisorQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SUPERVISOR, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfaceType()
  {
    return getSurfaceType(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfaceType(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SURFACETYPE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfaceType(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SURFACETYPE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends ZoneSprayView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<ZoneSprayView>(this.getMdClassIF(), valueIterator);
  }

}
