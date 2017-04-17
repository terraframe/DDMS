package dss.vector.solutions.kaleidoscope.dashboard.layer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.transport.conversion.json.BusinessDTOToJSON;

import dss.vector.solutions.kaleidoscope.JSONControllerUtil;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO;

public class DashboardReferenceLayerController extends DashboardReferenceLayerControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "/WEB-INF/net/geoprism/dashboard/layer/DashboardReferenceLayer/";

  public static final String LAYOUT  = "WEB-INF/templates/layout.jsp";

  public DashboardReferenceLayerController(HttpServletRequest req, HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void edit(String id) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      DashboardReferenceLayerDTO layer = DashboardReferenceLayerDTO.lock(request, id);
      DashboardMapDTO map = layer.getDashboardMap();
      DashboardStyleDTO style = layer.getAllHasStyle().get(0);

      String options = DashboardReferenceLayerDTO.getOptionsJSON(request, map.getDashboardId());

      JSONObject response = new JSONObject();
      response.put("layerDTO", BusinessDTOToJSON.getConverter(layer).populate());
      response.put("styleDTO", BusinessDTOToJSON.getConverter(style).populate());
      response.put("layer", new JSONObject(layer.getJSON()));
      response.put("options", new JSONObject(options));

      JSONControllerUtil.writeReponse(this.resp, response);
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }

  @Override
  public void newReferenceInstance(String universalId, String mapId) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      DashboardMapDTO map = DashboardMapDTO.get(request, mapId);

      DashboardReferenceLayerDTO layer = new DashboardReferenceLayerDTO(request);
      layer.setDashboardMap(map);

      DashboardStyleDTO style = new DashboardStyleDTO(request);

      String options = DashboardReferenceLayerDTO.getOptionsJSON(request, map.getDashboardId());

      JSONObject response = new JSONObject();
      response.put("layerDTO", BusinessDTOToJSON.getConverter(layer).populate());
      response.put("styleDTO", BusinessDTOToJSON.getConverter(style).populate());
      response.put("layer", new JSONObject(layer.getJSON()));
      response.put("options", new JSONObject(options));

      JSONControllerUtil.writeReponse(this.resp, response);
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }
}
