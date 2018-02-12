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
package dss.vector.solutions.etl.dhis2;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdTable;
import com.runwaysdk.system.metadata.MdTableQuery;

import dss.vector.solutions.etl.dhis2.exporter.DHIS2DataExporter;
import dss.vector.solutions.etl.dhis2.exporter.DHIS2ExportResults;
import dss.vector.solutions.query.SavedSearchQuery;

public class DHIS2ExportableDataset extends DHIS2ExportableDatasetBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -596796853;

  public DHIS2ExportableDataset()
  {
    super();
  }
  
  private String getNameForFile()
  {
    String cleanName = this.getDhis2Name();
    
    cleanName = cleanName.replaceAll("\\W+", "");
    
    return cleanName;
  }
  
  public String getLogLocation()
  {
    return DeployProperties.getDeployPath() + "/DHIS2/" + this.getNameForFile() + ".log";
  }
  
  public String getMetadataFileLocation()
  {
    return DeployProperties.getDeployPath() + "/DHIS2/" + this.getNameForFile() + "-metadata.json";
  }
  
  public String getDataFileLocation()
  {
    return DeployProperties.getDeployPath() + "/DHIS2/" + this.getNameForFile() + "-data.json";
  }

  public static DHIS2ExportableDataset[] getAll()
  {
    DHIS2ExportableDatasetQuery query = new DHIS2ExportableDatasetQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getQueryRef().getDisplayLabel().localize());

    OIterator<? extends DHIS2ExportableDataset> it = null;

    try
    {
      it = query.getIterator();

      List<? extends DHIS2ExportableDataset> list = it.getAll();

      return list.toArray(new DHIS2ExportableDataset[list.size()]);
    }
    finally
    {
      if (it != null)
      {
        it.close();
      }
    }

  }

  public static MdTable[] getQueries()
  {
    QueryFactory factory = new QueryFactory();

    SavedSearchQuery ssQuery = new SavedSearchQuery(factory);
    ssQuery.WHERE(ssQuery.getIsMaterialized().EQ(true));

    MdTableQuery mtQuery = new MdTableQuery(factory);
    mtQuery.WHERE(mtQuery.EQ(ssQuery.getMaterializedTable()));

    OIterator<? extends MdTable> it = null;

    try
    {
      it = mtQuery.getIterator();

      List<? extends MdTable> list = it.getAll();

      return list.toArray(new MdTable[list.size()]);
    }
    finally
    {
      if (it != null)
      {
        it.close();
      }
    }

  }

  @Transaction
  public static String xport(String datasets, String strategy)
  {
    JSONArray results = new JSONArray();
    
    try
    {
      JSONArray array = new JSONArray(datasets);
      
      if (array.length() == 0)
      {
        DatasetRequiredException ex = new DatasetRequiredException();
        throw ex;
      }

      for (int i = 0; i < array.length(); i++)
      {
        JSONObject object = array.getJSONObject(i);
        String datasetId = object.getString("id");

        DHIS2ExportableDataset dataset = DHIS2ExportableDataset.get(datasetId);

        DHIS2DataExporter exporter = new DHIS2DataExporter();
        DHIS2ExportResults result = exporter.export(dataset, strategy);
        
        if (results != null)
        {
          results.put(result.toJSON());
        }
      }
      
      return results.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

}
