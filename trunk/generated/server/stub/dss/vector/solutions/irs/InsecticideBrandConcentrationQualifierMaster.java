package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class InsecticideBrandConcentrationQualifierMaster extends InsecticideBrandConcentrationQualifierMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 779520207;

  public InsecticideBrandConcentrationQualifierMaster()
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
