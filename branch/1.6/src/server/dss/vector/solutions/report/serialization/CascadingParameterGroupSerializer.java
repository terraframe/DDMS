package dss.vector.solutions.report.serialization;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.birt.report.engine.api.ICascadingParameterGroup;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CascadingParameterGroupSerializer extends ParameterGroupSerializer
{
  public static final String PARAMETER_TYPE_CASCADING_PARAMETER_GROUP = "CascadingParameterGroup";
  
  private ArrayList<Object> cascadingValues;
  
  public CascadingParameterGroupSerializer(IGetParameterDefinitionTask task, IReportRunnable design, ICascadingParameterGroup group)
  {
    super(task, design, group);
    
    cascadingValues = new ArrayList<Object>();
  }
  
  public JSONObject toJSON() throws JSONException
  {
    JSONObject json = new JSONObject();
    json.put(ParameterSerializer.PARAMETER_TYPE, PARAMETER_TYPE_CASCADING_PARAMETER_GROUP);
    json.put(GROUP_NAME, this.getGroup().getName());
    
    JSONArray jsonContents = new JSONArray();
    
    @SuppressWarnings("unchecked")
    ArrayList<IScalarParameterDefn> contents = this.getGroup().getContents();

    Iterator<IScalarParameterDefn> it = contents.iterator();

    while (it.hasNext())
    {
      IScalarParameterDefn scalar = it.next();

      jsonContents.put(new CascadingScalarParameterSerializer(this.getTask(), this.getDesign(), scalar, cascadingValues, this.getGroup().getName()).toJSON());
    }
    
    json.put(CONTENTS, jsonContents);
    
    return json;
  }
}
