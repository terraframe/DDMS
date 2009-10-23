package dss.vector.solutions.permissions;


import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.irs.AbstractSprayDTO;
import dss.vector.solutions.irs.HouseholdSprayStatusDTO;
import dss.vector.solutions.irs.HouseholdSprayStatusViewDTO;
import dss.vector.solutions.irs.InsecticideBrandDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.irs.OperatorSprayDTO;
import dss.vector.solutions.irs.OperatorSprayStatusViewDTO;
import dss.vector.solutions.irs.OperatorSprayViewDTO;
import dss.vector.solutions.irs.SprayLeaderDTO;
import dss.vector.solutions.irs.SprayMethodDTO;
import dss.vector.solutions.irs.SprayOperatorDTO;
import dss.vector.solutions.irs.SprayStatusDTO;
import dss.vector.solutions.irs.SprayTeamDTO;
import dss.vector.solutions.irs.TeamSprayDTO;
import dss.vector.solutions.irs.TeamSprayStatusViewDTO;
import dss.vector.solutions.irs.TeamSprayViewDTO;
import dss.vector.solutions.irs.ZoneSprayDTO;
import dss.vector.solutions.irs.ZoneSprayViewDTO;
import dss.vector.solutions.ontology.TermDTO;

public class IRSCrudPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(IRSCrudPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.OPERATIONAL_MANAGER);
  }
  
  public void testOperatorSpray()
  {
    TermDTO term = TermDTO.get(request, termId);
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
    

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

    PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    try
    {
      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(facilityGeoId, leader.getId(), new String[] { operator.getId() });

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

          OperatorSprayViewDTO spray = OperatorSprayViewDTO.searchBySprayData(request, facilityGeoId, new Date(), method, brand, operator.getId());
          spray.setOperatorSprayWeek(33);
          spray.apply();

          try
          {
            OperatorSprayViewDTO update = OperatorSprayDTO.lockView(request, spray.getSprayId());
            update.setTeamSprayWeek(32);
            update.apply();

            OperatorSprayViewDTO test = OperatorSprayDTO.getView(request, spray.getSprayId());

            assertEquals(update.getOperatorSprayWeek(), test.getOperatorSprayWeek());
            assertEquals(update.getTeamSprayWeek(), test.getTeamSprayWeek());
          }
          catch (Exception e)
          {
            e.printStackTrace();
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
      try
      {
        PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
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
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      SprayLeaderDTO leader = person.getSprayLeaderDelegate();
      SprayOperatorDTO operator = person.getSprayOperatorDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(facilityGeoId, leader.getId(), new String[] { operator.getId() });

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

          OperatorSprayViewDTO spray = OperatorSprayViewDTO.searchBySprayData(request, facilityGeoId, new Date(), method, brand, operator.getId());
          spray.setOperatorSprayWeek(33);
          spray.apply();

          try
          {
            HouseholdSprayStatusViewDTO status = new HouseholdSprayStatusViewDTO(request);
            status.setSpray(AbstractSprayDTO.get(request, spray.getSprayId()));
            status.setHouseholdId("232");
            status.setStructureId("2321");
            status.apply();

            try
            {
              HouseholdSprayStatusViewDTO update = (HouseholdSprayStatusViewDTO) HouseholdSprayStatusDTO.lockView(request, status.getStatusId());
              update.setHouseholdId("22");
              update.setStructureId("221");
              update.apply();

              HouseholdSprayStatusViewDTO test = (HouseholdSprayStatusViewDTO) HouseholdSprayStatusDTO.getView(request, status.getStatusId());

              assertEquals(update.getHouseholdId(), test.getHouseholdId());
              assertEquals(update.getStructureId(), test.getStructureId());
            }
            finally
            {
              status.deleteConcrete();
            }
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
      team.create(facilityGeoId, leader.getId(), new String[] { operator.getId() });

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

          TeamSprayViewDTO spray = TeamSprayViewDTO.searchBySprayData(request, facilityGeoId, new Date(), method, brand, team.getId());
          spray.setTeamSprayWeek(31);
          spray.apply();

          try
          {
            TeamSprayViewDTO update = TeamSprayDTO.lockView(request, spray.getSprayId());
            update.setTeamSprayWeek(32);
            update.apply();

            TeamSprayViewDTO test = TeamSprayDTO.getView(request, spray.getSprayId());

            assertEquals(update.getTeamSprayWeek(), test.getTeamSprayWeek());
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
      team.create(facilityGeoId, leader.getId(), new String[] { operator.getId() });

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

        InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

        try
        {
          TeamSprayViewDTO spray = TeamSprayViewDTO.searchBySprayData(request, facilityGeoId, new Date(), method, brand, team.getId());
          spray.setTeamSprayWeek(33);
          spray.apply();

          try
          {
            OperatorSprayStatusViewDTO status = new OperatorSprayStatusViewDTO(request);
            status.setSprayOperator(operator);
            status.setSprayData(TeamSprayDTO.get(request, spray.getSprayId()).getSprayData());
            status.setHouseholds(32);
            status.setStructures(232);
            status.apply();

            try
            {
              OperatorSprayStatusViewDTO update = (OperatorSprayStatusViewDTO) SprayStatusDTO.lockView(request, status.getStatusId());
              update.setHouseholds(22);
              update.setStructures(221);
              update.apply();

              OperatorSprayStatusViewDTO test = (OperatorSprayStatusViewDTO) HouseholdSprayStatusDTO.getView(request, status.getStatusId());

              assertEquals(update.getHouseholds(), test.getHouseholds());
              assertEquals(update.getStructures(), test.getStructures());
            }
            finally
            {
              AbstractSprayDTO s = status.getSpray();

              status.deleteConcrete();
              s.delete();
            }
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
      team.create(facilityGeoId, leader.getId(), new String[] { operator.getId() });
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

          ZoneSprayViewDTO spray = ZoneSprayViewDTO.searchBySprayData(request, facilityGeoId, new Date(), method, brand);
          spray.apply();

          try
          {
            ZoneSprayViewDTO update = ZoneSprayDTO.lockView(request, spray.getSprayId());
            update.apply();

            ZoneSprayViewDTO test = ZoneSprayDTO.getView(request, spray.getSprayId());

            assertEquals(update.getSprayDate(), test.getSprayDate());
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

  public void testTeamSprayStatus()
  {
    TermDTO term = TermDTO.get(request, termId);
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
    

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId(TestConstants.LEADER_ID);
    dto.setOperatorId(TestConstants.OPERATOR_ID);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      SprayLeaderDTO leader = person.getSprayLeaderDelegate();
      SprayOperatorDTO operator = person.getSprayOperatorDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(facilityGeoId, leader.getId(), new String[] { operator.getId() });

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

          ZoneSprayViewDTO spray = ZoneSprayViewDTO.searchBySprayData(request, facilityGeoId, new Date(), method, brand);
          spray.apply();

          try
          {
            TeamSprayStatusViewDTO status = new TeamSprayStatusViewDTO(request);
            status.setSprayData(ZoneSprayDTO.get(request, spray.getSprayId()).getSprayData());
            status.setSprayTeam(team);
            status.setHouseholds(32);
            status.setStructures(232);
            status.apply();

            try
            {
              TeamSprayStatusViewDTO update = (TeamSprayStatusViewDTO) SprayStatusDTO.lockView(request, status.getStatusId());
              update.setHouseholds(22);
              update.setStructures(221);
              update.apply();

              TeamSprayStatusViewDTO test = (TeamSprayStatusViewDTO) HouseholdSprayStatusDTO.getView(request, status.getStatusId());

              assertEquals(update.getHouseholds(), test.getHouseholds());
              assertEquals(update.getStructures(), test.getStructures());
            }
            finally
            {
              AbstractSprayDTO s = status.getSpray();

              status.deleteConcrete();
              s.delete();
            }
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
