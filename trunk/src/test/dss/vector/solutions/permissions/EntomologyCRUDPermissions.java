package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
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
    dto.setCollectionDate(new Date());
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
      assertEquals(dto.getCollectionDate(), test.getCollectionDate());
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

  public void testADA()
  {
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    // Create the mosquito collection
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(request);
    dto.setCollectionMethod(TermDTO.get(request, termId));
    dto.setGeoEntity(GeoEntityDTO.searchByGeoId(request, siteGeoId));
    dto.setCollectionDate(new Date());
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
    dto.setCollectionDate(new Date());
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
    dto.setCollectionDate(new Date());
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
