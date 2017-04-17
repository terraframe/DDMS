package dss.vector.solutions.kaleidoscope.data.etl;

import org.json.JSONException;
import org.json.JSONObject;

public class CategoryProblem implements ImportProblemIF, Comparable<ImportProblemIF>
{
  public static final String TYPE = "categories";

  private String             label;

  private String             mdAttributeId;

  private String             attributeLabel;

  private String             categoryId;

  public CategoryProblem(String label, String categoryId, String mdAttributeId, String attributeLabel)
  {
    this.label = label;
    this.categoryId = categoryId;
    this.mdAttributeId = mdAttributeId;
    this.attributeLabel = attributeLabel;
  }

  public String getMdAttributeId()
  {
    return mdAttributeId;
  }

  public String getKey()
  {
    return this.categoryId + "-" + this.label;
  }

  @Override
  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("type", "DOMAIN");
    object.put("label", label);
    object.put("categoryId", categoryId);
    object.put("mdAttributeId", mdAttributeId);
    object.put("attributeLabel", attributeLabel);

    return object;
  }

  @Override
  public int compareTo(ImportProblemIF problem)
  {
    return this.getKey().compareTo(problem.getKey());
  }

  @Override
  public String getType()
  {
    return TYPE;
  }

}
