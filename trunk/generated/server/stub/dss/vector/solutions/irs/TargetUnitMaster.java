package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class TargetUnitMaster extends TargetUnitMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1243292741979L;

  public TargetUnitMaster()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    super.apply();

    SavedSearch.updateSavedSearchIds(this);
  }
}
