package dss.vector.solutions.report.serialization;

import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.json.JSONException;
import org.json.JSONObject;

abstract public class ParameterSerializer
{
  /**
   * The json key that tells what type of parameter this is;
   */
  public static final String PARAMETER_TYPE = "parameterType";
  
  private IGetParameterDefinitionTask task;
  
  private IReportRunnable design;
  
  public ParameterSerializer(IGetParameterDefinitionTask task, IReportRunnable design)
  {
     this.task = task;
     this.design = design;
  }
  
  /**
   * @return the task
   */
  public IGetParameterDefinitionTask getTask()
  {
    return task;
  }

  /**
   * @param task the task to set
   */
  public void setTask(IGetParameterDefinitionTask task)
  {
    this.task = task;
  }

  /**
   * @return the design
   */
  public IReportRunnable getDesign()
  {
    return design;
  }

  /**
   * @param design the design to set
   */
  public void setDesign(IReportRunnable design)
  {
    this.design = design;
  }
  
  abstract public JSONObject toJSON() throws JSONException;
}
