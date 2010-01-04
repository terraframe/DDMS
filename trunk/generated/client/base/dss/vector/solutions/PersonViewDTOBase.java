package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -701569520)
public abstract class PersonViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PersonView";
  private static final long serialVersionUID = -701569520;
  
  protected PersonViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGE = "age";
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
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
  public static java.lang.String LEADERID = "leaderId";
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String PAZZWORD = "pazzword";
  public static java.lang.String PERSONID = "personId";
  public static java.lang.String RESIDENTIALGEOID = "residentialGeoId";
  public static java.lang.String RESIDENTIALINFORMATION = "residentialInformation";
  public static java.lang.String SEX = "sex";
  public static java.lang.String UZERNAME = "uzername";
  public static java.lang.String WORKGEOID = "workGeoId";
  public static java.lang.String WORKINFORMATION = "workInformation";
  public Integer getAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getAgeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AGE).getAttributeMdDTO();
  }
  
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
  
  public Boolean getIsStockStaff()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSTOCKSTAFF));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsStockStaffMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSTOCKSTAFF).getAttributeMdDTO();
  }
  
  public Boolean getIsSupervisor()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSUPERVISOR));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsSupervisorMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSUPERVISOR).getAttributeMdDTO();
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
    return getValue(PAZZWORD);
  }
  
  public void setPassword(String value)
  {
    if(value == null)
    {
      setValue(PAZZWORD, "");
    }
    else
    {
      setValue(PAZZWORD, value);
    }
  }
  
  public boolean isPasswordWritable()
  {
    return isWritable(PAZZWORD);
  }
  
  public boolean isPasswordReadable()
  {
    return isReadable(PAZZWORD);
  }
  
  public boolean isPasswordModified()
  {
    return isModified(PAZZWORD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPasswordMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PAZZWORD).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPersonIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERSONID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getResidentialGeoIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RESIDENTIALGEOID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getResidentialInformationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RESIDENTIALINFORMATION).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public String getUsername()
  {
    return getValue(UZERNAME);
  }
  
  public void setUsername(String value)
  {
    if(value == null)
    {
      setValue(UZERNAME, "");
    }
    else
    {
      setValue(UZERNAME, value);
    }
  }
  
  public boolean isUsernameWritable()
  {
    return isWritable(UZERNAME);
  }
  
  public boolean isUsernameReadable()
  {
    return isReadable(UZERNAME);
  }
  
  public boolean isUsernameModified()
  {
    return isModified(UZERNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getUsernameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UZERNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getWorkGeoIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WORKGEOID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getWorkInformationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WORKINFORMATION).getAttributeMdDTO();
  }
  
  public final void applyAsITNRecipient()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyAsITNRecipient", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAsITNRecipient(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyAsITNRecipient", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void applyNonDelegates()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyNonDelegates", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyNonDelegates(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "applyNonDelegates", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonWithDelegatesViewQueryDTO getDuplicatesPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "getDuplicatesPage", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQueryDTO getDuplicatesPage(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "getDuplicatesPage", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonWithDelegatesViewQueryDTO searchForDuplicates()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "searchForDuplicates", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQueryDTO searchForDuplicates(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonViewDTO.CLASS, "searchForDuplicates", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static PersonViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
