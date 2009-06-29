package dss.vector.solutions;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.business.BusinessQuery;
import com.terraframe.mojo.constants.CommonProperties;
import com.terraframe.mojo.constants.ComponentInfo;
import com.terraframe.mojo.constants.RelationshipInfo;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSingle;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.generated.CountryQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.ProvinceQuery;
import dss.vector.solutions.surveillance.AggregatedCaseQuery;

public class Sandbox
{
  public static java.util.Date startTime   = new java.util.Date();

  private static int           feedbackMod = 50;

  public static void main(String[] args) throws Exception
  {

    // String temp = "CaseTreatmentStock_SP_TreatmentGrid";
    //
    // int firstIndex = temp.indexOf("_", 0);
    //
    // int secondIndex = temp.indexOf("_", firstIndex+1);
    //
    // String relString = temp.substring(0, secondIndex);
    //
    // System.out.println(firstIndex+"  "+secondIndex+"  "+relString);

    ClientSession session = ClientSession.createUserSession("MDSS", "mdsstest2", CommonProperties
        .getDefaultLocale());
    test(session.getSessionId());

    //
    // String s =
    // "dss.vector.solutions.geo.generated.CountryController&dss.vector.solutions.geo.generated.DistrictController&dss.vector.solutions.geo.generated.VillageController&dss.vector.solutions.geo.generated.LocalityController&dss.vector.solutions.geo.generated.AdminPostController&dss.vector.solutions.geo.generated.Trap&dss.vector.solutions.geo.generated.RoadController&dss.vector.solutions.geo.generated.Reserve&dss.vector.solutions.geo.generated.EarthController&dss.vector.solutions.geo.generated.CityController&dss.vector.solutions.geo.generated.Town&dss.vector.solutions.geo.generated.District&dss.vector.solutions.geo.generated.Village&dss.vector.solutions.geo.generated.RoofController&dss.vector.solutions.geo.generated.NonSentinelSiteController&dss.vector.solutions.geo.generated.Earth&dss.vector.solutions.geo.generated.SentinelSite&dss.vector.solutions.geo.generated.RiverController&dss.vector.solutions.geo.generated.Wall&dss.vector.solutions.geo.generated.SprayZoneController&dss.vector.solutions.geo.generated.WallController&dss.vector.solutions.geo.generated.PermanentWaterBodyController&dss.vector.solutions.geo.generated.BreedingSiteController&dss.vector.solutions.geo.generated.PopulatedArea&dss.vector.solutions.geo.generated.FacilityController&dss.vector.solutions.geo.generated.Railway&dss.vector.solutions.geo.generated.Locality&dss.vector.solutions.geo.generated.SentinelSiteController&dss.vector.solutions.geo.generated.NationalRoadController&dss.vector.solutions.geo.generated.Road&dss.vector.solutions.geo.generated.Facility&dss.vector.solutions.geo.generated.Province&dss.vector.solutions.geo.generated.PopulatedAreaController&dss.vector.solutions.geo.generated.ProvinceController&dss.vector.solutions.geo.generated.ReserveController&dss.vector.solutions.geo.generated.TrapController&dss.vector.solutions.geo.generated.BreedingSite&dss.vector.solutions.geo.generated.AdminPost&dss.vector.solutions.geo.generated.Roof&dss.vector.solutions.geo.generated.SprayZone&dss.vector.solutions.geo.generated.River&dss.vector.solutions.geo.generated.NationalRoad&dss.vector.solutions.geo.generated.City&dss.vector.solutions.geo.generated.Country&dss.vector.solutions.geo.generated.PermanentWaterBody&dss.vector.solutions.geo.generated.RailwayController&dss.vector.solutions.geo.generated.TownController&dss.vector.solutions.geo.generated.NonSentinelSite&dss.vector.solutions.geo.GeoEntityView";
    //
    // String[] arr = s.split("&");
    //
    // for(String type : arr)
    // {
    // // System.out.println(type);
    // }
    //
    // Date before = new Date();
    //
    // // String js = JSONController.importTypes(session.getSessionId(), arr,
    // true);
    // String js = JSONController.importTypes(session.getSessionId(), new
    // String[]{GeoEntity.CLASS}, true);
    //
    //
    // //System.out.println(js);
    //
    // Date after = new Date();

    // System.out.println(before);
    // System.out.println(after);

    // createGeoEntities(request.getSessionId());

    // testQueries();

    // queryAggregatedCases();

    // testAllPaths();
  }

  @StartSession
  public static void test(String sessionId)
  {
    try
    {
      MdBusinessDAOIF facility = MdBusinessDAO.getMdBusinessDAO(HealthFacility.CLASS);
      for(MdBusinessDAOIF sub : facility.getAllSubClasses())
      {
        System.out.println(sub.definesType());
      }
    }
    catch (Throwable t)
    {
      t.printStackTrace();
    }
  }

  @StartSession
  private static void testAllPaths()
  {
    // defineDatatypesForAllPaths();

    // GeoHierarchy earthGeoHierarchy =
    // GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
    // updateAllPaths(earthGeoHierarchy);
    //
    // updateAllPaths();
    //
    // java.util.Date endTime = new java.util.Date();
    // long totalTime = endTime.getTime() - startTime.getTime();
    // System.out.println("\nTotal Time: " + totalTime);
  }

  @Transaction
  private static void defineDatatypesForAllPaths()
  {
    MdBusinessDAOIF allPathsMdBusinessDAO = MdBusinessDAO.getMdBusinessDAO(PropertyInfo.GEO_PACKAGE
        + "." + "AllPaths");
    allPathsMdBusinessDAO.getBusinessDAO().delete();

    // MdBusinessDAOIF geoEntityMdBusiness =
    // MdBusinessDAO.getMdBusinessDAO(GeoEntity.CLASS);
    //
    // MdBusinessDAO allPathsMdBusinessDAO = MdBusinessDAO.newInstance();
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.NAME, "AllPaths");
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.PACKAGE,
    // PropertyInfo.GEO_PACKAGE);
    // allPathsMdBusinessDAO.setStructValue(MdBusinessInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "All Paths");
    // allPathsMdBusinessDAO.setValue(MdBusinessInfo.EXTENDABLE,
    // MdAttributeBooleanInfo.FALSE);
    // allPathsMdBusinessDAO.setGenerateMdController(false);
    // allPathsMdBusinessDAO.apply();
    //
    // MdAttributeReferenceDAO parentMdAttributeReferenceDAO =
    // MdAttributeReferenceDAO.newInstance();
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.NAME,
    // "parentGeoEntity");
    // parentMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Parent Geo Entity");
    // parentMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DESCRIPTION,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Parent Geo Entity");
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REQUIRED,
    // MdAttributeBooleanInfo.TRUE);
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REMOVE,
    // MdAttributeBooleanInfo.TRUE);
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.INDEX_TYPE,
    // IndexTypes.NON_UNIQUE_INDEX.getId());
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REF_MD_BUSINESS,
    // geoEntityMdBusiness.getId());
    // parentMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.DEFINING_MD_CLASS,
    // allPathsMdBusinessDAO.getId());
    // parentMdAttributeReferenceDAO.apply();
    //
    // MdAttributeReferenceDAO childMdAttributeReferenceDAO =
    // MdAttributeReferenceDAO.newInstance();
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.NAME,
    // "childGeoEntity");
    // childMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Child Geo Entity");
    // childMdAttributeReferenceDAO.setStructValue(MdAttributeReferenceInfo.DESCRIPTION,
    // MdAttributeLocalInfo.DEFAULT_LOCALE, "Child Geo Entity");
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REQUIRED,
    // MdAttributeBooleanInfo.TRUE);
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REMOVE,
    // MdAttributeBooleanInfo.TRUE);
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.INDEX_TYPE,
    // IndexTypes.NON_UNIQUE_INDEX.getId());
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.REF_MD_BUSINESS,
    // geoEntityMdBusiness.getId());
    // childMdAttributeReferenceDAO.setValue(MdAttributeReferenceInfo.DEFINING_MD_CLASS,
    // allPathsMdBusinessDAO.getId());
    // childMdAttributeReferenceDAO.apply();
    //
    //
    // MdIndexDAO mdIndex = MdIndexDAO.newInstance();
    // mdIndex.setValue(MdIndexInfo.MD_ENTITY, allPathsMdBusinessDAO.getId());
    // mdIndex.setValue(MdIndexInfo.UNIQUE, MdAttributeBooleanInfo.TRUE);
    // mdIndex.setStructValue(MdIndexInfo.DISPLAY_LABEL,
    // MdAttributeLocalInfo.DEFAULT_LOCALE,
    // "Parent Child Geo Entity Unique Index");
    // mdIndex.apply();
    //
    // mdIndex.addAttribute(parentMdAttributeReferenceDAO, 0);
    // mdIndex.addAttribute(childMdAttributeReferenceDAO, 1);
    //
    // mdIndex.setValue(MdIndexInfo.ACTIVE, MdAttributeBooleanInfo.TRUE);
    // mdIndex.apply();
  }

  @Transaction
  private static void updateAllPaths(GeoHierarchy parentGeoHierarchy)
  {
    updateAllPathsForUniversal(parentGeoHierarchy);

    List<? extends GeoHierarchy> geoHierarchyChildren = parentGeoHierarchy.getAllAcceptsGeoEntity()
        .getAll();

    for (GeoHierarchy childGeoHierarchy : geoHierarchyChildren)
    {
      updateAllPaths(childGeoHierarchy);
    }
  }

  private static void createPath(String parentId, String childId)
  {
    // try
    // {
    // AllPaths allPaths = new AllPaths();
    // allPaths.setValue(AllPaths.PARENTGEOENTITY, parentId);
    // allPaths.setValue(AllPaths.CHILDGEOENTITY, childId);
    // allPaths.apply();
    // }
    // catch(DuplicateDataDatabaseException ex)
    // {
    // // This might happen. Relationship already exists.
    // }
  }

  private static void updateAllPathsForUniversal(GeoHierarchy geoHierarchy)
  {
    MdBusiness mdBusiness = geoHierarchy.getGeoEntityClass();

    System.out.println("\nHeads up: " + mdBusiness.getDisplayLabel() + ": "
        + mdBusiness.getSuperMdBusiness().getDisplayLabel());

    // We only need to do this for the parent most types.
    // The child types will be included when we update paths for
    // the parent type.
    if (!mdBusiness.getSuperMdBusiness().definesType().equals(GeoEntity.CLASS))
    {
      return;
    }

    String geoEntityType = mdBusiness.definesType();

    QueryFactory qf = new QueryFactory();

    BusinessQuery businessQ = qf.businessQuery(geoEntityType);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(businessQ.aCharacter(ComponentInfo.ID, ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);
        createPath(childId, childId);
        System.out.print(".");

        applyCount++;

        if (applyCount % feedbackMod == 0)
        {
          System.out.println();
        }

        List<String> parentIdList = getParentIds(childId);

        for (String parentId : parentIdList)
        {
          createPath(parentId, childId);
          System.out.print(".");

          applyCount++;

          if (applyCount % feedbackMod == 0)
          {
            System.out.println();
          }
        }

      }
    }
    finally
    {
      i.close();
    }
  }

  private static void updateAllPaths()
  {
    QueryFactory qf = new QueryFactory();

    GeoEntityQuery geoEntityQ = new GeoEntityQuery(qf);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(geoEntityQ.getId(ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);
        createPath(childId, childId);
        System.out.print(".");

        applyCount++;

        if (applyCount % feedbackMod == 0)
        {
          System.out.println();
        }

        List<String> parentIdList = getParentIds(childId);

        for (String parentId : parentIdList)
        {
          createPath(parentId, childId);
          System.out.print(".");

          applyCount++;

          if (applyCount % feedbackMod == 0)
          {
            System.out.println();
          }
        }

      }
    }
    finally
    {
      i.close();
    }
  }

  // private static boolean parentPathsExist(String parentId)
  // {
  // QueryFactory queryFactory = new QueryFactory();
  //
  // AllPathsQuery allPathsQuery = new AllPathsQuery(queryFactory);
  //
  // ValueQuery valueQuery = new ValueQuery(queryFactory);
  //
  // valueQuery.SELECT(allPathsQuery.getParentGeoEntity(AllPaths.PARENTGEOENTITY));
  // valueQuery.WHERE(allPathsQuery.getChildGeoEntity().EQ(parentId));
  //
  // for (ValueObject valueObject : valueQuery.getIterator())
  // {
  // return true;
  // }
  //
  // return false;
  // }

  private static List<String> getParentIds(String childId)
  {
    QueryFactory queryFactory = new QueryFactory();

    LocatedInQuery locatedInQuery = new LocatedInQuery(queryFactory);

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    valueQuery.SELECT(locatedInQuery.parentId(RelationshipInfo.PARENT_ID));
    valueQuery.WHERE(locatedInQuery.childId().EQ(childId));

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    List<String> parentIdList = new LinkedList<String>();

    for (ValueObject valueObject : valueObjectList)
    {
      String parentId = valueObject.getValue(RelationshipInfo.PARENT_ID);
      parentIdList.add(parentId);
      parentIdList.addAll(getParentIds(parentId));
    }

    return parentIdList;
  }

  @StartSession
  private static void testQueries()
  {
    // QueryFactory qf = new QueryFactory();
    //
    // ValueQuery valueQuery = new ValueQuery(qf);
    //
    // AggregatedCaseQuery acq = new AggregatedCaseQuery(qf);
    //
    // CaseTreatmentMethodQuery ctmq1 = new CaseTreatmentMethodQuery(qf);
    // CaseTreatmentMethodQuery ctmq2 = new CaseTreatmentMethodQuery(qf);
    //
    // TreatmentMethodGridQuery tmgq1 = new TreatmentMethodGridQuery(qf);
    // tmgq1.WHERE(tmgq1.getOptionName().EQ("Tablets"));
    // TreatmentMethodGridQuery tmgq2 = new TreatmentMethodGridQuery(qf);
    // tmgq2.WHERE(tmgq2.getOptionName().EQ("Syrup"));
    //
    //
    // valueQuery.SELECT(acq.getStartAge(), acq.getEndAge(),
    // F.SUM(ctmq1.getAmount(), "Tablets"), F.SUM(ctmq2.getAmount(), "Syrup"));
    // valueQuery.WHERE(acq.treatmentMethod(ctmq1));
    // valueQuery.AND(acq.treatmentMethod(ctmq2));
    // valueQuery.AND(ctmq1.hasChild(tmgq1));
    // valueQuery.AND(ctmq2.hasChild(tmgq2));
    //
    //
    // System.out.println(valueQuery.getSQL());
    //
    //
    // for (ValueObject valueObject : valueQuery.getIterator())
    // {
    // valueObject.printAttributes();
    // for (com.terraframe.mojo.dataaccess.AttributeIF attributeIF :
    // valueObject.getAttributeArrayIF())
    // {
    // System.out.println(attributeIF.getDisplayLabel(Locale.ENGLISH));
    // }
    // }

  }

}
