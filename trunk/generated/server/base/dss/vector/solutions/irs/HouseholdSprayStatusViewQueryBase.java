package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 838525373)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to HouseholdSprayStatusView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class HouseholdSprayStatusViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public HouseholdSprayStatusViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public HouseholdSprayStatusViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.HouseholdSprayStatusView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getBedNets()
  {
    return getBedNets(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getBedNets(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.BEDNETS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getBedNets(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.BEDNETS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdId()
  {
    return getHouseholdId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.HOUSEHOLDID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.HOUSEHOLDID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getHouseholds()
  {
    return getHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.HOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.HOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked()
  {
    return getLocked(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.LOCKED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.LOCKED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther()
  {
    return getOther(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.OTHER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.OTHER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople()
  {
    return getPeople(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.PEOPLE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.PEOPLE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds()
  {
    return getPrevSprayedHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.PREVSPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.PREVSPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures()
  {
    return getPrevSprayedStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.PREVSPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.PREVSPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused()
  {
    return getRefused(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.REFUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.REFUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms()
  {
    return getRooms(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.ROOMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.ROOMS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets()
  {
    return getRoomsWithBedNets(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.ROOMSWITHBEDNETS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.ROOMSWITHBEDNETS, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.OperatorSprayQuery.OperatorSprayQueryReferenceIF getSpray()
  {
    return getSpray(null);

  }
 
  public dss.vector.solutions.irs.OperatorSprayQuery.OperatorSprayQueryReferenceIF getSpray(String alias)
  {

    return (dss.vector.solutions.irs.OperatorSprayQuery.OperatorSprayQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.SPRAY, alias, null);

  }
 
  public dss.vector.solutions.irs.OperatorSprayQuery.OperatorSprayQueryReferenceIF getSpray(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.OperatorSprayQuery.OperatorSprayQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.SPRAY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds()
  {
    return getSprayedHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.SPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.SPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms()
  {
    return getSprayedRooms(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.SPRAYEDROOMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.SPRAYEDROOMS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures()
  {
    return getSprayedStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.SPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.SPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getStructureId()
  {
    return getStructureId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getStructureId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.STRUCTUREID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getStructureId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.STRUCTUREID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures()
  {
    return getStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.STRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.STRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface()
  {
    return getWrongSurface(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.WRONGSURFACE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.HouseholdSprayStatusView.WRONGSURFACE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends HouseholdSprayStatusView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<HouseholdSprayStatusView>(this.getMdClassIF(), valueIterator);
  }

}
