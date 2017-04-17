package dss.vector.solutions.kaleidoscope;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.mvc.ViewTemplateResponse;
import com.runwaysdk.system.RolesDTO;
import com.runwaysdk.web.json.JSONController;

import dss.vector.solutions.MDSSUserDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO;
import dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyViewDTO;
import dss.vector.solutions.kaleidoscope.dashboard.AggregationTypeDTO;
import dss.vector.solutions.kaleidoscope.dashboard.AllAggregationTypeDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardController;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardDisplayLabelDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardThematicStyleDTO;
import dss.vector.solutions.kaleidoscope.dashboard.GeometryAggregationStrategyDTO;
import dss.vector.solutions.kaleidoscope.dashboard.UniversalAggregationStrategyDTO;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerController;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerViewDTO;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerController;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerDTO;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerViewDTO;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerController;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeEntityDTO;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometryDTO;
import dss.vector.solutions.report.ReportItemController;
import dss.vector.solutions.report.ReportItemDTO;

public class JavascriptUtil implements Reloadable
{
  public static String getJavascript(ClientRequestIF clientRequest, String... types)
  {
    String js = JSONController.importTypes(clientRequest.getSessionId(), types, true);

    return js;
  }

  private static void loadJavascript(ClientRequestIF request, HttpServletRequest req, Set<String> set)
  {
    String[] types = set.toArray(new String[set.size()]);
    String javascript = JavascriptUtil.getJavascript(request, types);

    req.setAttribute("js", javascript);
  }

  private static void loadJavascript(ClientRequestIF request, ViewTemplateResponse response, Set<String> set)
  {
    String[] types = set.toArray(new String[set.size()]);
    String javascript = JavascriptUtil.getJavascript(request, types);
    
    response.set("js", javascript);
  }
  
  public static void loadUserBundle(ClientRequestIF request, HttpServletRequest req)
  {
    Set<String> types = new HashSet<String>();
    types.add(RolesDTO.CLASS);
    types.add(MDSSUserDTO.CLASS);
    types.add(DashboardDTO.CLASS);
    types.add(DashboardDisplayLabelDTO.CLASS);
    types.add(DashboardController.CLASS);

    JavascriptUtil.loadJavascript(request, req, types);
  }
  
  public static void loadDynamicMapBundle(ClientRequestIF request, HttpServletRequest req)
  {
    Set<String> types = new HashSet<String>();
    types.add(DashboardDTO.CLASS);
    types.add(DashboardDisplayLabelDTO.CLASS);
    types.add(DashboardMapDTO.CLASS);
    types.add(DashboardLayerDTO.CLASS);
    types.add(DashboardLayerViewDTO.CLASS);
    types.add(DashboardLayerController.CLASS);
    types.add(DashboardThematicLayerDTO.CLASS);
    types.add(DashboardStyleDTO.CLASS);
    types.add(DashboardThematicStyleDTO.CLASS);
    types.add(DashboardThematicLayerController.CLASS);
    types.add(DashboardReferenceLayerDTO.CLASS);
    types.add(DashboardReferenceLayerViewDTO.CLASS);
    types.add(DashboardReferenceLayerController.CLASS);
    types.add(DashboardController.CLASS);
    types.add(DashboardMapController.CLASS);
    types.add(ReportItemController.CLASS);
    types.add(ReportItemDTO.CLASS);
    types.add(GeoNodeDTO.CLASS);
    types.add(GeoNodeEntityDTO.CLASS);
    types.add(GeoNodeGeometryDTO.CLASS);
    types.add(AggregationStrategyDTO.CLASS);
    types.add(UniversalAggregationStrategyDTO.CLASS);
    types.add(GeometryAggregationStrategyDTO.CLASS);
    types.add(AggregationStrategyViewDTO.CLASS);
    types.add(GeoNodeDTO.CLASS);
    types.add(AggregationStrategyViewDTO.CLASS);
    types.add(MappableClassDTO.CLASS);
    types.add(AllAggregationTypeDTO.CLASS);
    types.add(AggregationTypeDTO.CLASS);

    JavascriptUtil.loadJavascript(request, req, types);
  }

  public static void loadDashboardBundle(ClientRequestIF request, HttpServletRequest req)
  {
    Set<String> types = new HashSet<String>();
    types.add(DashboardDTO.CLASS);

    JavascriptUtil.loadJavascript(request, req, types);
  }

  public static void loadBuilderBundle(ClientRequestIF request, HttpServletRequest req)
  {
    Set<String> types = new HashSet<String>();
    types.add(DashboardDTO.CLASS);
    types.add(DashboardDisplayLabelDTO.CLASS);
    types.add(DashboardController.CLASS);
    types.add(GeoEntityDTO.CLASS);
//    types.add(DataUploaderController.CLASS);

    JavascriptUtil.loadJavascript(request, req, types);
  }

  public static void loadDatasets(ClientRequestIF request, HttpServletRequest req)
  {
    Set<String> types = new HashSet<String>();
    types.add(DataSetController.CLASS);
    types.add(GeoEntityDTO.CLASS);
//    types.add(DataUploaderController.CLASS);

    JavascriptUtil.loadJavascript(request, req, types);
  }

  public static void loadDatasets(ClientRequestIF request, ViewTemplateResponse req)
  {
    Set<String> types = new HashSet<String>();
    types.add(DataSetController.CLASS);
    types.add(GeoEntityDTO.CLASS);
//    types.add(DataUploaderController.CLASS);
    
    JavascriptUtil.loadJavascript(request, req, types);
  }

  public static void loadDataManagementBundle(ClientRequestIF request, HttpServletRequest req)
  {
    Set<String> types = new HashSet<String>();
    types.add(DataSetController.CLASS);
    types.add(GeoEntityDTO.CLASS);
//    types.add(CategoryIconController.CLASS);
//    types.add(DataUploaderController.CLASS);

    JavascriptUtil.loadJavascript(request, req, types);
  }
}
