/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
        String alias = split[1];

        list.add(alias);
        

        if (alias.contains("__") && alias.endsWith("_geoId"))
        {
          list.add(alias.replace("_geoId", "_id"));
        }        
      }
    }

    return list;
  }
}
