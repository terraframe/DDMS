package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -1028746533)
public abstract class IndividualIPTCaseViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualIPTCaseView";
  private static final long serialVersionUID = -1028746533;
  
  protected IndividualIPTCaseViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String ID = "id";
  public static java.lang.String PATIENT = "patient";
  public static java.lang.String RESIDENTIALLOCATION = "residentialLocation";
  public static java.lang.String SERVICEDATE = "serviceDate";
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
  
  public dss.vector.solutions.PersonDTO getPatient()
  {
    if(getValue(PATIENT) == null || getValue(PATIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PersonDTO.get(getRequest(), getValue(PATIENT));
    }
  }
  
  public void setPatient(dss.vector.solutions.PersonDTO value)
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPatientMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENT).getAttributeMdDTO();
  }
  
  public String getResidentialLocation()
  {
    return getValue(RESIDENTIALLOCATION);
  }
  
  public void setResidentialLocation(String value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALLOCATION, "");
    }
    else
    {
      setValue(RESIDENTIALLOCATION, value);
    }
  }
  
  public boolean isResidentialLocationWritable()
  {
    return isWritable(RESIDENTIALLOCATION);
  }
  
  public boolean isResidentialLocationReadable()
  {
    return isReadable(RESIDENTIALLOCATION);
  }
  
  public boolean isResidentialLocationModified()
  {
    return isModified(RESIDENTIALLOCATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getResidentialLocationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RESIDENTIALLOCATION).getAttributeMdDTO();
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
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewQueryDTO getPage(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonViewDTO getPatientView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO.CLASS, "getPatientView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonViewDTO getPatientView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO.CLASS, "getPatientView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO[] searchCases(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Date serviceDate, java.lang.String patientId)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.String"};
    Object[] _parameters = new Object[]{serviceDate, patientId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO.CLASS, "searchCases", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static IndividualIPTCaseViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (IndividualIPTCaseViewDTO) dto;
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
