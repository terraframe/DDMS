package dss.vector.solutions.surveillance;

public abstract class AggregatedCaseViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.AggregatedCaseView";
  protected AggregatedCaseViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGEGROUP = "ageGroup";
  public static java.lang.String CASEID = "caseId";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String PERIODYEAR = "periodYear";
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
  
  public void setAgeGroup(dss.vector.solutions.surveillance.AggregatedAgeGroupDTO value)
  {
    setValue(AGEGROUP, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAgeGroupMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(AGEGROUP).getAttributeMdDTO();
  }
  
  public String getCaseId()
  {
    return getValue(CASEID);
  }
  
  public void setCaseId(String value)
  {
    if(value == null)
    {
      setValue(CASEID, "");
    }
    else
    {
      setValue(CASEID, value);
    }
  }
  
  public boolean isCaseIdWritable()
  {
    return isWritable(CASEID);
  }
  
  public boolean isCaseIdReadable()
  {
    return isReadable(CASEID);
  }
  
  public boolean isCaseIdModified()
  {
    return isModified(CASEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getCaseIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CASEID).getAttributeMdDTO();
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
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    setValue(GEOENTITY, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodWritable()
  {
    return isWritable(PERIOD);
  }
  
  public boolean isPeriodReadable()
  {
    return isReadable(PERIOD);
  }
  
  public boolean isPeriodModified()
  {
    return isModified(PERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO> getPeriodType()
  {
    return (java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.surveillance.PeriodType", getEnumNames(PERIODTYPE));
  }
  
  public java.util.List<String> getPeriodTypeEnumNames()
  {
    return getEnumNames(PERIODTYPE);
  }
  
  public void addPeriodType(dss.vector.solutions.surveillance.PeriodTypeDTO enumDTO)
  {
    addEnumItem(PERIODTYPE, enumDTO.toString());
  }
  
  public void removePeriodType(dss.vector.solutions.surveillance.PeriodTypeDTO enumDTO)
  {
    removeEnumItem(PERIODTYPE, enumDTO.toString());
  }
  
  public void clearPeriodType()
  {
    clearEnum(PERIODTYPE);
  }
  
  public boolean isPeriodTypeWritable()
  {
    return isWritable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeReadable()
  {
    return isReadable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeModified()
  {
    return isModified(PERIODTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getPeriodTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(PERIODTYPE).getAttributeMdDTO();
  }
  
  public String getPeriodYear()
  {
    return getValue(PERIODYEAR);
  }
  
  public void setPeriodYear(String value)
  {
    if(value == null)
    {
      setValue(PERIODYEAR, "");
    }
    else
    {
      setValue(PERIODYEAR, value);
    }
  }
  
  public boolean isPeriodYearWritable()
  {
    return isWritable(PERIODYEAR);
  }
  
  public boolean isPeriodYearReadable()
  {
    return isReadable(PERIODYEAR);
  }
  
  public boolean isPeriodYearModified()
  {
    return isModified(PERIODYEAR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPeriodYearMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERIODYEAR).getAttributeMdDTO();
  }
  
  public final void lockCase()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "lockCase", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void lockCase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "lockCase", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void unlockCase()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "unlockCase", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void unlockCase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "unlockCase", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void applyAll(dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.surveillance.CaseTreatment;", "[Ldss.vector.solutions.surveillance.CaseTreatmentMethod;", "[Ldss.vector.solutions.surveillance.CaseTreatmentStock;", "[Ldss.vector.solutions.surveillance.CaseDiagnostic;", "[Ldss.vector.solutions.surveillance.CaseReferral;"};
    Object[] _parameters = new Object[]{treatments, treatmentMethods, stock, diagnosticMethods, referrals};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.surveillance.CaseTreatment;", "[Ldss.vector.solutions.surveillance.CaseTreatmentMethod;", "[Ldss.vector.solutions.surveillance.CaseTreatmentStock;", "[Ldss.vector.solutions.surveillance.CaseDiagnostic;", "[Ldss.vector.solutions.surveillance.CaseReferral;"};
    Object[] _parameters = new Object[]{id, treatments, treatmentMethods, stock, diagnosticMethods, referrals};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AggregatedCaseViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
