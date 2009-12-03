package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class LarvaeDiscriminatingDoseAssayExcelView extends LarvaeDiscriminatingDoseAssayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245996428742L;
  
  public LarvaeDiscriminatingDoseAssayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    LarvaeDiscriminatingDoseAssay ldda = new LarvaeDiscriminatingDoseAssay();
    
    MosquitoCollectionView mosquitoCollectionView = new MosquitoCollectionView();
    mosquitoCollectionView.setCollectionMethod(Term.validateByDisplayLabel(this.getCollectionMethod(), MosquitoCollection.getCollectionMethodMd()));
    mosquitoCollectionView.setCollectionDate(this.getDateCollected());
    mosquitoCollectionView.setGeoEntity(this.getGeoEntity());
    ldda.setCollection(mosquitoCollectionView.findMatch());
    
    ldda.setTestDate(this.getTestDate());
    ldda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), LarvaeDiscriminatingDoseAssay.getIdentificationMethodMd()));
    ldda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(), LarvaeDiscriminatingDoseAssay.getTestMethodMd()));
    ldda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), LarvaeDiscriminatingDoseAssay.getGenerationMd()));
    ldda.setIsofemale(this.getIsofemale());

    // Specie is optional so don't validate the input if
    // the value is null or empty
    if(this.hasSpecie())
    {
      ldda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), LarvaeDiscriminatingDoseAssay.getSpecieMd()));
    }

    // Age ranges
    ldda.setStartPoint(Term.validateByDisplayLabel(this.getStartPoint(), LarvaeDiscriminatingDoseAssay.getStartPointMd()));
    ldda.setEndPoint(Term.validateByDisplayLabel(this.getEndPoint(), LarvaeDiscriminatingDoseAssay.getEndPointMd()));
    
    ldda.setExposureTime(this.getExposureTime());
    ldda.setIntervalTime(this.getIntervalTime());
    ldda.setHoldingTime(this.getHoldingTime());
    ldda.setQuantityTested(this.getQuantityTested());
    ldda.setQuantityDead(this.getQuantityDead());
    ldda.setControlTestMortality(this.getControlTestMortality());
    
    ldda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));    
    ldda.apply();
  }
  
  private boolean hasSpecie()
  {
    return this.getSpecie() != null && !this.getSpecie().equals("");
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
