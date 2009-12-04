package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.MosquitoCollectionViewDTO;
import dss.vector.solutions.entomology.SubCollectionViewDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.general.KnockDownTimePropertyDTO;
import dss.vector.solutions.general.LethalTimePropertyDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;

public class EntomologyNoPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(EntomologyNoPermissions.class, MDSSRoleInfo.OPERATIONAL_MANAGER, MDSSRoleInfo.MANAGER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testMosqutioCollection()
  {  
    try
    {
      TermDTO term = TermDTO.get(request, termId);
      
      GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);    
      
      MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);    
      dto.applyAll(new SubCollectionViewDTO[]{TestFixture.createSubCollection(request, term)});

      dto.deleteConcrete();
      
      fail("Able to create a mosquito collection without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testADA()
  {
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);    
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(systemRequest, term, entity);    
    dto.applyAll(new SubCollectionViewDTO[]{});

    try
    {

      InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
      insecticide.setAmount(new Double(30.0));
      insecticide.setUnits(term);
      insecticide.apply();

      try
      {
        AdultDiscriminatingDoseAssayDTO assay = new AdultDiscriminatingDoseAssayDTO(request);
        assay.setSex(TermDTO.get(request, termId));
        assay.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
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
      dto.deleteConcrete();
    }
  }

  public void testLDA()
  {
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);    
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(systemRequest, term, entity);    
    dto.applyAll(new SubCollectionViewDTO[]{});

    try
    {
      InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
      insecticide.setAmount(new Double(30.0));
      insecticide.setUnits(term);
      insecticide.apply();

      try
      {
        LarvaeDiscriminatingDoseAssayDTO assay = new LarvaeDiscriminatingDoseAssayDTO(request);
        assay.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
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
      dto.deleteConcrete();
    }
  }

  public void testKDA()
  {
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);    
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(systemRequest, term, entity);    
    dto.applyAll(new SubCollectionViewDTO[]{});

    try
    {
      InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
      insecticide.setActiveIngredient(TermDTO.get(request, termId));
      insecticide.setAmount(new Double(30.0));
      insecticide.setUnits(term);
      insecticide.apply();

      try
      {
        KnockDownAssayDTO assay = new KnockDownAssayDTO(request);
        assay.setSex(TermDTO.get(request, termId));
        assay.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
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
      dto.deleteConcrete();
    }
  }

  public void testLTP()
  {
    TermDTO term = TermDTO.get(request, termId);

    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
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
    TermDTO term = TermDTO.get(request, termId);

    InsecticideDTO insecticide = new InsecticideDTO(systemRequest);
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
