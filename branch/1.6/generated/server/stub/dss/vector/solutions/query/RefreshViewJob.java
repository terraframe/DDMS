package dss.vector.solutions.query;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdTable;
import com.runwaysdk.system.scheduler.ExecutionContext;

public class RefreshViewJob extends RefreshViewJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1212923408;

  public RefreshViewJob()
  {
    super();
  }

  @Override
  public void apply()
  {
    MdTable mdTable = this.getMaterializedTable();

    if (mdTable != null)
    {
      String jobName = mdTable.getDisplayLabel().getValue();

      this.setJobId(jobName);
      this.getDescription().setDefaultValue(jobName);
    }

    super.apply();
  }

  @Override
  public void execute(ExecutionContext executionContext)
  {
    SavedSearch search = SavedSearch.getSavedSearch(this.getMaterializedTable());

    if (search != null)
    {
      search.refreshMaterializedView();
    }
  }

  public static RefreshViewJob getJob(MdTable materializedTable)
  {
    RefreshViewJobQuery query = new RefreshViewJobQuery(new QueryFactory());
    query.WHERE(query.getMaterializedTable().EQ(materializedTable));

    OIterator<? extends RefreshViewJob> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
    }
    finally
    {
      iterator.close();
    }

    return null;
  }

}
