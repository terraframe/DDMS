package csu.mrc.ivcc.mdss;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;

import csu.mrc.ivcc.mdss.geo.GeoEntityDefinition;
import csu.mrc.ivcc.mdss.geo.GeoHierarchy;
import csu.mrc.ivcc.mdss.geo.GeoHierarchyView;
import csu.mrc.ivcc.mdss.geo.SpatialTypes;
import csu.mrc.ivcc.mdss.geo.generated.AbstractSiteIF;
import csu.mrc.ivcc.mdss.geo.generated.AdminPostIF;
import csu.mrc.ivcc.mdss.geo.generated.BreedingSiteIF;
import csu.mrc.ivcc.mdss.geo.generated.CountryIF;
import csu.mrc.ivcc.mdss.geo.generated.DistrictIF;
import csu.mrc.ivcc.mdss.geo.generated.LocalityIF;
import csu.mrc.ivcc.mdss.geo.generated.NonSentinalSiteIF;
import csu.mrc.ivcc.mdss.geo.generated.PermanentWaterBodyIF;
import csu.mrc.ivcc.mdss.geo.generated.PopulatedAreaIF;
import csu.mrc.ivcc.mdss.geo.generated.ProvinceIF;
import csu.mrc.ivcc.mdss.geo.generated.SentinalSiteIF;
import csu.mrc.ivcc.mdss.geo.generated.TrapIF;

public class TypesInitializer
{
  /**
   * Creates default GeoEntity types.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    ClientSession session = ClientSession.createUserSession(ServerConstants.SYSTEM_USER_NAME, ServerConstants.SYSTEM_DEFAULT_PASSWORD);
    ClientRequestIF request = session.getRequest();
    
    createGeoEntityTypes(request.getSessionId());
  }
  
  @StartSession
  private static void createGeoEntityTypes(String sessionId)
  {
    createGeoEntityTypes();
  }
  
  @Transaction
  private static void createGeoEntityTypes()
  {
    // Country
    GeoHierarchyView country = defineGeoEntity(true, CountryIF.NAME, "Country", "", SpatialTypes.POLYGON, null, new GeoHierarchyView[]{});
    
    // Province
    GeoHierarchyView province = defineGeoEntity(true, ProvinceIF.NAME, "Province", "", SpatialTypes.POLYGON, null, country);
    
    // District
    GeoHierarchyView district = defineGeoEntity(true, DistrictIF.NAME, "District", "", SpatialTypes.POLYGON, null, province);
    
    // Admin Post
    GeoHierarchyView adminPost = defineGeoEntity(true, AdminPostIF.NAME, "Admin Post", "", SpatialTypes.POLYGON, null, district);
    
    // Locality
    GeoHierarchyView locality = defineGeoEntity(true, LocalityIF.NAME, "Locality", "", SpatialTypes.POLYGON, null, adminPost);
    
    // Populated Area
    GeoHierarchyView populatedArea = defineGeoEntity(true, PopulatedAreaIF.NAME, "PopulatedArea", "", SpatialTypes.POLYGON, null, locality);
    
    // Abstract Site
    GeoHierarchyView abstractSite = defineGeoEntity(false, AbstractSiteIF.NAME, "Abstract Site", "", SpatialTypes.POLYGON, null, locality);
    
    // Sentinel Site
    GeoHierarchyView sentinalSite = defineGeoEntity(false, SentinalSiteIF.NAME, "Sentinal Site", "", SpatialTypes.POLYGON, abstractSite, locality);    
    
    // Non Sentinal Site
    GeoHierarchyView nonSentinalSite = defineGeoEntity(false, NonSentinalSiteIF.NAME, "Non-Sentinal Site", "", SpatialTypes.POLYGON, abstractSite, locality);
    
    // Trap
    GeoHierarchyView fixedTrap = defineGeoEntity(false, TrapIF.NAME, "Trap", "", SpatialTypes.POINT, null, nonSentinalSite);
    
    // Breeding Site
    GeoHierarchyView breedingSite = defineGeoEntity(false, BreedingSiteIF.NAME, "Breeding Site", "", SpatialTypes.POLYGON, null, locality);
    
    // Permanent Water Body
    GeoHierarchyView permanentWaterBody = defineGeoEntity(false, PermanentWaterBodyIF.NAME, "Permanent Water Body", "", SpatialTypes.POLYGON, breedingSite, locality);
  }
  
  private static GeoHierarchyView defineGeoEntity(boolean political, String typeName, String label, String description, SpatialTypes spatialType, GeoHierarchyView parentType, GeoHierarchyView ... allowedIn)
  {
    GeoEntityDefinition def = new GeoEntityDefinition();
    def.setTypeName(typeName);
    def.setPolitical(political);
    def.setDisplayLabel(label);
    def.setDescription(description);
    def.addSpatialType(spatialType);
    def.setParentTypeId(parentType != null ? parentType.getReferenceId() : null);

    String[] allowedInIds = new String[allowedIn.length];
    for(int i=0; i<allowedIn.length ; i++)
    {
      allowedInIds[i] = allowedIn[i].getGeoHierarchyId();
    }
    
    return GeoHierarchy.defineGeoEntity(def, allowedInIds);
  }
}
