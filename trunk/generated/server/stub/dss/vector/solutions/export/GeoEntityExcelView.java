package dss.vector.solutions.export;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.gis.metadata.MdAttributeGeometry;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;

import dss.vector.solutions.UnknownTermProblem;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;

public class GeoEntityExcelView extends GeoEntityExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1247004716597L;
  
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
    boolean update;
    
    String entityType = getEntityType();
    if (entityType == null)
    {
      throw new DataNotFoundException("Invalid value: " + this.getGeoType(), MdTypeDAO.getMdTypeDAO(GeoHierarchy.CLASS));
    }
    
    dss.vector.solutions.geo.AllPathsQuery query = new dss.vector.solutions.geo.AllPathsQuery(new QueryFactory());
    query.WHERE(query.getChildGeoEntity().getGeoId().EQ(newGeoId));
    query.WHERE(query.getParentGeoEntity().getId().EQ(parentGeoEntityId));
    OIterator<? extends AllPaths> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      entity = iterator.next().getChildGeoEntity();
      entity.lock();
      update = true;
    }
    else
    {
      entity = (GeoEntity) BusinessFacade.newBusiness(entityType);
      update = false;
    }
    
    entity.setEntityName(this.getEntityName());
    entity.setActivated(this.getActivated() != null && this.getActivated());
    entity.setGeoId(newGeoId);
    entity.setTerm(this.validateGeoSubtypeByDisplayLabel(entity, this.getSubType(), GeoEntity.getTermMd()));
    
    MdAttributeGeometry geometry = GeoHierarchy.getGeometry(entityType);
    entity.setGeoData(this.getGeometryWKT());
    
    if (update)
    {
      entity.apply();
    }
    else
    {
      entity.applyWithParent(parentGeoEntityId, false, null);
    }
  }

  private String getEntityType()
  {
    String fullGeoType = this.getGeoType();
    int lastDot = fullGeoType.lastIndexOf('.');
    
    String geoPackage = null;
    String geoType = null;
    if (lastDot > 0) {
    	geoPackage = fullGeoType.substring(0, lastDot);
    	geoType = fullGeoType.substring(lastDot+1);
    } else {
        geoPackage = "dss.vector.solutions.geo.generated";
        geoType = fullGeoType;
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
