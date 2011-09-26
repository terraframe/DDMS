package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.MalariaSeasonQuery;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.general.ThresholdDataView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.util.HierarchyBuilder;

public class ThresholdDataExcelView extends ThresholdDataExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 631784904;
  
  public ThresholdDataExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    ThresholdDataView view = new ThresholdDataView();
    view.setThresholdType(this.getThresholdType());
    view.setGeoEntity(this.getGeoEntity().getGeoId());
    
    MalariaSeasonQuery query = new MalariaSeasonQuery(new QueryFactory());
    query.WHERE(query.getSeasonName().EQ(this.getSeasonName()));
    
    OIterator<? extends MalariaSeason> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      view.setSeason(iterator.next());
      iterator.close();
    }
    else
    {
      throw new DataNotFoundException("Transmission season not found", MdTypeDAO.getMdTypeDAO(MalariaSeason.CLASS));
    }
    
    view.setIdentification_0(this.getIdentification_0());
    view.setIdentification_1(this.getIdentification_1());
    view.setIdentification_2(this.getIdentification_2());
    view.setIdentification_3(this.getIdentification_3());
    view.setIdentification_4(this.getIdentification_4());
    view.setIdentification_5(this.getIdentification_5());
    view.setIdentification_6(this.getIdentification_6());
    view.setIdentification_7(this.getIdentification_7());
    view.setIdentification_8(this.getIdentification_8());
    view.setIdentification_9(this.getIdentification_9());
    view.setIdentification_10(this.getIdentification_10());
    view.setIdentification_11(this.getIdentification_11());
    view.setIdentification_12(this.getIdentification_12());
    view.setIdentification_13(this.getIdentification_13());
    view.setIdentification_14(this.getIdentification_14());
    view.setIdentification_15(this.getIdentification_15());
    view.setIdentification_16(this.getIdentification_16());
    view.setIdentification_17(this.getIdentification_17());
    view.setIdentification_18(this.getIdentification_18());
    view.setIdentification_19(this.getIdentification_19());
    view.setIdentification_20(this.getIdentification_20());
    view.setIdentification_21(this.getIdentification_21());
    view.setIdentification_22(this.getIdentification_22());
    view.setIdentification_23(this.getIdentification_23());
    view.setIdentification_24(this.getIdentification_24());
    view.setIdentification_25(this.getIdentification_25());
    view.setIdentification_26(this.getIdentification_26());
    view.setIdentification_27(this.getIdentification_27());
    view.setIdentification_28(this.getIdentification_28());
    view.setIdentification_29(this.getIdentification_29());
    view.setIdentification_30(this.getIdentification_30());
    view.setIdentification_31(this.getIdentification_31());
    view.setIdentification_32(this.getIdentification_32());
    view.setIdentification_33(this.getIdentification_33());
    view.setIdentification_34(this.getIdentification_34());
    view.setIdentification_35(this.getIdentification_35());
    view.setIdentification_36(this.getIdentification_36());
    view.setIdentification_37(this.getIdentification_37());
    view.setIdentification_38(this.getIdentification_38());
    view.setIdentification_39(this.getIdentification_39());
    view.setIdentification_40(this.getIdentification_40());
    view.setIdentification_41(this.getIdentification_41());
    view.setIdentification_42(this.getIdentification_42());
    view.setIdentification_43(this.getIdentification_43());
    view.setIdentification_44(this.getIdentification_44());
    view.setIdentification_45(this.getIdentification_45());
    view.setIdentification_46(this.getIdentification_46());
    view.setIdentification_47(this.getIdentification_47());
    view.setIdentification_48(this.getIdentification_48());
    view.setIdentification_49(this.getIdentification_49());
    view.setIdentification_50(this.getIdentification_50());
    view.setIdentification_51(this.getIdentification_51());
    view.setIdentification_52(this.getIdentification_52());
    view.setOutbreak_0(this.getOutbreak_0());
    view.setOutbreak_1(this.getOutbreak_1());
    view.setOutbreak_2(this.getOutbreak_2());
    view.setOutbreak_3(this.getOutbreak_3());
    view.setOutbreak_4(this.getOutbreak_4());
    view.setOutbreak_5(this.getOutbreak_5());
    view.setOutbreak_6(this.getOutbreak_6());
    view.setOutbreak_7(this.getOutbreak_7());
    view.setOutbreak_8(this.getOutbreak_8());
    view.setOutbreak_9(this.getOutbreak_9());
    view.setOutbreak_10(this.getOutbreak_10());
    view.setOutbreak_11(this.getOutbreak_11());
    view.setOutbreak_12(this.getOutbreak_12());
    view.setOutbreak_13(this.getOutbreak_13());
    view.setOutbreak_14(this.getOutbreak_14());
    view.setOutbreak_15(this.getOutbreak_15());
    view.setOutbreak_16(this.getOutbreak_16());
    view.setOutbreak_17(this.getOutbreak_17());
    view.setOutbreak_18(this.getOutbreak_18());
    view.setOutbreak_19(this.getOutbreak_19());
    view.setOutbreak_20(this.getOutbreak_20());
    view.setOutbreak_21(this.getOutbreak_21());
    view.setOutbreak_22(this.getOutbreak_22());
    view.setOutbreak_23(this.getOutbreak_23());
    view.setOutbreak_24(this.getOutbreak_24());
    view.setOutbreak_25(this.getOutbreak_25());
    view.setOutbreak_26(this.getOutbreak_26());
    view.setOutbreak_27(this.getOutbreak_27());
    view.setOutbreak_28(this.getOutbreak_28());
    view.setOutbreak_29(this.getOutbreak_29());
    view.setOutbreak_30(this.getOutbreak_30());
    view.setOutbreak_31(this.getOutbreak_31());
    view.setOutbreak_32(this.getOutbreak_32());
    view.setOutbreak_33(this.getOutbreak_33());
    view.setOutbreak_34(this.getOutbreak_34());
    view.setOutbreak_35(this.getOutbreak_35());
    view.setOutbreak_36(this.getOutbreak_36());
    view.setOutbreak_37(this.getOutbreak_37());
    view.setOutbreak_38(this.getOutbreak_38());
    view.setOutbreak_39(this.getOutbreak_39());
    view.setOutbreak_40(this.getOutbreak_40());
    view.setOutbreak_41(this.getOutbreak_41());
    view.setOutbreak_42(this.getOutbreak_42());
    view.setOutbreak_43(this.getOutbreak_43());
    view.setOutbreak_44(this.getOutbreak_44());
    view.setOutbreak_45(this.getOutbreak_45());
    view.setOutbreak_46(this.getOutbreak_46());
    view.setOutbreak_47(this.getOutbreak_47());
    view.setOutbreak_48(this.getOutbreak_48());
    view.setOutbreak_49(this.getOutbreak_49());
    view.setOutbreak_50(this.getOutbreak_50());
    view.setOutbreak_51(this.getOutbreak_51());
    view.setOutbreak_52(this.getOutbreak_52());
    view.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(THRESHOLDTYPE);
    list.add(SEASONNAME);
    list.add(OUTBREAK_0);
    list.add(IDENTIFICATION_0);
    list.add(OUTBREAK_1);
    list.add(IDENTIFICATION_1);
    list.add(OUTBREAK_2);
    list.add(IDENTIFICATION_2);
    list.add(OUTBREAK_3);
    list.add(IDENTIFICATION_3);
    list.add(OUTBREAK_4);
    list.add(IDENTIFICATION_4);
    list.add(OUTBREAK_5);
    list.add(IDENTIFICATION_5);
    list.add(OUTBREAK_6);
    list.add(IDENTIFICATION_6);
    list.add(OUTBREAK_7);
    list.add(IDENTIFICATION_7);
    list.add(OUTBREAK_8);
    list.add(IDENTIFICATION_8);
    list.add(OUTBREAK_9);
    list.add(IDENTIFICATION_9);
    list.add(OUTBREAK_10);
    list.add(IDENTIFICATION_10);
    list.add(OUTBREAK_11);
    list.add(IDENTIFICATION_11);
    list.add(OUTBREAK_12);
    list.add(IDENTIFICATION_12);
    list.add(OUTBREAK_13);
    list.add(IDENTIFICATION_13);
    list.add(OUTBREAK_14);
    list.add(IDENTIFICATION_14);
    list.add(OUTBREAK_15);
    list.add(IDENTIFICATION_15);
    list.add(OUTBREAK_16);
    list.add(IDENTIFICATION_16);
    list.add(OUTBREAK_17);
    list.add(IDENTIFICATION_17);
    list.add(OUTBREAK_18);
    list.add(IDENTIFICATION_18);
    list.add(OUTBREAK_19);
    list.add(IDENTIFICATION_19);
    list.add(OUTBREAK_20);
    list.add(IDENTIFICATION_20);
    list.add(OUTBREAK_21);
    list.add(IDENTIFICATION_21);
    list.add(OUTBREAK_22);
    list.add(IDENTIFICATION_22);
    list.add(OUTBREAK_23);
    list.add(IDENTIFICATION_23);
    list.add(OUTBREAK_24);
    list.add(IDENTIFICATION_24);
    list.add(OUTBREAK_25);
    list.add(IDENTIFICATION_25);
    list.add(OUTBREAK_26);
    list.add(IDENTIFICATION_26);
    list.add(OUTBREAK_27);
    list.add(IDENTIFICATION_27);
    list.add(OUTBREAK_28);
    list.add(IDENTIFICATION_28);
    list.add(OUTBREAK_29);
    list.add(IDENTIFICATION_29);
    list.add(OUTBREAK_30);
    list.add(IDENTIFICATION_30);
    list.add(OUTBREAK_31);
    list.add(IDENTIFICATION_31);
    list.add(OUTBREAK_32);
    list.add(IDENTIFICATION_32);
    list.add(OUTBREAK_33);
    list.add(IDENTIFICATION_33);
    list.add(OUTBREAK_34);
    list.add(IDENTIFICATION_34);
    list.add(OUTBREAK_35);
    list.add(IDENTIFICATION_35);
    list.add(OUTBREAK_36);
    list.add(IDENTIFICATION_36);
    list.add(OUTBREAK_37);
    list.add(IDENTIFICATION_37);
    list.add(OUTBREAK_38);
    list.add(IDENTIFICATION_38);
    list.add(OUTBREAK_39);
    list.add(IDENTIFICATION_39);
    list.add(OUTBREAK_40);
    list.add(IDENTIFICATION_40);
    list.add(OUTBREAK_41);
    list.add(IDENTIFICATION_41);
    list.add(OUTBREAK_42);
    list.add(IDENTIFICATION_42);
    list.add(OUTBREAK_43);
    list.add(IDENTIFICATION_43);
    list.add(OUTBREAK_44);
    list.add(IDENTIFICATION_44);
    list.add(OUTBREAK_45);
    list.add(IDENTIFICATION_45);
    list.add(OUTBREAK_46);
    list.add(IDENTIFICATION_46);
    list.add(OUTBREAK_47);
    list.add(IDENTIFICATION_47);
    list.add(OUTBREAK_48);
    list.add(IDENTIFICATION_48);
    list.add(OUTBREAK_49);
    list.add(IDENTIFICATION_49);
    list.add(OUTBREAK_50);
    list.add(IDENTIFICATION_50);
    list.add(OUTBREAK_51);
    list.add(IDENTIFICATION_51);
    list.add(OUTBREAK_52);
    list.add(IDENTIFICATION_52);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String...params)
  {
    exporter.addListener(createExcelGeoListener());
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener());
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    OIterator<? extends GeoHierarchy> iterator = PopulationData.getValidGeoHierarchies().getIterator();
    while (iterator.hasNext())
    {
      builder.add(iterator.next());
    }
    iterator.close();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
  
}
