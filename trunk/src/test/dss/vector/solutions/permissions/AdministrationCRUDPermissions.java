package dss.vector.solutions.permissions;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.transport.metadata.AttributeDateMdDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.MDSSUserDTO;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.PropertyDTO;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.util.ReadableAttributeViewDTO;

public class AdministrationCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(AdministrationCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR);
  }

  public void testUpdateProperty()
  {
    PropertyDTO property = PropertyDTO.getByPackageAndName(request, PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.ADULT_DDA_RESISTANCE);
    property.lock();

    String value = property.getPropertyValue();

    try
    {
      property.setPropertyValue("99");
      property.apply();
    }
    finally
    {

      property = PropertyDTO.lock(systemRequest, property.getId());
      property.setPropertyValue(value);
      property.apply();
    }
  }

  public void testCreateMalariaSeason()
  {
    MalariaSeasonDTO dto = new MalariaSeasonDTO(request);
    dto.setSeasonName(TestConstants.SEASON_NAME);
    dto.setStartDate(new Date());
    dto.setEndDate(new Date());
    dto.apply();

    try
    {
      dto.lock();
      dto.setSeasonName(TestConstants.SEASON_NAME_2);
      dto.apply();

      MalariaSeasonDTO test = MalariaSeasonDTO.get(request, dto.getId());
      assertEquals(dto.getSeasonName(), test.getSeasonName());
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
      dto.delete();
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
    }
    catch (Exception e)
    {
      fail(e.getLocalizedMessage());
    }
    finally
    {
      AggregatedAgeGroupDTO system = AggregatedAgeGroupDTO.lock(systemRequest, dto.getId());
      system.setStartAge(startAge);
      system.setEndAge(endAge);
      system.apply();
    }
  }

  public void testGUIVisibility()
  {
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    AttributeDateMdDTO attributeMd = dto.getCollectionDateMd();

    ReadableAttributeViewDTO view = new ReadableAttributeViewDTO(request);
    view.setAttributeName(attributeMd.getName());
    view.setDisplayLabel("Test");
    view.setReadPermission(false);
    view.setAttributeDescription("Desc");

    try
    {
      ReadableAttributeViewDTO.setActorAttributes(request, MosquitoCollectionDTO.CLASS, MDSSRoleInfo.GUI_VISIBILITY, new ReadableAttributeViewDTO[] { view });
    }
    finally
    {
      view = new ReadableAttributeViewDTO(request);
      view.setAttributeName(attributeMd.getName());
      view.setDisplayLabel(attributeMd.getDisplayLabel());
      view.setReadPermission(true);
      view.setAttributeDescription(attributeMd.getDescription());

      ReadableAttributeViewDTO.setActorAttributes(systemRequest, MosquitoCollectionDTO.CLASS, MDSSRoleInfo.GUI_VISIBILITY, new ReadableAttributeViewDTO[] { view });
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
    dto.setMemberId("1434343");
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonDTO personDTO = PersonDTO.get(request, dto.getPersonId());
      MDSSUserDTO user = personDTO.getUserDelegate();

      user.updateRoles(new String[] { MDSSRoleInfo.GUI_VISIBILITY }, new String[] {});
      user.updateRoles(new String[] {}, new String[] { MDSSRoleInfo.GUI_VISIBILITY });
    }
    finally
    {
      PersonDTO.lock(request, dto.getPersonId()).delete();
    }

  }

  public void testUpdateUser()
  {
    TermDTO term = TermDTO.get(request, termId);

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 1983);
    calendar.set(Calendar.MONTH, 5);
    calendar.set(Calendar.DAY_OF_YEAR, 11);

    PersonViewDTO dto = new PersonViewDTO(systemRequest);
    dto.setFirstName("Test");
    dto.setLastName("Test");
    dto.setDateOfBirth(calendar.getTime());
    dto.setIsMDSSUser(true);
    dto.setUsername("Test2");
    dto.setPassword("test2");
    dto.setIsIPTRecipient(true);
    dto.setIsITNRecipient(true);
    dto.setIsPatient(true);
    dto.setIsSprayLeader(true);
    dto.setIsSprayOperator(true);
    dto.setMemberId(TestConstants.LEADER_ID);
    dto.setSex(term);
    dto.apply();

    try
    {
      PersonViewDTO update = PersonDTO.lockView(request, dto.getPersonId());
      update.setFirstName("Test");
      update.setLastName("Test");
      update.setDateOfBirth(new Date());
      update.setIsMDSSUser(true);
      update.setUsername("Test2");
      update.setPassword("test2");
      update.setIsIPTRecipient(true);
      update.setIsITNRecipient(true);
      update.setIsPatient(true);
      update.setIsSprayLeader(true);
      update.setIsSprayOperator(true);
      update.setMemberId(TestConstants.LEADER_ID);
      update.apply();

      PersonViewDTO view = PersonDTO.getView(request, dto.getPersonId());

      assertEquals(update.getUsername(), view.getUsername());
    }
    finally
    {
      PersonDTO.lock(request, dto.getPersonId()).delete();
    }
  }
}
