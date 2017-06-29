package dss.vector.solutions.kaleidoscope.dashboard.condition;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AttributeBoolean;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.util.LocalizationFacade;

public class BooleanCondition extends DashboardAttributeCondition implements Reloadable
{
  public static final String CONDITION_TYPE = "BOOLEAN_CONDITION";

  private Boolean            yes;

  private Boolean            no;

  private Boolean            none;

  public BooleanCondition(String mdAttributeId, JSONObject value)
  {
    super(mdAttributeId);

    try
    {
      yes = value.has("yes") ? value.getBoolean("yes") : false;
      no = value.has("no") ? value.getBoolean("no") : false;
      none = value.has("none") ? value.getBoolean("none") : false;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public JSONObject getJSON()
  {
    try
    {
      JSONObject value = new JSONObject();
      value.put("yes", yes);
      value.put("no", no);
      value.put("none", none);

      JSONObject object = new JSONObject();
      object.put(TYPE_KEY, CONDITION_TYPE);
      object.put(MD_ATTRIBUTE_KEY, this.getMdAttributeId());
      object.put(VALUE_KEY, value);

      return object;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public void restrictQuery(ValueQuery vQuery, Selectable attribute)
  {
    List<Condition> conditions = new LinkedList<Condition>();

    if (yes)
    {
      conditions.add( ( (AttributeBoolean) attribute ).EQ(true));
    }

    if (no)
    {
      conditions.add( ( (AttributeBoolean) attribute ).EQ(false));
    }

    if (none)
    {
      conditions.add( ( (AttributeBoolean) attribute ).EQ((Boolean) null));
    }

    if (conditions.size() > 0)
    {
      if (conditions.size() == 1)
      {
        vQuery.AND(conditions.get(0));
      }
      else
      {
        vQuery.AND(OR.get(conditions.toArray(new Condition[conditions.size()])));
      }
    }
  }

  @Override
  public List<String> getConditionInformation()
  {
    List<String> messages = new LinkedList<String>();

    if (yes)
    {
      DashboardEqualCondition condition = new DashboardEqualCondition(this.getMdAttributeId(), LocalizationFacade.getFromBundles("filter.yes"));

      messages.addAll(condition.getConditionInformation());
    }

    if (no)
    {
      DashboardEqualCondition condition = new DashboardEqualCondition(this.getMdAttributeId(), LocalizationFacade.getFromBundles("filter.no"));

      messages.addAll(condition.getConditionInformation());
    }

    if (none)
    {
      DashboardEqualCondition condition = new DashboardEqualCondition(this.getMdAttributeId(), LocalizationFacade.getFromBundles("filter.none"));

      messages.addAll(condition.getConditionInformation());
    }

    return messages;
  }

}
