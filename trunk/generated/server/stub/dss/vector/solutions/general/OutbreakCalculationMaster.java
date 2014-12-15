package dss.vector.solutions.general;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class OutbreakCalculationMaster extends OutbreakCalculationMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256749759863L;

  public OutbreakCalculationMaster()
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
