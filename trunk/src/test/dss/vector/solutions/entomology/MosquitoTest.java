package dss.vector.solutions.entomology;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.entomology.Mosquito;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.MosquitoView;
import dss.vector.solutions.entomology.MosquitoViewDTO;
import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.entomology.SexDTO;
import dss.vector.solutions.entomology.UninterestingSpecieGroup;
import dss.vector.solutions.entomology.UninterestingSpecieGroupView;
import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.biochemical.AEsteraseTestResult;
import dss.vector.solutions.entomology.assay.biochemical.AcHETestResult;
import dss.vector.solutions.entomology.assay.infectivity.PMalariaeTestResult;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinalSite;
import dss.vector.solutions.mo.BiochemicalMethodology;
import dss.vector.solutions.mo.BiochemicalMethodologyDTO;
import dss.vector.solutions.mo.CollectionMethod;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.InfectivityMethodology;
import dss.vector.solutions.mo.InfectivityMethodologyDTO;
import dss.vector.solutions.mo.InsecticideMethodology;
import dss.vector.solutions.mo.InsecticideMethodologyDTO;
import dss.vector.solutions.mo.MolecularAssayResult;
import dss.vector.solutions.mo.MolecularAssayResultDTO;
import dss.vector.solutions.mo.Specie;
import dss.vector.solutions.mo.SpecieDTO;

public class MosquitoTest extends TestCase
{

  private static GeoEntity              geoEntity              = null;

  private static MosquitoCollection     collection             = null;

  private static CollectionMethod       collectionMethod       = null;

  private static Specie                 specie                 = null;

  private static IdentificationMethod   identificationMethod   = null;

  private static MolecularAssayResult   result                 = null;

  private static Generation             F0                     = null;

  private static InfectivityMethodology infectivityMethodology = null;
  
  private static InsecticideMethodology insecticideMethodology = null;
  
  private static BiochemicalMethodology biochemicalMethodology = null;
  
  private static ClientSession         clientSession;

  private static ClientRequestIF       clientRequest;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MosquitoTest.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        classSetUp();
      }

      protected void tearDown()
      {
        classTearDown();
      }

    };

    return wrapper;
  }

  protected static void classTearDown()
  {
    collection.delete();
    geoEntity.delete();
    
    clientSession.logout();
  }

  protected static void classSetUp()
  {
    clientSession = WebClientSession.createUserSession("SYSTEM", "SYSTEM", Locale.US);
    clientRequest = clientSession.getRequest();

    collectionMethod = CollectionMethod.getAll()[0];
    specie = Specie.getAll()[0];
    identificationMethod = IdentificationMethod.getAll()[0];
    F0 = Generation.getAll()[0];
    result = MolecularAssayResult.getAll()[0];
    biochemicalMethodology = BiochemicalMethodology.getAll()[0];
    infectivityMethodology = InfectivityMethodology.getAll()[0];
    insecticideMethodology = InsecticideMethodology.getAll()[0];

    try
    {
      SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
      Date date = dateTime.parse("2006-01-01");

      geoEntity = new SentinalSite();
      geoEntity.setGeoId("0");
      geoEntity.setEntityName("GeoEntity");
      geoEntity.apply();

      collection = new MosquitoCollection();
      collection.setGeoEntity(geoEntity);
      collection.setCollectionMethod(collectionMethod);
      collection.setDateCollected(date);
      collection.apply();
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }
  }

  public void testCreateMosquito() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");
  
    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setTestDate(date);
    view.setAcHEBiochemical(result);
    view.setAcHEBiochemicalMethod(biochemicalMethodology);
    view.setAcHEMolecular(result);
    view.setAcHEMolecularMethod(insecticideMethodology);
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(biochemicalMethodology);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivityMethodology);
    view.apply();
  
    try
    {
      Mosquito mosquito = Mosquito.get(view.getMosquitoId());
  
      assertEquals(specie.getId(), mosquito.getSpecie().getId());
      assertEquals(F0.getId(), mosquito.getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquito.getId());
      assertEquals(identificationMethod.getId(), mosquito.getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquito.getSex().get(0));
      assertEquals(date, mosquito.getTestDate());
      assertEquals(new Boolean(false), mosquito.getIsofemale());
  
      List<AssayTestResult> testResults = mosquito.getTestResults();
  
      assertEquals(4, testResults.size());
  
      for (AssayTestResult r : testResults)
      {
        if (r instanceof AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof dss.vector.solutions.entomology.assay.molecular.AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(insecticideMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof AEsteraseTestResult)
        {
          assertEquals(new Integer(4), (Integer) r.getTestResult());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof PMalariaeTestResult)
        {
          assertEquals(new Boolean(true), (Boolean) r.getTestResult());
          assertEquals(infectivityMethodology.getId(), r.getTestMethod().getId());
        }
      }
    }
    finally
    {
      view.delete();
    }
  }
  
  public void testCreateMosquitoDTO() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");
  
    MosquitoViewDTO view = new MosquitoViewDTO(clientRequest);
    view.setSpecie(SpecieDTO.get(clientRequest, specie.getId()));
    view.setCollection(MosquitoCollectionDTO.get(clientRequest,collection.getId()));
    view.setGeneration(GenerationDTO.get(clientRequest,F0.getId()));
    view.setIsofemale(false);
    view.setIdentificationMethod(IdentificationMethodDTO.get(clientRequest,identificationMethod.getId()));
    view.addSex(SexDTO.FEMALE);
    view.setTestDate(date);
    view.setAcHEBiochemical(MolecularAssayResultDTO.get(clientRequest,result.getId()));
    view.setAcHEBiochemicalMethod(BiochemicalMethodologyDTO.get(clientRequest,biochemicalMethodology.getId()));
    view.setAcHEMolecular(MolecularAssayResultDTO.get(clientRequest,result.getId()));
    view.setAcHEMolecularMethod(InsecticideMethodologyDTO.get(clientRequest,insecticideMethodology.getId()));
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(BiochemicalMethodologyDTO .get(clientRequest,biochemicalMethodology.getId()));
    view.setPMalariae(true);
    view.setPMalariaeMethod(InfectivityMethodologyDTO.get(clientRequest,infectivityMethodology.getId()));
    view.apply();
  
    try
    {
      Mosquito mosquito = Mosquito.get(view.getMosquitoId());
  
      assertEquals(specie.getId(), mosquito.getSpecie().getId());
      assertEquals(F0.getId(), mosquito.getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquito.getId());
      assertEquals(identificationMethod.getId(), mosquito.getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquito.getSex().get(0));
      assertEquals(date, mosquito.getTestDate());
      assertEquals(new Boolean(false), mosquito.getIsofemale());
  
      List<AssayTestResult> testResults = mosquito.getTestResults();
  
      assertEquals(4, testResults.size());
  
      for (AssayTestResult r : testResults)
      {
        if (r instanceof AcHETestResult)
        {
          assertEquals(view.getAcHEBiochemical().getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(view.getAcHEBiochemicalMethod().getId(), r.getTestMethod().getId());
        }
        else if (r instanceof dss.vector.solutions.entomology.assay.molecular.AcHETestResult)
        {
          assertEquals(view.getAcHEMolecular().getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(view.getAcHEMolecularMethod().getId(), r.getTestMethod().getId());
        }
        else if (r instanceof AEsteraseTestResult)
        {
          assertEquals(view.getAEsterase(), (Integer) r.getTestResult());
          assertEquals(view.getAEsteraseMethod().getId(), r.getTestMethod().getId());
        }
        else if (r instanceof PMalariaeTestResult)
        {
          assertEquals(view.getPMalariae(), (Boolean) r.getTestResult());
          assertEquals(view.getPMalariaeMethod().getId(), r.getTestMethod().getId());
        }
      }
      
      assertNull(view.getEKDR());
    }
    finally
    {
      Mosquito.get(view.getMosquitoId()).delete();
    }
  }

  
  public void testMosquitoSaveAll() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");
  
    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setTestDate(date);
    view.setAcHEBiochemical(result);
    view.setAcHEBiochemicalMethod(biochemicalMethodology);
    view.setAcHEMolecular(result);
    view.setAcHEMolecularMethod(insecticideMethodology);
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(biochemicalMethodology);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivityMethodology);
    
    MosquitoView.saveAll(new MosquitoView[]{view});
  
    try
    {
      Mosquito mosquito = Mosquito.get(view.getMosquitoId());
  
      assertEquals(specie.getId(), mosquito.getSpecie().getId());
      assertEquals(F0.getId(), mosquito.getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquito.getId());
      assertEquals(identificationMethod.getId(), mosquito.getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquito.getSex().get(0));
      assertEquals(date, mosquito.getTestDate());
      assertEquals(new Boolean(false), mosquito.getIsofemale());
  
      List<AssayTestResult> testResults = mosquito.getTestResults();
  
      assertEquals(4, testResults.size());
  
      for (AssayTestResult r : testResults)
      {
        if (r instanceof AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof dss.vector.solutions.entomology.assay.molecular.AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(insecticideMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof AEsteraseTestResult)
        {
          assertEquals(new Integer(4), (Integer) r.getTestResult());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof PMalariaeTestResult)
        {
          assertEquals(new Boolean(true), (Boolean) r.getTestResult());
          assertEquals(infectivityMethodology.getId(), r.getTestMethod().getId());
        }
      }
    }
    finally
    {
      view.delete();
    }

  }

  public void testUpdateMosquito() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");
  
    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setTestDate(date);
    view.setAcHEBiochemical(result);
    view.setAcHEBiochemicalMethod(biochemicalMethodology);
    view.setAcHEMolecular(result);
    view.setAcHEMolecularMethod(insecticideMethodology);
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(biochemicalMethodology);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivityMethodology);
    view.apply();
  
    view.setAEsterase(new Integer(5));
    view.apply();
  
    try
    {
      Mosquito mosquito = Mosquito.get(view.getMosquitoId());
  
      assertEquals(specie.getId(), mosquito.getSpecie().getId());
      assertEquals(F0.getId(), mosquito.getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquito.getId());
      assertEquals(identificationMethod.getId(), mosquito.getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquito.getSex().get(0));
      assertEquals(date, mosquito.getTestDate());
      assertEquals(new Boolean(false), mosquito.getIsofemale());
  
      List<AssayTestResult> testResults = mosquito.getTestResults();
  
      assertEquals(4, testResults.size());
  
      for (AssayTestResult r : testResults)
      {
        if (r instanceof AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof dss.vector.solutions.entomology.assay.molecular.AcHETestResult)
        {
          assertEquals(result.getId(), ( (MolecularAssayResult) r.getTestResult() ).getId());
          assertEquals(insecticideMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof AEsteraseTestResult)
        {
          assertEquals(new Integer(5), (Integer) r.getTestResult());
          assertEquals(biochemicalMethodology.getId(), r.getTestMethod().getId());
        }
        else if (r instanceof PMalariaeTestResult)
        {
          assertEquals(new Boolean(true), (Boolean) r.getTestResult());
          assertEquals(infectivityMethodology.getId(), r.getTestMethod().getId());
        }
      }
    }
    finally
    {
      view.delete();
    }
  }

  public void testGetMosquitos() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");

    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setTestDate(date);
    view.setAcHEBiochemical(result);
    view.setAcHEBiochemicalMethod(biochemicalMethodology);
    view.setAcHEMolecular(result);
    view.setAcHEMolecularMethod(insecticideMethodology);
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(biochemicalMethodology);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivityMethodology);
    view.apply();

    try
    {
      MosquitoView[] mosquitos = collection.getMosquitos();

      assertEquals(1, mosquitos.length);
      assertEquals(specie.getId(), mosquitos[0].getSpecie().getId());
      assertEquals(F0.getId(), mosquitos[0].getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquitos[0].getMosquitoId());
      assertEquals(identificationMethod.getId(), mosquitos[0].getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquitos[0].getSex().get(0));
      assertEquals(date, mosquitos[0].getTestDate());
      assertEquals(new Boolean(false), mosquitos[0].getIsofemale());
      assertEquals(result.getId(), mosquitos[0].getAcHEBiochemical().getId());
      assertEquals(biochemicalMethodology.getId(), mosquitos[0].getAcHEBiochemicalMethod().getId());
      assertEquals(result.getId(), mosquitos[0].getAcHEMolecular().getId());
      assertEquals(insecticideMethodology.getId(), mosquitos[0].getAcHEMolecularMethod().getId());
      assertEquals(null, mosquitos[0].getGABA());
      assertEquals(new Integer(4), mosquitos[0].getAEsterase());
      assertEquals(biochemicalMethodology.getId(), mosquitos[0].getAEsteraseMethod().getId());
      assertEquals(new Boolean(true), mosquitos[0].getPMalariae());
      assertEquals(infectivityMethodology.getId(), mosquitos[0].getPMalariaeMethod().getId());
    }
    finally
    {
      view.delete();
    }
  }
  
  public void testGetMultipleMosquitos() throws ParseException
  {
    SimpleDateFormat dateTime = new SimpleDateFormat(DatabaseProperties.getDateFormat());
    Date date = dateTime.parse("2007-01-01");

    MosquitoView view = new MosquitoView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setGeneration(F0);
    view.setIsofemale(false);
    view.setIdentificationMethod(identificationMethod);
    view.addSex(Sex.FEMALE);
    view.setTestDate(date);
    view.setAcHEBiochemical(result);
    view.setAcHEBiochemicalMethod(biochemicalMethodology);
    view.setAcHEMolecular(result);
    view.setAcHEMolecularMethod(insecticideMethodology);
    view.setAEsterase(new Integer(4));
    view.setAEsteraseMethod(biochemicalMethodology);
    view.setPMalariae(true);
    view.setPMalariaeMethod(infectivityMethodology);
    view.apply();
    
    MosquitoView view2 = new MosquitoView();
    view2.setSpecie(specie);
    view2.setCollection(collection);
    view2.setGeneration(F0);
    view2.setIsofemale(false);
    view2.setIdentificationMethod(identificationMethod);
    view2.addSex(Sex.FEMALE);
    view2.setTestDate(date);
    view2.setAcHEBiochemical(result);
    view2.setAcHEBiochemicalMethod(biochemicalMethodology);
    view2.setAcHEMolecular(result);
    view2.setAcHEMolecularMethod(insecticideMethodology);
    view2.setAEsterase(new Integer(5));
    view2.setAEsteraseMethod(biochemicalMethodology);
    view2.setPMalariae(true);
    view2.setPMalariaeMethod(infectivityMethodology);
    view2.apply();

    try
    {
      MosquitoView[] mosquitos = collection.getMosquitos();

      assertEquals(2, mosquitos.length);
      assertEquals(specie.getId(), mosquitos[0].getSpecie().getId());
      assertEquals(F0.getId(), mosquitos[0].getGeneration().getId());
      assertEquals(view.getMosquitoId(), mosquitos[0].getMosquitoId());
      assertEquals(identificationMethod.getId(), mosquitos[0].getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquitos[0].getSex().get(0));
      assertEquals(date, mosquitos[0].getTestDate());
      assertEquals(new Boolean(false), mosquitos[0].getIsofemale());
      assertEquals(result.getId(), mosquitos[0].getAcHEBiochemical().getId());
      assertEquals(biochemicalMethodology.getId(), mosquitos[0].getAcHEBiochemicalMethod().getId());
      assertEquals(result.getId(), mosquitos[0].getAcHEMolecular().getId());
      assertEquals(insecticideMethodology.getId(), mosquitos[0].getAcHEMolecularMethod().getId());
      assertEquals(null, mosquitos[0].getGABA());
      assertEquals(new Integer(4), mosquitos[0].getAEsterase());
      assertEquals(biochemicalMethodology.getId(), mosquitos[0].getAEsteraseMethod().getId());
      assertEquals(new Boolean(true), mosquitos[0].getPMalariae());
      assertEquals(infectivityMethodology.getId(), mosquitos[0].getPMalariaeMethod().getId());
      
      assertEquals(specie.getId(), mosquitos[1].getSpecie().getId());
      assertEquals(F0.getId(), mosquitos[1].getGeneration().getId());
      assertEquals(view2.getMosquitoId(), mosquitos[1].getMosquitoId());
      assertEquals(identificationMethod.getId(), mosquitos[1].getIdentificationMethod().getId());
      assertEquals(Sex.FEMALE, mosquitos[1].getSex().get(0));
      assertEquals(date, mosquitos[1].getTestDate());
      assertEquals(new Boolean(false), mosquitos[1].getIsofemale());
      assertEquals(result.getId(), mosquitos[1].getAcHEBiochemical().getId());
      assertEquals(biochemicalMethodology.getId(), mosquitos[1].getAcHEBiochemicalMethod().getId());
      assertEquals(result.getId(), mosquitos[1].getAcHEMolecular().getId());
      assertEquals(insecticideMethodology.getId(), mosquitos[1].getAcHEMolecularMethod().getId());
      assertEquals(null, mosquitos[1].getGABA());
      assertEquals(new Integer(5), mosquitos[1].getAEsterase());
      assertEquals(biochemicalMethodology.getId(), mosquitos[1].getAEsteraseMethod().getId());
      assertEquals(new Boolean(true), mosquitos[1].getPMalariae());
      assertEquals(infectivityMethodology.getId(), mosquitos[1].getPMalariaeMethod().getId());
    }
    finally
    {
      view.delete();
      view2.delete();
    }
  }

  public void testUninterestingSpecieGroup()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setIdentificationMethod(identificationMethod);
    view.setSampleId("0");
    view.setQuantity(200);
    view.apply();

    try
    {
      UninterestingSpecieGroup group = UninterestingSpecieGroup.get(view.getGroupId());

      assertEquals(specie.getId(), group.getSpecie().getId());
      assertEquals(view.getGroupId(), group.getId());
      assertEquals(identificationMethod.getId(), group.getIdentificationMethod().getId());
      assertEquals("0", group.getSampleId());
      assertEquals(new Integer(200), group.getQuantity());
    }
    finally
    {
      view.delete();
    }
  }
  
  public void testUninterestingGroupSaveAll()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setIdentificationMethod(identificationMethod);
    view.setSampleId("0");
    view.setQuantity(200);
    
    UninterestingSpecieGroupView.saveAll(new UninterestingSpecieGroupView[]{view});

    try
    {
      UninterestingSpecieGroup group = UninterestingSpecieGroup.get(view.getGroupId());

      assertEquals(specie.getId(), group.getSpecie().getId());
      assertEquals(view.getGroupId(), group.getId());
      assertEquals(identificationMethod.getId(), group.getIdentificationMethod().getId());
      assertEquals("0", group.getSampleId());
      assertEquals(new Integer(200), group.getQuantity());
    }
    finally
    {
      view.delete();
    }
  }

  public void testUpdateUninterestingSpecieGroup()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setIdentificationMethod(identificationMethod);
    view.setSampleId("0");
    view.setQuantity(200);
    view.apply();

    view.setQuantity(400);
    view.apply();

    try
    {
      UninterestingSpecieGroup group = UninterestingSpecieGroup.get(view.getGroupId());

      assertEquals(specie.getId(), group.getSpecie().getId());
      assertEquals(view.getGroupId(), group.getId());
      assertEquals(identificationMethod.getId(), group.getIdentificationMethod().getId());
      assertEquals("0", group.getSampleId());
      assertEquals(new Integer(400), group.getQuantity());
    }
    finally
    {
      view.delete();
    }
  }
  
  public void testGetUninterestingSpecieGroup()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setIdentificationMethod(identificationMethod);
    view.setSampleId("0");
    view.setQuantity(200);
    view.apply();

    try
    {
      UninterestingSpecieGroupView[] groups = collection.getUninterestingSpecieGroups();

      assertEquals(1, groups.length);
      assertEquals(specie.getId(), groups[0].getSpecie().getId());
      assertEquals(view.getGroupId(), groups[0].getGroupId());
      assertEquals(identificationMethod.getId(), groups[0].getIdentificationMethod().getId());
      assertEquals("0", groups[0].getSampleId());
      assertEquals(new Integer(200), groups[0].getQuantity());
    }
    finally
    {
      view.delete();
    }
  }

  public void testGetMultipleUninterestingSpecieGroup()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    view.setSpecie(specie);
    view.setCollection(collection);
    view.setIdentificationMethod(identificationMethod);
    view.setSampleId("0");
    view.setQuantity(200);
    view.apply();

    UninterestingSpecieGroupView view2 = new UninterestingSpecieGroupView();
    view2.setSpecie(specie);
    view2.setCollection(collection);
    view2.setIdentificationMethod(identificationMethod);
    view2.setSampleId("1");
    view2.setQuantity(300);
    view2.apply();

    
    try
    {
      UninterestingSpecieGroupView[] groups = collection.getUninterestingSpecieGroups();

      assertEquals(2, groups.length);
      assertEquals(specie.getId(), groups[0].getSpecie().getId());
      assertEquals(view.getGroupId(), groups[0].getGroupId());
      assertEquals(identificationMethod.getId(), groups[0].getIdentificationMethod().getId());
      assertEquals("0", groups[0].getSampleId());
      assertEquals(new Integer(200), groups[0].getQuantity());
      assertEquals(specie.getId(), groups[1].getSpecie().getId());
      assertEquals(view2.getGroupId(), groups[1].getGroupId());
      assertEquals(identificationMethod.getId(), groups[1].getIdentificationMethod().getId());
      assertEquals("1", groups[1].getSampleId());
      assertEquals(new Integer(300), groups[1].getQuantity());
    }
    finally
    {
      view.delete();
      view2.delete();
    }
  }
}
