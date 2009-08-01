package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.intervention.BloodslideResponse;
import dss.vector.solutions.intervention.FeverResponse;
import dss.vector.solutions.intervention.FeverTreatment;
import dss.vector.solutions.intervention.FeverTreatmentQuery;
import dss.vector.solutions.intervention.HumanSex;
import dss.vector.solutions.intervention.RDTResponse;
import dss.vector.solutions.intervention.RDTResult;
import dss.vector.solutions.intervention.monitor.Household;
import dss.vector.solutions.intervention.monitor.HouseholdNet;
import dss.vector.solutions.intervention.monitor.HouseholdQuery;
import dss.vector.solutions.intervention.monitor.Net;
import dss.vector.solutions.intervention.monitor.Person;
import dss.vector.solutions.intervention.monitor.SurveyPoint;
import dss.vector.solutions.intervention.monitor.Wall;
import dss.vector.solutions.intervention.monitor.WallQuery;
import dss.vector.solutions.intervention.monitor.WindowType;
import dss.vector.solutions.surveillance.TreatmentGrid;
import dss.vector.solutions.surveillance.TreatmentGridQuery;

public class SurveyExcelView extends SurveyExcelViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245785160306L;
  
  private List<Net> nets;
  private List<Integer> amounts;
  
  public SurveyExcelView()
  {
    super();
    nets = new LinkedList<Net>();
    amounts = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    Household house = getHousehold();
    
    HouseholdNet[] array = new HouseholdNet[nets.size()];
    for (int i=0; i<array.length; i++)
    {
      array[i] = new HouseholdNet(house, nets.get(i));
      array[i].setAmount(amounts.get(i));
    }
    
    house.applyAll(array);
    
    // Person stuff
    Person person = new Person();
    person.setHousehold(house);
    person.setPersonId(this.getPersonId());
    person.setDob(this.getDob());
    person.setPregnant(this.getPregnant());
    person.setSleptUnderNet(this.getSleptUnderNet());
    person.setHaemoglobinMeasured(this.getHaemoglobinMeasured());
    person.setHaemoglobin(this.getHaemoglobin());
    person.setAnaemiaTreatment(getTreatment(this.getAnaemiaTreatment()));
    person.setIron(this.getIron());
    
    person.addSex(getHumanSexByLabel(this.getSex()));
    person.addPerformedRDT(getRDTResponseByLabel(this.getPerformedRDT()));
    person.addBloodslide(getBloodsideByLabel(this.getBloodslide()));
    
    // This block mirrors the check boxes for RDT Results
    if (this.getMalariaePositive() != null && this.getMalariaePositive())
      person.addRDTResult(RDTResult.MALARIAE_POSITIVE);
    if (this.getMixedPositive() != null && this.getMixedPositive())
      person.addRDTResult(RDTResult.MIXED_POSITIVE);
    if (this.getNegative() != null && this.getNegative())
      person.addRDTResult(RDTResult.NEGATIVE);
    if (this.getNotValid() != null && this.getNotValid())
      person.addRDTResult(RDTResult.NOT_VALID);
    if (this.getOvalePositive() != null && this.getOvalePositive())
      person.addRDTResult(RDTResult.OVALE_POSITIVE);
    if (this.getPfPositive() != null && this.getPfPositive())
      person.addRDTResult(RDTResult.PF_POSITIVE);
    if (this.getVivaxPositive() != null && this.getVivaxPositive())
      person.addRDTResult(RDTResult.VIVAX_POSITIVE);
    
    person.setRdtTreatment(getTreatment(this.getRdtTreatment()));    
    person.addFever(getFeverByLabel(this.getFever()));
    
    FeverTreatmentQuery ftq = new FeverTreatmentQuery(new QueryFactory());
    ftq.WHERE(ftq.getDisplayLabel().currentLocale().EQ(this.getFeverTreatement()));
    OIterator<? extends FeverTreatment> iterator = ftq.getIterator();
    if (iterator.hasNext())
    {
      person.setFeverTreatment(iterator.next());
    }
    iterator.close();
    
    person.addMalaria(getFeverByLabel(this.getMalaria()));
    person.setMalariaTreatment(getTreatment(this.getMalariaTreatment()));
    person.addPayment(getFeverByLabel(this.getPayment()));
    person.apply();
  }

  private TreatmentGrid getTreatment(String label)
  {
    TreatmentGrid treatmentGrid = null;
    TreatmentGridQuery tgq = new TreatmentGridQuery(new QueryFactory());
    tgq.WHERE(tgq.getDisplayLabel().currentLocale().EQ(label));
    
    OIterator<? extends TreatmentGrid> iterator = tgq.getIterator();
    if (iterator.hasNext())
    {
      treatmentGrid = iterator.next();
    }
    iterator.close();
    
    return treatmentGrid;
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
    GeoHierarchy sentinelSite = GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS);
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, sentinelSite);
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
  private Household getHousehold()
  {
    Household house;
    
    String name = this.getHouseholdName();
    HouseholdQuery query = new HouseholdQuery(new QueryFactory());
    query.WHERE(query.getHouseholdName().EQ(name));
    OIterator<? extends Household> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      house = iterator.next();
      house.lock();
    }
    else
    {
      house = new Household();
    }
    iterator.close();
    
    house.setSurveyPoint(this.getSurveyPoint());
    house.setHasWindows(this.getHasWindows());
    house.setHouseholdName(this.getHouseholdName());
    house.setLastSprayed(this.getLastSprayed());
    house.setNets(this.getNets());
    house.setNetsUsed(this.getNetsUsed());
    house.setPeople(this.getPeople());
    house.setRoof(this.getRoof());
    house.setRoofInfo(this.getRoofInfo());
    house.setRooms(this.getRooms());
    house.setSleptUnderNets(this.getSleptUnderNets());
    house.setUrban(this.getUrban());

    WindowType windowType = getWindowTypeByLabel(this.getWindowType());
    
    if(windowType != null)
    {
      house.addWindowType(windowType);
    }
    
    WallQuery wallQuery = new WallQuery(new QueryFactory());
    wallQuery.WHERE(wallQuery.getDisplayLabel().currentLocale().EQ(this.getWallName()));
    OIterator<? extends Wall> wallIterator = wallQuery.getIterator();
    Wall wall = null;
    if (wallIterator.hasNext())
    {
      wall = wallIterator.next();
    }
    wallIterator.close();
    
    house.setWall(wall);
    house.setWallInfo(this.getWallInfo());
    return house;
  }
  
  public void addNets(Net net, int count)
  {
    nets.add(net);
    amounts.add(count);
  }
  
  public static WindowType getWindowTypeByLabel(String label)
  {
    if(label == null || label == "")
    {
      return null;
    }
    
    for (WindowType e : WindowType.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + WindowType.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(WindowType.CLASS));
  }
  
  public static HumanSex getHumanSexByLabel(String label)
  {
    if(label == null || label == "")
    {
      return null;
    }

    for (HumanSex e : HumanSex.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + HumanSex.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(HumanSex.CLASS));
  }

  private RDTResponse getRDTResponseByLabel(String label)
  {
    for (RDTResponse e : RDTResponse.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }

  private BloodslideResponse getBloodsideByLabel(String label)
  {
    for (BloodslideResponse e : BloodslideResponse.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }

  private FeverResponse getFeverByLabel(String label)
  {
    for (FeverResponse e : FeverResponse.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }
}
