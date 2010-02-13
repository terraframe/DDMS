package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 1369703170)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EpiDate.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class EpiDateQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1369703170;

  public EpiDateQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public EpiDateQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.general.EpiDate.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getEndDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.general.EpiDate.ENDDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getEndDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.general.EpiDate.ENDDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getEpiYear()
  {
    return getEpiYear(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getEpiYear(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.general.EpiDate.EPIYEAR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getEpiYear(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.general.EpiDate.EPIYEAR, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.general.EpiDate.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.general.EpiDate.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPeriod()
  {
    return getPeriod(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPeriod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.general.EpiDate.PERIOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPeriod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.general.EpiDate.PERIOD, alias, displayLabel);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType()
  {
    return getPeriodType(null);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType(String alias)
  {
    return (dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF)this.getSelectable(dss.vector.solutions.general.EpiDate.PERIODTYPE, alias, null);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType(String alias, String displayLabel)
  {
    return (dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF)this.getSelectable(dss.vector.solutions.general.EpiDate.PERIODTYPE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.general.EpiDate.STARTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.general.EpiDate.STARTDATE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends EpiDate> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<EpiDate>(this.getMdClassIF(), valueIterator);
  }

}
