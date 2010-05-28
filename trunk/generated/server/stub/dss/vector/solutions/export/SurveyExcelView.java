package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.MonthOfYear;
import dss.vector.solutions.RefusedResponse;
import dss.vector.solutions.Response;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.intervention.monitor.Household;
import dss.vector.solutions.intervention.monitor.HouseholdQuery;
import dss.vector.solutions.intervention.monitor.HouseholdView;
import dss.vector.solutions.intervention.monitor.ITNInstance;
import dss.vector.solutions.intervention.monitor.ITNInstanceQuery;
import dss.vector.solutions.intervention.monitor.ITNInstanceView;
import dss.vector.solutions.intervention.monitor.SurveyPoint;
import dss.vector.solutions.intervention.monitor.SurveyedPerson;
import dss.vector.solutions.intervention.monitor.SurveyedPersonQuery;
import dss.vector.solutions.intervention.monitor.SurveyedPersonView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class SurveyExcelView extends SurveyExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245785160306L;
  
  private List<Term> locations;
  private List<Term> treatments;
  
  public SurveyExcelView()
  {
    super();
    locations = new LinkedList<Term>();
    treatments = new LinkedList<Term>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    HouseholdView house = getHousehold();
    
    applyItnInstance(house);
    
    // SurveyedPerson stuff
    applyPerson(house);
  }
  
  private void applyItnInstance(HouseholdView house)
  {
    if (getNetById(this.getNetId())!=null)
    {
      return;
    }
    
    ITNInstanceView itn = new ITNInstanceView();
    itn.setNetId(this.getNetId());
    itn.setHousehold(Household.get(house.getConcreteId()));
    itn.setNetBrand(Term.validateByDisplayLabel(this.getNetBrand(), ITNInstanceView.getNetBrandMd()));
    itn.addMonthReceived(getMonthOfYearByLabel(this.getMonthReceived()));
    itn.setYearReceived(this.getYearReceived());
    itn.setObtained(Term.validateByDisplayLabel(this.getObtained(), ITNInstanceView.getObtainedMd()));
    itn.setPrice(this.getPrice());
    itn.setRetreated(this.getRetreated());
    itn.addMonthRetreated(getMonthOfYearByLabel(this.getMonthRetreated()));
    itn.setYearRetreated(this.getYearRetreated());
    itn.setDamaged(Term.validateByDisplayLabel(this.getDamaged(), ITNInstanceView.getDamagedMd()));
    itn.setHanging(Term.validateByDisplayLabel(this.getHanging(), ITNInstanceView.getHangingMd()));
    itn.setNotUsedForSleeping(this.getNotUsedForSleeping());
    itn.setPurpose(Term.validateByDisplayLabel(this.getPurpose(), ITNInstanceView.getPurposeMd()));
    itn.setPurposeComments(this.getPurposeComments());
    itn.addWashed(getResponseByLabel(this.getWashed()));
    itn.setWashFrequency(this.getWashFrequency());
    itn.setWashPeriod(Term.validateByDisplayLabel(this.getWashPeriod(), ITNInstanceView.getWashPeriodMd()));
    itn.apply();
  }

  private ITNInstance getNetById(String netId)
  {
    ITNInstanceQuery query = new ITNInstanceQuery(new QueryFactory());
    query.WHERE(query.getNetId().EQ(netId));
    OIterator<? extends ITNInstance> iterator = query.getIterator();
    
    ITNInstance instance = null;
    if (iterator.hasNext())
    {
      instance = iterator.next();
      iterator.close();
    }
    return instance;
  }

  private void applyPerson(HouseholdView house)
  {
    SurveyedPersonQuery query = new SurveyedPersonQuery(new QueryFactory());
    query.WHERE(query.getPersonId().EQ(this.getPersonId()));
    OIterator<? extends SurveyedPerson> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      iterator.close();
      return;
    }
    
    SurveyedPersonView person = new SurveyedPersonView();
    person.setHousehold(Household.get(house.getConcreteId()));
    person.setPersonId(this.getPersonId());
    person.setHeadOfHousehold(Term.validateByDisplayLabel(this.getHeadOfHousehold(), SurveyedPersonView.getHeadOfHouseholdMd()));
    person.setDob(this.getDob());
    person.setAge(this.getAge());
    person.setSex(Term.validateByDisplayLabel(this.getSex(), SurveyedPersonView.getSexMd()));
    person.setPregnant(this.getPregnant());
    person.setImmuneCompromised(Term.validateByDisplayLabel(this.getImmuneCompromised(), SurveyedPersonView.getImmuneCompromisedMd()));
    person.setSleptUnderNet(getNetById(this.getSleptUnderNetId()));
    person.addHaemoglobinMeasured(getRefusedResponseByLabel(this.getHaemoglobinMeasured()));
    person.setHaemoglobin(this.getHaemoglobin());
    person.setAnaemiaTreatment(Term.validateByDisplayLabel(this.getAnaemiaTreatment(), SurveyedPersonView.getAnaemiaTreatmentMd()));
    person.setIron(this.getIron());
    person.addPerformedRDT(getRefusedResponseByLabel(this.getPerformedRDT()));
    person.setRdtResult(this.getRdtResult());
    person.setRdtDetail(Term.validateByDisplayLabel(this.getRdtDetail(), SurveyedPersonView.getRdtDetailMd()));
    person.setRdtTreatment(Term.validateByDisplayLabel(this.getRdtTreatment(), SurveyedPersonView.getRdtTreatmentMd()));
    person.setPerformedBloodslide(this.getPerformedBloodslide());
    person.setBloodslideReason(Term.validateByDisplayLabel(this.getBloodslideReason(), SurveyedPersonView.getBloodslideReasonMd()));
    person.setBloodslideResult(this.getBloodslideResult());
    person.setBloodslideDetail(Term.validateByDisplayLabel(this.getBloodslideDetail(), SurveyedPersonView.getBloodslideDetailMd()));
    person.addFever(getResponseByLabel(this.getFever()));
    person.addMalaria(getResponseByLabel(this.getMalaria()));
    person.setMalariaConformationTechnique(Term.validateByDisplayLabel(this.getMalariaConformationTechnique(), SurveyedPersonView.getMalariaConformationTechniqueMd()));
    person.setPayment(Term.validateByDisplayLabel(this.getPayment(), SurveyedPersonView.getPaymentMd()));
    
    person.applyAll(locations.toArray(new Term[locations.size()]), treatments.toArray(new Term[treatments.size()]));
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
      house.setWall(Term.validateByDisplayLabel(this.getWallSurface(), HouseholdView.getWallMd()));
      house.setWallInfo(this.getWallInfo());
      house.setRoof(Term.validateByDisplayLabel(this.getRoofSurface(), HouseholdView.getRoofMd()));
      house.setRoofInfo(this.getRoofInfo());
      house.setHasWindows(this.getHasWindows());
      house.setWindowType(Term.validateByDisplayLabel(this.getWindowType(), HouseholdView.getWindowTypeMd()));
      house.setRooms(this.getRooms());
      house.addHasBeenSprayed(getResponseByLabel(this.getHasBeenSprayed()));
      house.setLastSprayed(this.getLastSprayed());
      house.setNets(this.getNets());      
      house.apply();
    }
    iterator.close();
    
    return house;
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(SURVEYDATE);
    list.add(HOUSEHOLDNAME);
    list.add(URBAN);
    list.add(PEOPLE);
    list.add(WALLSURFACE);
    list.add(WALLINFO);
    list.add(ROOFSURFACE);
    list.add(ROOFINFO);
    list.add(HASWINDOWS);
    list.add(WINDOWTYPE);
    list.add(ROOMS);
    list.add(HASBEENSPRAYED);
    list.add(LASTSPRAYED);
    list.add(NETS);
    list.add(PERSONID);
    list.add(HEADOFHOUSEHOLD);
    list.add(DOB);
    list.add(AGE);
    list.add(SEX);
    list.add(PREGNANT);
    list.add(IMMUNECOMPROMISED);
    list.add(SLEPTUNDERNETID);
    list.add(HAEMOGLOBINMEASURED);
    list.add(HAEMOGLOBIN);
    list.add(ANAEMIATREATMENT);
    list.add(IRON);
    list.add(PERFORMEDRDT);
    list.add(RDTRESULT);
    list.add(RDTDETAIL);
    list.add(RDTTREATMENT);
    list.add(PERFORMEDBLOODSLIDE);
    list.add(BLOODSLIDEREASON);
    list.add(BLOODSLIDERESULT);
    list.add(BLOODSLIDEDETAIL);
    list.add(FEVER);
    list.add(MALARIA);
    list.add(MALARIACONFORMATIONTECHNIQUE);
    list.add(PAYMENT);
    list.add(NETID);
    list.add(NETBRAND);
    list.add(MONTHRECEIVED);
    list.add(YEARRECEIVED);
    list.add(OBTAINED);
    list.add(PRICE);
    list.add(RETREATED);
    list.add(MONTHRETREATED);
    list.add(YEARRETREATED);
    list.add(DAMAGED);
    list.add(HANGING);
    list.add(NOTUSEDFORSLEEPING);
    list.add(PURPOSE);
    list.add(PURPOSECOMMENTS);
    list.add(WASHED);
    list.add(WASHFREQUENCY);
    list.add(WASHPERIOD);
    list.add(SLEPTUNDERNET);
    return list;
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(new SurveyExcelListener());
    context.addListener(createExcelGeoListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(new SurveyExcelListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
  
  public void addLocation(Term location)
  {
    locations.add(location);
  }
  
  public void addTreatment(Term treatment)
  {
    treatments.add(treatment);
  }

  private MonthOfYear getMonthOfYearByLabel(String label)
  {
    for (MonthOfYear e : MonthOfYear.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    return null;
  }
  
  private Response getResponseByLabel(String label)
  {
    for (Response e : Response.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    return null;
  }
  
  private RefusedResponse getRefusedResponseByLabel(String label)
  {
    for (RefusedResponse e : RefusedResponse.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    return null;
  }
}
