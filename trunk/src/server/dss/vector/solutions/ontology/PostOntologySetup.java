package dss.vector.solutions.ontology;

import java.math.BigDecimal;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;

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

    setupApplicationRate();
  }

  @Transaction
  private static void setupApplicationRate()
  {
    String DELTAMETHRIN = "MIRO:10000133";
    String DDT = "MIRO:10000157";
    
    InsecticideBrand deltamethrin = new InsecticideBrand();
    deltamethrin.setActiveIngredient(Term.getByTermId(DELTAMETHRIN));
    deltamethrin.setBrandName("K-othrine WG 250");
    deltamethrin.setAmount(25);
    deltamethrin.setWeight(new BigDecimal(20.00));
    deltamethrin.setSachetsPerRefill(1);
    deltamethrin.setEnabled(true);
    deltamethrin.apply();
    
    InsecticideBrand ddt = new InsecticideBrand();
    ddt.setActiveIngredient(Term.getByTermId(DDT));
    ddt.setBrandName("DDT WP 75");
    ddt.setAmount(75);
    ddt.setWeight(new BigDecimal(670.00));
    ddt.setSachetsPerRefill(1);
    ddt.setEnabled(true);
    ddt.apply();
    
    Nozzle n8001E = new Nozzle();
    n8001E.setDisplayLabel("8001 E");
    n8001E.setRatio(new BigDecimal(2.00));
    n8001E.setEnabled(true);
    n8001E.apply();
    
    Nozzle n8002E = new Nozzle();
    n8002E.setDisplayLabel("8002 E");
    n8002E.setRatio(new BigDecimal(1.00));
    n8002E.setEnabled(true);
    n8002E.apply();
    
    InsecticideNozzle configuration = new InsecticideNozzle(deltamethrin, n8001E);
    configuration.setEnabled(true);
    configuration.apply();

    InsecticideNozzle configuration2 = new InsecticideNozzle(ddt, n8002E);
    configuration2.setEnabled(true);
    configuration2.apply();
    
    AreaStandards standards = new AreaStandards();
    standards.setUnitNozzleAreaCoverage(250.0F);
    standards.setRoom(25F);
    standards.setStructureArea(100F);
    standards.setHousehold(300F);
    standards.addTargetUnit(TargetUnit.HOUSEHOLD);
    standards.apply();    
  }

}
