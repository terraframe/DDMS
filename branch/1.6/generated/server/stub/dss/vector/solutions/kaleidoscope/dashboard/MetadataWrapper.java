package dss.vector.solutions.kaleidoscope.dashboard;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.query.F;
import com.runwaysdk.query.MAX;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdClassQuery;

import dss.vector.solutions.kaleidoscope.MappableClassGeoNodeQuery;
import dss.vector.solutions.kaleidoscope.MappableClassQuery;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeQuery;

public class MetadataWrapper extends MetadataWrapperBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1121470685;

  public MetadataWrapper()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    Dashboard dashboard = this.getDashboard();
    MdClass wrappedMdClass = this.getWrappedMdClass();

    if (dashboard != null && wrappedMdClass != null)
    {
      return dashboard.getName() + "-" + wrappedMdClass.getKey();
    }

    return super.buildKey();
  }

  @Override
  public void delete()
  {
    Dashboard dashboard = this.getDashboard();

    for (AttributeWrapper aw : this.getAllAttributeWrapper())
    {
      aw.delete(dashboard);
    }

    super.delete();

    // TODO Fixme
    // Delete any reference layers which are no longer valid because the dataset country is being deleted
//    QueryFactory factory = new QueryFactory();
//
//    MetadataWrapperQuery mwQuery = new MetadataWrapperQuery(factory);
//    mwQuery.WHERE(mwQuery.getDashboard().EQ(dashboard));
//    mwQuery.AND(mwQuery.getId().NE(this.getId()));
//
//    MappableClassQuery mcQuery = new MappableClassQuery(factory);
//    mcQuery.WHERE(mcQuery.getWrappedMdClass().EQ(mwQuery.getWrappedMdClass()));
//
//    ClassUniversalQuery cuQuery = new ClassUniversalQuery(factory);
//    cuQuery.WHERE(cuQuery.getParent().EQ(mcQuery));
//
//    AllowedInQuery aiQuery = new AllowedInQuery(factory);
//    aiQuery.WHERE(aiQuery.getParent().EQ(Universal.getRoot()));
//
//    UniversalAllPathsTableQuery uAptQuery = new UniversalAllPathsTableQuery(factory);
//    uAptQuery.WHERE(uAptQuery.getParentTerm().EQ(aiQuery.getChild()));
//    uAptQuery.AND(uAptQuery.getChildTerm().EQ(cuQuery.getChild()));
//
//    UniversalAllPathsTableQuery aptQuery = new UniversalAllPathsTableQuery(factory);
//    aptQuery.WHERE(aptQuery.getParentTerm().EQ(uAptQuery.getParentTerm()));
//
//    DashboardReferenceLayerQuery query = new DashboardReferenceLayerQuery(factory);
//    query.WHERE(query.getUniversal().NI(arg0)(aptQuery.getChildTerm()));
//    query.AND(query.getDashboardMap().EQ(dashboard.getMap()));
//
//    OIterator<? extends DashboardReferenceLayer> it = query.getIterator();
//
//    try
//    {
//      while (it.hasNext())
//      {
//        DashboardReferenceLayer layer = it.next();
//        layer.delete();
//      }
//    }
//    finally
//    {
//      it.close();
//    }
  }

  public MdAttributeView[] getSortedAttributes()
  {
    String mdId = this.getWrappedMdClassId();

    List<MdAttributeView> mdAttr = new LinkedList<MdAttributeView>();
    Locale locale = Session.getCurrentLocale();

    QueryFactory f = new QueryFactory();

    DashboardAttributesQuery daQ = new DashboardAttributesQuery(f);

    daQ.WHERE(daQ.parentId().EQ(this.getId()));
    daQ.ORDER_BY_ASC(daQ.getListOrder());

    OIterator<? extends DashboardAttributes> iter = daQ.getIterator();
    try
    {
      while (iter.hasNext())
      {
        DashboardAttributes attribute = iter.next();
        AttributeWrapper aWrapper = attribute.getChild();

        MdAttributeDAOIF attr = MdAttributeDAO.get(aWrapper.getWrappedMdAttributeId());
        MdAttributeConcreteDAOIF mdAttributeConcrete = attr.getMdAttributeConcrete();

        String attrId = attr.getId();
        String attrType = mdAttributeConcrete.getType();

        String label = attr.getDisplayLabel(locale);

        MdAttributeView view = new MdAttributeView();
        view.setMdClassId(mdId);
        view.setMdAttributeId(attrId);
        view.setDisplayLabel(label);
        view.setAttributeName(attr.definesAttribute());
        view.setAttributeType(attrType);

        mdAttr.add(view);
      }
    }
    finally
    {
      iter.close();
    }

    return mdAttr.toArray(new MdAttributeView[mdAttr.size()]);
  }

  public static List<MdClass> getMdClassesWithGeoNodes()
  {
    // Returns list of all types defined with GeoNodes
    QueryFactory factory = new QueryFactory();

    MappableClassGeoNodeQuery mgQuery = new MappableClassGeoNodeQuery(factory);

    MappableClassQuery mcQuery = new MappableClassQuery(factory);
    mcQuery.WHERE(mcQuery.EQ(mgQuery.getParent()));

    MdClassQuery query = new MdClassQuery(factory);
    query.WHERE(query.EQ(mcQuery.getWrappedMdClass()));

    OIterator<? extends MdClass> iterator = query.getIterator();

    try
    {
      List<? extends MdClass> results = iterator.getAll();

      return new LinkedList<MdClass>(results);
    }
    finally
    {
      iterator.close();
    }

  }

  public static List<GeoNode> getGeoNodes(String type)
  {
    QueryFactory factory = new QueryFactory();

    MappableClassQuery mwQuery = new MappableClassQuery(factory);
    mwQuery.AND(mwQuery.getWrappedMdClass().EQ(MdClassDAO.getMdClassDAO(type)));

    MappableClassGeoNodeQuery mgQuery = new MappableClassGeoNodeQuery(factory);
    mgQuery.WHERE(mgQuery.getParent().EQ(mwQuery));

    GeoNodeQuery gnQuery = new GeoNodeQuery(factory);
    gnQuery.WHERE(gnQuery.EQ(mgQuery.getChild()));

    OIterator<? extends GeoNode> iterator = gnQuery.getIterator();

    try
    {
      List<? extends GeoNode> nodes = iterator.getAll();

      return new LinkedList<GeoNode>(nodes);
    }
    finally
    {
      iterator.close();
    }
  }

  public MetadataWrapper clone(Dashboard dashboard)
  {
    MetadataWrapper clone = new MetadataWrapper();
    clone.setDashboard(dashboard);
    clone.setWrappedMdClass(this.getWrappedMdClass());
    clone.apply();

    OIterator<? extends DashboardAttributes> attributes = this.getAllAttributeWrapperRel();

    for (DashboardAttributes attribute : attributes)
    {
      AttributeWrapper child = attribute.getChild();
      child.clone(clone, attribute.getListOrder());
    }

    return clone;
  }

  public int getMaxOrder()
  {
    ValueQuery vQuery = new ValueQuery(new QueryFactory());

    DashboardAttributesQuery query = new DashboardAttributesQuery(vQuery);

    vQuery.WHERE(query.getParent().EQ(this));

    MAX selectable = F.MAX(query.getListOrder());
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

  public static MetadataWrapper getByWrappedMdClassId(Dashboard dashboard, String classId)
  {
    MetadataWrapperQuery query = new MetadataWrapperQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(dashboard));
    query.AND(query.getWrappedMdClass().EQ(classId));

    OIterator<? extends MetadataWrapper> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        MetadataWrapper wrapper = it.next();

        return wrapper;
      }
    }
    finally
    {
      it.close();
    }

    return null;
  }

  public Collection<String> getLayersToDelete()
  {
    Collection<String> layerNames = new HashSet<String>();

    Dashboard dashboard = this.getDashboard();

    for (AttributeWrapper aw : this.getAllAttributeWrapper())
    {
      layerNames.addAll(aw.getLayersToDelete(dashboard));
    }

    // TODO Fixme
    // Check if any existing reference layers will be deleted because the dataset country is no longer being included
//    QueryFactory factory = new QueryFactory();
//
//    MetadataWrapperQuery mwQuery = new MetadataWrapperQuery(factory);
//    mwQuery.WHERE(mwQuery.getDashboard().EQ(dashboard));
//    mwQuery.AND(mwQuery.getId().NE(this.getId()));
//
//    MappableClassQuery mcQuery = new MappableClassQuery(factory);
//    mcQuery.WHERE(mcQuery.getWrappedMdClass().EQ(mwQuery.getWrappedMdClass()));
//
//    ClassUniversalQuery cuQuery = new ClassUniversalQuery(factory);
//    cuQuery.WHERE(cuQuery.getParent().EQ(mcQuery));
//
//    AllowedInQuery aiQuery = new AllowedInQuery(factory);
//    aiQuery.WHERE(aiQuery.getParent().EQ(Universal.getRoot()));
//
//    UniversalAllPathsTableQuery uAptQuery = new UniversalAllPathsTableQuery(factory);
//    uAptQuery.WHERE(uAptQuery.getParentTerm().EQ(aiQuery.getChild()));
//    uAptQuery.AND(uAptQuery.getChildTerm().EQ(cuQuery.getChild()));
//
//    UniversalAllPathsTableQuery aptQuery = new UniversalAllPathsTableQuery(factory);
//    aptQuery.WHERE(aptQuery.getParentTerm().EQ(uAptQuery.getParentTerm()));
//
//    DashboardReferenceLayerQuery query = new DashboardReferenceLayerQuery(factory);
//    query.WHERE(query.getUniversal().SUBSELECT_NOT_IN(aptQuery.getChildTerm()));
//    query.AND(query.getDashboardMap().EQ(dashboard.getMap()));
//
//    OIterator<? extends DashboardReferenceLayer> it = query.getIterator();
//
//    try
//    {
//      while (it.hasNext())
//      {
//        DashboardReferenceLayer layer = it.next();
//
//        layerNames.add(layer.getName());
//      }
//    }
//    finally
//    {
//      it.close();
//    }

    return layerNames;
  }
}
