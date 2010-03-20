package dss.vector.solutions.surveillance;


public abstract class AggregatedCaseViewDTO extends AggregatedCaseViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239135495819L;

  public AggregatedCaseViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public abstract void setCases(Integer cases);

  public abstract Integer getCases();

  public abstract void setCasesMale(Integer casesFemale);

  public abstract Integer getCasesMale();

  public abstract void setCasesFemale(Integer casesMale);

  public abstract Integer getCasesFemale();

  public abstract void setCasesPregnant(Integer casesPregnant);

  public abstract Integer getCasesPregnant();

  public abstract void setDeaths(Integer deaths);

  public abstract Integer getDeaths();

  public abstract void setDeathsMale(Integer deathsMale);

  public abstract Integer getDeathsMale();

  public abstract void setDeathsFemale(Integer deathsFemale);

  public abstract Integer getDeathsFemale();

  public abstract void setDeathsPregnant(Integer deathsPregnant);

  public abstract Integer getDeathsPregnant();

  public abstract void setInPatients(Integer inPatients);

  public abstract Integer getInPatients();

  public abstract void setOutPatients(Integer outPatients);

  public abstract Integer getOutPatients();

  public abstract void setReferralsReceived(Integer referralsReceived);

  public abstract Integer getReferralsReceived();

  public abstract void setReferralsSent(Integer referralsSent);

  public abstract Integer getReferralsSent();

  public abstract void setPregnantReferralsReceived(Integer pregnantReferralsReceived);

  public abstract Integer getPregnantReferralsReceived();

  public abstract void setPregnantDiagnosis(Integer pregnantDiagnosis);

  public abstract Integer getPregnantDiagnosis();

  public abstract void setPregnantDiagnosisDeath(Integer pregnantDiagnosisDeath);

  public abstract Integer getPregnantDiagnosisDeath();

  public abstract void setClinicallyDiagnosed(Integer clinicallyDiagnosed);

  public abstract Integer getClinicallyDiagnosed();

  public abstract void setDefinitivelyDiagnosed(Integer definitivelyDiagnosed);

  public abstract Integer getDefinitivelyDiagnosed();

  public abstract void setClinicallyDiagnosedDeath(Integer clinicallyDiagnosedDeath);

  public abstract Integer getClinicallyDiagnosedDeath();

  public abstract void setDefinitivelyDiagnosedDeath(Integer definitivelyDiagnosedDeath);

  public abstract Integer getDefinitivelyDiagnosedDeath();

  public abstract void setInPatientsTotal(Integer inPatientsTotal);

  public abstract Integer getInPatientsTotal();

  public abstract void setInPatientsAnemia(Integer inPatientsAnemia);

  public abstract Integer getInPatientsAnemia();

  public abstract void setInPatientsPregnantAnemia(Integer inPatientsPregnantAnemia);

  public abstract Integer getInPatientsPregnantAnemia();

  public abstract void setInPatientsPregnantDianosis(Integer inPatientsPregnantDianosis);

  public abstract Integer getInPatientsPregnantDianosis();

  public abstract void setInPatientsFemale(Integer inPatientsFemale);

  public abstract Integer getInPatientsFemale();

  public abstract void setInPatientsMale(Integer inPatientsMale);

  public abstract Integer getInPatientsMale();

  public abstract void setInPatientsDefinitive(Integer inPatientsDefinitive);

  public abstract Integer getInPatientsDefinitive();

  public abstract void setInPatientsClinically(Integer inPatientsClinically);

  public abstract Integer getInPatientsClinically();

  public abstract void setInPatientsDischarged(Integer inPatientsDischarged);

  public abstract Integer getInPatientsDischarged();

  public abstract void setInPatientsNotTreated(Integer inPatientsNotTreated);

  public abstract Integer getInPatientsNotTreated();

  public abstract void setOutPatientsTotal(Integer outPatientsTotal);

  public abstract Integer getOutPatientsTotal();

  public abstract void setOutPatientsFemale(Integer outPatientsFemale);

  public abstract Integer getOutPatientsFemale();

  public abstract void setOutPatientsMale(Integer outPatientsMale);

  public abstract Integer getOutPatientsMale();

  public abstract void setPatientsNotTreated(Integer patientsNotTreated);

  public abstract Integer getPatientsNotTreated();

  public abstract void setOutPatientsNotTreated(Integer outPatientsNotTreated);

  public abstract Integer getOutPatientsNotTreated();

  public abstract void setStillBirths(Integer stillBirths);

  public abstract Integer getStillBirths();

  public abstract void setDaysOutOfStock(Integer daysOutOfStock);

  public abstract Integer getDaysOutOfStock();

  public abstract boolean isCaseStocksReadable();

  public abstract boolean isCaseDiagnosticReadable();

  public abstract boolean isCaseReferralsReadable();

  public abstract boolean isCaseTreatmentMethodReadable();

  public abstract boolean isCaseTreatmentsReadable();

  public boolean getIsCaseStocksReadable()
  {
    return isCaseStocksReadable();
  }

  public boolean getIsCaseDiagnosticReadable()
  {
    return isCaseDiagnosticReadable();
  }

  public boolean getIsCaseReferralsReadable()
  {
    return isCaseReferralsReadable();
  }

  public boolean getIsCaseTreatmentMethodReadable()
  {
    return isCaseTreatmentMethodReadable();
  }

  public boolean getIsCaseTreatmentsReadable()
  {
    return isCaseTreatmentsReadable();
  }

  public boolean hasCaseId()
  {
    return this.getCaseId() != null && !this.getCaseId().equals("");
  }

  @Override
  public void delete()
  {
    if(this.hasCaseId())
    {
      AggregatedCaseDTO.lock(this.getRequest(), this.getCaseId()).delete();
    }
  }
  
  public AggregatedCaseSearchViewDTO getSearchDTO()
  {
    AggregatedCaseSearchViewDTO search = new AggregatedCaseSearchViewDTO(this.getRequest());
    
    for(PeriodTypeDTO type : this.getPeriodType())
    {
      search.addPeriodType(type);
    }
    
    search.setPeriod(this.getPeriod());
    search.setPeriodYear(this.getPeriodYear());
    search.setStartDate(this.getStartDate());
    search.setEndDate(this.getEndDate());
    search.setGeoEntity(this.getGeoEntity());
    search.setAgeGroup(this.getAgeGroup());
    
    return search;
    
  }
}
