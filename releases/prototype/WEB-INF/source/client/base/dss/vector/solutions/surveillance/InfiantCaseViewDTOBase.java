package dss.vector.solutions.surveillance;

public abstract class InfiantCaseViewDTOBase extends dss.vector.solutions.surveillance.AggregatedCaseViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.InfiantCaseView";
  protected InfiantCaseViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
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
  public Integer getCases()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASES));
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
  
  public boolean isCasesWritable()
  {
    return isWritable(CASES);
  }
  
  public boolean isCasesReadable()
  {
    return isReadable(CASES);
  }
  
  public boolean isCasesModified()
  {
    return isModified(CASES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCasesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASES).getAttributeMdDTO();
  }
  
  public Integer getCasesFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESFEMALE));
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
  
  public boolean isCasesFemaleWritable()
  {
    return isWritable(CASESFEMALE);
  }
  
  public boolean isCasesFemaleReadable()
  {
    return isReadable(CASESFEMALE);
  }
  
  public boolean isCasesFemaleModified()
  {
    return isModified(CASESFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCasesFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASESFEMALE).getAttributeMdDTO();
  }
  
  public Integer getCasesMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESMALE));
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
  
  public boolean isCasesMaleWritable()
  {
    return isWritable(CASESMALE);
  }
  
  public boolean isCasesMaleReadable()
  {
    return isReadable(CASESMALE);
  }
  
  public boolean isCasesMaleModified()
  {
    return isModified(CASESMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCasesMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASESMALE).getAttributeMdDTO();
  }
  
  public Integer getCasesPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESPREGNANT));
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
  
  public boolean isCasesPregnantWritable()
  {
    return isWritable(CASESPREGNANT);
  }
  
  public boolean isCasesPregnantReadable()
  {
    return isReadable(CASESPREGNANT);
  }
  
  public boolean isCasesPregnantModified()
  {
    return isModified(CASESPREGNANT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCasesPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASESPREGNANT).getAttributeMdDTO();
  }
  
  public Integer getClinicallyDiagnosed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CLINICALLYDIAGNOSED));
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
  
  public boolean isClinicallyDiagnosedWritable()
  {
    return isWritable(CLINICALLYDIAGNOSED);
  }
  
  public boolean isClinicallyDiagnosedReadable()
  {
    return isReadable(CLINICALLYDIAGNOSED);
  }
  
  public boolean isClinicallyDiagnosedModified()
  {
    return isModified(CLINICALLYDIAGNOSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getClinicallyDiagnosedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CLINICALLYDIAGNOSED).getAttributeMdDTO();
  }
  
  public Integer getClinicallyDiagnosedDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CLINICALLYDIAGNOSEDDEATH));
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
  
  public boolean isClinicallyDiagnosedDeathWritable()
  {
    return isWritable(CLINICALLYDIAGNOSEDDEATH);
  }
  
  public boolean isClinicallyDiagnosedDeathReadable()
  {
    return isReadable(CLINICALLYDIAGNOSEDDEATH);
  }
  
  public boolean isClinicallyDiagnosedDeathModified()
  {
    return isModified(CLINICALLYDIAGNOSEDDEATH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getClinicallyDiagnosedDeathMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CLINICALLYDIAGNOSEDDEATH).getAttributeMdDTO();
  }
  
  public Integer getDaysOutOfStock()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DAYSOUTOFSTOCK));
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
  
  public boolean isDaysOutOfStockWritable()
  {
    return isWritable(DAYSOUTOFSTOCK);
  }
  
  public boolean isDaysOutOfStockReadable()
  {
    return isReadable(DAYSOUTOFSTOCK);
  }
  
  public boolean isDaysOutOfStockModified()
  {
    return isModified(DAYSOUTOFSTOCK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDaysOutOfStockMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DAYSOUTOFSTOCK).getAttributeMdDTO();
  }
  
  public Integer getDeaths()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHS));
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
  
  public boolean isDeathsWritable()
  {
    return isWritable(DEATHS);
  }
  
  public boolean isDeathsReadable()
  {
    return isReadable(DEATHS);
  }
  
  public boolean isDeathsModified()
  {
    return isModified(DEATHS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDeathsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHS).getAttributeMdDTO();
  }
  
  public Integer getDeathsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSFEMALE));
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
  
  public boolean isDeathsFemaleWritable()
  {
    return isWritable(DEATHSFEMALE);
  }
  
  public boolean isDeathsFemaleReadable()
  {
    return isReadable(DEATHSFEMALE);
  }
  
  public boolean isDeathsFemaleModified()
  {
    return isModified(DEATHSFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDeathsFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHSFEMALE).getAttributeMdDTO();
  }
  
  public Integer getDeathsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSMALE));
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
  
  public boolean isDeathsMaleWritable()
  {
    return isWritable(DEATHSMALE);
  }
  
  public boolean isDeathsMaleReadable()
  {
    return isReadable(DEATHSMALE);
  }
  
  public boolean isDeathsMaleModified()
  {
    return isModified(DEATHSMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDeathsMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHSMALE).getAttributeMdDTO();
  }
  
  public Integer getDeathsPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSPREGNANT));
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
  
  public boolean isDeathsPregnantWritable()
  {
    return isWritable(DEATHSPREGNANT);
  }
  
  public boolean isDeathsPregnantReadable()
  {
    return isReadable(DEATHSPREGNANT);
  }
  
  public boolean isDeathsPregnantModified()
  {
    return isModified(DEATHSPREGNANT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDeathsPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHSPREGNANT).getAttributeMdDTO();
  }
  
  public Integer getDefinitivelyDiagnosed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEFINITIVELYDIAGNOSED));
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
  
  public boolean isDefinitivelyDiagnosedWritable()
  {
    return isWritable(DEFINITIVELYDIAGNOSED);
  }
  
  public boolean isDefinitivelyDiagnosedReadable()
  {
    return isReadable(DEFINITIVELYDIAGNOSED);
  }
  
  public boolean isDefinitivelyDiagnosedModified()
  {
    return isModified(DEFINITIVELYDIAGNOSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDefinitivelyDiagnosedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEFINITIVELYDIAGNOSED).getAttributeMdDTO();
  }
  
  public Integer getDefinitivelyDiagnosedDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEFINITIVELYDIAGNOSEDDEATH));
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
  
  public boolean isDefinitivelyDiagnosedDeathWritable()
  {
    return isWritable(DEFINITIVELYDIAGNOSEDDEATH);
  }
  
  public boolean isDefinitivelyDiagnosedDeathReadable()
  {
    return isReadable(DEFINITIVELYDIAGNOSEDDEATH);
  }
  
  public boolean isDefinitivelyDiagnosedDeathModified()
  {
    return isModified(DEFINITIVELYDIAGNOSEDDEATH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDefinitivelyDiagnosedDeathMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEFINITIVELYDIAGNOSEDDEATH).getAttributeMdDTO();
  }
  
  public Integer getInPatients()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTS));
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
  
  public boolean isInPatientsWritable()
  {
    return isWritable(INPATIENTS);
  }
  
  public boolean isInPatientsReadable()
  {
    return isReadable(INPATIENTS);
  }
  
  public boolean isInPatientsModified()
  {
    return isModified(INPATIENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTS).getAttributeMdDTO();
  }
  
  public Integer getInPatientsAnemia()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSANEMIA));
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
  
  public boolean isInPatientsAnemiaWritable()
  {
    return isWritable(INPATIENTSANEMIA);
  }
  
  public boolean isInPatientsAnemiaReadable()
  {
    return isReadable(INPATIENTSANEMIA);
  }
  
  public boolean isInPatientsAnemiaModified()
  {
    return isModified(INPATIENTSANEMIA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsAnemiaMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSANEMIA).getAttributeMdDTO();
  }
  
  public Integer getInPatientsClinically()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSCLINICALLY));
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
  
  public boolean isInPatientsClinicallyWritable()
  {
    return isWritable(INPATIENTSCLINICALLY);
  }
  
  public boolean isInPatientsClinicallyReadable()
  {
    return isReadable(INPATIENTSCLINICALLY);
  }
  
  public boolean isInPatientsClinicallyModified()
  {
    return isModified(INPATIENTSCLINICALLY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsClinicallyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSCLINICALLY).getAttributeMdDTO();
  }
  
  public Integer getInPatientsDefinitive()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSDEFINITIVE));
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
  
  public boolean isInPatientsDefinitiveWritable()
  {
    return isWritable(INPATIENTSDEFINITIVE);
  }
  
  public boolean isInPatientsDefinitiveReadable()
  {
    return isReadable(INPATIENTSDEFINITIVE);
  }
  
  public boolean isInPatientsDefinitiveModified()
  {
    return isModified(INPATIENTSDEFINITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsDefinitiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSDEFINITIVE).getAttributeMdDTO();
  }
  
  public Integer getInPatientsDischarged()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSDISCHARGED));
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
  
  public boolean isInPatientsDischargedWritable()
  {
    return isWritable(INPATIENTSDISCHARGED);
  }
  
  public boolean isInPatientsDischargedReadable()
  {
    return isReadable(INPATIENTSDISCHARGED);
  }
  
  public boolean isInPatientsDischargedModified()
  {
    return isModified(INPATIENTSDISCHARGED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsDischargedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSDISCHARGED).getAttributeMdDTO();
  }
  
  public Integer getInPatientsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSFEMALE));
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
  
  public boolean isInPatientsFemaleWritable()
  {
    return isWritable(INPATIENTSFEMALE);
  }
  
  public boolean isInPatientsFemaleReadable()
  {
    return isReadable(INPATIENTSFEMALE);
  }
  
  public boolean isInPatientsFemaleModified()
  {
    return isModified(INPATIENTSFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSFEMALE).getAttributeMdDTO();
  }
  
  public Integer getInPatientsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSMALE));
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
  
  public boolean isInPatientsMaleWritable()
  {
    return isWritable(INPATIENTSMALE);
  }
  
  public boolean isInPatientsMaleReadable()
  {
    return isReadable(INPATIENTSMALE);
  }
  
  public boolean isInPatientsMaleModified()
  {
    return isModified(INPATIENTSMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSMALE).getAttributeMdDTO();
  }
  
  public Integer getInPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSNOTTREATED));
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
  
  public boolean isInPatientsNotTreatedWritable()
  {
    return isWritable(INPATIENTSNOTTREATED);
  }
  
  public boolean isInPatientsNotTreatedReadable()
  {
    return isReadable(INPATIENTSNOTTREATED);
  }
  
  public boolean isInPatientsNotTreatedModified()
  {
    return isModified(INPATIENTSNOTTREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsNotTreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSNOTTREATED).getAttributeMdDTO();
  }
  
  public Integer getInPatientsPregnantAnemia()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSPREGNANTANEMIA));
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
  
  public boolean isInPatientsPregnantAnemiaWritable()
  {
    return isWritable(INPATIENTSPREGNANTANEMIA);
  }
  
  public boolean isInPatientsPregnantAnemiaReadable()
  {
    return isReadable(INPATIENTSPREGNANTANEMIA);
  }
  
  public boolean isInPatientsPregnantAnemiaModified()
  {
    return isModified(INPATIENTSPREGNANTANEMIA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsPregnantAnemiaMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSPREGNANTANEMIA).getAttributeMdDTO();
  }
  
  public Integer getInPatientsPregnantDianosis()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSPREGNANTDIANOSIS));
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
  
  public boolean isInPatientsPregnantDianosisWritable()
  {
    return isWritable(INPATIENTSPREGNANTDIANOSIS);
  }
  
  public boolean isInPatientsPregnantDianosisReadable()
  {
    return isReadable(INPATIENTSPREGNANTDIANOSIS);
  }
  
  public boolean isInPatientsPregnantDianosisModified()
  {
    return isModified(INPATIENTSPREGNANTDIANOSIS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsPregnantDianosisMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSPREGNANTDIANOSIS).getAttributeMdDTO();
  }
  
  public Integer getInPatientsTotal()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSTOTAL));
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
  
  public boolean isInPatientsTotalWritable()
  {
    return isWritable(INPATIENTSTOTAL);
  }
  
  public boolean isInPatientsTotalReadable()
  {
    return isReadable(INPATIENTSTOTAL);
  }
  
  public boolean isInPatientsTotalModified()
  {
    return isModified(INPATIENTSTOTAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsTotalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSTOTAL).getAttributeMdDTO();
  }
  
  public Integer getOutPatients()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTS));
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
  
  public boolean isOutPatientsWritable()
  {
    return isWritable(OUTPATIENTS);
  }
  
  public boolean isOutPatientsReadable()
  {
    return isReadable(OUTPATIENTS);
  }
  
  public boolean isOutPatientsModified()
  {
    return isModified(OUTPATIENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTS).getAttributeMdDTO();
  }
  
  public Integer getOutPatientsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSFEMALE));
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
  
  public boolean isOutPatientsFemaleWritable()
  {
    return isWritable(OUTPATIENTSFEMALE);
  }
  
  public boolean isOutPatientsFemaleReadable()
  {
    return isReadable(OUTPATIENTSFEMALE);
  }
  
  public boolean isOutPatientsFemaleModified()
  {
    return isModified(OUTPATIENTSFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTSFEMALE).getAttributeMdDTO();
  }
  
  public Integer getOutPatientsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSMALE));
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
  
  public boolean isOutPatientsMaleWritable()
  {
    return isWritable(OUTPATIENTSMALE);
  }
  
  public boolean isOutPatientsMaleReadable()
  {
    return isReadable(OUTPATIENTSMALE);
  }
  
  public boolean isOutPatientsMaleModified()
  {
    return isModified(OUTPATIENTSMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTSMALE).getAttributeMdDTO();
  }
  
  public Integer getOutPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSNOTTREATED));
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
  
  public boolean isOutPatientsNotTreatedWritable()
  {
    return isWritable(OUTPATIENTSNOTTREATED);
  }
  
  public boolean isOutPatientsNotTreatedReadable()
  {
    return isReadable(OUTPATIENTSNOTTREATED);
  }
  
  public boolean isOutPatientsNotTreatedModified()
  {
    return isModified(OUTPATIENTSNOTTREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsNotTreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTSNOTTREATED).getAttributeMdDTO();
  }
  
  public Integer getOutPatientsTotal()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSTOTAL));
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
  
  public boolean isOutPatientsTotalWritable()
  {
    return isWritable(OUTPATIENTSTOTAL);
  }
  
  public boolean isOutPatientsTotalReadable()
  {
    return isReadable(OUTPATIENTSTOTAL);
  }
  
  public boolean isOutPatientsTotalModified()
  {
    return isModified(OUTPATIENTSTOTAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsTotalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTSTOTAL).getAttributeMdDTO();
  }
  
  public Integer getPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PATIENTSNOTTREATED));
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
  
  public boolean isPatientsNotTreatedWritable()
  {
    return isWritable(PATIENTSNOTTREATED);
  }
  
  public boolean isPatientsNotTreatedReadable()
  {
    return isReadable(PATIENTSNOTTREATED);
  }
  
  public boolean isPatientsNotTreatedModified()
  {
    return isModified(PATIENTSNOTTREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPatientsNotTreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PATIENTSNOTTREATED).getAttributeMdDTO();
  }
  
  public Integer getPregnantDiagnosis()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTDIAGNOSIS));
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
  
  public boolean isPregnantDiagnosisWritable()
  {
    return isWritable(PREGNANTDIAGNOSIS);
  }
  
  public boolean isPregnantDiagnosisReadable()
  {
    return isReadable(PREGNANTDIAGNOSIS);
  }
  
  public boolean isPregnantDiagnosisModified()
  {
    return isModified(PREGNANTDIAGNOSIS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPregnantDiagnosisMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREGNANTDIAGNOSIS).getAttributeMdDTO();
  }
  
  public Integer getPregnantDiagnosisDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTDIAGNOSISDEATH));
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
  
  public boolean isPregnantDiagnosisDeathWritable()
  {
    return isWritable(PREGNANTDIAGNOSISDEATH);
  }
  
  public boolean isPregnantDiagnosisDeathReadable()
  {
    return isReadable(PREGNANTDIAGNOSISDEATH);
  }
  
  public boolean isPregnantDiagnosisDeathModified()
  {
    return isModified(PREGNANTDIAGNOSISDEATH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPregnantDiagnosisDeathMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREGNANTDIAGNOSISDEATH).getAttributeMdDTO();
  }
  
  public Integer getPregnantReferralsReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTREFERRALSRECEIVED));
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
  
  public boolean isPregnantReferralsReceivedWritable()
  {
    return isWritable(PREGNANTREFERRALSRECEIVED);
  }
  
  public boolean isPregnantReferralsReceivedReadable()
  {
    return isReadable(PREGNANTREFERRALSRECEIVED);
  }
  
  public boolean isPregnantReferralsReceivedModified()
  {
    return isModified(PREGNANTREFERRALSRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPregnantReferralsReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREGNANTREFERRALSRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getReferralsReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFERRALSRECEIVED));
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
  
  public boolean isReferralsReceivedWritable()
  {
    return isWritable(REFERRALSRECEIVED);
  }
  
  public boolean isReferralsReceivedReadable()
  {
    return isReadable(REFERRALSRECEIVED);
  }
  
  public boolean isReferralsReceivedModified()
  {
    return isModified(REFERRALSRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReferralsReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFERRALSRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getReferralsSent()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFERRALSSENT));
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
  
  public boolean isReferralsSentWritable()
  {
    return isWritable(REFERRALSSENT);
  }
  
  public boolean isReferralsSentReadable()
  {
    return isReadable(REFERRALSSENT);
  }
  
  public boolean isReferralsSentModified()
  {
    return isModified(REFERRALSSENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReferralsSentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFERRALSSENT).getAttributeMdDTO();
  }
  
  public Integer getStillBirths()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STILLBIRTHS));
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
  
  public boolean isStillBirthsWritable()
  {
    return isWritable(STILLBIRTHS);
  }
  
  public boolean isStillBirthsReadable()
  {
    return isReadable(STILLBIRTHS);
  }
  
  public boolean isStillBirthsModified()
  {
    return isModified(STILLBIRTHS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getStillBirthsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STILLBIRTHS).getAttributeMdDTO();
  }
  
  public static InfiantCaseViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (InfiantCaseViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
