package dss.vector.solutions;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.sun.mail.imap.Utility.Condition;
import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdRelationshipDAOIF;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.metadata.MdRelationshipDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.util.FileIO;
import com.terraframe.mojo.web.json.JSONController;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.CountryQuery;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.generated.Province;
import dss.vector.solutions.surveillance.AggregatedCase;
import dss.vector.solutions.surveillance.AggregatedCaseQuery;
import dss.vector.solutions.surveillance.CaseDiagnosticQuery;
import dss.vector.solutions.surveillance.CaseReferralQuery;
import dss.vector.solutions.surveillance.CaseTreatmentMethodQuery;
import dss.vector.solutions.surveillance.CaseTreatmentQuery;
import dss.vector.solutions.surveillance.CaseTreatmentStock;
import dss.vector.solutions.surveillance.CaseTreatmentStockQuery;
import dss.vector.solutions.surveillance.DiagnosticGridQuery;
import dss.vector.solutions.surveillance.ReferralGridQuery;
import dss.vector.solutions.surveillance.TreatmentGridQuery;
import dss.vector.solutions.surveillance.TreatmentMethodGridQuery;


public class Sandbox
{
  public static void main(String[] args) throws Exception
  {

    QueryFactory f = new QueryFactory();
    GeoEntityQuery q = new GeoEntityQuery(f);

    com.terraframe.mojo.query.Condition or = OR.get(q.getEntityName().LIKE("A"), q.getEntityName().LIKE("B"));
    com.terraframe.mojo.query.Condition and = AND.get(or, q.getGeoId().EQ("123"));

    q.WHERE(and);

//    String temp = "CaseTreatmentStock_SP_TreatmentGrid";
//
//    int firstIndex = temp.indexOf("_", 0);
//
//    int secondIndex = temp.indexOf("_", firstIndex+1);
//
//    String relString = temp.substring(0, secondIndex);
//
//    System.out.println(firstIndex+"  "+secondIndex+"  "+relString);

    ClientSession session = ClientSession.createUserSession(ServerConstants.SYSTEM_USER_NAME, "mdsstest2");

    String s = "dss.vector.solutions.geo.generated.CountryController&dss.vector.solutions.geo.generated.DistrictController&dss.vector.solutions.geo.generated.VillageController&dss.vector.solutions.geo.generated.LocalityController&dss.vector.solutions.geo.generated.AdminPostController&dss.vector.solutions.geo.generated.Trap&dss.vector.solutions.geo.generated.RoadController&dss.vector.solutions.geo.generated.Reserve&dss.vector.solutions.geo.generated.EarthController&dss.vector.solutions.geo.generated.CityController&dss.vector.solutions.geo.generated.Town&dss.vector.solutions.geo.generated.District&dss.vector.solutions.geo.generated.Village&dss.vector.solutions.geo.generated.RoofController&dss.vector.solutions.geo.generated.NonSentinelSiteController&dss.vector.solutions.geo.generated.Earth&dss.vector.solutions.geo.generated.SentinelSite&dss.vector.solutions.geo.generated.RiverController&dss.vector.solutions.geo.generated.Wall&dss.vector.solutions.geo.generated.SprayZoneController&dss.vector.solutions.geo.generated.WallController&dss.vector.solutions.geo.generated.PermanentWaterBodyController&dss.vector.solutions.geo.generated.BreedingSiteController&dss.vector.solutions.geo.generated.PopulatedArea&dss.vector.solutions.geo.generated.FacilityController&dss.vector.solutions.geo.generated.Railway&dss.vector.solutions.geo.generated.Locality&dss.vector.solutions.geo.generated.SentinelSiteController&dss.vector.solutions.geo.generated.NationalRoadController&dss.vector.solutions.geo.generated.Road&dss.vector.solutions.geo.generated.Facility&dss.vector.solutions.geo.generated.Province&dss.vector.solutions.geo.generated.PopulatedAreaController&dss.vector.solutions.geo.generated.ProvinceController&dss.vector.solutions.geo.generated.ReserveController&dss.vector.solutions.geo.generated.TrapController&dss.vector.solutions.geo.generated.BreedingSite&dss.vector.solutions.geo.generated.AdminPost&dss.vector.solutions.geo.generated.Roof&dss.vector.solutions.geo.generated.SprayZone&dss.vector.solutions.geo.generated.River&dss.vector.solutions.geo.generated.NationalRoad&dss.vector.solutions.geo.generated.City&dss.vector.solutions.geo.generated.Country&dss.vector.solutions.geo.generated.PermanentWaterBody&dss.vector.solutions.geo.generated.RailwayController&dss.vector.solutions.geo.generated.TownController&dss.vector.solutions.geo.generated.NonSentinelSite&dss.vector.solutions.geo.GeoEntityView";

    String[] arr = s.split("&");

    for(String type : arr)
    {
    //  System.out.println(type);
    }

    Date before = new Date();

//    String js = JSONController.importTypes(session.getSessionId(), arr, true);
    String js = JSONController.importTypes(session.getSessionId(), new String[]{GeoEntity.CLASS}, true);


    //System.out.println(js);

    Date after = new Date();

    //System.out.println(before);
    //System.out.println(after);

    //    createGeoEntities(request.getSessionId());

//    testQueries();

//    queryAggregatedCases();
  }

  @StartSession
  private static void testQueries()
  {
    QueryFactory qf = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(qf);

    AggregatedCaseQuery acq = new AggregatedCaseQuery(qf);

    CaseTreatmentMethodQuery ctmq1 = new CaseTreatmentMethodQuery(qf);
    CaseTreatmentMethodQuery ctmq2 = new CaseTreatmentMethodQuery(qf);

    TreatmentMethodGridQuery tmgq1 = new TreatmentMethodGridQuery(qf);
    tmgq1.WHERE(tmgq1.getOptionName().EQ("Tablets"));
    TreatmentMethodGridQuery tmgq2 = new TreatmentMethodGridQuery(qf);
    tmgq2.WHERE(tmgq2.getOptionName().EQ("Syrup"));


    valueQuery.SELECT(acq.getStartAge(), acq.getEndAge(), F.SUM(ctmq1.getAmount(), "Tablets"), F.SUM(ctmq2.getAmount(), "Syrup"));
    valueQuery.WHERE(acq.treatmentMethod(ctmq1));
    valueQuery.AND(acq.treatmentMethod(ctmq2));
    valueQuery.AND(ctmq1.hasChild(tmgq1));
    valueQuery.AND(ctmq2.hasChild(tmgq2));


    System.out.println(valueQuery.getSQL());


    for (ValueObject valueObject : valueQuery.getIterator())
    {
      valueObject.printAttributes();
      for (com.terraframe.mojo.dataaccess.AttributeIF attributeIF : valueObject.getAttributeArrayIF())
      {
        System.out.println(attributeIF.getDisplayLabel(Locale.ENGLISH));
      }
    }

  }

  @StartSession
  public static void queryAggregatedCases() throws Exception
  {
    QueryFactory qf = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(qf);

    String xml = FileIO.readString("/Users/nathan/workspace3.4/MDSS/src/test/aggregatedcase.xml");

    ValueQueryParser valueQueryParser = new ValueQueryParser(xml, valueQuery);

    Map<String, GeneratedEntityQuery> queryMap = valueQueryParser.parse();

    MdBusinessDAOIF aggregatedCaseMdBus = MdBusinessDAO.getMdBusinessDAO(AggregatedCase.CLASS);
    AggregatedCaseQuery aggregatedCaseQuery = (AggregatedCaseQuery)queryMap.get(aggregatedCaseMdBus.getTypeName());


    MdRelationshipDAOIF caseTreatmentStockRel = MdRelationshipDAO.getMdRelationshipDAO(CaseTreatmentStock.CLASS);

    for (String gridAlias : queryMap.keySet())
    {
      GeneratedEntityQuery generatedQuery = queryMap.get(gridAlias);

      if (generatedQuery instanceof TreatmentGridQuery)
      {
        TreatmentGridQuery treatmentGridQuery = (TreatmentGridQuery)generatedQuery;
        //Alias startse with CaseTreatmentStock_
        if (gridAlias.startsWith(caseTreatmentStockRel.getTypeName()+"_"))
        {
          String caseTreatmentStockAlias = getRelationshipAlias(gridAlias);
          CaseTreatmentStockQuery ctsq = (CaseTreatmentStockQuery)queryMap.get(caseTreatmentStockAlias);
          valueQuery.AND(aggregatedCaseQuery.treatmentStock(ctsq));
          valueQuery.AND(ctsq.hasChild(treatmentGridQuery));
        }
        else
        {
          String caseTreatmentAlias = getRelationshipAlias(gridAlias);
          CaseTreatmentQuery ctq = (CaseTreatmentQuery)queryMap.get(caseTreatmentAlias);
          valueQuery.AND(aggregatedCaseQuery.treatment(ctq));
          valueQuery.AND(ctq.hasChild(treatmentGridQuery));
        }
      }
      else if (generatedQuery instanceof ReferralGridQuery)
      {
        ReferralGridQuery referralGridQuery = (ReferralGridQuery)generatedQuery;
        String caseReferralAlias = getRelationshipAlias(gridAlias);
        CaseReferralQuery crq = (CaseReferralQuery)queryMap.get(caseReferralAlias);
        valueQuery.AND(aggregatedCaseQuery.referral(crq));
        valueQuery.AND(crq.hasChild(referralGridQuery));
      }
      else if (generatedQuery instanceof DiagnosticGridQuery)
      {
        DiagnosticGridQuery diagnosticGridQuery = (DiagnosticGridQuery)generatedQuery;
        String caseDiagnosticAlias = getRelationshipAlias(gridAlias);
        CaseDiagnosticQuery cdq = (CaseDiagnosticQuery)queryMap.get(caseDiagnosticAlias);
        valueQuery.AND(aggregatedCaseQuery.diagnosticMethod(cdq));
        valueQuery.AND(cdq.hasChild(diagnosticGridQuery));
      }
      else if (generatedQuery instanceof TreatmentMethodGridQuery)
      {
        TreatmentMethodGridQuery treatmentMethodGridQuery = (TreatmentMethodGridQuery)generatedQuery;
        String caseTreatmentMethodAlias = getRelationshipAlias(gridAlias);
        CaseTreatmentMethodQuery ctmq = (CaseTreatmentMethodQuery)queryMap.get(caseTreatmentMethodAlias);
        valueQuery.AND(aggregatedCaseQuery.treatmentMethod(ctmq));
        valueQuery.AND(ctmq.hasChild(treatmentMethodGridQuery));
      }
    }

    System.out.println(valueQuery.getSQL());

    for (ValueObject valueObject : valueQuery.getIterator())
    {
      valueObject.printAttributes();
      for (com.terraframe.mojo.dataaccess.AttributeIF attributeIF : valueObject.getAttributeArrayIF())
      {
        System.out.println(attributeIF.getDisplayLabel(Locale.ENGLISH));
      }
    }

  }

  /**
   * Returns the alias for the relationship query that maps to the grid query
   * with the given alias.
   */
  private static String getRelationshipAlias(String gridAlias)
  {
    int firstIndex = gridAlias.indexOf("_", 0);
    int secondIndex = gridAlias.indexOf("_", firstIndex+1);

    return gridAlias.substring(0, secondIndex);
  }

  @StartSession
  private static void createGeoEntities(String sessionId) throws IOException
  {
  }

  private static void r(GeoEntity child)
  {
    List<GeoEntity> parents = child.getImmediateParents();
    System.out.println(child.getId()+ " - " +child.getEntityName() + " - " + child.getType());
    for(GeoEntity parent : parents)
    {
      r(parent);
    }
  }

  @Transaction
  private static void createGeoEntities()
  {
    GeoEntity country = new Country();
    country.setGeoId(genGeoId());
    country.setEntityName("Country 1");
    country.apply();

    GeoEntity province = new Province();
    province.setGeoId(genGeoId());
    province.setEntityName("Province 1");
    province.apply();
    province.addLocatedInGeoEntity(country).apply();

    GeoEntity district1 = new District();
    district1.setGeoId(genGeoId());
    district1.setEntityName("District 1");
    district1.apply();
    district1.addLocatedInGeoEntity(province).apply();

    GeoEntity district2 = new District();
    district2.setGeoId(genGeoId());
    district2.setEntityName("District 2");
    district2.apply();
    district2.addLocatedInGeoEntity(province).apply();
  }

  private static String genGeoId()
  {
    String r = String.valueOf(Math.random());
    return r.substring(r.length()-6);
  }

  @StartSession
  private static void testDefineAllowedTree(String sessionId)
  {
    QueryFactory f = new QueryFactory();
    CountryQuery q = new CountryQuery(f);

    OIterator<? extends GeoEntity> iter = q.getIterator();
    try
    {
      String gHid = iter.next().getId();
      String json = GeoHierarchy.defineAllowedTree(gHid);
      System.out.println(json);
    }
    finally
    {
      iter.close();
    }
  }
}
