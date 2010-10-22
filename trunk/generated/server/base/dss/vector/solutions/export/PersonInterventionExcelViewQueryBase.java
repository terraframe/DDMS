package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -1418136111)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PersonInterventionExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class PersonInterventionExcelViewQueryBase extends dss.vector.solutions.export.ControlInterventionExcelViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1418136111;

  public PersonInterventionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public PersonInterventionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.PersonInterventionExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getVehicleDays()
  {
    return getVehicleDays(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVehicleDays(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PersonInterventionExcelView.VEHICLEDAYS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVehicleDays(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PersonInterventionExcelView.VEHICLEDAYS, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends PersonInterventionExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<PersonInterventionExcelView>(this.getMdClassIF(), valueIterator);
  }

}
