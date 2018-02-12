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

@com.runwaysdk.business.ClassSignature(hash = -103440977)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualCaseView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class IndividualCaseViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public IndividualCaseViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public IndividualCaseViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.IndividualCaseView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getAge()
  {
    return getAge(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAge(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.AGE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAge(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.AGE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseEntryDate()
  {
    return getCaseEntryDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseEntryDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.CASEENTRYDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseEntryDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.CASEENTRYDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseReportDate()
  {
    return getCaseReportDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseReportDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.CASEREPORTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCaseReportDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.CASEREPORTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getDiagnosisDate()
  {
    return getDiagnosisDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getDiagnosisDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.DIAGNOSISDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getDiagnosisDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.DIAGNOSISDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.PatientQuery.PatientQueryReferenceIF getPatient()
  {
    return getPatient(null);

  }
 
  public dss.vector.solutions.PatientQuery.PatientQueryReferenceIF getPatient(String alias)
  {

    return (dss.vector.solutions.PatientQuery.PatientQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.PATIENT, alias, null);

  }
 
  public dss.vector.solutions.PatientQuery.PatientQueryReferenceIF getPatient(String alias, String displayLabel)
  {

    return (dss.vector.solutions.PatientQuery.PatientQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.PATIENT, alias, displayLabel);

  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getProbableSource()
  {
    return getProbableSource(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getProbableSource(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.PROBABLESOURCE, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getProbableSource(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.PROBABLESOURCE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getProbableSourceText()
  {
    return getProbableSourceText(null);

  }
 
  public com.runwaysdk.query.SelectableChar getProbableSourceText(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.PROBABLESOURCETEXT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getProbableSourceText(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.PROBABLESOURCETEXT, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidence()
  {
    return getResidence(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidence(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.RESIDENCE, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidence(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.RESIDENCE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getResidenceText()
  {
    return getResidenceText(null);

  }
 
  public com.runwaysdk.query.SelectableChar getResidenceText(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.RESIDENCETEXT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getResidenceText(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.RESIDENCETEXT, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkplace()
  {
    return getWorkplace(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkplace(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.WORKPLACE, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkplace(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.WORKPLACE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getWorkplaceText()
  {
    return getWorkplaceText(null);

  }
 
  public com.runwaysdk.query.SelectableChar getWorkplaceText(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.WORKPLACETEXT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getWorkplaceText(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualCaseView.WORKPLACETEXT, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends IndividualCaseView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<IndividualCaseView>(this.getMdClassIF(), valueIterator);
  }

}
