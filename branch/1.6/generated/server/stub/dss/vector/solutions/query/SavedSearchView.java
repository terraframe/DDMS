package dss.vector.solutions.query;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.runwaysdk.dataaccess.ProgrammingErrorException;

public class SavedSearchView extends SavedSearchViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158127140L;

  public SavedSearchView()
  {
    super();
  }

  public List<String> getAttributesToAdd()
  {
    try
    {
      return this.convertJSONToList(this.getAdditiveSelectables());
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public List<String> getAttributeToDelete()
  {
    try
    {
      return this.convertJSONToList(this.getDeleteSelectables());
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private List<String> convertJSONToList(String source) throws JSONException
  {
    LinkedList<String> list = new LinkedList<String>();

    if (source != null && source.length() > 0)
    {
      JSONArray selectables = new JSONArray(source);

      for (int i = 0; i < selectables.length(); i++)
      {
        String selectable = selectables.getString(i);
        String[] split = selectable.split("-!-");
        String type = split[0];
        String alias = split[1];

        list.add(alias);
      }
    }

    return list;
  }
}
