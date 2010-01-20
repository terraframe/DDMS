package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -2071288605)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PersonExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class PersonExcelViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PersonExcelView";
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String ISIPTRECIPIENT = "isIPTRecipient";
  public static java.lang.String ISITNRECIPIENT = "isITNRecipient";
  public static java.lang.String ISMDSSUSER = "isMDSSUser";
  public static java.lang.String ISPATIENT = "isPatient";
  public static java.lang.String ISSPRAYLEADER = "isSprayLeader";
  public static java.lang.String ISSPRAYOPERATOR = "isSprayOperator";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String MEMBERID = "memberId";
  public static java.lang.String PASSWORD = "password";
  public static java.lang.String RESIDENTIALGEOENTITY = "residentialGeoEntity";
  public static java.lang.String SEX = "sex";
  public static java.lang.String USERNAME = "username";
  public static java.lang.String WORKGEOENTITY = "workGeoEntity";
  private static final long serialVersionUID = -2071288605;
  
  public PersonExcelViewBase()
  {
    super();
  }
  
  public java.util.Date getDateOfBirth()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATEOFBIRTH));
  }
  
  public void validateDateOfBirth()
  {
    this.validateAttribute(DATEOFBIRTH);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDateOfBirthMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(DATEOFBIRTH);
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
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void validateFirstName()
  {
    this.validateAttribute(FIRSTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFirstNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(FIRSTNAME);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Boolean getIsIPTRecipient()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISIPTRECIPIENT));
  }
  
  public void validateIsIPTRecipient()
  {
    this.validateAttribute(ISIPTRECIPIENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsIPTRecipientMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(ISIPTRECIPIENT);
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
  
  public Boolean getIsITNRecipient()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISITNRECIPIENT));
  }
  
  public void validateIsITNRecipient()
  {
    this.validateAttribute(ISITNRECIPIENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsITNRecipientMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(ISITNRECIPIENT);
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
  
  public Boolean getIsMDSSUser()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISMDSSUSER));
  }
  
  public void validateIsMDSSUser()
  {
    this.validateAttribute(ISMDSSUSER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsMDSSUserMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(ISMDSSUSER);
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
  
  public Boolean getIsPatient()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISPATIENT));
  }
  
  public void validateIsPatient()
  {
    this.validateAttribute(ISPATIENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsPatientMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(ISPATIENT);
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
  
  public Boolean getIsSprayLeader()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYLEADER));
  }
  
  public void validateIsSprayLeader()
  {
    this.validateAttribute(ISSPRAYLEADER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsSprayLeaderMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(ISSPRAYLEADER);
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
  
  public Boolean getIsSprayOperator()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYOPERATOR));
  }
  
  public void validateIsSprayOperator()
  {
    this.validateAttribute(ISSPRAYOPERATOR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsSprayOperatorMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(ISSPRAYOPERATOR);
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
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void validateLastName()
  {
    this.validateAttribute(LASTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(LASTNAME);
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
  
  public String getMemberId()
  {
    return getValue(MEMBERID);
  }
  
  public void validateMemberId()
  {
    this.validateAttribute(MEMBERID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMemberIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(MEMBERID);
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
  
  public String getPassword()
  {
    return getValue(PASSWORD);
  }
  
  public void validatePassword()
  {
    this.validateAttribute(PASSWORD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPasswordMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(PASSWORD);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getResidentialGeoEntity()
  {
    if (getValue(RESIDENTIALGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(RESIDENTIALGEOENTITY));
    }
  }
  
  public void validateResidentialGeoEntity()
  {
    this.validateAttribute(RESIDENTIALGEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getResidentialGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(RESIDENTIALGEOENTITY);
  }
  
  public void setResidentialGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(SEX);
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
  
  public String getUsername()
  {
    return getValue(USERNAME);
  }
  
  public void validateUsername()
  {
    this.validateAttribute(USERNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUsernameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(USERNAME);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getWorkGeoEntity()
  {
    if (getValue(WORKGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(WORKGEOENTITY));
    }
  }
  
  public void validateWorkGeoEntity()
  {
    this.validateAttribute(WORKGEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWorkGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonExcelView.CLASS);
    return mdClassIF.definesAttribute(WORKGEOENTITY);
  }
  
  public void setWorkGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PersonExcelView get(String id)
  {
    return (PersonExcelView) com.terraframe.mojo.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
