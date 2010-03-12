package dss.vector.solutions.export;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdBusinessQuery;

import dss.vector.solutions.UnknownTermProblem;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;

public class GeoEntityExcelView extends GeoEntityExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    String entityType = getEntityType();
    if (entityType == null) {
    	throw new DataNotFoundException("Invalid value: " + this.getGeoType(), MdTypeDAO.getMdTypeDAO(GeoHierarchy.CLASS));
    }
    GeoEntity entity = (GeoEntity) BusinessFacade.newBusiness(entityType);
    entity.setEntityName(this.getEntityName());
    entity.setActivated(this.getActivated() != null && this.getActivated());
    entity.setGeoId(this.getGeoId());
    entity.setTerm(this.validateGeoSubtypeByDisplayLabel(entity, this.getSubType(), GeoEntity.getTermMd()));
    
    MdAttributeGeometry geometry = GeoHierarchy.getGeometry(entityType);
    entity.setValue(geometry.getAttributeName(), this.getGeometryWKT());
    entity.applyWithParent(parentGeoEntityId, false, null);
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
  
  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(new GeoParentListener(params[0]));
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
