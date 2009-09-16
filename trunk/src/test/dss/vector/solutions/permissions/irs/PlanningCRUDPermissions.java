package dss.vector.solutions.permissions.irs;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.irs.AreaStandardsDTO;
import dss.vector.solutions.irs.AreaStandardsViewDTO;
import dss.vector.solutions.irs.GeoTargetDTO;
import dss.vector.solutions.irs.GeoTargetViewDTO;
import dss.vector.solutions.irs.InsecticideBrandDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.irs.InsecticideNozzleDTO;
import dss.vector.solutions.irs.InsecticideNozzleViewDTO;
import dss.vector.solutions.irs.NozzleDTO;
import dss.vector.solutions.irs.NozzleViewDTO;
import dss.vector.solutions.irs.ResourceTargetDTO;
import dss.vector.solutions.irs.ResourceTargetViewDTO;
import dss.vector.solutions.irs.SprayLeaderDTO;
import dss.vector.solutions.irs.SprayOperatorDTO;
import dss.vector.solutions.irs.SprayTeamDTO;
import dss.vector.solutions.irs.TargetUnitDTO;
import dss.vector.solutions.mo.ActiveIngredientDTO;

public abstract class PlanningCRUDPermissions extends TestCase
{
  protected static ClientSession   systemSession;

  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientRequestIF systemRequest;

  protected static String          geoId;

  public void testCreateSprayTeam()
  {
    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId(TestConstants.LEADER_ID);
    dto.setOperatorId(TestConstants.OPERATOR_ID);
    dto.apply();

    PersonDTO person = PersonDTO.get(request, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    SprayTeamDTO team = new SprayTeamDTO(request);
    team.setTeamId(TestConstants.TEAM_ID);
    team.create(geoId, leader.getId(), new String[] { operator.getId() });

    try
    {
      team.lock();
      team.setTeamId("Differn");
      team.apply();

      SprayTeamDTO test = SprayTeamDTO.get(request, team.getId());

      assertEquals(team.getTeamId(), test.getTeamId());
    }
    finally
    {
      team.delete();
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }

  }

  public void testInsecticideBrand()
  {
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);

    InsecticideBrandViewDTO brand = new InsecticideBrandViewDTO(request);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.setAmount(44);
    brand.setActiveIngredient(ingredients[0]);
    brand.setWeight(new BigDecimal(3.3));
    brand.setSachetsPerRefill(2);
    brand.setEnabled(true);
    brand.apply();

    try
    {

      InsecticideBrandViewDTO update = InsecticideBrandDTO.lockView(request, brand.getInsecticdeId());
      update.setBrandName(TestConstants.BRAND_NAME_2);
      update.setAmount(42);
      update.setActiveIngredient(ingredients[1]);
      update.setEnabled(true);
      update.apply();

      InsecticideBrandViewDTO test = InsecticideBrandDTO.getView(request, brand.getInsecticdeId());

      assertEquals(update.getBrandName(), test.getBrandName());
      assertEquals(update.getAmount(), test.getAmount());
      assertEquals(update.getActiveIngredient().getId(), test.getActiveIngredient().getId());
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF p : e.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {
      brand.deleteConcrete();
    }
  }

  public void testNozzle()
  {
    NozzleViewDTO nozzle = new NozzleViewDTO(request);
    nozzle.setDisplayLabel(TestConstants.NOZZLE_NAME);
    nozzle.setRatio(new BigDecimal(3.4));
    nozzle.setEnabled(true);
    nozzle.apply();

    try
    {

      NozzleViewDTO update = NozzleDTO.lockView(request, nozzle.getNozzleId());
      nozzle.setDisplayLabel(TestConstants.NOZZLE_NAME_2);
      nozzle.setRatio(new BigDecimal(3.1));
      nozzle.setEnabled(true);
      update.apply();

      NozzleViewDTO test = NozzleDTO.getView(request, nozzle.getNozzleId());

      assertEquals(update.getDisplayLabel(), test.getDisplayLabel());
      assertEquals(update.getRatio(), test.getRatio());
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF p : e.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {
      nozzle.deleteConcrete();
    }
  }

  public void testBrandNozzle()
  {
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);

    InsecticideBrandViewDTO brand = new InsecticideBrandViewDTO(request);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.setAmount(44);
    brand.setActiveIngredient(ingredients[0]);
    brand.setWeight(new BigDecimal(3.3));
    brand.setSachetsPerRefill(2);
    brand.setEnabled(true);
    brand.apply();

    NozzleViewDTO nozzle = new NozzleViewDTO(request);
    nozzle.setDisplayLabel(TestConstants.NOZZLE_NAME);
    nozzle.setRatio(new BigDecimal(3.4));
    nozzle.setEnabled(true);
    nozzle.apply();

    InsecticideNozzleViewDTO config = new InsecticideNozzleViewDTO(request);
    
    try
    {
      InsecticideBrandDTO concreteBrand = InsecticideBrandDTO.get(request, brand.getInsecticdeId());
      NozzleDTO concreteNozzle = NozzleDTO.get(request, nozzle.getNozzleId());
      
      config.setBrand(concreteBrand);
      config.setNozzle(concreteNozzle);
      config.apply();

      InsecticideNozzleViewDTO update = InsecticideNozzleDTO.lockView(request, config.getInsecticideNozzleId());
      update.apply();

      InsecticideNozzleViewDTO test = InsecticideNozzleDTO.getView(request, config.getInsecticideNozzleId());

      assertEquals(update.getBrand().getId(), test.getBrand().getId());
      assertEquals(update.getNozzle().getId(), test.getNozzle().getId());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      config.deleteConcrete();
      brand.deleteConcrete();
      nozzle.deleteConcrete();
    }
  }

  public void testAreaStandards()
  {
    AreaStandardsViewDTO area = new AreaStandardsViewDTO(request);
    area.setHousehold(3.3F);
    area.setStructureArea(3.4F);
    area.setRoom(2.3F);
    area.setUnitNozzleAreaCoverage(4.3F);
    area.addTargetUnit(TargetUnitDTO.HOUSEHOLD);
    area.apply();

    try
    {

      AreaStandardsViewDTO update = AreaStandardsDTO.lockView(request, area.getAreaStandardsId());
      update.setHousehold(3.3F);
      update.setStructureArea(3.4F);
      update.setRoom(2.3F);
      update.setUnitNozzleAreaCoverage(4.3F);
      update.apply();

      AreaStandardsViewDTO test = AreaStandardsDTO.getView(request, area.getAreaStandardsId());

      assertEquals(update.getHousehold(), test.getHousehold());
      assertEquals(update.getStructureArea(), test.getStructureArea());
      assertEquals(update.getRoom(), test.getRoom());
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF p : e.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {
      area.deleteConcrete();
    }
  }


  public void testGeoTargets()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(2005, 1, 1);

    Date startDate = calendar.getTime();

    calendar.clear();
    calendar.set(2005, 10, 1);

    Date endDate = calendar.getTime();

    MalariaSeasonDTO season = new MalariaSeasonDTO(systemRequest);
    season.setSeasonName(TestConstants.SEASON_NAME);
    season.setStartDate(startDate);
    season.setEndDate(endDate);
    season.apply();
    
    GeoTargetViewDTO view = new GeoTargetViewDTO(request);
    view.setGeoEntity(GeoEntityDTO.searchByGeoId(request, geoId));
    view.setSeason(season);
    view.setTarget_0(4);
    view.apply();
    
    try
    {
      GeoTargetDTO.lock(request, view.getTargetId());
      GeoTargetViewDTO update = GeoTargetDTO.getView(request, view.getTargetId());
      update.setTarget_0(6);
      update.apply();

      GeoTargetViewDTO test = GeoTargetDTO.getView(request, view.getTargetId());
      
      assertEquals(update.getTarget_0(), test.getTarget_0());
    }
    finally
    {
      view.deleteConcrete();
      season.delete();
    }
  }

  public void testTeamTargets()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(2005, 1, 1);

    Date startDate = calendar.getTime();

    calendar.clear();
    calendar.set(2005, 10, 1);

    Date endDate = calendar.getTime();

    MalariaSeasonDTO season = new MalariaSeasonDTO(systemRequest);
    season.setSeasonName(TestConstants.SEASON_NAME);
    season.setStartDate(startDate);
    season.setEndDate(endDate);
    season.apply();
    
    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId(TestConstants.LEADER_ID);
    dto.setOperatorId(TestConstants.OPERATOR_ID);
    dto.apply();

    PersonDTO person = PersonDTO.get(request, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    SprayTeamDTO team = new SprayTeamDTO(request);
    team.setTeamId(TestConstants.TEAM_ID);
    team.create(geoId, leader.getId(), new String[] { operator.getId() });

    
    ResourceTargetViewDTO view = new ResourceTargetViewDTO(request);
    view.setTargeter(team);
    view.setSeason(season);
    view.setTarget_0(4);
    view.apply();
    
    try
    {
      ResourceTargetDTO.lock(request, view.getTargetId());
      ResourceTargetViewDTO update = ResourceTargetDTO.getView(request, view.getTargetId());
      update.setTarget_0(6);
      update.apply();

      ResourceTargetViewDTO test = ResourceTargetDTO.getView(request, view.getTargetId());
      
      assertEquals(update.getTarget_0(), test.getTarget_0());
    }
    finally
    {
      view.deleteConcrete();
      season.delete();
      team.delete();
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }
  }

}
