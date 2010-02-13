package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 2005246564)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ZoneSprayView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ZoneSprayViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 2005246564;

  public ZoneSprayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ZoneSprayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
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
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.CONCRETEID, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getSprayDate()
  {
    return getSprayDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getSprayDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getSprayDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYDATE, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableSingleInteger getSprayWeek()
  {
    return getSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.SPRAYWEEK, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableSingleInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.irs.ZoneSprayView.TARGET, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ZoneSprayView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ZoneSprayView>(this.getMdClassIF(), valueIterator);
  }

}
