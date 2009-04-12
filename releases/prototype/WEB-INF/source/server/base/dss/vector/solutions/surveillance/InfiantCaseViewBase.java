package dss.vector.solutions.surveillance;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InfiantCaseView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InfiantCaseViewBase extends dss.vector.solutions.surveillance.AggregatedCaseView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.InfiantCaseView";
  public static java.lang.String CASES = "cases";
  public static java.lang.String CASESFEMALE = "casesFemale";
  public static java.lang.String CASESMALE = "casesMale";
  public static java.lang.String CASESPREGNANT = "casesPregnant";
  public static java.lang.String CLINICALLYDIAGNOSED = "clinicallyDiagnosed";
  public static java.lang.String CLINICALLYDIAGNOSEDDEATH = "clinicallyDiagnosedDeath";
  public static java.lang.String DAYSOUTOFSTOCK = "daysOutOfStock";
  public static java.lang.String DEATHS = "deaths";
  public static java.lang.String DEATHSFEMALE = "deathsFemale";
  public static java.lang.String DEATHSMALE = "deathsMale";
  public static java.lang.String DEATHSPREGNANT = "deathsPregnant";
  public static java.lang.String DEFINITIVELYDIAGNOSED = "definitivelyDiagnosed";
  public static java.lang.String DEFINITIVELYDIAGNOSEDDEATH = "definitivelyDiagnosedDeath";
  public static java.lang.String INPATIENTS = "inPatients";
  public static java.lang.String INPATIENTSANEMIA = "inPatientsAnemia";
  public static java.lang.String INPATIENTSCLINICALLY = "inPatientsClinically";
  public static java.lang.String INPATIENTSDEFINITIVE = "inPatientsDefinitive";
  public static java.lang.String INPATIENTSDISCHARGED = "inPatientsDischarged";
  public static java.lang.String INPATIENTSFEMALE = "inPatientsFemale";
  public static java.lang.String INPATIENTSMALE = "inPatientsMale";
  public static java.lang.String INPATIENTSNOTTREATED = "inPatientsNotTreated";
  public static java.lang.String INPATIENTSPREGNANTANEMIA = "inPatientsPregnantAnemia";
  public static java.lang.String INPATIENTSPREGNANTDIANOSIS = "inPatientsPregnantDianosis";
  public static java.lang.String INPATIENTSTOTAL = "inPatientsTotal";
  public static java.lang.String OUTPATIENTS = "outPatients";
  public static java.lang.String OUTPATIENTSFEMALE = "outPatientsFemale";
  public static java.lang.String OUTPATIENTSMALE = "outPatientsMale";
  public static java.lang.String OUTPATIENTSNOTTREATED = "outPatientsNotTreated";
  public static java.lang.String OUTPATIENTSTOTAL = "outPatientsTotal";
  public static java.lang.String PATIENTSNOTTREATED = "patientsNotTreated";
  public static java.lang.String PREGNANTDIAGNOSIS = "pregnantDiagnosis";
  public static java.lang.String PREGNANTDIAGNOSISDEATH = "pregnantDiagnosisDeath";
  public static java.lang.String PREGNANTREFERRALSRECEIVED = "pregnantReferralsReceived";
  public static java.lang.String REFERRALSRECEIVED = "referralsReceived";
  public static java.lang.String REFERRALSSENT = "referralsSent";
  public static java.lang.String STILLBIRTHS = "stillBirths";
  private static final long serialVersionUID = 1239572484958L;
  
  public InfiantCaseViewBase()
  {
    super();
  }
  
  public Integer getCases()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASES));
  }
  
  public void validateCases()
  {
    this.validateAttribute(CASES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCasesMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(CASES);
  }
  
  public void setCases(Integer value)
  {
    if(value == null)
    {
      setValue(CASES, "");
    }
    else
    {
      setValue(CASES, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getCasesFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESFEMALE));
  }
  
  public void validateCasesFemale()
  {
    this.validateAttribute(CASESFEMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCasesFemaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(CASESFEMALE);
  }
  
  public void setCasesFemale(Integer value)
  {
    if(value == null)
    {
      setValue(CASESFEMALE, "");
    }
    else
    {
      setValue(CASESFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getCasesMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESMALE));
  }
  
  public void validateCasesMale()
  {
    this.validateAttribute(CASESMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCasesMaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(CASESMALE);
  }
  
  public void setCasesMale(Integer value)
  {
    if(value == null)
    {
      setValue(CASESMALE, "");
    }
    else
    {
      setValue(CASESMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getCasesPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESPREGNANT));
  }
  
  public void validateCasesPregnant()
  {
    this.validateAttribute(CASESPREGNANT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCasesPregnantMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(CASESPREGNANT);
  }
  
  public void setCasesPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(CASESPREGNANT, "");
    }
    else
    {
      setValue(CASESPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getClinicallyDiagnosed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CLINICALLYDIAGNOSED));
  }
  
  public void validateClinicallyDiagnosed()
  {
    this.validateAttribute(CLINICALLYDIAGNOSED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getClinicallyDiagnosedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(CLINICALLYDIAGNOSED);
  }
  
  public void setClinicallyDiagnosed(Integer value)
  {
    if(value == null)
    {
      setValue(CLINICALLYDIAGNOSED, "");
    }
    else
    {
      setValue(CLINICALLYDIAGNOSED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getClinicallyDiagnosedDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CLINICALLYDIAGNOSEDDEATH));
  }
  
  public void validateClinicallyDiagnosedDeath()
  {
    this.validateAttribute(CLINICALLYDIAGNOSEDDEATH);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getClinicallyDiagnosedDeathMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(CLINICALLYDIAGNOSEDDEATH);
  }
  
  public void setClinicallyDiagnosedDeath(Integer value)
  {
    if(value == null)
    {
      setValue(CLINICALLYDIAGNOSEDDEATH, "");
    }
    else
    {
      setValue(CLINICALLYDIAGNOSEDDEATH, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getDaysOutOfStock()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DAYSOUTOFSTOCK));
  }
  
  public void validateDaysOutOfStock()
  {
    this.validateAttribute(DAYSOUTOFSTOCK);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDaysOutOfStockMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(DAYSOUTOFSTOCK);
  }
  
  public void setDaysOutOfStock(Integer value)
  {
    if(value == null)
    {
      setValue(DAYSOUTOFSTOCK, "");
    }
    else
    {
      setValue(DAYSOUTOFSTOCK, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getDeaths()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHS));
  }
  
  public void validateDeaths()
  {
    this.validateAttribute(DEATHS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDeathsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(DEATHS);
  }
  
  public void setDeaths(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHS, "");
    }
    else
    {
      setValue(DEATHS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getDeathsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSFEMALE));
  }
  
  public void validateDeathsFemale()
  {
    this.validateAttribute(DEATHSFEMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDeathsFemaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(DEATHSFEMALE);
  }
  
  public void setDeathsFemale(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHSFEMALE, "");
    }
    else
    {
      setValue(DEATHSFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getDeathsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSMALE));
  }
  
  public void validateDeathsMale()
  {
    this.validateAttribute(DEATHSMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDeathsMaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(DEATHSMALE);
  }
  
  public void setDeathsMale(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHSMALE, "");
    }
    else
    {
      setValue(DEATHSMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getDeathsPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSPREGNANT));
  }
  
  public void validateDeathsPregnant()
  {
    this.validateAttribute(DEATHSPREGNANT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDeathsPregnantMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(DEATHSPREGNANT);
  }
  
  public void setDeathsPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHSPREGNANT, "");
    }
    else
    {
      setValue(DEATHSPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getDefinitivelyDiagnosed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEFINITIVELYDIAGNOSED));
  }
  
  public void validateDefinitivelyDiagnosed()
  {
    this.validateAttribute(DEFINITIVELYDIAGNOSED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDefinitivelyDiagnosedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(DEFINITIVELYDIAGNOSED);
  }
  
  public void setDefinitivelyDiagnosed(Integer value)
  {
    if(value == null)
    {
      setValue(DEFINITIVELYDIAGNOSED, "");
    }
    else
    {
      setValue(DEFINITIVELYDIAGNOSED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getDefinitivelyDiagnosedDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEFINITIVELYDIAGNOSEDDEATH));
  }
  
  public void validateDefinitivelyDiagnosedDeath()
  {
    this.validateAttribute(DEFINITIVELYDIAGNOSEDDEATH);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDefinitivelyDiagnosedDeathMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(DEFINITIVELYDIAGNOSEDDEATH);
  }
  
  public void setDefinitivelyDiagnosedDeath(Integer value)
  {
    if(value == null)
    {
      setValue(DEFINITIVELYDIAGNOSEDDEATH, "");
    }
    else
    {
      setValue(DEFINITIVELYDIAGNOSEDDEATH, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatients()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTS));
  }
  
  public void validateInPatients()
  {
    this.validateAttribute(INPATIENTS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTS);
  }
  
  public void setInPatients(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTS, "");
    }
    else
    {
      setValue(INPATIENTS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsAnemia()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSANEMIA));
  }
  
  public void validateInPatientsAnemia()
  {
    this.validateAttribute(INPATIENTSANEMIA);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsAnemiaMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSANEMIA);
  }
  
  public void setInPatientsAnemia(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSANEMIA, "");
    }
    else
    {
      setValue(INPATIENTSANEMIA, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsClinically()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSCLINICALLY));
  }
  
  public void validateInPatientsClinically()
  {
    this.validateAttribute(INPATIENTSCLINICALLY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsClinicallyMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSCLINICALLY);
  }
  
  public void setInPatientsClinically(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSCLINICALLY, "");
    }
    else
    {
      setValue(INPATIENTSCLINICALLY, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsDefinitive()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSDEFINITIVE));
  }
  
  public void validateInPatientsDefinitive()
  {
    this.validateAttribute(INPATIENTSDEFINITIVE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsDefinitiveMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSDEFINITIVE);
  }
  
  public void setInPatientsDefinitive(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSDEFINITIVE, "");
    }
    else
    {
      setValue(INPATIENTSDEFINITIVE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsDischarged()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSDISCHARGED));
  }
  
  public void validateInPatientsDischarged()
  {
    this.validateAttribute(INPATIENTSDISCHARGED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsDischargedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSDISCHARGED);
  }
  
  public void setInPatientsDischarged(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSDISCHARGED, "");
    }
    else
    {
      setValue(INPATIENTSDISCHARGED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSFEMALE));
  }
  
  public void validateInPatientsFemale()
  {
    this.validateAttribute(INPATIENTSFEMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsFemaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSFEMALE);
  }
  
  public void setInPatientsFemale(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSFEMALE, "");
    }
    else
    {
      setValue(INPATIENTSFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSMALE));
  }
  
  public void validateInPatientsMale()
  {
    this.validateAttribute(INPATIENTSMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsMaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSMALE);
  }
  
  public void setInPatientsMale(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSMALE, "");
    }
    else
    {
      setValue(INPATIENTSMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSNOTTREATED));
  }
  
  public void validateInPatientsNotTreated()
  {
    this.validateAttribute(INPATIENTSNOTTREATED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsNotTreatedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSNOTTREATED);
  }
  
  public void setInPatientsNotTreated(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSNOTTREATED, "");
    }
    else
    {
      setValue(INPATIENTSNOTTREATED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsPregnantAnemia()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSPREGNANTANEMIA));
  }
  
  public void validateInPatientsPregnantAnemia()
  {
    this.validateAttribute(INPATIENTSPREGNANTANEMIA);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsPregnantAnemiaMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSPREGNANTANEMIA);
  }
  
  public void setInPatientsPregnantAnemia(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSPREGNANTANEMIA, "");
    }
    else
    {
      setValue(INPATIENTSPREGNANTANEMIA, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsPregnantDianosis()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSPREGNANTDIANOSIS));
  }
  
  public void validateInPatientsPregnantDianosis()
  {
    this.validateAttribute(INPATIENTSPREGNANTDIANOSIS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsPregnantDianosisMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSPREGNANTDIANOSIS);
  }
  
  public void setInPatientsPregnantDianosis(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSPREGNANTDIANOSIS, "");
    }
    else
    {
      setValue(INPATIENTSPREGNANTDIANOSIS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getInPatientsTotal()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSTOTAL));
  }
  
  public void validateInPatientsTotal()
  {
    this.validateAttribute(INPATIENTSTOTAL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInPatientsTotalMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(INPATIENTSTOTAL);
  }
  
  public void setInPatientsTotal(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSTOTAL, "");
    }
    else
    {
      setValue(INPATIENTSTOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getOutPatients()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTS));
  }
  
  public void validateOutPatients()
  {
    this.validateAttribute(OUTPATIENTS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOutPatientsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(OUTPATIENTS);
  }
  
  public void setOutPatients(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTS, "");
    }
    else
    {
      setValue(OUTPATIENTS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getOutPatientsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSFEMALE));
  }
  
  public void validateOutPatientsFemale()
  {
    this.validateAttribute(OUTPATIENTSFEMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOutPatientsFemaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(OUTPATIENTSFEMALE);
  }
  
  public void setOutPatientsFemale(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTSFEMALE, "");
    }
    else
    {
      setValue(OUTPATIENTSFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getOutPatientsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSMALE));
  }
  
  public void validateOutPatientsMale()
  {
    this.validateAttribute(OUTPATIENTSMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOutPatientsMaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(OUTPATIENTSMALE);
  }
  
  public void setOutPatientsMale(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTSMALE, "");
    }
    else
    {
      setValue(OUTPATIENTSMALE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getOutPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSNOTTREATED));
  }
  
  public void validateOutPatientsNotTreated()
  {
    this.validateAttribute(OUTPATIENTSNOTTREATED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOutPatientsNotTreatedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(OUTPATIENTSNOTTREATED);
  }
  
  public void setOutPatientsNotTreated(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTSNOTTREATED, "");
    }
    else
    {
      setValue(OUTPATIENTSNOTTREATED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getOutPatientsTotal()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSTOTAL));
  }
  
  public void validateOutPatientsTotal()
  {
    this.validateAttribute(OUTPATIENTSTOTAL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOutPatientsTotalMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(OUTPATIENTSTOTAL);
  }
  
  public void setOutPatientsTotal(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTSTOTAL, "");
    }
    else
    {
      setValue(OUTPATIENTSTOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PATIENTSNOTTREATED));
  }
  
  public void validatePatientsNotTreated()
  {
    this.validateAttribute(PATIENTSNOTTREATED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPatientsNotTreatedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(PATIENTSNOTTREATED);
  }
  
  public void setPatientsNotTreated(Integer value)
  {
    if(value == null)
    {
      setValue(PATIENTSNOTTREATED, "");
    }
    else
    {
      setValue(PATIENTSNOTTREATED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPregnantDiagnosis()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTDIAGNOSIS));
  }
  
  public void validatePregnantDiagnosis()
  {
    this.validateAttribute(PREGNANTDIAGNOSIS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPregnantDiagnosisMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(PREGNANTDIAGNOSIS);
  }
  
  public void setPregnantDiagnosis(Integer value)
  {
    if(value == null)
    {
      setValue(PREGNANTDIAGNOSIS, "");
    }
    else
    {
      setValue(PREGNANTDIAGNOSIS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPregnantDiagnosisDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTDIAGNOSISDEATH));
  }
  
  public void validatePregnantDiagnosisDeath()
  {
    this.validateAttribute(PREGNANTDIAGNOSISDEATH);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPregnantDiagnosisDeathMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(PREGNANTDIAGNOSISDEATH);
  }
  
  public void setPregnantDiagnosisDeath(Integer value)
  {
    if(value == null)
    {
      setValue(PREGNANTDIAGNOSISDEATH, "");
    }
    else
    {
      setValue(PREGNANTDIAGNOSISDEATH, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPregnantReferralsReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTREFERRALSRECEIVED));
  }
  
  public void validatePregnantReferralsReceived()
  {
    this.validateAttribute(PREGNANTREFERRALSRECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPregnantReferralsReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(PREGNANTREFERRALSRECEIVED);
  }
  
  public void setPregnantReferralsReceived(Integer value)
  {
    if(value == null)
    {
      setValue(PREGNANTREFERRALSRECEIVED, "");
    }
    else
    {
      setValue(PREGNANTREFERRALSRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getReferralsReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFERRALSRECEIVED));
  }
  
  public void validateReferralsReceived()
  {
    this.validateAttribute(REFERRALSRECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReferralsReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(REFERRALSRECEIVED);
  }
  
  public void setReferralsReceived(Integer value)
  {
    if(value == null)
    {
      setValue(REFERRALSRECEIVED, "");
    }
    else
    {
      setValue(REFERRALSRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getReferralsSent()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFERRALSSENT));
  }
  
  public void validateReferralsSent()
  {
    this.validateAttribute(REFERRALSSENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReferralsSentMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(REFERRALSSENT);
  }
  
  public void setReferralsSent(Integer value)
  {
    if(value == null)
    {
      setValue(REFERRALSSENT, "");
    }
    else
    {
      setValue(REFERRALSSENT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getStillBirths()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STILLBIRTHS));
  }
  
  public void validateStillBirths()
  {
    this.validateAttribute(STILLBIRTHS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStillBirthsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.InfiantCaseView.CLASS);
    return mdClassIF.definesAttribute(STILLBIRTHS);
  }
  
  public void setStillBirths(Integer value)
  {
    if(value == null)
    {
      setValue(STILLBIRTHS, "");
    }
    else
    {
      setValue(STILLBIRTHS, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static InfiantCaseView get(String id)
  {
    return (InfiantCaseView) com.terraframe.mojo.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
