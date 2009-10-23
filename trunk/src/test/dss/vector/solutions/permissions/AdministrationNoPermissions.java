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
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.intervention.FeverTreatmentDTO;
import dss.vector.solutions.intervention.monitor.CommercialITNProviderDTO;
import dss.vector.solutions.intervention.monitor.DoseGridDTO;
import dss.vector.solutions.intervention.monitor.FreeITNProviderDTO;
import dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDTO;
import dss.vector.solutions.intervention.monitor.NetDTO;
import dss.vector.solutions.intervention.monitor.NonUseReasonGridDTO;
import dss.vector.solutions.intervention.monitor.PatientGridDTO;
import dss.vector.solutions.intervention.monitor.RoofDTO;
import dss.vector.solutions.intervention.monitor.ServiceGridDTO;
import dss.vector.solutions.intervention.monitor.TargetGroupGridDTO;
import dss.vector.solutions.intervention.monitor.VisitGridDTO;
import dss.vector.solutions.intervention.monitor.WallDTO;
import dss.vector.solutions.mo.ActiveIngredientDTO;
import dss.vector.solutions.mo.CollectionMethodDTO;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.InfectivityMethodologyDTO;
import dss.vector.solutions.mo.LarvaeAgeDTO;
import dss.vector.solutions.mo.MolecularAssayResultDTO;
import dss.vector.solutions.mo.ResistanceMethodologyDTO;
import dss.vector.solutions.mo.SpecieDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.surveillance.DiagnosticGridDTO;
import dss.vector.solutions.surveillance.ReferralGridDTO;
import dss.vector.solutions.surveillance.TreatmentGridDTO;
import dss.vector.solutions.surveillance.TreatmentMethodGridDTO;
import dss.vector.solutions.util.ReadableAttributeViewDTO;

public class AdministrationNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(AdministrationNoPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.MANAGER, MDSSRoleInfo.OPERATIONAL_MANAGER);
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

  public void testFeverTreatment()
  {
    try
    {
      FeverTreatmentDTO dto = new FeverTreatmentDTO(request);
      dto.setTreatmentName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      dto.delete();

      fail("Able to create a new FeverTreatment");
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

      dto.delete();

      fail("Able to create a new Mo term");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testNetGrid()
  {
    try
    {
      NetDTO dto = new NetDTO(request);
      dto.setNetName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();
      dto.delete();

      fail("Able to create a new Net");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testRoofGrid()
  {
    try
    {
      RoofDTO dto = new RoofDTO(request);
      dto.setRoofName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      dto.delete();

      fail("Able to create a new Roof");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }

  }

  public void testWallGrid()
  {
    try
    {
      WallDTO dto = new WallDTO(request);
      dto.setWallName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      dto.delete();

      fail("Able to create a new Wall term");
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

  public void testServiceGrid()
  {
    try
    {
      ServiceGridDTO dto = new ServiceGridDTO(request);
      dto.setOptionName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      dto.delete();

      fail("Able to create a new Service Grid option");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testTargetGroupGrid()
  {
    try
    {
      TargetGroupGridDTO dto = new TargetGroupGridDTO(request);
      dto.setOptionName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();
      dto.delete();

      fail("Able to create a new Target Group Grid option");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testNonUseReasonGrid()
  {
    try
    {
      NonUseReasonGridDTO dto = new NonUseReasonGridDTO(request);
      dto.setOptionName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      dto.delete();

      fail("Able to create a new Non-use Reason Grid option");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testITNCommercialProvider()
  {
    try
    {
      CommercialITNProviderDTO dto = new CommercialITNProviderDTO(request);
      dto.setProviderName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      dto.delete();

      fail("Able to create a new Commercial ITN Provider");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testITNFreeProvider()
  {
    try
    {
      FreeITNProviderDTO dto = new FreeITNProviderDTO(request);
      dto.setProviderName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      dto.delete();

      fail("Able to create a new Free ITN Provider");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testITNRetreatmentPeriod()
  {
    try
    {
      ITNRetreatmentPeriodDTO dto = new ITNRetreatmentPeriodDTO(request);
      dto.setPeriodName(TestConstants.TERM_NAME);
      dto.getDisplayLabel().setDefaultLocale("Test Label");
      dto.apply();

      dto.delete();

      fail("Able to create a new ITN Re-treatment Period");
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
