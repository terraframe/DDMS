package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -866163030)
public abstract class IndividualIPTViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualIPTView";
  private static final long serialVersionUID = -866163030;
  
  protected IndividualIPTViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ADMINISTRATORNAME = "administratorName";
  public static java.lang.String ADMINISTRATORSURNAME = "administratorSurname";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DOSENUMBER = "doseNumber";
  public static java.lang.String DOSETYPE = "doseType";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String FACILITYNAME = "facilityName";
  public static java.lang.String ID = "id";
  public static java.lang.String IPTCASE = "iptCase";
  public static java.lang.String ISANCVISIT = "isANCVisit";
  public static java.lang.String NUMBEROFRECEIVEDITNS = "numberOfReceivedITNs";
  public static java.lang.String PATIENTTYPE = "patientType";
  public static java.lang.String RECEIVEDITN = "receivedITN";
  public static java.lang.String RECEIVEDSUPPLEMENT = "receivedSupplement";
  public static java.lang.String SERVICEDATE = "serviceDate";
  public static java.lang.String VISITNUMBER = "visitNumber";
  public String getAdministratorName()
  {
    return getValue(ADMINISTRATORNAME);
  }
  
  public void setAdministratorName(String value)
  {
    if(value == null)
    {
      setValue(ADMINISTRATORNAME, "");
    }
    else
    {
      setValue(ADMINISTRATORNAME, value);
    }
  }
  
  public boolean isAdministratorNameWritable()
  {
    return isWritable(ADMINISTRATORNAME);
  }
  
  public boolean isAdministratorNameReadable()
  {
    return isReadable(ADMINISTRATORNAME);
  }
  
  public boolean isAdministratorNameModified()
  {
    return isModified(ADMINISTRATORNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAdministratorNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ADMINISTRATORNAME).getAttributeMdDTO();
  }
  
  public String getAdministratorSurname()
  {
    return getValue(ADMINISTRATORSURNAME);
  }
  
  public void setAdministratorSurname(String value)
  {
    if(value == null)
    {
      setValue(ADMINISTRATORSURNAME, "");
    }
    else
    {
      setValue(ADMINISTRATORSURNAME, value);
    }
  }
  
  public boolean isAdministratorSurnameWritable()
  {
    return isWritable(ADMINISTRATORSURNAME);
  }
  
  public boolean isAdministratorSurnameReadable()
  {
    return isReadable(ADMINISTRATORSURNAME);
  }
  
  public boolean isAdministratorSurnameModified()
  {
    return isModified(ADMINISTRATORSURNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAdministratorSurnameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ADMINISTRATORSURNAME).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getDoseNumber()
  {
    if(getValue(DOSENUMBER) == null || getValue(DOSENUMBER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DOSENUMBER));
    }
  }
  
  public String getDoseNumberId()
  {
    return getValue(DOSENUMBER);
  }
  
  public void setDoseNumber(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DOSENUMBER, "");
    }
    else
    {
      setValue(DOSENUMBER, value.getId());
    }
  }
  
  public boolean isDoseNumberWritable()
  {
    return isWritable(DOSENUMBER);
  }
  
  public boolean isDoseNumberReadable()
  {
    return isReadable(DOSENUMBER);
  }
  
  public boolean isDoseNumberModified()
  {
    return isModified(DOSENUMBER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDoseNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DOSENUMBER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDoseType()
  {
    if(getValue(DOSETYPE) == null || getValue(DOSETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DOSETYPE));
    }
  }
  
  public String getDoseTypeId()
  {
    return getValue(DOSETYPE);
  }
  
  public void setDoseType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DOSETYPE, "");
    }
    else
    {
      setValue(DOSETYPE, value.getId());
    }
  }
  
  public boolean isDoseTypeWritable()
  {
    return isWritable(DOSETYPE);
  }
  
  public boolean isDoseTypeReadable()
  {
    return isReadable(DOSETYPE);
  }
  
  public boolean isDoseTypeModified()
  {
    return isModified(DOSETYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDoseTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DOSETYPE).getAttributeMdDTO();
  }
  
  public String getFacility()
  {
    return getValue(FACILITY);
  }
  
  public void setFacility(String value)
  {
    if(value == null)
    {
      setValue(FACILITY, "");
    }
    else
    {
      setValue(FACILITY, value);
    }
  }
  
  public boolean isFacilityWritable()
  {
    return isWritable(FACILITY);
  }
  
  public boolean isFacilityReadable()
  {
    return isReadable(FACILITY);
  }
  
  public boolean isFacilityModified()
  {
    return isModified(FACILITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFacilityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
  }
  
  public String getFacilityName()
  {
    return getValue(FACILITYNAME);
  }
  
  public void setFacilityName(String value)
  {
    if(value == null)
    {
      setValue(FACILITYNAME, "");
    }
    else
    {
      setValue(FACILITYNAME, value);
    }
  }
  
  public boolean isFacilityNameWritable()
  {
    return isWritable(FACILITYNAME);
  }
  
  public boolean isFacilityNameReadable()
  {
    return isReadable(FACILITYNAME);
  }
  
  public boolean isFacilityNameModified()
  {
    return isModified(FACILITYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFacilityNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FACILITYNAME).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO getIptCase()
  {
    if(getValue(IPTCASE) == null || getValue(IPTCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO.get(getRequest(), getValue(IPTCASE));
    }
  }
  
  public String getIptCaseId()
  {
    return getValue(IPTCASE);
  }
  
  public void setIptCase(dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO value)
  {
    if(value == null)
    {
      setValue(IPTCASE, "");
    }
    else
    {
      setValue(IPTCASE, value.getId());
    }
  }
  
  public boolean isIptCaseWritable()
  {
    return isWritable(IPTCASE);
  }
  
  public boolean isIptCaseReadable()
  {
    return isReadable(IPTCASE);
  }
  
  public boolean isIptCaseModified()
  {
    return isModified(IPTCASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getIptCaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IPTCASE).getAttributeMdDTO();
  }
  
  public Boolean getIsANCVisit()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISANCVISIT));
  }
  
  public void setIsANCVisit(Boolean value)
  {
    if(value == null)
    {
      setValue(ISANCVISIT, "");
    }
    else
    {
      setValue(ISANCVISIT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsANCVisitWritable()
  {
    return isWritable(ISANCVISIT);
  }
  
  public boolean isIsANCVisitReadable()
  {
    return isReadable(ISANCVISIT);
  }
  
  public boolean isIsANCVisitModified()
  {
    return isModified(ISANCVISIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsANCVisitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISANCVISIT).getAttributeMdDTO();
  }
  
  public Integer getNumberOfReceivedITNs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFRECEIVEDITNS));
  }
  
  public void setNumberOfReceivedITNs(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFRECEIVEDITNS, "");
    }
    else
    {
      setValue(NUMBEROFRECEIVEDITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfReceivedITNsWritable()
  {
    return isWritable(NUMBEROFRECEIVEDITNS);
  }
  
  public boolean isNumberOfReceivedITNsReadable()
  {
    return isReadable(NUMBEROFRECEIVEDITNS);
  }
  
  public boolean isNumberOfReceivedITNsModified()
  {
    return isModified(NUMBEROFRECEIVEDITNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfReceivedITNsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFRECEIVEDITNS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPatientType()
  {
    if(getValue(PATIENTTYPE) == null || getValue(PATIENTTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PATIENTTYPE));
    }
  }
  
  public String getPatientTypeId()
  {
    return getValue(PATIENTTYPE);
  }
  
  public void setPatientType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PATIENTTYPE, "");
    }
    else
    {
      setValue(PATIENTTYPE, value.getId());
    }
  }
  
  public boolean isPatientTypeWritable()
  {
    return isWritable(PATIENTTYPE);
  }
  
  public boolean isPatientTypeReadable()
  {
    return isReadable(PATIENTTYPE);
  }
  
  public boolean isPatientTypeModified()
  {
    return isModified(PATIENTTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPatientTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENTTYPE).getAttributeMdDTO();
  }
  
  public Boolean getReceivedITN()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECEIVEDITN));
  }
  
  public void setReceivedITN(Boolean value)
  {
    if(value == null)
    {
      setValue(RECEIVEDITN, "");
    }
    else
    {
      setValue(RECEIVEDITN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isReceivedITNWritable()
  {
    return isWritable(RECEIVEDITN);
  }
  
  public boolean isReceivedITNReadable()
  {
    return isReadable(RECEIVEDITN);
  }
  
  public boolean isReceivedITNModified()
  {
    return isModified(RECEIVEDITN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getReceivedITNMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RECEIVEDITN).getAttributeMdDTO();
  }
  
  public Boolean getReceivedSupplement()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECEIVEDSUPPLEMENT));
  }
  
  public void setReceivedSupplement(Boolean value)
  {
    if(value == null)
    {
      setValue(RECEIVEDSUPPLEMENT, "");
    }
    else
    {
      setValue(RECEIVEDSUPPLEMENT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isReceivedSupplementWritable()
  {
    return isWritable(RECEIVEDSUPPLEMENT);
  }
  
  public boolean isReceivedSupplementReadable()
  {
    return isReadable(RECEIVEDSUPPLEMENT);
  }
  
  public boolean isReceivedSupplementModified()
  {
    return isModified(RECEIVEDSUPPLEMENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getReceivedSupplementMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RECEIVEDSUPPLEMENT).getAttributeMdDTO();
  }
  
  public java.util.Date getServiceDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SERVICEDATE));
  }
  
  public void setServiceDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SERVICEDATE, "");
    }
    else
    {
      setValue(SERVICEDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isServiceDateWritable()
  {
    return isWritable(SERVICEDATE);
  }
  
  public boolean isServiceDateReadable()
  {
    return isReadable(SERVICEDATE);
  }
  
  public boolean isServiceDateModified()
  {
    return isModified(SERVICEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getServiceDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SERVICEDATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getVisitNumber()
  {
    if(getValue(VISITNUMBER) == null || getValue(VISITNUMBER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(VISITNUMBER));
    }
  }
  
  public String getVisitNumberId()
  {
    return getValue(VISITNUMBER);
  }
  
  public void setVisitNumber(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(VISITNUMBER, "");
    }
    else
    {
      setValue(VISITNUMBER, value.getId());
    }
  }
  
  public boolean isVisitNumberWritable()
  {
    return isWritable(VISITNUMBER);
  }
  
  public boolean isVisitNumberReadable()
  {
    return isReadable(VISITNUMBER);
  }
  
  public boolean isVisitNumberModified()
  {
    return isModified(VISITNUMBER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getVisitNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(VISITNUMBER).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTViewQueryDTO getCaseInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber, java.lang.String caseId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer", "java.lang.String"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber, caseId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO.CLASS, "getCaseInstances", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTViewQueryDTO getPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static IndividualIPTViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (IndividualIPTViewDTO) dto;
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
