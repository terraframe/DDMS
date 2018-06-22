/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.kaleidoscope;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.RunwayException;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.SmartException;
import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdClassQuery;

import dss.vector.solutions.DataUploaderImportJob;
import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoSynonym;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.kaleidoscope.data.etl.DefinitionBuilder;
import dss.vector.solutions.kaleidoscope.data.etl.ExcelSourceBinding;
import dss.vector.solutions.kaleidoscope.data.etl.SourceDefinitionIF;
import dss.vector.solutions.kaleidoscope.data.etl.TargetDefinitionIF;
import dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelDataFormatter;
import dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelSheetReader;
import dss.vector.solutions.kaleidoscope.data.etl.excel.FieldInfoContentsHandler;
import dss.vector.solutions.kaleidoscope.data.etl.excel.InvalidExcelFileException;
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
    try (BufferedInputStream bis = new BufferedInputStream(fileStream))
    {
      if (FileMagic.valueOf(bis) == FileMagic.OOXML)
      {
        VaultFile vf = VaultFile.createAndApply(fileName, bis);

        FieldInfoContentsHandler handler = new FieldInfoContentsHandler(fileName);
        ExcelDataFormatter formatter = new ExcelDataFormatter();

        ExcelSheetReader reader = new ExcelSheetReader(handler, formatter);
        reader.process(vf.getFileStream());

        if (handler.getType().equals("ETL"))
        {
          JSONObject object = new JSONObject();
          object.put("sheets", handler.getSheets());
          object.put("vaultId", vf.getId());
          object.put("filename", fileName);
          object.put("type", "ETL");

          return object.toString();
        }
        else
        {
          return handleLegacyImport(fileName, vf.getFileStream());
        }
      }
      else
      {
        return handleLegacyImport(fileName, bis);
      }
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

  private static String handleLegacyImport(String fileName, InputStream bis) throws JSONException
  {
    ExcelImportManager manager = ExcelImportManager.getNewInstance();
    manager.importWhatYouCan(bis, new String[] {}, fileName);

    JSONObject object = new JSONObject();
    object.put("type", "LEGACY");

    return object.toString();
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

      String vaultId = object.getString("vaultId");
      VaultFile vf = VaultFile.get(vaultId);

      DataUploaderImportJob job = new DataUploaderImportJob(configuration, vf.getFile(), object.getString("filename"));
      job.setRunAsUserId(Session.getCurrentSession().getUser().getId());
      job.setRunAsDimensionId(Session.getCurrentDimension().getId());
      job.apply();
      String responseJSON = job.doImport();

      // JSONObject responseJ = new JSONObject(responseJSON);
      //
      // if (!responseJ.has("problems"))
      // {
      // vf.delete();
      // }

      return responseJSON;
    }
    catch (JSONException e)
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

      String vaultId = object.getString("vaultId");
      VaultFile vf = VaultFile.get(vaultId);

      vf.delete();
    }
    catch (JSONException e)
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
