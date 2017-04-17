package dss.vector.solutions.kaleidoscope.dashboard;

import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardCondition;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardEqualCondition;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardGreaterThanCondition;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeCharDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeNumberDAO;

public class MdAttributeView extends MdAttributeViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1311378616;

  public MdAttributeView()
  {
    super();
  }

  public JSONObject toJSON(DashboardCondition condition) throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("attributeType", this.getAttributeType());
    object.put("mdAttributeId", this.getMdAttributeId());
    object.put("attributeName", this.getAttributeName());
    object.put("label", this.getDisplayLabel());

    if (condition != null)
    {
      object.put("filter", condition.getJSON());
    }
    else
    {
      MdAttributeConcreteDAOIF mdAttributeConcrete = MdAttributeDAO.get(this.getMdAttributeId()).getMdAttributeConcrete();

      JSONObject filter = new JSONObject();

      if (mdAttributeConcrete instanceof MdAttributeNumberDAO)
      {
        filter.put(DashboardCondition.OPERATION_KEY, DashboardGreaterThanCondition.OPERATION);
      }
      else if (mdAttributeConcrete instanceof MdAttributeCharDAOIF)
      {
        filter.put(DashboardCondition.OPERATION_KEY, DashboardEqualCondition.OPERATION);
      }

      object.put("filter", filter);
    }

    return object;
  }
}
