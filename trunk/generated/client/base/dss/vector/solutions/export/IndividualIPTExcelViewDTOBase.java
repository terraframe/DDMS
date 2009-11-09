package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 605484477)
public abstract class IndividualIPTExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.IndividualIPTExcelView";
  private static final long serialVersionUID = 605484477;
  
  protected IndividualIPTExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String NUMBEROFRECIEVEDITNS = "numberOfRecievedITNs";
  public static java.lang.String PATIENTDOB = "patientDOB";
  public static java.lang.String PATIENTFIRSTNAME = "patientFirstName";
  public static java.lang.String PATIENTLASTNAME = "patientLastName";
  public static java.lang.String PATIENTTYPE = "patientType";
  public static java.lang.String RECIEVEDITN = "recievedITN";
  public static java.lang.String RECIEVEDSUPPLEMENT = "recievedSupplement";
  public static java.lang.String RESIDENTIALLOCATION = "residentialLocation";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDoseNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DOSENUMBER).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDoseTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DOSETYPE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFacilityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
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
  
  public java.util.Date getPatientDOB()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(PATIENTDOB));
  }
  
  public void setPatientDOB(java.util.Date value)
  {
    if(value == null)
    {
      setValue(PATIENTDOB, "");
    }
    else
    {
      setValue(PATIENTDOB, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getPatientDOBMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(PATIENTDOB).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPatientFirstNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PATIENTFIRSTNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPatientLastNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PATIENTLASTNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPatientTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PATIENTTYPE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getResidentialLocationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RESIDENTIALLOCATION).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getVisitNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VISITNUMBER).getAttributeMdDTO();
  }
  
  public static IndividualIPTExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
