package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class ITNRetreatmentPeriod extends ITNRetreatmentPeriodBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252970229353L;
  
  public ITNRetreatmentPeriod()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getPeriodName();
  }
  
  public static ITNRetreatmentPeriod[] getAll()
  {
    ITNRetreatmentPeriodQuery query = new ITNRetreatmentPeriodQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getPeriodName());

    return ITNRetreatmentPeriod.convertQueryToArray(query);
  }
  
  public static ITNRetreatmentPeriod[] getAllActive()
  {
    ITNRetreatmentPeriodQuery query = new ITNRetreatmentPeriodQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getPeriodName());

    return ITNRetreatmentPeriod.convertQueryToArray(query);
  }

  private static ITNRetreatmentPeriod[] convertQueryToArray(ITNRetreatmentPeriodQuery query)
  {
    List<ITNRetreatmentPeriod> ITNRetreatmentPeriods = new LinkedList<ITNRetreatmentPeriod>();
    OIterator<? extends ITNRetreatmentPeriod> it = query.getIterator();

    try
    {
      while(it.hasNext())
      {
        ITNRetreatmentPeriods.add(it.next());
      }

      return ITNRetreatmentPeriods.toArray(new ITNRetreatmentPeriod[ITNRetreatmentPeriods.size()]);
    }
    finally
    {
      it.close();
    }
  }

}
