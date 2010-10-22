package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 25320404)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideInterventionExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class InsecticideInterventionExcelViewQueryBase extends dss.vector.solutions.export.ControlInterventionExcelViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 25320404;

  public InsecticideInterventionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public InsecticideInterventionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.InsecticideInterventionExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getInsecticide()
  {
    return getInsecticide(null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticide(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.InsecticideInterventionExcelView.INSECTICIDE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticide(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.InsecticideInterventionExcelView.INSECTICIDE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getInterventionMethod()
  {
    return getInterventionMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getInterventionMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.InsecticideInterventionExcelView.INTERVENTIONMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getInterventionMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.InsecticideInterventionExcelView.INTERVENTIONMETHOD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantity(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.InsecticideInterventionExcelView.QUANTITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantity(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.InsecticideInterventionExcelView.QUANTITY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getUnit()
  {
    return getUnit(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUnit(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.InsecticideInterventionExcelView.UNIT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUnit(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.InsecticideInterventionExcelView.UNIT, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends InsecticideInterventionExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<InsecticideInterventionExcelView>(this.getMdClassIF(), valueIterator);
  }

}
