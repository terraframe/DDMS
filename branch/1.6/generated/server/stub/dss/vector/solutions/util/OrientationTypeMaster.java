package dss.vector.solutions.util;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class OrientationTypeMaster extends OrientationTypeMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1208855484;

  public OrientationTypeMaster()
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
