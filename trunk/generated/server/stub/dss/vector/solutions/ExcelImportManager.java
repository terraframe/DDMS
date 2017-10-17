package dss.vector.solutions;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelImporter;

import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.ontology.UnknownTerm;

public class ExcelImportManager extends ExcelImportManagerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long        serialVersionUID        = 792922881;

  private List<UnknownGeoEntity>   unknownEntityList       = new LinkedList<UnknownGeoEntity>();

  private List<UnknownTerm>        unknownTerms            = new LinkedList<UnknownTerm>();

  private Set<String>              unknownGeoEntityNameSet = new HashSet<String>();

  private Map<String, Set<String>> unknownTermNames        = new HashMap<String, Set<String>>();

  public ExcelImportManager()
  {
    super();
  }

  public static dss.vector.solutions.ExcelImportManager getNewInstance()
  {
    ExcelImportManager inst = new ExcelImportManager();
    inst.apply();
    return inst;
  }

  /**
   * MdMethod
   * 
   * @return
   */
  public java.io.InputStream importWhatYouCan(java.io.InputStream inputStream, java.lang.String[] params)
  {
    // Start caching Broswer Roots for this Thread.
    TermRootCache.start();
    EpiCache.start();

    try
    {
      ContextBuilderFacade builder = new ContextBuilderFacade(new DefaultContextBuilder(params, this));

      ExcelImporter importer = new ExcelImporter(inputStream, builder);

      try
      {
        byte[] read = importer.read();

        this.onFinishImport();

        return new ByteArrayInputStream(read);
      }
      catch (RuntimeException e)
      {
        /*
         * Ticket #2663: Errors from reading external sheet should have a better
         * error message. Unfortunately, the HSSF API doesn't throw a specific
         * exception for external sheet errors, but throws a RuntimeException.
         * As such the only way to tell if the exception is an external sheet
         * error is by reading the message.
         */
        Throwable cause = e.getCause();

        if (cause != null && cause.getMessage().startsWith("No external workbook with name"))
        {
          throw new ExcelReadException();
        }

        throw e;
      }
    }
    finally
    {
      TermRootCache.stop();
      EpiCache.stop();
    }
  }

  /**
   * MdMethod
   * 
   * @return
   */
  public dss.vector.solutions.geo.UnknownGeoEntity[] getUnmatchedGeoViews()
  {
    String str = this.getUnmatchedGeoViewIdString();
    if (str == null || str.equals(""))
    {
      return null;
    }
    String[] ids = str.split(","); // if this character is a valid character in
                                   // an id string then we done goofed hardcore
                                   // here

    UnknownGeoEntity[] views = new UnknownGeoEntity[ids.length];
    for (int index = 0; index < ids.length; ++index)
    {
      String id = ids[index];
      views[index] = UnknownGeoEntity.get(id);
    }

    return views;
  }

  @Override
  public UnknownTerm[] getUnknownTerms()
  {
    try
    {
      JSONArray array = new JSONArray(this.getSerializedUnknownTerm());
      UnknownTerm[] terms = new UnknownTerm[array.length()];

      for (int i = 0; i < array.length(); i++)
      {
        JSONObject object = array.getJSONObject(i);

        terms[i] = UnknownTerm.deserialize(object);
      }

      return terms;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public void setUnmatchedGeoViews(List<UnknownGeoEntity> views)
  {
    if (views.size() <= 0)
    {
      return;
    }

    String str = "";

    for (int index = 0; index < ( views.size() - 1 ); ++index)
    {
      str = str + views.get(index).getId() + ",";
    }

    str = str + views.get(views.size() - 1).getId();

    this.setUnmatchedGeoViewIdString(str);
  }

  public void addUnknownEntity(UnknownGeoEntity unknownGeoEntity)
  {
    unknownEntityList.add(unknownGeoEntity);
  }

  public void addUnknownGeoEntityName(String endPointEntityName)
  {
    unknownGeoEntityNameSet.add(endPointEntityName);
  }

  public boolean isGeoEntityNameUnknown(String endPointEntityName)
  {
    return unknownGeoEntityNameSet.contains(endPointEntityName);
  }

  public void addUnknownTerm(UnknownTerm unknownTerm)
  {
    String key = unknownTerm.getBrowserClass() + "." + unknownTerm.getBrowserAttribute();

    this.unknownTermNames.putIfAbsent(key, new TreeSet<String>());

    Set<String> termNames = this.unknownTermNames.get(key);

    if (!termNames.contains(unknownTerm.getTermName()))
    {
      termNames.add(unknownTerm.getTermName());

      this.unknownTerms.add(unknownTerm);
    }
  }

  private String serializeUnknownTerms()
  {
    try
    {
      JSONArray array = new JSONArray();

      for (UnknownTerm uTerm : this.unknownTerms)
      {
        array.put(uTerm.serialize());
      }

      return array.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public void onFinishImport()
  {
    this.setUnmatchedGeoViews(unknownEntityList);
    this.setSerializedUnknownTerm(this.serializeUnknownTerms());

    this.apply();
  }
}
