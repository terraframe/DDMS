package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1524313633)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideNozzleView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class InsecticideNozzleViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1524313633;

  public InsecticideNozzleViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public InsecticideNozzleViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.InsecticideNozzleView.CLASS;
  }
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand()
  {
    return getBrand(null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand(String alias)
  {

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.BRAND, alias, null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.BRAND, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getBrandLabel()
  {
    return getBrandLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getBrandLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.BRANDLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getBrandLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.BRANDLABEL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getEnabled()
  {
    return getEnabled(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getEnabled(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.ENABLED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getEnabled(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.ENABLED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideNozzleId()
  {
    return getInsecticideNozzleId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideNozzleId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.INSECTICIDENOZZLEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getInsecticideNozzleId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.INSECTICIDENOZZLEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.NozzleQuery.NozzleQueryReferenceIF getNozzle()
  {
    return getNozzle(null);

  }
 
  public dss.vector.solutions.irs.NozzleQuery.NozzleQueryReferenceIF getNozzle(String alias)
  {

    return (dss.vector.solutions.irs.NozzleQuery.NozzleQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.NOZZLE, alias, null);

  }
 
  public dss.vector.solutions.irs.NozzleQuery.NozzleQueryReferenceIF getNozzle(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.NozzleQuery.NozzleQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.NOZZLE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getNozzleLabel()
  {
    return getNozzleLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getNozzleLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.NOZZLELABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getNozzleLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.InsecticideNozzleView.NOZZLELABEL, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends InsecticideNozzleView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<InsecticideNozzleView>(this.getMdClassIF(), valueIterator);
  }

}
