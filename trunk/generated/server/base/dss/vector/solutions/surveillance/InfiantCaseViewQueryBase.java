package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = -1929591231)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InfiantCaseView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class InfiantCaseViewQueryBase extends dss.vector.solutions.surveillance.AggregatedCaseViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1929591231;

  public InfiantCaseViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public InfiantCaseViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.surveillance.InfiantCaseView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseDiagnostic()
  {
    return getCaseDiagnostic(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseDiagnostic(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASEDIAGNOSTIC, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseDiagnostic(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASEDIAGNOSTIC, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseReferrals()
  {
    return getCaseReferrals(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseReferrals(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASEREFERRALS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseReferrals(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASEREFERRALS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseStocks()
  {
    return getCaseStocks(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseStocks(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASESTOCKS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseStocks(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASESTOCKS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseTreatmentMethod()
  {
    return getCaseTreatmentMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseTreatmentMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASETREATMENTMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseTreatmentMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASETREATMENTMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseTreatments()
  {
    return getCaseTreatments(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseTreatments(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASETREATMENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getCaseTreatments(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASETREATMENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCases()
  {
    return getCases(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCases(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASES, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCases(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASES, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesFemale()
  {
    return getCasesFemale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesFemale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASESFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesFemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASESFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesMale()
  {
    return getCasesMale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesMale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASESMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesMale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASESMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesPregnant()
  {
    return getCasesPregnant(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesPregnant(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASESPREGNANT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getCasesPregnant(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CASESPREGNANT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getClinicallyDiagnosed()
  {
    return getClinicallyDiagnosed(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getClinicallyDiagnosed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CLINICALLYDIAGNOSED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getClinicallyDiagnosed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CLINICALLYDIAGNOSED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getClinicallyDiagnosedDeath()
  {
    return getClinicallyDiagnosedDeath(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getClinicallyDiagnosedDeath(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CLINICALLYDIAGNOSEDDEATH, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getClinicallyDiagnosedDeath(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.CLINICALLYDIAGNOSEDDEATH, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDaysOutOfStock()
  {
    return getDaysOutOfStock(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDaysOutOfStock(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DAYSOUTOFSTOCK, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDaysOutOfStock(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DAYSOUTOFSTOCK, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeaths()
  {
    return getDeaths(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeaths(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEATHS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeaths(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEATHS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsFemale()
  {
    return getDeathsFemale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsFemale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEATHSFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsFemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEATHSFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsMale()
  {
    return getDeathsMale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsMale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEATHSMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsMale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEATHSMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsPregnant()
  {
    return getDeathsPregnant(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsPregnant(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEATHSPREGNANT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDeathsPregnant(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEATHSPREGNANT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDefinitivelyDiagnosed()
  {
    return getDefinitivelyDiagnosed(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDefinitivelyDiagnosed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEFINITIVELYDIAGNOSED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDefinitivelyDiagnosed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEFINITIVELYDIAGNOSED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDefinitivelyDiagnosedDeath()
  {
    return getDefinitivelyDiagnosedDeath(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDefinitivelyDiagnosedDeath(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEFINITIVELYDIAGNOSEDDEATH, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getDefinitivelyDiagnosedDeath(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.DEFINITIVELYDIAGNOSEDDEATH, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatients()
  {
    return getInPatients(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatients(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatients(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsAnemia()
  {
    return getInPatientsAnemia(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsAnemia(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSANEMIA, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsAnemia(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSANEMIA, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsClinically()
  {
    return getInPatientsClinically(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsClinically(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSCLINICALLY, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsClinically(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSCLINICALLY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsDefinitive()
  {
    return getInPatientsDefinitive(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsDefinitive(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSDEFINITIVE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsDefinitive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSDEFINITIVE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsDischarged()
  {
    return getInPatientsDischarged(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsDischarged(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSDISCHARGED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsDischarged(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSDISCHARGED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsFemale()
  {
    return getInPatientsFemale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsFemale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsFemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsMale()
  {
    return getInPatientsMale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsMale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsMale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsNotTreated()
  {
    return getInPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSNOTTREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsNotTreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSNOTTREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsPregnantAnemia()
  {
    return getInPatientsPregnantAnemia(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsPregnantAnemia(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSPREGNANTANEMIA, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsPregnantAnemia(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSPREGNANTANEMIA, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsPregnantDianosis()
  {
    return getInPatientsPregnantDianosis(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsPregnantDianosis(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSPREGNANTDIANOSIS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsPregnantDianosis(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSPREGNANTDIANOSIS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsTotal()
  {
    return getInPatientsTotal(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsTotal(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSTOTAL, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInPatientsTotal(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.INPATIENTSTOTAL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatients()
  {
    return getOutPatients(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatients(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatients(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsFemale()
  {
    return getOutPatientsFemale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsFemale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTSFEMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsFemale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTSFEMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsMale()
  {
    return getOutPatientsMale(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsMale(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTSMALE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsMale(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTSMALE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsNotTreated()
  {
    return getOutPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTSNOTTREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsNotTreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTSNOTTREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsTotal()
  {
    return getOutPatientsTotal(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsTotal(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTSTOTAL, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getOutPatientsTotal(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.OUTPATIENTSTOTAL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPatientsNotTreated()
  {
    return getPatientsNotTreated(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPatientsNotTreated(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.PATIENTSNOTTREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPatientsNotTreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.PATIENTSNOTTREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantDiagnosis()
  {
    return getPregnantDiagnosis(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantDiagnosis(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.PREGNANTDIAGNOSIS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantDiagnosis(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.PREGNANTDIAGNOSIS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantDiagnosisDeath()
  {
    return getPregnantDiagnosisDeath(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantDiagnosisDeath(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.PREGNANTDIAGNOSISDEATH, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantDiagnosisDeath(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.PREGNANTDIAGNOSISDEATH, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantReferralsReceived()
  {
    return getPregnantReferralsReceived(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantReferralsReceived(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.PREGNANTREFERRALSRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPregnantReferralsReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.PREGNANTREFERRALSRECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getReferralsReceived()
  {
    return getReferralsReceived(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getReferralsReceived(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.REFERRALSRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getReferralsReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.REFERRALSRECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getReferralsSent()
  {
    return getReferralsSent(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getReferralsSent(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.REFERRALSSENT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getReferralsSent(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.REFERRALSSENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getStillBirths()
  {
    return getStillBirths(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getStillBirths(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.STILLBIRTHS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getStillBirths(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.surveillance.InfiantCaseView.STILLBIRTHS, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends InfiantCaseView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<InfiantCaseView>(this.getMdClassIF(), valueIterator);
  }

}
