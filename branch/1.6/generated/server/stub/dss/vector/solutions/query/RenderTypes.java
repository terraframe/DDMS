package dss.vector.solutions.query;

import com.runwaysdk.dataaccess.transaction.Transaction;

public class RenderTypes extends RenderTypesBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -34058757;

  public RenderTypes()
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
