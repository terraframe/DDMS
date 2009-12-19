package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = 1256869971)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedCaseView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AggregatedCaseViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.AggregatedCaseView";
  public static java.lang.String AGEGROUP = "ageGroup";
  public static java.lang.String CASEID = "caseId";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String PERIODYEAR = "periodYear";
  private static final long serialVersionUID = 1256869971;
  
  public AggregatedCaseViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.surveillance.AggregatedAgeGroup getAgeGroup()
  {
    if (getValue(AGEGROUP).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.AggregatedAgeGroup.get(getValue(AGEGROUP));
    }
  }
  
  public void validateAgeGroup()
  {
    this.validateAttribute(AGEGROUP);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgeGroupMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return mdClassIF.definesAttribute(AGEGROUP);
  }
  
  public void setAgeGroup(dss.vector.solutions.surveillance.AggregatedAgeGroup value)
  {
    if(value == null)
    {
      setValue(AGEGROUP, "");
    }
    else
    {
      setValue(AGEGROUP, value.getId());
    }
  }
  
  public String getCaseId()
  {
    return getValue(CASEID);
  }
  
  public void validateCaseId()
  {
    this.validateAttribute(CASEID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCaseIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return mdClassIF.definesAttribute(CASEID);
  }
  
  public void setCaseId(String value)
  {
    if(value == null)
    {
      setValue(CASEID, "");
    }
    else
    {
      setValue(CASEID, value);
    }
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void validatePeriod()
  {
    this.validateAttribute(PERIOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeriodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return mdClassIF.definesAttribute(PERIOD);
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.surveillance.PeriodType> getPeriodType()
  {
    return (java.util.List<dss.vector.solutions.surveillance.PeriodType>) getEnumValues(PERIODTYPE);
  }
  
  public void addPeriodType(dss.vector.solutions.surveillance.PeriodType value)
  {
    if(value != null)
    {
      addEnumItem(PERIODTYPE, value.getId());
    }
  }
  
  public void removePeriodType(dss.vector.solutions.surveillance.PeriodType value)
  {
    if(value != null)
    {
      removeEnumItem(PERIODTYPE, value.getId());
    }
  }
  
  public void clearPeriodType()
  {
    clearEnum(PERIODTYPE);
  }
  
  public void validatePeriodType()
  {
    this.validateAttribute(PERIODTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeriodTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return mdClassIF.definesAttribute(PERIODTYPE);
  }
  
  public Integer getPeriodYear()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIODYEAR));
  }
  
  public void validatePeriodYear()
  {
    this.validateAttribute(PERIODYEAR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeriodYearMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return mdClassIF.definesAttribute(PERIODYEAR);
  }
  
  public void setPeriodYear(Integer value)
  {
    if(value == null)
    {
      setValue(PERIODYEAR, "");
    }
    else
    {
      setValue(PERIODYEAR, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AggregatedCaseView get(String id)
  {
    return (AggregatedCaseView) com.terraframe.mojo.business.View.get(id);
  }
  
  public void applyAll(dss.vector.solutions.surveillance.CaseTreatment[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethod[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStock[] stock, dss.vector.solutions.surveillance.CaseDiagnostic[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferral[] referrals)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyAll(java.lang.String id, dss.vector.solutions.surveillance.CaseTreatment[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethod[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStock[] stock, dss.vector.solutions.surveillance.CaseDiagnostic[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferral[] referrals)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    _instance.applyAll(treatments, treatmentMethods, stock, diagnosticMethods, referrals);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.surveillance.CaseDiagnostic[] getDiagnosticMethods()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnostic[] getDiagnosticMethods(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getDiagnosticMethods();
  }
  
  public dss.vector.solutions.surveillance.CaseReferral[] getReferrals()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseReferral[] getReferrals(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getReferrals();
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentMethod[] getTreatmentMethods()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentMethod[] getTreatmentMethods(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getTreatmentMethods();
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentStock[] getTreatmentStocks()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentStock[] getTreatmentStocks(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getTreatmentStocks();
  }
  
  public dss.vector.solutions.surveillance.CaseTreatment[] getTreatments()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatment[] getTreatments(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getTreatments();
  }
  
  public void lockCase()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void lockCase(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    _instance.lockCase();
  }
  
  public void unlockCase()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void unlockCase(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    _instance.unlockCase();
  }
  
  public static void validateEpiDate(java.lang.String periodType, java.lang.Integer period, java.lang.Integer year)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void validateSearchCriteria(java.lang.String geoId, java.lang.String periodType, java.lang.Integer period, java.lang.Integer year)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
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
