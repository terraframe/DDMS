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

@com.runwaysdk.business.ClassSignature(hash = 775791213)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to HouseholdView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class HouseholdViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public HouseholdViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public HouseholdViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.HouseholdView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getHasBeenSprayed()
  {
    return getHasBeenSprayed(null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getHasBeenSprayed(String alias)
  {
    return (dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HASBEENSPRAYED, alias, null);

  }
 
  public dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF getHasBeenSprayed(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ResponseMasterQuery.ResponseQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HASBEENSPRAYED, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableBoolean getHasHouseholdNets()
  {
    return getHasHouseholdNets(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasHouseholdNets(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HASHOUSEHOLDNETS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasHouseholdNets(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HASHOUSEHOLDNETS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasWindows()
  {
    return getHasWindows(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasWindows(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HASWINDOWS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasWindows(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HASWINDOWS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdName()
  {
    return getHouseholdName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HOUSEHOLDNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HOUSEHOLDNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getLastSprayed()
  {
    return getLastSprayed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLastSprayed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.LASTSPRAYED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLastSprayed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.LASTSPRAYED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNets()
  {
    return getNets(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNets(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.NETS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNets(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.NETS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople()
  {
    return getPeople(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.PEOPLE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.PEOPLE, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRoof()
  {
    return getRoof(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRoof(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOF, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRoof(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOF, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getRoofInfo()
  {
    return getRoofInfo(null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoofInfo(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOFINFO, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoofInfo(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOFINFO, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms()
  {
    return getRooms(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOMS, alias, displayLabel);

  }
 
  public dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF getSurveyPoint()
  {
    return getSurveyPoint(null);

  }
 
  public dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF getSurveyPoint(String alias)
  {

    return (dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.SURVEYPOINT, alias, null);

  }
 
  public dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF getSurveyPoint(String alias, String displayLabel)
  {

    return (dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.SURVEYPOINT, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableBoolean getUrban()
  {
    return getUrban(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getUrban(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.URBAN, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getUrban(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.URBAN, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWall()
  {
    return getWall(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWall(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WALL, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWall(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WALL, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getWallInfo()
  {
    return getWallInfo(null);

  }
 
  public com.runwaysdk.query.SelectableChar getWallInfo(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WALLINFO, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getWallInfo(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WALLINFO, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWindowType()
  {
    return getWindowType(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWindowType(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WINDOWTYPE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWindowType(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WINDOWTYPE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends HouseholdView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<HouseholdView>(this.getMdClassIF(), valueIterator);
  }

}
