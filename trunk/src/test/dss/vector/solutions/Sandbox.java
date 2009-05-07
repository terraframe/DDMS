package dss.vector.solutions;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.RoleDAOIF;
import com.terraframe.mojo.business.rbac.SingleActorDAOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdRelationshipDAOIF;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.metadata.MdRelationshipDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.util.FileIO;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.CountryQuery;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.GeoEntity;
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

//    String temp = "CaseTreatmentStock_SP_TreatmentGrid";
//
//    int firstIndex = temp.indexOf("_", 0);
//
//    int secondIndex = temp.indexOf("_", firstIndex+1);
//
//    String relString = temp.substring(0, secondIndex);
//
//    System.out.println(firstIndex+"  "+secondIndex+"  "+relString);


//    ClientSession session = ClientSession.createUserSession(ServerConstants.SYSTEM_USER_NAME, "mdsstest2");
//    ClientRequestIF request = session.getRequest();

//    createGeoEntities(request.getSessionId());
    //testDefineAllowedTree(request.getSessionId());

//    testQueries();

    queryAggregatedCases();
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

    List<TreatmentGridQuery> treatmentGridStockQueryList = new LinkedList<TreatmentGridQuery>();
    List<ReferralGridQuery> referralGridQueryList = new LinkedList<ReferralGridQuery>();
    List<DiagnosticGridQuery> diagnosticGridQueryList = new LinkedList<DiagnosticGridQuery>();
    List<TreatmentMethodGridQuery> treatmentMethodGridQueryList = new LinkedList<TreatmentMethodGridQuery>();
    List<TreatmentGridQuery> treatmentGridQueryList = new LinkedList<TreatmentGridQuery>();

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
          treatmentGridStockQueryList.add(treatmentGridQuery);
          String caseTreatmentStockAlias = getRelationshipAlias(gridAlias);
          CaseTreatmentStockQuery ctsq = (CaseTreatmentStockQuery)queryMap.get(caseTreatmentStockAlias);
          valueQuery.AND(aggregatedCaseQuery.treatmentStock(ctsq));
          valueQuery.AND(ctsq.hasChild(treatmentGridQuery));
        }
        else
        {
          treatmentGridQueryList.add(treatmentGridQuery);
          String caseTreatmentAlias = getRelationshipAlias(gridAlias);
          CaseTreatmentQuery ctq = (CaseTreatmentQuery)queryMap.get(caseTreatmentAlias);
          valueQuery.AND(aggregatedCaseQuery.treatment(ctq));
          valueQuery.AND(ctq.hasChild(treatmentGridQuery));
        }
      }
      else if (generatedQuery instanceof ReferralGridQuery)
      {
        ReferralGridQuery referralGridQuery = (ReferralGridQuery)generatedQuery;
        referralGridQueryList.add(referralGridQuery);
        String caseReferralAlias = getRelationshipAlias(gridAlias);
        CaseReferralQuery crq = (CaseReferralQuery)queryMap.get(caseReferralAlias);
        valueQuery.AND(aggregatedCaseQuery.referral(crq));
        valueQuery.AND(crq.hasChild(referralGridQuery));
      }
      else if (generatedQuery instanceof DiagnosticGridQuery)
      {
        DiagnosticGridQuery diagnosticGridQuery = (DiagnosticGridQuery)generatedQuery;
        diagnosticGridQueryList.add(diagnosticGridQuery);
        String caseDiagnosticAlias = getRelationshipAlias(gridAlias);
        CaseDiagnosticQuery cdq = (CaseDiagnosticQuery)queryMap.get(caseDiagnosticAlias);
        valueQuery.AND(aggregatedCaseQuery.diagnosticMethod(cdq));
        valueQuery.AND(cdq.hasChild(diagnosticGridQuery));
      }
      else if (generatedQuery instanceof TreatmentMethodGridQuery)
      {
        TreatmentMethodGridQuery treatmentMethodGridQuery = (TreatmentMethodGridQuery)generatedQuery;
        treatmentMethodGridQueryList.add(treatmentMethodGridQuery);
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
     MDSSUser u = new MDSSUser();
     u.setUsername("MDSS");
     u.setPassword("MDSS");
     u.apply();

     SingleActorDAOIF uDAO = (SingleActorDAOIF) BusinessFacade.getEntityDAO(u);
     RoleDAO.findRole(RoleDAOIF.ADMIN_ROLE).assignMember(uDAO);
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
