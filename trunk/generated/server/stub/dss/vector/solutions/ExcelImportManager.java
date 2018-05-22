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
package dss.vector.solutions;

import java.io.InputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.system.scheduler.AllJobStatus;

import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.ontology.UnknownTerm;

public class ExcelImportManager extends ExcelImportManagerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long            serialVersionUID        = 792922881;

  public List<UnknownGeoEntity>        unknownEntityList       = new LinkedList<UnknownGeoEntity>();

  public List<UnknownTerm>             unknownTerms            = new LinkedList<UnknownTerm>();

  private Set<String>                  unknownGeoEntityNameSet = new HashSet<String>();

  private Map<String, Set<String>>     unknownTermNames        = new HashMap<String, Set<String>>();

  public Map<UnknownGeoEntity, String> geoTypeInfo             = new HashMap<UnknownGeoEntity, String>();

  private String                       userId;

  private String                       dimensionId;

  public ExcelImportManager()
  {
    super();
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public void setDimensionId(String dimensionId)
  {
    this.dimensionId = dimensionId;
  }

  /**
   * MdMethod
   * 
   * @return
   */
  public java.io.InputStream importWhatYouCan(java.io.InputStream inputStream, java.lang.String[] params, java.lang.String fileName)
  {
    if (this.userId == null)
    {
      this.userId = Session.getCurrentSession().getUser().getId();
    }

    if (this.dimensionId == null)
    {
      this.dimensionId = Session.getCurrentDimension().getId();
    }

    if (params == null)
    {
      params = new String[] {};
    }

    ExcelImportJob job = new ExcelImportJob(this, inputStream, params, fileName);
    job.setRunAsUserId(this.userId);
    job.setRunAsDimensionId(this.dimensionId);
    job.apply();

    return job.doImport();
  }

  public AllJobStatus importAndWait(InputStream inputStream, String[] params, String fileName)
  {
    if (this.userId == null)
    {
      this.userId = Session.getCurrentSession().getUser().getId();
    }

    if (this.dimensionId == null)
    {
      this.dimensionId = Session.getCurrentDimension().getId();
    }

    if (params == null)
    {
      params = new String[] {};
    }

    ExcelImportJob job = new ExcelImportJob(this, inputStream, params, fileName);
    job.setRunAsUserId(this.userId);
    job.setRunAsDimensionId(this.dimensionId);
    job.apply();

    AllJobStatus status = job.importAndWait();
    return status;
  }

  /**
   * MdMethod
   * 
   * @return
   */
  public dss.vector.solutions.geo.UnknownGeoEntity[] getUnmatchedGeoViews()
  {
    try
    {
      String strArray = this.getUnmatchedGeoViewIdString();
      if (strArray == null || strArray.equals(""))
      {
        return null;
      }

      JSONArray array = new JSONArray(strArray);
      UnknownGeoEntity[] views = new UnknownGeoEntity[array.length()];

      for (int i = 0; i < array.length(); i++)
      {
        JSONObject json = array.getJSONObject(i);

        views[i] = UnknownGeoEntity.deserialize(json);
      }

      return views;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public void putGeoTypeInfo(UnknownGeoEntity uge, String type)
  {
    geoTypeInfo.put(uge, type);
  }

  public String getGeoTypeInfo(UnknownGeoEntity uge)
  {
    return geoTypeInfo.get(uge);
  }

  public int getNumberUnknownTerms()
  {
    return unknownTerms.size();
  }

  public int getNumberUnknownGeos()
  {
    return unknownEntityList.size();
  }

  @Override
  public UnknownTerm[] getUnknownTerms()
  {
    try
    {
      String strArray = this.getSerializedUnknownTerm();
      if (strArray == null || strArray.equals(""))
      {
        return null;
      }

      JSONArray array = new JSONArray(strArray);
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

  public String serializeUnknownTerms()
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

  public String serializeUnknownGeos()
  {
    try
    {
      JSONArray array = new JSONArray();

      for (UnknownGeoEntity geo : unknownEntityList)
      {
        array.put(geo.serialize());
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
    this.setSerializedUnknownTerm(this.serializeUnknownTerms());
    this.setUnmatchedGeoViewIdString(this.serializeUnknownGeos());

    this.apply();
  }

  public static dss.vector.solutions.ExcelImportManager getNewInstance()
  {
    ExcelImportManager inst = new ExcelImportManager();
    inst.apply();
    return inst;
  }

  /**
   * MdMethod
   */
  public static void excelImportFromVault(java.lang.String vaultId, String config)
  {
    VaultFile vf = VaultFile.get(vaultId);

    InputStream is = vf.getFileStream();

    ExcelImportManager man = getNewInstance();

    ExcelImportJob job = new ExcelImportJob(man, is, new String[] {}, vf.getFileName() + "." + vf.getFileExtension());
    job.setRunAsUserId(Session.getCurrentSession().getUser().getId());
    job.setRunAsDimensionId(Session.getCurrentDimension().getId());
    job.apply();
    job.importAsync();
  }
}
