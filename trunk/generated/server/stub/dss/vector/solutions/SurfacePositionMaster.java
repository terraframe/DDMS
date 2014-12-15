package dss.vector.solutions;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class SurfacePositionMaster extends SurfacePositionMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236363365604L;

  public SurfacePositionMaster()
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
