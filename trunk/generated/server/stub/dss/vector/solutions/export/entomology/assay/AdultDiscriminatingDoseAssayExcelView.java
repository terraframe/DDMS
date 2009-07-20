package dss.vector.solutions.export.entomology.assay;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.AssaySex;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.export.entomology.MosquitoCollectionView;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.ResistanceMethodology;
import dss.vector.solutions.mo.Specie;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.SearchableHierarchy;

public class AdultDiscriminatingDoseAssayExcelView extends AdultDiscriminatingDoseAssayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244508626257L;
  
  public AdultDiscriminatingDoseAssayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    AdultDiscriminatingDoseAssay adda = new AdultDiscriminatingDoseAssay();
    
    MosquitoCollectionView mosquitoCollectionView = new MosquitoCollectionView();
    mosquitoCollectionView.setCollectionMethod(this.getCollectionMethod());
    mosquitoCollectionView.setDateCollected(this.getDateCollected());
    mosquitoCollectionView.setGeoEntity_0(this.getGeoEntity_0());
    mosquitoCollectionView.setGeoEntity_01(this.getGeoEntity_01());
    mosquitoCollectionView.setGeoEntity_02(this.getGeoEntity_02());
    mosquitoCollectionView.setGeoEntity_03(this.getGeoEntity_03());
    mosquitoCollectionView.setGeoEntity_04(this.getGeoEntity_04());
    mosquitoCollectionView.setGeoEntity_05(this.getGeoEntity_05());
    mosquitoCollectionView.setGeoEntity_06(this.getGeoEntity_06());
    mosquitoCollectionView.setGeoEntity_07(this.getGeoEntity_07());
    mosquitoCollectionView.setGeoEntity_08(this.getGeoEntity_08());
    mosquitoCollectionView.setGeoEntity_09(this.getGeoEntity_09());
    mosquitoCollectionView.setGeoEntity_10(this.getGeoEntity_10());
//    mosquitoCollectionView.apply();
//    MosquitoCollection mosquitoCollection = MosquitoCollection.get(mosquitoCollectionView.getConcreteId());
    adda.setCollection(mosquitoCollectionView.findMatch());
    
    adda.setTestDate(this.getTestDate());
    adda.setTestMethod(ResistanceMethodology.validateByDisplayLabel(this.getTestMethod()));
    adda.setGeneration(Generation.validateByDisplayLabel(this.getGeneration()));
    adda.setIsofemale(this.getIsofemale());
    adda.addSex(getAssaySexByLabel(this.getSex()));
    adda.setSpecie(Specie.validateByDisplayLabel(this.getSpecie()));
    adda.setIdentificationMethod(IdentificationMethod.validateByDisplayLabel(this.getIdentificationMethod()));
    
    AdultAgeRange excelAgeRange = this.getAgeRange();
    AdultAgeRange newAgeRange = adda.getAgeRange();
    newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
    newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    
    adda.setFed(this.getFed());
    adda.setGravid(this.getGravid());
    adda.setExposureTime(this.getExposureTime());
    adda.setHoldingTime(this.getHoldingTime());
    
    adda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
    
    adda.setQuantityTested(this.getQuantityTested());
    adda.setQuantityDead(this.getQuantityDead());
    adda.setControlTestMortality(this.getControlTestMortality());
    adda.setIntervalTime(this.getIntervalTime());
    
    adda.apply();
  }
  
  public static AssaySex getAssaySexByLabel(String label)
  {
    for (AssaySex e : AssaySex.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String...params)
  {
    Map<String, String> map = new HashMap<String, String>();
    List<SearchableHierarchy> hierarchy = MosquitoCollectionView.getHierarchy();
    List<MdAttributeDAOIF> attributes = AdultDiscriminatingDoseAssayExcelView.getGeoEntityAttributes();
    
    int size = Math.min(hierarchy.size(), attributes.size());
    
    for(int i = 0; i < size; i++)
    {
      String key = attributes.get(i).getId();
      String displayLabel = hierarchy.get(i).getDisplayLabel();

      map.put(key, displayLabel);
    }
    
    exporter.addListener(new GeoColumnListener(map));    
  }
  
  private static List<MdAttributeDAOIF> getGeoEntityAttributes()
  {
    List<MdAttributeDAOIF> list = new LinkedList<MdAttributeDAOIF>();
    list.add(getGeoEntity_0Md());
    list.add(getGeoEntity_01Md());
    list.add(getGeoEntity_02Md());
    list.add(getGeoEntity_03Md());
    list.add(getGeoEntity_04Md());
    list.add(getGeoEntity_05Md());
    list.add(getGeoEntity_06Md());
    list.add(getGeoEntity_07Md());
    list.add(getGeoEntity_08Md());
    list.add(getGeoEntity_09Md());
    list.add(getGeoEntity_10Md());
    
    return list;
  }
}
