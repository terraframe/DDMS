package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.entomology.AssaySexDTO;
import dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO;
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

public abstract class EntomologyNoPermissions extends TestCase implements DoNotWeave
{
  protected static ClientSession   clientSession;

  protected static ClientRequestIF request;

  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  protected static String          siteId;

  protected static String          waterId;

  public void testMosqutioCollection()
  {
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(request);

    try
    {
      MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
      dto.setCollectionMethod(methods[0]);
      dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
      dto.setDateCollected(new Date());
      dto.apply();

      dto.delete();
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testMorphologicalSpecieGroup()
  {
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(request);
    IdentificationMethodDTO[] identifications = IdentificationMethodDTO.getAll(request);
    SpecieDTO[] species = SpecieDTO.getAll(request);

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
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
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
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
    try
    {
      MosquitoCollectionPointViewDTO dto = new MosquitoCollectionPointViewDTO(request);
      dto.setGeoEntity(geoEntity);
      dto.setDateCollected(date);
      dto.setIdentificationMethod(methods[0]);
      dto.setSpecie(species[0]);
      dto.setQuantity(24);
      dto.apply();
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
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
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
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
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
    finally
    {
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
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();

    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();

    try
    {
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
    }
    catch (CreatePermissionExceptionDTO e)
    {

    }
    finally
    {
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
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();

    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();

    try
    {
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
    }
    catch (CreatePermissionExceptionDTO e)
    {

    }
    finally
    {
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
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(methods[0]);
    dto.setGeoEntity(GeoEntityDTO.get(request, siteId));
    dto.setDateCollected(new Date());
    dto.apply();

    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();

    try
    {
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
    }
    catch (CreatePermissionExceptionDTO e)
    {

    }
    finally
    {
      insecticide.delete();
      dto.delete();
    }
  }

  public void testLTP()
  {
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);

    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();

    try
    {
      LethalTimePropertyDTO dto = new LethalTimePropertyDTO(request);
      dto.setInsecticide(insecticide);
      dto.setLowerPercent(50);
      dto.setLowerTime(30);
      dto.setUpperPercent(95);
      dto.setUpperTime(50);
      dto.apply();
    }
    catch (CreatePermissionExceptionDTO e)
    {
    }
    finally
    {
      insecticide.delete();
    }
  }

  public void testKDTP()
  {
    ActiveIngredientDTO[] ingredients = ActiveIngredientDTO.getAll(request);

    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
    insecticide.setActiveIngredient(ingredients[0]);
    insecticide.setAmount(new Double(30.0));
    insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
    insecticide.apply();

    try
    {
      KnockDownTimePropertyDTO dto = new KnockDownTimePropertyDTO(request);
      dto.setInsecticide(insecticide);
      dto.setLowerPercent(50);
      dto.setLowerTime(30);
      dto.setUpperPercent(95);
      dto.setUpperTime(50);
      dto.apply();
    }
    catch(CreatePermissionExceptionDTO e)
    {
      
    }
    finally
    {
      insecticide.delete();
    }
  }

}
