package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
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

public abstract class AdministrationCRUDPermissions extends TestCase
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  public void testUpdateProperty()
  {
    PropertyDTO property = PropertyDTO.getByPackageAndName(request, PropertyInfo.RESISTANCE_PACKAGE,
        PropertyInfo.ADULT_DDA_RESISTANCE);
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
    dto.setSeasonName("Test Season");
    dto.setStartDate(new Date());
    dto.setEndDate(new Date());
    dto.apply();

    try
    {
      dto.lock();
      dto.setSeasonName("Test Season 2");
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

  public void testActiveIngredient()
  {
    ActiveIngredientDTO dto = new ActiveIngredientDTO(request);
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setTermName("Test Term");
    dto.setTermId("34243");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setTermName("Test Term1");
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
    dto.setOptionName("Test Term");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName("Test Term1");
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
    dto.setOptionName("Test Term");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName("Test Term1");
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
    dto.setOptionName("Test Term");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName("Test Term1");
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
    dto.setOptionName("Test Term");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName("Test Term1");
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
    dto.setOptionName("Test Term");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName("Test Term1");
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
    dto.setOptionName("Test Term");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName("Test Term1");
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
    dto.setOptionName("Test Term");
    dto.getDisplayLabel().setDefaultLocale("Test Label");
    dto.apply();

    try
    {
      dto.lock();
      dto.setOptionName("Test Term1");
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


  public void testGUIVisibility()
  {
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    AttributeDateMdDTO attributeMd = dto.getDateCollectedMd();
    
    ReadableAttributeViewDTO view = new ReadableAttributeViewDTO(request);
    view.setAttributeName(attributeMd.getName());
    view.setDisplayLabel("Test");
    view.setReadPermission(false);
    view.setAttributeDescription("Desc");
    
    ReadableAttributeViewDTO.setActorAttributes(request, MosquitoCollectionDTO.CLASS, MDSSRoleInfo.GUI_VISIBILITY, new ReadableAttributeViewDTO[]{view});    

    view = new ReadableAttributeViewDTO(request);
    view.setAttributeName(attributeMd.getName());
    view.setDisplayLabel(attributeMd.getDisplayLabel());
    view.setReadPermission(true);
    view.setAttributeDescription(attributeMd.getDescription());
    
    ReadableAttributeViewDTO.setActorAttributes(request, MosquitoCollectionDTO.CLASS, MDSSRoleInfo.GUI_VISIBILITY, new ReadableAttributeViewDTO[]{view});    
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
      
      user.updateRoles(new String[]{MDSSRoleInfo.GUI_VISIBILITY}, new String[]{});
      user.updateRoles(new String[]{}, new String[]{MDSSRoleInfo.GUI_VISIBILITY});
    }
    finally
    {
      PersonDTO.lock(request, dto.getPersonId()).delete();
    }

  }
}
