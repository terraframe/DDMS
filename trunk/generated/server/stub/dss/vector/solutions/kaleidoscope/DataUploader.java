package dss.vector.solutions.kaleidoscope;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.RunwayException;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.SmartException;
import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.constants.VaultProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdClassQuery;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.DataUploaderImportJob;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoSynonym;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geoserver.SessionPredicate;
import dss.vector.solutions.kaleidoscope.data.etl.DefinitionBuilder;
import dss.vector.solutions.kaleidoscope.data.etl.ExcelSourceBinding;
import dss.vector.solutions.kaleidoscope.data.etl.ImportResponseIF;
import dss.vector.solutions.kaleidoscope.data.etl.SourceDefinitionIF;
import dss.vector.solutions.kaleidoscope.data.etl.TargetDefinitionIF;
import dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelDataFormatter;
import dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelSheetReader;
import dss.vector.solutions.kaleidoscope.data.etl.excel.FieldInfoContentsHandler;
import dss.vector.solutions.kaleidoscope.data.etl.excel.InvalidExcelFileException;
import dss.vector.solutions.kaleidoscope.data.etl.excel.JobHistoryProgressMonitor;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermSynonym;

public class DataUploader extends DataUploaderBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1960517297;

  public DataUploader()
  {
    super();
  }

  @Transaction
  @Authenticate
  public static String createGeoEntity(String parentId, String universalId, String label)
  {
    GeoHierarchy universal = GeoHierarchy.get(universalId);
    MdBusiness mdClass = universal.getGeoEntityClass();

    GeoEntity entity = (GeoEntity) BusinessFacade.newBusiness(mdClass.definesType());
    entity.setGeoId(LocalProperty.getNextId());
    entity.getEntityLabel().setDefaultValue(label);
    entity.applyWithParent(parentId, false, null);

    return entity.getId();
  }

  @Transaction
  @Authenticate
  public static void deleteGeoEntity(String entityId)
  {
    GeoEntity.deleteEntity(entityId);
  }

  @Transaction
  @Authenticate
  public static String createGeoEntitySynonym(String entityId, String label)
  {
    try
    {
      GeoEntity entity = GeoEntity.get(entityId);
      String synonymId = entity.addSynonym(label);

      JSONObject object = new JSONObject();
      object.put("synonymId", synonymId);
      object.put("label", entity.getEntityLabel().getValue());
      object.put("ancestors", new JSONArray(DataUploader.getAncestorsAsJSON(entity)));

      return object.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Transaction
  @Authenticate
  public static void deleteGeoEntitySynonym(String synonymId)
  {
    GeoSynonym synonym = GeoSynonym.get(synonymId);
    synonym.delete();
  }

  @Transaction
  @Authenticate
  public static String createTermSynonym(String classifierId, String label)
  {
    try
    {
      Term classifier = Term.get(classifierId);      
      String synonymId = classifier.addSynonym(label);

      JSONObject object = new JSONObject();
      object.put("synonymId", synonymId);
      object.put("label", classifier.getTermDisplayLabel().getValue());

      return object.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Transaction
  @Authenticate
  public static void deleteTermSynonym(String synonymId)
  {
    TermSynonym synonym = TermSynonym.get(synonymId);
    synonym.delete();
  }

  public static String getAttributeInformation(String fileName, InputStream fileStream)
  {
    // Save the file to the file system
    try
    {
      String name = SessionPredicate.generateId();

      File directory = new File(new File(VaultProperties.getPath("vault.default"), "files"), name);
      directory.mkdirs();

      File file = new File(directory, fileName);

      FileIO.write(new FileOutputStream(file), fileStream);

      FieldInfoContentsHandler handler = new FieldInfoContentsHandler(fileName);
      ExcelDataFormatter formatter = new ExcelDataFormatter();

      ExcelSheetReader reader = new ExcelSheetReader(handler, formatter);
      reader.process(new FileInputStream(file));

      JSONObject object = new JSONObject();
      object.put("sheets", handler.getSheets());
      object.put("directory", directory.getName());
      object.put("filename", fileName);

      return object.toString();
    }
    catch (InvalidFormatException e)
    {
      InvalidExcelFileException ex = new InvalidExcelFileException(e);
      ex.setFileName(fileName);

      throw ex;
    }
    catch (RunwayException | SmartException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static String getOptionsJSON()
  {
    try
    {
      JSONArray countries = new JSONArray();

      GeoHierarchy universal = GeoHierarchy.getRoot();

      List<GeoHierarchy> children = universal.getAllChildren();

      JSONArray options = new JSONArray();

      for (GeoHierarchy child : children)
      {
        JSONObject option = new JSONObject();
        option.put("value", child.getId());
        option.put("label", child.getDisplayLabel());

        options.put(option);
      }

      JSONObject country = new JSONObject();
      country.put("label", universal.getDisplayLabel());
      country.put("value", universal.getId());
      country.put("options", options);

      countries.put(country);

      JSONObject response = new JSONObject();
      response.put("countries", countries);

      return response.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Authenticate
  public static String importData(String configuration)
  {
    try
    {
      JSONObject object = new JSONObject(configuration);

      String name = object.getString("directory");
      String filename = object.getString("filename");

      File directory = new File(new File(VaultProperties.getPath("vault.default"), "files"), name);
      File file = new File(directory, filename);

      DataUploaderImportJob job = new DataUploaderImportJob(configuration, file);
      job.apply();
      String responseJSON = job.doImport();
      
      JSONObject responseJ = new JSONObject(responseJSON);

      if (!responseJ.has("problems"))
      {
        FileUtils.deleteDirectory(directory);
      }

      return responseJSON;
    }
    catch (JSONException | IOException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Deletes the temp files of the import
   * 
   * @param configuration
   */
  public static void cancelImport(String configuration)
  {
    try
    {
      JSONObject object = new JSONObject(configuration);

      String name = object.getString("directory");

      File directory = new File(new File(VaultProperties.getPath("vault.default"), "files"), name);

      FileUtils.deleteDirectory(directory);
    }
    catch (JSONException | IOException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static String getSavedConfiguration(String id, String sheetName)
  {
    try
    {
      ExcelSourceBinding binding = ExcelSourceBinding.get(id);

      SourceDefinitionIF sDefinition = binding.getDefinition(sheetName);
      TargetDefinitionIF tDefinition = binding.getTargetBinding().getDefinition();

      DefinitionBuilder builder = new DefinitionBuilder(sDefinition, tDefinition);
      JSONObject configuration = builder.getConfiguration();

      return configuration.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static void validateDatasetName(String name, String id)
  {
    QueryFactory factory = new QueryFactory();

    MappableClassQuery mClassQuery = new MappableClassQuery(factory);

    if (id != null && id.length() > 0)
    {
      mClassQuery.AND(mClassQuery.getId().NE(id));
    }

    MdClassQuery mdClassQuery = new MdClassQuery(factory);

    mdClassQuery.WHERE(mdClassQuery.EQ(mClassQuery.getWrappedMdClass()));
    mdClassQuery.AND(mdClassQuery.getDisplayLabel().localize().EQ(name.trim()));

    long count = mdClassQuery.getCount();

    if (count > 0)
    {
      NonUniqueDatasetException ex = new NonUniqueDatasetException();
      ex.setLabel(name.trim());

      throw ex;
    }
  }

  public static void validateCategoryName(String name, String id)
  {
    // QueryFactory factory = new QueryFactory();
    //
    // TermRelationshipQuery rQ = new TermRelationshipQuery(factory);
    // rQ.WHERE(rQ.parentId().EQ(Term.getRo.getId()));
    //
    // TermQuery classifierQuery = new TermQuery(factory);
    // classifierQuery.AND(classifierQuery.getTermDisplayLabel().localize().EQ(name.trim()));
    // classifierQuery.
    //
    // if (id != null && id.length() > 0)
    // {
    // classifierQuery.AND(classifierQuery.getId().NE(id));
    // }
    //
    // long count = classifierQuery.getCount();
    //
    // if (count > 0)
    // {
    // NonUniqueCategoryException ex = new NonUniqueCategoryException();
    // ex.setLabel(name.trim());
    //
    // throw ex;
    // }
  }

  public static String getAncestorsAsJSON(GeoEntity entity)
  {
    try
    {
      GeoEntity root = Earth.getEarthInstance();

      JSONArray array = new JSONArray();

      List<GeoEntity> ancestors = entity.getAllParents();

      Collections.reverse(ancestors);

      for (GeoEntity ancestor : ancestors)
      {
        if (!ancestor.getId().equals(root.getId()))
        {
          JSONObject object = new JSONObject();
          object.put("label", ancestor.getEntityLabel().getValue());

          array.put(object);
        }
      }

      return array.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  
  @Transaction
  @Authenticate
  public static void deleteTerm(String termId)
  {
    Term.deleteTerm(termId);
  }
  
  @Transaction
  @Authenticate
  public static String createTerm(String parentId, String label)
  {
    Term term = new Term();
    term.getTermDisplayLabel().setValue(label);
    term.setName(label);
    term.setTermId(LocalProperty.getNextId());
    term.applyWithParent(parentId, false, "", false);
    
    return term.getId();
  }
}
