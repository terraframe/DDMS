package dss.vector.solutions.util;

import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.geo.generated.GeoEntity;

public interface SearchableHierarchy extends Reloadable
{
  public List<GeoEntity> searchGeoEntity(String entityName, GeoEntity parent);
  
  public String getDisplayLabel();
}
