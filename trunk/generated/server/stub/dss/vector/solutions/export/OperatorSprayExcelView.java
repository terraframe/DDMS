package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.irs.HouseholdSprayStatusQuery;
import dss.vector.solutions.irs.HouseholdSprayStatusView;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.RequiredGeoIdProblem;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class OperatorSprayExcelView extends OperatorSprayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
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

    TeamMember operator = TeamMember.getOperatorById(this.getOperatorId());
    String operatorId = "";

    if (operator != null)
    {
      operatorId = operator.getId();
    }

    OperatorSprayView osv = OperatorSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(), ExcelEnums.getSprayMethod(this.getSprayMethod()), InsecticideBrand.validateByName(this.getInsecticideTerm()), operatorId);

    // Only create values if one already exists do not update
    String leaderID = this.getLeaderId();

    if (leaderID != null && !leaderID.equals(""))
    {
      TeamMember leader = TeamMember.getMemberById(leaderID);

      if (leader != null)
      {
        osv.setTeamLeader(leader);
      }
      else
      {
        String msg = "Unknown team member [" + leaderID + "]";
        throw new DataNotFoundException(msg, MdTypeDAO.getMdTypeDAO(TeamMember.CLASS));
      }
    }

    osv.setTarget(this.getTarget());
    osv.setReceived(this.getReceived());
    osv.setRefills(this.getRefills());
    osv.setReturned(this.getReturned());
    osv.setUsed(this.getUsed());
    osv.setSurfaceType(Term.validateByDisplayLabel(this.getSurfaceType(), OperatorSprayView.getSurfaceTypeMd()));
    osv.setSprayOperator(operator);
    osv.setSupervisor(Supervisor.getByName(this.getSupervisorName(), this.getSupervisorSurname()));

    if (this.getSprayTeam() != null && !this.getSprayTeam().equals(""))
    {
      SprayTeam team = SprayTeam.getByTeamId(this.getSprayTeam());

      if (team == null)
      {
        String msg = "Unknown spray team [" + this.getSprayTeam() + "]";
        throw new DataNotFoundException(msg, MdTypeDAO.getMdTypeDAO(SprayTeam.CLASS));
      }

      osv.setSprayTeam(team);
    }

    osv.apply();

    if (this.hasHouseholdSprayValues())
    {
      HouseholdSprayStatusView view = this.getHouseholdSprayStatusView(osv);
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
      view.setWrongSurface(this.getWrongSurface());
      view.apply();
    }
  }

  private boolean hasHouseholdSprayValues()
  {
    String[] attributeNames = new String[] { HOUSEHOLDID, STRUCTUREID, HOUSEHOLDS, STRUCTURES, SPRAYEDHOUSEHOLDS, SPRAYEDSTRUCTURES, PREVSPRAYEDHOUSEHOLDS, PREVSPRAYEDSTRUCTURES, ROOMS, SPRAYEDROOMS, PEOPLE, BEDNETS, ROOMSWITHBEDNETS, LOCKED, OTHER, REFUSED, WRONGSURFACE };

    for (String attributeName : attributeNames)
    {
      String value = this.getValue(attributeName);

      if (value != null && value.length() > 0)
      {
        return true;
      }
    }

    return false;
  }

  public HouseholdSprayStatusView getHouseholdSprayStatusView(OperatorSprayView osv)
  {
    // Check for existing records
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getSpray().getId().EQ(osv.getConcreteId()));
    query.WHERE(query.getHouseholdId().EQ(this.getHouseholdId()));
    query.WHERE(query.getStructureId().EQ(this.getStructureId()));
    OIterator<? extends HouseholdSprayStatus> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next().getView();
      }
      else
      {
        HouseholdSprayStatusView view = new HouseholdSprayStatusView();
        view.setHouseholdId(this.getHouseholdId());
        view.setStructureId(this.getStructureId());
        view.setSpray(OperatorSpray.get(osv.getConcreteId()));

        return view;
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(INSECTICIDETERM);
    list.add(SPRAYDATE);
    list.add(SPRAYMETHOD);
    list.add(SPRAYTEAM);
    list.add(OPERATORID);
    list.add(LEADERID);
    list.add(SURFACETYPE);
    list.add(SUPERVISORNAME);
    list.add(SUPERVISORSURNAME);
    list.add(TARGET);
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
    list.add(WRONGSURFACE);
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
