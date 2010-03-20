package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 2065943097)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoTargetView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class GeoTargetViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 2065943097;

  public GeoTargetViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public GeoTargetViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.GeoTargetView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getEntityName()
  {
    return getEntityName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.ENTITYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.ENTITYNAME, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.general.MalariaSeasonQuery.MalariaSeasonQueryReferenceIF getSeason()
  {
    return getSeason(null);

  }
 
  public dss.vector.solutions.general.MalariaSeasonQuery.MalariaSeasonQueryReferenceIF getSeason(String alias)
  {

    return (dss.vector.solutions.general.MalariaSeasonQuery.MalariaSeasonQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.SEASON, alias, null);

  }
 
  public dss.vector.solutions.general.MalariaSeasonQuery.MalariaSeasonQueryReferenceIF getSeason(String alias, String displayLabel)
  {

    return (dss.vector.solutions.general.MalariaSeasonQuery.MalariaSeasonQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.SEASON, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getTargetId()
  {
    return getTargetId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTargetId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGETID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTargetId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGETID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_0()
  {
    return getTarget_0(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_0(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_0, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_0(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_0, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_1()
  {
    return getTarget_1(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_1(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_1, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_1(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_1, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_10()
  {
    return getTarget_10(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_10(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_10, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_10(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_10, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_11()
  {
    return getTarget_11(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_11(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_11, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_11(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_11, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_12()
  {
    return getTarget_12(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_12(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_12, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_12(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_12, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_13()
  {
    return getTarget_13(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_13(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_13, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_13(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_13, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_14()
  {
    return getTarget_14(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_14(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_14, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_14(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_14, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_15()
  {
    return getTarget_15(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_15(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_15, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_15(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_15, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_16()
  {
    return getTarget_16(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_16(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_16, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_16(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_16, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_17()
  {
    return getTarget_17(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_17(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_17, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_17(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_17, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_18()
  {
    return getTarget_18(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_18(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_18, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_18(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_18, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_19()
  {
    return getTarget_19(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_19(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_19, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_19(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_19, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_2()
  {
    return getTarget_2(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_2(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_2, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_2(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_2, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_20()
  {
    return getTarget_20(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_20(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_20, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_20(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_20, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_21()
  {
    return getTarget_21(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_21(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_21, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_21(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_21, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_22()
  {
    return getTarget_22(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_22(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_22, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_22(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_22, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_23()
  {
    return getTarget_23(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_23(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_23, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_23(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_23, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_24()
  {
    return getTarget_24(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_24(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_24, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_24(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_24, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_25()
  {
    return getTarget_25(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_25(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_25, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_25(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_25, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_26()
  {
    return getTarget_26(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_26(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_26, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_26(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_26, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_27()
  {
    return getTarget_27(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_27(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_27, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_27(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_27, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_28()
  {
    return getTarget_28(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_28(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_28, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_28(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_28, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_29()
  {
    return getTarget_29(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_29(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_29, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_29(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_29, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_3()
  {
    return getTarget_3(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_3(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_3, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_3(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_3, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_30()
  {
    return getTarget_30(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_30(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_30, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_30(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_30, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_31()
  {
    return getTarget_31(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_31(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_31, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_31(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_31, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_32()
  {
    return getTarget_32(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_32(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_32, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_32(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_32, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_33()
  {
    return getTarget_33(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_33(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_33, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_33(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_33, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_34()
  {
    return getTarget_34(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_34(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_34, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_34(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_34, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_35()
  {
    return getTarget_35(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_35(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_35, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_35(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_35, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_36()
  {
    return getTarget_36(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_36(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_36, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_36(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_36, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_37()
  {
    return getTarget_37(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_37(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_37, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_37(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_37, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_38()
  {
    return getTarget_38(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_38(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_38, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_38(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_38, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_39()
  {
    return getTarget_39(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_39(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_39, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_39(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_39, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_4()
  {
    return getTarget_4(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_4(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_4, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_4(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_4, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_40()
  {
    return getTarget_40(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_40(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_40, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_40(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_40, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_41()
  {
    return getTarget_41(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_41(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_41, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_41(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_41, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_42()
  {
    return getTarget_42(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_42(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_42, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_42(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_42, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_43()
  {
    return getTarget_43(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_43(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_43, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_43(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_43, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_44()
  {
    return getTarget_44(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_44(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_44, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_44(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_44, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_45()
  {
    return getTarget_45(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_45(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_45, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_45(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_45, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_46()
  {
    return getTarget_46(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_46(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_46, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_46(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_46, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_47()
  {
    return getTarget_47(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_47(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_47, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_47(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_47, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_48()
  {
    return getTarget_48(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_48(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_48, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_48(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_48, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_49()
  {
    return getTarget_49(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_49(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_49, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_49(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_49, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_5()
  {
    return getTarget_5(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_5(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_5, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_5(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_5, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_50()
  {
    return getTarget_50(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_50(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_50, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_50(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_50, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_51()
  {
    return getTarget_51(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_51(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_51, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_51(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_51, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_52()
  {
    return getTarget_52(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_52(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_52, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_52(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_52, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_6()
  {
    return getTarget_6(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_6(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_6, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_6(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_6, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_7()
  {
    return getTarget_7(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_7(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_7, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_7(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_7, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_8()
  {
    return getTarget_8(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_8(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_8, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_8(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_8, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_9()
  {
    return getTarget_9(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_9(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_9, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget_9(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.GeoTargetView.TARGET_9, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends GeoTargetView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<GeoTargetView>(this.getMdClassIF(), valueIterator);
  }

}
