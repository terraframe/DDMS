package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
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
import dss.vector.solutions.mo.ActiveIngredientDTO;

public abstract class IRSNoPermissions extends TestCase
{
  protected static ClientSession   systemSession;

  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientRequestIF systemRequest;

  protected static String          geoId;

  public void testOperatorSpray()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(systemRequest);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId("3434343");
    dto.setOperatorId("3434343a");
    dto.apply();

    PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    SprayTeamDTO team = new SprayTeamDTO(systemRequest);
    team.setTeamId("test test 7");
    team.create(geoId, leader.getId(), new String[] { operator.getId() });

    InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
    view.setBrandName("Test Brand 349");
    view.setAmount(44);
    view.setActiveIngredient(ingredients[0]);
    view.setWeight(new BigDecimal(3.3));
    view.setSachetsPerRefill(2);
    view.setEnabled(true);
    view.apply();

    InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

    try
    {

      OperatorSprayViewDTO spray = OperatorSprayViewDTO.searchBySprayData(request, geoId, new Date(),
          method, brand, operator.getId());
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
      team.delete();
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }
  }

  public void testHouseholdSprayStatus()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId("3434343");
    dto.setOperatorId("3434343a");
    dto.apply();

    PersonDTO person = PersonDTO.get(request, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    SprayTeamDTO team = new SprayTeamDTO(systemRequest);
    team.setTeamId("test test 7");
    team.create(geoId, leader.getId(), new String[] { operator.getId() });

    InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
    view.setBrandName("Test Brand 349");
    view.setAmount(44);
    view.setActiveIngredient(ingredients[0]);
    view.setWeight(new BigDecimal(3.3));
    view.setSachetsPerRefill(2);
    view.setEnabled(true);
    view.apply();

    InsecticideBrandDTO brand = InsecticideBrandDTO.get(request, view.getInsecticdeId());

    OperatorSprayViewDTO spray = OperatorSprayViewDTO.searchBySprayData(systemRequest, geoId,
        new Date(), method, brand, operator.getId());
    spray.setOperatorSprayWeek(33);
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
      view.deleteConcrete();
      team.delete();
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }
  }

  public void testTeamSpray()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(systemRequest);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId("3434343");
    dto.setOperatorId("3434343a");
    dto.apply();

    PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    SprayTeamDTO team = new SprayTeamDTO(systemRequest);
    team.setTeamId("test test 7");
    team.create(geoId, leader.getId(), new String[] { operator.getId() });

    InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
    view.setBrandName("Test Brand 349");
    view.setAmount(44);
    view.setActiveIngredient(ingredients[0]);
    view.setWeight(new BigDecimal(3.3));
    view.setSachetsPerRefill(2);
    view.setEnabled(true);
    view.apply();

    InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

    try
    {
      TeamSprayViewDTO spray = TeamSprayViewDTO.searchBySprayData(request, geoId, new Date(), method,
          brand, team.getId());
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
      team.delete();
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }
  }

  public void testOperatorSprayStatus()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(systemRequest);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId("3434343");
    dto.setOperatorId("3434343a");
    dto.apply();

    PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    SprayTeamDTO team = new SprayTeamDTO(systemRequest);
    team.setTeamId("test test 7");
    team.create(geoId, leader.getId(), new String[] { operator.getId() });

    InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
    view.setBrandName("Test Brand 349");
    view.setAmount(44);
    view.setActiveIngredient(ingredients[0]);
    view.setWeight(new BigDecimal(3.3));
    view.setSachetsPerRefill(2);
    view.setEnabled(true);
    view.apply();

    InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

    TeamSprayViewDTO spray = TeamSprayViewDTO.searchBySprayData(systemRequest, geoId, new Date(),
        method, brand, team.getId());
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

      fail("Able to create a object without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
    finally
    {
      spray.deleteConcrete();
      view.deleteConcrete();
      team.delete();
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }
  }

  public void testZoneSpray()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(systemRequest);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId("3434343");
    dto.setOperatorId("3434343a");
    dto.apply();

    PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    SprayTeamDTO team = new SprayTeamDTO(systemRequest);
    team.setTeamId("test test 7");
    team.create(geoId, leader.getId(), new String[] { operator.getId() });

    InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
    view.setBrandName("Test Brand 349");
    view.setAmount(44);
    view.setActiveIngredient(ingredients[0]);
    view.setWeight(new BigDecimal(3.3));
    view.setSachetsPerRefill(2);
    view.setEnabled(true);
    view.apply();

    InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

    try
    {
      ZoneSprayViewDTO spray = ZoneSprayViewDTO.searchBySprayData(request, geoId, new Date(), method,
          brand);
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
      team.delete();
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }
  }

  public void testTeamSprayStatus()
  {
    SprayMethodDTO method = SprayMethodDTO.MAIN_SPRAY;
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(systemRequest);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId("3434343");
    dto.setOperatorId("3434343a");
    dto.apply();

    PersonDTO person = PersonDTO.get(systemRequest, dto.getPersonId());
    SprayLeaderDTO leader = person.getSprayLeaderDelegate();
    SprayOperatorDTO operator = person.getSprayOperatorDelegate();

    SprayTeamDTO team = new SprayTeamDTO(systemRequest);
    team.setTeamId("test test 7");
    team.create(geoId, leader.getId(), new String[] { operator.getId() });

    InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(systemRequest);
    view.setBrandName("Test Brand 349");
    view.setAmount(44);
    view.setActiveIngredient(ingredients[0]);
    view.setWeight(new BigDecimal(3.3));
    view.setSachetsPerRefill(2);
    view.setEnabled(true);
    view.apply();

    InsecticideBrandDTO brand = InsecticideBrandDTO.get(systemRequest, view.getInsecticdeId());

    ZoneSprayViewDTO spray = ZoneSprayViewDTO.searchBySprayData(systemRequest, geoId, new Date(),
        method, brand);
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
      view.deleteConcrete();
      team.delete();
      PersonDTO.lock(systemRequest, dto.getPersonId()).delete();
    }
  }
}
