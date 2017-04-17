package dss.vector.solutions.kaleidoscope;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.constants.BusinessInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdElementDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.kaleidoscope.dashboard.AttributeWrapper;
import dss.vector.solutions.kaleidoscope.dashboard.AttributeWrapperQuery;
import dss.vector.solutions.kaleidoscope.dashboard.Dashboard;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardAttributes;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardMetadata;
import dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapper;
import dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapperQuery;
import dss.vector.solutions.kaleidoscope.data.etl.TargetBinding;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.DatabaseUtil;

public class MappableClass extends MappableClassBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static class LabelComparator implements Comparator<MdAttributeDAOIF>, Reloadable
  {
    private Locale locale;

    public LabelComparator(Locale _locale)
    {
      this.locale = _locale;
    }

    @Override
    public int compare(MdAttributeDAOIF o1, MdAttributeDAOIF o2)
    {
      return o1.getDisplayLabel(locale).compareTo(o2.getDisplayLabel(locale));
    }
  }

  private static final long serialVersionUID = -931571965;

  public MappableClass()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    MdClass mdClass = this.getWrappedMdClass();

    if (mdClass != null)
    {
      return mdClass.getKey();
    }

    return super.buildKey();
  }

  @Override
  @Transaction
  public void delete()
  {
    MdClass mdClass = this.getWrappedMdClass();

    if (mdClass.getGenerateSource())
    {
      String msg = "Cannot delete types that have generated source.";

      RequiredMappableClassException ex = new RequiredMappableClassException(msg);
      ex.setDataSetLabel(mdClass.getDisplayLabel().getValue());

      throw ex;
    }

    /*
     * Delete all layers which reference attributes on this type
     */
    MetadataWrapperQuery query = new MetadataWrapperQuery(new QueryFactory());
    query.WHERE(query.getWrappedMdClass().EQ(mdClass));

    OIterator<? extends MetadataWrapper> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        MetadataWrapper wrapper = iterator.next();
        wrapper.delete();
      }
    }
    finally
    {
      iterator.close();
    }

    /*
     * Delete all import mappings if they exist
     */
    TargetBinding binding = TargetBinding.getBinding(mdClass.definesType());

    if (binding != null)
    {
      binding.delete();
    }

    /*
     * Delete all geo nodes
     */
    OIterator<? extends GeoNode> nodes = this.getAllGeoNode();

    try
    {
      while (nodes.hasNext())
      {
        GeoNode node = nodes.next();
        node.delete();
      }
    }
    finally
    {
      nodes.close();
    }

    /*
     * Delete the corresponding MappableAttributes
     */
    List<MappableAttribute> mAttributes = MappableAttribute.getMappableAttributes(mdClass);

    for (MappableAttribute mAttribute : mAttributes)
    {
      mAttribute.delete();
    }

    super.delete();

    /*
     * Delete all of the data views which reference this type
     */
    List<String> viewNames = Database.getReferencingViews(MdElementDAO.getMdElementDAO(mdClass.definesType()));

    DatabaseUtil.dropViews(viewNames);

    mdClass.delete();
  }

  public static MappableClass getMappableClass(String type)
  {
    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(type);

    return getMappableClass(mdClass);
  }

  public static MappableClass getMappableClass(MdClassDAOIF _mdClass)
  {
    MdClass mdClass = MdClass.get(_mdClass.getId());

    MappableClassQuery query = new MappableClassQuery(new QueryFactory());
    query.WHERE(query.getWrappedMdClass().EQ(mdClass));

    OIterator<? extends MappableClass> iterator = query.getIterator();

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

  public static MappableClass[] getAll()
  {
    MappableClassQuery query = new MappableClassQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getWrappedMdClass().getDisplayLabel().localize());

    OIterator<? extends MappableClass> iterator = query.getIterator();

    try
    {
      List<? extends MappableClass> classes = iterator.getAll();

      return classes.toArray(new MappableClass[classes.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  public JSONObject toJSON() throws JSONException
  {
    return this.toJSON(null, new LinkedList<MetadataWrapper>());
  }

  public JSONObject toJSON(Dashboard dashboard) throws JSONException
  {
    List<? extends MetadataWrapper> wrappers = dashboard.getAllMetadata().getAll();

    return toJSON(dashboard, wrappers);
  }

  public JSONObject toJSON(Dashboard dashboard, List<? extends MetadataWrapper> wrappers) throws JSONException
  {
    boolean value = this.isSelected(wrappers);

    MdClassDAOIF mdClass = (MdClassDAOIF) MdClassDAO.get(this.getWrappedMdClassId());
    String label = mdClass.getDisplayLabel(Session.getCurrentLocale());
    String description = mdClass.getDescription(Session.getCurrentLocale());

    JSONObject object = new JSONObject();
    object.put("label", label);
    object.put("description", description);
    object.put("id", this.getId());
    object.put("type", mdClass.getKey());
    object.put("value", value);
    object.put("source", this.getDataSource());

    LinkedList<AttributeWrapper> attributes = new LinkedList<AttributeWrapper>();

    if (dashboard != null)
    {
      MetadataWrapper wrapper = dashboard.getMetadataWrapper(mdClass);

      if (wrapper != null)
      {
        attributes.addAll(wrapper.getAllAttributeWrapper().getAll());
      }
    }

    object.put("attributes", this.getAttributeJSON(mdClass, attributes));

    return object;
  }

  private boolean isSelected(List<? extends MetadataWrapper> wrappers)
  {
    boolean selected = false;

    for (MetadataWrapper wrapper : wrappers)
    {
      if (wrapper.getWrappedMdClassId().equals(this.getWrappedMdClassId()))
      {
        selected = true;
      }
    }
    return selected;
  }

  public static String getClassesAsJSON(String dashboardId)
  {
    Dashboard dashboard = Dashboard.get(dashboardId);
    List<? extends MetadataWrapper> wrappers = dashboard.getAllMetadata().getAll();

    MappableClassQuery query = new MappableClassQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getWrappedMdClass().getDisplayLabel().localize());

    OIterator<? extends MappableClass> it = query.getIterator();

    try
    {
      JSONArray array = new JSONArray();

      while (it.hasNext())
      {
        MappableClass mClass = it.next();

        array.put(mClass.toJSON(dashboard, wrappers));
      }

      return array.toString();
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

  public static void assign(Dashboard dashboard, JSONArray types)
  {
    try
    {
      int index = dashboard.getMaxOrder() + 1;

      for (int i = 0; i < types.length(); i++)
      {
        JSONObject type = types.getJSONObject(i);
        String id = type.getString("id");
        boolean checked = type.getBoolean("value");

        MappableClass mClass = MappableClass.get(id);
        MdClass mdClass = mClass.getWrappedMdClass();

        MetadataWrapperQuery query = new MetadataWrapperQuery(new QueryFactory());
        query.WHERE(query.getDashboard().EQ(dashboard));
        query.AND(query.getWrappedMdClass().EQ(mdClass));

        if (checked)
        {
          if (query.getCount() == 0)
          {
            MetadataWrapper wrapper = new MetadataWrapper();
            wrapper.setWrappedMdClass(mdClass);
            wrapper.setDashboard(dashboard);
            wrapper.apply();

            DashboardMetadata metadata = dashboard.addMetadata(wrapper);
            metadata.setListOrder(index + i);
            metadata.apply();

            // Add all of the attributes
            JSONArray attributes = type.getJSONArray("attributes");

            MappableClass.assign(dashboard, wrapper, attributes);
          }
          else
          {
            OIterator<? extends MetadataWrapper> iterator = query.getIterator();

            try
            {
              while (iterator.hasNext())
              {
                MetadataWrapper wrapper = iterator.next();

                // Add all of the attributes
                JSONArray attributes = type.getJSONArray("attributes");

                MappableClass.assign(dashboard, wrapper, attributes);
              }
            }
            finally
            {
              iterator.close();
            }
          }
        }
        else if (!checked && query.getCount() > 0)
        {
          OIterator<? extends MetadataWrapper> iterator = query.getIterator();

          try
          {
            while (iterator.hasNext())
            {
              MetadataWrapper wrapper = iterator.next();
              wrapper.delete();
            }
          }
          finally
          {
            iterator.close();
          }
        }

      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private static void assign(Dashboard dashboard, MetadataWrapper wrapper, JSONArray attributes)
  {
    try
    {
      int index = wrapper.getMaxOrder() + 1;

      for (int i = 0; i < attributes.length(); i++)
      {
        JSONObject type = attributes.getJSONObject(i);
        String id = type.getString("id");
        boolean checked = type.getBoolean("selected");

        MdAttribute mdAttribute = MdAttribute.get(id);

        QueryFactory factory = new QueryFactory();

        MetadataWrapperQuery wQuery = new MetadataWrapperQuery(factory);
        wQuery.WHERE(wQuery.getId().EQ(wrapper.getId()));

        AttributeWrapperQuery query = new AttributeWrapperQuery(factory);
        query.WHERE(query.dashboardMetadata(wQuery));
        query.AND(query.getWrappedMdAttribute().EQ(mdAttribute));

        if (checked && query.getCount() == 0)
        {
          AttributeWrapper attribute = new AttributeWrapper();
          attribute.setWrappedMdAttribute(mdAttribute);
          attribute.apply();

          DashboardAttributes dAttribute = wrapper.addAttributeWrapper(attribute);
          dAttribute.setListOrder(index + i);
          dAttribute.apply();
        }
        else if (!checked && query.getCount() > 0)
        {
          OIterator<? extends AttributeWrapper> iterator = query.getIterator();

          try
          {
            while (iterator.hasNext())
            {
              AttributeWrapper attribute = iterator.next();
              attribute.delete(dashboard);
            }
          }
          finally
          {
            iterator.close();
          }
        }
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private JSONArray getAttributeJSON(MdClassDAOIF mdClass, List<? extends AttributeWrapper> attributes) throws JSONException
  {
    JSONArray array = new JSONArray();

    Collection<String> ids = this.getGeoNodeAttributeIds();

    List<? extends MdAttributeDAOIF> mdAttributes = mdClass.getAllDefinedMdAttributes();

    Collections.sort(mdAttributes, new LabelComparator(Session.getCurrentLocale()));

    for (MdAttributeDAOIF mdAttribute : mdAttributes)
    {
      if (this.isValid(mdAttribute, ids))
      {
        boolean selected = this.isSelected(mdAttribute, attributes);

        JSONObject object = new JSONObject();
        object.put("label", mdAttribute.getDisplayLabel(Session.getCurrentLocale()));
        object.put("id", mdAttribute.getId());
        object.put("selected", selected);
        object.put("type", mdAttribute.getMdBusinessDAO().getTypeName());

        if (mdAttribute instanceof MdAttributeReferenceDAOIF)
        {
          MdAttributeReferenceDAOIF mdAttributeTerm = (MdAttributeReferenceDAOIF) mdAttribute;
          MdBusinessDAOIF mdBusiness = mdAttributeTerm.getReferenceMdBusinessDAO();

          if (mdBusiness.definesType().equals(Term.CLASS))
          {
            Term[] children = Term.getRootChildren(mdAttributeTerm);

            JSONArray roots = new JSONArray();

            for (Term child : children)
            {
              JSONObject root = new JSONObject();
              root.put("id", child.getId());
              root.put("label", child.getTermDisplayLabel().getValue());

              roots.put(root);
            }

            object.put("type", "Category");
            object.put("roots", roots);
          }
        }

        array.put(object);
      }
    }

    return array;
  }

  private Collection<String> getGeoNodeAttributeIds()
  {
    Collection<String> collection = new TreeSet<String>();

    OIterator<? extends GeoNode> iterator = this.getAllGeoNode();

    try
    {
      while (iterator.hasNext())
      {
        GeoNode node = iterator.next();

        collection.add(node.getGeoEntityAttributeId());

        if (node instanceof GeoNodeGeometry)
        {
          GeoNodeGeometry geometry = (GeoNodeGeometry) node;

          collection.add(geometry.getDisplayLabelAttributeId());
          collection.add(geometry.getIdentifierAttributeId());
          collection.add(geometry.getGeometryAttributeId());
          collection.add(geometry.getMultiPolygonAttributeId());
          collection.add(geometry.getPointAttributeId());
        }
      }

    }
    finally
    {
      iterator.close();
    }

    return collection;
  }

  private boolean isValid(MdAttributeDAOIF mdAttribute, Collection<String> ids)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();

    if (mdAttributeConcrete instanceof MdAttributeReferenceDAOIF)
    {
      MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttributeConcrete;

      MdBusinessDAOIF referenceMdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

      if (referenceMdBusiness != null)
      {
        boolean isClassifier = referenceMdBusiness.definesType().equals(Term.CLASS);

        return isClassifier;
      }

      return false;
    }

    return !mdAttributeConcrete.isSystem() && !mdAttributeConcrete.definesAttribute().equals(BusinessInfo.KEY) && ( mdAttributeConcrete instanceof MdAttributePrimitiveDAOIF ) && !ids.contains(mdAttributeConcrete.getId());
  }

  private boolean isSelected(MdAttributeDAOIF mdAttribute, List<? extends AttributeWrapper> attributes)
  {
    for (AttributeWrapper attribute : attributes)
    {
      if (mdAttribute.getId().equals(attribute.getWrappedMdAttributeId()))
      {
        return true;
      }
    }

    return false;
  }

  public static Collection<String> getLayersToDelete(Dashboard dashboard, JSONArray types)
  {
    try
    {
      Collection<String> layerNames = new HashSet<String>();

      for (int i = 0; i < types.length(); i++)
      {
        JSONObject type = types.getJSONObject(i);
        String id = type.getString("id");
        boolean checked = type.getBoolean("value");

        MappableClass mClass = MappableClass.get(id);
        MdClass mdClass = mClass.getWrappedMdClass();

        MetadataWrapperQuery query = new MetadataWrapperQuery(new QueryFactory());
        query.WHERE(query.getDashboard().EQ(dashboard));
        query.AND(query.getWrappedMdClass().EQ(mdClass));

        if (checked && query.getCount() != 0)
        {
          OIterator<? extends MetadataWrapper> iterator = query.getIterator();

          try
          {
            while (iterator.hasNext())
            {
              MetadataWrapper wrapper = iterator.next();

              JSONArray attributes = type.getJSONArray("attributes");

              layerNames.addAll(MappableClass.getLayersToDelete(dashboard, wrapper, attributes));
            }
          }
          finally
          {
            iterator.close();
          }
        }
        else if (!checked && query.getCount() > 0)
        {
          OIterator<? extends MetadataWrapper> iterator = query.getIterator();

          try
          {
            while (iterator.hasNext())
            {
              MetadataWrapper wrapper = iterator.next();
              layerNames.addAll(wrapper.getLayersToDelete());
            }
          }
          finally
          {
            iterator.close();
          }
        }
      }

      return layerNames;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private static Collection<String> getLayersToDelete(Dashboard dashboard, MetadataWrapper wrapper, JSONArray attributes)
  {
    Collection<String> layerNames = new HashSet<String>();

    try
    {
      for (int i = 0; i < attributes.length(); i++)
      {
        JSONObject type = attributes.getJSONObject(i);
        String id = type.getString("id");
        boolean checked = type.getBoolean("selected");

        MdAttribute mdAttribute = MdAttribute.get(id);

        QueryFactory factory = new QueryFactory();

        MetadataWrapperQuery wQuery = new MetadataWrapperQuery(factory);
        wQuery.WHERE(wQuery.getId().EQ(wrapper.getId()));

        AttributeWrapperQuery query = new AttributeWrapperQuery(factory);
        query.WHERE(query.dashboardMetadata(wQuery));
        query.AND(query.getWrappedMdAttribute().EQ(mdAttribute));

        if (!checked && query.getCount() > 0)
        {
          OIterator<? extends AttributeWrapper> iterator = query.getIterator();

          try
          {
            while (iterator.hasNext())
            {
              AttributeWrapper attribute = iterator.next();
              layerNames.addAll(attribute.getLayersToDelete(dashboard));
            }
          }
          finally
          {
            iterator.close();
          }
        }
      }

      return layerNames;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public String getAsJSON()
  {
    JSONObject dataset = null;
    try
    {
      dataset = this.toJSON();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    return dataset.toString();
  }

  public static String getAllAsJSON()
  {
    try
    {
      JSONArray array = new JSONArray();

      MappableClass[] mClasses = MappableClass.getAll();

      for (MappableClass mClass : mClasses)
      {
        array.put(mClass.toJSON());
      }

      JSONObject response = new JSONObject();
      response.put("datasets", array);

      return response.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Transaction
  @Authenticate
  public static void applyDatasetUpdate(String dataset)
  {
    try
    {
      JSONObject object = new JSONObject(dataset);
      String label = object.getString("label");
      String id = object.getString("id");
      String description = object.getString("description");
      String source = object.getString("source");

      MappableClass ds = MappableClass.get(id);
      ds.lock();
      ds.setDataSource(source);
      ds.apply();

      MdClass mdClass = ds.getWrappedMdClass();
      mdClass.lock();
      mdClass.getDisplayLabel().setValue(label);
      mdClass.getDescription().setValue(description);
      mdClass.apply();

      if (object.has("attributes"))
      {
        JSONArray attributes = object.getJSONArray("attributes");

        for (int i = 0; i < attributes.length(); i++)
        {
          JSONObject attribute = attributes.getJSONObject(i);
          String attributeId = attribute.getString("id");
          String attributeLabel = attribute.getString("label");

          MdAttributeConcrete mdAttribute = MdAttributeConcrete.lock(attributeId);
          mdAttribute.getDisplayLabel().setValue(attributeLabel);
          mdAttribute.apply();
        }
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Transaction
  @Authenticate
  public static void remove(String id)
  {
    MappableClass mClass = MappableClass.get(id);
    mClass.delete();
  }

  public GeoHierarchy getCountry()
  {
    QueryFactory factory = new QueryFactory();

    ClassUniversalQuery cuQuery = new ClassUniversalQuery(factory);
    cuQuery.WHERE(cuQuery.getParent().EQ(this));

    GeoHierarchyQuery uQuery = new GeoHierarchyQuery(factory);
    uQuery.AND(uQuery.EQ(cuQuery.getChild()));

    OIterator<? extends GeoHierarchy> iterator = null;

    try
    {
      iterator = uQuery.getIterator();

      if (iterator.hasNext())
      {
        GeoHierarchy universal = iterator.next();
        List<GeoHierarchy> parents = universal.getAllParents();

        return parents.get(parents.size() - 1);
      }
    }
    finally
    {
      if (iterator != null)
      {
        iterator.close();
      }
    }

    return null;
  }
}