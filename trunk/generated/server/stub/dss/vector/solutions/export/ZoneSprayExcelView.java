package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.RequiredGeoIdProblem;
import dss.vector.solutions.irs.SprayOperator;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamSprayStatusView;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.irs.ZoneSprayView;
import dss.vector.solutions.util.HierarchyBuilder;

public class ZoneSprayExcelView extends ZoneSprayExcelViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246603807084L;

  public ZoneSprayExcelView()
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

    ZoneSprayView zsv = ZoneSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(),
        getSprayMethodByLabel(this.getSprayMethod()), InsecticideBrand.validateByName(this
            .getBrandName()));

    if (zsv.getSprayId() == null || zsv.getSprayId().equals(""))
    {
      super.populate(zsv);
      zsv.setSprayWeek(this.getSprayWeek());
      zsv.setSupervisor(Supervisor.getByName(this.getSupervisorName(), this.getSupervisorSurname()));
      
      zsv.setTarget(this.getTarget());

      zsv.apply();
    }

    if (this.getSprayTeam() != null && !this.getSprayTeam().equals(""))
    {
      ZoneSpray zoneSpray = ZoneSpray.get(zsv.getSprayId());

      TeamSprayStatusView view = new TeamSprayStatusView();
      view.setTarget(this.getTarget());
      view.setSprayTeam(SprayTeam.getByTeamId(this.getSprayTeam()));
      view.setTeamLeader(SprayOperator.getByOperatorId(this.getLeaderId()));
      view.setTeamSprayWeek(this.getTeamSprayWeek());
      view.setReceived(this.getTeamReceived());
      view.setRefills(this.getTeamRefills());
      view.setReturned(this.getTeamReturned());
      view.setUsed(this.getTeamUsed());
      view.setSprayData(zoneSpray.getSprayData());
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
