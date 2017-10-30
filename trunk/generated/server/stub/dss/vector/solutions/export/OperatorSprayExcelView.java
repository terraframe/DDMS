package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.RequiredAttributeException;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.irs.HouseholdSprayStatusQuery;
import dss.vector.solutions.irs.HouseholdSprayStatusView;
import dss.vector.solutions.irs.HouseholdSprayStatusViewDTO;
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
    
    if (this.getSprayMethod() == null || this.getSprayMethod().equals(""))
    {
      MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(OperatorSpray.CLASS);
      MdAttributeDAOIF mdAttribute = mdClass.definesAttribute(OperatorSpray.SPRAYMETHOD);

      RequiredAttributeException exception = new RequiredAttributeException("SprayMethod is required.");
      exception.setAttributeLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));

      throw exception;
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
    osv.setNozzlesUsed(this.getNozzlesUsed());
    osv.setPumpsUsed(this.getPumpsUsed());
    osv.setUsed(this.getUsed());
    osv.setSurfaceType(Term.validateByDisplayLabel(this.getSurfaceType(), OperatorSprayView.getSurfaceTypeMd()));
    osv.setSprayOperator(operator);
    osv.setSupervisor(Supervisor.getByCodeAndName(this.getSupervisorCode(), this.getSupervisorName(), this.getSupervisorSurname(), true));
    
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
      view.setVerandas(this.getVerandas());
      view.setCattleSheds(this.getCattleSheds());
      view.setSprayedRooms(this.getSprayedRooms());
      view.setVerandasSprayed(this.getVerandasSprayed());
      view.setCattleShedsSprayed(this.getCattleShedsSprayed());
      view.setPeople(this.getPeople());
      view.setNumberOfPeople(this.getNumberOfPeople());
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
      view.setStructureType(Term.validateByDisplayLabel(this.getStructureType(), HouseholdSprayStatusView.getStructureTypeMd()));
      view.setReasonNotSprayed(Term.validateByDisplayLabel(this.getReasonNotSprayed(), HouseholdSprayStatusView.getReasonNotSprayedMd()));
      
      // new as of 3792:
      view.setStructuresNotSprayedSick(this.getStructuresNotSprayedSick());
      view.setStructuresNotSprayedLocked(this.getStructuresNotSprayedLocked());
      view.setStructuresNotSprayedFuneral(this.getStructuresNotSprayedFuneral());
      view.setStructuresNotSprayedRefused(this.getStructuresNotSprayedRefused());
      view.setStructuresNotSprayedNoOneHome(this.getStructuresNotSprayedNoOneHome());
      view.setStructuresNotSprayedOther(this.getStructuresNotSprayedOther());
      view.setNumberMalesProtected(this.getNumberMalesProtected());
      view.setNumberFemalesProtected(this.getNumberFemalesProtected());
      view.setNumberPregnantWomenProtected(this.getNumberPregnantWomenProtected());
      view.setNumberChildrenUnderFiveProtected(this.getNumberChildrenUnderFiveProtected());
      view.setNumberRoomsNotSprayedSick(this.getNumberRoomsNotSprayedSick());
      view.setNumberItnsInUse(this.getNumberItnsInUse());
      view.setNumberPeopleSleepingUnderItns(this.getNumberPeopleSleepingUnderItns());
      view.setNumberPregnantWomenSleepingUnderItns(this.getNumberPregnantWomenSleepingUnderItns());
      view.setNumberChildrenUnderFiveSleepingUnderItns(this.getNumberChildrenUnderFiveSleepingUnderItns());
      
      view.apply();
    }
  }

  private boolean hasHouseholdSprayValues()
  {
    String[] attributeNames = new String[] { HOUSEHOLDID, STRUCTUREID, HOUSEHOLDS, STRUCTURES, SPRAYEDHOUSEHOLDS, SPRAYEDSTRUCTURES, PREVSPRAYEDHOUSEHOLDS, PREVSPRAYEDSTRUCTURES, ROOMS, VERANDAS, CATTLESHEDS, SPRAYEDROOMS, VERANDASSPRAYED, CATTLESHEDSSPRAYED, NUMBEROFPEOPLE, PEOPLE, BEDNETS, ROOMSWITHBEDNETS, LOCKED, VERANDASLOCKED, CATTLESHEDSLOCKED, OTHER, VERANDASOTHER, CATTLESHEDSOTHER, REFUSED, VERANDASREFUSED, CATTLESHEDSREFUSED, WRONGSURFACE, SURFACETYPE, REASONNOTSPRAYED,
        STRUCTURESNOTSPRAYEDSICK,
        STRUCTURESNOTSPRAYEDLOCKED,
        STRUCTURESNOTSPRAYEDFUNERAL,
        STRUCTURESNOTSPRAYEDREFUSED,
        STRUCTURESNOTSPRAYEDNOONEHOME,
        STRUCTURESNOTSPRAYEDOTHER,
        NUMBERMALESPROTECTED,
        NUMBERFEMALESPROTECTED,
        NUMBERPREGNANTWOMENPROTECTED,
        NUMBERCHILDRENUNDERFIVEPROTECTED,
        NUMBERROOMSNOTSPRAYEDSICK,
        NUMBERITNSINUSE,
        NUMBERPEOPLESLEEPINGUNDERITNS,
        NUMBERPREGNANTWOMENSLEEPINGUNDERITNS,
        NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS
    };

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
    list.add(SUPERVISORCODE);
    list.add(TARGET);
    list.add(RECEIVED);
    list.add(REFILLS);
    list.add(RETURNED);
    list.add(USED);
    list.add(NOZZLESUSED);
    list.add(PUMPSUSED);
    list.add(HOUSEHOLDID);
    list.add(STRUCTUREID);
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
    list.add(REASONNOTSPRAYED);
    list.add(SURFACETYPE);
    
    // new as of 3792
    list.add(STRUCTURESNOTSPRAYEDSICK);
    list.add(STRUCTURESNOTSPRAYEDLOCKED);
    list.add(STRUCTURESNOTSPRAYEDFUNERAL);
    list.add(STRUCTURESNOTSPRAYEDREFUSED);
    list.add(STRUCTURESNOTSPRAYEDNOONEHOME);
    list.add(STRUCTURESNOTSPRAYEDOTHER);
    list.add(NUMBERMALESPROTECTED);
    list.add(NUMBERFEMALESPROTECTED);
    list.add(NUMBERPREGNANTWOMENPROTECTED);
    list.add(NUMBERCHILDRENUNDERFIVEPROTECTED);
    list.add(NUMBERROOMSNOTSPRAYEDSICK);
    list.add(NUMBERITNSINUSE);
    list.add(NUMBERPEOPLESLEEPINGUNDERITNS);
    list.add(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS);
    list.add(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS);
    
    return list;
  }

  public static void setupExportListener(ExcelExporter exporter, String[] params)
  {
    exporter.addListener(createExcelGeoListener(null));
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(createExcelGeoListener(importer));
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllSprayTargets())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
}
