package mdss.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

public class AdultDiscriminatingDoseAssay extends AdultDiscriminatingDoseAssayBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543769104L;

  public AdultDiscriminatingDoseAssay()
  {
    super();
  }

  @Override
  public void validateTestDate()
  {
    super.validateTestDate();

    Date collectionDate = this.getCollection().getDateCollected();

    if (this.getTestDate().before(collectionDate))
    {
      String msg = "It is impossible to have a test date before the mosquito collection date";
      throw new RuntimeException(msg);
    }
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    if (this.getQuantityDead() > this.getQuantityTested())
    {
      String msg = "It is impossible to have a dead quantity larger than the total number of mosquitos tested";
      throw new RuntimeException(msg);
    }
  }

  @Override
  public void validateIntervalTime()
  {
    if (this.getIntervalTime() > this.getExposureTime())
    {
      String msg = "It is impossible to have an interval time larger than the exposure time";
      throw new RuntimeException(msg);
    }
  }

  @Override
  public void apply()
  {
    validateCollection();
    validateSex();

    validateTestDate();
    validateQuantityDead();
    validateIntervalTime();

    super.apply();
  }

  @Override
  public void delete()
  {
    this.deleteIntevals();

    super.delete();
  }

  private void deleteIntevals()
  {
    for (ADDATestIntervalView view : this.getTestIntervals())
    {
      ADDATestInterval.get(view.getIntervalId()).delete();
    }
  }

  public ADDATestIntervalView[] getTestIntervals()
  {
    List<ADDATestIntervalView> list = new LinkedList<ADDATestIntervalView>();

    ADDATestIntervalQuery query = new ADDATestIntervalQuery(new QueryFactory());
    query.WHERE(query.getAssay().getId().EQ(this.getId()));
    query.ORDER_BY(query.getPeriod(), SortOrder.ASC);

    OIterator<? extends ADDATestInterval> iterator = query.getIterator();

    while (iterator.hasNext())
    {
      ADDATestInterval interval = iterator.next();

      ADDATestIntervalView view = new ADDATestIntervalView();
      view.setIntervalId(interval.getId());
      view.setAssayId(this.getId());
      view.setKnockedDown(interval.getKnockedDown());
      view.setPeriod(interval.getPeriod());
      view.applyNoPersist();

      list.add(view);
    }

    iterator.close();

    return list.toArray(new ADDATestIntervalView[list.size()]);
  }

  @Override
  public Integer getKD50()
  {
    // TODO Use R logistical regression to calculate KD50
    return super.getKD50();
  }

  @Override
  public Integer getKD95()
  {
    // TODO Use R logistical regression to calculate KD95
    return super.getKD95();
  }
}
