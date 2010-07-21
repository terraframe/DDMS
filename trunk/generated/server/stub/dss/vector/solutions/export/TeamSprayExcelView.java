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
import dss.vector.solutions.irs.OperatorSprayStatusView;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.RequiredGeoIdProblem;
import dss.vector.solutions.irs.SprayMethod;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.irs.TeamSpray;
import dss.vector.solutions.irs.TeamSprayView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class TeamSprayExcelView extends TeamSprayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
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
        ExcelEnums.getSprayMethod(this.getSprayMethod()), InsecticideBrand.validateByName(this.getInsecticideTerm()),
        teamId);

    if (tsv.getConcreteId() == null || tsv.getConcreteId().equals(""))
    {
      String leaderID = this.getLeaderId();
      
      if (leaderID != null && !leaderID.equals(""))
      {
        TeamMember leader = TeamMember.getMemberById(leaderID);

        if(leader != null)
        {
          tsv.setTeamLeader(leader);
        }
        else
        {
          String msg = "Unknown team member [" + leaderID + "]";
          throw new DataNotFoundException(msg, MdTypeDAO.getMdTypeDAO(TeamMember.CLASS));
        }
      }
      
      tsv.setTeamSprayWeek(this.getTeamSprayWeek());
      tsv.setTarget(this.getTarget());
      tsv.setSurfaceType(Term.validateByDisplayLabel(this.getSurfaceType(), OperatorSprayView.getSurfaceTypeMd()));      
      tsv.apply();
    }

    if (this.getOperatorId() != null && !this.getOperatorId().equals(""))
    {
      OperatorSprayStatusView view = new OperatorSprayStatusView();
      view.setSprayOperator(TeamMember.getOperatorById(this.getOperatorId()));
      view.setOperatorSprayWeek(this.getOperatorSprayWeek());
      view.setReceived(this.getOperatorReceived());
      view.setRefills(this.getOperatorRefills());
      view.setReturned(this.getOperatorReturned());
      view.setUsed(this.getOperatorUsed());
      view.setSpray(TeamSpray.get(tsv.getConcreteId()));
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
    list.add(INSECTICIDETERM);
    list.add(SPRAYDATE);
    list.add(SPRAYMETHOD);
    list.add(SPRAYTEAM);
    list.add(LEADERID);
    list.add(SURFACETYPE);
    list.add(TEAMSPRAYWEEK);
    list.add(TARGET);
    list.add(OPERATORID);
    list.add(OPERATORSPRAYWEEK);
    list.add(OPERATORRECEIVED);
    list.add(OPERATORREFILLS);
    list.add(OPERATORRETURNED);
    list.add(OPERATORUSED);
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
