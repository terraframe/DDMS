package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 2131502730)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNHouseholdSurveyView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ITNHouseholdSurveyViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 2131502730;

  public ITNHouseholdSurveyViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ITNHouseholdSurveyViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getAgentFirstName()
  {
    return getAgentFirstName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentFirstName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.AGENTFIRSTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentFirstName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.AGENTFIRSTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentSurname()
  {
    return getAgentSurname(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentSurname(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.AGENTSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.AGENTSURNAME, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBoughtProvider()
  {
    return getBoughtProvider(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBoughtProvider(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.BOUGHTPROVIDER, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBoughtProvider(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.BOUGHTPROVIDER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getChildResidents()
  {
    return getChildResidents(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getChildResidents(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CHILDRESIDENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getChildResidents(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CHILDRESIDENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDamagedItns()
  {
    return getDamagedItns(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDamagedItns(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.DAMAGEDITNS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDamagedItns(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.DAMAGEDITNS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNets()
  {
    return getDisplayNets(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNets(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.DISPLAYNETS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNets(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.DISPLAYNETS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNonUseReasons()
  {
    return getDisplayNonUseReasons(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNonUseReasons(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.DISPLAYNONUSEREASONS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNonUseReasons(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.DISPLAYNONUSEREASONS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayTargetGroups()
  {
    return getDisplayTargetGroups(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayTargetGroups(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.DISPLAYTARGETGROUPS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayTargetGroups(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.DISPLAYTARGETGROUPS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getEndDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.ENDDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getEndDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.ENDDATE, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getFreeProvider()
  {
    return getFreeProvider(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getFreeProvider(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.FREEPROVIDER, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getFreeProvider(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.FREEPROVIDER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getHangingItns()
  {
    return getHangingItns(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHangingItns(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.HANGINGITNS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHangingItns(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.HANGINGITNS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getItns()
  {
    return getItns(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getItns(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.ITNS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getItns(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.ITNS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getKnowWashFrequency()
  {
    return getKnowWashFrequency(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getKnowWashFrequency(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.KNOWWASHFREQUENCY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getKnowWashFrequency(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.KNOWWASHFREQUENCY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMonthReceived()
  {
    return getMonthReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMonthReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.MONTHRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getMonthReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.MONTHRECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getNetsObtained()
  {
    return getNetsObtained(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getNetsObtained(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.NETSOBTAINED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getNetsObtained(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.NETSOBTAINED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOtherItns()
  {
    return getOtherItns(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOtherItns(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.OTHERITNS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOtherItns(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.OTHERITNS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantResidents()
  {
    return getPregnantResidents(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantResidents(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.PREGNANTRESIDENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantResidents(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.PREGNANTRESIDENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getQuestionnaireNumber()
  {
    return getQuestionnaireNumber(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getQuestionnaireNumber(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.QUESTIONNAIRENUMBER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getQuestionnaireNumber(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.QUESTIONNAIRENUMBER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getResidents()
  {
    return getResidents(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getResidents(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.RESIDENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getResidents(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.RESIDENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getRetreated()
  {
    return getRetreated(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getRetreated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.RETREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getRetreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.RETREATED, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRetreatmentPeriod()
  {
    return getRetreatmentPeriod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRetreatmentPeriod(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.RETREATMENTPERIOD, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRetreatmentPeriod(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.RETREATMENTPERIOD, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getStartDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.STARTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getStartDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.STARTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSurveyLocation()
  {
    return getSurveyLocation(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSurveyLocation(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.SURVEYLOCATION, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSurveyLocation(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.SURVEYLOCATION, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getUsedEveryNight()
  {
    return getUsedEveryNight(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getUsedEveryNight(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.USEDEVERYNIGHT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getUsedEveryNight(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.USEDEVERYNIGHT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsedItns()
  {
    return getUsedItns(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsedItns(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.USEDITNS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsedItns(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.USEDITNS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getWashFrequency()
  {
    return getWashFrequency(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getWashFrequency(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.WASHFREQUENCY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getWashFrequency(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.WASHFREQUENCY, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWashInterval()
  {
    return getWashInterval(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWashInterval(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.WASHINTERVAL, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWashInterval(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.WASHINTERVAL, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWashed()
  {
    return getWashed(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWashed(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.WASHED, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWashed(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.WASHED, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getYearReceived()
  {
    return getYearReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getYearReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.YEARRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getYearReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyView.YEARRECEIVED, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ITNHouseholdSurveyView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ITNHouseholdSurveyView>(this.getMdClassIF(), valueIterator);
  }

}
