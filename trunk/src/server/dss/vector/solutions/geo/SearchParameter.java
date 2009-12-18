package dss.vector.solutions.geo;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdClass;

import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class SearchParameter implements Reloadable
{
  /**
   * Flag denoting if the search restrict by political hierarchies
   */
  private boolean political;

  /**
   * Flag denoting if the search should restrict by spray hierarchies
   */
  private boolean spray;

  /**
   * Flag denoting if the search restribute by populated hierarchies
   */
  private boolean populated;

  /**
   * Flag denoting if the search should get the ancestors or decendants
   */
  private boolean ancestor;

  /**
   * Flag denoting if the search should only use the first applicable hierarchy
   */
  private boolean first;

  public SearchParameter()
  {
    this(false, false, false, false, false);
  }

  public SearchParameter(boolean political, boolean spray, boolean populated, boolean ancestor, boolean first)
  {
    this.political = political;
    this.spray = spray;
    this.populated = populated;
    this.ancestor = ancestor;
    this.first = first;
  }

  public boolean isPolitical()
  {
    return political;
  }

  public void setPolitical(boolean political)
  {
    this.political = political;
  }

  public boolean isSpray()
  {
    return spray;
  }

  public void setSpray(boolean spray)
  {
    this.spray = spray;
  }

  public boolean isPopulated()
  {
    return populated;
  }

  public void setPopulated(boolean populated)
  {
    this.populated = populated;
  }

  public boolean isAncestor()
  {
    return ancestor;
  }

  public void setAncestor(boolean ancestor)
  {
    this.ancestor = ancestor;
  }

  public boolean isFirst()
  {
    return first;
  }

  public void setFirst(boolean first)
  {
    this.first = first;
  }

  public List<GeoHierarchy> getHierarchies(GeoHierarchy hierarchy)
  {
    if (this.isAncestor())
    {
      return hierarchy.getImmediateParents();
    }

    return hierarchy.getImmediateChildren();
  }

  /**
   * Validates that the given GeoHierarchy meets the SearchParameter criteria
   * only on hierarchy flags (Political, Spray Targets) and any modifier flags (Populated).
   * 
   * @param hierarchy
   * @return
   */
  public boolean validateFlagsAndModifiers(GeoHierarchy hierarchy)
  {    
    if (this.isPopulated() && !hierarchy.getPopulationAllowed())
    {
      return false;
    }
    
    return this.validateFlags(hierarchy);
  }
  
  /**
   * Validates that the given GeoHierarchy meets the SearchParameter criteria
   * only on hierarchy flags (Political, Spray Targets).  However, it does not
   * check any modifier flags (Populated).
   * 
   * @param hierarchy
   * @return
   */
  public boolean validateFlags(GeoHierarchy hierarchy)
  {
    if (this.isPolitical() && !hierarchy.getPolitical())
    {
      return false;
    }

    if (this.isSpray() && !hierarchy.getSprayTargetAllowed())
    {
      return false;
    }
    
    return true;
  }

  public AllPathsQuery getPathsQuery(QueryFactory factory, GeoEntity entity)
  {
    AllPathsQuery query = new AllPathsQuery(factory);
    
    Condition[] array = this.getUniversalConditions(entity, query);

    if (this.isAncestor())
    {
      query.WHERE(AND.get(query.getChildGeoEntity().EQ(entity), OR.get(array)));
    }
    else
    {
      query.WHERE(AND.get(query.getParentGeoEntity().EQ(entity), OR.get(array)));
    }

    return query;
  }

  private Condition[] getUniversalConditions(GeoEntity entity, AllPathsQuery query)
  {
    List<Condition> conditions = new LinkedList<Condition>();

    GeoHierarchyView[] views = GeoHierarchy.getHierarchies(entity, this);
    
    for (GeoHierarchyView view : views)
    {
      MdClass mdClass = MdClass.getMdClass(view.getGeneratedType());

      if (this.isAncestor())
      {
        conditions.add(query.getParentUniversal().EQ(mdClass));
      }
      else
      {
        conditions.add(query.getChildUniversal().EQ(mdClass));
      }

    }
    
    // No hierarchy values are valid so just default to earth
    if(conditions.size() == 0)
    {
      MdClass mdClass = MdClass.getMdClass(Earth.CLASS);

      if (this.isAncestor())
      {
        conditions.add(query.getParentUniversal().EQ(mdClass));
      }
      else
      {
        conditions.add(query.getChildUniversal().EQ(mdClass));
      }      
    }
    
    return conditions.toArray(new Condition[conditions.size()]);
  }

  public GeoEntityQuery getGeoEntityQuery(QueryFactory factory, GeoEntity geoEntity)
  {
    GeoEntityQuery query = new GeoEntityQuery(factory);
    AllPathsQuery pathsQuery = this.getPathsQuery(factory, geoEntity);

    if (this.isAncestor())
    {
      query.WHERE(query.getId().EQ(pathsQuery.getParentGeoEntity().getId()));
    }
    else
    {
      query.WHERE(query.getId().EQ(pathsQuery.getChildGeoEntity().getId()));
    }

    return query;
  }
}
