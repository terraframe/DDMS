package dss.vector.solutions.kaleidoscope.report;

import java.util.List;

import org.json.JSONException;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.kaleidoscope.geo.GeoNode;

public interface ReportProviderIF extends Reloadable
{
  /**
   * Dashboard name
   */
  public static final String DASHBOARD_NAME = "dashboardName";

  /**
   * Dashboard id
   */
  public static final String DASHBOARD_ID   = "dashboardId";

  /**
   * A list of id-label pairing for all of the report queries supported by this provider
   * 
   * @return
   */
  public List<PairView> getSupportedQueryDescriptors();

  /**
   * If the ReportProvider supports the given query
   * 
   * @param queryId
   * @return
   */
  public boolean hasSupport(String queryId);

  /**
   * List of all of the aggregations supported by the given query
   * 
   * @param queryId
   * @return
   */
  public List<PairView> getSupportedAggregation(String queryId);

  /**
   * List of all of the geo nodes supported by the given query
   * 
   * @param queryId
   * @return
   */
  public List<GeoNode> getSupportedGeoNodes(String queryId);

  /**
   * Returns a ValueQuery containing all of the results for the given query based upon the context in which the query is run.
   * 
   * @param queryId
   * @param context
   * @return
   * @throws JSONException
   */
  public ValueQuery getReportQuery(String queryId, String context) throws JSONException;

}
