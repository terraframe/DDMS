package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;

public class SuccessResponse implements ImportResponseIF, Reloadable
{

  private int                         total;

  private int                         failures;

  private String                      fileId;

  private Collection<ImportProblemIF> problems;

  private JSONArray                   datasets;

  private SourceContextIF             sContext;

  private TargetContextIF             tContext;

  public SuccessResponse(SourceContextIF sContext, TargetContextIF tContext)
  {
    this.problems = new LinkedList<>();
    this.sContext = sContext;
    this.tContext = tContext;
  }

  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("success", true);
    object.put("datasets", datasets);
    object.put("total", this.total);
    object.put("failures", this.failures);

    if (this.problems.size() > 0)
    {
      object.put("problems", this.getProblemsJSON());
      object.put("sheets", this.getSheetsJSON());
    }

    if (this.fileId != null)
    {
      object.put("fileId", this.fileId);
    }

    return object;
  }

  public JSONObject getProblemsJSON() throws JSONException
  {
    Map<String, JSONArray> map = new HashMap<String, JSONArray>();
    map.put(LocationProblem.TYPE, new JSONArray());
    map.put(CategoryProblem.TYPE, new JSONArray());

    JSONObject options = new JSONObject();

    for (ImportProblemIF problem : this.problems)
    {
      map.putIfAbsent(problem.getType(), new JSONArray());

      map.get(problem.getType()).put(problem.toJSON());
    }

    JSONObject object = new JSONObject();
    object.put("options", options);

    Set<Entry<String, JSONArray>> entries = map.entrySet();

    for (Entry<String, JSONArray> entry : entries)
    {
      object.put(entry.getKey(), entry.getValue());
    }

    return object;
  }

  @Override
  public boolean hasProblems()
  {
    return this.problems.size() > 0;
  }

  public void setTotal(int total)
  {
    this.total = total;
  }

  public int getTotal()
  {
    return total;
  }

  public void setFailures(int failures)
  {
    this.failures = failures;
  }

  public int getFailures()
  {
    return failures;
  }

  public void setFileId(String fileId)
  {
    this.fileId = fileId;
  }

  public String getFileId()
  {
    return fileId;
  }

  public void setProblems(Collection<ImportProblemIF> problems)
  {
    this.problems = problems;
  }

  public Collection<ImportProblemIF> getProblems()
  {
    return problems;
  }

  public void setDatasets(JSONArray datasets)
  {
    this.datasets = datasets;
  }

  public JSONArray getDatasets()
  {
    return datasets;
  }

  private JSONArray getSheetsJSON() throws JSONException
  {
    JSONArray sheets = new JSONArray();

    Collection<SourceDefinitionIF> definitions = this.sContext.getDefinitions();

    for (SourceDefinitionIF sDefinition : definitions)
    {
      TargetDefinitionIF tDefinition = this.tContext.getDefinition(sDefinition.getType());

      DefinitionBuilder builder = new DefinitionBuilder(sDefinition, tDefinition);
      JSONObject sheet = builder.getConfiguration();

      sheets.put(sheet);
    }
    return sheets;
  }
}
