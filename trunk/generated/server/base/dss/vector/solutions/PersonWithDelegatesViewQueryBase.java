package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -1710874848)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PersonWithDelegatesView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class PersonWithDelegatesViewQueryBase extends dss.vector.solutions.PersonViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1710874848;

  public PersonWithDelegatesViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public PersonWithDelegatesViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.PersonWithDelegatesView.CLASS;
  }
  public dss.vector.solutions.intervention.monitor.IPTRecipientQuery.IPTRecipientQueryReferenceIF getIptRecipientDelegate()
  {
    return getIptRecipientDelegate(null);

  }
 
  public dss.vector.solutions.intervention.monitor.IPTRecipientQuery.IPTRecipientQueryReferenceIF getIptRecipientDelegate(String alias)
  {

    return (dss.vector.solutions.intervention.monitor.IPTRecipientQuery.IPTRecipientQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.IPTRECIPIENTDELEGATE, alias, null);

  }
 
  public dss.vector.solutions.intervention.monitor.IPTRecipientQuery.IPTRecipientQueryReferenceIF getIptRecipientDelegate(String alias, String displayLabel)
  {

    return (dss.vector.solutions.intervention.monitor.IPTRecipientQuery.IPTRecipientQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.IPTRECIPIENTDELEGATE, alias, displayLabel);

  }
  public dss.vector.solutions.intervention.monitor.ITNRecipientQuery.ITNRecipientQueryReferenceIF getItnRecipientDelegate()
  {
    return getItnRecipientDelegate(null);

  }
 
  public dss.vector.solutions.intervention.monitor.ITNRecipientQuery.ITNRecipientQueryReferenceIF getItnRecipientDelegate(String alias)
  {

    return (dss.vector.solutions.intervention.monitor.ITNRecipientQuery.ITNRecipientQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.ITNRECIPIENTDELEGATE, alias, null);

  }
 
  public dss.vector.solutions.intervention.monitor.ITNRecipientQuery.ITNRecipientQueryReferenceIF getItnRecipientDelegate(String alias, String displayLabel)
  {

    return (dss.vector.solutions.intervention.monitor.ITNRecipientQuery.ITNRecipientQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.ITNRECIPIENTDELEGATE, alias, displayLabel);

  }
  public dss.vector.solutions.PatientQuery.PatientQueryReferenceIF getPatientDelegate()
  {
    return getPatientDelegate(null);

  }
 
  public dss.vector.solutions.PatientQuery.PatientQueryReferenceIF getPatientDelegate(String alias)
  {

    return (dss.vector.solutions.PatientQuery.PatientQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.PATIENTDELEGATE, alias, null);

  }
 
  public dss.vector.solutions.PatientQuery.PatientQueryReferenceIF getPatientDelegate(String alias, String displayLabel)
  {

    return (dss.vector.solutions.PatientQuery.PatientQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.PATIENTDELEGATE, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SprayLeaderQuery.SprayLeaderQueryReferenceIF getSprayLeaderDelegate()
  {
    return getSprayLeaderDelegate(null);

  }
 
  public dss.vector.solutions.irs.SprayLeaderQuery.SprayLeaderQueryReferenceIF getSprayLeaderDelegate(String alias)
  {

    return (dss.vector.solutions.irs.SprayLeaderQuery.SprayLeaderQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.SPRAYLEADERDELEGATE, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayLeaderQuery.SprayLeaderQueryReferenceIF getSprayLeaderDelegate(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.SprayLeaderQuery.SprayLeaderQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.SPRAYLEADERDELEGATE, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SprayOperatorQuery.SprayOperatorQueryReferenceIF getSprayOperatorDelegate()
  {
    return getSprayOperatorDelegate(null);

  }
 
  public dss.vector.solutions.irs.SprayOperatorQuery.SprayOperatorQueryReferenceIF getSprayOperatorDelegate(String alias)
  {

    return (dss.vector.solutions.irs.SprayOperatorQuery.SprayOperatorQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.SPRAYOPERATORDELEGATE, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayOperatorQuery.SprayOperatorQueryReferenceIF getSprayOperatorDelegate(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.SprayOperatorQuery.SprayOperatorQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.SPRAYOPERATORDELEGATE, alias, displayLabel);

  }
  public dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF getStockStaffDelegate()
  {
    return getStockStaffDelegate(null);

  }
 
  public dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF getStockStaffDelegate(String alias)
  {

    return (dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.STOCKSTAFFDELEGATE, alias, null);

  }
 
  public dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF getStockStaffDelegate(String alias, String displayLabel)
  {

    return (dss.vector.solutions.stock.StockStaffQuery.StockStaffQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.STOCKSTAFFDELEGATE, alias, displayLabel);

  }
  public dss.vector.solutions.MDSSUserQuery.MDSSUserQueryReferenceIF getUserDelegate()
  {
    return getUserDelegate(null);

  }
 
  public dss.vector.solutions.MDSSUserQuery.MDSSUserQueryReferenceIF getUserDelegate(String alias)
  {

    return (dss.vector.solutions.MDSSUserQuery.MDSSUserQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.USERDELEGATE, alias, null);

  }
 
  public dss.vector.solutions.MDSSUserQuery.MDSSUserQueryReferenceIF getUserDelegate(String alias, String displayLabel)
  {

    return (dss.vector.solutions.MDSSUserQuery.MDSSUserQueryReferenceIF)this.getSelectable(dss.vector.solutions.PersonWithDelegatesView.USERDELEGATE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends PersonWithDelegatesView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<PersonWithDelegatesView>(this.getMdClassIF(), valueIterator);
  }

}
