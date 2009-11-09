package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 1181288253)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualIPTExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class IndividualIPTExcelViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.IndividualIPTExcelView";
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
  private static final long serialVersionUID = 1181288253;
  
  public IndividualIPTExcelViewBase()
  {
    super();
  }
  
  public String getAdministratorName()
  {
    return getValue(ADMINISTRATORNAME);
  }
  
  public void validateAdministratorName()
  {
    this.validateAttribute(ADMINISTRATORNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAdministratorNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(ADMINISTRATORNAME);
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
  
  public String getAdministratorSurname()
  {
    return getValue(ADMINISTRATORSURNAME);
  }
  
  public void validateAdministratorSurname()
  {
    this.validateAttribute(ADMINISTRATORSURNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAdministratorSurnameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(ADMINISTRATORSURNAME);
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
  
  public String getDoseNumber()
  {
    return getValue(DOSENUMBER);
  }
  
  public void validateDoseNumber()
  {
    this.validateAttribute(DOSENUMBER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDoseNumberMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(DOSENUMBER);
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
  
  public String getDoseType()
  {
    return getValue(DOSETYPE);
  }
  
  public void validateDoseType()
  {
    this.validateAttribute(DOSETYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDoseTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(DOSETYPE);
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
  
  public dss.vector.solutions.geo.generated.HealthFacility getFacility()
  {
    if (getValue(FACILITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.HealthFacility.get(getValue(FACILITY));
    }
  }
  
  public void validateFacility()
  {
    this.validateAttribute(FACILITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFacilityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(FACILITY);
  }
  
  public void setFacility(dss.vector.solutions.geo.generated.HealthFacility value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Boolean getIsANCVisit()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISANCVISIT));
  }
  
  public void validateIsANCVisit()
  {
    this.validateAttribute(ISANCVISIT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsANCVisitMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(ISANCVISIT);
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
  
  public Integer getNumberOfRecievedITNs()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFRECIEVEDITNS));
  }
  
  public void validateNumberOfRecievedITNs()
  {
    this.validateAttribute(NUMBEROFRECIEVEDITNS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberOfRecievedITNsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(NUMBEROFRECIEVEDITNS);
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
  
  public java.util.Date getPatientDOB()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(PATIENTDOB));
  }
  
  public void validatePatientDOB()
  {
    this.validateAttribute(PATIENTDOB);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPatientDOBMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(PATIENTDOB);
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
  
  public String getPatientFirstName()
  {
    return getValue(PATIENTFIRSTNAME);
  }
  
  public void validatePatientFirstName()
  {
    this.validateAttribute(PATIENTFIRSTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPatientFirstNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(PATIENTFIRSTNAME);
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
  
  public String getPatientLastName()
  {
    return getValue(PATIENTLASTNAME);
  }
  
  public void validatePatientLastName()
  {
    this.validateAttribute(PATIENTLASTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPatientLastNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(PATIENTLASTNAME);
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
  
  public String getPatientType()
  {
    return getValue(PATIENTTYPE);
  }
  
  public void validatePatientType()
  {
    this.validateAttribute(PATIENTTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPatientTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(PATIENTTYPE);
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
  
  public Boolean getRecievedITN()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECIEVEDITN));
  }
  
  public void validateRecievedITN()
  {
    this.validateAttribute(RECIEVEDITN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRecievedITNMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(RECIEVEDITN);
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
  
  public Boolean getRecievedSupplement()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECIEVEDSUPPLEMENT));
  }
  
  public void validateRecievedSupplement()
  {
    this.validateAttribute(RECIEVEDSUPPLEMENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRecievedSupplementMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(RECIEVEDSUPPLEMENT);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getResidentialLocation()
  {
    if (getValue(RESIDENTIALLOCATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(RESIDENTIALLOCATION));
    }
  }
  
  public void validateResidentialLocation()
  {
    this.validateAttribute(RESIDENTIALLOCATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getResidentialLocationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(RESIDENTIALLOCATION);
  }
  
  public void setResidentialLocation(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  public java.util.Date getServiceDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SERVICEDATE));
  }
  
  public void validateServiceDate()
  {
    this.validateAttribute(SERVICEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getServiceDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(SERVICEDATE);
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
  
  public String getVisitNumber()
  {
    return getValue(VISITNUMBER);
  }
  
  public void validateVisitNumber()
  {
    this.validateAttribute(VISITNUMBER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getVisitNumberMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualIPTExcelView.CLASS);
    return mdClassIF.definesAttribute(VISITNUMBER);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static IndividualIPTExcelView get(String id)
  {
    return (IndividualIPTExcelView) com.terraframe.mojo.business.View.get(id);
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
