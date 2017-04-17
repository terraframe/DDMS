package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class LifeStageMaster extends LifeStageMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 481381685;

  public LifeStageMaster()
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
