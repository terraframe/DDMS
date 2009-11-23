package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.irs.AbstractSprayDTO;
import dss.vector.solutions.irs.HouseholdSprayStatusViewDTO;
import dss.vector.solutions.irs.InsecticideBrandDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.irs.OperatorSprayStatusViewDTO;
import dss.vector.solutions.irs.OperatorSprayViewDTO;
import dss.vector.solutions.irs.SprayLeaderDTO;
import dss.vector.solutions.irs.SprayMethodDTO;
import dss.vector.solutions.irs.SprayOperatorDTO;
import dss.vector.solutions.irs.SprayTeamDTO;
import dss.vector.solutions.irs.TeamSprayDTO;
import dss.vector.solutions.irs.TeamSprayStatusViewDTO;
import dss.vector.solutions.irs.TeamSprayViewDTO;
import dss.vector.solutions.irs.ZoneSprayDTO;
import dss.vector.solutions.irs.ZoneSprayViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public class IRSNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(IRSNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MANAGER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testOperatorSpray()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
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
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      SprayLeaderDTO leader = person.getSprayLeaderDelegate();
      SprayOperatorDTO operator = person.getSprayOperatorDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, leader.getId(), new String[] { operator.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setBrandName(TestConstants.BRAND_NAME);
        view.setAmount(44);
        view.setActiveIngredient(term);
        view.setWeight(new BigDecimal(3.3));
        view.setSachetsPerRefill(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

          OperatorSprayViewDTO spray = OperatorSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand, operator.getId());
          spray.setOperatorSprayWeek(33);
          spray.apply();
          fail("Able to create a object without permissions");

        }
        catch (CreatePermissionExceptionDTO e)
        {
          // This is expected
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

  public void testHouseholdSprayStatus()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
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

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, leader.getId(), new String[] { operator.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setBrandName(TestConstants.BRAND_NAME);
        view.setAmount(44);
        view.setActiveIngredient(term);
        view.setWeight(new BigDecimal(3.3));
        view.setSachetsPerRefill(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(request, view.getInsecticdeId());

          OperatorSprayViewDTO spray = OperatorSprayViewDTO.searchBySprayData(systemRequest, zoneGeoId, new Date(), method, brand, operator.getId());
          spray.setOperatorSprayWeek(33);
          spray.setSurfaceType(term);
          spray.apply();

          try
          {
            HouseholdSprayStatusViewDTO status = new HouseholdSprayStatusViewDTO(request);
            status.setSpray(AbstractSprayDTO.get(request, spray.getSprayId()));
            status.setHouseholdId("232");
            status.setStructureId("2321");
            status.apply();

            fail("Able to create a object without permissions");
          }
          catch (CreatePermissionExceptionDTO e)
          {
            // This is expected
          }
          finally
          {
            spray.deleteConcrete();
          }
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

  public void testTeamSpray()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
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
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      SprayLeaderDTO leader = person.getSprayLeaderDelegate();
      SprayOperatorDTO operator = person.getSprayOperatorDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, leader.getId(), new String[] { operator.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setBrandName(TestConstants.BRAND_NAME);
        view.setAmount(44);
        view.setActiveIngredient(term);
        view.setWeight(new BigDecimal(3.3));
        view.setSachetsPerRefill(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());
          TeamSprayViewDTO spray = TeamSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand, team.getId());
          spray.setTeamSprayWeek(31);
          spray.apply();

          fail("Able to create a object without permissions");
        }
        catch (CreatePermissionExceptionDTO e)
        {
          // This is expected
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

  public void testOperatorSprayStatus()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
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
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      SprayLeaderDTO leader = person.getSprayLeaderDelegate();
      SprayOperatorDTO operator = person.getSprayOperatorDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, leader.getId(), new String[] { operator.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setBrandName(TestConstants.BRAND_NAME);
        view.setAmount(44);
        view.setActiveIngredient(term);
        view.setWeight(new BigDecimal(3.3));
        view.setSachetsPerRefill(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

          TeamSprayViewDTO spray = TeamSprayViewDTO.searchBySprayData(systemRequest, zoneGeoId, new Date(), method, brand, team.getId());
          spray.setTeamSprayWeek(33);
          spray.setSurfaceType(term);
          spray.apply();

          try
          {
            OperatorSprayStatusViewDTO status = new OperatorSprayStatusViewDTO(request);
            status.setSprayOperator(operator);
            status.setSprayData(TeamSprayDTO.get(request, spray.getSprayId()).getSprayData());
            status.setHouseholds(32);
            status.setStructures(232);
            status.apply();

            fail("Able to create a object without permissions");
          }
          catch (CreatePermissionExceptionDTO e)
          {
            // This is expected
          }
          finally
          {
            spray.deleteConcrete();
          }
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

  public void testZoneSpray()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
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
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      SprayLeaderDTO leader = person.getSprayLeaderDelegate();
      SprayOperatorDTO operator = person.getSprayOperatorDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, leader.getId(), new String[] { operator.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setBrandName(TestConstants.BRAND_NAME);
        view.setAmount(44);
        view.setActiveIngredient(term);
        view.setWeight(new BigDecimal(3.3));
        view.setSachetsPerRefill(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());
          ZoneSprayViewDTO spray = ZoneSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand);
          spray.apply();

          fail("Able to create a object without permissions");
        }
        catch (CreatePermissionExceptionDTO e)
        {
          // This is expected
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

  public void testTeamSprayStatus()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
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
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      SprayLeaderDTO leader = person.getSprayLeaderDelegate();
      SprayOperatorDTO operator = person.getSprayOperatorDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, leader.getId(), new String[] { operator.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setBrandName(TestConstants.BRAND_NAME);
        view.setAmount(44);
        view.setActiveIngredient(term);
        view.setWeight(new BigDecimal(3.3));
        view.setSachetsPerRefill(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

          ZoneSprayViewDTO spray = ZoneSprayViewDTO.searchBySprayData(systemRequest, zoneGeoId, new Date(), method, brand);
          spray.setSurfaceType(term);
          spray.apply();

          try
          {
            TeamSprayStatusViewDTO status = new TeamSprayStatusViewDTO(request);
            status.setSprayData(ZoneSprayDTO.get(request, spray.getSprayId()).getSprayData());
            status.setSprayTeam(team);
            status.setHouseholds(32);
            status.setStructures(232);
            status.apply();

            fail("Able to create a object without permissions");
          }
          catch (CreatePermissionExceptionDTO e)
          {
            // This is expected
          }
          finally
          {
            spray.deleteConcrete();
          }
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
}
