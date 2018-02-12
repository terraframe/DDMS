/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -730196986)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedIPTView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AggregatedIPTViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.AggregatedIPTView";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISPLAYDOSE = "displayDose";
  public static java.lang.String DISPLAYPATIENTS = "displayPatients";
  public static java.lang.String DISPLAYTREATMENTS = "displayTreatments";
  public static java.lang.String DISPLAYVISITS = "displayVisits";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBERNATALCARE = "numberNatalCare";
  public static java.lang.String NUMBERPREGNANT = "numberPregnant";
  public static java.lang.String NUMBERPREGNANTITN = "numberPregnantITN";
  public static java.lang.String NUMBERPREGNANTIRON = "numberPregnantIron";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String PERIODYEAR = "periodYear";
  public static java.lang.String SEARCHTYPE = "searchType";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TOTALITN = "totalITN";
  private static final long serialVersionUID = -730196986;
  
  public AggregatedIPTViewBase()
  {
    super();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CONCRETEID);
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
  
  public dss.vector.solutions.ontology.Term getDisplayDose()
  {
    if (getValue(DISPLAYDOSE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DISPLAYDOSE));
    }
  }
  
  public String getDisplayDoseId()
  {
    return getValue(DISPLAYDOSE);
  }
  
  public void validateDisplayDose()
  {
    this.validateAttribute(DISPLAYDOSE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDisplayDoseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISPLAYDOSE);
  }
  
  public void setDisplayDose(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DISPLAYDOSE, "");
    }
    else
    {
      setValue(DISPLAYDOSE, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getDisplayPatients()
  {
    if (getValue(DISPLAYPATIENTS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DISPLAYPATIENTS));
    }
  }
  
  public String getDisplayPatientsId()
  {
    return getValue(DISPLAYPATIENTS);
  }
  
  public void validateDisplayPatients()
  {
    this.validateAttribute(DISPLAYPATIENTS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDisplayPatientsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISPLAYPATIENTS);
  }
  
  public void setDisplayPatients(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DISPLAYPATIENTS, "");
    }
    else
    {
      setValue(DISPLAYPATIENTS, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getDisplayTreatments()
  {
    if (getValue(DISPLAYTREATMENTS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DISPLAYTREATMENTS));
    }
  }
  
  public String getDisplayTreatmentsId()
  {
    return getValue(DISPLAYTREATMENTS);
  }
  
  public void validateDisplayTreatments()
  {
    this.validateAttribute(DISPLAYTREATMENTS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDisplayTreatmentsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISPLAYTREATMENTS);
  }
  
  public void setDisplayTreatments(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DISPLAYTREATMENTS, "");
    }
    else
    {
      setValue(DISPLAYTREATMENTS, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getDisplayVisits()
  {
    if (getValue(DISPLAYVISITS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DISPLAYVISITS));
    }
  }
  
  public String getDisplayVisitsId()
  {
    return getValue(DISPLAYVISITS);
  }
  
  public void validateDisplayVisits()
  {
    this.validateAttribute(DISPLAYVISITS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDisplayVisitsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISPLAYVISITS);
  }
  
  public void setDisplayVisits(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DISPLAYVISITS, "");
    }
    else
    {
      setValue(DISPLAYVISITS, value.getId());
    }
  }
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void validateEndDate()
  {
    this.validateAttribute(ENDDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getEndDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(ENDDATE);
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void validateGeoId()
  {
    this.validateAttribute(GEOID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getGeoIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Integer getNumberNatalCare()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERNATALCARE));
  }
  
  public void validateNumberNatalCare()
  {
    this.validateAttribute(NUMBERNATALCARE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNumberNatalCareMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NUMBERNATALCARE);
  }
  
  public void setNumberNatalCare(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERNATALCARE, "");
    }
    else
    {
      setValue(NUMBERNATALCARE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberPregnant()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANT));
  }
  
  public void validateNumberPregnant()
  {
    this.validateAttribute(NUMBERPREGNANT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNumberPregnantMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NUMBERPREGNANT);
  }
  
  public void setNumberPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANT, "");
    }
    else
    {
      setValue(NUMBERPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberPregnantITN()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTITN));
  }
  
  public void validateNumberPregnantITN()
  {
    this.validateAttribute(NUMBERPREGNANTITN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNumberPregnantITNMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NUMBERPREGNANTITN);
  }
  
  public void setNumberPregnantITN(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTITN, "");
    }
    else
    {
      setValue(NUMBERPREGNANTITN, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberPregnantIron()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTIRON));
  }
  
  public void validateNumberPregnantIron()
  {
    this.validateAttribute(NUMBERPREGNANTIRON);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNumberPregnantIronMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NUMBERPREGNANTIRON);
  }
  
  public void setNumberPregnantIron(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTIRON, "");
    }
    else
    {
      setValue(NUMBERPREGNANTIRON, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void validatePeriod()
  {
    this.validateAttribute(PERIOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getPeriodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(PERIOD);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF getPeriodTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF)mdClassIF.definesAttribute(PERIODTYPE);
  }
  
  public Integer getPeriodYear()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIODYEAR));
  }
  
  public void validatePeriodYear()
  {
    this.validateAttribute(PERIODYEAR);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getPeriodYearMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(PERIODYEAR);
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
  
  public Boolean getSearchType()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SEARCHTYPE));
  }
  
  public void validateSearchType()
  {
    this.validateAttribute(SEARCHTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getSearchTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(SEARCHTYPE);
  }
  
  public void setSearchType(Boolean value)
  {
    if(value == null)
    {
      setValue(SEARCHTYPE, "");
    }
    else
    {
      setValue(SEARCHTYPE, java.lang.Boolean.toString(value));
    }
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void validateStartDate()
  {
    this.validateAttribute(STARTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getStartDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(STARTDATE);
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public Integer getTotalITN()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTALITN));
  }
  
  public void validateTotalITN()
  {
    this.validateAttribute(TOTALITN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getTotalITNMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(TOTALITN);
  }
  
  public void setTotalITN(Integer value)
  {
    if(value == null)
    {
      setValue(TOTALITN, "");
    }
    else
    {
      setValue(TOTALITN, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AggregatedIPTView get(String id)
  {
    return (AggregatedIPTView) com.runwaysdk.business.View.get(id);
  }
  
  public void applyAll(dss.vector.solutions.intervention.monitor.IPTPatients[] patients, dss.vector.solutions.intervention.monitor.IPTANCVisit[] visits, dss.vector.solutions.intervention.monitor.IPTDose[] doses, dss.vector.solutions.intervention.monitor.IPTTreatment[] treatments)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyAll(java.lang.String id, dss.vector.solutions.intervention.monitor.IPTPatients[] patients, dss.vector.solutions.intervention.monitor.IPTANCVisit[] visits, dss.vector.solutions.intervention.monitor.IPTDose[] doses, dss.vector.solutions.intervention.monitor.IPTTreatment[] treatments)
  {
    AggregatedIPTView _instance = AggregatedIPTView.get(id);
    _instance.applyAll(patients, visits, doses, treatments);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    AggregatedIPTView _instance = AggregatedIPTView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.intervention.monitor.IPTANCVisit[] getIPTANCVisits()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IPTANCVisit[] getIPTANCVisits(java.lang.String id)
  {
    AggregatedIPTView _instance = AggregatedIPTView.get(id);
    return _instance.getIPTANCVisits();
  }
  
  public dss.vector.solutions.intervention.monitor.IPTDose[] getIPTDoses()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IPTDose[] getIPTDoses(java.lang.String id)
  {
    AggregatedIPTView _instance = AggregatedIPTView.get(id);
    return _instance.getIPTDoses();
  }
  
  public dss.vector.solutions.intervention.monitor.IPTPatients[] getIPTPatients()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IPTPatients[] getIPTPatients(java.lang.String id)
  {
    AggregatedIPTView _instance = AggregatedIPTView.get(id);
    return _instance.getIPTPatients();
  }
  
  public dss.vector.solutions.intervention.monitor.IPTTreatment[] getIPTTreatments()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IPTTreatment[] getIPTTreatments(java.lang.String id)
  {
    AggregatedIPTView _instance = AggregatedIPTView.get(id);
    return _instance.getIPTTreatments();
  }
  
  public dss.vector.solutions.intervention.monitor.AggregatedIPTView searchByView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPTView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedIPTView searchByView(java.lang.String id)
  {
    AggregatedIPTView _instance = AggregatedIPTView.get(id);
    return _instance.searchByView();
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
