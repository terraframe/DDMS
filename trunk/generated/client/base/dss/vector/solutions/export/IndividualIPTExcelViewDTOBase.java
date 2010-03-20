package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 989409282)
public abstract class IndividualIPTExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.IndividualIPTExcelView";
  private static final long serialVersionUID = 989409282;
  
  protected IndividualIPTExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ADMINISTRATORNAME = "administratorName";
  public static java.lang.String ADMINISTRATORSURNAME = "administratorSurname";
  public static java.lang.String DOSENUMBER = "doseNumber";
  public static java.lang.String DOSETYPE = "doseType";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String ID = "id";
  public static java.lang.String ISANCVISIT = "isANCVisit";
  public static java.lang.String NUMBEROFRECEIVEDITNS = "numberOfReceivedITNs";
  public static java.lang.String PATIENTDOB = "patientDOB";
  public static java.lang.String PATIENTFIRSTNAME = "patientFirstName";
  public static java.lang.String PATIENTLASTNAME = "patientLastName";
  public static java.lang.String PATIENTTYPE = "patientType";
  public static java.lang.String RECEIVEDITN = "receivedITN";
  public static java.lang.String RECEIVEDSUPPLEMENT = "receivedSupplement";
  public static java.lang.String RESIDENTIALLOCATION = "residentialLocation";
  public static java.lang.String SERVICEDATE = "serviceDate";
  public static java.lang.String VISITNUMBER = "visitNumber";
  public static java.lang.String WORKGEOENTITY = "workGeoEntity";
  public static java.lang.String WORKINFORMATION = "workInformation";
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
  
  public String getDoseNumber()
  {
    return getValue(DOSENUMBER);
  }
  
  public void setDoseNumber(String value)
  {
    if(value == null)
    {
      setValue(DOSENUMBER, "");
    }
    else
    {
      setValue(DOSENUMBER, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDoseNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DOSENUMBER).getAttributeMdDTO();
  }
  
  public String getDoseType()
  {
    return getValue(DOSETYPE);
  }
  
  public void setDoseType(String value)
  {
    if(value == null)
    {
      setValue(DOSETYPE, "");
    }
    else
    {
      setValue(DOSETYPE, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDoseTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DOSETYPE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.HealthFacilityDTO getFacility()
  {
    if(getValue(FACILITY) == null || getValue(FACILITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.HealthFacilityDTO.get(getRequest(), getValue(FACILITY));
    }
  }
  
  public void setFacility(dss.vector.solutions.geo.generated.HealthFacilityDTO value)
  {
    if(value == null)
    {
      setValue(FACILITY, "");
    }
    else
    {
      setValue(FACILITY, value.getId());
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getFacilityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
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
  
  public java.util.Date getPatientDOB()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(PATIENTDOB));
  }
  
  public void setPatientDOB(java.util.Date value)
  {
    if(value == null)
    {
      setValue(PATIENTDOB, "");
    }
    else
    {
      setValue(PATIENTDOB, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isPatientDOBWritable()
  {
    return isWritable(PATIENTDOB);
  }
  
  public boolean isPatientDOBReadable()
  {
    return isReadable(PATIENTDOB);
  }
  
  public boolean isPatientDOBModified()
  {
    return isModified(PATIENTDOB);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getPatientDOBMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(PATIENTDOB).getAttributeMdDTO();
  }
  
  public String getPatientFirstName()
  {
    return getValue(PATIENTFIRSTNAME);
  }
  
  public void setPatientFirstName(String value)
  {
    if(value == null)
    {
      setValue(PATIENTFIRSTNAME, "");
    }
    else
    {
      setValue(PATIENTFIRSTNAME, value);
    }
  }
  
  public boolean isPatientFirstNameWritable()
  {
    return isWritable(PATIENTFIRSTNAME);
  }
  
  public boolean isPatientFirstNameReadable()
  {
    return isReadable(PATIENTFIRSTNAME);
  }
  
  public boolean isPatientFirstNameModified()
  {
    return isModified(PATIENTFIRSTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPatientFirstNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PATIENTFIRSTNAME).getAttributeMdDTO();
  }
  
  public String getPatientLastName()
  {
    return getValue(PATIENTLASTNAME);
  }
  
  public void setPatientLastName(String value)
  {
    if(value == null)
    {
      setValue(PATIENTLASTNAME, "");
    }
    else
    {
      setValue(PATIENTLASTNAME, value);
    }
  }
  
  public boolean isPatientLastNameWritable()
  {
    return isWritable(PATIENTLASTNAME);
  }
  
  public boolean isPatientLastNameReadable()
  {
    return isReadable(PATIENTLASTNAME);
  }
  
  public boolean isPatientLastNameModified()
  {
    return isModified(PATIENTLASTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPatientLastNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PATIENTLASTNAME).getAttributeMdDTO();
  }
  
  public String getPatientType()
  {
    return getValue(PATIENTTYPE);
  }
  
  public void setPatientType(String value)
  {
    if(value == null)
    {
      setValue(PATIENTTYPE, "");
    }
    else
    {
      setValue(PATIENTTYPE, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPatientTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PATIENTTYPE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getResidentialLocation()
  {
    if(getValue(RESIDENTIALLOCATION) == null || getValue(RESIDENTIALLOCATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(RESIDENTIALLOCATION));
    }
  }
  
  public void setResidentialLocation(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALLOCATION, "");
    }
    else
    {
      setValue(RESIDENTIALLOCATION, value.getId());
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getResidentialLocationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RESIDENTIALLOCATION).getAttributeMdDTO();
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
  
  public String getVisitNumber()
  {
    return getValue(VISITNUMBER);
  }
  
  public void setVisitNumber(String value)
  {
    if(value == null)
    {
      setValue(VISITNUMBER, "");
    }
    else
    {
      setValue(VISITNUMBER, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getVisitNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VISITNUMBER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getWorkGeoEntity()
  {
    if(getValue(WORKGEOENTITY) == null || getValue(WORKGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(WORKGEOENTITY));
    }
  }
  
  public void setWorkGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(WORKGEOENTITY, "");
    }
    else
    {
      setValue(WORKGEOENTITY, value.getId());
    }
  }
  
  public boolean isWorkGeoEntityWritable()
  {
    return isWritable(WORKGEOENTITY);
  }
  
  public boolean isWorkGeoEntityReadable()
  {
    return isReadable(WORKGEOENTITY);
  }
  
  public boolean isWorkGeoEntityModified()
  {
    return isModified(WORKGEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWorkGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WORKGEOENTITY).getAttributeMdDTO();
  }
  
  public String getWorkInformation()
  {
    return getValue(WORKINFORMATION);
  }
  
  public void setWorkInformation(String value)
  {
    if(value == null)
    {
      setValue(WORKINFORMATION, "");
    }
    else
    {
      setValue(WORKINFORMATION, value);
    }
  }
  
  public boolean isWorkInformationWritable()
  {
    return isWritable(WORKINFORMATION);
  }
  
  public boolean isWorkInformationReadable()
  {
    return isReadable(WORKINFORMATION);
  }
  
  public boolean isWorkInformationModified()
  {
    return isModified(WORKINFORMATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWorkInformationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WORKINFORMATION).getAttributeMdDTO();
  }
  
  public static IndividualIPTExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (IndividualIPTExcelViewDTO) dto;
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
