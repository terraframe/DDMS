package dss.vector.solutions.query;

import com.runwaysdk.dataaccess.transaction.Transaction;

public class WellKnownNamesMaster extends WellKnownNamesMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158252775L;

  public WellKnownNamesMaster()
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
