package dss.vector.solutions.ontology;

import java.math.BigDecimal;
import java.util.Calendar;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.irs.AreaStandards;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifier;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.irs.InsecticideNozzle;
import dss.vector.solutions.irs.Nozzle;
import dss.vector.solutions.irs.TargetUnit;

public class PostOntologySetup
{

  /**
   * @param args
   */
	@Request
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
    person.setFirstName("DDMS");
    person.setLastName("User");
    person.setSex(Term.getByTermId(UNKNOWN));
    person.apply();

    MDSSUser user = new MDSSUser();
    user.setUsername("ddms");
    user.setPassword("ddms");
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
    final String K_OTHERINE_WG_250 = "MDSS:0000476";
    final String DDT_WP_75 = "MDSS:0000477";
    final Disease DEFAULT_DISEASE = Disease.getMalaria();

    InsecticideBrand deltamethrin = new InsecticideBrand();
    deltamethrin.setProductName(Term.getByTermId(K_OTHERINE_WG_250));
    deltamethrin.addInsecticideUse(InsecticideBrandUse.IRS);
    deltamethrin.setDisease(DEFAULT_DISEASE);
    deltamethrin.setActiveIngredient(Term.getByTermId(DELTAMETHRIN));
    deltamethrin.setConcentrationQuantifier(new BigDecimal("25"));
    deltamethrin.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
    deltamethrin.setUnitQuantifier(new BigDecimal(20.00));
    deltamethrin.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
    deltamethrin.setUnitsPerApplication(1);
    deltamethrin.setEnabled(true);
    deltamethrin.apply();

    InsecticideBrand ddt = new InsecticideBrand();
    ddt.setProductName(Term.getByTermId(DDT_WP_75));
    ddt.addInsecticideUse(InsecticideBrandUse.IRS);
    ddt.setDisease(DEFAULT_DISEASE);
    ddt.setActiveIngredient(Term.getByTermId(DDT));
    ddt.setConcentrationQuantifier(new BigDecimal("75"));
    ddt.addConcentrationQualifier(InsecticideBrandConcentrationQualifier.PERCENT);
    ddt.setUnitQuantifier(new BigDecimal(670.00));
    ddt.addUnitQualifier(InsecticideBrandUnitQualifier.GRAMS);
    ddt.setUnitsPerApplication(1);
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
