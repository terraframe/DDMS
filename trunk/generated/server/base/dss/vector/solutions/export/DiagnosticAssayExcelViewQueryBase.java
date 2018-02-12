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
package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 543346234)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DiagnosticAssayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class DiagnosticAssayExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public DiagnosticAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public DiagnosticAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.DiagnosticAssayExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getActiveIngredient()
  {
    return getActiveIngredient(null);

  }
 
  public com.runwaysdk.query.SelectableChar getActiveIngredient(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.ACTIVEINGREDIENT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getActiveIngredient(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.ACTIVEINGREDIENT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.COLLECTIONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage()
  {
    return getLifeStage(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.LIFESTAGE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.LIFESTAGE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getOutcome()
  {
    return getOutcome(null);

  }
 
  public com.runwaysdk.query.SelectableChar getOutcome(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.OUTCOME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getOutcome(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.OUTCOME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies()
  {
    return getSpecies(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.SPECIES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.SPECIES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSynergist()
  {
    return getSynergist(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSynergist(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.SYNERGIST, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSynergist(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.SYNERGIST, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.DiagnosticAssayExcelView.UNIQUEASSAYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends DiagnosticAssayExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<DiagnosticAssayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
