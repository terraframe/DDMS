package dss.vector.solutions.kaleidoscope.data.etl;

public interface DataSetBuilderIF
{
  public void build();

  public SourceContextIF getSourceContext();

  public TargetContextIF getTargetContext();

}
