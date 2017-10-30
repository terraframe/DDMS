package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1647943259)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OperatorSprayStatusView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class OperatorSprayStatusViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public OperatorSprayStatusViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public OperatorSprayStatusViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.OperatorSprayStatusView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getBedNets()
  {
    return getBedNets(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getBedNets(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.BEDNETS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getBedNets(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.BEDNETS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleSheds()
  {
    return getCattleSheds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleSheds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleSheds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsLocked()
  {
    return getCattleShedsLocked(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsLocked(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDSLOCKED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsLocked(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDSLOCKED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsOther()
  {
    return getCattleShedsOther(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsOther(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDSOTHER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsOther(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDSOTHER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsRefused()
  {
    return getCattleShedsRefused(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsRefused(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDSREFUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsRefused(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDSREFUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsSprayed()
  {
    return getCattleShedsSprayed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsSprayed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDSSPRAYED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsSprayed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CATTLESHEDSSPRAYED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getHouseholds()
  {
    return getHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.HOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.HOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked()
  {
    return getLocked(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.LOCKED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.LOCKED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNozzlesUsed()
  {
    return getNozzlesUsed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNozzlesUsed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NOZZLESUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNozzlesUsed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NOZZLESUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberChildrenUnderFiveProtected()
  {
    return getNumberChildrenUnderFiveProtected(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberChildrenUnderFiveProtected(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERCHILDRENUNDERFIVEPROTECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberChildrenUnderFiveProtected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERCHILDRENUNDERFIVEPROTECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberChildrenUnderFiveSleepingUnderItns()
  {
    return getNumberChildrenUnderFiveSleepingUnderItns(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberChildrenUnderFiveSleepingUnderItns(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberChildrenUnderFiveSleepingUnderItns(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberFemalesProtected()
  {
    return getNumberFemalesProtected(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberFemalesProtected(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERFEMALESPROTECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberFemalesProtected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERFEMALESPROTECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberItnsInUse()
  {
    return getNumberItnsInUse(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberItnsInUse(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERITNSINUSE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberItnsInUse(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERITNSINUSE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberMalesProtected()
  {
    return getNumberMalesProtected(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberMalesProtected(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERMALESPROTECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberMalesProtected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERMALESPROTECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfPeople()
  {
    return getNumberOfPeople(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfPeople(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBEROFPEOPLE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfPeople(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBEROFPEOPLE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPeopleSleepingUnderItns()
  {
    return getNumberPeopleSleepingUnderItns(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPeopleSleepingUnderItns(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERPEOPLESLEEPINGUNDERITNS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPeopleSleepingUnderItns(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERPEOPLESLEEPINGUNDERITNS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantWomenProtected()
  {
    return getNumberPregnantWomenProtected(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantWomenProtected(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERPREGNANTWOMENPROTECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantWomenProtected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERPREGNANTWOMENPROTECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantWomenSleepingUnderItns()
  {
    return getNumberPregnantWomenSleepingUnderItns(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantWomenSleepingUnderItns(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPregnantWomenSleepingUnderItns(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberRoomsNotSprayedSick()
  {
    return getNumberRoomsNotSprayedSick(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberRoomsNotSprayedSick(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERROOMSNOTSPRAYEDSICK, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberRoomsNotSprayedSick(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.NUMBERROOMSNOTSPRAYEDSICK, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getOperatorLabel()
  {
    return getOperatorLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getOperatorLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.OPERATORLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getOperatorLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.OPERATORLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getOperatorTarget()
  {
    return getOperatorTarget(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOperatorTarget(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.OPERATORTARGET, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOperatorTarget(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.OPERATORTARGET, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther()
  {
    return getOther(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.OTHER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.OTHER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople()
  {
    return getPeople(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.PEOPLE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.PEOPLE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds()
  {
    return getPrevSprayedHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.PREVSPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.PREVSPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures()
  {
    return getPrevSprayedStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.PREVSPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.PREVSPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPumpsUsed()
  {
    return getPumpsUsed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPumpsUsed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.PUMPSUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPumpsUsed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.PUMPSUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceived()
  {
    return getReceived(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceived(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.RECEIVED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceived(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.RECEIVED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefills()
  {
    return getRefills(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefills(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.REFILLS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefills(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.REFILLS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused()
  {
    return getRefused(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.REFUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.REFUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getReturned()
  {
    return getReturned(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getReturned(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.RETURNED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getReturned(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.RETURNED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms()
  {
    return getRooms(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.ROOMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.ROOMS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets()
  {
    return getRoomsWithBedNets(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.ROOMSWITHBEDNETS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.ROOMSWITHBEDNETS, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.TeamSprayQuery.TeamSprayQueryReferenceIF getSpray()
  {
    return getSpray(null);

  }
 
  public dss.vector.solutions.irs.TeamSprayQuery.TeamSprayQueryReferenceIF getSpray(String alias)
  {

    return (dss.vector.solutions.irs.TeamSprayQuery.TeamSprayQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAY, alias, null);

  }
 
  public dss.vector.solutions.irs.TeamSprayQuery.TeamSprayQueryReferenceIF getSpray(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.TeamSprayQuery.TeamSprayQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAY, alias, displayLabel);

  }
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getSprayOperator()
  {
    return getSprayOperator(null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getSprayOperator(String alias)
  {

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAYOPERATOR, alias, null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getSprayOperator(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAYOPERATOR, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds()
  {
    return getSprayedHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms()
  {
    return getSprayedRooms(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAYEDROOMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAYEDROOMS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures()
  {
    return getSprayedStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.SPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures()
  {
    return getStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedFuneral()
  {
    return getStructuresNotSprayedFuneral(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedFuneral(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDFUNERAL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedFuneral(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDFUNERAL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedLocked()
  {
    return getStructuresNotSprayedLocked(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedLocked(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDLOCKED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedLocked(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDLOCKED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedNoOneHome()
  {
    return getStructuresNotSprayedNoOneHome(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedNoOneHome(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDNOONEHOME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedNoOneHome(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDNOONEHOME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedOther()
  {
    return getStructuresNotSprayedOther(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedOther(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDOTHER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedOther(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDOTHER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedRefused()
  {
    return getStructuresNotSprayedRefused(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedRefused(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDREFUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedRefused(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDREFUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedSick()
  {
    return getStructuresNotSprayedSick(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedSick(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDSICK, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructuresNotSprayedSick(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.STRUCTURESNOTSPRAYEDSICK, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getUsed()
  {
    return getUsed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getUsed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.USED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getUsed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.USED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandas()
  {
    return getVerandas(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandas(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDAS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandas(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDAS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasLocked()
  {
    return getVerandasLocked(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasLocked(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDASLOCKED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasLocked(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDASLOCKED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasOther()
  {
    return getVerandasOther(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasOther(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDASOTHER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasOther(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDASOTHER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasRefused()
  {
    return getVerandasRefused(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasRefused(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDASREFUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasRefused(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDASREFUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasSprayed()
  {
    return getVerandasSprayed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasSprayed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDASSPRAYED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasSprayed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.VERANDASSPRAYED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface()
  {
    return getWrongSurface(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.WRONGSURFACE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayStatusView.WRONGSURFACE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends OperatorSprayStatusView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<OperatorSprayStatusView>(this.getMdClassIF(), valueIterator);
  }

}
