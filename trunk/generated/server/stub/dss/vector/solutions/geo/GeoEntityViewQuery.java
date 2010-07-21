package dss.vector.solutions.geo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.F;
import com.runwaysdk.query.GeneratedViewQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ViewQueryBuilder;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.TermQuery;

/**
 * 
 * @author Autogenerated by TerraFrame
 */
public class GeoEntityViewQuery extends dss.vector.solutions.geo.GeoEntityViewQueryBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238962018908L;

  private String[]          ids;

  private GeoEntityQuery    geoEntityQuery;

  private MdBusinessQuery   mdBusinessQuery;

  public GeoEntityViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultGeoEntityViewBuilder(queryFactory));
  }

  public GeoEntityViewQuery(com.runwaysdk.query.QueryFactory queryFactory, String[] ids)
  {
    super(queryFactory);
    this.ids = ids;
    this.geoEntityQuery = new GeoEntityQuery(queryFactory);
    this.mdBusinessQuery = new MdBusinessQuery(queryFactory);
    this.buildQuery(new DefaultGeoEntityViewBuilder(queryFactory));
  }

  public GeoEntityViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultGeoEntityViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultGeoEntityViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected GeoEntityViewQuery getViewQuery()
    {
      return (GeoEntityViewQuery) super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.map(GeoEntityView.GEOENTITYID, geoEntityQuery.getId());
      vQuery.map(GeoEntityView.GEOID, geoEntityQuery.getGeoId());
      vQuery.map(GeoEntityView.ACTIVATED, geoEntityQuery.getActivated());
      vQuery.map(GeoEntityView.ENTITYNAME, geoEntityQuery.getEntityName());
      vQuery.map(GeoEntityView.ENTITYTYPE, geoEntityQuery.getType());
      vQuery.map(GeoEntityView.TYPEDISPLAYLABEL, mdBusinessQuery.getDisplayLabel().localize());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.WHERE(F.CONCAT(mdBusinessQuery.getPackageName(), F.CONCAT(".", mdBusinessQuery.getTypeName())).EQ(geoEntityQuery.getType()));

      vQuery.WHERE(geoEntityQuery.getId().IN(ids));
    }
  }

  public static class ImmediateChildrenQueryBuilder extends ViewQueryBuilder implements Reloadable
  {

    private GeoEntity       geoEntity;

    private GeoEntityQuery  geoEntityQuery;

    private LocatedInQuery  locatedInQuery;

    private MdBusinessQuery mdBusinessQuery;

    private TermQuery       termQuery;

    private String          filter;

    public ImmediateChildrenQueryBuilder(QueryFactory queryFactory, GeoEntity geoEntity, String filter)
    {
      super(queryFactory);

      this.filter = filter;
      this.geoEntity = geoEntity;
      this.geoEntityQuery = new GeoEntityQuery(queryFactory);
      this.locatedInQuery = new LocatedInQuery(queryFactory);
      this.mdBusinessQuery = new MdBusinessQuery(queryFactory);
      this.termQuery = new TermQuery(queryFactory);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.map(GeoEntityView.GEOENTITYID, geoEntityQuery.getId());
      vQuery.map(GeoEntityView.GEOID, geoEntityQuery.getGeoId());
      vQuery.map(GeoEntityView.ACTIVATED, geoEntityQuery.getActivated());
      vQuery.map(GeoEntityView.ENTITYNAME, geoEntityQuery.getEntityName());
      vQuery.map(GeoEntityView.ENTITYTYPE, geoEntityQuery.getType());
      vQuery.map(GeoEntityView.TYPEDISPLAYLABEL, mdBusinessQuery.getDisplayLabel().localize());
      vQuery.map(GeoEntityView.MOSUBTYPE, termQuery.getTermDisplayLabel().localize());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();

      vQuery.WHERE(this.locatedInQuery.parentId().EQ(geoEntity.getId()));
      vQuery.AND(this.geoEntityQuery.locatedInGeoEntity(this.locatedInQuery));

      vQuery.AND(F.CONCAT(mdBusinessQuery.getPackageName(), F.CONCAT(".", mdBusinessQuery.getTypeName())).EQ(geoEntityQuery.getType()));

      // filter by type if possible (and all of type's child subclasses)
      if (filter != null && filter.trim().length() > 0)
      {
        vQuery.AND(geoEntityQuery.getType().EQ(filter));
      }

      // Restricted types to avoid returning large data sets
      String[] baseTypes = { MDSSInfo.GENERATED_GEO_PACKAGE + ".WaterBody", MDSSInfo.GENERATED_GEO_PACKAGE + ".Reserve", MDSSInfo.GENERATED_GEO_PACKAGE + ".River", MDSSInfo.GENERATED_GEO_PACKAGE + ".Road", MDSSInfo.GENERATED_GEO_PACKAGE + ".Railway" };

      // Grab all is_a children of the restricted types to add to
      // the restricted list.
      Set<String> notInSet = new HashSet<String>(Arrays.asList(baseTypes));
      for (String baseType : baseTypes)
      {
        try
        {
          MdBusiness baseMd = MdBusiness.getMdBusiness(baseType);
          MdBusinessDAOIF baseDAOIF = (MdBusinessDAOIF) BusinessFacade.getEntityDAO(baseMd);
          for (MdBusinessDAOIF subclass : baseDAOIF.getAllConcreteSubClasses())
          {
            notInSet.add(subclass.definesType());
          }
        }
        catch (DataNotFoundException e)
        {
          // The type doesn't exist for this country. Just ignore it
        }
      }

      vQuery.AND(geoEntityQuery.getType().NI(notInSet.toArray(new String[notInSet.size()])));

      vQuery.AND(geoEntityQuery.getTerm("geoTermId").LEFT_JOIN_EQ(termQuery.getId("termId")));

      vQuery.ORDER_BY_ASC(this.geoEntityQuery.getEntityName());
    }
  }
  
  public static class AllParentsQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    
    private GeoEntity       geoEntity;
    
    private GeoEntityQuery  geoEntityQuery;
    
    private AllPathsQuery  pathsQuery;
    
    private MdBusinessQuery mdBusinessQuery;
    
    private TermQuery       termQuery;
    
    private String          filter;
    
    public AllParentsQueryBuilder(QueryFactory queryFactory, GeoEntity geoEntity, String filter)
    {
      super(queryFactory);
      
      this.filter = filter;
      this.geoEntity = geoEntity;
      this.geoEntityQuery = new GeoEntityQuery(queryFactory);
      this.pathsQuery= new AllPathsQuery(queryFactory);
      this.mdBusinessQuery = new MdBusinessQuery(queryFactory);
      this.termQuery = new TermQuery(queryFactory);
    }
    
    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(GeoEntityView.GEOENTITYID, geoEntityQuery.getId());
      vQuery.map(GeoEntityView.GEOID, geoEntityQuery.getGeoId());
      vQuery.map(GeoEntityView.ACTIVATED, geoEntityQuery.getActivated());
      vQuery.map(GeoEntityView.ENTITYNAME, geoEntityQuery.getEntityName());
      vQuery.map(GeoEntityView.ENTITYTYPE, geoEntityQuery.getType());
      vQuery.map(GeoEntityView.TYPEDISPLAYLABEL, mdBusinessQuery.getDisplayLabel().localize());
      vQuery.map(GeoEntityView.MOSUBTYPE, termQuery.getTermDisplayLabel().localize());
    }
    
    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery vQuery = this.getViewQuery();
      
      vQuery.WHERE(this.pathsQuery.getChildGeoEntity().EQ(geoEntity));
      vQuery.AND(this.geoEntityQuery.getId().EQ(this.pathsQuery.getParentGeoEntity().getId()));
      
      vQuery.AND(F.CONCAT(mdBusinessQuery.getPackageName(), F.CONCAT(".", mdBusinessQuery.getTypeName())).EQ(geoEntityQuery.getType()));
      
      // filter by type if possible (and all of type's child subclasses)
      if (filter != null && filter.trim().length() > 0)
      {
        vQuery.AND(geoEntityQuery.getType().EQ(filter));
      }
      
      // Restricted types to avoid returning large data sets
      String[] baseTypes = { MDSSInfo.GENERATED_GEO_PACKAGE + ".WaterBody", MDSSInfo.GENERATED_GEO_PACKAGE + ".Reserve", MDSSInfo.GENERATED_GEO_PACKAGE + ".River", MDSSInfo.GENERATED_GEO_PACKAGE + ".Road", MDSSInfo.GENERATED_GEO_PACKAGE + ".Railway" };
      
      // Grab all is_a children of the restricted types to add to
      // the restricted list.
      Set<String> notInSet = new HashSet<String>(Arrays.asList(baseTypes));
      for (String baseType : baseTypes)
      {
        try
        {
          MdBusiness baseMd = MdBusiness.getMdBusiness(baseType);
          MdBusinessDAOIF baseDAOIF = (MdBusinessDAOIF) BusinessFacade.getEntityDAO(baseMd);
          for (MdBusinessDAOIF subclass : baseDAOIF.getAllConcreteSubClasses())
          {
            notInSet.add(subclass.definesType());
          }
        }
        catch (DataNotFoundException e)
        {
          // The type doesn't exist for this country. Just ignore it
        }
      }
      
      vQuery.AND(geoEntityQuery.getType().NI(notInSet.toArray(new String[notInSet.size()])));
      
      vQuery.AND(geoEntityQuery.getTerm("geoTermId").LEFT_JOIN_EQ(termQuery.getId("termId")));
      
      vQuery.ORDER_BY_ASC(this.geoEntityQuery.getEntityName());
    }
  }
  
  public static List<GeoEntityView> getImmediateChildren(GeoEntity entity, String filter)
  {
    List<GeoEntityView> list = new LinkedList<GeoEntityView>();
    QueryFactory factory = new QueryFactory();

    GeoEntityViewQuery query = new GeoEntityViewQuery(factory, new GeoEntityViewQuery.ImmediateChildrenQueryBuilder(factory, entity, filter));
    
    OIterator<? extends GeoEntityView> it = query.getIterator();
    
    try
    {
      while(it.hasNext())
      {
        GeoEntityView view = it.next();

        list.add(view);
      }
    }
    finally
    {
      it.close();
    }
    
    return list;
  }

  public static List<GeoEntityView> getAllParents(GeoEntity entity, String filter)
  {
    List<GeoEntityView> list = new LinkedList<GeoEntityView>();
    QueryFactory factory = new QueryFactory();
    
    GeoEntityViewQuery query = new GeoEntityViewQuery(factory, new GeoEntityViewQuery.AllParentsQueryBuilder(factory, entity, filter));
    
    OIterator<? extends GeoEntityView> it = query.getIterator();
    
    try
    {
      while(it.hasNext())
      {
        GeoEntityView view = it.next();
        
        list.add(view);
      }
    }
    finally
    {
      it.close();
    }
    
    return list;
  }
  
}
