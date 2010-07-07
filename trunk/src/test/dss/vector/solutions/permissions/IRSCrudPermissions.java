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
import dss.vector.solutions.irs.HouseholdSprayStatusDTO;
import dss.vector.solutions.irs.HouseholdSprayStatusViewDTO;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier;
import dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO;
import dss.vector.solutions.irs.InsecticideBrandDTO;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifier;
import dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.irs.InsecticideBrandUseDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.irs.OperatorSprayDTO;
import dss.vector.solutions.irs.OperatorSprayStatusDTO;
import dss.vector.solutions.irs.OperatorSprayStatusViewDTO;
import dss.vector.solutions.irs.OperatorSprayViewDTO;
import dss.vector.solutions.irs.SprayMethodDTO;
import dss.vector.solutions.irs.SprayTeamDTO;
import dss.vector.solutions.irs.TeamMemberDTO;
import dss.vector.solutions.irs.TeamSprayDTO;
import dss.vector.solutions.irs.TeamSprayStatusDTO;
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
    dto.setMemberId(TestConstants.LEADER_ID);    
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      TeamMemberDTO member = person.getTeamMemberDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, member.getId(), new String[] { member.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setProductName(term);
        view.addInsecticideUse(InsecticideBrandUseDTO.IRS);
        view.setActiveIngredient(term);
        view.setConcentrationQuantifier(new BigDecimal("3.3"));
        view.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
        view.setUnitQuantifier(new BigDecimal("44"));
        view.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
        view.setUnitsPerApplication(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

          OperatorSprayViewDTO spray = OperatorSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand, member.getId());
          spray.setSurfaceType(term);
          spray.setOperatorSprayWeek(33);
          spray.apply();

          try
          {
            OperatorSprayViewDTO update = OperatorSprayDTO.lockView(request, spray.getConcreteId());
            update.setTeamSprayWeek(32);
            update.apply();

            OperatorSprayViewDTO test = OperatorSprayDTO.getView(request, spray.getConcreteId());

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
        catch(ProblemExceptionDTO e)
        {
          for(ProblemDTOIF p : e.getProblems())
          {
            fail(p.getMessage());
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
    dto.setMemberId(TestConstants.LEADER_ID);    
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      TeamMemberDTO member = person.getTeamMemberDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, member.getId(), new String[] { member.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setProductName(term);
        view.addInsecticideUse(InsecticideBrandUseDTO.IRS);
        view.setActiveIngredient(term);
        view.setConcentrationQuantifier(new BigDecimal("3.3"));
        view.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
        view.setUnitQuantifier(new BigDecimal("44"));
        view.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
        view.setUnitsPerApplication(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

          OperatorSprayViewDTO spray = OperatorSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand, member.getId());
          spray.setOperatorSprayWeek(33);
          spray.setSurfaceType(term);
          spray.apply();

          try
          {
            HouseholdSprayStatusViewDTO status = new HouseholdSprayStatusViewDTO(request);
            status.setSpray(OperatorSprayDTO.get(request, spray.getConcreteId()));
            status.setHouseholdId("232");
            status.setStructureId("2321");
            status.apply();

            try
            {
              HouseholdSprayStatusViewDTO update = (HouseholdSprayStatusViewDTO) HouseholdSprayStatusDTO.lockView(request, status.getConcreteId());
              update.setHouseholdId("22");
              update.setStructureId("221");
              update.apply();

              HouseholdSprayStatusViewDTO test = (HouseholdSprayStatusViewDTO) HouseholdSprayStatusDTO.getView(request, status.getConcreteId());

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
        catch(ProblemExceptionDTO e)
        {
          for(ProblemDTOIF p : e.getProblems())
          {
            fail(p.getMessage());
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
    dto.setMemberId(TestConstants.LEADER_ID);    
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      TeamMemberDTO member = person.getTeamMemberDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, member.getId(), new String[] { member.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setProductName(term);
        view.addInsecticideUse(InsecticideBrandUseDTO.IRS);
        view.setActiveIngredient(term);
        view.setConcentrationQuantifier(new BigDecimal("3.3"));
        view.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
        view.setUnitQuantifier(new BigDecimal("44"));
        view.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
        view.setUnitsPerApplication(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

          TeamSprayViewDTO spray = TeamSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand, team.getId());
          spray.setTeamSprayWeek(31);
          spray.setSurfaceType(term);
          spray.apply();

          try
          {
            TeamSprayViewDTO update = TeamSprayDTO.lockView(request, spray.getConcreteId());
            update.setTeamSprayWeek(32);
            update.apply();

            TeamSprayViewDTO test = TeamSprayDTO.getView(request, spray.getConcreteId());

            assertEquals(update.getTeamSprayWeek(), test.getTeamSprayWeek());
          }
          finally
          {
            spray.deleteConcrete();
          }
        }
        catch(ProblemExceptionDTO e)
        {
          for(ProblemDTOIF p : e.getProblems())
          {
            fail(p.getMessage());
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
    dto.setMemberId(TestConstants.LEADER_ID);    
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      TeamMemberDTO member = person.getTeamMemberDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, member.getId(), new String[] { member.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setProductName(term);
        view.addInsecticideUse(InsecticideBrandUseDTO.IRS);
        view.setActiveIngredient(term);
        view.setConcentrationQuantifier(new BigDecimal("3.3"));
        view.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
        view.setUnitQuantifier(new BigDecimal("44"));
        view.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
        view.setUnitsPerApplication(2);
        view.setEnabled(true);
        view.apply();

        InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

        try
        {
          TeamSprayViewDTO spray = TeamSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand, team.getId());
          spray.setTeamSprayWeek(33);
          spray.setSurfaceType(term);
          spray.apply();

          try
          {
            OperatorSprayStatusViewDTO status = new OperatorSprayStatusViewDTO(request);
            status.setSprayOperator(member);
            status.setSpray(TeamSprayDTO.get(request, spray.getConcreteId()));
            status.setHouseholds(32);
            status.setStructures(232);
            status.apply();

            try
            {
              OperatorSprayStatusViewDTO update = OperatorSprayStatusDTO.lockView(request, status.getConcreteId());
              update.setHouseholds(22);
              update.setStructures(221);
              update.apply();

              OperatorSprayStatusViewDTO test = OperatorSprayStatusDTO.getView(request, status.getConcreteId());

              assertEquals(update.getHouseholds(), test.getHouseholds());
              assertEquals(update.getStructures(), test.getStructures());
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
        catch(ProblemExceptionDTO e)
        {
          for(ProblemDTOIF p : e.getProblems())
          {
            fail(p.getMessage());
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
    dto.setMemberId(TestConstants.LEADER_ID);    
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      TeamMemberDTO member = person.getTeamMemberDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, member.getId(), new String[] { member.getId() });
      
      try
      {

        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setProductName(term);
        view.addInsecticideUse(InsecticideBrandUseDTO.IRS);
        view.setActiveIngredient(term);
        view.setConcentrationQuantifier(new BigDecimal("3.3"));
        view.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
        view.setUnitQuantifier(new BigDecimal("44"));
        view.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
        view.setUnitsPerApplication(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

          ZoneSprayViewDTO spray = ZoneSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand);
          spray.setSurfaceType(term);
          spray.apply();

          try
          {
            ZoneSprayViewDTO update = ZoneSprayDTO.lockView(request, spray.getConcreteId());
            update.apply();

            ZoneSprayViewDTO test = ZoneSprayDTO.getView(request, spray.getConcreteId());

            assertEquals(update.getSprayDate(), test.getSprayDate());
          }
          finally
          {
            spray.deleteConcrete();
          }
        }
        catch(ProblemExceptionDTO e)
        {
          for(ProblemDTOIF p : e.getProblems())
          {
            fail(p.getMessage());
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
    dto.setMemberId(TestConstants.LEADER_ID);
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
      TeamMemberDTO member = person.getTeamMemberDelegate();

      SprayTeamDTO team = new SprayTeamDTO(systemRequest);
      team.setTeamId(TestConstants.TEAM_ID);
      team.create(zoneGeoId, member.getId(), new String[] { member.getId() });

      try
      {
        InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
        view.setProductName(term);
        view.addInsecticideUse(InsecticideBrandUseDTO.IRS);
        view.setActiveIngredient(term);
        view.setConcentrationQuantifier(new BigDecimal("3.3"));
        view.addConcentrationQualifier(InsecticideBrandConcentrationQualifierDTO.PERCENT);
        view.setUnitQuantifier(new BigDecimal("44"));
        view.addUnitQualifier(InsecticideBrandUnitQualifierDTO.GRAMS);
        view.setUnitsPerApplication(2);
        view.setEnabled(true);
        view.apply();

        try
        {
          InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

          ZoneSprayViewDTO spray = ZoneSprayViewDTO.searchBySprayData(request, zoneGeoId, new Date(), method, brand);
          spray.setSurfaceType(term);
          spray.apply();

          try
          {
            TeamSprayStatusViewDTO status = new TeamSprayStatusViewDTO(request);
            status.setSpray(ZoneSprayDTO.get(request, spray.getConcreteId()));
            status.setSprayTeam(team);
            status.setHouseholds(32);
            status.setStructures(232);
            status.apply();

            try
            {
              TeamSprayStatusViewDTO update = (TeamSprayStatusViewDTO) TeamSprayStatusDTO.lockView(request, status.getConcreteId());
              update.setHouseholds(22);
              update.setStructures(221);
              update.apply();

              TeamSprayStatusViewDTO test = (TeamSprayStatusViewDTO) TeamSprayStatusDTO.getView(request, status.getConcreteId());

              assertEquals(update.getHouseholds(), test.getHouseholds());
              assertEquals(update.getStructures(), test.getStructures());
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
        catch(ProblemExceptionDTO e)
        {
          for(ProblemDTOIF p : e.getProblems())
          {
            fail(p.getMessage());
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
