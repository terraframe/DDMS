package dss.vector.solutions.export;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.export.entomology.MosquitoCollectionView;
import dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.InsecticideQuery;
import dss.vector.solutions.mo.ActiveIngredient;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.ResistanceMethodology;
import dss.vector.solutions.mo.Specie;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.SearchableHierarchy;

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
    
    ActiveIngredient activeIngredient = ActiveIngredient.validateByDisplayLabel(this.getInsecticideActiveIngredient());
    Unit unit = AdultDiscriminatingDoseAssayExcelView.getUnitByLabel(this.getInsecticideUnits());
    InsecticideQuery insecticideQuery = new InsecticideQuery(new QueryFactory());
    insecticideQuery.WHERE(insecticideQuery.getActiveIngredient().EQ(activeIngredient));
    insecticideQuery.WHERE(insecticideQuery.getUnits().containsExactly(unit));
    insecticideQuery.WHERE(insecticideQuery.getAmount().EQ(this.getInsecticideAmount()));
    
    OIterator<? extends Insecticide> iterator = insecticideQuery.getIterator();
    if (iterator.hasNext())
    {
      ldda.setInsecticide(iterator.next());
    }
    iterator.close();
    
    ldda.apply();
  }
  
  public static void setupExportListener(ExcelExporter exporter, String...params)
  {
    Map<String, String> map = new HashMap<String, String>();
    List<SearchableHierarchy> hierarchy = MosquitoCollectionView.getHierarchy();
    List<MdAttributeDAOIF> attributes = getGeoEntityAttributes();
    
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
