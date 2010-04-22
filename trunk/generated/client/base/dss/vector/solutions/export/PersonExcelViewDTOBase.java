package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -686436781)
public abstract class PersonExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PersonExcelView";
  private static final long serialVersionUID = -686436781;
  
  protected PersonExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String ISIPTRECIPIENT = "isIPTRecipient";
  public static java.lang.String ISITNRECIPIENT = "isITNRecipient";
  public static java.lang.String ISMDSSUSER = "isMDSSUser";
  public static java.lang.String ISPATIENT = "isPatient";
  public static java.lang.String ISSPRAYLEADER = "isSprayLeader";
  public static java.lang.String ISSPRAYOPERATOR = "isSprayOperator";
  public static java.lang.String ISSTOCKSTAFF = "isStockStaff";
  public static java.lang.String ISSUPERVISOR = "isSupervisor";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String MEMBERID = "memberId";
  public static java.lang.String PASSWORD = "password";
  public static java.lang.String RESIDENTIALGEOENTITY = "residentialGeoEntity";
  public static java.lang.String SEX = "sex";
  public static java.lang.String USERNAME = "username";
  public static java.lang.String WORKGEOENTITY = "workGeoEntity";
  public java.util.Date getDateOfBirth()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATEOFBIRTH));
  }
  
  public void setDateOfBirth(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATEOFBIRTH, "");
    }
    else
    {
      setValue(DATEOFBIRTH, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDateOfBirthWritable()
  {
    return isWritable(DATEOFBIRTH);
  }
  
  public boolean isDateOfBirthReadable()
  {
    return isReadable(DATEOFBIRTH);
  }
  
  public boolean isDateOfBirthModified()
  {
    return isModified(DATEOFBIRTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDateOfBirthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DATEOFBIRTH).getAttributeMdDTO();
  }
  
  public String getDisease()
  {
    return getValue(DISEASE);
  }
  
  public void setDisease(String value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value);
    }
  }
  
  public boolean isDiseaseWritable()
  {
    return isWritable(DISEASE);
  }
  
  public boolean isDiseaseReadable()
  {
    return isReadable(DISEASE);
  }
  
  public boolean isDiseaseModified()
  {
    return isModified(DISEASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
  }
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void setFirstName(String value)
  {
    if(value == null)
    {
      setValue(FIRSTNAME, "");
    }
    else
    {
      setValue(FIRSTNAME, value);
    }
  }
  
  public boolean isFirstNameWritable()
  {
    return isWritable(FIRSTNAME);
  }
  
  public boolean isFirstNameReadable()
  {
    return isReadable(FIRSTNAME);
  }
  
  public boolean isFirstNameModified()
  {
    return isModified(FIRSTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFirstNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTNAME).getAttributeMdDTO();
  }
  
  public Boolean getIsIPTRecipient()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISIPTRECIPIENT));
  }
  
  public void setIsIPTRecipient(Boolean value)
  {
    if(value == null)
    {
      setValue(ISIPTRECIPIENT, "");
    }
    else
    {
      setValue(ISIPTRECIPIENT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsIPTRecipientWritable()
  {
    return isWritable(ISIPTRECIPIENT);
  }
  
  public boolean isIsIPTRecipientReadable()
  {
    return isReadable(ISIPTRECIPIENT);
  }
  
  public boolean isIsIPTRecipientModified()
  {
    return isModified(ISIPTRECIPIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsIPTRecipientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISIPTRECIPIENT).getAttributeMdDTO();
  }
  
  public Boolean getIsITNRecipient()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISITNRECIPIENT));
  }
  
  public void setIsITNRecipient(Boolean value)
  {
    if(value == null)
    {
      setValue(ISITNRECIPIENT, "");
    }
    else
    {
      setValue(ISITNRECIPIENT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsITNRecipientWritable()
  {
    return isWritable(ISITNRECIPIENT);
  }
  
  public boolean isIsITNRecipientReadable()
  {
    return isReadable(ISITNRECIPIENT);
  }
  
  public boolean isIsITNRecipientModified()
  {
    return isModified(ISITNRECIPIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsITNRecipientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISITNRECIPIENT).getAttributeMdDTO();
  }
  
  public Boolean getIsMDSSUser()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISMDSSUSER));
  }
  
  public void setIsMDSSUser(Boolean value)
  {
    if(value == null)
    {
      setValue(ISMDSSUSER, "");
    }
    else
    {
      setValue(ISMDSSUSER, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsMDSSUserWritable()
  {
    return isWritable(ISMDSSUSER);
  }
  
  public boolean isIsMDSSUserReadable()
  {
    return isReadable(ISMDSSUSER);
  }
  
  public boolean isIsMDSSUserModified()
  {
    return isModified(ISMDSSUSER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsMDSSUserMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISMDSSUSER).getAttributeMdDTO();
  }
  
  public Boolean getIsPatient()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISPATIENT));
  }
  
  public void setIsPatient(Boolean value)
  {
    if(value == null)
    {
      setValue(ISPATIENT, "");
    }
    else
    {
      setValue(ISPATIENT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsPatientWritable()
  {
    return isWritable(ISPATIENT);
  }
  
  public boolean isIsPatientReadable()
  {
    return isReadable(ISPATIENT);
  }
  
  public boolean isIsPatientModified()
  {
    return isModified(ISPATIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsPatientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISPATIENT).getAttributeMdDTO();
  }
  
  public Boolean getIsSprayLeader()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYLEADER));
  }
  
  public void setIsSprayLeader(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSPRAYLEADER, "");
    }
    else
    {
      setValue(ISSPRAYLEADER, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsSprayLeaderWritable()
  {
    return isWritable(ISSPRAYLEADER);
  }
  
  public boolean isIsSprayLeaderReadable()
  {
    return isReadable(ISSPRAYLEADER);
  }
  
  public boolean isIsSprayLeaderModified()
  {
    return isModified(ISSPRAYLEADER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsSprayLeaderMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSPRAYLEADER).getAttributeMdDTO();
  }
  
  public Boolean getIsSprayOperator()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYOPERATOR));
  }
  
  public void setIsSprayOperator(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSPRAYOPERATOR, "");
    }
    else
    {
      setValue(ISSPRAYOPERATOR, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsSprayOperatorWritable()
  {
    return isWritable(ISSPRAYOPERATOR);
  }
  
  public boolean isIsSprayOperatorReadable()
  {
    return isReadable(ISSPRAYOPERATOR);
  }
  
  public boolean isIsSprayOperatorModified()
  {
    return isModified(ISSPRAYOPERATOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsSprayOperatorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSPRAYOPERATOR).getAttributeMdDTO();
  }
  
  public Boolean getIsStockStaff()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSTOCKSTAFF));
  }
  
  public void setIsStockStaff(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSTOCKSTAFF, "");
    }
    else
    {
      setValue(ISSTOCKSTAFF, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsStockStaffWritable()
  {
    return isWritable(ISSTOCKSTAFF);
  }
  
  public boolean isIsStockStaffReadable()
  {
    return isReadable(ISSTOCKSTAFF);
  }
  
  public boolean isIsStockStaffModified()
  {
    return isModified(ISSTOCKSTAFF);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsStockStaffMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSTOCKSTAFF).getAttributeMdDTO();
  }
  
  public Boolean getIsSupervisor()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSUPERVISOR));
  }
  
  public void setIsSupervisor(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSUPERVISOR, "");
    }
    else
    {
      setValue(ISSUPERVISOR, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsSupervisorWritable()
  {
    return isWritable(ISSUPERVISOR);
  }
  
  public boolean isIsSupervisorReadable()
  {
    return isReadable(ISSUPERVISOR);
  }
  
  public boolean isIsSupervisorModified()
  {
    return isModified(ISSUPERVISOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsSupervisorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSUPERVISOR).getAttributeMdDTO();
  }
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void setLastName(String value)
  {
    if(value == null)
    {
      setValue(LASTNAME, "");
    }
    else
    {
      setValue(LASTNAME, value);
    }
  }
  
  public boolean isLastNameWritable()
  {
    return isWritable(LASTNAME);
  }
  
  public boolean isLastNameReadable()
  {
    return isReadable(LASTNAME);
  }
  
  public boolean isLastNameModified()
  {
    return isModified(LASTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLastNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LASTNAME).getAttributeMdDTO();
  }
  
  public String getMemberId()
  {
    return getValue(MEMBERID);
  }
  
  public void setMemberId(String value)
  {
    if(value == null)
    {
      setValue(MEMBERID, "");
    }
    else
    {
      setValue(MEMBERID, value);
    }
  }
  
  public boolean isMemberIdWritable()
  {
    return isWritable(MEMBERID);
  }
  
  public boolean isMemberIdReadable()
  {
    return isReadable(MEMBERID);
  }
  
  public boolean isMemberIdModified()
  {
    return isModified(MEMBERID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMemberIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MEMBERID).getAttributeMdDTO();
  }
  
  public String getPassword()
  {
    return getValue(PASSWORD);
  }
  
  public void setPassword(String value)
  {
    if(value == null)
    {
      setValue(PASSWORD, "");
    }
    else
    {
      setValue(PASSWORD, value);
    }
  }
  
  public boolean isPasswordWritable()
  {
    return isWritable(PASSWORD);
  }
  
  public boolean isPasswordReadable()
  {
    return isReadable(PASSWORD);
  }
  
  public boolean isPasswordModified()
  {
    return isModified(PASSWORD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPasswordMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PASSWORD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getResidentialGeoEntity()
  {
    if(getValue(RESIDENTIALGEOENTITY) == null || getValue(RESIDENTIALGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(RESIDENTIALGEOENTITY));
    }
  }
  
  public void setResidentialGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALGEOENTITY, "");
    }
    else
    {
      setValue(RESIDENTIALGEOENTITY, value.getId());
    }
  }
  
  public boolean isResidentialGeoEntityWritable()
  {
    return isWritable(RESIDENTIALGEOENTITY);
  }
  
  public boolean isResidentialGeoEntityReadable()
  {
    return isReadable(RESIDENTIALGEOENTITY);
  }
  
  public boolean isResidentialGeoEntityModified()
  {
    return isModified(RESIDENTIALGEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getResidentialGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RESIDENTIALGEOENTITY).getAttributeMdDTO();
  }
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void setSex(String value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value);
    }
  }
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public String getUsername()
  {
    return getValue(USERNAME);
  }
  
  public void setUsername(String value)
  {
    if(value == null)
    {
      setValue(USERNAME, "");
    }
    else
    {
      setValue(USERNAME, value);
    }
  }
  
  public boolean isUsernameWritable()
  {
    return isWritable(USERNAME);
  }
  
  public boolean isUsernameReadable()
  {
    return isReadable(USERNAME);
  }
  
  public boolean isUsernameModified()
  {
    return isModified(USERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUsernameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(USERNAME).getAttributeMdDTO();
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
  
  public static PersonExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PersonExcelViewDTO) dto;
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
