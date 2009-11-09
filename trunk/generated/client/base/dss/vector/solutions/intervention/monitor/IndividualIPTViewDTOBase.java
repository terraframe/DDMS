package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 139945115)
public abstract class IndividualIPTViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualIPTView";
  private static final long serialVersionUID = 139945115;
  
  protected IndividualIPTViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String ID = "id";
  public static java.lang.String IPTCASE = "iptCase";
  public static java.lang.String ISANCVISIT = "isANCVisit";
  public static java.lang.String NUMBEROFRECIEVEDITNS = "numberOfRecievedITNs";
  public static java.lang.String PATIENTTYPE = "patientType";
  public static java.lang.String RECIEVEDITN = "recievedITN";
  public static java.lang.String RECIEVEDSUPPLEMENT = "recievedSupplement";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAdministratorNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ADMINISTRATORNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAdministratorSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ADMINISTRATORSURNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDoseNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DOSENUMBER).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDoseTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DOSETYPE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFacilityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIptCaseMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IPTCASE).getAttributeMdDTO();
  }
  
  public Boolean getIsANCVisit()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISANCVISIT));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsANCVisitMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISANCVISIT).getAttributeMdDTO();
  }
  
  public Integer getNumberOfRecievedITNs()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFRECIEVEDITNS));
  }
  
  public void setNumberOfRecievedITNs(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFRECIEVEDITNS, "");
    }
    else
    {
      setValue(NUMBEROFRECIEVEDITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfRecievedITNsWritable()
  {
    return isWritable(NUMBEROFRECIEVEDITNS);
  }
  
  public boolean isNumberOfRecievedITNsReadable()
  {
    return isReadable(NUMBEROFRECIEVEDITNS);
  }
  
  public boolean isNumberOfRecievedITNsModified()
  {
    return isModified(NUMBEROFRECIEVEDITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberOfRecievedITNsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFRECIEVEDITNS).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPatientTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENTTYPE).getAttributeMdDTO();
  }
  
  public Boolean getRecievedITN()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECIEVEDITN));
  }
  
  public void setRecievedITN(Boolean value)
  {
    if(value == null)
    {
      setValue(RECIEVEDITN, "");
    }
    else
    {
      setValue(RECIEVEDITN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRecievedITNWritable()
  {
    return isWritable(RECIEVEDITN);
  }
  
  public boolean isRecievedITNReadable()
  {
    return isReadable(RECIEVEDITN);
  }
  
  public boolean isRecievedITNModified()
  {
    return isModified(RECIEVEDITN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getRecievedITNMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RECIEVEDITN).getAttributeMdDTO();
  }
  
  public Boolean getRecievedSupplement()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECIEVEDSUPPLEMENT));
  }
  
  public void setRecievedSupplement(Boolean value)
  {
    if(value == null)
    {
      setValue(RECIEVEDSUPPLEMENT, "");
    }
    else
    {
      setValue(RECIEVEDSUPPLEMENT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRecievedSupplementWritable()
  {
    return isWritable(RECIEVEDSUPPLEMENT);
  }
  
  public boolean isRecievedSupplementReadable()
  {
    return isReadable(RECIEVEDSUPPLEMENT);
  }
  
  public boolean isRecievedSupplementModified()
  {
    return isModified(RECIEVEDSUPPLEMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getRecievedSupplementMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RECIEVEDSUPPLEMENT).getAttributeMdDTO();
  }
  
  public java.util.Date getServiceDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SERVICEDATE));
  }
  
  public void setServiceDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SERVICEDATE, "");
    }
    else
    {
      setValue(SERVICEDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getServiceDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SERVICEDATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getVisitNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(VISITNUMBER).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTViewQueryDTO getCaseInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber, java.lang.String caseId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer", "java.lang.String"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber, caseId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO.CLASS, "getCaseInstances", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTViewQueryDTO getPage(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static IndividualIPTViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
