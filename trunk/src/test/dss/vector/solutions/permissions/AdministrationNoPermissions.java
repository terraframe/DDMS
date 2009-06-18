package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
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
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.intervention.monitor.DoseGridDTO;
import dss.vector.solutions.intervention.monitor.PatientGridDTO;
import dss.vector.solutions.intervention.monitor.VisitGridDTO;
import dss.vector.solutions.mo.ActiveIngredientDTO;
import dss.vector.solutions.mo.CollectionMethodDTO;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.InfectivityMethodologyDTO;
import dss.vector.solutions.mo.LarvaeAgeDTO;
import dss.vector.solutions.mo.MolecularAssayResultDTO;
import dss.vector.solutions.mo.ResistanceMethodologyDTO;
import dss.vector.solutions.mo.SpecieDTO;
import dss.vector.solutions.surveillance.DiagnosticGridDTO;
import dss.vector.solutions.surveillance.ReferralGridDTO;
import dss.vector.solutions.surveillance.TreatmentGridDTO;
import dss.vector.solutions.surveillance.TreatmentMethodGridDTO;
import dss.vector.solutions.util.ReadableAttributeViewDTO;

public abstract class AdministrationNoPermissions extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  public void testUpdateProperty()
  {
    try
    {
      PropertyDTO property = PropertyDTO.getByPackageAndName(request, PropertyInfo.RESISTANCE_PACKAGE,
          PropertyInfo.ADULT_DDA_RESISTANCE);
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

  public void testActiveIngredient()
  {
    try
    {
      ActiveIngredientDTO dto = new ActiveIngredientDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testCollectionMethod()
  {
    try
    {
      CollectionMethodDTO dto = new CollectionMethodDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testGeneration()
  {
    try
    {
      GenerationDTO dto = new GenerationDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testIdentificationMethod()
  {
    try
    {
      IdentificationMethodDTO dto = new IdentificationMethodDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testInfectivityMethodologies()
  {
    try
    {
      InfectivityMethodologyDTO dto = new InfectivityMethodologyDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testLarvaeAges()
  {
    try
    {
      LarvaeAgeDTO dto = new LarvaeAgeDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testMolecularAssayResult()
  {
    try
    {
      MolecularAssayResultDTO dto = new MolecularAssayResultDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testResistanceMethodology()
  {
    try
    {
      ResistanceMethodologyDTO dto = new ResistanceMethodologyDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testSpecie()
  {
    try
    {
      SpecieDTO dto = new SpecieDTO(request);
      dto.setTermName("Test Term");
      dto.setTermId("34243");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testDiagnosticGrid()
  {
    try
    {
      DiagnosticGridDTO dto = new DiagnosticGridDTO(request);
      dto.setOptionName("Test Term");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testTreatmentGrid()
  {
    try
    {
      TreatmentGridDTO dto = new TreatmentGridDTO(request);
      dto.setOptionName("Test Term");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testTreatmentMethodGrid()
  {
    try
    {
      TreatmentMethodGridDTO dto = new TreatmentMethodGridDTO(request);
      dto.setOptionName("Test Term");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testReferralGrid()
  {
    try
    {
      ReferralGridDTO dto = new ReferralGridDTO(request);
      dto.setOptionName("Test Term");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testDoseGrid()
  {
    try
    {
      DoseGridDTO dto = new DoseGridDTO(request);
      dto.setOptionName("Test Term");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testVisitGrid()
  {
    try
    {
      VisitGridDTO dto = new VisitGridDTO(request);
      dto.setOptionName("Test Term");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testPatientGrid()
  {
    try
    {
      PatientGridDTO dto = new PatientGridDTO(request);
      dto.setOptionName("Test Term");
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testGUIVisibility()
  {
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    AttributeDateMdDTO attributeMd = dto.getDateCollectedMd();

    try
    {

      ReadableAttributeViewDTO view = new ReadableAttributeViewDTO(request);
      view.setAttributeName(attributeMd.getName());
      view.setDisplayLabel("Test");
      view.setReadPermission(false);
      view.setAttributeDescription("Desc");

      ReadableAttributeViewDTO.setActorAttributes(request, MosquitoCollectionDTO.CLASS,
          MDSSRoleInfo.GUI_VISIBILITY, new ReadableAttributeViewDTO[] { view });

      fail("Able to create a new Mo term");
    }
    catch (ClientReadAttributePermissionException e)
    {
      // This is expected
    }
  }

  public void testRoleAssignment()
  {
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
