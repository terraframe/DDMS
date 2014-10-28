package dss.vector.solutions.query;

import com.runwaysdk.dataaccess.transaction.Transaction;

public class CycleJobView extends CycleJobViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -702672080;

  public CycleJobView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    if (this.getCreateJob())
    {
      CycleJob concrete = this.getConcrete();

      if (concrete.isAppliedToDB())
      {
        concrete.lock();
      }

      concrete.populate(this);
      concrete.apply();

      this.populate(concrete);
    }
    else
    {
      this.delete();
    }
  }

  @Override
  public void delete()
  {
    if (this.getConcreteId() != null && this.getConcreteId().length() > 0)
    {
      CycleJob concrete = this.getConcrete();
      concrete.delete();
    }
  }

  /**
   * Populates the contents of the view from the supplied job
   * 
   * @param concrete
   */
  public void populate(CycleJob job)
  {
    this.setCreateJob(false);
    this.setJobName(job.getJobName());
    this.setSavedMap(job.getSavedMap());
    this.setLayerId(job.getLayerId());

    if (job.isAppliedToDB())
    {
      this.setCreateJob(true);
      this.setConcreteId(job.getId());
    }
  }

  public CycleJob getConcrete()
  {
    if (this.getConcreteId() != null && this.getConcreteId().length() > 0)
    {
      return CycleJob.get(this.getConcreteId());
    }

    return new CycleJob();
  }

}
