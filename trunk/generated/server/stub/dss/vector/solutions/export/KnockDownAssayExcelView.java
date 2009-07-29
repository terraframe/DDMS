package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.export.entomology.MosquitoCollectionView;
import dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.NonSentinelSite;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.ResistanceMethodology;
import dss.vector.solutions.mo.Specie;

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
    kda.setTestMethod(ResistanceMethodology.validateByDisplayLabel(this.getTestMethod()));
    kda.setGeneration(Generation.validateByDisplayLabel(this.getGeneration()));
    kda.setIsofemale(this.getIsofemale());
    kda.addSex(AdultDiscriminatingDoseAssayExcelView.getAssaySexByLabel(this.getSex()));
    kda.setSpecie(Specie.validateByDisplayLabel(this.getSpecie()));
    kda.setIdentificationMethod(IdentificationMethod.validateByDisplayLabel(this.getIdentificationMethod()));
    
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
    GeoHierarchy sentinelSite = GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS);
    GeoHierarchy nonSentinelSite = GeoHierarchy.getGeoHierarchyFromType(NonSentinelSite.CLASS);
    exporter.addListener(new DynamicGeoColumnListener(CLASS, GEOENTITY, sentinelSite, nonSentinelSite));
  }
}
