package dss.vector.solutions.entomology.assay;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

public abstract class LarvaeAssay extends LarvaeAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237579421887L;
  
  public LarvaeAssay()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    boolean firstApply = this.isNew() && !this.isAppliedToDB();
    
    super.apply();
    
    // CREATE Test Intervals
    if (firstApply)
    {
      int periods = this.calculatePeriod();

      for (int i = 0; i < periods; i++)
      {
        LarvaeTestInterval interval = new LarvaeTestInterval();
        interval.setAssay(this);
        interval.setPeriod(i);
        interval.setQuantityDead(0);
        interval.apply();
      }
    }
  }
  
  @Override
  public void delete()
  {
    this.deleteIntevals();

    super.delete();
  }
  
  private void deleteIntevals()
  {
    for (LarvaeTestIntervalView view : this.getTestIntervals())
    {
      LarvaeTestInterval.get(view.getIntervalId()).delete();
    }
  }
  
  public LarvaeTestIntervalView[] getTestIntervals()
  {
    List<LarvaeTestIntervalView> list = new LinkedList<LarvaeTestIntervalView>();

    LarvaeTestIntervalQuery query = new LarvaeTestIntervalQuery(new QueryFactory());
    query.WHERE(query.getAssay().getId().EQ(this.getId()));
    query.ORDER_BY(query.getPeriod(), SortOrder.ASC);

    OIterator<? extends LarvaeTestInterval> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        LarvaeTestInterval interval = iterator.next();

        LarvaeTestIntervalView view = new LarvaeTestIntervalView();
        view.setIntervalId(interval.getId());
        view.setAssay(this);
        view.setQuantityDead(interval.getQuantityDead());
        view.setPeriod(interval.getPeriod());
        view.setIntervalTime(interval.getIntervalTime());
        view.applyNoPersist();

        list.add(view);
      }

      return list.toArray(new LarvaeTestIntervalView[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  
}
