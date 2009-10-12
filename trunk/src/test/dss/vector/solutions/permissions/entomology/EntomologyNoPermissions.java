package dss.vector.solutions.permissions.entomology;

import java.util.Date;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO;
import dss.vector.solutions.entomology.MosquitoViewDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.UnitDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.general.KnockDownTimePropertyDTO;
import dss.vector.solutions.general.LethalTimePropertyDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;

public abstract class EntomologyNoPermissions extends EntomologyPermissionTest implements DoNotWeave
{
  public void testMosqutioCollection()
  {  
    try
    {
      MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
      dto.setCollectionMethod(TermDTO.get(request, termId));
      dto.setGeoEntity(GeoEntityDTO.get(request, geoId));
      dto.setDateCollected(new Date());
      dto.apply();

      dto.delete();
      
      fail("Able to create a mosquito collection without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testMorphologicalSpecieGroup()
  {
    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.get(request, geoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      // Create the specie group
      MorphologicalSpecieGroupViewDTO view = new MorphologicalSpecieGroupViewDTO(request);
      view.setSpecie(TermDTO.get(request, termId));
      view.setIdentificationMethod(TermDTO.get(request, termId));
      view.setQuantity(5);
      view.setQuantityFemale(2);
      view.setQuantityMale(3);
      view.setCollection(dto);
      view.apply();
      
      fail("Able to create a morphological group without permissions");
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

    // Create the mosquito collection
    try
    {
      MosquitoCollectionPointViewDTO dto = new MosquitoCollectionPointViewDTO(request);
      dto.setGeoEntity(geoEntity);
      dto.setDateCollected(date);
      dto.setIdentificationMethod(TermDTO.get(request, termId));
      dto.setSpecie(TermDTO.get(request, termId));
      dto.setQuantity(24);
      dto.apply();
      
      fail("Able to create a mosquito collection point without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testMosquito()
  {
    Date date = new Date();

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.get(request, geoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      MosquitoViewDTO view = new MosquitoViewDTO(request);
      view.setSpecie(TermDTO.get(request, termId));
      view.setCollection(dto);
      view.setGeneration(TermDTO.get(request, termId));
      view.setIsofemale(false);
      view.setSampleId("0");
      view.setIdentificationMethod(TermDTO.get(request, termId));
      view.setSex(TermDTO.get(request, termId));
      view.setTestDate(date);
      view.setP450(true);
      view.setIAcHE(TermDTO.get(request, termId));
      view.setIAcHEMethod(TermDTO.get(request, termId));
      view.setAAcetate(false);
      view.setPMalariae(true);
      view.setPMalariaeMethod(TermDTO.get(request, termId));
      view.apply();
      
      fail("Able to create a mosquito without permissions");
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

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.get(request, geoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {

      InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
      insecticide.setAmount(new Double(30.0));
      insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
      insecticide.apply();

      try
      {
        AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(request);
        assay.setSex(TermDTO.get(request, termId));
        assay.setCollection(dto);
        assay.setControlTestMortality(2.5F);
        assay.setFed(30);
        assay.setGravid(30);
        assay.setExposureTime(60);
        assay.setIntervalTime(10);
        assay.setGeneration(TermDTO.get(request, termId));
        assay.setIdentificationMethod(TermDTO.get(request, termId));
        assay.setIsofemale(false);
        assay.setQuantityDead(30);
        assay.setQuantityTested(60);
        assay.setQuantityLive(30);
        assay.setSpecie(TermDTO.get(request, termId));
        assay.setTestDate(date);
        assay.setInsecticide(insecticide);
        assay.apply();
        
        fail("Able to create an adda without permissions");
      }
      catch (CreatePermissionExceptionDTO e)
      {

      }
      finally
      {
        insecticide.delete();
      }
    }
    finally
    {
      dto.delete();
    }
  }

  public void testLDA()
  {
    Date date = new Date();

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.get(request, geoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
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
        assay.setGeneration(TermDTO.get(request, termId));
        assay.setIdentificationMethod(TermDTO.get(request, termId));
        assay.setIsofemale(false);
        assay.setQuantityDead(30);
        assay.setQuantityTested(60);
        assay.setQuantityLive(30);
        assay.setSpecie(TermDTO.get(request, termId));
        assay.setTestDate(date);
        assay.setInsecticide(insecticide);
        assay.apply();
        
        fail("Able to create a ldda without permissions");
      }
      catch (CreatePermissionExceptionDTO e)
      {

      }
      finally
      {
        insecticide.delete();
      }
    }
    finally
    {
      dto.delete();
    }
  }

  public void testKDA()
  {
    Date date = new Date();

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(systemRequest);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.get(request, geoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
      insecticide.setAmount(new Double(30.0));
      insecticide.addUnits(UnitDTO.MICROGRAM_PER_LITER);
      insecticide.apply();

      try
      {
        KnockDownAssayDTO assay = new KnockDownAssayDTO(request);
        assay.setSex(TermDTO.get(request, termId));
        assay.setCollection(dto);
        assay.setFed(30);
        assay.setGravid(30);
        assay.setExposureTime(60);
        assay.setIntervalTime(10);
        assay.setGeneration(TermDTO.get(request, termId));
        assay.setIdentificationMethod(TermDTO.get(request, termId));
        assay.setIsofemale(false);
        assay.setQuantityTested(60);
        assay.setSpecie(TermDTO.get(request, termId));
        assay.setTestDate(date);
        assay.setInsecticide(insecticide);
        assay.apply();
        
        fail("Able to create a kda without permissions");
      }
      catch (CreatePermissionExceptionDTO e)
      {

      }
      finally
      {
        insecticide.delete();
      }
    }
    finally
    {
      dto.delete();
    }
  }

  public void testLTP()
  {
    

    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
    insecticide.setActiveIngredient(TermDTO.get(request, termId));
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
      
      fail("Able to create a ltp without permissions");
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
    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
    insecticide.setActiveIngredient(TermDTO.get(request, termId));
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
      
      fail("Able to create a kdtp without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {

    }
    finally
    {
      insecticide.delete();
    }
  }

}
