package dss.vector.solutions.ontology;

import java.math.BigDecimal;
import java.util.Calendar;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.StartSession;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.irs.AreaStandards;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideNozzle;
import dss.vector.solutions.irs.Nozzle;
import dss.vector.solutions.irs.TargetUnit;

public class PostOntologySetup
{

  /**
   * @param args
   */
  @StartSession
  public static void main(String[] args)  throws Exception
  {
    //Setup root values
    AttributeRootImporter.main(args);    

    setupMDSSUser();
    
    setupApplicationRate();
  }

  @Transaction
  private static void setupMDSSUser()
  {
    String UNKNOWN = "MDSS:0000320";
    
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 2009);
    calendar.set(Calendar.MONTH, 1);
    calendar.set(Calendar.DAY_OF_YEAR, 1);
    
    Person person = new Person();
    person.setDateOfBirth(calendar.getTime());
    person.setFirstName("MDSS");
    person.setLastName("User");
    person.setSex(Term.getByTermId(UNKNOWN));
    person.apply();
    
    MDSSUser user = new MDSSUser();
    user.setUsername("MDSS");
    user.setPassword("mdsstest2");
    user.setSessionLimit(25);
    user.setPerson(person);
    user.apply();

    RoleDAO.findRole(RoleDAOIF.ADMIN_ROLE).assignMember(UserDAO.get(user.getId()));
    
    person.setUserDelegate(user);
    person.apply();    
  }

  @Transaction
  private static void setupApplicationRate()
  {
    final String DELTAMETHRIN = "MIRO:10000133";
    final String DDT = "MIRO:10000157";
    final Disease DEFAULT_DISEASE = Disease.getMalaria();
    
    InsecticideBrand deltamethrin = new InsecticideBrand();
    deltamethrin.setDisease(DEFAULT_DISEASE);
    deltamethrin.setActiveIngredient(Term.getByTermId(DELTAMETHRIN));
    deltamethrin.setBrandName("K-othrine WG 250");
    deltamethrin.setAmount(25);
    deltamethrin.setWeight(new BigDecimal(20.00));
    deltamethrin.setSachetsPerRefill(1);
    deltamethrin.setEnabled(true);
    deltamethrin.apply();
    
    InsecticideBrand ddt = new InsecticideBrand();
    ddt.setDisease(DEFAULT_DISEASE);
    ddt.setActiveIngredient(Term.getByTermId(DDT));
    ddt.setBrandName("DDT WP 75");
    ddt.setAmount(75);
    ddt.setWeight(new BigDecimal(670.00));
    ddt.setSachetsPerRefill(1);
    ddt.setEnabled(true);
    ddt.apply();
    
    Nozzle n8001E = new Nozzle();
    n8001E.setDisease(DEFAULT_DISEASE);
    n8001E.setDisplayLabel("8001 E");
    n8001E.setRatio(new BigDecimal(2.00));
    n8001E.setEnabled(true);
    n8001E.apply();
    
    Nozzle n8002E = new Nozzle();
    n8002E.setDisease(DEFAULT_DISEASE);
    n8002E.setDisplayLabel("8002 E");
    n8002E.setRatio(new BigDecimal(1.00));
    n8002E.setEnabled(true);
    n8002E.apply();
    
    InsecticideNozzle configuration = new InsecticideNozzle(deltamethrin, n8001E);
    configuration.setDisease(DEFAULT_DISEASE);
    configuration.setEnabled(true);
    configuration.apply();

    InsecticideNozzle configuration2 = new InsecticideNozzle(ddt, n8002E);
    configuration2.setDisease(DEFAULT_DISEASE);
    configuration2.setEnabled(true);
    configuration2.apply();
    
    AreaStandards standards = new AreaStandards();
    standards.setDisease(DEFAULT_DISEASE);
    standards.setUnitNozzleAreaCoverage(250.0F);
    standards.setRoom(25F);
    standards.setStructureArea(100F);
    standards.setHousehold(300F);
    standards.addTargetUnit(TargetUnit.ROOM);
    standards.apply();    
  }

}
