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
package dss.vector.solutions.etl.dhis2.exporter;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.etl.dhis2.DHIS2ExportableDataset;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;

public class DHIS2ExportResults implements Reloadable
{
  private DHIS2ExportableDataset dataset;
  
  private String status;
  
  private Stats metadataStats;
  
  private Stats dataStats;
  
  public DHIS2ExportResults(DHIS2ExportableDataset dataset)
  {
    this.dataset = dataset;
  }
  
  public void processMetadataResponse(HTTPResponse response)
  {
    try
    {
      JSONObject resp = response.getJSONObject();
      
      String status = resp.getString("status");
      mergeStatus(status);
      
      metadataStats = new Stats();
      
      if (resp.has("stats"))
      {
        JSONObject jStats = resp.getJSONObject("stats");
        
        metadataStats.created = jStats.getInt("created");
        metadataStats.updated = jStats.getInt("updated");
        metadataStats.deleted = jStats.getInt("deleted");
        metadataStats.ignored = jStats.getInt("ignored");
        metadataStats.total = jStats.getInt("total");
      }
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public void processDataResponse(HTTPResponse response)
  {
    try
    {
      JSONObject resp = response.getJSONObject();
      
      String status = resp.getString("status");
      mergeStatus(status);
      
      dataStats = new Stats();
      
      if (resp.has("importCount"))
      {
        JSONObject jStats = resp.getJSONObject("importCount");
        
        dataStats.created = jStats.getInt("imported");
        dataStats.updated = jStats.getInt("updated");
        dataStats.deleted = jStats.getInt("deleted");
        dataStats.ignored = jStats.getInt("ignored");
        dataStats.total = dataStats.created + dataStats.updated + dataStats.deleted + dataStats.ignored;
      }
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public JSONObject toJSON()
  {
    JSONObject ret = new JSONObject();
    
    try
    {
      ret.put("name", this.dataset.getDhis2Name());
      ret.put("status", this.status);
      ret.put("metadataStats", this.metadataStats.toJSON());
      ret.put("dataStats", this.dataStats.toJSON());
      ret.put("logLocation", this.dataset.getLogLocation());
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
    
    return ret;
  }
  
  @Override
  public String toString()
  {
    return this.toJSON().toString();
  }
  
  public void add(DHIS2ExportResults extra)
  {
    mergeStatus(extra.status);
    
    metadataStats.created += extra.metadataStats.created;
    metadataStats.updated += extra.metadataStats.updated;
    metadataStats.deleted += extra.metadataStats.deleted;
    metadataStats.ignored += extra.metadataStats.ignored;
    metadataStats.total += extra.metadataStats.total;
    
    dataStats.created += extra.dataStats.created;
    dataStats.updated += extra.dataStats.updated;
    dataStats.deleted += extra.dataStats.deleted;
    dataStats.ignored += extra.dataStats.ignored;
    dataStats.total += extra.dataStats.total;
  }
  
  private void mergeStatus(String incoming)
  {
    if (this.status == null)
    {
      this.status = incoming;
    }
    else if (this.status.equals("ERROR"))
    {
      // do nothing our status is already as bad as it can get
      return;
    }
    else if (this.status.equals("WARN") && incoming.equals("ERROR"))
    {
      this.status = incoming;
    }
    else if (this.status.equals("OK") || this.status.equals(""))
    {
      this.status = incoming;
    }
  }
  
  public class Stats implements Reloadable
  {
    public int created;
    public int updated;
    public int deleted;
    public int ignored;
    public int total;
    
    public JSONObject toJSON()
    {
      JSONObject ret = new JSONObject();
      
      try
      {
        ret.put("created", created);
        ret.put("updated", updated);
        ret.put("deleted", deleted);
        ret.put("ignored", ignored);
        ret.put("total", total);
      }
      catch (JSONException e)
      {
        throw new RuntimeException(e);
      }
      
      return ret;
    }
  }
}
