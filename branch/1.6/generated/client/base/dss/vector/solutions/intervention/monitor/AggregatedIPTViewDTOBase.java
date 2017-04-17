package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -1292344698)
public abstract class AggregatedIPTViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.AggregatedIPTView";
  private static final long serialVersionUID = -1292344698;
  
  protected AggregatedIPTViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISPLAYDOSE = "displayDose";
  public static java.lang.String DISPLAYPATIENTS = "displayPatients";
  public static java.lang.String DISPLAYTREATMENTS = "displayTreatments";
  public static java.lang.String DISPLAYVISITS = "displayVisits";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBERNATALCARE = "numberNatalCare";
  public static java.lang.String NUMBERPREGNANT = "numberPregnant";
  public static java.lang.String NUMBERPREGNANTITN = "numberPregnantITN";
  public static java.lang.String NUMBERPREGNANTIRON = "numberPregnantIron";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String PERIODYEAR = "periodYear";
  public static java.lang.String SEARCHTYPE = "searchType";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TOTALITN = "totalITN";
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
  
  public dss.vector.solutions.ontology.TermDTO getDisplayDose()
  {
    if(getValue(DISPLAYDOSE) == null || getValue(DISPLAYDOSE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISPLAYDOSE));
    }
  }
  
  public String getDisplayDoseId()
  {
    return getValue(DISPLAYDOSE);
  }
  
  public void setDisplayDose(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISPLAYDOSE, "");
    }
    else
    {
      setValue(DISPLAYDOSE, value.getId());
    }
  }
  
  public boolean isDisplayDoseWritable()
  {
    return isWritable(DISPLAYDOSE);
  }
  
  public boolean isDisplayDoseReadable()
  {
    return isReadable(DISPLAYDOSE);
  }
  
  public boolean isDisplayDoseModified()
  {
    return isModified(DISPLAYDOSE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDisplayDoseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISPLAYDOSE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDisplayPatients()
  {
    if(getValue(DISPLAYPATIENTS) == null || getValue(DISPLAYPATIENTS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISPLAYPATIENTS));
    }
  }
  
  public String getDisplayPatientsId()
  {
    return getValue(DISPLAYPATIENTS);
  }
  
  public void setDisplayPatients(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISPLAYPATIENTS, "");
    }
    else
    {
      setValue(DISPLAYPATIENTS, value.getId());
    }
  }
  
  public boolean isDisplayPatientsWritable()
  {
    return isWritable(DISPLAYPATIENTS);
  }
  
  public boolean isDisplayPatientsReadable()
  {
    return isReadable(DISPLAYPATIENTS);
  }
  
  public boolean isDisplayPatientsModified()
  {
    return isModified(DISPLAYPATIENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDisplayPatientsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISPLAYPATIENTS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDisplayTreatments()
  {
    if(getValue(DISPLAYTREATMENTS) == null || getValue(DISPLAYTREATMENTS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISPLAYTREATMENTS));
    }
  }
  
  public String getDisplayTreatmentsId()
  {
    return getValue(DISPLAYTREATMENTS);
  }
  
  public void setDisplayTreatments(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISPLAYTREATMENTS, "");
    }
    else
    {
      setValue(DISPLAYTREATMENTS, value.getId());
    }
  }
  
  public boolean isDisplayTreatmentsWritable()
  {
    return isWritable(DISPLAYTREATMENTS);
  }
  
  public boolean isDisplayTreatmentsReadable()
  {
    return isReadable(DISPLAYTREATMENTS);
  }
  
  public boolean isDisplayTreatmentsModified()
  {
    return isModified(DISPLAYTREATMENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDisplayTreatmentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISPLAYTREATMENTS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDisplayVisits()
  {
    if(getValue(DISPLAYVISITS) == null || getValue(DISPLAYVISITS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISPLAYVISITS));
    }
  }
  
  public String getDisplayVisitsId()
  {
    return getValue(DISPLAYVISITS);
  }
  
  public void setDisplayVisits(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISPLAYVISITS, "");
    }
    else
    {
      setValue(DISPLAYVISITS, value.getId());
    }
  }
  
  public boolean isDisplayVisitsWritable()
  {
    return isWritable(DISPLAYVISITS);
  }
  
  public boolean isDisplayVisitsReadable()
  {
    return isReadable(DISPLAYVISITS);
  }
  
  public boolean isDisplayVisitsModified()
  {
    return isModified(DISPLAYVISITS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDisplayVisitsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISPLAYVISITS).getAttributeMdDTO();
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
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
    }
  }
  
  public boolean isGeoIdWritable()
  {
    return isWritable(GEOID);
  }
  
  public boolean isGeoIdReadable()
  {
    return isReadable(GEOID);
  }
  
  public boolean isGeoIdModified()
  {
    return isModified(GEOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOID).getAttributeMdDTO();
  }
  
  public Integer getNumberNatalCare()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERNATALCARE));
  }
  
  public void setNumberNatalCare(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERNATALCARE, "");
    }
    else
    {
      setValue(NUMBERNATALCARE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberNatalCareWritable()
  {
    return isWritable(NUMBERNATALCARE);
  }
  
  public boolean isNumberNatalCareReadable()
  {
    return isReadable(NUMBERNATALCARE);
  }
  
  public boolean isNumberNatalCareModified()
  {
    return isModified(NUMBERNATALCARE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberNatalCareMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERNATALCARE).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnant()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANT));
  }
  
  public void setNumberPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANT, "");
    }
    else
    {
      setValue(NUMBERPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantWritable()
  {
    return isWritable(NUMBERPREGNANT);
  }
  
  public boolean isNumberPregnantReadable()
  {
    return isReadable(NUMBERPREGNANT);
  }
  
  public boolean isNumberPregnantModified()
  {
    return isModified(NUMBERPREGNANT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPregnantMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANT).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantITN()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTITN));
  }
  
  public void setNumberPregnantITN(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTITN, "");
    }
    else
    {
      setValue(NUMBERPREGNANTITN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantITNWritable()
  {
    return isWritable(NUMBERPREGNANTITN);
  }
  
  public boolean isNumberPregnantITNReadable()
  {
    return isReadable(NUMBERPREGNANTITN);
  }
  
  public boolean isNumberPregnantITNModified()
  {
    return isModified(NUMBERPREGNANTITN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPregnantITNMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTITN).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantIron()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTIRON));
  }
  
  public void setNumberPregnantIron(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTIRON, "");
    }
    else
    {
      setValue(NUMBERPREGNANTIRON, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantIronWritable()
  {
    return isWritable(NUMBERPREGNANTIRON);
  }
  
  public boolean isNumberPregnantIronReadable()
  {
    return isReadable(NUMBERPREGNANTIRON);
  }
  
  public boolean isNumberPregnantIronModified()
  {
    return isModified(NUMBERPREGNANTIRON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPregnantIronMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTIRON).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO> getPeriodType()
  {
    return (java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.surveillance.PeriodTypeDTO.CLASS, getEnumNames(PERIODTYPE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getPeriodTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(PERIODTYPE).getAttributeMdDTO();
  }
  
  public Integer getPeriodYear()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIODYEAR));
  }
  
  public void setPeriodYear(Integer value)
  {
    if(value == null)
    {
      setValue(PERIODYEAR, "");
    }
    else
    {
      setValue(PERIODYEAR, java.lang.Integer.toString(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeriodYearMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIODYEAR).getAttributeMdDTO();
  }
  
  public Boolean getSearchType()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SEARCHTYPE));
  }
  
  public void setSearchType(Boolean value)
  {
    if(value == null)
    {
      setValue(SEARCHTYPE, "");
    }
    else
    {
      setValue(SEARCHTYPE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSearchTypeWritable()
  {
    return isWritable(SEARCHTYPE);
  }
  
  public boolean isSearchTypeReadable()
  {
    return isReadable(SEARCHTYPE);
  }
  
  public boolean isSearchTypeModified()
  {
    return isModified(SEARCHTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getSearchTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SEARCHTYPE).getAttributeMdDTO();
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
  
  public Integer getTotalITN()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTALITN));
  }
  
  public void setTotalITN(Integer value)
  {
    if(value == null)
    {
      setValue(TOTALITN, "");
    }
    else
    {
      setValue(TOTALITN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTotalITNWritable()
  {
    return isWritable(TOTALITN);
  }
  
  public boolean isTotalITNReadable()
  {
    return isReadable(TOTALITN);
  }
  
  public boolean isTotalITNModified()
  {
    return isModified(TOTALITN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTotalITNMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTALITN).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.intervention.monitor.IPTPatientsDTO[] patients, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[] visits, dss.vector.solutions.intervention.monitor.IPTDoseDTO[] doses, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[] treatments)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.IPTPatients;", "[Ldss.vector.solutions.intervention.monitor.IPTANCVisit;", "[Ldss.vector.solutions.intervention.monitor.IPTDose;", "[Ldss.vector.solutions.intervention.monitor.IPTTreatment;"};
    Object[] _parameters = new Object[]{patients, visits, doses, treatments};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO[] patients, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[] visits, dss.vector.solutions.intervention.monitor.IPTDoseDTO[] doses, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[] treatments)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.IPTPatients;", "[Ldss.vector.solutions.intervention.monitor.IPTANCVisit;", "[Ldss.vector.solutions.intervention.monitor.IPTDose;", "[Ldss.vector.solutions.intervention.monitor.IPTTreatment;"};
    Object[] _parameters = new Object[]{id, patients, visits, doses, treatments};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[] getIPTANCVisits()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "getIPTANCVisits", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[] getIPTANCVisits(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "getIPTANCVisits", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IPTANCVisitDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IPTDoseDTO[] getIPTDoses()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "getIPTDoses", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IPTDoseDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IPTDoseDTO[] getIPTDoses(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "getIPTDoses", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IPTDoseDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IPTPatientsDTO[] getIPTPatients()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "getIPTPatients", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IPTPatientsDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IPTPatientsDTO[] getIPTPatients(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "getIPTPatients", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IPTPatientsDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[] getIPTTreatments()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "getIPTTreatments", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[] getIPTTreatments(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "getIPTTreatments", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IPTTreatmentDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO searchByView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "searchByView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO searchByView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO.CLASS, "searchByView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AggregatedIPTViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AggregatedIPTViewDTO) dto;
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
