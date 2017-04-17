package dss.vector.solutions;

import java.util.List;

import com.runwaysdk.business.BusinessEnumeration;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.SavedSearch;

public class ResponseMaster extends ResponseMasterBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 976308167;

  public ResponseMaster()
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

  public static String getValueForErrorMsg(List<? extends BusinessEnumeration> list)
  {
    String value = "";

    for (BusinessEnumeration response : list)
    {
      value += "/" + response.getDisplayLabel();
    }

    value = value.replaceFirst("/", "");
    return value;
  }
}
