package dss.vector.solutions.export;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.InvalidIdException;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.facade.Facade;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.RequestType;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.MDSSUserQuery;
import dss.vector.solutions.Person;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.entomology.assay.EfficacyAssayQuery;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.ontology.Term;

public class ExcelViewTest extends TestCase
{
  @Override
  public TestResult run()
  {
    return super.run();
  }

  @Override
  public void run(TestResult testResult)
  {
    super.run(testResult);
  }

  private static String sessionId;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ExcelViewTest.class);

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

  private static final String DIRECTORY = "doc/exampleMoViews/";

  private static Person john;
  private static Insecticide deltamethrin;
  private static Surface surface;
  private static SprayTeam sprayTeam;

  protected static void classSetUp()
  {
    sessionId = Facade.login("MDSS", "mdsstest2", new Locale[]{Locale.US});
    // Create data that is referenced by other views
    try
    {
      setupWithSession();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Request
  private static void setupWithSession()
  {
    john = new Person();
    john.setFirstName("John");
    john.setLastName("Wayne");
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(1907, 4, 26);
    john.setDateOfBirth(calendar.getTime());
    john.setResidentialGeoEntity(GeoEntity.searchByGeoId("700707"));
    john.apply();

    TeamMember member = new TeamMember();
    member.setMemberId("member2");
    member.setIsSprayLeader(true);
    member.setIsSprayOperator(true);
    member.apply();

    john.lock();
    john.setTeamMemberDelegate(member);
    john.apply();

    sprayTeam = new SprayTeam();
    sprayTeam.setTeamId("team1");
    sprayTeam.create("2828007", member.getId(), new String[] {member.getId()});

    deltamethrin = new Insecticide();
    deltamethrin.setActiveIngredient(Term.getByTermId("MIRO:10000133"));
    deltamethrin.setAmount(25.0);
    deltamethrin.setUnits(Term.getByTermId("MDSS:0000381"));
    deltamethrin.apply();

    try
    {
      surface = (Surface)GeoEntity.searchByGeoId("TestSurfaceId");
    }
    catch (InvalidIdException e)
    {
      surface = new Surface();
      surface.setGeoId("TestSurfaceId");
      surface.setEntityName("Test Surface");
      surface.applyWithParent(GeoEntity.searchByGeoId("700701103030").getId(), false, null);
    }
  }

  protected static void classTearDown()
  {
    cleanupWithSession(sessionId);
  }

  @Request
  private static void cleanupWithSession(String sessionId)
  {
    john.delete();
    deltamethrin.delete();
    sprayTeam.delete();
  }

//  public void testMopUpSpray() throws IOException
//  {
//    importOperatorSprayView(sessionId, "(027) OperatorSprayMopUp.xls");
//
//    // If we get here the import was successful.  Clean up the imported records
//
//  }

  public void testSuccessfulIndividualCase() throws IOException
  {
    importIndividualCaseExcelView(sessionId, "(001) IndividualCaseExcelView.xls");
  }

//  public void testSuccessfulMorphologicalSpecieGroup() throws IOException
//  {
//    importMorphologicalSpecieGroup(sessionId, "(016) MorphologicalSpecieGroupExcelView.xls");
//
//    Calendar calendar = Calendar.getInstance();
//    calendar.clear();
//    calendar.set(2009, 10, 2);
//    MosquitoCollectionPoint mcp = MosquitoCollectionPoint.findOrCreate(GeoEntity.searchByGeoId("1110000"), calendar.getTime());
//
//    MorphologicalSpecieGroupQuery query = new MorphologicalSpecieGroupQuery(new QueryFactory());
//    query.WHERE(query.getCollection().EQ(mcp));
//    OIterator<? extends MorphologicalSpecieGroup> iterator = query.getIterator();
//
//    if (!iterator.hasNext())
//    {
//      fail("No morphological specie group created");
//    }
//
//    MorphologicalSpecieGroup msg = iterator.next();
//    if (iterator.hasNext())
//    {
//      iterator.close();
//      fail("Multiple morphological specie groups created.  Expected only one.  Data may be corrupt.");
//    }
//
//    try
//    {
//      assertEquals("MIRO:30000042", msg.getIdentificationMethod().getTermId());
//      assertEquals(10, msg.getQuantity().intValue());
//      assertEquals(6, msg.getQuantityFemale().intValue());
//      assertEquals(4, msg.getQuantityMale().intValue());
//      assertEquals("MIRO:40002525", msg.getSpecie().getTermId());
//    }
//    finally
//    {
//      delete(sessionId, msg);
//      delete(sessionId, mcp);
//    }
//  }
//
//  public void testSuccessfulMosquitoCollection() throws IOException
//  {
//    importMosquitoCollection(sessionId, "(024) MosquitoCollectionView.xls");
//
//    Calendar calendar = Calendar.getInstance();
//    calendar.clear();
//    calendar.set(2009, 9, 31);
//    MosquitoCollection collection = MosquitoCollection.searchByGeoEntityAndDate(GeoEntity.searchByGeoId("100011"), calendar.getTime());
//    assertNotNull(collection);
//
//    MorphologicalSpecieGroup msg = null;
//    try
//    {
//      assertEquals("MDSS:0000316", collection.getCollectionMethod().getTermId());
//      MorphologicalSpecieGroupView[] groups = collection.getMorphologicalSpecieGroups();
//      assertEquals(1, groups.length);
//
//      msg = MorphologicalSpecieGroup.get(groups[0].getGroupId());
//      assertEquals("MIRO:30000042", msg.getIdentificationMethod().getTermId());
//      assertEquals(10, msg.getQuantity().intValue());
//      assertEquals(6, msg.getQuantityFemale().intValue());
//      assertEquals(4, msg.getQuantityMale().intValue());
//      assertEquals("MIRO:40002525", msg.getSpecie().getTermId());
//    }
//    finally
//    {
//      delete(sessionId, msg);
//      delete(sessionId, collection);
//    }
//  }

  public void testSuccessfulEfficacyAssay() throws IOException
  {
    importEfficacyAssay(sessionId, "(025) EfficacyAssayExcelView.xls");

    EfficacyAssayQuery query = new EfficacyAssayQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(surface));
//    query.WHERE(query.getInsecticideBrand().getProductName().getName().EQ(deltamethrin));
    query.WHERE(query.getColonyName().EQ("Test Colony"));
    OIterator<? extends EfficacyAssay> iterator = query.getIterator();

    if (!iterator.hasNext())
    {
      fail("No assay created");
    }

    EfficacyAssay assay = iterator.next();
    if (iterator.hasNext())
    {
      iterator.close();
      fail("Multiple assays created.  Expected only one.  Data may be corrupt.");
    }

    try
    {
      AdultAgeRange ageRange = assay.getAgeRange();
      assertNotNull(ageRange);
      assertEquals(0, ageRange.getStartPoint().intValue());
      assertEquals(4, ageRange.getEndPoint().intValue());
      assertEquals(45, assay.getExposureTime().intValue());
      assertEquals(4, assay.getFed().intValue());
      assertEquals(3, assay.getGravid().intValue());
      assertEquals(18, assay.getHoldingTime().intValue());
      assertEquals(2, assay.getQuantityDead().intValue());
      assertEquals(4, assay.getQuantityTested().intValue());
      assertEquals("MIRO:20000102", assay.getTestMethod().getTermId());
      assertEquals("MDSS:0000326", assay.getSex().getTermId());
      assertEquals("MIRO:40002458", assay.getSpecie().getTermId());
      assertEquals("MDSS:0000387", assay.getSurfacePostion().getTermId());
      assertEquals(2, assay.getTimeOnSurface().intValue());
    }
    finally
    {
      delete(sessionId, assay);
    }
  }

//  public void testSuccessfulIndicatorSurvey() throws IOException
//  {
//    importSurveyExcelView(sessionId, "(026) SurveyExcelView.xls");
//
//    Calendar calendar = Calendar.getInstance();
//    calendar.clear();
//    calendar.set(2007, 8, 1);
//    SurveyPoint surveyPoint = SurveyPoint.searchByGeoEntityAndDate(GeoEntity.searchByGeoId("700707"), calendar.getTime());
//
//    assertNotNull(surveyPoint);
//  }

  public void testSuccessfulGeoEntity() throws IOException
  {
    importGeoEntityView(sessionId, "(111) GeoEntityExcelView.xls");

    GeoEntity canada = GeoEntity.searchByGeoId("8675307");
    GeoEntity mexico = GeoEntity.searchByGeoId("8675308");
    GeoEntity usa = GeoEntity.searchByGeoId("8675309");

    assertEquals(Boolean.TRUE, canada.getActivated());
    assertEquals(Boolean.TRUE, mexico.getActivated());
    assertEquals(Boolean.TRUE, usa.getActivated());
    assertEquals("Canada", canada.getEntityName());
    assertEquals("Mexico", mexico.getEntityName());
    assertEquals("United States", usa.getEntityName());
    assertEquals(Country.CLASS, canada.getType());
    assertEquals(Country.CLASS, mexico.getType());
    assertEquals(Country.CLASS, usa.getType());
  }

  public void testSuccessfulPerson() throws IOException
  {
    importPersonView(sessionId, "(114) PersonExcelView.xls");

    // Check the imported data for accuracy
    MDSSUserQuery query = new MDSSUserQuery(new QueryFactory());
    query.WHERE(query.getUsername().EQ("Brian.J.Lewis@trash2009.com"));
    OIterator<? extends MDSSUser> iterator = query.getIterator();

    if (!iterator.hasNext())
    {
      fail("No user created.  Expected to create user Brian Lewis");
    }

    MDSSUser user = iterator.next();
    if (iterator.hasNext())
    {
      iterator.close();
      fail("Multiple users created.  Expected only one.  Data may be corrupt.");
    }

    if (!user.passwordEquals("vaness"))
    {
      fail("User password does not equal [vaness] as expected");
    }

    Person person = user.getPerson();
    if (person==null)
    {
      fail("Person not correctly associated with User.  Data may be corrupt");
    }

    try
    {
      assertEquals("Brian", person.getFirstName());
      assertEquals("Lewis", person.getLastName());
      assertEquals(new GregorianCalendar(1968, 4, 25).getTime(), person.getDateOfBirth());
      assertEquals("Brian", person.getFirstName());
      assertEquals("Brian", person.getFirstName());
      assertEquals("Brian", person.getFirstName());

      TeamMember sprayLeader = person.getTeamMemberDelegate();
      assertNotNull(sprayLeader);
      assertEquals("e13371", sprayLeader.getMemberId());

      GeoEntity residentialGeoEntity = person.getResidentialGeoEntity();
      assertNotNull(residentialGeoEntity);
      assertEquals("800803130070", residentialGeoEntity.getGeoId());

      GeoEntity workGeoEntity = person.getWorkGeoEntity();
      assertNotNull(workGeoEntity);
      assertEquals("88000136", workGeoEntity.getGeoId());
    }
    finally
    {
      delete(sessionId, person);
    }
  }

  @Request(RequestType.SESSION)
  private void delete(String sessionId, Mutable instance)
  {
    if (instance!=null)
    {
      instance.delete();
    }
  }

  @Request(RequestType.SESSION)
  private void importIndividualCaseExcelView(String sessionId, String fileName) throws IOException
  {
    ExcelImporter importer = new ExcelImporter(new FileInputStream(fileName));
    IndividualCaseExcelView.setupImportListener(importer.getContexts().get(0));
    printImportErrors(importer, DIRECTORY + fileName);
  }

  @Request(RequestType.SESSION)
  private void importOperatorSprayView(String sessionId, String fileName) throws IOException
  {
    ExcelImporter importer = new ExcelImporter(new FileInputStream(fileName));
    OperatorSprayExcelView.setupImportListener(importer.getContexts().get(0));
    printImportErrors(importer, DIRECTORY + fileName);
  }

  @Request(RequestType.SESSION)
  public void importEfficacyAssay(String sessionId, String fileName) throws IOException
  {
    ExcelImporter importer = new ExcelImporter(new FileInputStream(fileName));
    EfficacyAssayExcelView.setupImportListener(importer.getContexts().get(0));
    printImportErrors(importer, DIRECTORY + fileName);
  }

  @Request(RequestType.SESSION)
  public void importSurveyExcelView(String sessionId, String fileName) throws IOException
  {
    ExcelImporter importer = new ExcelImporter(new FileInputStream(fileName));
    SurveyExcelView.setupImportListener(importer.getContexts().get(0));
    printImportErrors(importer, DIRECTORY + fileName);
  }

  @Request(RequestType.SESSION)
  public void importGeoEntityView(String sessionId, String fileName) throws IOException
  {
    ExcelImporter importer = new ExcelImporter(new FileInputStream(fileName));
    GeoEntity earth = GeoEntity.searchByGeoId("000000");
    GeoEntityExcelView.setupImportListener(importer.getContexts().get(0), earth.getId());
    printImportErrors(importer, DIRECTORY + fileName);
  }

  @Request(RequestType.SESSION)
  public void importPersonView(String sessionId, String fileName) throws IOException
  {
    ExcelImporter importer = new ExcelImporter(new FileInputStream(fileName));
    PersonExcelView.setupImportListener(importer.getContexts().get(0));
    printImportErrors(importer, DIRECTORY + fileName);
  }

  @SuppressWarnings("unchecked")
  @Transaction
  private void printImportErrors(ExcelImporter importer, String fileName) throws IOException
  {
    byte[] bytes = importer.read();
    if (bytes.length > 0)
    {
      String errorMessage = "\n==================================\nErrors found in import of " + fileName + "\n";

      POIFSFileSystem fileSystem = new POIFSFileSystem(new ByteArrayInputStream(bytes));
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet errorSheet = workbook.getSheetAt(1);
      Iterator<HSSFRow> rowIterator = errorSheet.rowIterator();
      rowIterator.next();
      while (rowIterator.hasNext())
      {
        HSSFRow row = rowIterator.next();
        if (row.getCell(0) != null)
          errorMessage += "  [" + row.getCell(0).getNumericCellValue();
        if (row.getCell(1) != null)
          errorMessage += ", " + ExcelUtil.getString(row.getCell(1));
        if (row.getCell(2) != null)
          errorMessage += ", " + ExcelUtil.getString(row.getCell(2));
        errorMessage += "]\n";
      }
      System.err.println(errorMessage);
      throw new RuntimeException(errorMessage);
    }
  }

}
