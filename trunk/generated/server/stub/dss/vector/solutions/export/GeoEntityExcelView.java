package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.excel.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.UnknownTermProblem;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;

public class GeoEntityExcelView extends GeoEntityExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1247004716597L;
  
  public static final String LARGE_GEO_DATA = "+++";
  public static final String NOT_EXPORTED = "###";
  
  private String parentGeoEntityId;
  
  public GeoEntityExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoEntity entity;
    String newGeoId = this.getGeoId();
    String pid = inferParentId();
    boolean newLocatedIn;
    
    String entityType = buildQualifiedType(this.getGeoType());
    if (entityType == null)
    {
      throw new DataNotFoundException("Invalid value: " + this.getGeoType(), MdTypeDAO.getMdTypeDAO(GeoHierarchy.CLASS));
    }
    
    GeoEntityQuery q = new GeoEntityQuery(new QueryFactory());
    q.WHERE(q.getGeoId().EQ(newGeoId));
    OIterator<? extends GeoEntity> i = q.getIterator();
    if (i.hasNext())
    {
      entity = i.next();
      entity.lock();
      
      // Per Miguel's instructions, importing removes all prior located_in relationships, semantically "moving" the entity 
      for (LocatedIn l : entity.getAllLocatedInGeoEntityRel())
      {
        // Don't bother to delete the node we want to end up under
        if (!l.getParentId().equals(pid))
        {
          l.delete();
        }
      }
    }
    else
    {
      entity = (GeoEntity) BusinessFacade.newBusiness(entityType);
    }
    i.close();
    
    dss.vector.solutions.geo.AllPathsQuery query = new dss.vector.solutions.geo.AllPathsQuery(new QueryFactory());
    query.WHERE(query.getChildGeoEntity().getGeoId().EQ(newGeoId));
    query.WHERE(query.getParentGeoEntity().getId().EQ(pid));
    OIterator<? extends AllPaths> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      newLocatedIn = false;
    }
    else
    {
      newLocatedIn = true;
    }
    
    entity.setEntityName(this.getEntityName());
    entity.setActivated(this.getActivated() != null && this.getActivated());
    entity.setGeoId(newGeoId);
    entity.setTerm(this.validateGeoSubtypeByDisplayLabel(entity, this.getSubType(), GeoEntity.getTermMd()));
    
    String importData = this.getGeometryWKT();
    if (!importData.equals(LARGE_GEO_DATA) && !importData.equals(NOT_EXPORTED))
    {
      entity.setGeoData(importData);
    }
    
    if (newLocatedIn)
    {
      entity.applyWithParent(pid, false, null);
    }
    else
    {
      entity.apply();
    }
  }

  private String buildQualifiedType(String input)
  {
    int lastDot = input.lastIndexOf('.');
    
    String geoPackage = null;
    String geoType = null;
    if (lastDot > 0) {
    	geoPackage = input.substring(0, lastDot);
    	geoType = input.substring(lastDot+1);
    } else {
        geoPackage = "dss.vector.solutions.geo.generated";
        geoType = input;
    }
    
    MdBusinessQuery query = new MdBusinessQuery(new QueryFactory());
    query.WHERE(query.getPackageName().EQ(geoPackage));
    query.AND(OR.get(query.getTypeName().EQi(geoType), query.getDisplayLabel().localize().EQi(geoType)));
    OIterator<? extends MdBusiness> iterator = query.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next().definesType();
      }
      return null;
    }
    finally
    {
      iterator.close();
    }
  }
  
  @Override
  public String getGeoId()
  {
    String superId = super.getGeoId();
    return superId; //.split("\\.")[0];
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(new GeoParentListener(params[0]));
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(ENTITYNAME);
    list.add(GEOID);
    list.add(GEOTYPE);
    list.add(SUBTYPE);
    list.add(PARENTNAME);
    list.add(PARENTTYPE);
    list.add(ACTIVATED);
    list.add(GEOMETRYWKT);
    return list;
  }

  public String inferParentId()
  {
    String pName = this.getParentName();
    String pType = this.getParentType();
    if (pName.length()==0)
    {
      return parentGeoEntityId;
    }
    
    GeoEntityQuery query = new GeoEntityQuery(new QueryFactory());
    query.WHERE(OR.get(query.getEntityName().EQ(pName), query.getGeoId().EQ(pName)));
    
    if (pType.length()>0)
    {
      query.WHERE(query.getType().EQ(buildQualifiedType(pType)));
    }
    
    OIterator<? extends GeoEntity> iterator = query.getIterator();
    if (!iterator.hasNext())
    {
      UnknownGeoParentWarning w = new UnknownGeoParentWarning();
      w.setChildName(this.getEntityName());
      w.setParentName(pName);
      w.setParentType(pType.length()>0 ? pType : " ");
      w.setDefaultParent(GeoEntity.get(parentGeoEntityId).getEntityName());
      w.throwIt();
      return parentGeoEntityId;
    }
    
    String queriedId = iterator.next().getId();
    
    if (iterator.hasNext())
    {
      iterator.close();
      String msg = "Geo Entity name [" + pName + "] is ambiguous (It has more than one possible solution)";
      AmbigiousGeoEntityException ex = new AmbigiousGeoEntityException(msg);
      ex.setEntityName(pName);
      throw ex;
    }
    
    iterator.close();
    return queriedId;
  }
  
  public String getParentGeoEntityId()
  {
    return parentGeoEntityId;
  }

  public void setParentGeoEntityId(String parentGeoEntityId)
  {
    this.parentGeoEntityId = parentGeoEntityId;
  }
  
  @Transaction
  public Term validateGeoSubtypeByDisplayLabel(GeoEntity entity, String displayLabel, MdAttributeDAOIF mdAttribute)
  {
    // No value means they didn't specify one.  Don't throw a problem; just return null
    if (displayLabel.trim().length()==0)
    {
      return null;
    }

    QueryFactory factory = new QueryFactory();
    
    String type = entity.getType();
    GeoHierarchy universal = GeoHierarchy.getGeoHierarchyFromType(type);
    Term universalMoRoot = universal.getTerm();

    AllPathsQuery apq = new AllPathsQuery(factory);
    apq.WHERE(apq.getParentTerm().EQ(universalMoRoot));

    TermQuery tq = new TermQuery(factory);
    tq.WHERE(tq.getName().EQi(displayLabel));
    tq.WHERE(tq.getId().EQ(apq.getChildTerm().getId()));

    OIterator<? extends Term> iterator = tq.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      String attributeLabel = mdAttribute.getDisplayLabel(Session.getCurrentLocale());
      String msg = "Unknown " + attributeLabel + " with the given name [" + displayLabel + "]";

      UnknownTermProblem e = new UnknownTermProblem(msg);
      e.setTermName(displayLabel);
      e.setAttributeLabel(attributeLabel);
      e.throwIt();

      // We expect to return nothing, as we're throwing a problem, but include this to satisfy the compile time requirement
      return null;
    }
    finally
    {
      iterator.close();
    }
  }
}
