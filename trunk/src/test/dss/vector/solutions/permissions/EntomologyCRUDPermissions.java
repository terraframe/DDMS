package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.entomology.AssaySexDTO;
import dss.vector.solutions.entomology.MorphologicalSpecieGroupDTO;
import dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO;
import dss.vector.solutions.entomology.MosquitoDTO;
import dss.vector.solutions.entomology.MosquitoViewDTO;
import dss.vector.solutions.entomology.SexDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.UnitDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.general.KnockDownTimePropertyDTO;
import dss.vector.solutions.general.LethalTimePropertyDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.mo.ActiveIngredientDTO;
import dss.vector.solutions.mo.CollectionMethodDTO;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.InfectivityMethodologyDTO;
import dss.vector.solutions.mo.InsecticideMethodologyDTO;
import dss.vector.solutions.mo.MolecularAssayResultDTO;
import dss.vector.solutions.mo.SpecieDTO;

public abstract class EntomologyCRUDPermissions extends TestCase implements DoNotWeave
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static String          siteId;

  protected static String          waterId;

  public void testMosqutioCollection()
  {
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(request);

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      // Update the mosquito collection
      dto.lock();

      dto.setCollectionMethod(methods[1]);
      dto.apply();

      // Read the mosquito collection
      MosquitoCollectionDTO test = MosquitoCollectionDTO.get(request, dto.getId());

      assertEquals(dto.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(dto.getCollectionMethod().getId(), test.getCollectionMethod().getId());
      assertEquals(dto.getDateCollected(), test.getDateCollected());
      assertEquals(dto.getCollectionId(), test.getCollectionId());
    }
    finally
    {
      // delete the mosquito collection
      dto.delete();
    }
  }

  public void testMorphologicalSpecieGroup()
  {
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(request);
    IdentificationMethodDTO[] identifications = IdentificationMethodDTO.getAll(request);
    SpecieDTO[] species = SpecieDTO.getAll(request);

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      // Create the specie group
      MorphologicalSpecieGroupViewDTO view = new MorphologicalSpecieGroupViewDTO(request);
      view.setSpecie(species[0]);
      view.setIdentificationMethod(identifications[0]);
      view.setQuantity(5);
      view.setQuantityFemale(2);
      view.setQuantityMale(3);
      view.setCollection(dto);
      view.apply();

      try
      {
        // Update the mosquito collection
        MorphologicalSpecieGroupViewDTO update = MorphologicalSpecieGroupDTO.lockView(request, view.getGroupId());
        update.setQuantity(10);
        update.setQuantityFemale(5);
        update.setQuantityMale(5);
        update.apply();

        // Read the mosquito collection
        MorphologicalSpecieGroupViewDTO test = MorphologicalSpecieGroupDTO.getView(request, view.getGroupId());

        assertEquals(update.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
        assertEquals(update.getSpecie().getId(), test.getSpecie().getId());
        assertEquals(update.getQuantity(), test.getQuantity());
        assertEquals(update.getQuantityFemale(), test.getQuantityFemale());
        assertEquals(update.getQuantityMale(), test.getQuantityMale());
      }
      finally
      {
        view.deleteConcrete();
      }
    }
    finally
    {
      // delete the mosquito collection
      dto.delete();
    }
  }

  public void testMosqutioCollectionPoint()
  {
    Date date = new Date();
    GeoEntityDTO geoEntity = GeoEntityDTO.get(request, waterId);
    IdentificationMethodDTO[] methods = IdentificationMethodDTO.getAll(request);
    SpecieDTO[] species = SpecieDTO.getAll(request);

    // Create the mosquito collection
    MosquitoCollectionPointViewDTO dto = new MosquitoCollectionPointViewDTO(request);
    dto.setGeoEntity(geoEntity);
    dto.setDateCollected(date);
    dto.setIdentificationMethod(methods[0]);
    dto.setSpecie(species[0]);
    dto.setQuantity(24);
    dto.apply();

    try
    {
      // Update the mosquito collection
      MosquitoCollectionPointViewDTO update = MosquitoCollectionPointViewDTO.lockView(request, dto.getGroupId());
      update.setQuantity(10);
      update.setQuantityFemale(5);
      update.setQuantityMale(5);
      update.apply();

      // Read the mosquito collection
      MosquitoCollectionPointViewDTO test = MosquitoCollectionPointViewDTO.getView(request, dto.getGroupId());

      assertEquals(update.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(update.getDateCollected(), test.getDateCollected());
      assertEquals(update.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
      assertEquals(update.getSpecie().getId(), test.getSpecie().getId());
      assertEquals(update.getQuantity(), test.getQuantity());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        dto.getCollection().delete();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void testMosquito()
  {
    Date date = new Date();
    IdentificationMethodDTO[] identification = IdentificationMethodDTO.getAll(request);
    SpecieDTO[] species = SpecieDTO.getAll(request);
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(request);
    GenerationDTO[] generations = GenerationDTO.getAll(request);
    MolecularAssayResultDTO[] resutls = MolecularAssayResultDTO.getAll(request);
    InsecticideMethodologyDTO[] insecticide = InsecticideMethodologyDTO.getAll(request);
    InfectivityMethodologyDTO[] infectivity = InfectivityMethodologyDTO.getAll(request);
    
    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();
    
    MosquitoViewDTO view = new MosquitoViewDTO(request);
    view.setSpecie(species[0]);
    view.setCollection(dto);
    view.setGeneration(generations[0]);
    view.setIsofemale(false);
    view.setSampleId("0");
    view.setIdentificationMethod(identification[0]);
    view.addSex(SexDTO.FEMALE);
    view.setTestDate(date);
    view.setP450(true);
    view.setIAcHE(resutls[0]);
    view.setIAcHEMethod(insecticide[0]);
    view.setAAcetate(false);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivity[0]);
    view.apply();

    try
    {
      MosquitoViewDTO update = MosquitoDTO.lockView(request, view.getMosquitoId());
      update.setSpecie(species[1]);
      update.setGeneration(generations[1]);
      update.setIdentificationMethod(identification[1]);
      update.setP450(false);
      update.setIAcHE(resutls[1]);
      update.setIAcHEMethod(insecticide[1]);
      update.setAAcetate(true);
      update.setPMalariae(false);
      update.setPMalariaeMethod(infectivity[1]);
      update.apply();
      
      MosquitoViewDTO test = MosquitoDTO.getView(request, view.getMosquitoId());
      
      assertEquals(update.getSpecie().getId(), test.getSpecie().getId());
      assertEquals(update.getGeneration().getId(), test.getGeneration().getId());
      assertEquals(update.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
      assertEquals(update.getSex().get(0), test.getSex().get(0));
      assertEquals(update.getTestDate(), test.getTestDate());
      assertEquals(update.getIsofemale(), test.getIsofemale());
      assertEquals(update.getP450(), test.getP450());
      assertEquals(update.getIAcHE().getId(), test.getIAcHE().getId());
      assertEquals(update.getIAcHEMethod().getId(), test.getIAcHEMethod().getId());
      assertEquals(update.getAAcetate(), test.getAAcetate());
      assertEquals(update.getPMalariae(), test.getPMalariae());
      assertEquals(update.getPMalariaeMethod().getId(), test.getPMalariaeMethod().getId());
      assertNull(update.getEKDR());
    }
    finally
    {
      view.deleteConcrete();
      dto.delete();
    }
  }
  
  public void testADA()
  {
    Date date = new Date();
    IdentificationMethodDTO[] identification = IdentificationMethodDTO.getAll(request);
    SpecieDTO[] species = SpecieDTO.getAll(request);
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(request);
    GenerationDTO[] generations = GenerationDTO.getAll(request);
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);
    
    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();  
    
    InsecticideDTO insecticide = new InsecticideDTO(request);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();
    
    AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(request);
    assay.addSex(AssaySexDTO.MIXED);
    assay.setCollection(dto);
    assay.setControlTestMortality(2.5F);
    assay.setFed(30);
    assay.setGravid(30);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(generations[0]);
    assay.setIdentificationMethod(identification[0]);
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(60);
    assay.setQuantityLive(30);
    assay.setSpecie(species[0]);
    assay.setTestDate(date);
    assay.setInsecticide(insecticide);
    assay.apply();
    
    try
    {
      assay.lock();
      assay.setIsofemale(false);
      assay.setQuantityDead(300);
      assay.setQuantityTested(600);
      assay.setQuantityLive(300);
      assay.setSpecie(species[1]);
      assay.setTestDate(date);
      assay.setInsecticide(insecticide);
      assay.apply();
      
      AdultDiscriminatingDoseAssayDTO test = AdultDiscriminatingDoseAssayDTO.get(request, assay.getId());
      
      assertEquals(assay.getSpecie().getId(), test.getSpecie().getId());
      assertEquals(assay.getGeneration().getId(), test.getGeneration().getId());
      assertEquals(assay.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
      assertEquals(assay.getSex().get(0), test.getSex().get(0));
      assertEquals(assay.getTestDate(), test.getTestDate());
    }
    finally
    {
      assay.delete();
      insecticide.delete();
      dto.delete();
    }
  }

  public void testLDA()
  {
    Date date = new Date();
    IdentificationMethodDTO[] identification = IdentificationMethodDTO.getAll(request);
    SpecieDTO[] species = SpecieDTO.getAll(request);
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(request);
    GenerationDTO[] generations = GenerationDTO.getAll(request);
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);
    
    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();  
    
    InsecticideDTO insecticide = new InsecticideDTO(request);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();
    
    LarvaeDiscriminatingDoseAssayDTO assay = new LarvaeDiscriminatingDoseAssayDTO(request);
    assay.setCollection(dto);
    assay.setControlTestMortality(2.5F);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(generations[0]);
    assay.setIdentificationMethod(identification[0]);
    assay.setIsofemale(false);
    assay.setQuantityDead(30);
    assay.setQuantityTested(60);
    assay.setQuantityLive(30);
    assay.setSpecie(species[0]);
    assay.setTestDate(date);
    assay.setInsecticide(insecticide);
    assay.apply();
    
    try
    {
      assay.lock();
      assay.setIsofemale(false);
      assay.setQuantityDead(300);
      assay.setQuantityTested(600);
      assay.setQuantityLive(300);
      assay.setSpecie(species[1]);
      assay.setTestDate(date);
      assay.setInsecticide(insecticide);
      assay.apply();
      
      LarvaeDiscriminatingDoseAssayDTO test = LarvaeDiscriminatingDoseAssayDTO.get(request, assay.getId());
      
      assertEquals(assay.getSpecie().getId(), test.getSpecie().getId());
      assertEquals(assay.getGeneration().getId(), test.getGeneration().getId());
      assertEquals(assay.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
      assertEquals(assay.getTestDate(), test.getTestDate());
    }
    finally
    {
      assay.delete();
      insecticide.delete();
      dto.delete();
    }    
  }

  public void testKDA()
  {
    Date date = new Date();
    IdentificationMethodDTO[] identification = IdentificationMethodDTO.getAll(request);
    SpecieDTO[] species = SpecieDTO.getAll(request);
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(request);
    GenerationDTO[] generations = GenerationDTO.getAll(request);
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);
    
    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();  
    
    InsecticideDTO insecticide = new InsecticideDTO(request);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();
    
    KnockDownAssayDTO assay = new KnockDownAssayDTO(request);
    assay.addSex(AssaySexDTO.MIXED);
    assay.setCollection(dto);
    assay.setFed(30);
    assay.setGravid(30);
    assay.setExposureTime(60);
    assay.setIntervalTime(10);
    assay.setGeneration(generations[0]);
    assay.setIdentificationMethod(identification[0]);
    assay.setIsofemale(false);
    assay.setQuantityTested(60);
    assay.setSpecie(species[0]);
    assay.setTestDate(date);
    assay.setInsecticide(insecticide);
    assay.apply();
    
    try
    {
      assay.lock();
      assay.setIsofemale(false);
      assay.setQuantityTested(600);
      assay.setSpecie(species[1]);
      assay.setTestDate(date);
      assay.setInsecticide(insecticide);
      assay.apply();
      
      KnockDownAssayDTO test = KnockDownAssayDTO.get(request, assay.getId());
      
      assertEquals(assay.getSpecie().getId(), test.getSpecie().getId());
      assertEquals(assay.getGeneration().getId(), test.getGeneration().getId());
      assertEquals(assay.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
      assertEquals(assay.getSex().get(0), test.getSex().get(0));
      assertEquals(assay.getTestDate(), test.getTestDate());
    }
    finally
    {
      assay.delete();
      insecticide.delete();
      dto.delete();
    }    
  }

  public void testLTP()
  {
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);

    InsecticideDTO insecticide = new InsecticideDTO(request);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();

    LethalTimePropertyDTO dto = new LethalTimePropertyDTO(request);
    dto.setInsecticide(insecticide);
    dto.setLowerPercent(50);
    dto.setLowerTime(30);
    dto.setUpperPercent(95);
    dto.setUpperTime(50);
    dto.apply();
    
    try
    {
      dto.lock();
      dto.setLowerPercent(55);
      dto.setLowerTime(35);
      dto.apply();
      
      LethalTimePropertyDTO test = LethalTimePropertyDTO.get(request, dto.getId());
      
      assertEquals(dto.getInsecticide().getId(), test.getInsecticide().getId());
      assertEquals(dto.getLowerPercent(), test.getLowerPercent());
      assertEquals(dto.getLowerTime(), test.getLowerTime());
      assertEquals(dto.getUpperPercent(), test.getUpperPercent());
      assertEquals(dto.getUpperTime(), test.getUpperTime());
    }
    finally
    {
      dto.delete();
      insecticide.delete();
    }
  }
  
  public void testKDTP()
  {
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);

    InsecticideDTO insecticide = new InsecticideDTO(request);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();

    KnockDownTimePropertyDTO dto = new KnockDownTimePropertyDTO(request);
    dto.setInsecticide(insecticide);
    dto.setLowerPercent(50);
    dto.setLowerTime(30);
    dto.setUpperPercent(95);
    dto.setUpperTime(50);
    dto.apply();
    
    try
    {
      dto.lock();
      dto.setLowerPercent(55);
      dto.setLowerTime(35);
      dto.apply();
      
      KnockDownTimePropertyDTO test = KnockDownTimePropertyDTO.get(request, dto.getId());
      
      assertEquals(dto.getInsecticide().getId(), test.getInsecticide().getId());
      assertEquals(dto.getLowerPercent(), test.getLowerPercent());
      assertEquals(dto.getLowerTime(), test.getLowerTime());
      assertEquals(dto.getUpperPercent(), test.getUpperPercent());
      assertEquals(dto.getUpperTime(), test.getUpperTime());
    }
    finally
    {
      dto.delete();
      insecticide.delete();
    }    
  }
}
