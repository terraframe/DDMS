package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.AbstractSprayView;
import dss.vector.solutions.irs.SprayMethod;
import dss.vector.solutions.irs.SurfaceType;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.GeoEntitySearcher;

public class AbstractSprayExcelView extends AbstractSprayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246598143605L;
  
  public AbstractSprayExcelView()
  {
    super();
  }
  
  public void populate(AbstractSprayView abstractSprayView)
  {
    abstractSprayView.addSurfaceType(getSurfaceTypeByLabel(this.getSurfaceType()));
  }

  protected GeoEntity getGeoEntity()
  {
    GeoEntitySearcher searcher = new GeoEntitySearcher(GeoColumnListener.getSprayHierarchy());
    
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
    return entity;
  }
  
  public static List<MdAttributeDAOIF> getGeoEntityAttributes()
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
  
  public SurfaceType getSurfaceTypeByLabel(String label)
  {
    for (SurfaceType e : SurfaceType.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }

  public SprayMethod getSprayMethodByLabel(String label)
  {
    for (SprayMethod e : SprayMethod.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }
}
