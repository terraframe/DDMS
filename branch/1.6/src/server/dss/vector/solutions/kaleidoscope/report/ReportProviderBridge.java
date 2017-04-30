package dss.vector.solutions.kaleidoscope.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import org.json.JSONException;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.DelegatingClassLoader;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyView;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;

/**
 * This class is responsible for forwarding an MdMethod request from BIRT for either a query (getValues) or a list of
 * possible queries (getTypes).
 * 
 * @author rrowlands, jsmethie
 */
public class ReportProviderBridge implements Reloadable
{
  private static ReportProviderBridge instance;

  private ReportProviderBridge()
  {

  }

  public static synchronized ReportProviderBridge getInstance()
  {
    if (instance == null)
    {
      instance = new ReportProviderBridge();
    }

    return instance;
  }

  public static ValueQuery getValuesForReporting(String queryId, String context)
  {
    try
    {
      List<ReportProviderIF> providers = getReportProviders();

      for (ReportProviderIF provider : providers)
      {
        if (provider.hasSupport(queryId))
        {
          return provider.getReportQuery(queryId, context);
        }
      }

      throw new ReportRenderException("ReportProvider with id '" + queryId + "' does not exist. Are you using the wrong RPT file?");
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static PairView[] getQueriesForReporting()
  {
    ArrayList<PairView> types = new ArrayList<PairView>();
    List<ReportProviderIF> providers = getReportProviders();

    for (ReportProviderIF provider : providers)
    {
      types.addAll(provider.getSupportedQueryDescriptors());
    }

    return types.toArray(new PairView[types.size()]);
  }

  public static PairView[] getSupportedAggregation(String queryId)
  {
    List<ReportProviderIF> providers = getReportProviders();

    for (ReportProviderIF provider : providers)
    {
      if (provider.hasSupport(queryId))
      {
        return provider.getSupportedAggregation(queryId);
      }
    }

    throw new ReportRenderException("ReportProvider with id '" + queryId + "' does not exist. Are you using the wrong RPT file?");
  }

  public static PairView[] getGeoNodeIds(String queryId)
  {
    List<ReportProviderIF> providers = getReportProviders();

    List<PairView> list = new LinkedList<PairView>();

    for (ReportProviderIF provider : providers)
    {
      if (provider.hasSupport(queryId))
      {
        List<GeoNode> nodes = provider.getSupportedGeoNodes(queryId);

        for (GeoNode node : nodes)
        {
          String displayLabel = AggregationStrategyView.getDisplayLabel(node);

          list.add(PairView.createWithLabel(node.getId(), displayLabel));
        }
      }
    }

    return list.toArray(new PairView[list.size()]);
  }

  /**
   * Retrieve all implementations of ReportProviderIF.
   */
  public static List<ReportProviderIF> getReportProviders()
  {
    List<ReportProviderIF> reports = new ArrayList<ReportProviderIF>();
    reports.add(new GenericTypeProvider());

    ServiceLoader<ReportProviderIF> loader = ServiceLoader.load(ReportProviderIF.class, ( (DelegatingClassLoader) LoaderDecorator.instance() ));

    try
    {
      Iterator<ReportProviderIF> it = loader.iterator();
      while (it.hasNext())
      {
        reports.add(it.next());
      }
    }
    catch (ServiceConfigurationError serviceError)
    {
      throw new ProgrammingErrorException(serviceError);
    }

    return reports;
  }

}
