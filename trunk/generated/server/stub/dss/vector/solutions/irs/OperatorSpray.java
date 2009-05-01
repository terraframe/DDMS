package dss.vector.solutions.irs;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;


public class OperatorSpray extends OperatorSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853391117L;

  public OperatorSpray()
  {
    super();
  }


  public OperatorSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public OperatorSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public OperatorSprayView getView()
  {
    OperatorSprayView view = new OperatorSprayView();

    this.populateView(view);

    return view;
  }

  public void populateView(OperatorSprayView view)
  {
    SprayData data = this.getSprayData();
    view.setBrand(data.getBrand());
    view.setGeoEntity(data.getGeoEntity());
    view.setSprayDate(data.getSprayDate());

    view.clearSprayMethod();
    view.clearSurfaceType();

    for(SprayMethod method : data.getSprayMethod())
    {
      view.addSprayMethod(method);
    }

    for(SurfaceType type : data.getSurfaceType())
    {
      view.addSurfaceType(type);
    }

    view.setOperatorSprayWeek(this.getOperatorSprayWeek());
    view.setReceived(this.getReceived());
    view.setRefills(this.getRefills());
    view.setReturned(this.getReturned());
    view.setSprayOperator(this.getSprayOperator());
    view.setTarget(this.getTarget());
    view.setTeamSprayWeek(this.getTeamSprayWeek());
    view.setUsed(this.getUsed());

    view.setSprayId(this.getId());
    view.setDataId(data.getId());
  }

  @Override
  public SprayStatusView getSprayStatusView()
  {
    return new OperatorSprayStatusView();
  }

  public static OperatorSprayView getView(String id)
  {
    return OperatorSpray.get(id).getView();
  }

  public static OperatorSpray find(SprayData data, SprayOperator operator)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayData().EQ(data));
    query.AND(query.getSprayOperator().EQ(operator));

    OIterator<? extends OperatorSpray> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public static OperatorSpray findOrCreate(SprayData data, SprayOperator operator)
  {
    OperatorSpray spray = OperatorSpray.find(data, operator);

    if(spray == null)
    {
      spray = new OperatorSpray();
    }

    return spray;
  }
}
