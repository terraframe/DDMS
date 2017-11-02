package dss.vector.solutions.geo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.conversion.ConversionExceptionDTO;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.query.GeoFieldIF;

public class GeoFieldDTO extends GeoFieldDTOBase implements Reloadable, GeoFieldIF
{
  private static final long   serialVersionUID      = 727056416;

  private static final String SELECT_SEARCH_ROOT_ID = "selectSearchRootId";
  
  private ArrayList<String> extraUniversals = new ArrayList<String>();

  public GeoFieldDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public void addExtraUniversal(String extraUniversal)
  {
    extraUniversals.add(extraUniversal);
  }

  /**
   * Copy Constructor: Duplicates the values and attributes of the given
   * BusinessDTO into a new DTO.
   * 
   * @param businessDTO
   *          The BusinessDTO to duplicate
   * @param clientRequest
   *          The clientRequest this DTO should use to communicate with the
   *          server.
   */
  protected GeoFieldDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  public JSONObject convertToJSON()
  {
    // get the GeoField object for this field

    JSONObject geoFieldJSON = new JSONObject();
    try
    {
      geoFieldJSON.put(GeoFieldDTO.ISPOLITICALHIERARCHY, this.getIsPoliticalHierarchy());
      geoFieldJSON.put(GeoFieldDTO.ISPOPULATIONHIERARCHY, this.getIsPopulationHierarchy());
      geoFieldJSON.put(GeoFieldDTO.ISSPRAYHIERARCHY, this.getIsSprayHierarchy());
      geoFieldJSON.put(GeoFieldDTO.ISURBANHIERARCHY, this.getIsUrbanHierarchy());
      geoFieldJSON.put(GeoFieldDTO.ISUNDERSYSTEMROOT, this.getIsUnderSystemRoot());

      if (!this.getIsUnderSystemRoot())
      {
        geoFieldJSON.put(SELECT_SEARCH_ROOT_ID, EarthDTO.getEarthInstance(this.getRequest()).getId());
      }
      else
      {
        geoFieldJSON.put(SELECT_SEARCH_ROOT_ID, JSONObject.NULL);
      }

      // create the filter
      if (this.getFilterId() != null && this.getFilterId().length() > 0)
      {
        GeoHierarchyViewDTO filterGH = GeoHierarchyDTO.getViewForGeoHierarchy(this.getRequest(), this.getFilterId());
        geoFieldJSON.put("filter", MDSSInfo.GENERATED_GEO_PACKAGE + "." + filterGH.getTypeName());
      }
      else
      {
        geoFieldJSON.put("filter", JSONObject.NULL);
      }

      JSONArray jsonExtraUniversals = new JSONArray();
      geoFieldJSON.put("extraUniversals", jsonExtraUniversals);
      if (extraUniversals.size() == 0)
      {
        List<? extends GeoHierarchyDTO> geoHierarchies = this.getAllGeoHierarchies();
  
        for (GeoHierarchyDTO universal : geoHierarchies)
        {
          // The GeoHierarchyView is the object that contains the qualified type
          // name
          GeoHierarchyViewDTO extra = GeoHierarchyDTO.getViewForGeoHierarchy(universal.getRequest(), universal.getId());
          jsonExtraUniversals.put(MDSSInfo.GENERATED_GEO_PACKAGE + "." + extra.getTypeName());
        }
      }
      else
      {
        for (String extra : extraUniversals)
        {
          jsonExtraUniversals.put(extra);
        }
        
        
      }
    }
    catch (JSONException e)
    {
      throw new ConversionExceptionDTO("Could not convert an instance of [" + GeoFieldDTO.CLASS + "] to JSON.", e);
    }

    return geoFieldJSON;
  }

}
