package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.BasicCondition;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;

public class OperatorSprayStatusView extends OperatorSprayStatusViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860663695L;

  public OperatorSprayStatusView()
  {
    super();
  }

  public void populate(SprayStatus status)
  {
    super.populate(status);

    OperatorSpray s = (OperatorSpray) status.getSpray();
    SprayOperator operator = s.getSprayOperator();

    this.populate(operator);
    this.setOperatorSprayWeek(s.getOperatorSprayWeek());
  }

  public void populate(SprayOperator operator)
  {
    this.setSprayOperator(operator);
  }

  protected void populateSpray(OperatorSpray spray)
  {
    super.populateSpray(spray);

    spray.setSprayOperator(this.getSprayOperator());
    spray.setOperatorSprayWeek(this.getOperatorSprayWeek());
  }

  @Override
  @Transaction
  public void apply()
  {
    // The spray must be created before a status can be attached to it.
    this.applySpray();

    ActorSprayStatus status = new ActorSprayStatus();

    if (this.hasConcrete())
    {
      status = ActorSprayStatus.lock(this.getStatusId());
    }

    this.populateConcrete(status);

    status.apply();

    this.populate(status);
  }

  private void applySpray()
  {
    AbstractSpray spray = this.getSpray();

    if (spray == null)
    {
      spray = new OperatorSpray();
    }
    else
    {
      spray.lock();
    }

    this.populateSpray((OperatorSpray) spray);

    spray.apply();

    this.setSpray(spray);
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      SprayStatus.get(this.getStatusId()).delete();
    }
  }

  @Transaction
  public static OperatorSprayStatusView[] applyAll(OperatorSprayStatusView[] views)
  {
    for (OperatorSprayStatusView view : views)
    {
      view.apply();
    }

    return views;
  }

  public static OperatorSprayStatusView[] search(SprayData data, SprayOperator... operators)
  {
    List<OperatorSprayStatusView> list = new LinkedList<OperatorSprayStatusView>();

    QueryFactory factory = new QueryFactory();

    OperatorSprayQuery operatorQuery = new OperatorSprayQuery(factory);
    operatorQuery.WHERE(operatorQuery.getSprayData().EQ(data));
    operatorQuery.AND(OperatorSprayStatusView.buildOrCondition(operatorQuery, operators));

    SprayStatusQuery query = new SprayStatusQuery(factory);
    query.WHERE(query.getSpray().EQ(operatorQuery));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends SprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        SprayStatus status = it.next();

        if (! ( status instanceof HouseholdSprayStatus ))
        {
          list.add((OperatorSprayStatusView) status.getView());
        }
      }
    }
    finally
    {
      it.close();
    }

    return list.toArray(new OperatorSprayStatusView[list.size()]);
  }

  private static Condition buildOrCondition(OperatorSprayQuery query, SprayOperator... operators)
  {
    Condition or = null;

    for (SprayOperator operator : operators)
    {
      BasicCondition condition = query.getSprayOperator().EQ(operator);

      if (or == null)
      {
        or = condition;
      }
      else
      {
        or = OR.get(or, condition);
      }
    }
    return or;
  }

}
