package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.intervention.monitor.Household;
import dss.vector.solutions.intervention.monitor.HouseholdQuery;
import dss.vector.solutions.intervention.monitor.HouseholdView;
import dss.vector.solutions.intervention.monitor.SurveyedPerson;
import dss.vector.solutions.intervention.monitor.SurveyPoint;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class SurveyExcelView extends SurveyExcelViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245785160306L;
  
  private List<Term> nets;
  private List<Integer> amounts;
  
  public SurveyExcelView()
  {
    super();
    nets = new LinkedList<Term>();
    amounts = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    HouseholdView house = getHousehold();
    
    // SurveyedPerson stuff
    SurveyedPerson person = new SurveyedPerson();
    person.setHousehold(Household.get(house.getConcreteId()));
//    person.setPersonId(this.getPersonId());
//    person.setDob(this.getDob());
    person.setSex(Term.validateByDisplayLabel(this.getSex(), SurveyedPerson.getSexMd()));
//    person.setPregnant(this.getPregnant());
//    person.setSleptUnderNet(this.getSleptUnderNet());
//    person.setHaemoglobinMeasured(this.getHaemoglobinMeasured());
//    person.setHaemoglobin(this.getHaemoglobin());
//    person.setAnaemiaTreatment(Term.validateByDisplayLabel(this.getAnaemiaTreatment(), SurveyedPerson.getAnaemiaTreatmentMd()));
//    person.setIron(this.getIron());
//    person.setPerformedRDT(Term.validateByDisplayLabel(this.getPerformedRDT(), SurveyedPerson.getPerformedRDTMd()));
//    person.setBloodslide(Term.validateByDisplayLabel(this.getBloodslide(), SurveyedPerson.getBloodslideMd()));
    person.setRdtTreatment(Term.validateByDisplayLabel(this.getRdtTreatment(), SurveyedPerson.getRdtTreatmentMd()));
//    person.setFever(Term.validateByDisplayLabel(this.getFever(), SurveyedPerson.getFeverMd()));
//    person.setFeverTreatment(Term.validateByDisplayLabel(this.getFeverTreatement(), SurveyedPerson.getFeverTreatmentMd()));
//    person.setMalaria(Term.validateByDisplayLabel(this.getMalaria(), SurveyedPerson.getMalariaMd()));
//    person.setMalariaTreatment(Term.validateByDisplayLabel(this.getMalariaTreatment(), SurveyedPerson.getMalariaTreatmentMd()));
    person.setPayment(Term.validateByDisplayLabel(this.getPayment(), SurveyedPerson.getPaymentMd()));
    person.apply();
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(new DynamicNetListener());
    importer.addListener(createExcelGeoListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(new DynamicNetListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
  
  public SurveyPoint getSurveyPoint()
  {
    SurveyPoint surveyPoint = SurveyPoint.searchByGeoEntityAndDate(this.getGeoEntity(), this.getSurveyDate());
    
    
    if(surveyPoint == null)
    {
      // The survey point is null so we need to create it
      surveyPoint = new SurveyPoint();
      surveyPoint.setGeoEntity(this.getGeoEntity());
      surveyPoint.setSurveyDate(this.getSurveyDate());
      surveyPoint.apply();
    }
    
    return surveyPoint;
  }

  /**
   * Gets the household by it's unique name. If the name is not found, a new one
   * is created (but not applied).
   * 
   * @return
   */
  private HouseholdView getHousehold()
  {
    HouseholdView house;
    
    String name = this.getHouseholdName();
    HouseholdQuery query = new HouseholdQuery(new QueryFactory());
    query.WHERE(query.getHouseholdName().EQ(name));
    OIterator<? extends Household> iterator = query.getIterator();
    
    if (iterator.hasNext())
    {
      Household concrete = iterator.next();
      house = concrete.lockView();
    }
    else
    {
      house = new HouseholdView();
      house.setHouseholdName(name);
      house.setSurveyPoint(this.getSurveyPoint());
      house.setUrban(this.getUrban());
      house.setPeople(this.getPeople());
      house.setWall(Term.validateByDisplayLabel(this.getWallSurface(), Household.getWallMd()));
      house.setWallInfo(this.getWallInfo());
      house.setRoof(Term.validateByDisplayLabel(this.getRoofSurface(), Household.getRoofMd()));
      house.setRoofInfo(this.getRoofInfo());
      house.setHasWindows(this.getHasWindows());
      house.setWindowType(Term.validateByDisplayLabel(this.getWindowType(), Household.getWindowTypeMd()));
      house.setRooms(this.getRooms());
      house.setLastSprayed(this.getLastSprayed());
      house.setNets(this.getNets());      
      house.apply();
    }
    iterator.close();
    
    return house;
  }
  
  public void addNets(Term net, int count)
  {
    nets.add(net);
    amounts.add(count);
  }
}
