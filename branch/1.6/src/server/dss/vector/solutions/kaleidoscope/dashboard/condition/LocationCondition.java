package dss.vector.solutions.kaleidoscope.dashboard.condition;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.GeneratedComponentQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.LocalizationFacade;
import dss.vector.solutions.util.QueryUtil;

public class LocationCondition extends DashboardCondition implements com.runwaysdk.generation.loader.Reloadable
{

  /**
   * Condition type for restricting on global location
   */
  public static final String CONDITION_TYPE = "LOCATION_CONDITION";

  /**
   * JSON key for the serialized label attribute
   */
  public static final String LABEL_KEY      = "label";

  public static final String LOCATIONS_KEY  = "locations";

  private String             locations;

  public LocationCondition()
  {
    super();
  }

  public LocationCondition(String locations)
  {
    super();

    this.locations = locations;
  }

  public String getLocations()
  {
    return locations;
  }

  public void setLocations(String locations)
  {
    this.locations = locations;
  }

  public List<GeoEntity> getGeoEntities()
  {
    List<GeoEntity> entities = new LinkedList<GeoEntity>();

    /*
     * Handle legacy data
     */
    if (this.locations != null && this.locations.length() > 0)
    {
      if (!this.locations.startsWith("["))
      {
        entities.add(GeoEntity.get(this.locations));
      }
      else
      {
        try
        {
          JSONArray array = new JSONArray(this.locations);

          for (int i = 0; i < array.length(); i++)
          {
            JSONObject location = array.getJSONObject(i);
            String entityId = location.getString(VALUE_KEY);

            entities.add(GeoEntity.get(entityId));
          }
        }
        catch (JSONException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }

    return entities;
  }

  private List<GeoEntity> getFilteredGeoEntities()
  {
    List<GeoEntity> entities = this.getGeoEntities();

    List<GeoEntity> filtered = new LinkedList<GeoEntity>();

    for (GeoEntity entity : entities)
    {
      boolean within = false;

      for (GeoEntity test : entities)
      {
        if (!entity.getId().equals(test.getId()))
        {
          AllPathsQuery query = new AllPathsQuery(new QueryFactory());
          query.WHERE(query.getChildGeoEntity().EQ(entity));
          query.AND(query.getParentGeoEntity().EQ(test));

          if (query.getCount() > 0)
          {
            within = true;
          }
        }
      }

      if (!within)
      {
        filtered.add(entity);
      }
    }

    return filtered;
  }

  @Override
  public void restrictQuery(ValueQuery _vQuery, Selectable _attribute)
  {
    List<GeoEntity> entities = this.getFilteredGeoEntities();

    if (entities.size() > 0)
    {
      AttributeReference attributeReference = (AttributeReference) _attribute;

      AllPathsQuery aptQuery = new AllPathsQuery(_vQuery);

      for (GeoEntity entity : entities)
      {
        aptQuery.OR(aptQuery.getParentGeoEntity().EQ(entity));
      }

      _vQuery.AND(attributeReference.EQ(aptQuery.getChildGeoEntity()));
    }
  }

  @Override
  public JSONObject getJSON()
  {
    try
    {
      JSONArray locations = new JSONArray();

      List<GeoEntity> entities = this.getGeoEntities();

      for (GeoEntity entity : entities)
      {
        JSONObject location = new JSONObject();
        location.put(VALUE_KEY, entity.getId());
        location.put(LABEL_KEY, entity.getLabel());

        locations.put(location);
      }

      JSONObject object = new JSONObject();
      object.put(TYPE_KEY, CONDITION_TYPE);
      object.put(OPERATION_KEY, OPERATION_KEY);
      object.put(LOCATIONS_KEY, locations);

      return object;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public String getJSONKey()
  {
    return CONDITION_TYPE;
  }

  @Override
  public List<String> getConditionInformation()
  {
    List<String> messages = new LinkedList<String>();

    List<GeoEntity> entities = this.getGeoEntities();

    for (GeoEntity entity : entities)
    {
      String localizedValue = entity.getEntityLabel().getValue();

      String message = LocalizationFacade.getFromBundles("location.condition");
      message = message.replace("{0}", localizedValue);

      messages.add(message);
    }

    return messages;
  }

  @Override
  public void restrictQuery(String _type, ValueQuery _vQuery, GeneratedComponentQuery _query)
  {
    MdClassDAOIF mdClass = _query.getMdClassIF();
    MdAttributeDAOIF mdAttribute = QueryUtil.getGeoEntityAttribute(mdClass);

    if (mdAttribute != null)
    {
      AttributeReference attribute = (AttributeReference) _query.get(mdAttribute.definesAttribute());

      this.restrictQuery(_vQuery, attribute);
    }
  }
}
