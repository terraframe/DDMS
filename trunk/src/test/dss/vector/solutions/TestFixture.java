package dss.vector.solutions;

import java.util.Calendar;
import java.util.Date;

import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.UserDAO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.geo.generated.SentinelSiteDTO;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.geo.generated.WaterBody;
import dss.vector.solutions.ontology.MO;
import dss.vector.solutions.ontology.MODTO;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermDTO;

public class TestFixture
{
  private static String getRandomTermId()
  {
    String geoId = new Long(new Date().getTime()).toString();
    return geoId;
  }
  
  public static Term createRandomTerm()
  {
    MO term = new MO();
    term.setTermId(TestFixture.getRandomTermId());
    term.setTermName("Test Term");
    term.setTermComment("Test Comment");
    term.setObsolete(false);
    term.apply();

    return term;
  }

  public static Insecticide createInsecticide(Term activeIngredient)
  {
    Insecticide insecticide = new Insecticide();
    insecticide.setActiveIngredient(activeIngredient);
    insecticide.setAmount(new Double(40.0));
    insecticide.addUnits(Unit.PERCENT);
    insecticide.apply();

    return insecticide;
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

  public static WaterBody createRandomPermanentWaterBody()
  {
    WaterBody body = new WaterBody();
    body.setGeoId(TestFixture.getRandomGeoId());
    body.setEntityName("Test Site");
    body.apply();

    return body;
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


  public static TermDTO createRandomTerm(ClientRequestIF request)
  {
    MODTO term = new MODTO(request);
    term.setTermId(TestFixture.getRandomTermId());
    term.setTermName("Test Term");
    term.setTermComment("Test Comment");
    term.setObsolete(false);
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
  
  public static void delete(Person person)
  {
    Term sex = person.getSex();
    
    person.deleteDelegates();
    
    Person.get(person.getId()).delete();
    
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
    collection.setDateCollected(calendar.getTime());
    collection.apply();
    
    return collection;
  }

  public static PopulationData createPopulationData(GeoEntity entity, Integer year, Boolean estimated, Long population, Float rate)
  {
    PopulationData data = new PopulationData();
    data.setGeoEntity(entity);
    data.setYearOfData(year);
    data.setEstimated(estimated);
    
    if(population != null)
    {
      data.setPopulation(population);
    }
    
    if(rate != null)
    {
     data.setGrowthRate(rate); 
    }
    
    data.apply();
    
    return data;
  }
}
