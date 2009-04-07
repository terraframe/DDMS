package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultTestIntervalView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class AdultTestIntervalViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239075027033L;

  public AdultTestIntervalViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AdultTestIntervalViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return "dss.vector.solutions.entomology.assay.AdultTestIntervalView";
  }
  public dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF getAssay()
  {
    return getAssay(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF getAssay(String alias)
  {

    return (dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.AdultTestIntervalView.ASSAY, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.entomology.assay.AdultTestIntervalView.ID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getIntervalId()
  {
    return getIntervalId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getIntervalId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.entomology.assay.AdultTestIntervalView.INTERVALID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime()
  {
    return getIntervalTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.entomology.assay.AdultTestIntervalView.INTERVALTIME, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getKnockedDown()
  {
    return getKnockedDown(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getKnockedDown(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.entomology.assay.AdultTestIntervalView.KNOCKEDDOWN, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPeriod()
  {
    return getPeriod(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPeriod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.entomology.assay.AdultTestIntervalView.PERIOD, alias);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends AdultTestIntervalView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<AdultTestIntervalView>(this.getMdClassIF(), valueIterator);
  }

}
