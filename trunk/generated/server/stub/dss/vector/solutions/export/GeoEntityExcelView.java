package dss.vector.solutions.export;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdBusinessQuery;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;

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
    GeoEntity entity = (GeoEntity) BusinessFacade.newBusiness(entityType);
    entity.setEntityName(this.getEntityName());
    entity.setActivated(this.getActivated() != null && this.getActivated());
    entity.setGazId(this.getGazId());
    entity.setGeoId(this.getGeoId());
    
    MdAttributeGeometry geometry = GeoHierarchy.getGeometry(entityType);
    entity.setValue(geometry.getAttributeName(), this.getGeometryWKT());
    entity.applyWithParent(parentGeoEntityId, false);
  }

  private String getEntityType()
  {
    MdBusinessQuery query = new MdBusinessQuery(new QueryFactory());
    query.WHERE(query.getDisplayLabel().currentLocale().EQ(this.getGeoType()));
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
}
