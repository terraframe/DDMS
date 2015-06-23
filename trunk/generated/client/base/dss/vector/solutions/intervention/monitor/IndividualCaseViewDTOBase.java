package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1323271686)
public abstract class IndividualCaseViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualCaseView";
  private static final long serialVersionUID = 1323271686;
  
  protected IndividualCaseViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGE = "age";
  public static java.lang.String CASEENTRYDATE = "caseEntryDate";
  public static java.lang.String CASEREPORTDATE = "caseReportDate";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DIAGNOSISDATE = "diagnosisDate";
  public static java.lang.String ID = "id";
  public static java.lang.String PATIENT = "patient";
  public static java.lang.String PROBABLESOURCE = "probableSource";
  public static java.lang.String PROBABLESOURCETEXT = "probableSourceText";
  public static java.lang.String RESIDENCE = "residence";
  public static java.lang.String RESIDENCETEXT = "residenceText";
  public static java.lang.String WORKPLACE = "workplace";
  public static java.lang.String WORKPLACETEXT = "workplaceText";
  public Integer getAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
  }
  
  public void setAge(Integer value)
  {
    if(value == null)
    {
      setValue(AGE, "");
    }
    else
    {
      setValue(AGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAgeWritable()
  {
    return isWritable(AGE);
  }
  
  public boolean isAgeReadable()
  {
    return isReadable(AGE);
  }
  
  public boolean isAgeModified()
  {
    return isModified(AGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAgeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AGE).getAttributeMdDTO();
  }
  
  public java.util.Date getCaseEntryDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CASEENTRYDATE));
  }
  
  public void setCaseEntryDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(CASEENTRYDATE, "");
    }
    else
    {
      setValue(CASEENTRYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCaseEntryDateWritable()
  {
    return isWritable(CASEENTRYDATE);
  }
  
  public boolean isCaseEntryDateReadable()
  {
    return isReadable(CASEENTRYDATE);
  }
  
  public boolean isCaseEntryDateModified()
  {
    return isModified(CASEENTRYDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCaseEntryDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(CASEENTRYDATE).getAttributeMdDTO();
  }
  
  public java.util.Date getCaseReportDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CASEREPORTDATE));
  }
  
  public void setCaseReportDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(CASEREPORTDATE, "");
    }
    else
    {
      setValue(CASEREPORTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCaseReportDateWritable()
  {
    return isWritable(CASEREPORTDATE);
  }
  
  public boolean isCaseReportDateReadable()
  {
    return isReadable(CASEREPORTDATE);
  }
  
  public boolean isCaseReportDateModified()
  {
    return isModified(CASEREPORTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCaseReportDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(CASEREPORTDATE).getAttributeMdDTO();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public java.util.Date getDiagnosisDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DIAGNOSISDATE));
  }
  
  public void setDiagnosisDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DIAGNOSISDATE, "");
    }
    else
    {
      setValue(DIAGNOSISDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDiagnosisDateWritable()
  {
    return isWritable(DIAGNOSISDATE);
  }
  
  public boolean isDiagnosisDateReadable()
  {
    return isReadable(DIAGNOSISDATE);
  }
  
  public boolean isDiagnosisDateModified()
  {
    return isModified(DIAGNOSISDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDiagnosisDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DIAGNOSISDATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.PatientDTO getPatient()
  {
    if(getValue(PATIENT) == null || getValue(PATIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PatientDTO.get(getRequest(), getValue(PATIENT));
    }
  }
  
  public String getPatientId()
  {
    return getValue(PATIENT);
  }
  
  public void setPatient(dss.vector.solutions.PatientDTO value)
  {
    if(value == null)
    {
      setValue(PATIENT, "");
    }
    else
    {
      setValue(PATIENT, value.getId());
    }
  }
  
  public boolean isPatientWritable()
  {
    return isWritable(PATIENT);
  }
  
  public boolean isPatientReadable()
  {
    return isReadable(PATIENT);
  }
  
  public boolean isPatientModified()
  {
    return isModified(PATIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPatientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getProbableSource()
  {
    if(getValue(PROBABLESOURCE) == null || getValue(PROBABLESOURCE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(PROBABLESOURCE));
    }
  }
  
  public String getProbableSourceId()
  {
    return getValue(PROBABLESOURCE);
  }
  
  public void setProbableSource(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(PROBABLESOURCE, "");
    }
    else
    {
      setValue(PROBABLESOURCE, value.getId());
    }
  }
  
  public boolean isProbableSourceWritable()
  {
    return isWritable(PROBABLESOURCE);
  }
  
  public boolean isProbableSourceReadable()
  {
    return isReadable(PROBABLESOURCE);
  }
  
  public boolean isProbableSourceModified()
  {
    return isModified(PROBABLESOURCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getProbableSourceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PROBABLESOURCE).getAttributeMdDTO();
  }
  
  public String getProbableSourceText()
  {
    return getValue(PROBABLESOURCETEXT);
  }
  
  public void setProbableSourceText(String value)
  {
    if(value == null)
    {
      setValue(PROBABLESOURCETEXT, "");
    }
    else
    {
      setValue(PROBABLESOURCETEXT, value);
    }
  }
  
  public boolean isProbableSourceTextWritable()
  {
    return isWritable(PROBABLESOURCETEXT);
  }
  
  public boolean isProbableSourceTextReadable()
  {
    return isReadable(PROBABLESOURCETEXT);
  }
  
  public boolean isProbableSourceTextModified()
  {
    return isModified(PROBABLESOURCETEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getProbableSourceTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PROBABLESOURCETEXT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getResidence()
  {
    if(getValue(RESIDENCE) == null || getValue(RESIDENCE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(RESIDENCE));
    }
  }
  
  public String getResidenceId()
  {
    return getValue(RESIDENCE);
  }
  
  public void setResidence(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(RESIDENCE, "");
    }
    else
    {
      setValue(RESIDENCE, value.getId());
    }
  }
  
  public boolean isResidenceWritable()
  {
    return isWritable(RESIDENCE);
  }
  
  public boolean isResidenceReadable()
  {
    return isReadable(RESIDENCE);
  }
  
  public boolean isResidenceModified()
  {
    return isModified(RESIDENCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getResidenceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RESIDENCE).getAttributeMdDTO();
  }
  
  public String getResidenceText()
  {
    return getValue(RESIDENCETEXT);
  }
  
  public void setResidenceText(String value)
  {
    if(value == null)
    {
      setValue(RESIDENCETEXT, "");
    }
    else
    {
      setValue(RESIDENCETEXT, value);
    }
  }
  
  public boolean isResidenceTextWritable()
  {
    return isWritable(RESIDENCETEXT);
  }
  
  public boolean isResidenceTextReadable()
  {
    return isReadable(RESIDENCETEXT);
  }
  
  public boolean isResidenceTextModified()
  {
    return isModified(RESIDENCETEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getResidenceTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(RESIDENCETEXT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getWorkplace()
  {
    if(getValue(WORKPLACE) == null || getValue(WORKPLACE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(WORKPLACE));
    }
  }
  
  public String getWorkplaceId()
  {
    return getValue(WORKPLACE);
  }
  
  public void setWorkplace(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(WORKPLACE, "");
    }
    else
    {
      setValue(WORKPLACE, value.getId());
    }
  }
  
  public boolean isWorkplaceWritable()
  {
    return isWritable(WORKPLACE);
  }
  
  public boolean isWorkplaceReadable()
  {
    return isReadable(WORKPLACE);
  }
  
  public boolean isWorkplaceModified()
  {
    return isModified(WORKPLACE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWorkplaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WORKPLACE).getAttributeMdDTO();
  }
  
  public String getWorkplaceText()
  {
    return getValue(WORKPLACETEXT);
  }
  
  public void setWorkplaceText(String value)
  {
    if(value == null)
    {
      setValue(WORKPLACETEXT, "");
    }
    else
    {
      setValue(WORKPLACETEXT, value);
    }
  }
  
  public boolean isWorkplaceTextWritable()
  {
    return isWritable(WORKPLACETEXT);
  }
  
  public boolean isWorkplaceTextReadable()
  {
    return isReadable(WORKPLACETEXT);
  }
  
  public boolean isWorkplaceTextModified()
  {
    return isModified(WORKPLACETEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getWorkplaceTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(WORKPLACETEXT).getAttributeMdDTO();
  }
  
  public static IndividualCaseViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (IndividualCaseViewDTO) dto;
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
