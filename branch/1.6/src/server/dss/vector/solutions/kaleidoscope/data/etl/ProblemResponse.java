package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdAttributeReferenceDAO;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.kaleidoscope.MappableClass;
import dss.vector.solutions.ontology.Term;

public class ProblemResponse implements ImportResponseIF, Reloadable
{
  private Collection<ImportProblemIF> problems;

  private SourceContextIF             sContext;

  private TargetContextIF             tContext;

  public ProblemResponse(Collection<ImportProblemIF> problems, SourceContextIF sContext, TargetContextIF tContext)
  {
    this.problems = problems;
    this.sContext = sContext;
    this.tContext = tContext;
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

        MdBusinessDAOIF mdBusiness = MdBusinessDAO.getMdBusinessDAO(type);
        MappableClass mClass = MappableClass.getMappableClass(mdBusiness);

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
    Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
    map.put(LocationProblem.TYPE, new LinkedList<JSONObject>());
    map.put(CategoryProblem.TYPE, new LinkedList<JSONObject>());

    JSONObject options = new JSONObject();

    for (ImportProblemIF problem : this.problems)
    {
      map.putIfAbsent(problem.getType(), new LinkedList<JSONObject>());

      map.get(problem.getType()).add(problem.toJSON());

      if (problem instanceof CategoryProblem)
      {
        CategoryProblem cProblem = (CategoryProblem) problem;

        /*
         * Load all of the options for this attribute
         */
        if (!options.has(cProblem.getMdAttributeId()))
        {
          // Serialized JSON array of all the classifier options for this mdAttribute
          JSONArray array = new JSONArray();

          MdAttributeReferenceDAOIF mdAttributeTerm = MdAttributeReferenceDAO.get(cProblem.getMdAttributeId());
          Term[] children = Term.getRootChildren(mdAttributeTerm, true);

          for (Term child : children)
          {
            JSONObject option = new JSONObject();
            option.put("label", child.getTermDisplayLabel().getValue());
            option.put("id", child.getId());

            array.put(option);
          }

          options.put(cProblem.getMdAttributeId(), array);
        }

      }
    }

    JSONObject object = new JSONObject(map);
    object.put("options", options);

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
