package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -998440624)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedIPTExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class AggregatedIPTExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -998440624;

  public AggregatedIPTExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AggregatedIPTExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.AggregatedIPTExcelView.CLASS;
  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberNatalCare()
  {
    return getNumberNatalCare(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberNatalCare(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.NUMBERNATALCARE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberNatalCare(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.NUMBERNATALCARE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnant()
  {
    return getNumberPregnant(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnant(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.NUMBERPREGNANT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnant(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.NUMBERPREGNANT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnantITN()
  {
    return getNumberPregnantITN(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnantITN(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.NUMBERPREGNANTITN, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnantITN(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.NUMBERPREGNANTITN, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnantIron()
  {
    return getNumberPregnantIron(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnantIron(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.NUMBERPREGNANTIRON, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberPregnantIron(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.NUMBERPREGNANTIRON, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeriod()
  {
    return getPeriod(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeriod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.PERIOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeriod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.PERIOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPeriodType()
  {
    return getPeriodType(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPeriodType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.PERIODTYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPeriodType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.PERIODTYPE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeriodYear()
  {
    return getPeriodYear(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeriodYear(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.PERIODYEAR, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeriodYear(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.PERIODYEAR, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTotalITN()
  {
    return getTotalITN(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTotalITN(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.TOTALITN, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTotalITN(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.AggregatedIPTExcelView.TOTALITN, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends AggregatedIPTExcelView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<AggregatedIPTExcelView>(this.getMdClassIF(), valueIterator);
  }

}
