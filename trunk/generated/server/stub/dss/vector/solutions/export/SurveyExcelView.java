package dss.vector.solutions.export;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;
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
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.GeoEntitySearcher;
import dss.vector.solutions.util.SearchableHierarchy;

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
    person.addSex(getHumanSexByLabel(this.getSex()));
    person.setPregnant(this.getPregnant());
    person.setSleptUnderNet(this.getSleptUnderNet());
    person.setHaemoglobinMeasured(this.getHaemoglobinMeasured());
    person.setHaemoglobin(this.getHaemoglobin());
    
    person.setAnaemiaTreatment(getTreatment(this.getAnaemiaTreatment()));
    
    person.setIron(this.getIron());
    person.addPerformedRDT(getRDTResponseByLabel(this.getPerformedRDT()));
    person.addBloodslide(getBloodsideByLabel(this.getBloodslide()));
    
    // This block mirrors the check boxes for RDT Results
    if (this.getMalariaePositive())
      person.addRDTResult(RDTResult.MALARIAE_POSITIVE);
    if (this.getMixedPositive())
      person.addRDTResult(RDTResult.MIXED_POSITIVE);
    if (this.getNegative())
      person.addRDTResult(RDTResult.NEGATIVE);
    if (this.getNotValid())
      person.addRDTResult(RDTResult.NOT_VALID);
    if (this.getOvalePositive())
      person.addRDTResult(RDTResult.OVALE_POSITIVE);
    if (this.getPfPositive())
      person.addRDTResult(RDTResult.PF_POSITIVE);
    if (this.getVivaxPositive())
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
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    Map<String, String> map = new HashMap<String, String>();
    List<SearchableHierarchy> hierarchy = GeoColumnListener.getSentinelSiteHierarchy();
    List<MdAttributeDAOIF> attributes = SurveyExcelView.getGeoEntityAttributes();

    int size = Math.min(hierarchy.size(), attributes.size());

    for (int i = 0; i < size; i++)
    {
      String key = attributes.get(i).getId();
      String displayLabel = hierarchy.get(i).getDisplayLabel();

      map.put(key, displayLabel);
    }

    exporter.addListener(new GeoColumnListener(map));
    exporter.addListener(new DynamicNetListener());
  }
  
  private static List<MdAttributeDAOIF> getGeoEntityAttributes()
  {
    List<MdAttributeDAOIF> list = new LinkedList<MdAttributeDAOIF>();
    list.add(getGeoEntity_0Md());
    list.add(getGeoEntity_01Md());
    list.add(getGeoEntity_02Md());
    list.add(getGeoEntity_03Md());
    list.add(getGeoEntity_04Md());
    list.add(getGeoEntity_05Md());
    list.add(getGeoEntity_06Md());
    list.add(getGeoEntity_07Md());
    list.add(getGeoEntity_08Md());
    list.add(getGeoEntity_09Md());
    list.add(getGeoEntity_10Md());
    
    return list;
  }
  
  public SurveyPoint getSurveyPoint()
  {
    GeoEntitySearcher searcher = new GeoEntitySearcher(GeoColumnListener.getSentinelSiteHierarchy());
    
    List<String> geoEntityNames = new LinkedList<String>();
    geoEntityNames.add(this.getGeoEntity_0());
    geoEntityNames.add(this.getGeoEntity_01());
    geoEntityNames.add(this.getGeoEntity_02());
    geoEntityNames.add(this.getGeoEntity_03());
    geoEntityNames.add(this.getGeoEntity_04());
    geoEntityNames.add(this.getGeoEntity_05());
    geoEntityNames.add(this.getGeoEntity_06());
    geoEntityNames.add(this.getGeoEntity_07()); 
    geoEntityNames.add(this.getGeoEntity_08());
    geoEntityNames.add(this.getGeoEntity_09());
    geoEntityNames.add(this.getGeoEntity_10());
    
    GeoEntity entity = searcher.getGeoEntity(geoEntityNames);
    return SurveyPoint.searchByGeoEntityAndDate(entity, this.getSurveyDate());
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
    house.addWindowType(getWindowTypeByLabel(this.getWindowType()));
    
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
    for (WindowType e : WindowType.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }
  
  public static HumanSex getHumanSexByLabel(String label)
  {
    for (HumanSex e : HumanSex.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
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
