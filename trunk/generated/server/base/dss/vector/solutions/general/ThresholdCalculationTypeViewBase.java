package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 925085965)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ThresholdCalculationTypeView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ThresholdCalculationTypeViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ThresholdCalculationTypeView";
  public static java.lang.String CALCULATIONINTERVAL = "calculationInterval";
  public static java.lang.String CASETYPES = "caseTypes";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String COUNTINGMETHOD = "countingMethod";
  public static java.lang.String EPIDEMICUNIVERSAL = "epidemicUniversal";
  public static java.lang.String ID = "id";
  public static java.lang.String PRIORYEARS = "priorYears";
  public static java.lang.String T1METHOD = "t1Method";
  public static java.lang.String T2METHOD = "t2Method";
  public static java.lang.String WEEKSAFTER = "weeksAfter";
  public static java.lang.String WEEKSBEFORE = "weeksBefore";
  public static java.lang.String WEIGHT0 = "weight0";
  public static java.lang.String WEIGHT1 = "weight1";
  public static java.lang.String WEIGHT2 = "weight2";
  public static java.lang.String WEIGHT3 = "weight3";
  public static java.lang.String WEIGHT4 = "weight4";
  public static java.lang.String WEIGHT5 = "weight5";
  public static java.lang.String WEIGHT6 = "weight6";
  public static java.lang.String WEIGHT7 = "weight7";
  public static java.lang.String WEIGHT8 = "weight8";
  public static java.lang.String WEIGHT9 = "weight9";
  private static final long serialVersionUID = 925085965;
  
  public ThresholdCalculationTypeViewBase()
  {
    super();
  }
  
  public Boolean getCalculationInterval()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CALCULATIONINTERVAL));
  }
  
  public void validateCalculationInterval()
  {
    this.validateAttribute(CALCULATIONINTERVAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCalculationIntervalMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(CALCULATIONINTERVAL);
  }
  
  public void setCalculationInterval(Boolean value)
  {
    if(value == null)
    {
      setValue(CALCULATIONINTERVAL, "");
    }
    else
    {
      setValue(CALCULATIONINTERVAL, java.lang.Boolean.toString(value));
    }
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationCaseTypes> getCaseTypes()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationCaseTypes>) getEnumValues(CASETYPES);
  }
  
  public void addCaseTypes(dss.vector.solutions.general.ThresholdCalculationCaseTypes value)
  {
    if(value != null)
    {
      addEnumItem(CASETYPES, value.getId());
    }
  }
  
  public void removeCaseTypes(dss.vector.solutions.general.ThresholdCalculationCaseTypes value)
  {
    if(value != null)
    {
      removeEnumItem(CASETYPES, value.getId());
    }
  }
  
  public void clearCaseTypes()
  {
    clearEnum(CASETYPES);
  }
  
  public void validateCaseTypes()
  {
    this.validateAttribute(CASETYPES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCaseTypesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(CASETYPES);
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
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
  
  public Boolean getCountingMethod()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(COUNTINGMETHOD));
  }
  
  public void validateCountingMethod()
  {
    this.validateAttribute(COUNTINGMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCountingMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(COUNTINGMETHOD);
  }
  
  public void setCountingMethod(Boolean value)
  {
    if(value == null)
    {
      setValue(COUNTINGMETHOD, "");
    }
    else
    {
      setValue(COUNTINGMETHOD, java.lang.Boolean.toString(value));
    }
  }
  
  public dss.vector.solutions.geo.GeoHierarchy getEpidemicUniversal()
  {
    if (getValue(EPIDEMICUNIVERSAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchy.get(getValue(EPIDEMICUNIVERSAL));
    }
  }
  
  public void validateEpidemicUniversal()
  {
    this.validateAttribute(EPIDEMICUNIVERSAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEpidemicUniversalMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(EPIDEMICUNIVERSAL);
  }
  
  public void setEpidemicUniversal(dss.vector.solutions.geo.GeoHierarchy value)
  {
    if(value == null)
    {
      setValue(EPIDEMICUNIVERSAL, "");
    }
    else
    {
      setValue(EPIDEMICUNIVERSAL, value.getId());
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Integer getPriorYears()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PRIORYEARS));
  }
  
  public void validatePriorYears()
  {
    this.validateAttribute(PRIORYEARS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPriorYearsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(PRIORYEARS);
  }
  
  public void setPriorYears(Integer value)
  {
    if(value == null)
    {
      setValue(PRIORYEARS, "");
    }
    else
    {
      setValue(PRIORYEARS, java.lang.Integer.toString(value));
    }
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationMethod> getT1Method()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationMethod>) getEnumValues(T1METHOD);
  }
  
  public void addT1Method(dss.vector.solutions.general.ThresholdCalculationMethod value)
  {
    if(value != null)
    {
      addEnumItem(T1METHOD, value.getId());
    }
  }
  
  public void removeT1Method(dss.vector.solutions.general.ThresholdCalculationMethod value)
  {
    if(value != null)
    {
      removeEnumItem(T1METHOD, value.getId());
    }
  }
  
  public void clearT1Method()
  {
    clearEnum(T1METHOD);
  }
  
  public void validateT1Method()
  {
    this.validateAttribute(T1METHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getT1MethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(T1METHOD);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationMethod> getT2Method()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationMethod>) getEnumValues(T2METHOD);
  }
  
  public void addT2Method(dss.vector.solutions.general.ThresholdCalculationMethod value)
  {
    if(value != null)
    {
      addEnumItem(T2METHOD, value.getId());
    }
  }
  
  public void removeT2Method(dss.vector.solutions.general.ThresholdCalculationMethod value)
  {
    if(value != null)
    {
      removeEnumItem(T2METHOD, value.getId());
    }
  }
  
  public void clearT2Method()
  {
    clearEnum(T2METHOD);
  }
  
  public void validateT2Method()
  {
    this.validateAttribute(T2METHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getT2MethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(T2METHOD);
  }
  
  public Integer getWeeksAfter()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WEEKSAFTER));
  }
  
  public void validateWeeksAfter()
  {
    this.validateAttribute(WEEKSAFTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeeksAfterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEEKSAFTER);
  }
  
  public void setWeeksAfter(Integer value)
  {
    if(value == null)
    {
      setValue(WEEKSAFTER, "");
    }
    else
    {
      setValue(WEEKSAFTER, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getWeeksBefore()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WEEKSBEFORE));
  }
  
  public void validateWeeksBefore()
  {
    this.validateAttribute(WEEKSBEFORE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeeksBeforeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEEKSBEFORE);
  }
  
  public void setWeeksBefore(Integer value)
  {
    if(value == null)
    {
      setValue(WEEKSBEFORE, "");
    }
    else
    {
      setValue(WEEKSBEFORE, java.lang.Integer.toString(value));
    }
  }
  
  public Double getWeight0()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT0));
  }
  
  public void validateWeight0()
  {
    this.validateAttribute(WEIGHT0);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight0Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT0);
  }
  
  public void setWeight0(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT0, "");
    }
    else
    {
      setValue(WEIGHT0, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight1()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT1));
  }
  
  public void validateWeight1()
  {
    this.validateAttribute(WEIGHT1);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight1Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT1);
  }
  
  public void setWeight1(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT1, "");
    }
    else
    {
      setValue(WEIGHT1, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight2()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT2));
  }
  
  public void validateWeight2()
  {
    this.validateAttribute(WEIGHT2);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight2Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT2);
  }
  
  public void setWeight2(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT2, "");
    }
    else
    {
      setValue(WEIGHT2, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight3()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT3));
  }
  
  public void validateWeight3()
  {
    this.validateAttribute(WEIGHT3);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight3Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT3);
  }
  
  public void setWeight3(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT3, "");
    }
    else
    {
      setValue(WEIGHT3, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight4()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT4));
  }
  
  public void validateWeight4()
  {
    this.validateAttribute(WEIGHT4);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight4Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT4);
  }
  
  public void setWeight4(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT4, "");
    }
    else
    {
      setValue(WEIGHT4, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight5()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT5));
  }
  
  public void validateWeight5()
  {
    this.validateAttribute(WEIGHT5);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight5Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT5);
  }
  
  public void setWeight5(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT5, "");
    }
    else
    {
      setValue(WEIGHT5, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight6()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT6));
  }
  
  public void validateWeight6()
  {
    this.validateAttribute(WEIGHT6);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight6Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT6);
  }
  
  public void setWeight6(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT6, "");
    }
    else
    {
      setValue(WEIGHT6, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight7()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT7));
  }
  
  public void validateWeight7()
  {
    this.validateAttribute(WEIGHT7);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight7Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT7);
  }
  
  public void setWeight7(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT7, "");
    }
    else
    {
      setValue(WEIGHT7, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight8()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT8));
  }
  
  public void validateWeight8()
  {
    this.validateAttribute(WEIGHT8);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight8Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT8);
  }
  
  public void setWeight8(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT8, "");
    }
    else
    {
      setValue(WEIGHT8, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight9()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT9));
  }
  
  public void validateWeight9()
  {
    this.validateAttribute(WEIGHT9);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeight9Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationTypeView.CLASS);
    return mdClassIF.definesAttribute(WEIGHT9);
  }
  
  public void setWeight9(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT9, "");
    }
    else
    {
      setValue(WEIGHT9, java.lang.Double.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ThresholdCalculationTypeView get(String id)
  {
    return (ThresholdCalculationTypeView) com.runwaysdk.business.View.get(id);
  }
  
  public static void calculateFacilityThresholds(dss.vector.solutions.general.ThresholdCalculationTypeView thresholdCalculation, java.lang.Boolean currentYear)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdCalculationTypeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void calculatePoliticalThresholds(dss.vector.solutions.general.ThresholdCalculationTypeView thresholdCalculation, java.lang.Boolean currentYear)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdCalculationTypeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void calculateThresholds(dss.vector.solutions.general.ThresholdCalculationTypeView thresholdCalculation, java.lang.Boolean currentYear)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdCalculationTypeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.general.ThresholdCalculationTypeView getCalculationThreshold()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdCalculationTypeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.Integer getPercentComplete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ThresholdCalculationTypeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
