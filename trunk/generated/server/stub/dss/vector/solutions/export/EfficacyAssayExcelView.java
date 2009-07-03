package dss.vector.solutions.export;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.SurfacePosition;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.EfficacyAssayView;
import dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.mo.ResistanceMethodology;
import dss.vector.solutions.mo.Specie;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.GeoEntitySearcher;
import dss.vector.solutions.util.SearchableHierarchy;

public class EfficacyAssayExcelView extends EfficacyAssayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246490694186L;
  
  public EfficacyAssayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    EfficacyAssayView eav = new EfficacyAssayView();
    
    GeoEntitySearcher searcher = new GeoEntitySearcher(GeoColumnListener.getSurfaceHierarchy());
    
    List<String> geoEntityNames = new LinkedList<String>();
    geoEntityNames.add(this.getGeoEntity_0());
    geoEntityNames.add(this.getGeoEntity_01());
    geoEntityNames.add(this.getGeoEntity_02());
    geoEntityNames.add(this.getGeoEntity_03());
    geoEntityNames.add(this.getGeoEntity_04());
    geoEntityNames.add(this.getGeoEntity_05());
    geoEntityNames.add(this.getGeoEntity_06());
    geoEntityNames.add(this.getGeoEntity_07()); 
    geoEntityNames.add(this.getGeoEntity_08());
    geoEntityNames.add(this.getGeoEntity_09());
    geoEntityNames.add(this.getGeoEntity_10());
    
    eav.setGeoId(searcher.getGeoEntity(geoEntityNames).getGeoId());

    eav.setTestMethod(ResistanceMethodology.validateByDisplayLabel(this.getTestMethod()));
    eav.setSpecie(Specie.validateByDisplayLabel(this.getSpecie()));
    eav.setTestDate(this.getTestDate());
    eav.setColonyName(this.getColonyName());
    
    AdultAgeRange excelAgeRange = this.getAgeRange();
    AdultAgeRange newAgeRange = eav.getAgeRange();
    newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
    newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    
    eav.addSex(AdultDiscriminatingDoseAssayExcelView.getAssaySexByLabel(this.getSex()));
    eav.setGravid(this.getGravid());
    eav.setFed(this.getFed());
    eav.setTimeOnSurface(this.getTimeOnSurface());
    eav.addSurfacePostion(getSurfacePositionByLabel(this.getSurfacePosition()));
    eav.setExposureTime(this.getExposureTime());
    eav.setHoldingTime(this.getHoldingTime());
    eav.setQuantityTested(this.getQuantityTested());
    eav.setQuantityDead(this.getQuantityDead());
    
    eav.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
    
    eav.apply();
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    Map<String, String> map = new HashMap<String, String>();
    List<SearchableHierarchy> hierarchy = GeoColumnListener.getSurfaceHierarchy();
    List<MdAttributeDAOIF> attributes = getGeoEntityAttributes();

    int size = Math.min(hierarchy.size(), attributes.size());

    for (int i = 0; i < size; i++)
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

  public static SurfacePosition getSurfacePositionByLabel(String label)
  {
    for (SurfacePosition e : SurfacePosition.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }
}
