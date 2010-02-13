package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -1325149596)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SurveyedPersonView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class SurveyedPersonViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1325149596;

  public SurveyedPersonViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public SurveyedPersonViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.SurveyedPersonView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleInteger getAge()
  {
    return getAge(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getAge(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.AGE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getAge(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.AGE, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getAnaemiaTreatment()
  {
    return getAnaemiaTreatment(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getAnaemiaTreatment(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.ANAEMIATREATMENT, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getAnaemiaTreatment(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.ANAEMIATREATMENT, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBloodslideDetail()
  {
    return getBloodslideDetail(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBloodslideDetail(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.BLOODSLIDEDETAIL, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBloodslideDetail(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.BLOODSLIDEDETAIL, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBloodslideReason()
  {
    return getBloodslideReason(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBloodslideReason(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.BLOODSLIDEREASON, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getBloodslideReason(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.BLOODSLIDEREASON, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleBoolean getBloodslideResult()
  {
    return getBloodslideResult(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getBloodslideResult(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.BLOODSLIDERESULT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getBloodslideResult(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.BLOODSLIDERESULT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayLocations()
  {
    return getDisplayLocations(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayLocations(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.DISPLAYLOCATIONS, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayLocations(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.DISPLAYLOCATIONS, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTreatments()
  {
    return getDisplayTreatments(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTreatments(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.DISPLAYTREATMENTS, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTreatments(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.DISPLAYTREATMENTS, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleMoment getDob()
  {
    return getDob(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getDob(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.DOB, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getDob(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.DOB, alias, displayLabel);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getFever()
  {
    return getFever(null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getFever(String alias)
  {
    return (dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.FEVER, alias, null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getFever(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.FEVER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleDecimal getHaemoglobin()
  {
    return getHaemoglobin(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDecimal getHaemoglobin(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDecimal)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.HAEMOGLOBIN, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDecimal getHaemoglobin(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDecimal)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.HAEMOGLOBIN, alias, displayLabel);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF getHaemoglobinMeasured()
  {
    return getHaemoglobinMeasured(null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF getHaemoglobinMeasured(String alias)
  {
    return (dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.HAEMOGLOBINMEASURED, alias, null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF getHaemoglobinMeasured(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.HAEMOGLOBINMEASURED, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getHeadOfHousehold()
  {
    return getHeadOfHousehold(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getHeadOfHousehold(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.HEADOFHOUSEHOLD, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getHeadOfHousehold(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.HEADOFHOUSEHOLD, alias, displayLabel);

  }
  public dss.vector.solutions.intervention.monitor.HouseholdQuery.HouseholdQueryReferenceIF getHousehold()
  {
    return getHousehold(null);

  }
 
  public dss.vector.solutions.intervention.monitor.HouseholdQuery.HouseholdQueryReferenceIF getHousehold(String alias)
  {

    return (dss.vector.solutions.intervention.monitor.HouseholdQuery.HouseholdQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.HOUSEHOLD, alias, null);

  }
 
  public dss.vector.solutions.intervention.monitor.HouseholdQuery.HouseholdQueryReferenceIF getHousehold(String alias, String displayLabel)
  {

    return (dss.vector.solutions.intervention.monitor.HouseholdQuery.HouseholdQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.HOUSEHOLD, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getImmuneCompromised()
  {
    return getImmuneCompromised(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getImmuneCompromised(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.IMMUNECOMPROMISED, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getImmuneCompromised(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.IMMUNECOMPROMISED, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleBoolean getIron()
  {
    return getIron(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getIron(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.IRON, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getIron(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.IRON, alias, displayLabel);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getMalaria()
  {
    return getMalaria(null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getMalaria(String alias)
  {
    return (dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.MALARIA, alias, null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getMalaria(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.MALARIA, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getMalariaConformationTechnique()
  {
    return getMalariaConformationTechnique(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getMalariaConformationTechnique(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.MALARIACONFORMATIONTECHNIQUE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getMalariaConformationTechnique(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.MALARIACONFORMATIONTECHNIQUE, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getPayment()
  {
    return getPayment(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getPayment(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PAYMENT, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getPayment(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PAYMENT, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleBoolean getPerformedBloodslide()
  {
    return getPerformedBloodslide(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getPerformedBloodslide(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PERFORMEDBLOODSLIDE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getPerformedBloodslide(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PERFORMEDBLOODSLIDE, alias, displayLabel);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF getPerformedRDT()
  {
    return getPerformedRDT(null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF getPerformedRDT(String alias)
  {
    return (dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PERFORMEDRDT, alias, null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF getPerformedRDT(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ResponseMasterQuery.RefusedResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PERFORMEDRDT, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getPersonId()
  {
    return getPersonId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getPersonId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PERSONID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getPersonId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PERSONID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getPregnant()
  {
    return getPregnant(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getPregnant(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PREGNANT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getPregnant(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.PREGNANT, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRdtDetail()
  {
    return getRdtDetail(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRdtDetail(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.RDTDETAIL, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRdtDetail(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.RDTDETAIL, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleBoolean getRdtResult()
  {
    return getRdtResult(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getRdtResult(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.RDTRESULT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getRdtResult(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.RDTRESULT, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRdtTreatment()
  {
    return getRdtTreatment(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRdtTreatment(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.RDTTREATMENT, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRdtTreatment(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.RDTTREATMENT, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.SEX, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.SEX, alias, displayLabel);

  }
  public dss.vector.solutions.intervention.monitor.ITNInstanceQuery.ITNInstanceQueryReferenceIF getSleptUnderNet()
  {
    return getSleptUnderNet(null);

  }
 
  public dss.vector.solutions.intervention.monitor.ITNInstanceQuery.ITNInstanceQueryReferenceIF getSleptUnderNet(String alias)
  {

    return (dss.vector.solutions.intervention.monitor.ITNInstanceQuery.ITNInstanceQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.SLEPTUNDERNET, alias, null);

  }
 
  public dss.vector.solutions.intervention.monitor.ITNInstanceQuery.ITNInstanceQueryReferenceIF getSleptUnderNet(String alias, String displayLabel)
  {

    return (dss.vector.solutions.intervention.monitor.ITNInstanceQuery.ITNInstanceQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.SurveyedPersonView.SLEPTUNDERNET, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends SurveyedPersonView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<SurveyedPersonView>(this.getMdClassIF(), valueIterator);
  }

}
