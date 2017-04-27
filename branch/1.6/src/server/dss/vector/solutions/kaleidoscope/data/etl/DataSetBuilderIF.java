package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public interface DataSetBuilderIF extends Reloadable
{
  public void build();

  public SourceContextIF getSourceContext();

  public TargetContextIF getTargetContext();

}
