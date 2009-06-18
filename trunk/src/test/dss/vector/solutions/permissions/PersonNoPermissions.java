package dss.vector.solutions.permissions;

import java.util.Date;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.PersonViewDTO;

import junit.framework.TestCase;

public abstract class PersonNoPermissions extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;


  public void testPeople()
  {
    try
    {
      PersonViewDTO dto = new PersonViewDTO(request);
      dto.setFirstName("Test");
      dto.setLastName("Test");
      dto.setDateOfBirth(new Date());
      dto.setIsMDSSUser(true);
      dto.setUsername("Test");
      dto.setPassword("test");
      dto.setIsIPTRecipient(true);
      dto.setIsITNRecipient(true);
      dto.setIsPatient(true);
      dto.setIsSprayLeader(true);
      dto.setIsSprayOperator(true);
      dto.setLeaderId("3434343");
      dto.setOperatorId("3434343a");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }
}
