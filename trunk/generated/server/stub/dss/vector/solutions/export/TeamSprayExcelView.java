package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.OperatorSprayStatusView;
import dss.vector.solutions.irs.RequiredGeoIdProblem;
import dss.vector.solutions.irs.SprayOperator;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.TeamSpray;
import dss.vector.solutions.irs.TeamSprayView;

public class TeamSprayExcelView extends TeamSprayExcelViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246603807054L;

  public TeamSprayExcelView()
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

    SprayTeam team = SprayTeam.getByTeamId(this.getSprayTeam());
    String teamId = "";

    if (team != null)
    {
      teamId = team.getId();
    }

    TeamSprayView tsv = TeamSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(),
        getSprayMethodByLabel(this.getSprayMethod()), InsecticideBrand.validateByName(this.getBrandName()),
        teamId);

    if (tsv.getSprayId() == null || tsv.getSprayId().equals(""))
    {
      super.populate(tsv);
      tsv.apply();
    }

    if (this.getOperatorId() != null && !this.getOperatorId().equals(""))
    {
      TeamSpray teamSpray = TeamSpray.get(tsv.getSprayId());

      OperatorSprayStatusView view = new OperatorSprayStatusView();
      view.setSprayOperator(SprayOperator.getByOperatorId(this.getOperatorId()));
      view.setOperatorSprayWeek(this.getOperatorSprayWeek());
      view.setReceived(this.getReceived());
      view.setRefills(this.getRefills());
      view.setReturned(this.getReturned());
      view.setUsed(this.getUsed());
      view.setSprayData(teamSpray.getSprayData());
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
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, GeoHierarchy.getAllSprayTargets());
  }

}
