package dss.vector.solutions.permissions;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.dataaccess.ProgrammingErrorExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.ontology.TermDTO;

public class PersonCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(PersonCRUDPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.OPERATIONAL_MANAGER);
  }

  public void testPeople()
  {
    TermDTO term = TermDTO.get(request, termId);

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 1983);
    calendar.set(Calendar.MONTH, 5);
    calendar.set(Calendar.DAY_OF_YEAR, 11);
    
    PersonViewDTO dto = new PersonViewDTO(request);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(calendar.getTime());
    dto.setIsMDSSUser(true);
    dto.setUsername("Testy2");
    dto.setPassword("test");
    dto.setIsIPTRecipient(true);
    dto.setIsITNRecipient(true);
    dto.setIsPatient(true);
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId(TestConstants.LEADER_ID);
    dto.setOperatorId(TestConstants.OPERATOR_ID);
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonViewDTO update = PersonDTO.lockView(request, dto.getPersonId());
      update.setFirstName("Test");
      update.setLastName("Test");
      update.setDateOfBirth(new Date());
      update.setIsMDSSUser(false);
      update.setIsIPTRecipient(false);
      update.setIsITNRecipient(false);
      update.setIsPatient(false);
      update.setIsSprayLeader(false);
      update.setIsSprayOperator(false);
      update.apply();

      PersonViewDTO test = PersonDTO.getView(request, dto.getPersonId());
      assertEquals(update.getFirstName(), test.getFirstName());
    }
    catch(ProgrammingErrorExceptionDTO e)
    {
      fail(e.getLocalizedMessage());
    }
    finally
    {
      PersonDTO.lock(request, dto.getPersonId()).delete();
    }
  }

}
