package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.irs.AreaStandardsViewDTO;
import dss.vector.solutions.irs.GeoTargetViewDTO;
import dss.vector.solutions.irs.InsecticideBrandDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.irs.InsecticideNozzleViewDTO;
import dss.vector.solutions.irs.NozzleDTO;
import dss.vector.solutions.irs.NozzleViewDTO;
import dss.vector.solutions.irs.ResourceTargetViewDTO;
import dss.vector.solutions.irs.SprayLeaderDTO;
import dss.vector.solutions.irs.SprayOperatorDTO;
import dss.vector.solutions.irs.SprayTeamDTO;
import dss.vector.solutions.irs.TargetUnitDTO;
import dss.vector.solutions.ontology.TermDTO;

public class PlanningNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(PlanningNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.MANAGER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testCreateSprayTeam()
  {
    TermDTO term = TermDTO.get(request, termId);
    
    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId(TestConstants.LEADER_ID);
    dto.setOperatorId(TestConstants.OPERATOR_ID);
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(request, dto.getPersonId());
      SprayLeaderDTO leader = person.getSprayLeaderDelegate();
      SprayOperatorDTO operator = person.getSprayOperatorDelegate();

      SprayTeamDTO team = new SprayTeamDTO(request);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(facilityGeoId, leader.getId(), new String[] { operator.getId() });

      fail("Able to create a object without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
    finally
    {
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }

  }

  public void testInsecticideBrand()
  {
    TermDTO term = TermDTO.get(request, termId);

    try
    {
      InsecticideBrandViewDTO brand = new InsecticideBrandViewDTO(request);
      brand.setBrandName(TestConstants.BRAND_NAME);
      brand.setAmount(44);
      brand.setActiveIngredient(term);
      brand.setWeight(new BigDecimal(3.3));
      brand.setSachetsPerRefill(2);
      brand.setEnabled(true);
      brand.apply();

      fail("Able to create a object without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testNozzle()
  {

    try
    {
      NozzleViewDTO nozzle = new NozzleViewDTO(request);
      nozzle.setDisplayLabel(TestConstants.NOZZLE_NAME);
      nozzle.setRatio(new BigDecimal(3.4));
      nozzle.setEnabled(true);
      nozzle.apply();

      fail("Able to create a object without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testBrandNozzle()
  {
    TermDTO term = TermDTO.get(request, termId);

    InsecticideBrandViewDTO brand = new InsecticideBrandViewDTO(systemRequest);
    brand.setBrandName(TestConstants.BRAND_NAME);
    brand.setAmount(44);
    brand.setActiveIngredient(term);
    brand.setWeight(new BigDecimal(3.3));
    brand.setSachetsPerRefill(2);
    brand.setEnabled(true);
    brand.apply();

    try
    {
      NozzleViewDTO nozzle = new NozzleViewDTO(systemRequest);
      nozzle.setDisplayLabel(TestConstants.NOZZLE_NAME);
      nozzle.setRatio(new BigDecimal(3.4));
      nozzle.setEnabled(true);
      nozzle.apply();

      try
      {
        InsecticideNozzleViewDTO config = new InsecticideNozzleViewDTO(request);
        InsecticideBrandDTO concreteBrand = InsecticideBrandDTO.get(request, brand.getInsecticdeId());
        NozzleDTO concreteNozzle = NozzleDTO.get(request, nozzle.getNozzleId());

        config.setBrand(concreteBrand);
        config.setNozzle(concreteNozzle);
        config.apply();

        fail("Able to create a object without permissions");
      }
      catch (CreatePermissionExceptionDTO e)
      {
        // This is expected
      }
      finally
      {
        nozzle.deleteConcrete();
      }
    }
    finally
    {
      brand.deleteConcrete();
    }
  }

  public void testAreaStandards()
  {

    try
    {
      AreaStandardsViewDTO area = new AreaStandardsViewDTO(request);
      area.setHousehold(3.3F);
      area.setStructureArea(3.4F);
      area.setRoom(2.3F);
      area.setUnitNozzleAreaCoverage(4.3F);
      area.addTargetUnit(TargetUnitDTO.HOUSEHOLD);
      area.apply();

      fail("Able to create a object without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testGeoTargets()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(2006, 1, 1);

    Date startDate = calendar.getTime();

    calendar.clear();
    calendar.set(2006, 10, 1);

    Date endDate = calendar.getTime();

    MalariaSeasonDTO season = new MalariaSeasonDTO(systemRequest);
    season.setSeasonName(TestConstants.SEASON_NAME);
    season.setStartDate(startDate);
    season.setEndDate(endDate);
    season.apply();

    try
    {
      GeoTargetViewDTO view = new GeoTargetViewDTO(request);
      view.setGeoEntity(GeoEntityDTO.searchByGeoId(request, facilityGeoId));
      view.setSeason(season);
      view.setTarget_0(4);
      view.apply();

      fail("Able to create a object without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
    finally
    {
      season.delete();
    }
  }

  public void testTeamTargets()
  {
    TermDTO term = TermDTO.get(request, termId);

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(2006, 1, 1);

    Date startDate = calendar.getTime();

    calendar.clear();
    calendar.set(2006, 10, 1);

    Date endDate = calendar.getTime();

    MalariaSeasonDTO season = new MalariaSeasonDTO(systemRequest);
    season.setSeasonName(TestConstants.SEASON_NAME);
    season.setStartDate(startDate);
    season.setEndDate(endDate);
    season.apply();

    try
    {

      PersonViewDTO dto = new PersonViewDTO(systemRequest);
      dto.setFirstName("Test");
      dto.setLastName("Test");
      dto.setDateOfBirth(new Date());
      dto.setIsSprayLeader(true);
      dto.setIsSprayOperator(true);
      dto.setLeaderId(TestConstants.LEADER_ID);
      dto.setOperatorId(TestConstants.OPERATOR_ID);
      dto.setSex(term);
      dto.apply();

      try
      {
        PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
        SprayLeaderDTO leader = person.getSprayLeaderDelegate();
        SprayOperatorDTO operator = person.getSprayOperatorDelegate();

        SprayTeamDTO team = new SprayTeamDTO(systemRequest);
        team.setTeamId(TestConstants.TEAM_ID);
        team.create(zoneGeoId, leader.getId(), new String[] { operator.getId() });

        try
        {
          ResourceTargetViewDTO view = new ResourceTargetViewDTO(request);
          view.setTargeter(team);
          view.setSeason(season);
          view.setTarget_0(4);
          view.apply();

          fail("Able to create a object without permissions");
        }
        catch (CreatePermissionExceptionDTO e)
        {
          // This is expected
        }
        finally
        {
          team.delete();
        }
      }
      finally
      {
        PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
      }
    }
    finally
    {
      season.delete();
    }

  }
}
