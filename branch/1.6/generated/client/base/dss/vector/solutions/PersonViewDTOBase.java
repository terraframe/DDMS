package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -1644444085)
public abstract class PersonViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PersonView";
  private static final long serialVersionUID = -1644444085;
  
  protected PersonViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGE = "age";
  public static java.lang.String BIRTHENTITY = "birthEntity";
  public static java.lang.String BIRTHLOCATION = "birthLocation";
  public static java.lang.String CODE = "code";
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFIER = "identifier";
  public static java.lang.String ISIPTRECIPIENT = "isIPTRecipient";
  public static java.lang.String ISITNRECIPIENT = "isITNRecipient";
  public static java.lang.String ISMDSSUSER = "isMDSSUser";
  public static java.lang.String ISPATIENT = "isPatient";
  public static java.lang.String ISPHYSICIAN = "isPhysician";
  public static java.lang.String ISSPRAYLEADER = "isSprayLeader";
  public static java.lang.String ISSPRAYOPERATOR = "isSprayOperator";
  public static java.lang.String ISSTOCKSTAFF = "isStockStaff";
  public static java.lang.String ISSUPERVISOR = "isSupervisor";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String MEMBERID = "memberId";
  public static java.lang.String PASSWORD = "password";
  public static java.lang.String PERSONID = "personId";
  public static java.lang.String RESIDENTIALGEOID = "residentialGeoId";
  public static java.lang.String RESIDENTIALINFORMATION = "residentialInformation";
  public static java.lang.String SEX = "sex";
  public static java.lang.String USERNAME = "username";
  public static java.lang.String WORKGEOID = "workGeoId";
  public static java.lang.String WORKINFORMATION = "workInformation";
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
  
  public String getBirthEntity()
  {
    return getValue(BIRTHENTITY);
  }
  
  public void setBirthEntity(String value)
  {
    if(value == null)
    {
      setValue(BIRTHENTITY, "");
    }
    else
    {
      setValue(BIRTHENTITY, value);
    }
  }
  
  public boolean isBirthEntityWritable()
  {
    return isWritable(BIRTHENTITY);
  }
  
  public boolean isBirthEntityReadable()
  {
    return isReadable(BIRTHENTITY);
  }
  
  public boolean isBirthEntityModified()
  {
    return isModified(BIRTHENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBirthEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BIRTHENTITY).getAttributeMdDTO();
  }
  
  public String getBirthLocation()
  {
    return getValue(BIRTHLOCATION);
  }
  
  public void setBirthLocation(String value)
  {
    if(value == null)
    {
      setValue(BIRTHLOCATION, "");
    }
    else
    {
      setValue(BIRTHLOCATION, value);
    }
  }
  
  public boolean isBirthLocationWritable()
  {
    return isWritable(BIRTHLOCATION);
  }
  
  public boolean isBirthLocationReadable()
  {
    return isReadable(BIRTHLOCATION);
  }
  
  public boolean isBirthLocationModified()
  {
    return isModified(BIRTHLOCATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getBirthLocationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(BIRTHLOCATION).getAttributeMdDTO();
  }
  
  public String getCode()
  {
    return getValue(CODE);
  }
  
  public void setCode(String value)
  {
    if(value == null)
    {
      setValue(CODE, "");
    }
    else
    {
      setValue(CODE, value);
    }
  }
  
  public boolean isCodeWritable()
  {
    return isWritable(CODE);
  }
  
  public boolean isCodeReadable()
  {
    return isReadable(CODE);
  }
  
  public boolean isCodeModified()
  {
    return isModified(CODE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCodeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CODE).getAttributeMdDTO();
  }
  
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
  
  public dss.vector.solutions.general.DiseaseDTO getDisease()
  {
    if(getValue(DISEASE) == null || getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.DiseaseDTO.get(getRequest(), getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.DiseaseDTO value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
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
  
  public String getIdentifier()
  {
    return getValue(IDENTIFIER);
  }
  
  public void setIdentifier(String value)
  {
    if(value == null)
    {
      setValue(IDENTIFIER, "");
    }
    else
    {
      setValue(IDENTIFIER, value);
    }
  }
  
  public boolean isIdentifierWritable()
  {
    return isWritable(IDENTIFIER);
  }
  
  public boolean isIdentifierReadable()
  {
    return isReadable(IDENTIFIER);
  }
  
  public boolean isIdentifierModified()
  {
    return isModified(IDENTIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIdentifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTIFIER).getAttributeMdDTO();
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
  
  public Boolean getIsPhysician()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISPHYSICIAN));
  }
  
  public void setIsPhysician(Boolean value)
  {
    if(value == null)
    {
      setValue(ISPHYSICIAN, "");
    }
    else
    {
      setValue(ISPHYSICIAN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsPhysicianWritable()
  {
    return isWritable(ISPHYSICIAN);
  }
  
  public boolean isIsPhysicianReadable()
  {
    return isReadable(ISPHYSICIAN);
  }
  
  public boolean isIsPhysicianModified()
  {
    return isModified(ISPHYSICIAN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsPhysicianMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISPHYSICIAN).getAttributeMdDTO();
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
  
  public String getPersonId()
  {
    return getValue(PERSONID);
  }
  
  public void setPersonId(String value)
  {
    if(value == null)
    {
      setValue(PERSONID, "");
    }
    else
    {
      setValue(PERSONID, value);
    }
  }
  
  public boolean isPersonIdWritable()
  {
    return isWritable(PERSONID);
  }
  
  public boolean isPersonIdReadable()
  {
    return isReadable(PERSONID);
  }
  
  public boolean isPersonIdModified()
  {
    return isModified(PERSONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPersonIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERSONID).getAttributeMdDTO();
  }
  
  public String getResidentialGeoId()
  {
    return getValue(RESIDENTIALGEOID);
  }
  
  public void setResidentialGeoId(String value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALGEOID, "");
    }
    else
    {
      setValue(RESIDENTIALGEOID, value);
    }
  }
  
  public boolean isResidentialGeoIdWritable()
  {
    return isWritable(RESIDENTIALGEOID);
  }
  
  public boolean isResidentialGeoIdReadable()
  {
    return isReadable(RESIDENTIALGEOID);
  }
  
  public boolean isResidentialGeoIdModified()
  {
    return isModified(RESIDENTIALGEOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getResidentialGeoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RESIDENTIALGEOID).getAttributeMdDTO();
  }
  
  public String getResidentialInformation()
  {
    return getValue(RESIDENTIALINFORMATION);
  }
  
  public void setResidentialInformation(String value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALINFORMATION, "");
    }
    else
    {
      setValue(RESIDENTIALINFORMATION, value);
    }
  }
  
  public boolean isResidentialInformationWritable()
  {
    return isWritable(RESIDENTIALINFORMATION);
  }
  
  public boolean isResidentialInformationReadable()
  {
    return isReadable(RESIDENTIALINFORMATION);
  }
  
  public boolean isResidentialInformationModified()
  {
    return isModified(RESIDENTIALINFORMATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getResidentialInformationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RESIDENTIALINFORMATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSex()
  {
    if(getValue(SEX) == null || getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SEX));
    }
  }
  
  public String getSexId()
  {
    return getValue(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public String getWorkGeoId()
  {
    return getValue(WORKGEOID);
  }
  
  public void setWorkGeoId(String value)
  {
    if(value == null)
    {
      setValue(WORKGEOID, "");
    }
    else
    {
      setValue(WORKGEOID, value);
    }
  }
  
  public boolean isWorkGeoIdWritable()
  {
    return isWritable(WORKGEOID);
  }
  
  public boolean isWorkGeoIdReadable()
  {
    return isReadable(WORKGEOID);
  }
  
  public boolean isWorkGeoIdModified()
  {
    return isModified(WORKGEOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWorkGeoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WORKGEOID).getAttributeMdDTO();
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
  
  public final void applyAsITNRecipient()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyAsITNRecipient", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAsITNRecipient(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyAsITNRecipient", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonWithDelegatesViewDTO applyAsPhysician()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyAsPhysician", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewDTO applyAsPhysician(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyAsPhysician", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void applyNonDelegates()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyNonDelegates", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyNonDelegates(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyNonDelegates", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonWithDelegatesViewQueryDTO getDuplicatesPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "getDuplicatesPage", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQueryDTO getDuplicatesPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "getDuplicatesPage", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonWithDelegatesViewQueryDTO searchForDuplicates()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "searchForDuplicates", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQueryDTO searchForDuplicates(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "searchForDuplicates", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static PersonViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PersonViewDTO) dto;
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
