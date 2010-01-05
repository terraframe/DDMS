package dss.vector.solutions.irs;

public class AreaStandards extends AreaStandardsBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597940844L;

  public AreaStandards()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  public void populateView(AreaStandardsView view)
  {
    view.setRoom(this.getRoom());
    view.setStructureArea(this.getStructureArea());
    view.setHousehold(this.getHousehold());
    view.setUnitNozzleAreaCoverage(this.getUnitNozzleAreaCoverage());
    view.setAreaStandardsId(this.getId());

    view.clearTargetUnit();

    for (TargetUnit unit : this.getTargetUnit())
    {
      view.addTargetUnit(unit);
    }
  }

  public AreaStandardsView getView()
  {
    AreaStandardsView view = new AreaStandardsView();

    this.populateView(view);

    return view;
  }

  public static AreaStandardsView getView(String id)
  {
    return AreaStandards.get(id).getView();
  }

  public static AreaStandardsView lockView(String id)
  {
    return AreaStandards.lock(id).getView();
  }

  public static AreaStandardsView unlockView(String id)
  {
    return AreaStandards.unlock(id).getView();
  }

}
