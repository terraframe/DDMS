package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class SurfaceTypeMaster extends SurfaceTypeMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597914271L;

  public SurfaceTypeMaster()
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
