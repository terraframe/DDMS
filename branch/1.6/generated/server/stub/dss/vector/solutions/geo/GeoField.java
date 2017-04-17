package dss.vector.solutions.geo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdWebGeo;

import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoField extends GeoFieldBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -84935008;

  public GeoField()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getGeoAttribute() != null)
    {
      return this.getGeoAttribute().getKey();
    }

    return super.buildKey();
  }

  @Override
  public String getFilterType()
  {
    GeoHierarchy filter = this.getFilter();

    if (filter != null)
    {
      GeoHierarchyView view = filter.getViewForGeoHierarchy();

      return view.getGeneratedType();
    }

    return null;
  }

  @Override
  public String[] getExtraUniversals()
  {
    Set<String> universals = new TreeSet<String>();
    OIterator<? extends GeoHierarchy> iterator = this.getAllGeoHierarchies();

    try
    {
      while (iterator.hasNext())
      {
        GeoHierarchy hierarchy = iterator.next();
        GeoHierarchyView view = hierarchy.getViewForGeoHierarchy();

        universals.add(view.getGeneratedType());
      }

      return universals.toArray(new String[universals.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  /**
   * This method returns the GeoField for the given MdWebGeo. It is expected that a valid MdWebGeo
   * id is passed in, otherwise this method will error out.
   * 
   * @param mdFieldId
   * @return
   */
  public static GeoField getGeoFieldForMdWebGeo(String mdFieldId)
  {
    GeoFieldQuery q = new GeoFieldQuery(new QueryFactory());

    MdWebGeo mdGeo = MdWebGeo.get(mdFieldId);
    q.WHERE(q.getGeoAttribute().EQ(mdGeo.getDefiningMdAttributeId()));
    return q.getIterator().getAll().get(0);
  }

  public static GeoField getGeoField(String klass, String name)
  {
    GeoFieldQuery query = new GeoFieldQuery(new QueryFactory());

    query.WHERE(query.getGeoAttribute().getDefiningMdClass().getKeyName().EQ(klass));
    query.AND(query.getGeoAttribute().getAttributeName().EQ(name));

    OIterator<? extends GeoField> it = query.getIterator();

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

  public List<GeoHierarchy> getUniversals()
  {
    List<GeoHierarchy> list = new LinkedList<GeoHierarchy>();

    if (this.getFilter() != null)
    {
      list.add(this.getFilter());
    }
    else
    {
      SearchParameter searchParameter = this.getSearchParameter();

      GeoHierarchyView[] views = GeoHierarchy.getHierarchies(searchParameter);

      for (GeoHierarchyView view : views)
      {
        list.add(GeoHierarchy.get(view.getGeoHierarchyId()));
      }

      OIterator<? extends GeoHierarchy> it = this.getAllGeoHierarchies();

      try
      {
        while (it.hasNext())
        {
          list.add(it.next());
        }
      }
      finally
      {
        it.close();
      }
    }

    return list;
  }

  private SearchParameter getSearchParameter()
  {
    return new SearchParameter(this.getIsPoliticalHierarchy(), this.getIsSprayHierarchy(), this.getIsPopulationHierarchy(), this.getIsUrbanHierarchy(), false, false);
  }

  public static GeoField getGeoField(MdAttribute definingAttr)
  {
    GeoFieldQuery query = new GeoFieldQuery(new QueryFactory());

    query.WHERE(query.getGeoAttribute().EQ(definingAttr));

    OIterator<? extends GeoField> it = query.getIterator();

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

  public static GeoHierarchyView[] getFieldUniversals()
  {
    GeoHierarchyView[] views = GeoHierarchy.getAllViews();

    List<GeoHierarchyView> list = new LinkedList<GeoHierarchyView>(Arrays.asList(views));

    Iterator<GeoHierarchyView> it = list.iterator();

    while (it.hasNext())
    {
      GeoHierarchyView view = it.next();

      if (view.getGeneratedType().equals(Earth.CLASS) || view.getGeneratedType().equals(GeoEntity.CLASS))
      {
        it.remove();
      }
    }

    return list.toArray(new GeoHierarchyView[list.size()]);
  }
}
