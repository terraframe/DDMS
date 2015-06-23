package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1721966329)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SurveyPointView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class SurveyPointViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public SurveyPointViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public SurveyPointViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.SurveyPointView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyPointView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyPointView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId()
  {
    return getGeoId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyPointView.GEOID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyPointView.GEOID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyPointView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyPointView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getSurveyDate()
  {
    return getSurveyDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSurveyDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyPointView.SURVEYDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSurveyDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyPointView.SURVEYDATE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends SurveyPointView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<SurveyPointView>(this.getMdClassIF(), valueIterator);
  }

}
