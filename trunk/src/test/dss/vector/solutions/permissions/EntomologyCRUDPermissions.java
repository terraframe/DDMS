package dss.vector.solutions.permissions;

import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.BiochemicalAssayDTO;
import dss.vector.solutions.entomology.BiochemicalAssayViewDTO;
import dss.vector.solutions.entomology.InfectionAssayDTO;
import dss.vector.solutions.entomology.InfectionAssayViewDTO;
import dss.vector.solutions.entomology.MolecularAssayDTO;
import dss.vector.solutions.entomology.MolecularAssayViewDTO;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.MosquitoCollectionViewDTO;
import dss.vector.solutions.entomology.PooledInfectionAssayDTO;
import dss.vector.solutions.entomology.PooledInfectionAssayViewDTO;
import dss.vector.solutions.entomology.SubCollectionViewDTO;
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
    TermDTO term = TermDTO.get(request, termId);

    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);

    SubCollectionViewDTO sub = TestFixture.createSubCollection(request, term);
    SubCollectionViewDTO[] collections = dto.applyAll(new SubCollectionViewDTO[] { sub });

    try
    {
      MosquitoCollectionViewDTO edit = MosquitoCollectionDTO.getView(request, dto.getConcreteId());
      edit.setCollectionId("Editied Id");
      edit.setAbundance(false);
      collections[0].setSubCollectionId("Test Sub Collection");
      edit.applyAll(collections);

      MosquitoCollectionViewDTO test = MosquitoCollectionDTO.getView(request, edit.getConcreteId());

      assertEquals(edit.getCollectionId(), test.getCollectionId());
      assertEquals(edit.getAbundance(), test.getAbundance());
      assertEquals(edit.getCollectionDate(), test.getCollectionDate());
      assertEquals(edit.getCollectionMethod().getId(), test.getCollectionMethod().getId());
      assertEquals(edit.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertTrue(edit.getLifeStage().containsAll(test.getLifeStage()) && test.getLifeStage().containsAll(edit.getLifeStage()));

      SubCollectionViewDTO[] testCollections = test.getSubCollections();

      assertEquals(collections.length, testCollections.length);
      assertEquals(collections[0].getSubCollectionId(), testCollections[0].getSubCollectionId());
      assertEquals(collections[0].getTaxon().getId(), testCollections[0].getTaxon().getId());
      assertEquals(collections[0].getIdentMethod().getId(), testCollections[0].getIdentMethod().getId());
      assertEquals(collections[0].getEggs(), testCollections[0].getEggs());
      assertEquals(collections[0].getTotal(), testCollections[0].getTotal());
    }
    finally
    {
      dto.deleteConcrete();
    }

  }

  public void testInfectionAssay()
  {
    TermDTO term = TermDTO.get(request, termId);
    
    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);
    dto.applyAll(new SubCollectionViewDTO[] {});

    try
    {
      InfectionAssayViewDTO view = new InfectionAssayViewDTO(request);
      view.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
      view.setIdentMethod(term);
      view.setInfected(true);
      view.setNumberPositive(23);
      view.setNumberTested(45);
      view.setParasite(term);
      view.setSex(term);
      view.setSpecies(term);
      view.setTestMethod(term);
      view.apply();
      
      try
      {
        InfectionAssayViewDTO edit = InfectionAssayDTO.lockView(request, view.getConcreteId());
        edit.setInfected(false);
        edit.setNumberPositive(1);
        edit.setNumberTested(1);
        edit.apply();
        
        InfectionAssayViewDTO test = InfectionAssayDTO.getView(request, view.getConcreteId());
        
        assertEquals(edit.getMosquitoId(), test.getMosquitoId());
        assertEquals(edit.getCollection().getId(), test.getCollection().getId());
        assertEquals(edit.getIdentMethod().getId(), test.getIdentMethod().getId());
        assertEquals(edit.getParasite().getId(), test.getParasite().getId());
        assertEquals(edit.getSex().getId(), test.getSex().getId());
        assertEquals(edit.getSpecies().getId(), test.getSpecies().getId());
        assertEquals(edit.getTestMethod().getId(), test.getTestMethod().getId());
        assertEquals(edit.getInfected(), test.getInfected());
        assertEquals(edit.getNumberPositive(), test.getNumberPositive());
        assertEquals(edit.getNumberTested(), test.getNumberTested());
      }
      finally
      {
        view.deleteConcrete();
      }
    }
    finally
    {
      dto.deleteConcrete();
    }
  }
  
  public void testPooledInfectionAssay()
  {
    TermDTO term = TermDTO.get(request, termId);
    
    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);
    dto.applyAll(new SubCollectionViewDTO[] {});
    
    try
    {
      PooledInfectionAssayViewDTO view = new PooledInfectionAssayViewDTO(request);
      view.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
      view.setIdentMethod(term);
      view.setInfected(true);
      view.setNumberPositive(23);
      view.setPoolsTested(34);
      view.setMosquitosTested(234);
      view.setParasite(term);
      view.setSex(term);
      view.setSpecies(term);
      view.setTestMethod(term);
      view.apply();
      
      try
      {
        PooledInfectionAssayViewDTO edit = PooledInfectionAssayDTO.lockView(request, view.getConcreteId());
        edit.setInfected(false);
        edit.setNumberPositive(1);
        edit.setPoolsTested(1);
        edit.setMosquitosTested(34);
        edit.apply();
        
        PooledInfectionAssayViewDTO test = PooledInfectionAssayDTO.getView(request, view.getConcreteId());
        
        assertEquals(edit.getPoolId(), test.getPoolId());
        assertEquals(edit.getCollection().getId(), test.getCollection().getId());
        assertEquals(edit.getIdentMethod().getId(), test.getIdentMethod().getId());
        assertEquals(edit.getParasite().getId(), test.getParasite().getId());
        assertEquals(edit.getSex().getId(), test.getSex().getId());
        assertEquals(edit.getSpecies().getId(), test.getSpecies().getId());
        assertEquals(edit.getTestMethod().getId(), test.getTestMethod().getId());
        assertEquals(edit.getInfected(), test.getInfected());
        assertEquals(edit.getNumberPositive(), test.getNumberPositive());
        assertEquals(edit.getPoolsTested(), test.getPoolsTested());
        assertEquals(edit.getMosquitosTested(), test.getMosquitosTested());
      }
      finally
      {
        view.deleteConcrete();
      }
    }
    finally
    {
      dto.deleteConcrete();
    }
  }
  
  public void testBiochemicalAssay()
  {
    TermDTO term = TermDTO.get(request, termId);
    
    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);
    dto.applyAll(new SubCollectionViewDTO[] {});

    try
    {
      BiochemicalAssayViewDTO view = new BiochemicalAssayViewDTO(request);
      view.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
      view.setIdentMethod(term);
      view.setIsofemale(true);
      view.setNumberElevated(23);
      view.setNumberTested(45);
      view.setGeneration(term);
      view.setSex(term);
      view.setSpecies(term);
      view.setAssay(term);
      view.apply();
      
      try
      {
        BiochemicalAssayViewDTO edit = BiochemicalAssayDTO.lockView(request, view.getConcreteId());
        edit.setIsofemale(false);
        edit.setNumberElevated(1);
        edit.setNumberTested(1);
        edit.apply();
        
        BiochemicalAssayViewDTO test = BiochemicalAssayDTO.getView(request, view.getConcreteId());
        
        assertEquals(edit.getMosquitoId(), test.getMosquitoId());
        assertEquals(edit.getCollection().getId(), test.getCollection().getId());
        assertEquals(edit.getIdentMethod().getId(), test.getIdentMethod().getId());
        assertEquals(edit.getGeneration().getId(), test.getGeneration().getId());
        assertEquals(edit.getSex().getId(), test.getSex().getId());
        assertEquals(edit.getSpecies().getId(), test.getSpecies().getId());
        assertEquals(edit.getAssay().getId(), test.getAssay().getId());
        assertEquals(edit.getIsofemale(), test.getIsofemale());
        assertEquals(edit.getNumberElevated(), test.getNumberElevated());
        assertEquals(edit.getNumberTested(), test.getNumberTested());
      }
      finally
      {
        view.deleteConcrete();
      }
    }
    finally
    {
      dto.deleteConcrete();
    }
  }  
 
  public void testMolecularAssay()
  {
    TermDTO term = TermDTO.get(request, termId);
    
    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);
    dto.applyAll(new SubCollectionViewDTO[] {});

    try
    {
      MolecularAssayViewDTO view = new MolecularAssayViewDTO(request);
      view.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
      view.setIdentMethod(term);
      view.setIsofemale(true);
      view.setNumberRR(23);
      view.setNumberRS(23);
      view.setNumberSS(23);
      view.setGeneration(term);
      view.setSex(term);
      view.setSpecies(term);
      view.setAssayMethod(term);
      view.setTarget(term);
      view.apply();
      
      try
      {
        MolecularAssayViewDTO edit = MolecularAssayDTO.lockView(request, view.getConcreteId());
        edit.setIsofemale(false);
        edit.setNumberRR(1);
        edit.setNumberRS(0);
        edit.setNumberSS(0);
        edit.apply();
        
        MolecularAssayViewDTO test = MolecularAssayDTO.getView(request, view.getConcreteId());
        
        assertEquals(edit.getMosquitoId(), test.getMosquitoId());
        assertEquals(edit.getCollection().getId(), test.getCollection().getId());
        assertEquals(edit.getIdentMethod().getId(), test.getIdentMethod().getId());
        assertEquals(edit.getGeneration().getId(), test.getGeneration().getId());
        assertEquals(edit.getSex().getId(), test.getSex().getId());
        assertEquals(edit.getSpecies().getId(), test.getSpecies().getId());
        assertEquals(edit.getAssayMethod().getId(), test.getAssayMethod().getId());
        assertEquals(edit.getTarget().getId(), test.getTarget().getId());
        assertEquals(edit.getIsofemale(), test.getIsofemale());
        assertEquals(edit.getNumberRR(), test.getNumberRR());
        assertEquals(edit.getNumberRS(), test.getNumberRS());
        assertEquals(edit.getNumberSS(), test.getNumberSS());
      }
      finally
      {
        view.deleteConcrete();
      }
    }
    finally
    {
      dto.deleteConcrete();
    }
  }

  public void testADA()
  {
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);
    dto.applyAll(new SubCollectionViewDTO[] {});

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
        assay.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
        assay.setControlTestMortality(2.5F);
        assay.setFed(30);
        assay.setGravid(30);
        assay.setExposureTime(60);
        
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
      dto.deleteConcrete();
    }
  }

  public void testLDA()
  {
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    // Create the mosquito collection
    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);
    dto.applyAll(new SubCollectionViewDTO[] {});

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
        assay.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
        assay.setControlTestMortality(2.5F);
        assay.setExposureTime(60);
        
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
      dto.deleteConcrete();
    }
  }

  public void testKDA()
  {
    TermDTO term = TermDTO.get(request, termId);

    Date date = new Date();

    GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, siteGeoId);
    MosquitoCollectionViewDTO dto = TestFixture.createCollection(request, term, entity);
    dto.applyAll(new SubCollectionViewDTO[] {});

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
        assay.setCollection(MosquitoCollectionDTO.get(request, dto.getConcreteId()));
        assay.setFed(30);
        assay.setGravid(30);
        assay.setExposureTime(60);        
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
      dto.deleteConcrete();
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
