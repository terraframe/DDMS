package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.ontology.TermDTO;

public class PersonNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(PersonNoPermissions.class, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.MANAGER);
  }

  public void testPeople()
  {
    TermDTO term = TermDTO.get(request, termId);

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
      dto.setSex(term);
      dto.apply();

      dto.delete();
      
      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }
}
