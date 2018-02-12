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
package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdWebForm;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.kaleidoscope.MappableClass;

public class ProblemResponse implements ImportResponseIF, Reloadable
{
  private Collection<ImportProblemIF> problems;

  private SourceContextIF             sContext;

  private TargetContextIF             tContext;

  private Disease                     current;

  public ProblemResponse(Collection<ImportProblemIF> problems, SourceContextIF sContext, TargetContextIF tContext, Disease current)
  {
    this.problems = problems;
    this.sContext = sContext;
    this.tContext = tContext;
    this.current = current;
  }
  
  public Collection<ImportProblemIF> getProblems()
  {
    return problems;
  }

  public String getJSON()
  {
    try
    {
      JSONObject object = this.toJSON();

      return object.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public JSONObject toJSON() throws JSONException
  {
    /*
     * Return a JSONArray of the datasets which were created a part of the import. Do not include datasets which have already been created.
     */
    JSONArray datasets = new JSONArray();

    List<TargetDefinitionIF> definitions = tContext.getDefinitions();

    for (TargetDefinitionIF definition : definitions)
    {
      if (definition.isNew())
      {
        String type = definition.getTargetType();

        MdWebForm mdForm = MdWebForm.getByKey(type);
        MdClass mdClass = mdForm.getFormMdClass();

        MappableClass mClass = MappableClass.getMappableClass(mdClass, this.current);

        datasets.put(mClass.toJSON());
      }
    }

    JSONObject object = new JSONObject();
    object.put("success", false);
    object.put("sheets", this.getSheetsJSON());
    object.put("problems", this.getProblemsJSON());
    object.put("datasets", datasets);

    return object;
  }

  @Override
  public boolean hasProblems()
  {
    return true;
  }

  private JSONObject getProblemsJSON() throws JSONException
  {
    Map<String, JSONArray> map = new HashMap<String, JSONArray>();
    map.put(LocationProblem.TYPE, new JSONArray());
    map.put(CategoryProblem.TYPE, new JSONArray());

    JSONObject options = new JSONObject();

    for (ImportProblemIF problem : this.problems)
    {
      map.putIfAbsent(problem.getType(), new JSONArray());

      map.get(problem.getType()).put(problem.toJSON());
    }

    JSONObject object = new JSONObject();
    object.put("options", options);

    Set<Entry<String, JSONArray>> entries = map.entrySet();

    for (Entry<String, JSONArray> entry : entries)
    {
      object.put(entry.getKey(), entry.getValue());
    }

    return object;
  }

  private JSONArray getSheetsJSON() throws JSONException
  {
    JSONArray sheets = new JSONArray();

    Collection<SourceDefinitionIF> definitions = this.sContext.getDefinitions();

    for (SourceDefinitionIF sDefinition : definitions)
    {
      TargetDefinitionIF tDefinition = this.tContext.getDefinition(sDefinition.getType());

      DefinitionBuilder builder = new DefinitionBuilder(sDefinition, tDefinition);
      JSONObject sheet = builder.getConfiguration();

      sheets.put(sheet);
    }
    return sheets;
  }
}
