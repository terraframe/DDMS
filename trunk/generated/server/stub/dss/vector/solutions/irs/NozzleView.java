package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.transaction.Transaction;

public class NozzleView extends NozzleViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597925945L;

  public NozzleView()
  {
    super();
  }

  public void populateConcrete(Nozzle nozzle)
  {
    nozzle.setDisplayLabel(this.getDisplayLabel());
    nozzle.setEnabled(this.getEnabled());
    nozzle.setRatio(this.getRatio());
  }

  @Override
  @Transaction
  public void apply()
  {
    Nozzle nozzle = new Nozzle();

    if(this.hasConcrete())
    {
      nozzle = Nozzle.lock(this.getNozzleId());
    }

    this.populateConcrete(nozzle);

    nozzle.apply();
    nozzle.populateView(this);
  }

  @Override
  @Transaction
  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      Nozzle.get(this.getNozzleId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getNozzleId() != null && !this.getNozzleId().equals("");
  }
  
  @Transaction
  public static NozzleView[] getAllActive()
  {
    return NozzleView.getViews(Nozzle.getAllActive());
  }


  @Transaction
  public static NozzleView[] getAll()
  {
    return NozzleView.getViews(Nozzle.getAll());
  }

  private static NozzleView[] getViews(Nozzle[] nozzels)
  {
    NozzleView[] views = new NozzleView[nozzels.length];
    
    for(int i = 0; i < nozzels.length; i++)
    {
      views[i] = nozzels[i].getView();
    }
    
    return views;
  }

  @Transaction
  public static NozzleView[] applyAll(NozzleView[] nozzles)
  {
    for(NozzleView view : nozzles)
    {
      view.apply();
    }

    return nozzles;
  }

}
