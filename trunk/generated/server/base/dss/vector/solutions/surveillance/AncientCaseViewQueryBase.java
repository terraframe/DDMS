package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = -1677125126)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AncientCaseView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class AncientCaseViewQueryBase extends dss.vector.solutions.surveillance.AggregatedCaseViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1677125126;

  public AncientCaseViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AncientCaseViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.surveillance.AncientCaseView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeBoolean getCaseDiagnostic()
  {
    return getCaseDiagnostic(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseDiagnostic(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASEDIAGNOSTIC, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseDiagnostic(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASEDIAGNOSTIC, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseReferrals()
  {
    return getCaseReferrals(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseReferrals(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASEREFERRALS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseReferrals(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASEREFERRALS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseStocks()
  {
    return getCaseStocks(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseStocks(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASESTOCKS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseStocks(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASESTOCKS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseTreatmentMethod()
  {
    return getCaseTreatmentMethod(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseTreatmentMethod(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASETREATMENTMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseTreatmentMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASETREATMENTMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseTreatments()
  {
    return getCaseTreatments(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseTreatments(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASETREATMENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getCaseTreatments(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASETREATMENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCases()
  {
    return getCases(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCases(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASES, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCases(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASES, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesFemale()
  {
    return getCasesFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASESFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesFemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASESFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesMale()
  {
    return getCasesMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASESMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesMale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASESMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesPregnant()
  {
    return getCasesPregnant(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesPregnant(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASESPREGNANT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getCasesPregnant(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CASESPREGNANT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getClinicallyDiagnosed()
  {
    return getClinicallyDiagnosed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getClinicallyDiagnosed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CLINICALLYDIAGNOSED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getClinicallyDiagnosed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CLINICALLYDIAGNOSED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getClinicallyDiagnosedDeath()
  {
    return getClinicallyDiagnosedDeath(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getClinicallyDiagnosedDeath(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CLINICALLYDIAGNOSEDDEATH, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getClinicallyDiagnosedDeath(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.CLINICALLYDIAGNOSEDDEATH, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDaysOutOfStock()
  {
    return getDaysOutOfStock(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDaysOutOfStock(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DAYSOUTOFSTOCK, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDaysOutOfStock(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DAYSOUTOFSTOCK, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeaths()
  {
    return getDeaths(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeaths(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEATHS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeaths(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEATHS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsFemale()
  {
    return getDeathsFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEATHSFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsFemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEATHSFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsMale()
  {
    return getDeathsMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEATHSMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsMale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEATHSMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsPregnant()
  {
    return getDeathsPregnant(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsPregnant(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEATHSPREGNANT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDeathsPregnant(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEATHSPREGNANT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDefinitivelyDiagnosed()
  {
    return getDefinitivelyDiagnosed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDefinitivelyDiagnosed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEFINITIVELYDIAGNOSED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDefinitivelyDiagnosed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEFINITIVELYDIAGNOSED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDefinitivelyDiagnosedDeath()
  {
    return getDefinitivelyDiagnosedDeath(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDefinitivelyDiagnosedDeath(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEFINITIVELYDIAGNOSEDDEATH, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDefinitivelyDiagnosedDeath(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.DEFINITIVELYDIAGNOSEDDEATH, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatients()
  {
    return getInPatients(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatients(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatients(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsAnemia()
  {
    return getInPatientsAnemia(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsAnemia(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSANEMIA, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsAnemia(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSANEMIA, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsClinically()
  {
    return getInPatientsClinically(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsClinically(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSCLINICALLY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsClinically(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSCLINICALLY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsDefinitive()
  {
    return getInPatientsDefinitive(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsDefinitive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSDEFINITIVE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsDefinitive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSDEFINITIVE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsDischarged()
  {
    return getInPatientsDischarged(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsDischarged(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSDISCHARGED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsDischarged(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSDISCHARGED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsFemale()
  {
    return getInPatientsFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsFemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsMale()
  {
    return getInPatientsMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsMale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsNotTreated()
  {
    return getInPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSNOTTREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsNotTreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSNOTTREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsPregnantAnemia()
  {
    return getInPatientsPregnantAnemia(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsPregnantAnemia(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSPREGNANTANEMIA, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsPregnantAnemia(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSPREGNANTANEMIA, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsPregnantDianosis()
  {
    return getInPatientsPregnantDianosis(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsPregnantDianosis(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSPREGNANTDIANOSIS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsPregnantDianosis(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSPREGNANTDIANOSIS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsTotal()
  {
    return getInPatientsTotal(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsTotal(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSTOTAL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getInPatientsTotal(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.INPATIENTSTOTAL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatients()
  {
    return getOutPatients(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatients(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatients(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsFemale()
  {
    return getOutPatientsFemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsFemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTSFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsFemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTSFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsMale()
  {
    return getOutPatientsMale(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsMale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTSMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsMale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTSMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsNotTreated()
  {
    return getOutPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTSNOTTREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsNotTreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTSNOTTREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsTotal()
  {
    return getOutPatientsTotal(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsTotal(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTSTOTAL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOutPatientsTotal(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.OUTPATIENTSTOTAL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPatientsNotTreated()
  {
    return getPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.PATIENTSNOTTREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPatientsNotTreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.PATIENTSNOTTREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantDiagnosis()
  {
    return getPregnantDiagnosis(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantDiagnosis(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.PREGNANTDIAGNOSIS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantDiagnosis(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.PREGNANTDIAGNOSIS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantDiagnosisDeath()
  {
    return getPregnantDiagnosisDeath(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantDiagnosisDeath(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.PREGNANTDIAGNOSISDEATH, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantDiagnosisDeath(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.PREGNANTDIAGNOSISDEATH, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantReferralsReceived()
  {
    return getPregnantReferralsReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantReferralsReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.PREGNANTREFERRALSRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPregnantReferralsReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.PREGNANTREFERRALSRECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReferralsReceived()
  {
    return getReferralsReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReferralsReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.REFERRALSRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReferralsReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.REFERRALSRECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReferralsSent()
  {
    return getReferralsSent(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReferralsSent(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.REFERRALSSENT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReferralsSent(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.REFERRALSSENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStillBirths()
  {
    return getStillBirths(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStillBirths(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.STILLBIRTHS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStillBirths(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.surveillance.AncientCaseView.STILLBIRTHS, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends AncientCaseView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<AncientCaseView>(this.getMdClassIF(), valueIterator);
  }

}
