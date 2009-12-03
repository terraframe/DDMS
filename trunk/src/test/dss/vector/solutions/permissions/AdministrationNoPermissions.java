package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.dataaccess.attributes.ClientReadAttributePermissionException;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;
import com.terraframe.mojo.session.WritePermissionExceptionDTO;
import com.terraframe.mojo.transport.metadata.AttributeDateMdDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.MDSSUserDTO;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.PropertyDTO;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.util.ReadableAttributeViewDTO;

public class AdministrationNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(AdministrationNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.MANAGER, MDSSRoleInfo.OPERATIONAL_MANAGER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testUpdateProperty()
  {
    try
    {
      PropertyDTO property = PropertyDTO.getByPackageAndName(request, PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.ADULT_DDA_RESISTANCE);
      property.lock();

      fail("Able to write a property without permissions");
    }
    catch (WritePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testCreateMalariaSeason()
  {
    try
    {
      MalariaSeasonDTO dto = new MalariaSeasonDTO(request);
      dto.setSeasonName("Test Season");
      dto.setStartDate(new Date());
      dto.setEndDate(new Date());
      dto.apply();

      fail("Able to create a new Malaraia Season");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testAgeGroupGrid()
  {
    AggregatedAgeGroupDTO[] groups = AggregatedAgeGroupDTO.getAll(request);

    AggregatedAgeGroupDTO dto = groups[groups.length - 1];

    Integer startAge = dto.getStartAge();
    Integer endAge = dto.getStartAge();

    try
    {
      dto.lock();
      dto.setStartAge(1);
      dto.setEndAge(2);
      dto.apply();

      dto.lock();
      dto.setStartAge(startAge);
      dto.setEndAge(endAge);
      dto.apply();

      fail("Able to modify an age group");
    }
    catch (WritePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testGUIVisibility()
  {
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    AttributeDateMdDTO attributeMd = dto.getCollectionDateMd();

    try
    {

      ReadableAttributeViewDTO view = new ReadableAttributeViewDTO(request);
      view.setAttributeName(attributeMd.getName());
      view.setDisplayLabel("Test");
      view.setReadPermission(false);
      view.setAttributeDescription("Desc");

      ReadableAttributeViewDTO.setActorAttributes(request, MosquitoCollectionDTO.CLASS, MDSSRoleInfo.GUI_VISIBILITY, new ReadableAttributeViewDTO[] { view });

      fail("Able to create a new Mo term");
    }
    catch (ClientReadAttributePermissionException e)
    {
      // This is expected
    }
  }

  public void testRoleAssignment()
  {
    TermDTO term = TermDTO.get(request, termId);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(new Date());
    dto.setIsMDSSUser(true);
    dto.setUsername("Test2");
    dto.setPassword("test2");
    dto.setIsIPTRecipient(true);
    dto.setIsITNRecipient(true);
    dto.setIsPatient(true);
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setLeaderId("1434343");
    dto.setOperatorId("1434343a");
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO personDTO = PersonDTO.get(request, dto.getPersonId());
      MDSSUserDTO user = personDTO.getUserDelegate();

      user.updateRoles(new String[] { MDSSRoleInfo.ENTOMOLOGIST }, new String[] {});
      user.updateRoles(new String[] {}, new String[] { MDSSRoleInfo.ENTOMOLOGIST });

      fail("Able to update GUIVisibility permissions");
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
}
