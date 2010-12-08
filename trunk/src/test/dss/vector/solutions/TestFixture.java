package dss.vector.solutions;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.entomology.ImmatureCollectionView;
import dss.vector.solutions.entomology.LifeStageDTO;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionViewDTO;
import dss.vector.solutions.entomology.SubCollectionViewDTO;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.geo.generated.SentinelSiteDTO;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.geo.generated.StockDepot;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifier;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.stock.StockStaffDTO;

public class TestFixture
{
  public static String getRandomTermId()
  {
    String geoId = new Long(new Date().getTime()).toString();
    return geoId;
  }

  public static Term createRandomTerm()
  {
    Term term = new Term();
    term.setTermId(TestFixture.getRandomTermId());
    term.setName("Test Term");
    term.setComment("Test Comment");
//    term.setObsolete(false);
    term.apply();

    return term;
  }

  public static Insecticide createInsecticide()
  {
    Term unit = TestFixture.createRandomTerm();
    Term activeIngredient = TestFixture.createRandomTerm();

    Insecticide insecticide = new Insecticide();
    insecticide.setActiveIngredient(activeIngredient);
    insecticide.setAmount(new Double(40.0));
    insecticide.setUnits(unit);
    insecticide.apply();

    return insecticide;
  }
  
  public static InsecticideBrand createInsecticideBrand()
  {
    InsecticideBrand deltamethrin = new InsecticideBrand();
    deltamethrin.setProductName(TestFixture.createRandomTerm());
    deltamethrin.addInsecticideUse(InsecticideBrandUse.IRS);
    deltamethrin.setDisease(Disease.getMalaria());
    deltamethrin.setActiveIngredient(TestFixture.createRandomTerm());
    deltamethrin.setConcentrationQuantifier(new BigDecimal("25"));
    deltamethrin.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
    deltamethrin.setUnitQuantifier(new BigDecimal(20.00));
    deltamethrin.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
    deltamethrin.setUnitsPerApplication(1);
    deltamethrin.setEnabled(true);
    deltamethrin.apply();

    return deltamethrin;
  }

  private static String getRandomGeoId()
  {
    String geoId = new Long(new Date().getTime()).toString();
    geoId = geoId.substring(Math.max(0, geoId.length() - 15), geoId.length());
    return geoId;
  }

  public static Country createRandomCountry()
  {
    Country country = new Country();
    country.setGeoId(TestFixture.getRandomGeoId());
    country.setEntityName("Test Country");
    country.apply();

    return country;
  }

  public static SentinelSite createRandomSite()
  {
    SentinelSite site = new SentinelSite();
    site.setGeoId(TestFixture.getRandomGeoId());
    site.setEntityName("Test Site");
    site.apply();

    return site;
  }

  public static SentinelSiteDTO createRandomSite(ClientRequestIF clientRequest)
  {
    SentinelSiteDTO site = new SentinelSiteDTO(clientRequest);
    site.setGeoId(TestFixture.getRandomGeoId());
    site.setEntityName("Test Site");
    site.apply();

    return site;
  }

  public static CollectionSite createRandomWaterBody()
  {
    CollectionSite site = new CollectionSite();
    site.setGeoId(TestFixture.getRandomGeoId());
    site.setEntityName("Test Site");
    site.apply();

    return site;
  }

  public static HealthFacility createRandomFacility()
  {
    HealthFacility facility = new HealthFacility();
    facility.setGeoId(TestFixture.getRandomGeoId());
    facility.setEntityName("Test Site");
    facility.apply();

    return facility;
  }

  public static Surface createRandomSurface()
  {
    Surface surface = new Surface();
    surface.setGeoId(TestFixture.getRandomGeoId());
    surface.setEntityName("Test Site");
    surface.apply();

    return surface;
  }

  public static SprayZone createRandomZone()
  {
    SprayZone sprayZone = new SprayZone();
    sprayZone.setGeoId(TestFixture.getRandomGeoId());
    sprayZone.setEntityName("Test Site");
    sprayZone.apply();

    return sprayZone;
  }

  public static StockDepot createRandomDepot()
  {
    StockDepot depot = new StockDepot();
    depot.setGeoId(TestFixture.getRandomGeoId());
    depot.setEntityName("Test Depot");
    depot.apply();

    return depot;
  }

  public static TermDTO createRandomTerm(ClientRequestIF request)
  {
    TermDTO term = new TermDTO(request);
    term.setTermId(TestFixture.getRandomTermId());
    term.setName("Test Term");
    term.setComment("Test Comment");
//    term.setObsolete(false);
    term.apply();

    return term;
  }

  public static Person createTestPerson(String username, String password, String rolename)
  {
    Term sex = TestFixture.createRandomTerm();

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(1983, 5, 11);

    // Create a test user and assign it to the entomology role
    Person person = new Person();
    person.setFirstName("Justin");
    person.setLastName("Smethie");
    person.setDateOfBirth(calendar.getTime());
    person.setSex(sex);
    person.apply();
    person.lock();

    // Create MDSS User
    MDSSUser user = new MDSSUser();
    user.setPerson(person);
    user.setUsername(username);
    user.setPassword(password);
    user.apply();

    // Assign the MDSS User to the Entomologist role
    RoleDAO role = RoleDAO.findRole(rolename).getBusinessDAO();
    role.assignMember(UserDAO.get(user.getId()));

    person.setUserDelegate(user);
    person.apply();

    return person;
  }

  public static StockStaffDTO createTestStaff(ClientRequestIF request, TermDTO sex)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(1983, 5, 11);

    // Create a test user and assign it to the entomology role
    PersonDTO person = new PersonDTO(request);
    person.setFirstName("Justin");
    person.setLastName("Smethie");
    person.setDateOfBirth(calendar.getTime());
    person.setSex(sex);
    person.apply();

    // Create MDSS User
    StockStaffDTO staff = new StockStaffDTO(request);
    staff.setPerson(person);
    staff.apply();

    person.lock();
    person.setStockStaffDelegate(staff);
    person.apply();

    return staff;
  }

  public static void delete(Insecticide insecticide)
  {
    Term units = insecticide.getUnits();
    Term activeIngredient = insecticide.getActiveIngredient();

    insecticide.delete();

    units.delete();
    activeIngredient.delete();
  }

  public static void delete(InsecticideBrand insecticideBrand)
  {
    Term productName = insecticideBrand.getProductName();
    Term activeIngredient = insecticideBrand.getActiveIngredient();

    insecticideBrand.delete();

    productName.delete();
    activeIngredient.delete();
  }

  public static void delete(Person person)
  {
    Term sex = person.getSex();

    person.deleteDelegates();

    Person.get(person.getId()).delete();

    sex.delete();
  }

  public static void delete(PersonDTO person)
  {
    TermDTO sex = person.getSex();

    person.delete();

    sex.delete();
  }

  public static MosquitoCollection createMosquitoCollection(GeoEntity geoEntity, Term collectionMethod)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 2006);
    calendar.set(Calendar.DAY_OF_YEAR, 1);
    calendar.set(Calendar.MONTH, 1);

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(geoEntity);
    collection.setCollectionMethod(collectionMethod);
    collection.setCollectionDate(calendar.getTime());
    collection.apply();

    return collection;
  }

  public static PopulationData createPopulationData(GeoEntity entity, Integer year, Boolean estimated, Long population, Double rate)
  {
    PopulationData data = new PopulationData();
    data.setGeoEntity(entity);
    data.setYearOfData(year);
    data.setEstimated(estimated);

    if (population != null)
    {
      data.setPopulation(population);
    }

    if (rate != null)
    {
      data.setGrowthRate(rate);
    }

    data.apply();

    return data;
  }

  public static MalariaSeasonDTO createMalairaSeason(ClientRequestIF request)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(2003, 1, 1);

    Date startDate = calendar.getTime();

    calendar.clear();
    calendar.set(2003, 10, 1);

    Date endDate = calendar.getTime();

    MalariaSeasonDTO season = new MalariaSeasonDTO(request);
    season.setSeasonName(TestConstants.SEASON_NAME);
    season.setStartDate(startDate);
    season.setEndDate(endDate);
    season.apply();

    return season;
  }
  
  public static MosquitoCollectionViewDTO createCollection(ClientRequestIF request, TermDTO term, GeoEntityDTO entity)
  {
    MosquitoCollectionViewDTO view = new MosquitoCollectionViewDTO(request);
    view.setAbundance(true);
    view.setCollectionDate(new Date());
    view.setCollectionId(TestFixture.getRandomGeoId());
    view.setCollectionMethod(term);
    view.setGeoEntity(entity);
    view.addLifeStage(LifeStageDTO.EGG);
    
    return view;
  }
  
  public static SubCollectionViewDTO createSubCollection(ClientRequestIF request, TermDTO term)
  {
    SubCollectionViewDTO view = new SubCollectionViewDTO(request);
    view.setEggs(45);
    view.setIdentMethod(term);
    view.setTaxon(term);
    view.setSubCollectionId(TestFixture.getRandomGeoId());
    
    return view;
  }

  public static ImmatureCollectionView createImmatureCollection(GeoEntity entity, Term premiseType, Term taxon)
  {
    ImmatureCollectionView collection = new ImmatureCollectionView();
    collection.setGeoEntity(entity);
    collection.setStartDate(new Date());
    collection.setEndDate(new Date());
    collection.setCollectionId(TestFixture.getRandomTermId());
    collection.setPremiseType(premiseType);
    collection.setNumberExamined(5);
    collection.setPremiseSize(new BigDecimal(8));
    collection.setNumberInhabitants(9);
    collection.setTaxon(taxon);
    
    return collection;
  }

}
