package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.AbstractSpray;
import dss.vector.solutions.irs.HouseholdSprayStatusView;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.RequiredGeoIdProblem;
import dss.vector.solutions.irs.SprayOperator;
import dss.vector.solutions.util.HierarchyBuilder;

public class OperatorSprayExcelView extends OperatorSprayExcelViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246588713740L;

  public OperatorSprayExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    this.applyNoPersist();
    
    GeoEntity entity = getGeoEntity();
    
    if (entity == null)
    {
      RequiredGeoIdProblem p = new RequiredGeoIdProblem();
      p.throwIt();
    }

    SprayOperator operator = SprayOperator.getByOperatorId(this.getOperatorId());
    String operatorId = "";

    if (operator != null)
    {
      operatorId = operator.getId();
    }

    OperatorSprayView osv = OperatorSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(),
        getSprayMethodByLabel(this.getSprayMethod()), InsecticideBrand.validateByName(this.getBrandName()),
        operatorId);

    // Only create values if one already exists do not update
    if (osv.getSprayId() == null || osv.getSprayId().equals(""))
    {
      super.populate(osv);
      osv.setSprayOperator(operator);

      osv.apply();
    }

    // Popluate the Household Spray Status data
    if (this.getHouseholdId() != null && !this.getHouseholdId().equals(""))
    {
      HouseholdSprayStatusView view = new HouseholdSprayStatusView();
      view.setHouseholdId(this.getHouseholdId());
      view.setStructureId(this.getStructureId());
      view.setSpray(AbstractSpray.get(osv.getSprayId()));
      view.setHouseholds(this.getHouseholds());
      view.setStructures(this.getStructures());
      view.setSprayedHouseholds(this.getSprayedHouseholds());
      view.setSprayedStructures(this.getSprayedStructures());
      view.setPrevSprayedHouseholds(this.getPrevSprayedHouseholds());
      view.setPrevSprayedStructures(this.getPrevSprayedStructures());
      view.setRooms(this.getRooms());
      view.setSprayedRooms(this.getSprayedRooms());
      view.setPeople(this.getPeople());
      view.setBedNets(this.getBedNets());
      view.setRoomsWithBedNets(this.getRoomsWithBedNets());
      view.setLocked(this.getLocked());
      view.setOther(this.getOther());
      view.setRefused(this.getRefused());
      view.apply();
    }
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(BRANDNAME);
    list.add(SPRAYDATE);
    list.add(SPRAYMETHOD);
    list.add(SPRAYTEAM);
    list.add(OPERATORID);
    list.add(LEADERID);
    list.add(SURFACETYPE);
    list.add(TEAMSPRAYWEEK);
    list.add(TARGET);
    list.add(OPERATORSPRAYWEEK);
    list.add(RECEIVED);
    list.add(REFILLS);
    list.add(RETURNED);
    list.add(USED);
    list.add(HOUSEHOLDID);
    list.add(STRUCTUREID);
    list.add(HOUSEHOLDS);
    list.add(STRUCTURES);
    list.add(SPRAYEDHOUSEHOLDS);
    list.add(SPRAYEDSTRUCTURES);
    list.add(PREVSPRAYEDHOUSEHOLDS);
    list.add(PREVSPRAYEDSTRUCTURES);
    list.add(ROOMS);
    list.add(SPRAYEDROOMS);
    list.add(PEOPLE);
    list.add(BEDNETS);
    list.add(ROOMSWITHBEDNETS);
    list.add(LOCKED);
    list.add(REFUSED);
    list.add(OTHER);
    return list;
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllSprayTargets())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
}
