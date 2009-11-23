package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.MorphologicalSpecieGroupDTO;
import dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO;
import dss.vector.solutions.entomology.MosquitoDTO;
import dss.vector.solutions.entomology.MosquitoViewDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.general.KnockDownTimePropertyDTO;
import dss.vector.solutions.general.LethalTimePropertyDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;

public class EntomologyCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(EntomologyCRUDPermissions.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.MDSS_CORRDINATOR);
  }

  public void testMosqutioCollection()
  {
    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      // Update the mosquito collection
      dto.lock();

      dto.setCollectionMethod(TermDTO.get(request, termId));
      dto.apply();

      // Read the mosquito collection
      MosquitoCollectionDTO test = MosquitoCollectionDTO.get(request, dto.getId());

      assertEquals(dto.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(dto.getCollectionMethod().getId(), test.getCollectionMethod().getId());
      assertEquals(dto.getDateCollected(), test.getDateCollected());
      assertEquals(dto.getCollectionId(), test.getCollectionId());
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF p : e.getProblems())
      {
        fail(p.getDeveloperMessage());
      }
    }
    finally
    {
      // delete the mosquito collection
      dto.delete();
    }
  }

  public void testMorphologicalSpecieGroup()
  {
    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
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
    GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(request, collectionSiteGeoId);

    // Create the mosquito collection
    MosquitoCollectionPointViewDTO dto = new MosquitoCollectionPointViewDTO(request);
    dto.setGeoEntity(geoEntity);
    dto.setDateCollected(date);
    dto.setIdentificationMethod(TermDTO.get(request, termId));
    dto.setSpecie(TermDTO.get(request, termId));
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

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
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
      view.setMixed(true);
      view.setMixedMethod(TermDTO.get(request, termId));
      view.apply();

      try
      {
        MosquitoViewDTO update = MosquitoDTO.lockView(request, view.getMosquitoId());
        update.setSpecie(TermDTO.get(request, termId));
        update.setGeneration(TermDTO.get(request, termId));
        update.setIdentificationMethod(TermDTO.get(request, termId));
        update.setP450(false);
        update.setIAcHE(TermDTO.get(request, termId));
        update.setIAcHEMethod(TermDTO.get(request, termId));
        update.setAAcetate(true);
        update.setPMalariae(false);
        update.setPMalariaeMethod(TermDTO.get(request, termId));
        update.setMixed(false);
        update.setMixedMethod(TermDTO.get(request, termId));
        update.apply();

        MosquitoViewDTO test = MosquitoDTO.getView(request, view.getMosquitoId());

        assertEquals(update.getSpecie().getId(), test.getSpecie().getId());
        assertEquals(update.getGeneration().getId(), test.getGeneration().getId());
        assertEquals(update.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
        assertEquals(update.getSex(), test.getSex());
        assertEquals(update.getTestDate(), test.getTestDate());
        assertEquals(update.getIsofemale(), test.getIsofemale());
        assertEquals(update.getP450(), test.getP450());
        assertEquals(update.getIAcHE().getId(), test.getIAcHE().getId());
        assertEquals(update.getIAcHEMethod().getId(), test.getIAcHEMethod().getId());
        assertEquals(update.getAAcetate(), test.getAAcetate());
        assertEquals(update.getPMalariae(), test.getPMalariae());
        assertEquals(update.getPMalariaeMethod().getId(), test.getPMalariaeMethod().getId());
        assertEquals(update.getMixed(), test.getMixed());
        assertEquals(update.getMixedMethod().getId(), test.getMixedMethod().getId());
        assertNull(update.getEKDR());
      }
      finally
      {
        view.deleteConcrete();
      }
    }
    finally
    {
      dto.delete();
    }

  }

  public void testADA()
  {
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      InsecticideDTO insecticide = new InsecticideDTO(request);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
      insecticide.setAmount(new Double(30.0));
      insecticide.setUnits(term);
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
        assay.setKd50(77.7);
        assay.setKd95(77.7);
        assay.apply();

        try
        {
          assay.lock();
          assay.setIsofemale(false);
          assay.setQuantityDead(300);
          assay.setQuantityTested(600);
          assay.setQuantityLive(300);
          assay.setSpecie(TermDTO.get(request, termId));
          assay.setTestDate(date);
          assay.setInsecticide(insecticide);
          assay.apply();

          AdultDiscriminatingDoseAssayDTO test = AdultDiscriminatingDoseAssayDTO.get(request, assay.getId());

          assertEquals(assay.getSpecie().getId(), test.getSpecie().getId());
          assertEquals(assay.getGeneration().getId(), test.getGeneration().getId());
          assertEquals(assay.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
          assertEquals(assay.getSex(), test.getSex());
          assertEquals(assay.getTestDate(), test.getTestDate());
          assertEquals(assay.getKd50(), test.getKd50());
          assertEquals(assay.getKd95(), test.getKd95());
        }
        finally
        {
          assay.delete();
        }
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
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();   

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      InsecticideDTO insecticide = new InsecticideDTO(request);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
      insecticide.setAmount(new Double(30.0));
      insecticide.setUnits(term);
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
        assay.setLt50(77.7);
        assay.setLt95(77.7);
        assay.apply();

        try
        {
          assay.lock();
          assay.setIsofemale(false);
          assay.setQuantityDead(300);
          assay.setQuantityTested(600);
          assay.setQuantityLive(300);
          assay.setSpecie(TermDTO.get(request, termId));
          assay.setTestDate(date);
          assay.setInsecticide(insecticide);
          assay.apply();

          LarvaeDiscriminatingDoseAssayDTO test = LarvaeDiscriminatingDoseAssayDTO.get(request, assay.getId());

          assertEquals(assay.getSpecie().getId(), test.getSpecie().getId());
          assertEquals(assay.getGeneration().getId(), test.getGeneration().getId());
          assertEquals(assay.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
          assertEquals(assay.getTestDate(), test.getTestDate());
          assertEquals(assay.getLt50(), test.getLt50());
          assertEquals(assay.getLt95(), test.getLt95());
        }
        finally
        {
          assay.delete();
        }
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
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
    dto.setDateCollected(new Date());
    dto.apply();

    try
    {
      InsecticideDTO insecticide = new InsecticideDTO(request);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
      insecticide.setAmount(new Double(30.0));
      insecticide.setUnits(term);
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
        assay.setKd50(77.7);
        assay.setKd95(77.7);
        assay.apply();

        try
        {
          assay.lock();
          assay.setIsofemale(false);
          assay.setQuantityTested(600);
          assay.setSpecie(TermDTO.get(request, termId));
          assay.setTestDate(date);
          assay.setInsecticide(insecticide);
          assay.apply();

          KnockDownAssayDTO test = KnockDownAssayDTO.get(request, assay.getId());

          assertEquals(assay.getSpecie().getId(), test.getSpecie().getId());
          assertEquals(assay.getGeneration().getId(), test.getGeneration().getId());
          assertEquals(assay.getIdentificationMethod().getId(), test.getIdentificationMethod().getId());
          assertEquals(assay.getSex(), test.getSex());
          assertEquals(assay.getTestDate(), test.getTestDate());
          assertEquals(assay.getKd50(), test.getKd50());
          assertEquals(assay.getKd95(), test.getKd95());
        }
        finally
        {
          assay.delete();
        }
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
    TermDTO term = TermDTO.get(request, termId);

    InsecticideDTO insecticide = new InsecticideDTO(request);
    insecticide.setActiveIngredient(TermDTO.get(request, termId));
    insecticide.setAmount(new Double(30.0));
    insecticide.setUnits(term);
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
      }
    }
    finally
    {
      insecticide.delete();
    }
  }

  public void testKDTP()
  {
    TermDTO term = TermDTO.get(request, termId);
    
    InsecticideDTO insecticide = new InsecticideDTO(request);
    insecticide.setActiveIngredient(TermDTO.get(request, termId));
    insecticide.setAmount(new Double(30.0));
    insecticide.setUnits(term);
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
      }
    }
    finally
    {
      insecticide.delete();
    }
  }
}
