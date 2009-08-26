package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorExceptionDTO;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestConstants;

public abstract class PersonCRUDPermissions extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  public void testPeople()
  {
    PersonViewDTO dto = new PersonViewDTO(request);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
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
