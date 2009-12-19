package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = -882995552)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MalariaSeasonView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class MalariaSeasonViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -882995552;

  public MalariaSeasonViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MalariaSeasonViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.general.MalariaSeasonView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getEndDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.general.MalariaSeasonView.ENDDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getEndDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.general.MalariaSeasonView.ENDDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.general.MalariaSeasonView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.general.MalariaSeasonView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSeasonName()
  {
    return getSeasonName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSeasonName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.general.MalariaSeasonView.SEASONNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSeasonName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.general.MalariaSeasonView.SEASONNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getStartDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.general.MalariaSeasonView.STARTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getStartDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.general.MalariaSeasonView.STARTDATE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends MalariaSeasonView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<MalariaSeasonView>(this.getMdClassIF(), valueIterator);
  }

}
