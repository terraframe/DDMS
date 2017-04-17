package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class InsecticideBrandUseMaster extends InsecticideBrandUseMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -711430269;

  public InsecticideBrandUseMaster()
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
