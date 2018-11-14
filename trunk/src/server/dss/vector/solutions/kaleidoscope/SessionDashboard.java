package dss.vector.solutions.kaleidoscope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;

import dss.vector.solutions.kaleidoscope.dashboard.DashboardMap;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer;

public class SessionDashboard
{
  private static HashMap<String, Set<SessionDashboard>> instances = new HashMap<String, Set<SessionDashboard>>();
  
  private final DashboardMap dashboard;
  
  private final String sessionId;
  
  private Set<SessionLayer> extraLayers;
  
  private SessionDashboard(DashboardMap dashboard, String sessionId)
  {
    this.dashboard = dashboard;
    this.sessionId = sessionId;
    this.extraLayers = new HashSet<SessionLayer>();
  }
  
  public static DashboardLayer getContainingLayer(DashboardStyle dashboardStyle)
  {
    Set<SessionDashboard> sessionDashboards = instances.get(Session.getCurrentSession().getId());
    if (sessionDashboards == null) { return null; }
    
    for (SessionDashboard sessionDb : sessionDashboards)
    {
      for (SessionLayer layer : sessionDb.extraLayers)
      {
        for (DashboardStyle sessionStyle : layer.styles)
        {
          if (sessionStyle.getId().equals(dashboardStyle.getId()))
          {
            return layer.layer;
          }
        }
      }
    }
    
    return null;
  }
  
  public static DashboardMap getContainingMap(DashboardLayer dashboardLayer)
  {
    Set<SessionDashboard> sessionDashboards = instances.get(Session.getCurrentSession().getId());
    if (sessionDashboards == null) { return null; }
    
    for (SessionDashboard sessionDb : sessionDashboards)
    {
      List<DashboardLayer> layers = sessionDb.getExtraLayers();
      
      for (DashboardLayer layer : layers)
      {
        if (layer.equals(dashboardLayer))
        {
          return sessionDb.dashboard;
        }
      }
    }
    
    return null;
  }
  
  public static Iterable<DashboardStyle> getExtraStyles(DashboardLayer layer)
  {
    DashboardMap mapIn = SessionDashboard.getContainingMap(layer);
    if (mapIn == null) { return new ArrayList<DashboardStyle>(); }
    
    SessionDashboard sessionDashboard = SessionDashboard.getInstance(mapIn);
    
    for (SessionLayer sessionLayer : sessionDashboard.extraLayers)
    {
      if (sessionLayer.getDashboardLayer().getId().equals(layer.getId()))
      {
        return sessionLayer.getDashboardStyles();
      }
    }
    
    return new ArrayList<DashboardStyle>();
  }
  
  public List<DashboardLayer> getExtraLayers()
  {
    List<DashboardLayer> retLayers = new ArrayList<DashboardLayer>();
    
    for (SessionLayer sessionLayer : this.extraLayers)
    {
      retLayers.add(sessionLayer.getDashboardLayer());
    }
    
    return retLayers;
  }
  
  public boolean containsLayer(DashboardLayer layer)
  {
    for (SessionLayer sessionLayer : this.extraLayers)
    {
      if (sessionLayer.getDashboardLayer().getId().equals(layer.getId()))
      {
        return true;
      }
    }
    
    return false;
  }
  
  public void addLayer(DashboardLayer layer)
  {
    if (!this.containsLayer(layer))
    {
      this.extraLayers.add(new SessionLayer(layer));
    }
  }
  
  public void addStyle(DashboardLayer layer, DashboardStyle style)
  {
    for (SessionLayer sessionLayer : this.extraLayers)
    {
      if (sessionLayer.getDashboardLayer().equals(layer))
      {
        sessionLayer.addStyle(style);
        break;
      }
    }
  }
  
  public static synchronized SessionDashboard getInstance(DashboardMap dashboard)
  {
    return SessionDashboard.getInstance(dashboard, Session.getCurrentSession().getId());
  }
  
  public static synchronized SessionDashboard getInstance(DashboardMap dashboard, String sessionId)
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
      SessionDashboard sessionDashboard = new SessionDashboard(dashboard, sessionId);
      list.add(sessionDashboard);
      
      instances.put(sessionId, list);
      
      return sessionDashboard;
    }
    else
    {
      Set<SessionDashboard> dashboards = instances.get(sessionId);
      
      for (SessionDashboard sessionDashboard : dashboards)
      {
        if (sessionDashboard.equals(dashboard))
        {
          return sessionDashboard;
        }
      }
      
      SessionDashboard sessionDashboard = new SessionDashboard(dashboard, sessionId);
      dashboards.add(sessionDashboard);
      return sessionDashboard;
    }
  }
  
  private class SessionLayer
  {
    private Set<DashboardStyle> styles = new HashSet<DashboardStyle>();
    
    private DashboardLayer layer;
    
    protected SessionLayer(DashboardLayer layer)
    {
      this.layer = layer;
    }
    
    public DashboardLayer getDashboardLayer()
    {
      return this.layer;
    }
    
    public void addStyle(DashboardStyle style)
    {
      styles.add(style);
    }
    
    public Iterable<DashboardStyle> getDashboardStyles()
    {
      return styles;
    }
    
    @Override
    public int hashCode()
    {
      return this.layer.hashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
      return this.layer.equals(obj);
    }
  }
  
  @Override
  public int hashCode()
  {
    return this.dashboard.hashCode();
  }
  
  @Override
  public boolean equals(Object obj)
  {
    return this.dashboard.equals(obj);
  }
}
