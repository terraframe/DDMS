package csu.mrc.ivcc.mdss.geo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;

import csu.mrc.ivcc.mdss.MDSSInfo;
import csu.mrc.ivcc.mdss.geo.generated.GeoEntity;

public class GeoHierarchy extends GeoHierarchyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133816255L;
  
  public GeoHierarchy()
  {
    super();
  }
  
  /**
   * Returns a JSON object representing the allowed GeoEntity types within
   * the GeoEntity of the given id.
   * 
   * @param geoEntityId
   * @return
   */
  public static String defineAllowedTree(String geoEntityId)
  {
    try
    {
      GeoEntity geoEntity = GeoEntity.get(geoEntityId);
      MdBusiness rootMd = MdBusiness.getMdBusiness(geoEntity.getType());

      GeoHierarchy geo = getGeoHierarchyFromType(rootMd);
      
      JSONObject map = new JSONObject();
      JSONObject types = new JSONObject();
      HashSet<String> imports = new HashSet<String>();
      
      treeRecurse(types, imports, geo);
    
      map.put("types", types);
      map.put("imports", new JSONArray(imports));

      return map.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  /**
   * Returns the GeoHierarchy representative of the given MdBusiness (a GeoEntity subtype).
   * 
   * @param md
   * @return
   */
  public static GeoHierarchy getGeoHierarchyFromType(MdBusiness md)
  {
    QueryFactory f = new QueryFactory();
    GeoHierarchyQuery geoQuery = new GeoHierarchyQuery(f);
    geoQuery.WHERE(geoQuery.getGeoEntityClass().EQ(md));
    
    OIterator<? extends GeoHierarchy> iter = geoQuery.getIterator();
    try
    {
      return iter.next(); // There will always be one result.
    }
    finally
    {
      iter.close();
    }
  }
  
  /**
   * Recursive function
   * 
   * @param types
   * @param imports
   * @param geo
   * @throws JSONException
   */
  private static void treeRecurse(JSONObject types, HashSet<String> imports, GeoHierarchy geo) throws JSONException
  {
    List<GeoHierarchy> geos = new LinkedList<GeoHierarchy>();

    OIterator<? extends GeoHierarchy> iter = geo.getAllAcceptsGeoEntity();
    try
    {
      while(iter.hasNext())
      {
        geos.add(iter.next());
      }
    }
    finally
    {
      iter.close();
    }
    
    JSONArray allowed = new JSONArray();
    for(int i=0; i<geos.size(); i++)
    {
      GeoHierarchy g = geos.get(i);
      
      MdBusiness md = g.getGeoEntityClass();
      String type = md.getPackageName()+"."+md.getTypeName();
      
      JSONObject typeAndLabel = new JSONObject();
      typeAndLabel.put("type", type);
      typeAndLabel.put("label", md.getDisplayLabel());
      
      allowed.put(typeAndLabel);
      
      treeRecurse(types, imports, g);
    }
    
    MdBusiness md = geo.getGeoEntityClass();
    String type = md.getPackageName()+"."+md.getTypeName();
    types.put(type, allowed);
    
    imports.add(type);
    imports.add(type+"Controller");
  }
  
  /**
   * Defines a new GeoEntity type based on the value of this view.
   */
  @Transaction
  public static GeoHierarchyView defineGeoEntity(GeoEntityDefinition definition, String[] allowedInIds)
  {
    // validate attributes
    definition.applyNoPersist();
    
    // define the new MdBusiness
    String typeName = definition.getTypeName();
    String label = definition.getDisplayLabel();
    String description = definition.getDescription();
    
    MdBusiness mdGeoEntity = new MdBusiness();
    mdGeoEntity.setPackageName(MDSSInfo.GENERATED_GEO_PACKAGE);
    mdGeoEntity.setTypeName(typeName);
    mdGeoEntity.setDisplayLabel(label);
    mdGeoEntity.setDescription(description);
    mdGeoEntity.setIsAbstract(false); // FIXME let admin set this value
    mdGeoEntity.setExtendable(true);
    mdGeoEntity.setPublish(true);
    
    String parentTypeId = definition.getParentTypeId();
    MdBusiness parent;
    
    if(parentTypeId != null && parentTypeId.trim().length() > 0)
    {
      parent = MdBusiness.get(parentTypeId);
    }
    else
    {
      parent = MdBusiness.getMdBusiness(GeoEntity.CLASS);
    }
    mdGeoEntity.setSuperMdBusiness(parent);
    mdGeoEntity.apply();
    
    // add the spatial attribute to the MdBusiness
    //SpatialTypes spatialTypes = definition.getSpatialType().get(0);
    // FIXME add spatial attribute when nathan is done
    
    // create the GeoHeirachy and relationship
    GeoHierarchy geoHierarchy = new GeoHierarchy();
    geoHierarchy.setPolitical(definition.getPolitical());
    geoHierarchy.setGeoEntityClass(mdGeoEntity);
    geoHierarchy.apply();

    if(allowedInIds != null)
    {
      for(int i=0; i<allowedInIds.length; i++)
      {
        GeoHierarchy allowedIn = GeoHierarchy.get(allowedInIds[i]);
        geoHierarchy.addAllowedInGeoEntity(allowedIn).apply();
      }
    }
    
    GeoHierarchyView view = new GeoHierarchyView();
    view.setReferenceId(mdGeoEntity.getId());
    view.setGeoHierarchyId(geoHierarchy.getId());
    view.setTypeName(definition.getTypeName());
    view.setDisplayLabel(definition.getDisplayLabel());
    
    return view;
  }
  
}
