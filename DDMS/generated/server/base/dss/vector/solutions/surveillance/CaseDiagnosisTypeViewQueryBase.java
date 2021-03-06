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

@com.runwaysdk.business.ClassSignature(hash = 726434492)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseDiagnosisTypeView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class CaseDiagnosisTypeViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public CaseDiagnosisTypeViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public CaseDiagnosisTypeViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.surveillance.CaseDiagnosisTypeView.CLASS;
  }
  public dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF getAggregatedCase()
  {
    return getAggregatedCase(null);

  }
 
  public dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF getAggregatedCase(String alias)
  {

    return (dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.AGGREGATEDCASE, alias, null);

  }
 
  public dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF getAggregatedCase(String alias, String displayLabel)
  {

    return (dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.AGGREGATEDCASE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDiagnosisCategory()
  {
    return getDiagnosisCategory(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDiagnosisCategory(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.DIAGNOSISCATEGORY, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDiagnosisCategory(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.DIAGNOSISCATEGORY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.TERM, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends CaseDiagnosisTypeView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<CaseDiagnosisTypeView>(this.getMdClassIF(), valueIterator);
  }

}
