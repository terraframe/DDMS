package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

public class Nozzle extends NozzleBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597892193L;

  public Nozzle()
  {
    super();
  }

  public void populateView(NozzleView view)
  {
    view.setDisplayLabel(this.getDisplayLabel());
    view.setEnabled(this.getEnabled());
    view.setNozzleId(this.getId());
    view.setRatio(this.getRatio());
  }

  public NozzleView getView()
  {
    NozzleView view = new NozzleView();

    this.populateView(view);

    return view;
  }

  @Transaction
  public static NozzleView getView(String id)
  {
    return Nozzle.get(id).getView();
  }

  @Transaction
  public static NozzleView unlockView(String id)
  {
    return Nozzle.unlock(id).getView();
  }

  @Transaction
  public static NozzleView lockView(String id)
  {
    return Nozzle.lock(id).getView();
  }
}
