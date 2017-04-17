package dss.vector.solutions;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class MonthOfYearMaster extends MonthOfYearMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -280373836;

  public MonthOfYearMaster()
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
