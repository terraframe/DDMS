package dss.vector.solutions.kaleidoscope.data.etl;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;

public class DataSetBuilder implements DataSetBuilderIF
{
  private JSONObject    configuration;

  private SourceContext source;

  private TargetContext target;

  public DataSetBuilder(String _configuration)
  {
    try
    {
      this.configuration = new JSONObject(_configuration);

      this.source = new SourceContext();
      this.target = new TargetContext();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public DataSetBuilder(JSONObject _configuration)
  {
    this.configuration = _configuration;
    this.source = new SourceContext();
    this.target = new TargetContext();
  }

  @Override
  @Transaction
  @AbortIfProblem
  public void build()
  {
    new SourceBuilder(this.configuration, this.source).build();

    new TargetBuilder(this.configuration, this.source, this.target).build();

    this.source.persist();
    this.target.persist();
  }

  @Override
  public SourceContextIF getSourceContext()
  {
    return this.source;
  }

  @Override
  public TargetContextIF getTargetContext()
  {
    return this.target;
  }
}
