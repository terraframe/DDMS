package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.dataaccess.BusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;

public interface TargetContextIF extends Reloadable
{
  public BusinessDAO newMutable(String sourceType);

  public String getType(String sourceType);

  public List<TargetFieldIF> getFields(String sourceType);

  public List<TargetDefinitionIF> getDefinitions();

  public TargetDefinitionIF getDefinition(String sourceType);

  public Map<String, Set<String>> getLocationExclusions();

  public Map<String, Set<String>> getCategoryExclusions();
}
