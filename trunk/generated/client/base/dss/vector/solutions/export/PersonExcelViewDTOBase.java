package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -830701473)
public abstract class PersonExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PersonExcelView";
  private static final long serialVersionUID = -830701473;
  
  protected PersonExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String ISIPTRECIPIENT = "isIPTRecipient";
  public static java.lang.String ISITNRECIPIENT = "isITNRecipient";
  public static java.lang.String ISMDSSUSER = "isMDSSUser";
  public static java.lang.String ISPATIENT = "isPatient";
  public static java.lang.String ISSPRAYLEADER = "isSprayLeader";
  public static java.lang.String ISSPRAYOPERATOR = "isSprayOperator";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String LEADERID = "leaderId";
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String PASSWORD = "password";
  public static java.lang.String SEX = "sex";
  public static java.lang.String USERNAME = "username";
  public java.util.Date getDateOfBirth()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATEOFBIRTH));
  }
  
  public void setDateOfBirth(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATEOFBIRTH, "");
    }
    else
    {
      setValue(DATEOFBIRTH, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getDateOfBirthMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DATEOFBIRTH).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFirstNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Boolean getIsIPTRecipient()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISIPTRECIPIENT));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsIPTRecipientMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISIPTRECIPIENT).getAttributeMdDTO();
  }
  
  public Boolean getIsITNRecipient()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISITNRECIPIENT));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsITNRecipientMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISITNRECIPIENT).getAttributeMdDTO();
  }
  
  public Boolean getIsMDSSUser()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISMDSSUSER));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsMDSSUserMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISMDSSUSER).getAttributeMdDTO();
  }
  
  public Boolean getIsPatient()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISPATIENT));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsPatientMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISPATIENT).getAttributeMdDTO();
  }
  
  public Boolean getIsSprayLeader()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYLEADER));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsSprayLeaderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSPRAYLEADER).getAttributeMdDTO();
  }
  
  public Boolean getIsSprayOperator()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYOPERATOR));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsSprayOperatorMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSPRAYOPERATOR).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLastNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LASTNAME).getAttributeMdDTO();
  }
  
  public String getLeaderId()
  {
    return getValue(LEADERID);
  }
  
  public void setLeaderId(String value)
  {
    if(value == null)
    {
      setValue(LEADERID, "");
    }
    else
    {
      setValue(LEADERID, value);
    }
  }
  
  public boolean isLeaderIdWritable()
  {
    return isWritable(LEADERID);
  }
  
  public boolean isLeaderIdReadable()
  {
    return isReadable(LEADERID);
  }
  
  public boolean isLeaderIdModified()
  {
    return isModified(LEADERID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLeaderIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEADERID).getAttributeMdDTO();
  }
  
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void setOperatorId(String value)
  {
    if(value == null)
    {
      setValue(OPERATORID, "");
    }
    else
    {
      setValue(OPERATORID, value);
    }
  }
  
  public boolean isOperatorIdWritable()
  {
    return isWritable(OPERATORID);
  }
  
  public boolean isOperatorIdReadable()
  {
    return isReadable(OPERATORID);
  }
  
  public boolean isOperatorIdModified()
  {
    return isModified(OPERATORID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getOperatorIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OPERATORID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPasswordMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PASSWORD).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getUsernameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(USERNAME).getAttributeMdDTO();
  }
  
  public static PersonExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
