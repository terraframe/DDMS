package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = 1802563861)
public abstract class AggregatedCaseViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.AggregatedCaseView";
  private static final long serialVersionUID = 1802563861;
  
  protected AggregatedCaseViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGEGROUP = "ageGroup";
  public static java.lang.String CASEDIAGNOSISTYPE = "caseDiagnosisType";
  public static java.lang.String CASEDIAGNOSTIC = "caseDiagnostic";
  public static java.lang.String CASEDISEASEMANIFESTATION = "caseDiseaseManifestation";
  public static java.lang.String CASEPATIENTTYPE = "casePatientType";
  public static java.lang.String CASEREFERRALS = "caseReferrals";
  public static java.lang.String CASESTOCKREFERRAL = "caseStockReferral";
  public static java.lang.String CASESTOCKS = "caseStocks";
  public static java.lang.String CASETREATMENTMETHOD = "caseTreatmentMethod";
  public static java.lang.String CASETREATMENTS = "caseTreatments";
  public static java.lang.String CASES = "cases";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DEATHS = "deaths";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NEGATIVECASES = "negativeCases";
  public static java.lang.String POSITIVECASES = "positiveCases";
  public static java.lang.String STARTDATE = "startDate";
  public dss.vector.solutions.surveillance.AggregatedAgeGroupDTO getAgeGroup()
  {
    if(getValue(AGEGROUP) == null || getValue(AGEGROUP).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.AggregatedAgeGroupDTO.get(getRequest(), getValue(AGEGROUP));
    }
  }
  
  public String getAgeGroupId()
  {
    return getValue(AGEGROUP);
  }
  
  public void setAgeGroup(dss.vector.solutions.surveillance.AggregatedAgeGroupDTO value)
  {
    if(value == null)
    {
      setValue(AGEGROUP, "");
    }
    else
    {
      setValue(AGEGROUP, value.getId());
    }
  }
  
  public boolean isAgeGroupWritable()
  {
    return isWritable(AGEGROUP);
  }
  
  public boolean isAgeGroupReadable()
  {
    return isReadable(AGEGROUP);
  }
  
  public boolean isAgeGroupModified()
  {
    return isModified(AGEGROUP);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAgeGroupMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(AGEGROUP).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCaseDiagnosisType()
  {
    if(getValue(CASEDIAGNOSISTYPE) == null || getValue(CASEDIAGNOSISTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASEDIAGNOSISTYPE));
    }
  }
  
  public String getCaseDiagnosisTypeId()
  {
    return getValue(CASEDIAGNOSISTYPE);
  }
  
  public void setCaseDiagnosisType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASEDIAGNOSISTYPE, "");
    }
    else
    {
      setValue(CASEDIAGNOSISTYPE, value.getId());
    }
  }
  
  public boolean isCaseDiagnosisTypeWritable()
  {
    return isWritable(CASEDIAGNOSISTYPE);
  }
  
  public boolean isCaseDiagnosisTypeReadable()
  {
    return isReadable(CASEDIAGNOSISTYPE);
  }
  
  public boolean isCaseDiagnosisTypeModified()
  {
    return isModified(CASEDIAGNOSISTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCaseDiagnosisTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASEDIAGNOSISTYPE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCaseDiagnostic()
  {
    if(getValue(CASEDIAGNOSTIC) == null || getValue(CASEDIAGNOSTIC).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASEDIAGNOSTIC));
    }
  }
  
  public String getCaseDiagnosticId()
  {
    return getValue(CASEDIAGNOSTIC);
  }
  
  public void setCaseDiagnostic(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASEDIAGNOSTIC, "");
    }
    else
    {
      setValue(CASEDIAGNOSTIC, value.getId());
    }
  }
  
  public boolean isCaseDiagnosticWritable()
  {
    return isWritable(CASEDIAGNOSTIC);
  }
  
  public boolean isCaseDiagnosticReadable()
  {
    return isReadable(CASEDIAGNOSTIC);
  }
  
  public boolean isCaseDiagnosticModified()
  {
    return isModified(CASEDIAGNOSTIC);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCaseDiagnosticMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASEDIAGNOSTIC).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCaseDiseaseManifestation()
  {
    if(getValue(CASEDISEASEMANIFESTATION) == null || getValue(CASEDISEASEMANIFESTATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASEDISEASEMANIFESTATION));
    }
  }
  
  public String getCaseDiseaseManifestationId()
  {
    return getValue(CASEDISEASEMANIFESTATION);
  }
  
  public void setCaseDiseaseManifestation(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASEDISEASEMANIFESTATION, "");
    }
    else
    {
      setValue(CASEDISEASEMANIFESTATION, value.getId());
    }
  }
  
  public boolean isCaseDiseaseManifestationWritable()
  {
    return isWritable(CASEDISEASEMANIFESTATION);
  }
  
  public boolean isCaseDiseaseManifestationReadable()
  {
    return isReadable(CASEDISEASEMANIFESTATION);
  }
  
  public boolean isCaseDiseaseManifestationModified()
  {
    return isModified(CASEDISEASEMANIFESTATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCaseDiseaseManifestationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASEDISEASEMANIFESTATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCasePatientType()
  {
    if(getValue(CASEPATIENTTYPE) == null || getValue(CASEPATIENTTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASEPATIENTTYPE));
    }
  }
  
  public String getCasePatientTypeId()
  {
    return getValue(CASEPATIENTTYPE);
  }
  
  public void setCasePatientType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASEPATIENTTYPE, "");
    }
    else
    {
      setValue(CASEPATIENTTYPE, value.getId());
    }
  }
  
  public boolean isCasePatientTypeWritable()
  {
    return isWritable(CASEPATIENTTYPE);
  }
  
  public boolean isCasePatientTypeReadable()
  {
    return isReadable(CASEPATIENTTYPE);
  }
  
  public boolean isCasePatientTypeModified()
  {
    return isModified(CASEPATIENTTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCasePatientTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASEPATIENTTYPE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCaseReferrals()
  {
    if(getValue(CASEREFERRALS) == null || getValue(CASEREFERRALS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASEREFERRALS));
    }
  }
  
  public String getCaseReferralsId()
  {
    return getValue(CASEREFERRALS);
  }
  
  public void setCaseReferrals(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASEREFERRALS, "");
    }
    else
    {
      setValue(CASEREFERRALS, value.getId());
    }
  }
  
  public boolean isCaseReferralsWritable()
  {
    return isWritable(CASEREFERRALS);
  }
  
  public boolean isCaseReferralsReadable()
  {
    return isReadable(CASEREFERRALS);
  }
  
  public boolean isCaseReferralsModified()
  {
    return isModified(CASEREFERRALS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCaseReferralsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASEREFERRALS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCaseStockReferral()
  {
    if(getValue(CASESTOCKREFERRAL) == null || getValue(CASESTOCKREFERRAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASESTOCKREFERRAL));
    }
  }
  
  public String getCaseStockReferralId()
  {
    return getValue(CASESTOCKREFERRAL);
  }
  
  public void setCaseStockReferral(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASESTOCKREFERRAL, "");
    }
    else
    {
      setValue(CASESTOCKREFERRAL, value.getId());
    }
  }
  
  public boolean isCaseStockReferralWritable()
  {
    return isWritable(CASESTOCKREFERRAL);
  }
  
  public boolean isCaseStockReferralReadable()
  {
    return isReadable(CASESTOCKREFERRAL);
  }
  
  public boolean isCaseStockReferralModified()
  {
    return isModified(CASESTOCKREFERRAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCaseStockReferralMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASESTOCKREFERRAL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCaseStocks()
  {
    if(getValue(CASESTOCKS) == null || getValue(CASESTOCKS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASESTOCKS));
    }
  }
  
  public String getCaseStocksId()
  {
    return getValue(CASESTOCKS);
  }
  
  public void setCaseStocks(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASESTOCKS, "");
    }
    else
    {
      setValue(CASESTOCKS, value.getId());
    }
  }
  
  public boolean isCaseStocksWritable()
  {
    return isWritable(CASESTOCKS);
  }
  
  public boolean isCaseStocksReadable()
  {
    return isReadable(CASESTOCKS);
  }
  
  public boolean isCaseStocksModified()
  {
    return isModified(CASESTOCKS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCaseStocksMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASESTOCKS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCaseTreatmentMethod()
  {
    if(getValue(CASETREATMENTMETHOD) == null || getValue(CASETREATMENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASETREATMENTMETHOD));
    }
  }
  
  public String getCaseTreatmentMethodId()
  {
    return getValue(CASETREATMENTMETHOD);
  }
  
  public void setCaseTreatmentMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASETREATMENTMETHOD, "");
    }
    else
    {
      setValue(CASETREATMENTMETHOD, value.getId());
    }
  }
  
  public boolean isCaseTreatmentMethodWritable()
  {
    return isWritable(CASETREATMENTMETHOD);
  }
  
  public boolean isCaseTreatmentMethodReadable()
  {
    return isReadable(CASETREATMENTMETHOD);
  }
  
  public boolean isCaseTreatmentMethodModified()
  {
    return isModified(CASETREATMENTMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCaseTreatmentMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASETREATMENTMETHOD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCaseTreatments()
  {
    if(getValue(CASETREATMENTS) == null || getValue(CASETREATMENTS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CASETREATMENTS));
    }
  }
  
  public String getCaseTreatmentsId()
  {
    return getValue(CASETREATMENTS);
  }
  
  public void setCaseTreatments(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CASETREATMENTS, "");
    }
    else
    {
      setValue(CASETREATMENTS, value.getId());
    }
  }
  
  public boolean isCaseTreatmentsWritable()
  {
    return isWritable(CASETREATMENTS);
  }
  
  public boolean isCaseTreatmentsReadable()
  {
    return isReadable(CASETREATMENTS);
  }
  
  public boolean isCaseTreatmentsModified()
  {
    return isModified(CASETREATMENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCaseTreatmentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CASETREATMENTS).getAttributeMdDTO();
  }
  
  public Integer getCases()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASES));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCasesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASES).getAttributeMdDTO();
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
  
  public Integer getDeaths()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHS));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getDeathsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHS).getAttributeMdDTO();
  }
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEndDateWritable()
  {
    return isWritable(ENDDATE);
  }
  
  public boolean isEndDateReadable()
  {
    return isReadable(ENDDATE);
  }
  
  public boolean isEndDateModified()
  {
    return isModified(ENDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Integer getNegativeCases()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NEGATIVECASES));
  }
  
  public void setNegativeCases(Integer value)
  {
    if(value == null)
    {
      setValue(NEGATIVECASES, "");
    }
    else
    {
      setValue(NEGATIVECASES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNegativeCasesWritable()
  {
    return isWritable(NEGATIVECASES);
  }
  
  public boolean isNegativeCasesReadable()
  {
    return isReadable(NEGATIVECASES);
  }
  
  public boolean isNegativeCasesModified()
  {
    return isModified(NEGATIVECASES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNegativeCasesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NEGATIVECASES).getAttributeMdDTO();
  }
  
  public Integer getPositiveCases()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POSITIVECASES));
  }
  
  public void setPositiveCases(Integer value)
  {
    if(value == null)
    {
      setValue(POSITIVECASES, "");
    }
    else
    {
      setValue(POSITIVECASES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPositiveCasesWritable()
  {
    return isWritable(POSITIVECASES);
  }
  
  public boolean isPositiveCasesReadable()
  {
    return isReadable(POSITIVECASES);
  }
  
  public boolean isPositiveCasesModified()
  {
    return isModified(POSITIVECASES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPositiveCasesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POSITIVECASES).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isStartDateWritable()
  {
    return isWritable(STARTDATE);
  }
  
  public boolean isStartDateReadable()
  {
    return isReadable(STARTDATE);
  }
  
  public boolean isStartDateModified()
  {
    return isModified(STARTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.surveillance.CaseTreatmentViewDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodViewDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockViewDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticViewDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralViewDTO[] referrals, dss.vector.solutions.surveillance.CaseStockReferralViewDTO[] stockReferrals, dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO[] diagnosticTypes, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountViewDTO[][] diagnosticTypeAmounts, dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO[] diseaseManifestations, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountViewDTO[][] diseaseManifestationAmounts, dss.vector.solutions.surveillance.CasePatientTypeViewDTO[] patientTypes, dss.vector.solutions.surveillance.CasePatientTypeAmountViewDTO[][] patientTypeAmounts)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.surveillance.CaseTreatmentView;", "[Ldss.vector.solutions.surveillance.CaseTreatmentMethodView;", "[Ldss.vector.solutions.surveillance.CaseTreatmentStockView;", "[Ldss.vector.solutions.surveillance.CaseDiagnosticView;", "[Ldss.vector.solutions.surveillance.CaseReferralView;", "[Ldss.vector.solutions.surveillance.CaseStockReferralView;", "[Ldss.vector.solutions.surveillance.CaseDiagnosisTypeView;", "[[Ldss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView;", "[Ldss.vector.solutions.surveillance.CaseDiseaseManifestationView;", "[[Ldss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView;", "[Ldss.vector.solutions.surveillance.CasePatientTypeView;", "[[Ldss.vector.solutions.surveillance.CasePatientTypeAmountView;"};
    Object[] _parameters = new Object[]{treatments, treatmentMethods, stock, diagnosticMethods, referrals, stockReferrals, diagnosticTypes, diagnosticTypeAmounts, diseaseManifestations, diseaseManifestationAmounts, patientTypes, patientTypeAmounts};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.surveillance.CaseTreatmentViewDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodViewDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockViewDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticViewDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralViewDTO[] referrals, dss.vector.solutions.surveillance.CaseStockReferralViewDTO[] stockReferrals, dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO[] diagnosticTypes, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountViewDTO[][] diagnosticTypeAmounts, dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO[] diseaseManifestations, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountViewDTO[][] diseaseManifestationAmounts, dss.vector.solutions.surveillance.CasePatientTypeViewDTO[] patientTypes, dss.vector.solutions.surveillance.CasePatientTypeAmountViewDTO[][] patientTypeAmounts)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.surveillance.CaseTreatmentView;", "[Ldss.vector.solutions.surveillance.CaseTreatmentMethodView;", "[Ldss.vector.solutions.surveillance.CaseTreatmentStockView;", "[Ldss.vector.solutions.surveillance.CaseDiagnosticView;", "[Ldss.vector.solutions.surveillance.CaseReferralView;", "[Ldss.vector.solutions.surveillance.CaseStockReferralView;", "[Ldss.vector.solutions.surveillance.CaseDiagnosisTypeView;", "[[Ldss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView;", "[Ldss.vector.solutions.surveillance.CaseDiseaseManifestationView;", "[[Ldss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView;", "[Ldss.vector.solutions.surveillance.CasePatientTypeView;", "[[Ldss.vector.solutions.surveillance.CasePatientTypeAmountView;"};
    Object[] _parameters = new Object[]{id, treatments, treatmentMethods, stock, diagnosticMethods, referrals, stockReferrals, diagnosticTypes, diagnosticTypeAmounts, diseaseManifestations, diseaseManifestationAmounts, patientTypes, patientTypeAmounts};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseDiagnosticViewDTO[] getDiagnosticMethods()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getDiagnosticMethods", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosticViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnosticViewDTO[] getDiagnosticMethods(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getDiagnosticMethods", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosticViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO[] getDiagnosticTypes()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getDiagnosticTypes", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO[] getDiagnosticTypes(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getDiagnosticTypes", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO[] getDiseaseManifestations()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getDiseaseManifestations", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO[] getDiseaseManifestations(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getDiseaseManifestations", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CasePatientTypeViewDTO[] getPatientTypes()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getPatientTypes", _declaredTypes);
    return (dss.vector.solutions.surveillance.CasePatientTypeViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CasePatientTypeViewDTO[] getPatientTypes(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getPatientTypes", _declaredTypes);
    return (dss.vector.solutions.surveillance.CasePatientTypeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseReferralViewDTO[] getReferrals()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getReferrals", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseReferralViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseReferralViewDTO[] getReferrals(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getReferrals", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseReferralViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseStockReferralViewDTO[] getStockReferrals()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getStockReferrals", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseStockReferralViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseStockReferralViewDTO[] getStockReferrals(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getStockReferrals", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseStockReferralViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseTreatmentMethodViewDTO[] getTreatmentMethods()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getTreatmentMethods", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentMethodViewDTO[] getTreatmentMethods(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getTreatmentMethods", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseTreatmentStockViewDTO[] getTreatmentStocks()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getTreatmentStocks", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentStockViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentStockViewDTO[] getTreatmentStocks(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getTreatmentStocks", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentStockViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseTreatmentViewDTO[] getTreatments()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getTreatments", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentViewDTO[] getTreatments(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "getTreatments", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void validateEpiDate(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String periodType, java.lang.Integer period, java.lang.Integer year)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{periodType, period, year};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "validateEpiDate", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void validateSearchCriteria(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.lang.String periodType, java.lang.Integer period, java.lang.Integer year)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{geoId, periodType, period, year};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "validateSearchCriteria", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AggregatedCaseViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AggregatedCaseViewDTO) dto;
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
