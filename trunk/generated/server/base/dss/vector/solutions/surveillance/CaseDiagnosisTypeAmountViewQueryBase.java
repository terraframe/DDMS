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

@com.runwaysdk.business.ClassSignature(hash = 445113645)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseDiagnosisTypeAmountView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class CaseDiagnosisTypeAmountViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public CaseDiagnosisTypeAmountViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public CaseDiagnosisTypeAmountViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.AMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.AMOUNT, alias, displayLabel);

  }
 
  public dss.vector.solutions.surveillance.CaseDiagnosisTypeQuery.CaseDiagnosisTypeQueryReferenceIF getDiagnosis()
  {
    return getDiagnosis(null);

  }
 
  public dss.vector.solutions.surveillance.CaseDiagnosisTypeQuery.CaseDiagnosisTypeQueryReferenceIF getDiagnosis(String alias)
  {

    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeQuery.CaseDiagnosisTypeQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.DIAGNOSIS, alias, null);

  }
 
  public dss.vector.solutions.surveillance.CaseDiagnosisTypeQuery.CaseDiagnosisTypeQueryReferenceIF getDiagnosis(String alias, String displayLabel)
  {

    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeQuery.CaseDiagnosisTypeQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.DIAGNOSIS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView.TERM, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends CaseDiagnosisTypeAmountView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<CaseDiagnosisTypeAmountView>(this.getMdClassIF(), valueIterator);
  }

}
