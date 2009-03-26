package dss.vector.solutions.entomology.assay;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

public abstract class AdultAssay extends AdultAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237579415849L;
  
  public AdultAssay()
  {
    super();
  }
  
  @Override
  public void validateAgeRange()
  {
    super.validateAgeRange();

    new AdultAgeRangeValidator(this).validate();
  }

  @Override
  public void validateGravid()
  {
    super.validateGravid();

    new GravidValidator(this).validate();
  }

  @Override
  public void validateFed()
  {
    super.validateFed();

    new FedValidator(this).validate();
  }

  
  @Override
  public void apply()
  {
    validateAgeRange();
    validateFed();
    validateGravid();
    
    boolean firstApply = this.isNew() && !this.isAppliedToDB();
    
    super.apply();
    
    // CREATE Test Intervals
    if (firstApply)
    {
      int periods = this.calculatePeriod();

      for (int i = 0; i < periods; i++)
      {
        AdultTestInterval interval = new AdultTestInterval();
        interval.setAssay(this);
        interval.setPeriod(i);
        interval.setKnockedDown(0);
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
    for (AdultTestIntervalView view : this.getTestIntervals())
    {
      AdultTestInterval.get(view.getIntervalId()).delete();
    }
  }
  
  public AdultTestIntervalView[] getTestIntervals()
  {
    List<AdultTestIntervalView> list = new LinkedList<AdultTestIntervalView>();

    AdultTestIntervalQuery query = new AdultTestIntervalQuery(new QueryFactory());
    query.WHERE(query.getAssay().getId().EQ(this.getId()));
    query.ORDER_BY(query.getPeriod(), SortOrder.ASC);

    OIterator<? extends AdultTestInterval> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        AdultTestInterval interval = iterator.next();

        AdultTestIntervalView view = new AdultTestIntervalView();
        view.setIntervalId(interval.getId());
        view.setAssay(this);
        view.setKnockedDown(interval.getKnockedDown());
        view.setPeriod(interval.getPeriod());
        view.setIntervalTime(interval.getIntervalTime());
        view.applyNoPersist();

        list.add(view);
      }

      return list.toArray(new AdultTestIntervalView[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }
}