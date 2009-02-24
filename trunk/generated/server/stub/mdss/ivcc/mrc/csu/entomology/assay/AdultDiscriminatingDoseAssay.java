package mdss.ivcc.mrc.csu.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mdss.ivcc.mrc.csu.entomology.assay.ADDATestIntervalQuery;
import mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayBase;

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

      InvalidTestDateProblem p = new InvalidTestDateProblem(msg);
      p.setTestDate(this.getTestDate());
      p.setCollectionDate(collectionDate);
      p.setAssayId(this.getId());
      p.throwIt();
    }
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    if (this.getQuantityDead() > this.getQuantityTested())
    {
      String msg = "It is impossible to have a dead quantity larger than the total number of mosquitos tested";

      InvalidDeadQuantityProblem p = new InvalidDeadQuantityProblem(msg);
      p.setAssayId(this.getId());
      p.setQuantityDead(this.getQuantityDead());
      p.setQuantityTested(this.getQuantityTested());
      p.throwIt();
    }
  }

  @Override
  public void validateIntervalTime()
  {
    if (this.getIntervalTime() > this.getExposureTime())
    {
      String msg = "It is impossible to have an interval time larger than the exposure time";

      InvalidIntervalTimeProblem p = new InvalidIntervalTimeProblem(msg);
      p.setIntervalTime(this.getIntervalTime());
      p.setExposureTime(this.getExposureTime());
      p.setAssayId(this.getId());
      p.throwIt();
    }
  }

  @Override
  public void apply()
  {
    validateCollection();
    validateSex();

    validateTestDate();
    validateIntervalTime();
    validateQuantityDead();

    if (this.getQuantityDead() <= this.getQuantityTested())
    {
      this.setQuantityLive(this.getQuantityTested() - this.getQuantityDead());
      this.setMortality(this.getQuantityDead() / ( (float) this.getQuantityTested() ));
    }
    else
    {
      this.setQuantityLive(0);
      this.setMortality(new Float(0));
    }
    
    super.apply();

    // CREATE Test Intervals
    int periods = this.calculatePeriod();

    for (int i = 0; i < periods; i++)
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(this);
      interval.setPeriod(i);
      interval.setKnockedDown(0);
      interval.apply();
    }
  }

  public Integer calculatePeriod()
  {
    double exposureTime = (double) this.getExposureTime();
    Integer intervalTime = this.getIntervalTime();

    return (int) Math.ceil(exposureTime / intervalTime) + 1;
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
