package dss.vector.solutions.kaleidoscope.dashboard.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.ComponentIF;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.ComponentDTO;
import com.runwaysdk.business.ComponentDTOIF;
import com.runwaysdk.business.Entity;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.constants.ComponentInfo;
import com.runwaysdk.dataaccess.EntityDAO;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.mvc.NullConfiguration;
import com.runwaysdk.mvc.conversion.BasicJSONToComponentDTO;
import com.runwaysdk.mvc.conversion.ComponentDTOIFToBasicJSON;
import com.runwaysdk.session.Session;
import com.runwaysdk.transport.conversion.ComponentIFtoComponentDTOIF;
import com.runwaysdk.transport.conversion.business.MutableDTOToMutable;
import com.runwaysdk.transport.conversion.business.MutableToMutableDTO;

import dss.vector.solutions.kaleidoscope.dashboard.DashboardMap;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer;
import dss.vector.solutions.kaleidoscope.dashboard.session.SessionDashboard.SessionLayer;
import dss.vector.solutions.kaleidoscope.dashboard.session.SessionDashboard.SessionStyle;

public class SessionDashboardHelper implements Reloadable
{

  public static List<DashboardLayer> getExtraLayers(DashboardMap dashboardMap)
  {
    SessionDashboard sDash = SessionDashboard.getInstance(dashboardMap.getId(), Session.getCurrentSession().getId());
    
    Set<SessionLayer> sessionLayers = sDash.getExtraLayers();
    List<DashboardLayer> layers = new ArrayList<DashboardLayer>();
    
    for (SessionLayer sessionLayer: sessionLayers)
    {
      DashboardLayer layer = (DashboardLayer) jsonToMutable(sessionLayer.getJson().toString(), sessionLayer.getType());
      
      layers.add(layer);
    }
    
    return layers;
  }
  
  public static Iterable<DashboardStyle> getExtraStyles(DashboardLayer dashboardLayer)
  {
    try
    {
      String dashboardLayerJson = componentToJson(dashboardLayer, dashboardLayer.getType());
      SessionLayer sessionLayer = new SessionLayer(new JSONObject(dashboardLayerJson), dashboardLayer.getType(), dashboardLayer.getId());
      
      Set<SessionStyle> sessionStyles = SessionDashboard.getExtraStyles(sessionLayer, Session.getCurrentSession().getId());
      ArrayList<DashboardStyle> styles = new ArrayList<DashboardStyle>();
      
      for (SessionStyle sessionStyle : sessionStyles)
      {
        DashboardStyle style = (DashboardStyle) jsonToMutable(sessionStyle.getJson().toString(), sessionStyle.getType());
        
        styles.add(style);
      }
      
      return styles;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static DashboardLayer getContainingLayer(DashboardStyle dashboardStyle)
  {
    try
    {
      String dashboardStyleJson = componentToJson(dashboardStyle, dashboardStyle.getType());
      SessionStyle sessionStyle = new SessionStyle(new JSONObject(dashboardStyleJson), dashboardStyle.getType(), dashboardStyle.getId());
      
      SessionLayer sessionLayer = SessionDashboard.getContainingLayer(sessionStyle, Session.getCurrentSession().getId());
      
      return (DashboardLayer) jsonToMutable(sessionLayer.getJson().toString(), sessionLayer.getType());
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static void addLayer(DashboardMap map, DashboardLayer dashboardLayer)
  {
    try
    {
      SessionDashboard sDash = SessionDashboard.getInstance(map.getId(), Session.getCurrentSession().getId());
      
      String dashboardLayerJson = componentToJson(dashboardLayer, dashboardLayer.getType());
      SessionLayer sLayer = new SessionLayer(new JSONObject(dashboardLayerJson), dashboardLayer.getType(), dashboardLayer.getId());
      
      sDash.addLayer(sLayer);
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static void addStyle(DashboardMap map, DashboardLayer dashboardLayer, DashboardStyle style)
  {
    try
    {
      String dashboardLayerJson = componentToJson(dashboardLayer, dashboardLayer.getType());
      SessionLayer sLayer = new SessionLayer(new JSONObject(dashboardLayerJson), dashboardLayer.getType(), dashboardLayer.getId());
      
      String dashboardStyleJson = componentToJson(style, style.getType());
      SessionStyle sStyle = new SessionStyle(new JSONObject(dashboardStyleJson), style.getType(), style.getId());
      
      SessionDashboard sessionDashboard = SessionDashboard.getInstance(map.getId(), Session.getCurrentSession().getId());
      
      sessionDashboard.addStyle(sLayer, sStyle);
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  private static Mutable jsonToMutable(String sJson, String type)
  {
    try
    {
      JSONObject oJson = new JSONObject(sJson);
      String id = (String) oJson.remove("id");
      sJson = oJson.toString();
      
      BasicJSONToComponentDTO converter = BasicJSONToComponentDTO.getConverter(Session.getCurrentSession().getId(), Session.getCurrentLocale(), type + "DTO", sJson);
      ComponentDTO componentDTO = converter.populate();
      
      MutableDTOToMutable dtoToComponent = MutableDTOToMutable.getConverter(Session.getCurrentSession().getId(), (MutableDTO) componentDTO);
      Mutable mutable = dtoToComponent.populate();
      
      EntityDAO entityDAO = (EntityDAO) BusinessFacade.getEntityDAO((Entity) mutable);
      entityDAO.getAttribute(ComponentInfo.ID).setValue(id);
      
      return mutable;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  private static String componentToJson(ComponentIF component, String type)
  {
    ComponentIFtoComponentDTOIF converter = MutableToMutableDTO.getConverter(Session.getCurrentSession().getId(), component, true);
    ComponentDTOIF componentDTO = converter.populate();
    
    ComponentDTOIFToBasicJSON componentDTOToJSON = ComponentDTOIFToBasicJSON.getConverter(componentDTO, new NullConfiguration());
    try
    {
      JSONObject json = componentDTOToJSON.populate();
      
      json.put("id", component.getId());
      
      return json.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

}
