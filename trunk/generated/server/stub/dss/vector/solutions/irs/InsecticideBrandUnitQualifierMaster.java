package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class InsecticideBrandUnitQualifierMaster extends InsecticideBrandUnitQualifierMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 91724754;

  public InsecticideBrandUnitQualifierMaster()
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
