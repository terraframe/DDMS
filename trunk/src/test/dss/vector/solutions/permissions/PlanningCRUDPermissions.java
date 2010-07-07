package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;

import com.runwaysdk.DoNotWeave;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.irs.AreaStandardsDTO;
import dss.vector.solutions.irs.AreaStandardsViewDTO;
import dss.vector.solutions.irs.GeoTargetDTO;
import dss.vector.solutions.irs.GeoTargetViewDTO;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO;
import dss.vector.solutions.irs.InsecticideBrandDTO;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifier;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.irs.InsecticideBrandUseDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.irs.InsecticideNozzleDTO;
import dss.vector.solutions.irs.InsecticideNozzleViewDTO;
import dss.vector.solutions.irs.NozzleDTO;
import dss.vector.solutions.irs.NozzleViewDTO;
import dss.vector.solutions.irs.ResourceTargetDTO;
import dss.vector.solutions.irs.ResourceTargetViewDTO;
import dss.vector.solutions.irs.SprayTeamDTO;
import dss.vector.solutions.irs.TargetUnitDTO;
import dss.vector.solutions.irs.TeamMemberDTO;
import dss.vector.solutions.ontology.TermDTO;

public class PlanningCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(PlanningCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.OPERATIONAL_MANAGER);
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
    dto.setMemberId(TestConstants.LEADER_ID);
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(request, dto.getPersonId());
      TeamMemberDTO member = person.getTeamMemberDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, member.getId(), new String[] { member.getId() });

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
      }
    }
    finally
    {
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }

  }

  public void testInsecticideBrand()
  {
    TermDTO term = TermDTO.get(request, termId);

    InsecticideBrandViewDTO brand = new InsecticideBrandViewDTO(request);
    brand.setProductName(term);
    brand.addInsecticideUse(InsecticideBrandUseDTO.IRS);
    brand.setActiveIngredient(term);
    brand.setConcentrationQuantifier(new BigDecimal("4.50"));
    brand.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
    brand.setUnitQuantifier(new BigDecimal("100"));
    brand.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
    brand.setUnitsPerApplication(20);
    brand.setEnabled(true);
    brand.apply();

    try
    {

      InsecticideBrandViewDTO update = InsecticideBrandDTO.lockView(request, brand.getInsecticdeId());
      update.setProductName(term);
      update.addInsecticideUse(InsecticideBrandUseDTO.IRS);
      update.setActiveIngredient(term);
      update.setConcentrationQuantifier(new BigDecimal("4.50"));
      update.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
      update.setUnitQuantifier(new BigDecimal("100"));
      update.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
      update.setUnitsPerApplication(20);
      update.setEnabled(true);
      update.apply();

      InsecticideBrandViewDTO test = InsecticideBrandDTO.getView(request, brand.getInsecticdeId());

      assertEquals(update.getProductName().getId(), test.getProductName().getId());
      assertEquals(update.getConcentrationQuantifier(), test.getConcentrationQuantifier());
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
    TermDTO term = TermDTO.get(request, termId);

    InsecticideBrandViewDTO brand = new InsecticideBrandViewDTO(request);
    brand.setProductName(term);
    brand.addInsecticideUse(InsecticideBrandUseDTO.IRS);
    brand.setActiveIngredient(term);
    brand.setConcentrationQuantifier(new BigDecimal("4.50"));
    brand.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
    brand.setUnitQuantifier(new BigDecimal("100"));
    brand.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
    brand.setUnitsPerApplication(20);
    brand.setEnabled(true);
    brand.apply();

    try
    {
      NozzleViewDTO nozzle = new NozzleViewDTO(request);
      nozzle.setDisplayLabel(TestConstants.NOZZLE_NAME);
      nozzle.setRatio(new BigDecimal(3.4));
      nozzle.setEnabled(true);
      nozzle.apply();

      try
      {
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
        }
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
    MalariaSeasonDTO season = TestFixture.createMalairaSeason(systemRequest);

    try
    {
      GeoTargetViewDTO view = new GeoTargetViewDTO(request);
      view.setGeoEntity(GeoEntityDTO.searchByGeoId(request, zoneGeoId));
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
      }
    }
    finally
    {
      season.delete();
    }
  }

  public void testTeamTargets()
  {
    TermDTO term = TermDTO.get(request, termId);
    MalariaSeasonDTO season = TestFixture.createMalairaSeason(systemRequest);

    try
    {
      PersonViewDTO dto = new PersonViewDTO(systemRequest);
      dto.setFirstName("Test");
      dto.setLastName("Test");
      dto.setDateOfBirth(new Date());
      dto.setIsSprayLeader(true);
      dto.setIsSprayOperator(true);
      dto.setMemberId(TestConstants.LEADER_ID);
      dto.setSex(term);
      dto.apply();

      try
      {
        PersonDTO person = PersonDTO.get(request, dto.getPersonId());
        TeamMemberDTO member = person.getTeamMemberDelegate();

        SprayTeamDTO team = new SprayTeamDTO(systemRequest);
        team.setTeamId(TestConstants.TEAM_ID);
        team.create(zoneGeoId, member.getId(), new String[] { member.getId() });

        try
        {
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
          }
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
