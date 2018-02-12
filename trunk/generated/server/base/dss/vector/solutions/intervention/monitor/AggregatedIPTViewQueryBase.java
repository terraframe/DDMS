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

@com.runwaysdk.business.ClassSignature(hash = 1176616623)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedIPTView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class AggregatedIPTViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public AggregatedIPTViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AggregatedIPTViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.AggregatedIPTView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayDose()
  {
    return getDisplayDose(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayDose(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.DISPLAYDOSE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayDose(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.DISPLAYDOSE, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayPatients()
  {
    return getDisplayPatients(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayPatients(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.DISPLAYPATIENTS, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayPatients(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.DISPLAYPATIENTS, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTreatments()
  {
    return getDisplayTreatments(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTreatments(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.DISPLAYTREATMENTS, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTreatments(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.DISPLAYTREATMENTS, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayVisits()
  {
    return getDisplayVisits(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayVisits(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.DISPLAYVISITS, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayVisits(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.DISPLAYVISITS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.ENDDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId()
  {
    return getGeoId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.GEOID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.GEOID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberNatalCare()
  {
    return getNumberNatalCare(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberNatalCare(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.NUMBERNATALCARE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberNatalCare(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.NUMBERNATALCARE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnant()
  {
    return getNumberPregnant(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnant(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.NUMBERPREGNANT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnant(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.NUMBERPREGNANT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantITN()
  {
    return getNumberPregnantITN(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantITN(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.NUMBERPREGNANTITN, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantITN(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.NUMBERPREGNANTITN, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantIron()
  {
    return getNumberPregnantIron(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantIron(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.NUMBERPREGNANTIRON, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantIron(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.NUMBERPREGNANTIRON, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriod()
  {
    return getPeriod(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriod(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.PERIOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.PERIOD, alias, displayLabel);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType()
  {
    return getPeriodType(null);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType(String alias)
  {
    return (dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.PERIODTYPE, alias, null);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType(String alias, String displayLabel)
  {
    return (dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.PERIODTYPE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getPeriodYear()
  {
    return getPeriodYear(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriodYear(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.PERIODYEAR, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriodYear(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.PERIODYEAR, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSearchType()
  {
    return getSearchType(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSearchType(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.SEARCHTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSearchType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.SEARCHTYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.STARTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalITN()
  {
    return getTotalITN(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalITN(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.TOTALITN, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalITN(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedIPTView.TOTALITN, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends AggregatedIPTView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<AggregatedIPTView>(this.getMdClassIF(), valueIterator);
  }

}
