package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.RequiredGeoIdProblem;
import dss.vector.solutions.irs.SprayMethod;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamMember;
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

  public SprayMethod getSprayMethodByLabel(String label)
  {
    for (SprayMethod e : SprayMethod.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + SprayMethod.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(SprayMethod.CLASS));
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

    ZoneSprayView zsv = ZoneSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(), getSprayMethodByLabel(this.getSprayMethod()), InsecticideBrand.validateByName(this.getInsecticideTerm()));

    if (zsv.getConcreteId() == null || zsv.getConcreteId().equals(""))
    {
      zsv.setSurfaceType(Term.validateByDisplayLabel(this.getSurfaceType(), OperatorSprayView.getSurfaceTypeMd()));      
      zsv.setSprayWeek(this.getSprayWeek());
      zsv.setSupervisor(Supervisor.getByName(this.getSupervisorName(), this.getSupervisorSurname()));
      zsv.setTarget(this.getTarget());

      zsv.apply();
    }

    if (this.getSprayTeam() != null && !this.getSprayTeam().equals(""))
    {
      TeamSprayStatusView view = new TeamSprayStatusView();
      view.setSpray(ZoneSpray.get(zsv.getConcreteId()));
      view.setSprayTeam(SprayTeam.getByTeamId(this.getSprayTeam()));
      view.setTeamLeader(TeamMember.getMemberById(this.getLeaderId()));
      view.setTeamSprayWeek(this.getTeamSprayWeek());
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
      view.setSprayedRooms(this.getSprayedRooms());
      view.setPeople(this.getPeople());
      view.setBedNets(this.getBedNets());
      view.setRoomsWithBedNets(this.getRoomsWithBedNets());
      view.setLocked(this.getLocked());
      view.setRefused(this.getRefused());
      view.setOther(this.getOther());
      view.apply();
    }
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(INSECTICIDETERM);
    list.add(SPRAYDATE);
    list.add(SPRAYMETHOD);
    list.add(SURFACETYPE);
    list.add(SPRAYWEEK);
    list.add(SUPERVISORNAME);
    list.add(SUPERVISORSURNAME);
    list.add(TARGET);
    list.add(SPRAYTEAM);
    list.add(LEADERID);
    list.add(TEAMSPRAYWEEK);
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
