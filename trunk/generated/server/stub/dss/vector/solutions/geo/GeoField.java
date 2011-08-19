package dss.vector.solutions.geo;

import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class GeoField extends GeoFieldBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -84935008;

  public GeoField()
  {
    super();
  }
  
  @Override
  public String getFilterType()
  {
    GeoHierarchy filter = this.getFilter();
    
    if(filter != null)
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
      while(iterator.hasNext())
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
}
