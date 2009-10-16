package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.export.entomology.MosquitoCollectionView;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class KnockDownAssayExcelView extends KnockDownAssayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245998013289L;
  
  public KnockDownAssayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    KnockDownAssay kda = new KnockDownAssay();
    
    MosquitoCollectionView mosquitoCollectionView = new MosquitoCollectionView();
    mosquitoCollectionView.setCollectionMethod(this.getCollectionMethod());
    mosquitoCollectionView.setDateCollected(this.getDateCollected());
    mosquitoCollectionView.setGeoEntity(this.getGeoEntity());
    kda.setCollection(mosquitoCollectionView.findMatch());
    
    kda.setTestDate(this.getTestDate());
    kda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(), KnockDownAssay.getTestMethodMd()));
    kda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), KnockDownAssay.getGenerationMd()));
    kda.setIsofemale(this.getIsofemale());
    kda.setSex(Term.validateByDisplayLabel(this.getSex(), KnockDownAssay.getSexMd()));
    kda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), KnockDownAssay.getSpecieMd()));
    kda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), KnockDownAssay.getIdentificationMethodMd()));
    
    AdultAgeRange excelAgeRange = this.getAgeRange();
    AdultAgeRange newAgeRange = kda.getAgeRange();
    newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
    newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    
    kda.setFed(this.getFed());
    kda.setGravid(this.getGravid());
    kda.setExposureTime(this.getExposureTime());
    kda.setIntervalTime(this.getIntervalTime());
    kda.setQuantityTested(this.getQuantityTested());
    
    kda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
    
    kda.apply();
  }
  
  public static void setupExportListener(ExcelExporter exporter, String...params)
  {
    exporter.addListener(createExcelGeoListener());
  }
  
  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }

}
