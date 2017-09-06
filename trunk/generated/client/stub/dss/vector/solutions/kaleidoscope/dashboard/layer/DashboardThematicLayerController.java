package dss.vector.solutions.kaleidoscope.dashboard.layer;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.system.metadata.MdAttributeDTO;
import com.runwaysdk.transport.conversion.json.BusinessDTOToJSON;

import dss.vector.solutions.kaleidoscope.JSONControllerUtil;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardThematicStyleDTO;

public class DashboardThematicLayerController extends DashboardThematicLayerControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "/WEB-INF/dss/vector/solutions/kaleidoscope/dashboard/layer/DashboardThematicLayer/";

  public static final String LAYOUT  = "WEB-INF/templates/layout.jsp";

  public DashboardThematicLayerController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void edit(String id) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      DashboardThematicLayerDTO layer = DashboardThematicLayerDTO.lock(request, id);
      DashboardThematicStyleDTO style = (DashboardThematicStyleDTO) layer.getAllHasStyle().get(0);

      DashboardMapDTO map = layer.getDashboardMap();

      String options = DashboardThematicLayerDTO.getOptionsJSON(request, layer.getMdAttributeId(), map.getDashboardId());

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
  public void newThematicInstance(String mdAttribute, String mapId) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      MdAttributeDTO mdAttributeDTO = MdAttributeDTO.get(request, mdAttribute);
      DashboardMapDTO map = DashboardMapDTO.get(request, mapId);

      DashboardThematicLayerDTO layer = new DashboardThematicLayerDTO(request);
      layer.setMdAttribute(mdAttributeDTO);
      layer.setDashboardMap(map);

      DashboardThematicStyleDTO style = new DashboardThematicStyleDTO(request);

      String options = DashboardThematicLayerDTO.getOptionsJSON(request, mdAttribute, map.getDashboardId());

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
