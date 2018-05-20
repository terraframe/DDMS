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
package dss.vector.solutions.kaleidoscope.dashboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.SingleActorDAO;
import com.runwaysdk.business.rbac.SingleActorDAOIF;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.dataaccess.DuplicateDataException;
import com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AttributeChar;
import com.runwaysdk.query.CONCAT;
import com.runwaysdk.query.Coalesce;
import com.runwaysdk.query.F;
import com.runwaysdk.query.MAX;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.RequestType;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.SingleActor;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdBusinessQuery;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.kaleidoscope.MappableClass;
import dss.vector.solutions.kaleidoscope.MappableClassGeoNodeQuery;
import dss.vector.solutions.kaleidoscope.MappableClassQuery;
import dss.vector.solutions.kaleidoscope.TaskExecutor;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardCondition;
import dss.vector.solutions.kaleidoscope.dashboard.condition.LocationCondition;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer;
import dss.vector.solutions.kaleidoscope.dashboard.layer.UnsupportedAggregationException;
import dss.vector.solutions.kaleidoscope.dashboard.query.GeoEntityThematicQueryBuilder;
import dss.vector.solutions.kaleidoscope.dashboard.query.GeometryThematicQueryBuilder;
import dss.vector.solutions.kaleidoscope.dashboard.query.ThematicQueryBuilder;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeQuery;
import dss.vector.solutions.kaleidoscope.report.KaleidoscopeReport;
import dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportQuery;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.BrowserRootQuery;
import dss.vector.solutions.ontology.BrowserRootView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.QueryUtil;
import net.coobird.thumbnailator.Thumbnails;

public class Dashboard extends DashboardBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static class ThumbnailTask implements Runnable, Reloadable
  {
    private Dashboard     dashboard;

    private SingleActor[] users;

    private String        sessionId;

    public ThumbnailTask(String sessionId, Dashboard dashboard, SingleActor... users)
    {
      this.dashboard = dashboard;
      this.users = users;
      this.sessionId = sessionId;
    }

    @Override
    public void run()
    {
      this.execute(this.sessionId);
    }

    @Request(RequestType.SESSION)
    public void execute(String sessionId)
    {
      dashboard.generateThumbnailImage(users);
    }

  }

  private static final long serialVersionUID = 2043512251;

  public Dashboard()
  {
    super();
  }

  @Override
  public String toString()
  {
    return this.getDisplayLabel().getValue();
  }

  public static DashboardQuery getSortedDashboards()
  {
    QueryFactory f = new QueryFactory();
    DashboardQuery q = new DashboardQuery(f);
    q.WHERE(q.getDisease().EQ(Disease.getCurrent()));

    q.ORDER_BY_ASC(q.getDisplayLabel().localize());

    return q;
  }

  @Override
  @Transaction
  public void delete()
  {
    // Delete all saved states
    DashboardStateQuery query = new DashboardStateQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(this));

    OIterator<? extends DashboardState> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        DashboardState state = it.next();
        state.delete();
      }
    }
    finally
    {
      it.close();
    }

    // Delete the corresponding map
    this.getMap().delete();

    // Delete the wrapper mapping
    OIterator<? extends MetadataWrapper> mIterator = this.getAllMetadata();

    try
    {
      while (mIterator.hasNext())
      {
        MetadataWrapper metadata = mIterator.next();
        metadata.delete();
      }
    }
    finally
    {
      mIterator.close();
    }

    /*
     * Delete the cycle job corresponding to the map
     */
    this.deleteDashboardJob();

    // Delete the corresponding report item
    KaleidoscopeReport report = KaleidoscopeReport.getByDashboard(this.getId());

    if (report != null)
    {
      report.delete();
    }

    Roles role = this.getDashboardRole();

    super.delete();

    // Delete the dashboard role
    role.delete();
  }

  private void deleteDashboardJob()
  {
    DashboardJob job = this.getDashboardJob();

    if (job != null)
    {
      job.delete();
    }
  }

  private DashboardJob getDashboardJob()
  {
    DashboardJobQuery query = new DashboardJobQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(this));

    OIterator<? extends DashboardJob> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
    }
    finally
    {
      it.close();
    }

    return null;
  }

  public DashboardMap getMap()
  {
    DashboardMapQuery query = new DashboardMapQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(this));

    OIterator<? extends DashboardMap> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public String getMapId()
  {
    DashboardMap map = this.getMap();

    if (map != null)
    {
      return map.getId();
    }

    return null;
  }

  public MdClass[] getSortedTypes()
  {
    // This operation should use only cached objects
    DashboardMetadataQuery query = new DashboardMetadataQuery(new QueryFactory());
    query.WHERE(query.getParent().EQ(this));
    query.ORDER_BY_ASC(query.getListOrder());

    OIterator<? extends DashboardMetadata> iter = query.getIterator();

    List<MdClass> mdClasses = new LinkedList<MdClass>();

    try
    {
      while (iter.hasNext())
      {
        DashboardMetadata dm = iter.next();
        MetadataWrapper mw = dm.getChild();

        mdClasses.add(mw.getWrappedMdClass());
      }
    }
    finally
    {
      iter.close();
    }

    return mdClasses.toArray(new MdClass[mdClasses.size()]);
  }

  @Transaction
  public static Dashboard create(Dashboard dashboard)
  {
    dashboard.apply();

    return dashboard;
  }

  @Override
  @Transaction
  public void apply()
  {
    boolean isNew = isNew();

    if (this.isNew() && !this.isAppliedToDB())
    {
      this.setDisease(Disease.getCurrent());

      if (this.getName() == null || this.getName().length() == 0)
      {
        this.setName(LocalProperty.getNextId());
      }

      String dashboardLabel = this.getDisplayLabel().getValue();
      String roleName = this.getName().replaceAll("\\s", "");

      // Create the Dashboard Role
      Roles role = new Roles();
      role.setRoleName("ddms." + roleName);
      role.getDisplayLabel().setValue(dashboardLabel);
      role.apply();

      this.setDashboardRole(role);
    }

    super.apply();

    Roles role = this.getDashboardRole();
    role.lock();
    role.getDisplayLabel().setValue(this.getDisplayLabel().getValue());
    role.apply();

    if (isNew)
    {
      DashboardMap map = new DashboardMap();
      map.setName(this.getName());
      map.setDashboard(this);
      map.apply();

      DashboardState state = new DashboardState();
      state.setDashboard(this);
      state.apply();
    }
  }

  @Override
  public String applyWithOptions(String options)
  {
    // Loop until a name is selected which works
    while (true)
    {
      try
      {
        return this.applyWithOptionsInTransaction(options);
      }
      catch (DuplicateDataException e)
      {
        this.setName(LocalProperty.getNextId());
      }
    }
  }

  @Transaction
  private String applyWithOptionsInTransaction(String options)
  {
    try
    {
      JSONObject object = new JSONObject(options);

      if (object.has("label"))
      {
        this.getDisplayLabel().setValue(object.getString("label"));
      }

      this.apply();

      if (object.has("types"))
      {
        JSONArray types = object.getJSONArray("types");

        MappableClass.assign(this, types);
      }

      /*
       * If this is a new instance always give the current user permissions to the dashboard
       */
      if (this.isNew())
      {
        String roleId = this.getDashboardRoleId();
        SingleActorDAOIF user = Session.getCurrentSession().getUser();

        RoleDAO roleDAO = RoleDAO.get(roleId).getBusinessDAO();
        roleDAO.assignMember(user);
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    return this.getJSON();
  }

  @Override
  public String getLayersToDelete(String options)
  {
    try
    {
      JSONArray layers = new JSONArray();

      JSONObject object = new JSONObject(options);

      if (object.has("types"))
      {
        JSONArray types = object.getJSONArray("types");

        Collection<String> layerNames = MappableClass.getLayersToDelete(this, types);

        for (String layerName : layerNames)
        {
          layers.put(layerName);
        }
      }

      return layers.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

  }

  @Override
  @Transaction
  public Dashboard clone(String name)
  {
    Dashboard clone = new Dashboard();
    clone.getDisplayLabel().setDefaultValue(name);
    clone.getDescription().setDefaultValue(this.getDescription().getValue());
    clone.apply();

    OIterator<? extends DashboardMetadata> allMetadata = this.getAllMetadataRel();

    try
    {
      while (allMetadata.hasNext())
      {
        DashboardMetadata rel = allMetadata.next();

        MetadataWrapper existingWrapper = rel.getChild();

        MetadataWrapper cloneWrapper = existingWrapper.clone(clone);

        DashboardMetadata dm = clone.addMetadata(cloneWrapper);
        dm.setListOrder(rel.getListOrder());
        dm.apply();
      }
    }
    finally
    {
      allMetadata.close();
    }

    DashboardMap map = clone.getMap();

    // Clone the layer definitions
    DashboardLayer[] layers = this.getMap().getOrderedLayers();

    for (DashboardLayer layer : layers)
    {
      layer.clone(map);
    }

    // Clone the global state
    DashboardState state = DashboardState.getDashboardState(this, null);
    state.clone(this);

    SingleActor user = MDSSUser.getCurrentUser();

    if (user != null)
    {
      RoleDAO roleDAO = RoleDAO.get(clone.getDashboardRoleId()).getBusinessDAO();
      roleDAO.assignMember((SingleActorDAOIF) SingleActorDAO.get(user.getId()));
    }

    return clone;
  }

  public static Dashboard getDashboardByLabel(String label)
  {
    DashboardQuery query = new DashboardQuery(new QueryFactory());
    query.WHERE(query.getDisplayLabel().localize().EQ(label));
    OIterator<? extends Dashboard> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  public static String[] getTextSuggestions(String mdAttributeId, String text, Integer limit)
  {
    List<String> suggestions = new LinkedList<String>();

    MdAttributeConcreteDAOIF mdAttributeConcrete = MdAttributeDAO.get(mdAttributeId).getMdAttributeConcrete();
    MdClassDAOIF mdClass = mdAttributeConcrete.definedByClass();

    QueryFactory factory = new QueryFactory();

    BusinessQuery bQuery = factory.businessQuery(mdClass.definesType());
    AttributeChar selectable = null;

    if (mdAttributeConcrete instanceof MdAttributeCharacterDAOIF)
    {
      selectable = bQuery.aCharacter(mdAttributeConcrete.definesAttribute());
    }
    else
    {
      selectable = bQuery.aText(mdAttributeConcrete.definesAttribute());
    }

    ValueQuery query = new ValueQuery(factory);
    query.SELECT_DISTINCT(selectable);
    query.WHERE(selectable.LIKEi("%" + text + "%"));
    query.ORDER_BY_ASC(selectable);
    query.restrictRows(limit, 1);

    OIterator<ValueObject> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        ValueObject object = iterator.next();
        String value = object.getValue(mdAttributeConcrete.definesAttribute());

        suggestions.add(value);
      }
    }
    finally
    {
      iterator.close();
    }

    return suggestions.toArray(new String[suggestions.size()]);
  }

  public static String getTermSuggestions(String mdAttributeId, String text, Integer limit)
  {
    MdAttributeDAOIF mdAttributeDAOIF = MdAttributeDAO.get(mdAttributeId);
    String[] parameters = new String[] { mdAttributeDAOIF.definedByClass().definesType(), mdAttributeDAOIF.definesAttribute() };

    ValueQuery query = Term.termQueryWithRoots(text, parameters);

    OIterator<ValueObject> it = query.getIterator();

    try
    {
      JSONArray results = new JSONArray();

      while (it.hasNext())
      {
        ValueObject vObject = it.next();
        String id = vObject.getValue(Term.ID);
        String label = vObject.getValue(Term.TERMDISPLAYLABEL);

        JSONObject result = new JSONObject();
        result.put("label", label);
        result.put("value", label);
        result.put("id", id);

        results.put(result);
      }

      return results.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      it.close();
    }
  }

  public static String getClassifierTree(String mdAttributeId)
  {
    JSONArray nodes = Dashboard.getClassifierTreeJSON(mdAttributeId);

    return nodes.toString();
  }

  public static JSONArray getClassifierTreeJSON(String mdAttributeId)
  {
    MdAttributeConcreteDAOIF mdAttribute = MdAttributeDAO.get(mdAttributeId).getMdAttributeConcrete();

    List<BrowserRootView> roots = BrowserRoot.getDirectAttributeRoots(mdAttribute.definedByClass().definesType(), mdAttribute.definesAttribute());

    JSONArray nodes = new JSONArray();

    for (BrowserRootView root : roots)
    {
      Term term = Term.get(root.getTermId());

      if (root.getSelectable())
      {
        nodes.put(term.getJSONObject());
      }
      else
      {
        List<Term> children = term.getActiveChildren();

        for (Term child : children)
        {
          nodes.put(child.getJSONObject());
        }
      }
    }

    return nodes;
  }

  public static long getOptionCount(String mdAttributeId)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = MdAttributeDAO.get(mdAttributeId).getMdAttributeConcrete();

    QueryFactory factory = new QueryFactory();

    BrowserRootQuery rootQuery = BrowserRoot.getAttributeRoots(null, mdAttributeConcrete.getId(), factory);

    AllPathsQuery aptQuery = new AllPathsQuery(factory);
    aptQuery.WHERE(aptQuery.getParentTerm().EQ(rootQuery.getTerm()));

    return aptQuery.getCount();
  }

  public static String[] getCategoryInputSuggestions(String mdAttributeId, String geoNodeId, String universalId, String aggregationVal, String text, Integer limit, String state)
  {
    List<DashboardCondition> conditions = DashboardCondition.getConditionsFromState(state);

    Set<String> suggestions = new TreeSet<String>();

    MdAttributeDAOIF mdAttribute = MdAttributeDAO.get(mdAttributeId);
    String attributeName = mdAttribute.definesAttribute();

    ThematicQueryBuilder builder = getBuilder(geoNodeId, universalId, aggregationVal, conditions, mdAttribute);
    ValueQuery query = builder.getThematicValueQuery();

    OIterator<ValueObject> iterator = null;

    try
    {
      iterator = query.getIterator();

      while (iterator.hasNext())
      {
        ValueObject object = iterator.next();

        String value = object.getValue(attributeName);

        if (value.toLowerCase().contains(text.toLowerCase()))
        {
          suggestions.add(value);
        }
      }
    }
    finally
    {
      if (iterator != null)
      {
        iterator.close();
      }
    }

    String[] array = suggestions.toArray(new String[suggestions.size()]);

    return Arrays.copyOf(array, Math.min(limit, array.length));
  }

  private static ThematicQueryBuilder getBuilder(String geoNodeId, String universalId, String aggregationVal, List<DashboardCondition> conditions, MdAttributeDAOIF mdAttribute)
  {
    GeoNode geoNode = GeoNode.get(geoNodeId);
    AllAggregationType aggregationType = AllAggregationType.valueOf(aggregationVal);

    if (universalId.equals(GeometryAggregationStrategy.VALUE))
    {
      return new GeometryThematicQueryBuilder(new QueryFactory(), mdAttribute, null, aggregationType, conditions, geoNode);
    }
    else
    {
      GeoHierarchy universal = GeoHierarchy.get(universalId);

      return new GeoEntityThematicQueryBuilder(new QueryFactory(), mdAttribute, null, aggregationType, conditions, universal, geoNode);
    }
  }

  public List<GeoEntity> getCountries()
  {
    GeoEntityQuery query = (GeoEntityQuery) QueryUtil.getQuery(MdClassDAO.getMdClassDAO(Country.CLASS), new QueryFactory());
    query.ORDER_BY_ASC(query.getEntityLabel().localize());

    OIterator<? extends GeoEntity> it = query.getIterator();

    try
    {
      List<? extends GeoEntity> entities = it.getAll();

      return new LinkedList<GeoEntity>(entities);
    }
    finally
    {
      it.close();
    }
  }

  // @Override
  // public Boolean hasReport()
  // {
  // ReportItemQuery query = new ReportItemQuery(new QueryFactory());
  // query.WHERE(query.getDashboard().EQ(this));
  //
  // return ( query.getCount() > 0 );
  // }

  public List<DashboardCondition> getConditions()
  {
    DashboardState state = this.getDashboardState();

    if (state != null)
    {
      String json = state.getConditions();

      if (json != null && json.length() > 0)
      {
        List<DashboardCondition> conditions = DashboardCondition.deserialize(json);

        return conditions;
      }
    }

    return new LinkedList<DashboardCondition>();
  }

  public Map<String, DashboardCondition> getConditionMap()
  {
    Map<String, DashboardCondition> map = new HashMap<String, DashboardCondition>();

    List<DashboardCondition> conditions = this.getConditions();

    for (DashboardCondition condition : conditions)
    {
      map.put(condition.getJSONKey(), condition);
    }

    return map;
  }

  @Override
  public String getConditionsJSON()
  {
    DashboardState state = this.getDashboardState();
    String conditions = state.getConditions();

    if (conditions != null && conditions.length() > 0)
    {
      return conditions;
    }

    return "''";
  }

  public Map<String, Integer> getUniversalIndices()
  {
    Map<String, Integer> indices = new HashMap<String, Integer>();

    int count = 0;

    GeoHierarchy country = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

    List<GeoHierarchy> children = country.getAllChildren();

    indices.put(country.getId(), count++);

    for (GeoHierarchy child : children)
    {
      indices.put(child.getId(), count++);
    }

    return indices;
  }

  public GeoNode[] getGeoNodes(MdAttribute thematicAttribute)
  {
    MdAttributeDAOIF thematicAttributeDAO = MdAttributeDAO.get(thematicAttribute.getId());

    return this.getGeoNodes(thematicAttributeDAO);
  }

  @Override
  public String getGeoNodesJSON(MdAttribute thematicAttribute)
  {
    return this.getGeoNodesJSON(thematicAttribute, true);
  }

  public String getGeoNodesJSON(MdAttribute thematicAttribute, Boolean aggregatable)
  {
    try
    {
      JSONArray nodesArr = new JSONArray();
      GeoNode[] nodes = this.getGeoNodes(thematicAttribute);
      for (GeoNode node : nodes)
      {
        String nodeLabel = node.getGeoEntityAttribute().getDisplayLabel().getValue();

        if (node.getSuffix() != null && node.getSuffix().length() > 0)
        {
          nodeLabel += " (" + node.getSuffix() + ")";
        }

        JSONObject nodeJSON = new JSONObject();
        nodeJSON.put("id", node.getId());
        nodeJSON.put("type", node.getType());
        nodeJSON.put("displayLabel", nodeLabel);
        nodesArr.put(nodeJSON);
      }

      if (nodesArr.length() == 0)
      {
        throw new UnsupportedAggregationException();
      }

      return nodesArr.toString();
    }
    catch (JSONException e)
    {
      String error = "Could not build GeoNode JSON.";
      throw new ProgrammingErrorException(error, e);
    }
  }

  public GeoNode[] getGeoNodes(MdAttributeDAOIF thematicAttribute)
  {
    QueryFactory factory = new QueryFactory();

    MappableClassQuery mcQuery = new MappableClassQuery(factory);
    mcQuery.AND(mcQuery.getWrappedMdClass().EQ(thematicAttribute.definedByClass()));

    MappableClassGeoNodeQuery mcgnQuery = new MappableClassGeoNodeQuery(factory);
    mcgnQuery.WHERE(mcgnQuery.getParent().EQ(mcQuery));

    GeoNodeQuery gnQuery = new GeoNodeQuery(factory);
    gnQuery.WHERE(gnQuery.EQ(mcgnQuery.getChild()));

    OIterator<? extends GeoNode> iterator = gnQuery.getIterator();

    List<GeoNode> nodes = new LinkedList<GeoNode>();

    try
    {
      while (iterator.hasNext())
      {
        GeoNode geoNode = iterator.next();

        nodes.add(geoNode);
      }

      return nodes.toArray(new GeoNode[nodes.size()]);

    }
    finally
    {
      iterator.close();
    }
  }

  public GeoNode getGeoNode(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();
    QueryFactory factory = new QueryFactory();

    MappableClassQuery mcQuery = new MappableClassQuery(factory);
    mcQuery.AND(mcQuery.getWrappedMdClass().EQ(mdAttribute.definedByClass()));

    MappableClassGeoNodeQuery mcgnQuery = new MappableClassGeoNodeQuery(factory);
    mcgnQuery.WHERE(mcgnQuery.getParent().EQ(mcQuery));

    GeoNodeQuery gnQuery = new GeoNodeQuery(factory);
    gnQuery.WHERE(gnQuery.EQ(mcgnQuery.getChild()));

    OIterator<? extends GeoNode> iterator = gnQuery.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        GeoNode geoNode = iterator.next();
        // Geo entity node
        String geoEntityAttributeId = geoNode.getGeoEntityAttributeId();

        if (geoEntityAttributeId.equals(mdAttribute.getId()) || geoEntityAttributeId.equals(mdAttributeConcrete.getId()))
        {
          return geoNode;
        }
      }

      throw new ProgrammingErrorException("Unable to find a Geo Node for the Dashboard [" + this.getId() + "] and Attribute [" + mdAttribute.getId() + "]");
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public InputStream getThumbnailStream()
  {
    DashboardState state = this.getDashboardState();
    byte[] buffer = state.getMapThumbnail();

    return new ByteArrayInputStream(buffer);
  }

  private DashboardState getDashboardState()
  {
    SingleActor user = MDSSUser.getCurrentUser();

    DashboardState state = null;

    if (user != null)
    {
      state = DashboardState.getDashboardState(this, user);
    }

    if (state == null)
    {
      state = DashboardState.getDashboardState(this, null);
    }

    return state;
  }

  private DashboardState getOrCreateDashboardState(SingleActor user)
  {
    DashboardState state = DashboardState.getDashboardState(this, user);

    if (state == null)
    {
      state = new DashboardState();
      state.setDashboard(this);
      state.setGeoprismUser(user);
    }
    else
    {
      state.lock();
    }
    return state;
  }

  @Override
  @Transaction
  public void setBaseLayerState(String baseLayerState)
  {
    DashboardMap dm = this.getMap();
    dm.lock();
    dm.setActiveBaseMap(baseLayerState);
    dm.unlock();
  }

  public MetadataWrapper getMetadataWrapper(MdClass mdClass)
  {
    MetadataWrapperQuery query = new MetadataWrapperQuery(new QueryFactory());
    query.WHERE(query.getWrappedMdClass().EQ(mdClass));
    query.AND(query.getDashboard().EQ(this));

    OIterator<? extends MetadataWrapper> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public String getJSON()
  {
    try
    {
      return this.toJSON().toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public JSONObject toJSON() throws JSONException
  {
    // /*
    // * Ensure the user has permissions to read this dashboard
    // */
    // if (!this.hasAccess())
    // {
    // SingleActorDAOIF user = Session.getCurrentSession().getUser();
    // throw new ReadPermissionException("", this, user);
    // }

    return getJSON(this.getConditionMap());
  }

  @Override
  public Boolean hasReport()
  {
    KaleidoscopeReportQuery query = new KaleidoscopeReportQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(this));

    return ( query.getCount() > 0 );
  }

  public JSONObject getJSON(Map<String, DashboardCondition> conditions) throws JSONException
  {
    MdClass[] mdClasses = this.getSortedTypes();

    JSONArray types = new JSONArray();

    for (MdClass mdClass : mdClasses)
    {
      types.put(this.toJSON(mdClass, conditions));
    }

    DashboardMap map = this.getMap();
    DashboardState state = this.getDashboardState();

    JSONObject object = new JSONObject();
    object.put("id", this.getId());
    object.put("name", this.getName());
    object.put("label", this.getDisplayLabel().getValue());
    object.put("description", this.getDescription().getValue());
    object.put("hasReport", this.hasReport());
    object.put("editDashboard", true);
    // object.put("editDashboard", GeoprismUser.hasAccess(AccessConstants.EDIT_DASHBOARD));
    object.put("savedWidth", state.getSavedWidth());
    object.put("savedHeight", state.getSavedHeight());
    object.put("scaleXPosition", state.getScaleXPosition());
    object.put("scaleYPosition", state.getScaleYPosition());
    object.put("enableScale", state.getEnableScale());
    object.put("arrowXPosition", state.getArrowXPosition());
    object.put("arrowYPosition", state.getArrowYPosition());
    object.put("enableArrow", state.getEnableArrow());
    object.put("reportXPosition", state.getReportXPosition());
    object.put("reportYPosition", state.getReportYPosition());
    object.put("isReportVertical", state.getIsReportVertical());
    object.put("isReportOpaque", state.getIsReportOpaque());
    object.put("isExpandRight", state.getIsExpandRight());
    object.put("isExpandLeft", state.getIsExpandLeft());
    object.put("mapExtent", state.getMapExtent());
    object.put("types", types);

    List<GeoEntity> countries = this.getCountries();

    JSONArray areas = new JSONArray();

    for (GeoEntity country : countries)
    {
      areas.put(country.getEntityLabel().getValue());
    }

    object.put("focusAreas", areas);

    if (map != null)
    {
      object.put("mapId", map.getId());
    }

    String activeBaseMap = map.getActiveBaseMap();

    if (activeBaseMap != null && activeBaseMap.length() > 0)
    {
      object.put("activeBaseMap", new JSONObject(activeBaseMap));
    }
    else
    {
      JSONObject baseMap = new JSONObject();
      baseMap.put("LAYER_SOURCE_TYPE", "OSM");

      object.put("activeBaseMap", baseMap);
    }

    if (conditions.containsKey(LocationCondition.CONDITION_TYPE))
    {
      DashboardCondition condition = conditions.get(LocationCondition.CONDITION_TYPE);

      object.put("location", condition.getJSON());
    }
    else
    {
      LocationCondition condition = new LocationCondition();

      object.put("location", condition.getJSON());
    }

    return object;
  }

  private JSONObject toJSON(MdClass mdClass, Map<String, DashboardCondition> conditions) throws JSONException
  {
    JSONArray attributes = new JSONArray();

    MetadataWrapper wrapper = this.getMetadataWrapper(mdClass);
    MdAttributeView[] views = wrapper.getSortedAttributes();

    for (MdAttributeView view : views)
    {
      DashboardCondition condition = conditions.get(view.getMdAttributeId());

      attributes.put(view.toJSON(condition));
    }

    JSONObject object = new JSONObject();
    object.put("label", mdClass.getDisplayLabel().getValue());
    object.put("id", mdClass.getId());
    object.put("description", this.getDescription().getValue());
    object.put("attributes", attributes);

    return object;
  }

  @Override
  public String saveState(String json, Boolean global)
  {
    try
    {
      JSONObject object = new JSONObject(json);

      List<DashboardCondition> conditions = DashboardCondition.getConditionsFromState(json);

      SingleActor user = null;

      if (!global)
      {
        user = MDSSUser.getCurrentUser();
      }

      DashboardState state = this.getOrCreateDashboardState(user);
      state.setConditions(DashboardCondition.serialize(conditions));

      if (object.has("savedWidth"))
      {
        state.setSavedWidth(object.getInt("savedWidth"));
      }

      if (object.has("savedHeight"))
      {
        state.setSavedHeight(object.getInt("savedHeight"));
      }

      if (object.has("scaleXPosition"))
      {
        state.setScaleXPosition(object.getInt("scaleXPosition"));
      }

      if (object.has("scaleYPosition"))
      {
        state.setScaleYPosition(object.getInt("scaleYPosition"));
      }

      if (object.has("enableScale"))
      {
        state.setEnableScale(object.getBoolean("enableScale"));
      }

      if (object.has("arrowXPosition"))
      {
        state.setArrowXPosition(object.getInt("arrowXPosition"));
      }

      if (object.has("arrowYPosition"))
      {
        state.setArrowYPosition(object.getInt("arrowYPosition"));
      }

      if (object.has("enableArrow"))
      {
        state.setEnableArrow(object.getBoolean("enableArrow"));
      }

      if (object.has("reportXPosition"))
      {
        state.setReportXPosition(object.getInt("reportXPosition"));
      }
      
      if (object.has("reportYPosition"))
      {
        state.setReportYPosition(object.getInt("reportYPosition"));
      }
      
      if (object.has("isReportVertical"))
      {
        state.setIsReportVertical(object.getBoolean("isReportVertical"));
      }
      
      if (object.has("isReportOpaque"))
      {
        state.setIsReportOpaque(object.getBoolean("isReportOpaque"));
      }
      
      if (object.has("isExpandRight"))
      {
        state.setIsExpandRight(object.getBoolean("isExpandRight"));
      }
      
      if (object.has("isExpandLeft"))
      {
        state.setIsExpandLeft(object.getBoolean("isExpandLeft"));
      }
      
      if (object.has("mapExtent"))
      {
        state.setMapExtent(object.getString("mapExtent"));
      }
      
      state.apply();

      this.executeThumbnailThread(user);

      return "";
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public String getDashboardInformation()
  {
    try
    {
      return this.getDashboardInformationJSON().toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public JSONObject getDashboardInformationJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("dashboardId", this.getId());
    object.put("label", this.getDisplayLabel().getValue());
    object.put("description", this.getDescription().getValue());

    return object;
  }

  public MetadataWrapper getMetadataWrapper(MdClassDAOIF mdClass)
  {
    MetadataWrapperQuery query = new MetadataWrapperQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(this));
    query.AND(query.getWrappedMdClass().EQ(mdClass.getId()));

    OIterator<? extends MetadataWrapper> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  private JSONArray getMappableClassJSON() throws JSONException
  {
    List<? extends MetadataWrapper> wrappers = this.getAllMetadata().getAll();

    JSONArray array = new JSONArray();

    MappableClass[] mClasses = MappableClass.getAll();

    for (MappableClass mClass : mClasses)
    {
      array.put(mClass.toJSON(this, wrappers));
    }
    return array;
  }

  @Override
  public String getDashboardDefinition()
  {
    try
    {
      if (!this.isNew())
      {
        this.lock();
      }

      JSONObject options = new JSONObject();
      options.put("types", this.getMappableClassJSON());

      JSONObject object = new JSONObject();
      object.put(Dashboard.NAME, this.getName());
      object.put(Dashboard.DISPLAYLABEL, this.getDisplayLabel().getValue());
      object.put(Dashboard.DESCRIPTION, this.getDescription().getValue());

      List<GeoEntity> countries = this.getCountries();

      JSONArray areas = new JSONArray();

      for (GeoEntity country : countries)
      {
        areas.put(country.getEntityLabel().getValue());
      }

      object.put("focusAreas", areas);

      object.put("options", options);

      return object.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public int getMaxOrder()
  {
    ValueQuery vQuery = new ValueQuery(new QueryFactory());

    MetadataWrapperQuery wQuery = new MetadataWrapperQuery(vQuery);
    DashboardMetadataQuery dmQuery = new DashboardMetadataQuery(vQuery);

    vQuery.WHERE(wQuery.getDashboard().EQ(this));
    vQuery.AND(dmQuery.hasChild(wQuery));

    MAX selectable = F.MAX(dmQuery.getListOrder());
    selectable.setColumnAlias("order_max");
    selectable.setUserDefinedAlias("order_max");

    vQuery.SELECT(selectable);

    OIterator<ValueObject> iterator = vQuery.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        ValueObject result = iterator.next();
        String value = result.getValue("order_max");

        if (value != null && value.length() > 0)
        {
          return Integer.parseInt(value);
        }
      }
    }
    finally
    {
      iterator.close();
    }

    return 0;
  }

  @Override
  @Transaction
  public void setMetadataWrapperOrder(String[] typeIds)
  {
    DashboardMetadataQuery query = new DashboardMetadataQuery(new QueryFactory());
    query.WHERE(query.getParent().EQ(this));

    OIterator<? extends DashboardMetadata> it = query.getIterator();

    try
    {
      List<? extends DashboardMetadata> dms = it.getAll();

      for (DashboardMetadata dm : dms)
      {
        MetadataWrapper wrapper = dm.getChild();

        for (int i = 0; i < typeIds.length; i++)
        {
          String typeId = typeIds[i];

          if (wrapper.getWrappedMdClassId().equals(typeId))
          {
            dm.lock();
            dm.setListOrder(i);
            dm.apply();
          }
        }
      }
    }
    finally
    {
      it.close();
    }
  }

  @Override
  @Transaction
  public void setDashboardAttributesOrder(String classId, String[] attributeIds)
  {
    MetadataWrapper wrapper = MetadataWrapper.getByWrappedMdClassId(this, classId);

    if (wrapper != null)
    {
      DashboardAttributesQuery query = new DashboardAttributesQuery(new QueryFactory());
      query.WHERE(query.getParent().EQ(wrapper));

      OIterator<? extends DashboardAttributes> it = query.getIterator();

      try
      {
        List<? extends DashboardAttributes> attributes = it.getAll();

        for (DashboardAttributes attribute : attributes)
        {
          AttributeWrapper aw = attribute.getChild();

          for (int i = 0; i < attributeIds.length; i++)
          {
            String attributeId = attributeIds[i];

            if (aw.getWrappedMdAttributeId().equals(attributeId))
            {
              attribute.lock();
              attribute.setListOrder(i);
              attribute.apply();
            }
          }
        }
      }
      finally
      {
        it.close();
      }
    }
  }

  public static String getAvailableDashboardsAsJSON(String dashboardId)
  {
    DashboardQuery query = Dashboard.getSortedDashboards();
    OIterator<? extends Dashboard> iterator = query.getIterator();

    try
    {
      JSONArray dashboards = new JSONArray();
      boolean first = true;

      JSONObject response = new JSONObject();

      while (iterator.hasNext())
      {
        Dashboard dashboard = iterator.next();

        JSONObject object = dashboard.getDashboardInformationJSON();

        dashboards.put(object);

        if (first || dashboard.getId().equals(dashboardId))
        {
          response.put("state", dashboard.toJSON());

          first = false;
        }
      }

      response.put("dashboards", dashboards);

      return response.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      iterator.close();
    }

  }

  public ValueQuery getGeoEntitySuggestions(String text, Integer limit)
  {
    ValueQuery query = new ValueQuery(new QueryFactory());

    // List<GeoEntity> countries = this.getCountries();

    GeoEntityQuery entityQuery = new GeoEntityQuery(query);
    MdBusinessQuery mdQ = new MdBusinessQuery(query);
    // dss.vector.solutions.geo.AllPathsQuery aptQuery = new dss.vector.solutions.geo.AllPathsQuery(query);

    SelectableChar id = entityQuery.getId();
    Coalesce universalLabel = mdQ.getDisplayLabel().localize(MdBusinessInfo.DISPLAY_LABEL);
    Coalesce geoLabel = entityQuery.getEntityLabel().localize();
    SelectableChar geoId = entityQuery.getGeoId();

    CONCAT label = F.CONCAT(F.CONCAT(F.CONCAT(F.CONCAT(geoLabel, " ("), F.CONCAT(universalLabel, ")")), " : "), geoId);
    label.setColumnAlias(GeoEntity.ENTITYLABEL);
    label.setUserDefinedAlias(GeoEntity.ENTITYLABEL);
    label.setUserDefinedDisplayLabel(GeoEntity.ENTITYLABEL);

    // Condition cCondition = null;
    //
    // for (GeoEntity country : countries)
    // {
    // if (cCondition == null)
    // {
    // cCondition = aptQuery.getParentGeoEntity().EQ(country);
    // }
    // else
    // {
    // cCondition = OR.get(cCondition, aptQuery.getPGeoEntity().EQ(country));
    // }
    // }

    query.SELECT(id, label);
    query.WHERE(label.LIKEi("%" + text + "%"));
    // query.AND(entityQuery.EQ(aptQuery.getChildGeoEntity()));
    query.AND(F.CONCAT(mdQ.getPackageName(), F.CONCAT(".", mdQ.getTypeName())).EQ(entityQuery.getType()));
    query.AND(entityQuery.getActivated().EQ(true));
    // query.AND(cCondition);

    query.ORDER_BY_ASC(geoLabel);

    query.restrictRows(limit, 1);

    return query;
  }

  @Override
  public void generateThumbnailImage()
  {
    /*
     * This method is only invoked when a new layer is created. As such, it generates a thumbnail for both the current users state and the global
     * state. Normally you just want to generate a thumbnail for one or the other.
     */
    this.executeThumbnailThread(MDSSUser.getCurrentUser(), null);
  }

  private void executeThumbnailThread(SingleActor... users)
  {
    String sessionId = Session.getCurrentSession().getId();

    // Write the thumbnail
    TaskExecutor.addTask(new ThumbnailTask(sessionId, this, users));
  }

  @Transaction
  private void generateThumbnailImage(SingleActor[] users)
  {
    byte[] image = this.generateThumbnail();

    for (SingleActor user : users)
    {
      DashboardState state = DashboardState.getDashboardState(this, user);

      if (state != null)
      {
        state.lock();
        if (image == null)
        {
          state.setMapThumbnail(new byte[0]);
        }
        else
        {
          state.setMapThumbnail(image);
        }
        state.apply();
      }
    }
  }

  @Transaction
  public byte[] generateThumbnail()
  {
    String outFileFormat = "png";
    BufferedImage base = null;
    Graphics mapBaseGraphic = null;
    BufferedImage resizedImage = null;
    int width = 660;
    int height = 420;
    Double bottom;
    Double top;
    Double right;
    Double left;
    JSONObject restructuredBounds = new JSONObject();

    DashboardMap dashMap = this.getMap();

    // Ordering the layers from the default map
    DashboardLayer[] orderedLayers = dashMap.getOrderedLayers();
    JSONArray mapBoundsArr = dashMap.getExpandedMapLayersBBox(orderedLayers, .2);

    if (mapBoundsArr != null)
    {

      // Get bounds of the map
      try
      {
        left = mapBoundsArr.getDouble(0);
        bottom = mapBoundsArr.getDouble(1);
        right = mapBoundsArr.getDouble(2);
        top = mapBoundsArr.getDouble(3);

        restructuredBounds.put("left", left);
        restructuredBounds.put("bottom", bottom);
        restructuredBounds.put("right", right);
        restructuredBounds.put("top", top);
      }
      catch (JSONException e)
      {
        String error = "Could not parse map bounds.";
        throw new ProgrammingErrorException(error, e);
      }

      int layerWidth = (int) Math.min(width, Math.round( ( ( ( right - left ) / ( top - bottom ) ) * height )));
      int layerHeight = (int) Math.min(height, Math.round( ( ( ( top - bottom ) / ( right - left ) ) * width )));

      // Offset the layerCanvas so that it is center
      int widthOffset = (int) ( ( width - layerWidth ) / 2 );
      int heightOffset = (int) ( ( height - layerHeight ) / 2 );

      MapBound bound = new MapBound(layerWidth, layerHeight, left, right, bottom, top);

      base = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

      // Create the base canvas that all other map elements will be draped on top of
      mapBaseGraphic = base.getGraphics();
      mapBaseGraphic.setColor(Color.white);
      mapBaseGraphic.fillRect(0, 0, width, height);
      mapBaseGraphic.drawImage(base, 0, 0, null);

      // Ticket #412: Get base map
      String baseType = "osm";

      if (baseType.length() > 0)
      {
        BufferedImage baseMapImage = dashMap.getBaseMapCanvas(layerWidth, layerHeight, Double.toString(left), Double.toString(bottom), Double.toString(right), Double.toString(top), baseType);

        if (baseMapImage != null)
        {
          mapBaseGraphic.drawImage(baseMapImage, widthOffset, heightOffset, null);
        }
      }

      // Add layers to the base canvas
      BufferedImage layerCanvas = dashMap.getLayersExportCanvas(orderedLayers, bound);

      mapBaseGraphic.drawImage(layerCanvas, widthOffset, heightOffset, null);

      try
      {
        resizedImage = Thumbnails.of(base).size(330, 210).asBufferedImage();
      }
      catch (IOException e)
      {
        String error = "Could not resize map image to thumbnail size.";
        throw new ProgrammingErrorException(error, e);
      }

      ByteArrayOutputStream outStream = new ByteArrayOutputStream();

      try
      {
        ImageIO.write(resizedImage, outFileFormat, outStream);
      }
      catch (IOException e)
      {
        String error = "Could not write map image to the output stream.";
        throw new ProgrammingErrorException(error, e);
      }
      finally
      {
        if (outStream != null)
        {
          try
          {
            outStream.flush();
            outStream.close();
          }
          catch (IOException e)
          {
            String error = "Could not close stream.";
            throw new ProgrammingErrorException(error, e);
          }
        }
      }

      if (mapBaseGraphic != null)
      {
        mapBaseGraphic.dispose();
      }

      return outStream.toByteArray();
    }

    return null;
  }
}
