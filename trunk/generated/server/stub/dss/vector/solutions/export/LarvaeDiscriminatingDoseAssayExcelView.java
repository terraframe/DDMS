package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.export.entomology.MosquitoCollectionView;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.NonSentinelSite;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.ResistanceMethodology;
import dss.vector.solutions.mo.Specie;

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
    mosquitoCollectionView.setCollectionMethod(this.getCollectionMethod());
    mosquitoCollectionView.setDateCollected(this.getDateCollected());
    mosquitoCollectionView.setGeoEntity(this.getGeoEntity());
    ldda.setCollection(mosquitoCollectionView.findMatch());
    
    ldda.setTestDate(this.getTestDate());
    ldda.setSpecie(Specie.validateByDisplayLabel(this.getSpecie()));
    ldda.setIdentificationMethod(IdentificationMethod.validateByDisplayLabel(this.getIdentificationMethod()));
    ldda.setTestMethod(ResistanceMethodology.validateByDisplayLabel(this.getTestMethod()));
    ldda.setGeneration(Generation.validateByDisplayLabel(this.getGeneration()));
    ldda.setIsofemale(this.getIsofemale());

    // Age ranges
    
    ldda.setExposureTime(this.getExposureTime());
    ldda.setIntervalTime(this.getIntervalTime());
    ldda.setHoldingTime(this.getHoldingTime());
    ldda.setQuantityTested(this.getQuantityTested());
    ldda.setQuantityDead(this.getQuantityDead());
    ldda.setControlTestMortality(this.getControlTestMortality());
    
    ldda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
    
    ldda.apply();
  }
  
  public static void setupExportListener(ExcelExporter exporter, String...params)
  {
    GeoHierarchy sentinelSite = GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS);
    GeoHierarchy nonSentinelSite = GeoHierarchy.getGeoHierarchyFromType(NonSentinelSite.CLASS);
    exporter.addListener(new DynamicGeoColumnListener(CLASS, GEOENTITY, sentinelSite, nonSentinelSite));
  }
}
