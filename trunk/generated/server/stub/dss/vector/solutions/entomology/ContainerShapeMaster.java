package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class ContainerShapeMaster extends ContainerShapeMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 245386940;

  public ContainerShapeMaster()
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
