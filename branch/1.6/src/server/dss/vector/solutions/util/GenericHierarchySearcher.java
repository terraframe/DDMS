package dss.vector.solutions.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GenericHierarchySearcher implements SearchableHierarchy, Reloadable
{
  private List<String> types;
  
  public GenericHierarchySearcher()
  {
    this.types = new LinkedList<String>();
  }
    
  public GenericHierarchySearcher(String... types)
  {
    this.types = Arrays.asList(types);
  }
  
  public GenericHierarchySearcher(List<String> types)
  {
    this.types = types;
  }
  
  public List<GeoEntity> searchGeoEntity(String entityName, GeoEntity parent)
  {
    List<GeoEntity> list = new LinkedList<GeoEntity>();
    List<GeoEntity> children = this.getChildren(parent);

    for (GeoEntity child : children)
    {
      String type = child.getType();
      String name = child.getEntityLabel().getValue();

      if (name.equals(entityName) && ( types.contains(type)))
      {
        list.add(child);
      }
    }

    return list;
  }

  private List<GeoEntity> getChildren(GeoEntity parent)
  {
    if(parent != null)
    {
      return parent.getPrunedChildren(types);
    }
    else
    {
      GeoEntityQuery query = new GeoEntityQuery(new QueryFactory());
      
      for(int i = 0; i < types.size(); i++)
      {
        if(i == 0)
        {
          query.WHERE(query.getType().EQ(types.get(i)));
        }
        else
        {
          query.OR(query.getType().EQ(types.get(i)));
        }
      }
      
      return new LinkedList<GeoEntity>(query.getIterator().getAll());
    }
  }

  public String getDisplayLabel()
  {
    StringBuffer buffer = new StringBuffer();
    
    for(String type : types)
    {
      MdBusiness mdBusiness = MdBusiness.getMdBusiness(type);
      
      buffer.append("/" + mdBusiness.getDisplayLabel().getValue());
    }
    
    return buffer.toString().replaceFirst("/", "");
  }
}
