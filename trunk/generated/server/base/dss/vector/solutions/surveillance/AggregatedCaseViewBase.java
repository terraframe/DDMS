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
package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = 1862823829)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedCaseView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AggregatedCaseViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.AggregatedCaseView";
  public static java.lang.String AGEGROUP = "ageGroup";
  public static java.lang.String CASEDIAGNOSISTYPE = "caseDiagnosisType";
  public static java.lang.String CASEDIAGNOSTIC = "caseDiagnostic";
  public static java.lang.String CASEDISEASEMANIFESTATION = "caseDiseaseManifestation";
  public static java.lang.String CASEPATIENTTYPE = "casePatientType";
  public static java.lang.String CASEREFERRALS = "caseReferrals";
  public static java.lang.String CASESTOCKREFERRAL = "caseStockReferral";
  public static java.lang.String CASESTOCKS = "caseStocks";
  public static java.lang.String CASETREATMENTMETHOD = "caseTreatmentMethod";
  public static java.lang.String CASETREATMENTS = "caseTreatments";
  public static java.lang.String CASES = "cases";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DEATHS = "deaths";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NEGATIVECASES = "negativeCases";
  public static java.lang.String POSITIVECASES = "positiveCases";
  public static java.lang.String STARTDATE = "startDate";
  private static final long serialVersionUID = 1862823829;
  
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
  
  public String getAgeGroupId()
  {
    return getValue(AGEGROUP);
  }
  
  public void validateAgeGroup()
  {
    this.validateAttribute(AGEGROUP);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getAgeGroupMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(AGEGROUP);
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
  
  public dss.vector.solutions.ontology.Term getCaseDiagnosisType()
  {
    if (getValue(CASEDIAGNOSISTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASEDIAGNOSISTYPE));
    }
  }
  
  public String getCaseDiagnosisTypeId()
  {
    return getValue(CASEDIAGNOSISTYPE);
  }
  
  public void validateCaseDiagnosisType()
  {
    this.validateAttribute(CASEDIAGNOSISTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCaseDiagnosisTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASEDIAGNOSISTYPE);
  }
  
  public void setCaseDiagnosisType(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASEDIAGNOSISTYPE, "");
    }
    else
    {
      setValue(CASEDIAGNOSISTYPE, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getCaseDiagnostic()
  {
    if (getValue(CASEDIAGNOSTIC).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASEDIAGNOSTIC));
    }
  }
  
  public String getCaseDiagnosticId()
  {
    return getValue(CASEDIAGNOSTIC);
  }
  
  public void validateCaseDiagnostic()
  {
    this.validateAttribute(CASEDIAGNOSTIC);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCaseDiagnosticMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASEDIAGNOSTIC);
  }
  
  public void setCaseDiagnostic(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASEDIAGNOSTIC, "");
    }
    else
    {
      setValue(CASEDIAGNOSTIC, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getCaseDiseaseManifestation()
  {
    if (getValue(CASEDISEASEMANIFESTATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASEDISEASEMANIFESTATION));
    }
  }
  
  public String getCaseDiseaseManifestationId()
  {
    return getValue(CASEDISEASEMANIFESTATION);
  }
  
  public void validateCaseDiseaseManifestation()
  {
    this.validateAttribute(CASEDISEASEMANIFESTATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCaseDiseaseManifestationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASEDISEASEMANIFESTATION);
  }
  
  public void setCaseDiseaseManifestation(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASEDISEASEMANIFESTATION, "");
    }
    else
    {
      setValue(CASEDISEASEMANIFESTATION, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getCasePatientType()
  {
    if (getValue(CASEPATIENTTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASEPATIENTTYPE));
    }
  }
  
  public String getCasePatientTypeId()
  {
    return getValue(CASEPATIENTTYPE);
  }
  
  public void validateCasePatientType()
  {
    this.validateAttribute(CASEPATIENTTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCasePatientTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASEPATIENTTYPE);
  }
  
  public void setCasePatientType(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASEPATIENTTYPE, "");
    }
    else
    {
      setValue(CASEPATIENTTYPE, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getCaseReferrals()
  {
    if (getValue(CASEREFERRALS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASEREFERRALS));
    }
  }
  
  public String getCaseReferralsId()
  {
    return getValue(CASEREFERRALS);
  }
  
  public void validateCaseReferrals()
  {
    this.validateAttribute(CASEREFERRALS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCaseReferralsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASEREFERRALS);
  }
  
  public void setCaseReferrals(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASEREFERRALS, "");
    }
    else
    {
      setValue(CASEREFERRALS, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getCaseStockReferral()
  {
    if (getValue(CASESTOCKREFERRAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASESTOCKREFERRAL));
    }
  }
  
  public String getCaseStockReferralId()
  {
    return getValue(CASESTOCKREFERRAL);
  }
  
  public void validateCaseStockReferral()
  {
    this.validateAttribute(CASESTOCKREFERRAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCaseStockReferralMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASESTOCKREFERRAL);
  }
  
  public void setCaseStockReferral(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASESTOCKREFERRAL, "");
    }
    else
    {
      setValue(CASESTOCKREFERRAL, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getCaseStocks()
  {
    if (getValue(CASESTOCKS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASESTOCKS));
    }
  }
  
  public String getCaseStocksId()
  {
    return getValue(CASESTOCKS);
  }
  
  public void validateCaseStocks()
  {
    this.validateAttribute(CASESTOCKS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCaseStocksMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASESTOCKS);
  }
  
  public void setCaseStocks(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASESTOCKS, "");
    }
    else
    {
      setValue(CASESTOCKS, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getCaseTreatmentMethod()
  {
    if (getValue(CASETREATMENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASETREATMENTMETHOD));
    }
  }
  
  public String getCaseTreatmentMethodId()
  {
    return getValue(CASETREATMENTMETHOD);
  }
  
  public void validateCaseTreatmentMethod()
  {
    this.validateAttribute(CASETREATMENTMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCaseTreatmentMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASETREATMENTMETHOD);
  }
  
  public void setCaseTreatmentMethod(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASETREATMENTMETHOD, "");
    }
    else
    {
      setValue(CASETREATMENTMETHOD, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getCaseTreatments()
  {
    if (getValue(CASETREATMENTS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CASETREATMENTS));
    }
  }
  
  public String getCaseTreatmentsId()
  {
    return getValue(CASETREATMENTS);
  }
  
  public void validateCaseTreatments()
  {
    this.validateAttribute(CASETREATMENTS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCaseTreatmentsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CASETREATMENTS);
  }
  
  public void setCaseTreatments(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CASETREATMENTS, "");
    }
    else
    {
      setValue(CASETREATMENTS, value.getId());
    }
  }
  
  public Integer getCases()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASES));
  }
  
  public void validateCases()
  {
    this.validateAttribute(CASES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getCasesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(CASES);
  }
  
  public void setCases(Integer value)
  {
    if(value == null)
    {
      setValue(CASES, "");
    }
    else
    {
      setValue(CASES, java.lang.Integer.toString(value));
    }
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
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
  
  public Integer getDeaths()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHS));
  }
  
  public void validateDeaths()
  {
    this.validateAttribute(DEATHS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getDeathsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(DEATHS);
  }
  
  public void setDeaths(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHS, "");
    }
    else
    {
      setValue(DEATHS, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
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
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(GEOENTITY);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Integer getNegativeCases()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NEGATIVECASES));
  }
  
  public void validateNegativeCases()
  {
    this.validateAttribute(NEGATIVECASES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNegativeCasesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NEGATIVECASES);
  }
  
  public void setNegativeCases(Integer value)
  {
    if(value == null)
    {
      setValue(NEGATIVECASES, "");
    }
    else
    {
      setValue(NEGATIVECASES, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPositiveCases()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POSITIVECASES));
  }
  
  public void validatePositiveCases()
  {
    this.validateAttribute(POSITIVECASES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getPositiveCasesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(POSITIVECASES);
  }
  
  public void setPositiveCases(Integer value)
  {
    if(value == null)
    {
      setValue(POSITIVECASES, "");
    }
    else
    {
      setValue(POSITIVECASES, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.AggregatedCaseView.CLASS);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AggregatedCaseView get(String id)
  {
    return (AggregatedCaseView) com.runwaysdk.business.View.get(id);
  }
  
  public void applyAll(dss.vector.solutions.surveillance.CaseTreatmentView[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodView[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockView[] stock, dss.vector.solutions.surveillance.CaseDiagnosticView[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralView[] referrals, dss.vector.solutions.surveillance.CaseStockReferralView[] stockReferrals, dss.vector.solutions.surveillance.CaseDiagnosisTypeView[] diagnosticTypes, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView[][] diagnosticTypeAmounts, dss.vector.solutions.surveillance.CaseDiseaseManifestationView[] diseaseManifestations, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView[][] diseaseManifestationAmounts, dss.vector.solutions.surveillance.CasePatientTypeView[] patientTypes, dss.vector.solutions.surveillance.CasePatientTypeAmountView[][] patientTypeAmounts)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyAll(java.lang.String id, dss.vector.solutions.surveillance.CaseTreatmentView[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodView[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockView[] stock, dss.vector.solutions.surveillance.CaseDiagnosticView[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralView[] referrals, dss.vector.solutions.surveillance.CaseStockReferralView[] stockReferrals, dss.vector.solutions.surveillance.CaseDiagnosisTypeView[] diagnosticTypes, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView[][] diagnosticTypeAmounts, dss.vector.solutions.surveillance.CaseDiseaseManifestationView[] diseaseManifestations, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView[][] diseaseManifestationAmounts, dss.vector.solutions.surveillance.CasePatientTypeView[] patientTypes, dss.vector.solutions.surveillance.CasePatientTypeAmountView[][] patientTypeAmounts)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    _instance.applyAll(treatments, treatmentMethods, stock, diagnosticMethods, referrals, stockReferrals, diagnosticTypes, diagnosticTypeAmounts, diseaseManifestations, diseaseManifestationAmounts, patientTypes, patientTypeAmounts);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.surveillance.CaseDiagnosticView[] getDiagnosticMethods()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnosticView[] getDiagnosticMethods(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getDiagnosticMethods();
  }
  
  public dss.vector.solutions.surveillance.CaseDiagnosisTypeView[] getDiagnosticTypes()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnosisTypeView[] getDiagnosticTypes(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getDiagnosticTypes();
  }
  
  public dss.vector.solutions.surveillance.CaseDiseaseManifestationView[] getDiseaseManifestations()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiseaseManifestationView[] getDiseaseManifestations(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getDiseaseManifestations();
  }
  
  public dss.vector.solutions.surveillance.CasePatientTypeView[] getPatientTypes()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CasePatientTypeView[] getPatientTypes(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getPatientTypes();
  }
  
  public dss.vector.solutions.surveillance.CaseReferralView[] getReferrals()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseReferralView[] getReferrals(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getReferrals();
  }
  
  public dss.vector.solutions.surveillance.CaseStockReferralView[] getStockReferrals()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseStockReferralView[] getStockReferrals(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getStockReferrals();
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentMethodView[] getTreatmentMethods()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentMethodView[] getTreatmentMethods(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getTreatmentMethods();
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentStockView[] getTreatmentStocks()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentStockView[] getTreatmentStocks(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getTreatmentStocks();
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentView[] getTreatments()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentView[] getTreatments(java.lang.String id)
  {
    AggregatedCaseView _instance = AggregatedCaseView.get(id);
    return _instance.getTreatments();
  }
  
  public static void validateEpiDate(java.lang.String periodType, java.lang.Integer period, java.lang.Integer year)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static void validateSearchCriteria(java.lang.String geoId, java.lang.String periodType, java.lang.Integer period, java.lang.Integer year)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.AggregatedCaseView.java";
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
