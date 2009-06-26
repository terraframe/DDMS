package dss.vector.solutions.export;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MorphologicalSpecieGroup;
import dss.vector.solutions.entomology.MosquitoCollectionPoint;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.SurveyPoint;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.Specie;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.GeoEntitySearcher;
import dss.vector.solutions.util.SearchableHierarchy;

public class MorphologicalSpecieGroupExcelView extends MorphologicalSpecieGroupExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246002222478L;
  
  public MorphologicalSpecieGroupExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    MorphologicalSpecieGroup msg = new MorphologicalSpecieGroup();
    
    msg.setCollection(getMosquitoCollectionPoint());
    msg.setSpecie(Specie.validateByDisplayLabel(this.getSpecie()));
    msg.setIdentificationMethod(IdentificationMethod.validateByDisplayLabel(this.getIdentificationMethod()));
    msg.setQuantity(this.getQuantity());
    msg.setQuantityMale(this.getQuantityMale());
    msg.setQuantityFemale(this.getQuantityFemale());
    msg.apply();
  }
  
  public MosquitoCollectionPoint getMosquitoCollectionPoint()
  {
    GeoEntitySearcher searcher = new GeoEntitySearcher(GeoColumnListener.getHierarchy());
    
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
    
    GeoEntity entity = searcher.getGeoEntity(geoEntityNames);
    return MosquitoCollectionPoint.findOrCreate(entity, this.getDateCollected());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    Map<String, String> map = new HashMap<String, String>();
    List<SearchableHierarchy> hierarchy = GeoColumnListener.getHierarchy();
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
}
