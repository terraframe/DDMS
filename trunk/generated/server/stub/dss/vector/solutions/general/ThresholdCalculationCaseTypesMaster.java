package dss.vector.solutions.general;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class ThresholdCalculationCaseTypesMaster extends ThresholdCalculationCaseTypesMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1188596859;

  public ThresholdCalculationCaseTypesMaster()
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
