package dss.vector.solutions.surveillance;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ChildCaseView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ChildCaseViewQueryBase extends dss.vector.solutions.surveillance.AggregatedCaseViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239658600104L;

  public ChildCaseViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ChildCaseViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return "dss.vector.solutions.surveillance.ChildCaseView";
  }
  public com.terraframe.mojo.query.AttributeIntegerIF getCases()
  {
    return getCases(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getCases(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.CASES, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getCasesFemale()
  {
    return getCasesFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getCasesFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.CASESFEMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getCasesMale()
  {
    return getCasesMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getCasesMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.CASESMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getCasesPregnant()
  {
    return getCasesPregnant(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getCasesPregnant(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.CASESPREGNANT, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getClinicallyDiagnosed()
  {
    return getClinicallyDiagnosed(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getClinicallyDiagnosed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.CLINICALLYDIAGNOSED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getClinicallyDiagnosedDeath()
  {
    return getClinicallyDiagnosedDeath(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getClinicallyDiagnosedDeath(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.CLINICALLYDIAGNOSEDDEATH, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDaysOutOfStock()
  {
    return getDaysOutOfStock(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDaysOutOfStock(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.DAYSOUTOFSTOCK, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDeaths()
  {
    return getDeaths(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDeaths(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.DEATHS, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDeathsFemale()
  {
    return getDeathsFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDeathsFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.DEATHSFEMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDeathsMale()
  {
    return getDeathsMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDeathsMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.DEATHSMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDeathsPregnant()
  {
    return getDeathsPregnant(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDeathsPregnant(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.DEATHSPREGNANT, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDefinitivelyDiagnosed()
  {
    return getDefinitivelyDiagnosed(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDefinitivelyDiagnosed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.DEFINITIVELYDIAGNOSED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDefinitivelyDiagnosedDeath()
  {
    return getDefinitivelyDiagnosedDeath(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getDefinitivelyDiagnosedDeath(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.DEFINITIVELYDIAGNOSEDDEATH, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatients()
  {
    return getInPatients(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatients(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTS, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsAnemia()
  {
    return getInPatientsAnemia(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsAnemia(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSANEMIA, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsClinically()
  {
    return getInPatientsClinically(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsClinically(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSCLINICALLY, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsDefinitive()
  {
    return getInPatientsDefinitive(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsDefinitive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSDEFINITIVE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsDischarged()
  {
    return getInPatientsDischarged(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsDischarged(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSDISCHARGED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsFemale()
  {
    return getInPatientsFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSFEMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsMale()
  {
    return getInPatientsMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsNotTreated()
  {
    return getInPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSNOTTREATED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsPregnantAnemia()
  {
    return getInPatientsPregnantAnemia(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsPregnantAnemia(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSPREGNANTANEMIA, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsPregnantDianosis()
  {
    return getInPatientsPregnantDianosis(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsPregnantDianosis(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSPREGNANTDIANOSIS, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsTotal()
  {
    return getInPatientsTotal(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getInPatientsTotal(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.INPATIENTSTOTAL, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatients()
  {
    return getOutPatients(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatients(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.OUTPATIENTS, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatientsFemale()
  {
    return getOutPatientsFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatientsFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.OUTPATIENTSFEMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatientsMale()
  {
    return getOutPatientsMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatientsMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.OUTPATIENTSMALE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatientsNotTreated()
  {
    return getOutPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.OUTPATIENTSNOTTREATED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatientsTotal()
  {
    return getOutPatientsTotal(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getOutPatientsTotal(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.OUTPATIENTSTOTAL, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPatientsNotTreated()
  {
    return getPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.PATIENTSNOTTREATED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPregnantDiagnosis()
  {
    return getPregnantDiagnosis(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPregnantDiagnosis(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.PREGNANTDIAGNOSIS, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPregnantDiagnosisDeath()
  {
    return getPregnantDiagnosisDeath(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPregnantDiagnosisDeath(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.PREGNANTDIAGNOSISDEATH, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPregnantReferralsReceived()
  {
    return getPregnantReferralsReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getPregnantReferralsReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.PREGNANTREFERRALSRECEIVED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getReferralsReceived()
  {
    return getReferralsReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getReferralsReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.REFERRALSRECEIVED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getReferralsSent()
  {
    return getReferralsSent(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getReferralsSent(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.REFERRALSSENT, alias);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getStillBirths()
  {
    return getStillBirths(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getStillBirths(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getSelectable(dss.vector.solutions.surveillance.ChildCaseView.STILLBIRTHS, alias);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ChildCaseView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ChildCaseView>(this.getMdClassIF(), valueIterator);
  }

}
