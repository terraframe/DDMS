package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = 790656908)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EfficacyAssayView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class EfficacyAssayViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 790656908;

  public EfficacyAssayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public EfficacyAssayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.EfficacyAssayView.CLASS;
  }
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.AGERANGE, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.AGERANGE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getColonyName()
  {
    return getColonyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getColonyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.COLONYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getColonyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.COLONYNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime()
  {
    return getExposureTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.EXPOSURETIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getExposureTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.EXPOSURETIME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.FED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getFed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.FED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeoId()
  {
    return getGeoId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeoId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.GEOID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeoId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.GEOID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.GRAVID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getGravid(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.GRAVID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.HOLDINGTIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.HOLDINGTIME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.general.InsecticideQuery.InsecticideQueryReferenceIF getInsecticide()
  {
    return getInsecticide(null);

  }
 
  public dss.vector.solutions.general.InsecticideQuery.InsecticideQueryReferenceIF getInsecticide(String alias)
  {

    return (dss.vector.solutions.general.InsecticideQuery.InsecticideQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.INSECTICIDE, alias, null);

  }
 
  public dss.vector.solutions.general.InsecticideQuery.InsecticideQueryReferenceIF getInsecticide(String alias, String displayLabel)
  {

    return (dss.vector.solutions.general.InsecticideQuery.InsecticideQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.INSECTICIDE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.MORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.MORTALITY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.QUANTITYDEAD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.QUANTITYDEAD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.QUANTITYLIVE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.QUANTITYLIVE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested()
  {
    return getQuantityTested(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.QUANTITYTESTED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityTested(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.QUANTITYTESTED, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.SEX, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.SEX, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecie()
  {
    return getSpecie(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecie(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.SPECIE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecie(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.SPECIE, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion()
  {
    return getSurfacePostion(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.SURFACEPOSTION, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfacePostion(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.SURFACEPOSTION, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.TESTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getTestDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.TESTDATE, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.TESTMETHOD, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.TESTMETHOD, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface()
  {
    return getTimeOnSurface(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.TIMEONSURFACE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTimeOnSurface(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.entomology.assay.EfficacyAssayView.TIMEONSURFACE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends EfficacyAssayView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<EfficacyAssayView>(this.getMdClassIF(), valueIterator);
  }

}
