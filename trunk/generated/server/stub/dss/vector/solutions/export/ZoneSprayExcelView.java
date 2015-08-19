package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.RequiredGeoIdProblem;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.irs.TeamSprayStatus;
import dss.vector.solutions.irs.TeamSprayStatusQuery;
import dss.vector.solutions.irs.TeamSprayStatusView;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.irs.ZoneSprayView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class ZoneSprayExcelView extends ZoneSprayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
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

    ZoneSprayView zsv = ZoneSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(), ExcelEnums.getSprayMethod(this.getSprayMethod()), InsecticideBrand.validateByName(this.getInsecticideTerm()));

    zsv.setSurfaceType(Term.validateByDisplayLabel(this.getSurfaceType(), OperatorSprayView.getSurfaceTypeMd()));
    zsv.setSupervisor(Supervisor.getByCodeAndName(this.getSupervisorCode(), this.getSupervisorName(), this.getSupervisorSurname()));

    zsv.apply();

    if (this.getSprayTeam() != null && !this.getSprayTeam().equals(""))
    {
      TeamSprayStatusView view = this.getSprayStatusView(zsv);
      view.setTeamLeader(this.getTeamLeader());
      view.setTarget(this.getTeamTarget());
      view.setReceived(this.getTeamReceived());
      view.setRefills(this.getTeamRefills());
      view.setReturned(this.getTeamReturned());
      view.setUsed(this.getTeamUsed());
      view.setHouseholds(this.getHouseholds());
      view.setStructures(this.getStructures());
      view.setSprayedHouseholds(this.getSprayedHouseholds());
      view.setSprayedStructures(this.getSprayedStructures());
      view.setPrevSprayedHouseholds(this.getPrevSprayedHouseholds());
      view.setPrevSprayedStructures(this.getPrevSprayedStructures());
      view.setRooms(this.getRooms());
      view.setVerandas(this.getVerandas());
      view.setCattleSheds(this.getCattleSheds());
      view.setSprayedRooms(this.getSprayedRooms());
      view.setVerandasSprayed(this.getVerandasSprayed());
      view.setCattleShedsSprayed(this.getCattleShedsSprayed());
      view.setPeople(this.getPeople());
      view.setBedNets(this.getBedNets());
      view.setRoomsWithBedNets(this.getRoomsWithBedNets());
      view.setLocked(this.getLocked());
      view.setVerandasLocked(this.getVerandasLocked());
      view.setCattleShedsLocked(this.getCattleShedsLocked());
      view.setOther(this.getOther());
      view.setVerandasOther(this.getVerandasOther());
      view.setCattleShedsOther(this.getCattleShedsOther());    
      view.setRefused(this.getRefused());
      view.setVerandasRefused(this.getVerandasRefused());
      view.setCattleShedsRefused(this.getCattleShedsRefused());
      view.setWrongSurface(this.getWrongSurface());    
      view.setNozzlesUsed(this.getNozzlesUsed());
      view.setPumpsUsed(this.getPumpsUsed());
      view.apply();
    }
  }

  public TeamSprayStatusView getSprayStatusView(ZoneSprayView zsv)
  {
    // Check for existing records
    TeamSprayStatusQuery query = new TeamSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getSpray().getId().EQ(zsv.getConcreteId()));
    query.WHERE(query.getSprayTeam().getTeamId().EQ(this.getSprayTeam()));

    OIterator<? extends TeamSprayStatus> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next().lockView();
      }
      else
      {
        TeamSprayStatusView view = new TeamSprayStatusView();
        view.setSpray(ZoneSpray.get(zsv.getConcreteId()));
        view.setSprayTeam(SprayTeam.getByTeamId(this.getSprayTeam()));

        return view;
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public TeamMember getTeamLeader()
  {
    if (this.getLeaderId() != null && this.getLeaderId().length() > 0)
    {
      return TeamMember.getMemberById(this.getLeaderId());
    }

    return null;
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(INSECTICIDETERM);
    list.add(SPRAYDATE);
    list.add(SPRAYMETHOD);
    list.add(SURFACETYPE);
    list.add(SUPERVISORNAME);
    list.add(SUPERVISORSURNAME);
    list.add(SUPERVISORCODE);
    list.add(SPRAYTEAM);
    list.add(LEADERID);
    list.add(TEAMTARGET);
    list.add(TEAMRECEIVED);
    list.add(TEAMREFILLS);
    list.add(TEAMRETURNED);
    list.add(TEAMUSED);
    list.add(HOUSEHOLDS);
    list.add(STRUCTURES);
    list.add(SPRAYEDHOUSEHOLDS);
    list.add(SPRAYEDSTRUCTURES);
    list.add(PREVSPRAYEDHOUSEHOLDS);
    list.add(PREVSPRAYEDSTRUCTURES);
    list.add(ROOMS);
    list.add(VERANDAS);
    list.add(CATTLESHEDS);
    list.add(SPRAYEDROOMS);
    list.add(VERANDASSPRAYED);
    list.add(CATTLESHEDSSPRAYED);
    list.add(NUMBEROFPEOPLE);
    list.add(PEOPLE);
    list.add(BEDNETS);
    list.add(ROOMSWITHBEDNETS);
    list.add(LOCKED);
    list.add(VERANDASLOCKED);
    list.add(CATTLESHEDSLOCKED);
    list.add(REFUSED);
    list.add(VERANDASREFUSED);
    list.add(CATTLESHEDSREFUSED);
    list.add(OTHER);
    list.add(VERANDASOTHER);
    list.add(CATTLESHEDSOTHER);
    list.add(WRONGSURFACE);
    list.add(NOZZLESUSED);
    list.add(PUMPSUSED);
    
    return list;
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener());
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
