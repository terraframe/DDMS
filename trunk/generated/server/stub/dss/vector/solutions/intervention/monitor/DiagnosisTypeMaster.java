package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class DiagnosisTypeMaster extends DiagnosisTypeMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1893808505;

  public DiagnosisTypeMaster()
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
