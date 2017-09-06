package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.dataaccess.BusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;

public interface TargetContextIF extends Reloadable
{
  /**
   * @param sourceType
   * 
   * @param oid
   *          Object id
   * @return
   */
  public BusinessDAO getOrCreateMutable(String sourceType, String oid);

  public String getType(String sourceType);

  public List<TargetFieldIF> getFields(String sourceType);

  public List<TargetDefinitionIF> getDefinitions();

  public TargetDefinitionIF getDefinition(String sourceType);

  public Map<String, Set<String>> getLocationExclusions();

  public Map<String, Set<String>> getCategoryExclusions();
}
