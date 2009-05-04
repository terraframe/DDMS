package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
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

    this.setSprayOperator(s.getSprayOperator());
    this.setOperatorSprayWeek(s.getOperatorSprayWeek());
    this.setReceived(s.getReceived());
    this.setRefills(s.getRefills());
    this.setReturned(s.getReturned());
    this.setUsed(s.getUsed());
  }

  protected void populateSpray(OperatorSpray spray)
  {
    spray.setSprayData(this.getSprayData());
    spray.setSprayOperator(this.getSprayOperator());
    spray.setOperatorSprayWeek(this.getOperatorSprayWeek());
    spray.setReceived(this.getReceived());
    spray.setRefills(this.getRefills());
    spray.setReturned(this.getReturned());
    spray.setUsed(this.getUsed());
  }

  @Override
  @Transaction
  public void apply()
  {
    //Create spray
    AbstractSpray abstractSpray = this.getSpray();

    if(abstractSpray == null)
    {
      abstractSpray = OperatorSpray.findOrCreate(this.getSprayData(), this.getSprayOperator());
      
      if(!abstractSpray.isNew())
      {
        abstractSpray.lock();
      }

      this.populateSpray((OperatorSpray) abstractSpray);

      abstractSpray.apply();
    }

    SprayStatus status = new SprayStatus();

    if(this.hasConcrete())
    {
      status = SprayStatus.lock(this.getStatusId());
    }

    this.populateConcrete(status, abstractSpray);

    status.apply();

    this.populate(status);
  }

  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      SprayStatus.get(this.getStatusId()).delete();
    }
  }

  @Transaction
  public static OperatorSprayStatusView[] applyAll(OperatorSprayStatusView[] views)
  {
    for(OperatorSprayStatusView view : views)
    {
      view.apply();
    }

    return views;
  }

  public static OperatorSprayStatusView search(SprayData data, SprayOperator op)
  {
    OperatorSpray spray = OperatorSpray.find(data, op);

    if (spray != null)
    {
      SprayStatusQuery query = new SprayStatusQuery(new QueryFactory());
      query.WHERE(query.getSpray().EQ(spray));
      query.ORDER_BY_ASC(query.getCreateDate());

      OIterator<? extends SprayStatus> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          SprayStatus status = it.next();

          if (! ( status instanceof HouseholdSprayStatus ))
          {
            return (OperatorSprayStatusView) status.getView();
          }
        }
      }
      finally
      {
        it.close();
      }
    }

    return null;
  }

}
