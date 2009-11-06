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

    property.setPropertyValue("99");
    property.apply();

    property = PropertyDTO.lock(systemRequest, property.getId());
    property.setPropertyValue(value);
    property.apply();
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

  /* FIXME MO REFACTOR
  public void testActiveIngredient()
  {
    ActiveIngredientDTO dto = new ActiveIngredientDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      ActiveIngredientDTO test = ActiveIngredientDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testCollectionMethod()
  {
    CollectionMethodDTO dto = new CollectionMethodDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      CollectionMethodDTO test = CollectionMethodDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }

  }

  public void testGeneration()
  {
    GenerationDTO dto = new GenerationDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      GenerationDTO test = GenerationDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }

  }

  public void testIdentificationMethod()
  {
    IdentificationMethodDTO dto = new IdentificationMethodDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      IdentificationMethodDTO test = IdentificationMethodDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testInfectivityMethodologies()
  {
    InfectivityMethodologyDTO dto = new InfectivityMethodologyDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      InfectivityMethodologyDTO test = InfectivityMethodologyDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }

  }

  public void testLarvaeAges()
  {
    LarvaeAgeDTO dto = new LarvaeAgeDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      LarvaeAgeDTO test = LarvaeAgeDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testMolecularAssayResult()
  {
    MolecularAssayResultDTO dto = new MolecularAssayResultDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      MolecularAssayResultDTO test = MolecularAssayResultDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }

  }

  public void testResistanceMethodology()
  {
    ResistanceMethodologyDTO dto = new ResistanceMethodologyDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      ResistanceMethodologyDTO test = ResistanceMethodologyDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testSpecie()
  {
    SpecieDTO dto = new SpecieDTO(request);
    dto.setTermName(TestConstants.TERM_NAME);
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName(TestConstants.TERM_NAME_2);
      dto.setTermId("3424343");
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      SpecieDTO test = SpecieDTO.get(request, dto.getId());
      assertEquals(dto.getTermName(), test.getTermName());
      assertEquals(dto.getTermId(), test.getTermId());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }


  public void testDiagnosticGrid()
  {
    DiagnosticGridDTO dto = new DiagnosticGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      DiagnosticGridDTO test = DiagnosticGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testTreatmentGrid()
  {
    TreatmentGridDTO dto = new TreatmentGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      TreatmentGridDTO test = TreatmentGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testTreatmentMethodGrid()
  {
    TreatmentMethodGridDTO dto = new TreatmentMethodGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      TreatmentMethodGridDTO test = TreatmentMethodGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testReferralGrid()
  {
    ReferralGridDTO dto = new ReferralGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      ReferralGridDTO test = ReferralGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testDoseGrid()
  {
    DoseGridDTO dto = new DoseGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      DoseGridDTO test = DoseGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testVisitGrid()
  {
    VisitGridDTO dto = new VisitGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      VisitGridDTO test = VisitGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testPatientGrid()
  {
    PatientGridDTO dto = new PatientGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      PatientGridDTO test = PatientGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testNetGrid()
  {
    NetDTO dto = new NetDTO(request);
    dto.setNetName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      NetDTO child = new NetDTO(request);

      try
      {
        child.setNetName("Test Net");
        child.getDisplayLabel().setDefaultLocale("Test Label");
        child.setParentNet(dto);
        child.apply();
      }
      finally
      {
        if (!child.isNewInstance())
        {
          child.delete();
        }
      }

      dto.lock();
      dto.setNetName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      NetDTO test = NetDTO.get(request, dto.getId());
      assertEquals(dto.getNetName(), test.getNetName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testRoofGrid()
  {
    RoofDTO dto = new RoofDTO(request);
    dto.setRoofName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      RoofDTO child = new RoofDTO(request);

      try
      {
        child.setRoofName(TestConstants.TERM_NAME_3);
        child.getDisplayLabel().setDefaultLocale("Test Label");
        child.setParentRoof(dto);
        child.apply();
      }
      catch (Exception e)
      {
        fail(e.getLocalizedMessage());
      }
      finally
      {
        if (!child.isNewInstance())
        {
          child.delete();
        }
      }

      dto.lock();
      dto.setRoofName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      RoofDTO test = RoofDTO.get(request, dto.getId());
      assertEquals(dto.getRoofName(), test.getRoofName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }

  }

  public void testWallGrid()
  {
    WallDTO dto = new WallDTO(request);
    dto.setWallName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      WallDTO child = new WallDTO(request);

      try
      {
        child.setWallName(TestConstants.TERM_NAME_3);
        child.getDisplayLabel().setDefaultLocale("Test Label");
        child.setParentWall(dto);
        child.apply();
      }
      catch (Exception e)
      {
        fail(e.getLocalizedMessage());
      }
      finally
      {
        if (!child.isNewInstance())
        {
          child.delete();
        }
      }

      dto.lock();
      dto.setWallName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      WallDTO test = WallDTO.get(request, dto.getId());
      assertEquals(dto.getWallName(), test.getWallName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
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

  public void testFeverTreatmentGrid()
  {
    FeverTreatmentDTO dto = new FeverTreatmentDTO(request);
    dto.setTreatmentName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTreatmentName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      FeverTreatmentDTO test = FeverTreatmentDTO.get(request, dto.getId());
      assertEquals(dto.getTreatmentName(), test.getTreatmentName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }

  }
  
  public void testServiceGrid()
  {
    ServiceGridDTO dto = new ServiceGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      ServiceGridDTO test = ServiceGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }

  public void testTargetGroupGrid()
  {
    TargetGroupGridDTO dto = new TargetGroupGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      TargetGroupGridDTO test = TargetGroupGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
  }
  
  public void testNonUseReasonGrid()
  {
    NonUseReasonGridDTO dto = new NonUseReasonGridDTO(request);
    dto.setOptionName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      NonUseReasonGridDTO test = NonUseReasonGridDTO.get(request, dto.getId());
      assertEquals(dto.getOptionName(), test.getOptionName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }
    
  }
  
  public void testITNCommercialProvider()
  {
    CommercialITNProviderDTO dto = new CommercialITNProviderDTO(request);
    dto.setProviderName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setProviderName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      CommercialITNProviderDTO test = CommercialITNProviderDTO.get(request, dto.getId());
      assertEquals(dto.getProviderName(), test.getProviderName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }    
  }
  
  public void testITNFreeProvider()
  {
    FreeITNProviderDTO dto = new FreeITNProviderDTO(request);
    dto.setProviderName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setProviderName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      FreeITNProviderDTO test = FreeITNProviderDTO.get(request, dto.getId());
      assertEquals(dto.getProviderName(), test.getProviderName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }    
  }
  
  public void testITNRetreatmentPeriod()
  {
    ITNRetreatmentPeriodDTO dto = new ITNRetreatmentPeriodDTO(request);
    dto.setPeriodName(TestConstants.TERM_NAME);
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setPeriodName(TestConstants.TERM_NAME_2);
      dto.getDisplayLabel().setDefaultLocale("Test Label 2");
      dto.apply();

      ITNRetreatmentPeriodDTO test = ITNRetreatmentPeriodDTO.get(request, dto.getId());
      assertEquals(dto.getPeriodName(), test.getPeriodName());
      assertEquals(dto.getDisplayLabel().getDefaultLocale(), test.getDisplayLabel().getDefaultLocale());
    }
    finally
    {
      dto.delete();
    }    
  }
   */

  public void testGUIVisibility()
  {
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    AttributeDateMdDTO attributeMd = dto.getDateCollectedMd();

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

      ReadableAttributeViewDTO.setActorAttributes(request, MosquitoCollectionDTO.CLASS, MDSSRoleInfo.GUI_VISIBILITY, new ReadableAttributeViewDTO[] { view });
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
      update.setIsMDSSUser(true);
      update.setUsername("Test2");
      update.setPassword("test2");
      update.setIsIPTRecipient(true);
      update.setIsITNRecipient(true);
      update.setIsPatient(true);
      update.setIsSprayLeader(true);
      update.setIsSprayOperator(true);
      update.setLeaderId(TestConstants.LEADER_ID);
      update.setOperatorId(TestConstants.OPERATOR_ID);
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
