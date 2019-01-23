package dss.vector.solutions.kaleidoscope.dashboard.session;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.session.SessionFacade;

/**
 * HEADS UP: This class has major issues with not playing nicely with Class Reloading.
 * Don't access this class directly as a result. Access it via the SessionDashboardHelper only.
 * 
 * This class is intentionally not reloadable because we don't want our 'instances' HashMap to be destroyed
 * 
 * Here are the rules!
 * 
 * A non reloadable class cannot reference a reloadable class
 * but a reloadable class can reference a non-reloadable class
 * 
 * Because DashboardMaps cannot be created by the read-only user, we can store the ID and fetch it from the database.
 * 
 * DashboardLayer and DashboardStyle can be created by the read-only user and thus we store the serialized json.
 * 
 * DashboardCondition is stored by the state, which is held client-side.
 * 
 * Hindsight is always 20-20 and I now think it would have been better to implement this all client-side (using the state json) but it's too late now.
 * 
 * If this class is protected or default access permissions we STILL can't access it from the SessionDashboardHelper because the classloader is different
 * and Java considers classes that are of a different classloader, but same package, to be of a different package which is why its now public (please don't abuse).
 * https://stackoverflow.com/questions/14070215/java-lang-illegalaccesserror-tried-to-access-field-concreteentity-instance-from
 * 
 * @author Richard Rowlands
 *
 */
public class SessionDashboard
{
  private static HashMap<String, Set<SessionDashboard>> instances = new HashMap<String, Set<SessionDashboard>>();
  
  private final String dashboardMapId;
  
//  private final String sessionId;
  
  private Set<SessionLayer> extraLayers;
  
  private SessionDashboard(String dashboardMapId, String sessionId)
  {
    this.dashboardMapId = dashboardMapId;
//    this.sessionId = sessionId;
    this.extraLayers = new HashSet<SessionLayer>();
  }
  
  public static SessionLayer getContainingLayer(SessionStyle sessionStyle, String sessionId)
  {
    Set<SessionDashboard> sessionDashboards = instances.get(sessionId);
    if (sessionDashboards == null) { return null; }
    
    for (SessionDashboard sessionDb : sessionDashboards)
    {
      for (SessionLayer extraLayer : sessionDb.extraLayers)
      {
        for (SessionStyle extraStyle : extraLayer.styles)
        {
          if (extraStyle.equals(sessionStyle))
          {
            return extraLayer;
          }
        }
      }
    }
    
    return null;
  }
  
  /**
   * @return DashboardMap id
   */
  public static String getContainingMap(SessionLayer sessionLayer, String sessionId)
  {
    Set<SessionDashboard> sessionDashboards = instances.get(sessionId);
    if (sessionDashboards == null) { return null; }
    
    for (SessionDashboard sessionDb : sessionDashboards)
    {
      Set<SessionLayer> extraLayers = sessionDb.getExtraLayers();
      
      for (SessionLayer extraLayer : extraLayers)
      {
        if (extraLayer.equals(sessionLayer))
        {
          return sessionDb.dashboardMapId;
        }
      }
    }
    
    return null;
  }
  
  public static Set<SessionStyle> getExtraStyles(SessionLayer dashboardLayer, String sessionId)
  {
    String mapIn = SessionDashboard.getContainingMap(dashboardLayer, sessionId);
    if (mapIn == null) { return new HashSet<SessionStyle>(); }
    
    SessionDashboard sessionDashboard = SessionDashboard.getInstance(mapIn, sessionId);
    
    for (SessionLayer extraLayer : sessionDashboard.extraLayers)
    {
      if (extraLayer.equals(dashboardLayer))
      {
        return extraLayer.getDashboardStyles();
      }
    }
    
    return new HashSet<SessionStyle>();
  }
  
  public Set<SessionLayer> getExtraLayers()
  {
    return this.extraLayers;
  }
  
  public boolean containsLayer(SessionLayer layer)
  {
    for (SessionLayer sessionLayer : this.extraLayers)
    {
      if (sessionLayer.equals(layer))
      {
        return true;
      }
    }
    
    return false;
  }
  
  public void addLayer(SessionLayer layer)
  {
    if (!this.containsLayer(layer))
    {
      this.extraLayers.add(layer);
    }
  }
  
  public void addStyle( SessionLayer dashboardLayer, SessionStyle dashboardStyle )
  {
    for (SessionLayer sessionLayer : this.extraLayers)
    {
      if (sessionLayer.equals(dashboardLayer))
      {
        sessionLayer.addStyle(dashboardStyle);
        break;
      }
    }
  }
  
  public static synchronized SessionDashboard getInstance(String dashboardMapId, String sessionId)
  {
    // Remove invalid sessions
    Iterator<String> sessionIt = instances.keySet().iterator();
    while (sessionIt.hasNext())
    {
      String session = sessionIt.next();
      
      if (!SessionFacade.containsSession(session))
      {
        sessionIt.remove();
      }
    }
    
    if(!instances.containsKey(sessionId))
    {
      HashSet<SessionDashboard> list = new HashSet<SessionDashboard>();
      SessionDashboard sessionDashboard = new SessionDashboard(dashboardMapId, sessionId);
      list.add(sessionDashboard);
      
      instances.put(sessionId, list);
      
      return sessionDashboard;
    }
    else
    {
      Set<SessionDashboard> dashboards = instances.get(sessionId);
      
      for (SessionDashboard sessionDashboard : dashboards)
      {
        if (sessionDashboard.dashboardMapId.equals(dashboardMapId))
        {
          return sessionDashboard;
        }
      }
      
      SessionDashboard sessionDashboard = new SessionDashboard(dashboardMapId, sessionId);
      dashboards.add(sessionDashboard);
      return sessionDashboard;
    }
  }
  
  public abstract static class SerializedSessionComponent
  {
    protected JSONObject json;
    
    protected String type;
    
    protected String id;
    
    public SerializedSessionComponent(JSONObject json, String type, String id)
    {
      this.json = json;
      this.type = type;
      this.id = id;
    }
    
    public JSONObject toJSON()
    {
      try
      {
        JSONObject json = new JSONObject();
        
        json.put("json", this.json);
        
        json.put("type", type);
        
        json.put("id", id);
        
        return json;
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }
    
    public JSONObject getJson()
    {
      return json;
    }

    public String getType()
    {
      return type;
    }

    public String getId()
    {
      return id;
    }

    abstract public SerializedSessionComponent fromJSON(String json);
    
    @Override
    public int hashCode()
    {
      return this.id.hashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
      if (obj instanceof SerializedSessionComponent)
      {
        return ((SerializedSessionComponent)obj).getId().equals(this.getId());
      }
      else if (obj instanceof String)
      {
        return obj.equals(this.getId());
      }
      else
      {
        return false;
      }
    }
  }
  
  public static class SessionLayer extends SerializedSessionComponent
  {
    /**
     * Set<DashboardStyle json>
     */
    private Set<SessionStyle> styles = new HashSet<SessionStyle>();
    
    public SessionLayer(JSONObject json, String type, String id)
    {
      super(json, type, id);
    }
    
    public void addStyle(SessionStyle sessionStyle)
    {
      styles.add(sessionStyle);
    }
    
    public Set<SessionStyle> getDashboardStyles()
    {
      return styles;
    }

    @Override
    public SerializedSessionComponent fromJSON(String json)
    {
      try
      {
        JSONObject jo = new JSONObject(json);
        
        SessionLayer sl = new SessionLayer(jo.getJSONObject("json"), jo.getString("type"), jo.getString("id"));
        
        return sl;
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }
  }
  
  public static class SessionStyle extends SerializedSessionComponent
  {
    public SessionStyle(JSONObject json, String type, String id)
    {
      super(json, type, id);
    }
    
    @Override
    public SerializedSessionComponent fromJSON(String json)
    {
      try
      {
        JSONObject jo = new JSONObject(json);
        
        SessionStyle sl = new SessionStyle(jo.getJSONObject("json"), jo.getString("type"), jo.getString("id"));
        
        return sl;
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }
  }
  
  @Override
  public int hashCode()
  {
    return this.dashboardMapId.hashCode();
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof SessionDashboard)
    {
      return ((SessionDashboard)obj).dashboardMapId.equals(this.dashboardMapId);
    }
    else if (obj instanceof String)
    {
      return obj.equals(this.dashboardMapId);
    }
    else
    {
      return false;
    }
  }
}
