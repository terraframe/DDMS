package dss.vector.solutions.report.serialization;

import java.util.Collection;
import java.util.List;

import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterSelectionChoice;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CascadingScalarParameterSerializer extends ScalarParameterSerializer
{
  private List<Object> cascadingValues;
  
  private String groupName;
  
  public CascadingScalarParameterSerializer(IGetParameterDefinitionTask task, IReportRunnable design, IScalarParameterDefn scalar, List<Object> cascadingValues, String groupName)
  {
    super(task, design, scalar);
    
    this.cascadingValues = cascadingValues;
    this.groupName = groupName;
  }
  
  public JSONArray optionsToJSON(Object defaultValue, String convertedDefaultValue) throws JSONException
  {
    JSONArray options = new JSONArray();
    
    @SuppressWarnings("unchecked")
    Collection<IParameterSelectionChoice> sList = this.getTask().getSelectionListForCascadingGroup(groupName, cascadingValues.toArray());

    boolean didAddCascaded = false;
    for (IParameterSelectionChoice sI : sList)
    {
      String label = sI.getLabel();
      Object value = sI.getValue();

      JSONObject object = new JSONObject();
      object.put("label", label);
      object.put("value", value);
      options.put(object);
      
      if (!didAddCascaded)
      {
        if (defaultValue != null)
        {
          cascadingValues.add(defaultValue);
        }
        else
        {
          cascadingValues.add(value);
        }
        didAddCascaded = true;
      }
    }

    return options;
  }
}
