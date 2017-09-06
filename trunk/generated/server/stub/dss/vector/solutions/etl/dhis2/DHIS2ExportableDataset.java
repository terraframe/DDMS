package dss.vector.solutions.etl.dhis2;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    DHIS2ExportResults results = null;
    
    try
    {
      JSONArray array = new JSONArray(datasets);

      for (int i = 0; i < array.length(); i++)
      {
        JSONObject object = array.getJSONObject(i);
        String datasetId = object.getString("id");

        DHIS2ExportableDataset dataset = DHIS2ExportableDataset.get(datasetId);

        DHIS2DataExporter exporter = new DHIS2DataExporter();
        DHIS2ExportResults result = exporter.export(dataset);
        
        if (results != null)
        {
          results.add(result);
        }
        else
        {
          results = result;
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
