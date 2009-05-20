package dss.vector.solutions.intervention.monitor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.dataaccess.database.DuplicateDataDatabaseException;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.intervention.BloodslideResponse;
import dss.vector.solutions.intervention.Drug;
import dss.vector.solutions.intervention.FeverResponse;
import dss.vector.solutions.intervention.FeverTreatment;
import dss.vector.solutions.intervention.HumanSex;
import dss.vector.solutions.intervention.RDTResponse;
import dss.vector.solutions.intervention.RDTResult;

public class SurveyTest extends TestCase
{
  private static GeoEntity      geoEntity = null;

  private static Wall           wall      = null;

  private static Roof           roof      = null;

  private static Drug           drug      = null;

  private static FeverTreatment treatment = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(SurveyTest.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        classSetUp();
      }

      protected void tearDown()
      {
        classTearDown();
      }

    };

    return wrapper;
  }

  protected static void classTearDown()
  {
    geoEntity.delete();
    wall.delete();
    roof.delete();
    drug.delete();
    treatment.delete();
  }

  protected static void classSetUp()
  {
    geoEntity = new SentinelSite();
    geoEntity.setGeoId("9");
    geoEntity.setEntityName("Sentinel Site");
    geoEntity.apply();

    wall = new Wall();
    wall.setWallName("testWall");
    wall.setDisplayLabel("Test Wall");
    wall.apply();

    roof = new Roof();
    roof.setRoofName("testRoof");
    roof.setDisplayLabel("Test Roof");
    roof.apply();

    drug = new Drug();
    drug.getDisplayLabel().setDefaultLocale("Test Drug");
    drug.setDrugName("testDrug");
    drug.apply();
    
    treatment = new FeverTreatment();
    treatment.getDisplayLabel().setDefaultLocale("Test FeverTreatment");
    treatment.setTreatmentName("testFeverTreatment");
    treatment.apply();
  }

  public void testCreateSurveyPoint()
  {
    Date date = new Date();

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    try
    {
      SurveyPoint test = SurveyPoint.get(point.getId());

      assertNotNull(test);
      assertEquals(test.getSurveyDate(), point.getSurveyDate());
      assertEquals(test.getGeoEntity().getId(), point.getGeoEntity().getId());
    }
    finally
    {
      point.delete();
    }
  }

  public void testCurrentDateProblem()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 99);
    Date date = calendar.getTime();

    try
    {
      SurveyPoint point = new SurveyPoint();
      point.setSurveyDate(date);
      point.setGeoEntity(geoEntity);
      point.apply();
      point.delete();

      fail("Able to create a survey point with a survey date after the current date");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof CurrentDateProblem);
    }
  }

  public void testSearchSurveyPoint()
  {
    Date date = new Date();

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    try
    {
      SurveyPoint test = SurveyPoint.searchByGeoEntityAndDate(geoEntity, date);

      assertNotNull(test);
      assertEquals(test.getSurveyDate(), point.getSurveyDate());
      assertEquals(test.getGeoEntity().getId(), point.getGeoEntity().getId());
    }
    finally
    {
      point.delete();
    }
  }

  public void testSearchEmptySurveyPoint()
  {
    assertNull(SurveyPoint.searchByGeoEntityAndDate(geoEntity, new Date()));
  }

  public void createNet()
  {
    Net net = new Net();
    net.setNetName("Test_Net");
    net.setDisplayLabel("Test Net");
    net.apply();

    try
    {
      Net test = Net.get(net.getId());
      assertEquals(net.getNetName(), test.getNetName());
      assertEquals(net.getDisplayLabel(), test.getDisplayLabel());
    }
    finally
    {
      net.delete();
    }
  }

  public void testGetAllNets()
  {
    Net[] nets = Net.getAll();

    assertEquals(12, nets.length);
  }

  public void testGelAllLeafNets()
  {
    Net[] leafs = Net.getAllLeafs();

    assertEquals(10, leafs.length);
  }

  public void testCreateHousehold()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);
    household.apply();

    try
    {
      Household test = Household.get(household.getId());

      assertEquals(test.getSurveyPoint().getId(), point.getId());
      assertEquals(test.getHouseholdName(), household.getHouseholdName());
      assertEquals(test.getUrban(), household.getUrban());
      assertEquals(test.getPeople(), household.getPeople());
      assertEquals(test.getWall().getId(), household.getWall().getId());
      assertEquals(test.getWallInfo(), household.getWallInfo());
      assertEquals(test.getRoof().getId(), household.getRoof().getId());
      assertEquals(test.getRoofInfo(), household.getRoofInfo());
      assertEquals(1, test.getWindowType().size());
      assertEquals(WindowType.ANY_WINDOW, test.getWindowType().get(0));
      assertEquals(test.getRooms(), household.getRooms());
      assertEquals(test.getLastSprayed(), household.getLastSprayed());
      assertEquals(test.getNets(), household.getNets());
      assertEquals(test.getNetsUsed(), household.getNetsUsed());
      assertEquals(test.getSleptUnderNets(), household.getSleptUnderNets());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (household.isAppliedToDB())
      {
        household.delete();
      }

      point.delete();
    }
  }

  public void testApplyAll()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);

    Map<String, HouseholdNet> map = new HashMap<String, HouseholdNet>();

    for (Net net : Net.getAllLeafs())
    {
      map.put(net.getId(), new HouseholdNet(household, net));
    }

    Collection<HouseholdNet> values = map.values();
    HouseholdNet[] nets = values.toArray(new HouseholdNet[values.size()]);

    for (HouseholdNet net : nets)
    {
      net.setAmount(30);
    }

    household.applyAll(nets);

    try
    {
      Household test = Household.get(household.getId());
      List<HouseholdNet> testNets = new LinkedList<HouseholdNet>();

      for (HouseholdNet net : test.getAllNetsRel())
      {
        testNets.add(net);
      }

      assertEquals(test.getSurveyPoint().getId(), point.getId());
      assertEquals(test.getHouseholdName(), household.getHouseholdName());
      assertEquals(test.getUrban(), household.getUrban());
      assertEquals(test.getPeople(), household.getPeople());
      assertEquals(test.getWall().getId(), household.getWall().getId());
      assertEquals(test.getWallInfo(), household.getWallInfo());
      assertEquals(test.getRoof().getId(), household.getRoof().getId());
      assertEquals(test.getRoofInfo(), household.getRoofInfo());
      assertEquals(1, test.getWindowType().size());
      assertEquals(WindowType.ANY_WINDOW, test.getWindowType().get(0));
      assertEquals(test.getRooms(), household.getRooms());
      assertEquals(test.getLastSprayed(), household.getLastSprayed());
      assertEquals(test.getNets(), household.getNets());
      assertEquals(test.getNetsUsed(), household.getNetsUsed());
      assertEquals(test.getSleptUnderNets(), household.getSleptUnderNets());
      assertEquals(nets.length, testNets.size());

      for (int i = 0; i < nets.length; i++)
      {
        assertEquals(nets[i].getAmount(), testNets.get(i).getAmount());
      }
    }
    finally
    {
      household.delete();
      point.delete();
    }
  }

  public void testSetAbstractNet()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);

    HouseholdNet[] nets = household.getHouseholdNets();

    for (HouseholdNet net : nets)
    {
      net.setAmount(30);
    }

    household.applyAll(nets);

    try
    {
      Household test = Household.get(household.getId());
      List<HouseholdNet> testNets = new LinkedList<HouseholdNet>();

      for (HouseholdNet net : test.getAllNetsRel())
      {
        testNets.add(net);
      }

      assertEquals(test.getSurveyPoint().getId(), point.getId());
      assertEquals(test.getHouseholdName(), household.getHouseholdName());
      assertEquals(test.getUrban(), household.getUrban());
      assertEquals(test.getPeople(), household.getPeople());
      assertEquals(test.getWall().getId(), household.getWall().getId());
      assertEquals(test.getWallInfo(), household.getWallInfo());
      assertEquals(test.getRoof().getId(), household.getRoof().getId());
      assertEquals(test.getRoofInfo(), household.getRoofInfo());
      assertEquals(1, test.getWindowType().size());
      assertEquals(WindowType.ANY_WINDOW, test.getWindowType().get(0));
      assertEquals(test.getRooms(), household.getRooms());
      assertEquals(test.getLastSprayed(), household.getLastSprayed());
      assertEquals(test.getNets(), household.getNets());
      assertEquals(test.getNetsUsed(), household.getNetsUsed());
      assertEquals(test.getSleptUnderNets(), household.getSleptUnderNets());
      assertEquals(10, testNets.size());

      for (int i = 0; i < 10; i++)
      {
        assertEquals(nets[i].getAmount(), testNets.get(i).getAmount());
      }
    }
    finally
    {
      household.delete();
      point.delete();
    }
  }

  public void testEditHouseholds()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);

    HouseholdNet[] nets = household.getHouseholdNets();

    for (HouseholdNet net : nets)
    {
      net.setAmount(30);
    }

    household.applyAll(nets);

    Household edit = Household.lock(household.getId());
    edit.setHouseholdName("Edit Name");

    nets = edit.getHouseholdNets();

    for (HouseholdNet net : nets)
    {
      net.setAmount(50);
    }

    edit.applyAll(nets);

    try
    {
      Household test = Household.get(household.getId());
      List<HouseholdNet> testNets = new LinkedList<HouseholdNet>();

      for (HouseholdNet net : test.getAllNetsRel())
      {
        testNets.add(net);
      }

      assertEquals(test.getSurveyPoint().getId(), point.getId());
      assertEquals(test.getHouseholdName(), edit.getHouseholdName());
      assertEquals(test.getUrban(), edit.getUrban());
      assertEquals(test.getPeople(), edit.getPeople());
      assertEquals(test.getWall().getId(), edit.getWall().getId());
      assertEquals(test.getWallInfo(), edit.getWallInfo());
      assertEquals(test.getRoof().getId(), edit.getRoof().getId());
      assertEquals(test.getRoofInfo(), edit.getRoofInfo());
      assertEquals(1, test.getWindowType().size());
      assertEquals(WindowType.ANY_WINDOW, test.getWindowType().get(0));
      assertEquals(test.getRooms(), edit.getRooms());
      assertEquals(test.getLastSprayed(), edit.getLastSprayed());
      assertEquals(test.getNets(), edit.getNets());
      assertEquals(test.getNetsUsed(), edit.getNetsUsed());
      assertEquals(test.getSleptUnderNets(), edit.getSleptUnderNets());
      assertEquals(10, testNets.size());

      for (int i = 0; i < 10; i++)
      {
        assertEquals(nets[i].getAmount(), testNets.get(i).getAmount());
      }
    }
    finally
    {
      edit.delete();
      point.delete();
    }
  }

  public void testGetHouseholds()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);
    household.apply();

    Household household2 = new Household();
    household2.setSurveyPoint(point);
    household2.setHouseholdName("Some name");
    household2.setUrban(b);
    household2.setPeople(20);
    household2.setWall(wall);
    household2.setWallInfo("Some generic info");
    household2.setRoof(roof);
    household2.setRoofInfo("Some roof info");
    household2.addWindowType(WindowType.ANY_WINDOW);
    household2.setRooms(30);
    household2.setLastSprayed(7);
    household2.setNets(24);
    household2.setNetsUsed(2);
    household2.setSleptUnderNets(14);
    household2.apply();

    try
    {
      Set<Household> list = new TreeSet<Household>(new Comparator<Household>()
      {
        public int compare(Household o1, Household o2)
        {
          return o1.getId().compareTo(o2.getId());
        }
      });

      for (Household test : point.getAllHouseholds())
      {
        list.add(test);
      }

      assertEquals(2, list.size());
      assertTrue(list.contains(household));
      assertTrue(list.contains(household2));
    }
    finally
    {
      household.delete();
      household2.delete();
      point.delete();
    }
  }

  public void testInvalidLastSprayedValue()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    try
    {
      Household household = new Household();
      household.setSurveyPoint(point);
      household.setHouseholdName("Some name");
      household.setUrban(b);
      household.setPeople(20);
      household.setWall(wall);
      household.setWallInfo("Some generic info");
      household.setRoof(roof);
      household.setRoofInfo("Some roof info");
      household.addWindowType(WindowType.ANY_WINDOW);
      household.setRooms(30);
      household.setLastSprayed(15);
      household.setNets(24);
      household.setNetsUsed(2);
      household.setSleptUnderNets(14);
      household.apply();

      fail("Able to set a last sprayed month larger than 12");
    }
    catch (RuntimeException e)
    {
      // This is expected
    }
    finally
    {
      point.delete();
    }
  }

  public void testCreatePerson() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    Boolean b = new Boolean(false);
    BigDecimal haemoglobin = new BigDecimal("99.2");
    haemoglobin = haemoglobin.stripTrailingZeros();

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);
    household.apply();

    Person person = new Person();
    person.setHousehold(household);
    person.setDob(date);
    person.setAnaemiaTreatment(drug);
    person.setFeverTreatment(treatment);
    person.setHaemoglobin(haemoglobin);
    person.setHaemoglobinMeasured(true);
    person.setIron(true);
    person.setMalariaTreatment(drug);
    person.setPersonId("000");
    person.setPregnant(true);
    person.setRdtTreatment(drug);
    person.addBloodslide(BloodslideResponse.NOT_AVAILABLE);
    person.addFever(FeverResponse.DONT_KNOW);
    person.addMalaria(FeverResponse.YES);
    person.addPayment(FeverResponse.NO);
    person.addPerformedRDT(RDTResponse.REFUSED);
    person.addRDTResult(RDTResult.VIVAX_POSITIVE);
    person.addRDTResult(RDTResult.OVALE_POSITIVE);
    person.addSex(HumanSex.FEMALE);
    person.apply();

    try
    {
      Person test = Person.get(person.getId());

      assertNotNull(test);
      assertEquals(household.getId(), test.getHousehold().getId());
      assertEquals(date, test.getDob());
      assertEquals(drug.getId(), test.getAnaemiaTreatment().getId());
      assertEquals(treatment.getId(), test.getFeverTreatment().getId());
      assertEquals(haemoglobin, test.getHaemoglobin());
      assertEquals(new Boolean(true), test.getHaemoglobinMeasured());
      assertEquals(new Boolean(true), test.getIron());
      assertEquals(drug.getId(), test.getMalariaTreatment().getId());
      assertEquals("000", test.getPersonId());
      assertEquals(new Boolean(true), test.getPregnant());
      assertEquals(drug.getId(), test.getRdtTreatment().getId());
      assertEquals(1, test.getBloodslide().size());
      assertEquals(BloodslideResponse.NOT_AVAILABLE, test.getBloodslide().get(0));
      assertEquals(1, test.getFever().size());
      assertEquals(FeverResponse.DONT_KNOW, test.getFever().get(0));
      assertEquals(1, test.getMalaria().size());
      assertEquals(FeverResponse.YES, test.getMalaria().get(0));
      assertEquals(1, test.getPayment().size());
      assertEquals(FeverResponse.NO, test.getPayment().get(0));
      assertEquals(1, test.getPerformedRDT().size());
      assertEquals(RDTResponse.REFUSED, test.getPerformedRDT().get(0));
      assertEquals(2, test.getRDTResult().size());
      assertTrue(test.getRDTResult().contains(RDTResult.VIVAX_POSITIVE));
      assertTrue(test.getRDTResult().contains(RDTResult.OVALE_POSITIVE));
      assertEquals(1, test.getSex().size());
      assertEquals(HumanSex.FEMALE, test.getSex().get(0));
    }
    finally
    {
      person.delete();
      household.delete();
      point.delete();
    }
  }

  public void testCreatePersonView() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    Boolean b = new Boolean(false);
    BigDecimal haemoglobin = new BigDecimal("99.2");
    haemoglobin = haemoglobin.stripTrailingZeros();
    
    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();
    
    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);
    household.apply();
    
    PersonView person = new PersonView();
    person.setHousehold(household);
    person.setDob(date);
    person.setAnaemiaTreatment(drug);
    person.setFeverTreatment(treatment);
    person.setHaemoglobin(haemoglobin);
    person.setHaemoglobinMeasured(true);
    person.setIron(true);
    person.setMalariaTreatment(drug);
    person.setPersonId("000");
    person.setPregnant(true);
    person.setRdtTreatment(drug);
    person.addBloodslide(BloodslideResponse.NOT_AVAILABLE);
    person.addFever(FeverResponse.DONT_KNOW);
    person.addMalaria(FeverResponse.YES);
    person.addPayment(FeverResponse.NO);
    person.addPerformedRDT(RDTResponse.REFUSED);
    person.addRDTResult(RDTResult.VIVAX_POSITIVE);
    person.addRDTResult(RDTResult.OVALE_POSITIVE);
    person.addSex(HumanSex.FEMALE);
    person.apply();
    
    try
    {
      PersonView test = Person.getView(person.getConcreteId());
      
      assertNotNull(test);
      assertEquals(household.getId(), test.getHousehold().getId());
      assertEquals(date, test.getDob());
      assertEquals(drug.getId(), test.getAnaemiaTreatment().getId());
      assertEquals(treatment.getId(), test.getFeverTreatment().getId());
      assertEquals(haemoglobin, test.getHaemoglobin());
      assertEquals(new Boolean(true), test.getHaemoglobinMeasured());
      assertEquals(new Boolean(true), test.getIron());
      assertEquals(drug.getId(), test.getMalariaTreatment().getId());
      assertEquals("000", test.getPersonId());
      assertEquals(new Boolean(true), test.getPregnant());
      assertEquals(drug.getId(), test.getRdtTreatment().getId());
      assertEquals(1, test.getBloodslide().size());
      assertEquals(BloodslideResponse.NOT_AVAILABLE, test.getBloodslide().get(0));
      assertEquals(1, test.getFever().size());
      assertEquals(FeverResponse.DONT_KNOW, test.getFever().get(0));
      assertEquals(1, test.getMalaria().size());
      assertEquals(FeverResponse.YES, test.getMalaria().get(0));
      assertEquals(1, test.getPayment().size());
      assertEquals(FeverResponse.NO, test.getPayment().get(0));
      assertEquals(1, test.getPerformedRDT().size());
      assertEquals(RDTResponse.REFUSED, test.getPerformedRDT().get(0));
      assertEquals(2, test.getRDTResult().size());
      assertTrue(test.getRDTResult().contains(RDTResult.VIVAX_POSITIVE));
      assertTrue(test.getRDTResult().contains(RDTResult.OVALE_POSITIVE));
      assertEquals(1, test.getSex().size());
      assertEquals(HumanSex.FEMALE, test.getSex().get(0));
    }
    finally
    {
      person.delete();
      household.delete();
      point.delete();
    }
  }
  
  public void testUniquePersonId() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    Boolean b = new Boolean(false);
    BigDecimal haemoglobin = new BigDecimal("99.2");
    haemoglobin = haemoglobin.stripTrailingZeros();

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);
    household.apply();

    Person person = new Person();
    person.setHousehold(household);
    person.setDob(date);
    person.setAnaemiaTreatment(drug);
    person.setFeverTreatment(treatment);
    person.setHaemoglobin(haemoglobin);
    person.setHaemoglobinMeasured(true);
    person.setIron(true);
    person.setMalariaTreatment(drug);
    person.setPersonId("000");
    person.setPregnant(true);
    person.setRdtTreatment(drug);
    person.addBloodslide(BloodslideResponse.NOT_AVAILABLE);
    person.addFever(FeverResponse.DONT_KNOW);
    person.addMalaria(FeverResponse.YES);
    person.addPayment(FeverResponse.NO);
    person.addPerformedRDT(RDTResponse.REFUSED);
    person.addRDTResult(RDTResult.VIVAX_POSITIVE);
    person.addRDTResult(RDTResult.OVALE_POSITIVE);
    person.addSex(HumanSex.FEMALE);
    person.apply();

    try
    {
      Person duplicate = new Person();
      duplicate.setHousehold(household);
      duplicate.setDob(date);
      duplicate.setAnaemiaTreatment(drug);
      duplicate.setFeverTreatment(treatment);
      duplicate.setHaemoglobin(haemoglobin);
      duplicate.setHaemoglobinMeasured(true);
      duplicate.setIron(true);
      duplicate.setMalariaTreatment(drug);
      duplicate.setPersonId("000");
      duplicate.setPregnant(true);
      duplicate.setRdtTreatment(drug);
      duplicate.addBloodslide(BloodslideResponse.NOT_AVAILABLE);
      duplicate.addFever(FeverResponse.DONT_KNOW);
      duplicate.addMalaria(FeverResponse.YES);
      duplicate.addPayment(FeverResponse.NO);
      duplicate.addPerformedRDT(RDTResponse.REFUSED);
      duplicate.addRDTResult(RDTResult.VIVAX_POSITIVE);
      duplicate.addRDTResult(RDTResult.OVALE_POSITIVE);
      duplicate.addSex(HumanSex.FEMALE);
      duplicate.apply();

      duplicate.delete();

      fail("Able to create a duplicate person id");
    }
    catch (DuplicateDataDatabaseException e)
    {
      // This is expected
    }
    finally
    {
      person.delete();
      household.delete();
      point.delete();
    }
  }

  public void testPregnantSex() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    Boolean b = new Boolean(false);
    BigDecimal haemoglobin = new BigDecimal("99.2");
    haemoglobin = haemoglobin.stripTrailingZeros();

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);
    household.apply();

    try
    {
      Person person = new Person();
      person.setHousehold(household);
      person.setDob(date);
      person.setAnaemiaTreatment(drug);
      person.setFeverTreatment(treatment);
      person.setHaemoglobin(haemoglobin);
      person.setHaemoglobinMeasured(true);
      person.setIron(true);
      person.setMalariaTreatment(drug);
      person.setPersonId("000");
      person.setPregnant(true);
      person.setRdtTreatment(drug);
      person.addBloodslide(BloodslideResponse.NOT_AVAILABLE);
      person.addFever(FeverResponse.DONT_KNOW);
      person.addMalaria(FeverResponse.YES);
      person.addPayment(FeverResponse.NO);
      person.addPerformedRDT(RDTResponse.REFUSED);
      person.addRDTResult(RDTResult.VIVAX_POSITIVE);
      person.addRDTResult(RDTResult.OVALE_POSITIVE);
      person.addSex(HumanSex.MALE);
      person.apply();

      person.delete();

      fail("Able to create a preganent male");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();
      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof PregnantProblem);
    }
    finally
    {
      household.delete();
      point.delete();
    }
  }

  public void testGetPersons() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2008-01-01");
    Boolean b = new Boolean(false);
    BigDecimal haemoglobin = new BigDecimal("99.2");
    haemoglobin = haemoglobin.stripTrailingZeros();

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(24);
    household.setNetsUsed(2);
    household.setSleptUnderNets(14);
    household.apply();

    Person person = new Person();
    person.setHousehold(household);
    person.setDob(date);
    person.setAnaemiaTreatment(drug);
    person.setFeverTreatment(treatment);
    person.setHaemoglobin(haemoglobin);
    person.setHaemoglobinMeasured(true);
    person.setIron(true);
    person.setMalariaTreatment(drug);
    person.setPersonId("000");
    person.setPregnant(true);
    person.setRdtTreatment(drug);
    person.addBloodslide(BloodslideResponse.NOT_AVAILABLE);
    person.addFever(FeverResponse.DONT_KNOW);
    person.addMalaria(FeverResponse.YES);
    person.addPayment(FeverResponse.NO);
    person.addPerformedRDT(RDTResponse.REFUSED);
    person.addRDTResult(RDTResult.VIVAX_POSITIVE);
    person.addRDTResult(RDTResult.OVALE_POSITIVE);
    person.addSex(HumanSex.FEMALE);
    person.apply();

    Person person2 = new Person();
    person2.setHousehold(household);
    person2.setDob(date);
    person2.setAnaemiaTreatment(drug);
    person2.setFeverTreatment(treatment);
    person2.setHaemoglobin(haemoglobin);
    person2.setHaemoglobinMeasured(true);
    person2.setIron(true);
    person2.setMalariaTreatment(drug);
    person2.setPersonId("001");
    person2.setPregnant(true);
    person2.setRdtTreatment(drug);
    person2.addBloodslide(BloodslideResponse.NOT_AVAILABLE);
    person2.addFever(FeverResponse.DONT_KNOW);
    person2.addMalaria(FeverResponse.YES);
    person2.addPayment(FeverResponse.NO);
    person2.addPerformedRDT(RDTResponse.REFUSED);
    person2.addRDTResult(RDTResult.VIVAX_POSITIVE);
    person2.addRDTResult(RDTResult.OVALE_POSITIVE);
    person2.addSex(HumanSex.FEMALE);
    person2.apply();

    try
    {
      Set<Person> list = new TreeSet<Person>(new Comparator<Person>()
      {
        public int compare(Person o1, Person o2)
        {
          return o1.getId().compareTo(o2.getId());
        }
      });

      for (Person p : household.getAllPersons())
      {
        list.add(p);
      }

      assertEquals(2, list.size());
      assertTrue(list.contains(person));
      assertTrue(list.contains(person2));
    }
    finally
    {
      person.delete();
      person2.delete();
      household.delete();
      point.delete();
    }
  }

  public void testCreateSurveyPointVeiw()
  {
    Date date = new Date();

    SurveyPointView point = new SurveyPointView();
    point.setSurveyDate(date);
    point.setGeoId(geoEntity.getGeoId());
    point.apply();

    try
    {
      SurveyPointView test = SurveyPoint.getView(point.getConcreteId());

      assertNotNull(test);
      assertEquals(test.getSurveyDate(), point.getSurveyDate());
      assertEquals(test.getGeoId(), point.getGeoId());
    }
    finally
    {
      point.deleteConcrete();
    }
  }

  public void testInvalidNetsUsed()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    try
    {
      Household household = new Household();
      household.setSurveyPoint(point);
      household.setHouseholdName("Some name");
      household.setUrban(b);
      household.setPeople(20);
      household.setWall(wall);
      household.setWallInfo("Some generic info");
      household.setRoof(roof);
      household.setRoofInfo("Some roof info");
      household.addWindowType(WindowType.ANY_WINDOW);
      household.setRooms(30);
      household.setLastSprayed(7);
      household.setNets(24);
      household.setNetsUsed(30);
      household.setSleptUnderNets(14);
      household.apply();

      household.delete();

      fail("Able to create a household where the nets used is greater than nets sprayed");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof NetQuantityProblem);
    }
    finally
    {
      point.delete();
    }

  }

  public void testInvalidSleptUnderNets()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    try
    {
      Household household = new Household();
      household.setSurveyPoint(point);
      household.setHouseholdName("Some name");
      household.setUrban(b);
      household.setPeople(20);
      household.setWall(wall);
      household.setWallInfo("Some generic info");
      household.setRoof(roof);
      household.setRoofInfo("Some roof info");
      household.addWindowType(WindowType.ANY_WINDOW);
      household.setRooms(30);
      household.setLastSprayed(7);
      household.setNets(24);
      household.setNetsUsed(3);
      household.setSleptUnderNets(30);
      household.apply();

      household.delete();

      fail("Able to create a household where the nets used is greater than nets sprayed");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof NetQuantityProblem);
    }
    finally
    {
      point.delete();
    }
  }

  public void testNullNets()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    try
    {
      Household household = new Household();
      household.setSurveyPoint(point);
      household.setHouseholdName("Some name");
      household.setUrban(b);
      household.setPeople(20);
      household.setWall(wall);
      household.setWallInfo("Some generic info");
      household.setRoof(roof);
      household.setRoofInfo("Some roof info");
      household.addWindowType(WindowType.ANY_WINDOW);
      household.setRooms(30);
      household.setLastSprayed(7);
      household.setNetsUsed(4);
      household.setSleptUnderNets(30);
      household.apply();

      household.delete();

      fail("Able to create a household where the nets used is greater than nets sprayed");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(2, problems.size());

      for (ProblemIF problem : problems)
      {
        assertTrue(problem instanceof NetQuantityProblem);
      }
    }
    finally
    {
      point.delete();
    }
  }

  public void testInvalidNetAmount()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    Household household = new Household();
    household.setSurveyPoint(point);
    household.setHouseholdName("Some name");
    household.setUrban(b);
    household.setPeople(20);
    household.setWall(wall);
    household.setWallInfo("Some generic info");
    household.setRoof(roof);
    household.setRoofInfo("Some roof info");
    household.addWindowType(WindowType.ANY_WINDOW);
    household.setRooms(30);
    household.setLastSprayed(7);
    household.setNets(0);
    household.setNetsUsed(0);
    household.setSleptUnderNets(0);

    Collection<HouseholdNet> values = new LinkedList<HouseholdNet>();

    for (Net net : Net.getAllLeafs())
    {
      HouseholdNet value = new HouseholdNet(household, net);
      value.setAmount(30);

      values.add(value);
    }

    try
    {
      household.applyAll(values.toArray(new HouseholdNet[values.size()]));

      household.delete();

      fail("Able to apply household nets with invalid amount values");
    }
    catch (ProblemException e)
    {
      List<ProblemIF> problems = e.getProblems();

      assertEquals(values.size(), problems.size());

      for (ProblemIF problem : problems)
      {
        assertTrue(problem instanceof NetProblem);
      }
    }
    finally
    {
      point.delete();
    }

  }

  public void testInvalidWindowType()
  {
    Date date = new Date();

    Boolean b = new Boolean(false);

    SurveyPoint point = new SurveyPoint();
    point.setSurveyDate(date);
    point.setGeoEntity(geoEntity);
    point.apply();

    try
    {
      Household household = new Household();
      household.setSurveyPoint(point);
      household.setHouseholdName("Some name");
      household.setUrban(b);
      household.setPeople(20);
      household.setWall(wall);
      household.setWallInfo("Some generic info");
      household.setRoof(roof);
      household.setRoofInfo("Some roof info");
      household.setHasWindows(false);
      household.addWindowType(WindowType.ANY_WINDOW);
      household.setRooms(30);
      household.setLastSprayed(7);
      household.setNets(50);
      household.setNetsUsed(4);
      household.setSleptUnderNets(30);
      household.apply();

      household.delete();

      fail("Able to create a household where hasWindows=false && windowType != null");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF problem : problems)
      {
        assertTrue(problem instanceof WindowTypeProblem);
      }
    }
    finally
    {
      point.delete();
    }
  }
}
