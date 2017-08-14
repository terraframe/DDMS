package dss.vector.solutions.etl.dhis2;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.session.Request;

import dss.vector.solutions.geo.generated.Earth;

public class GeoMap extends GeoMapBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 891743714;

  public GeoMap()
  {
    super();
  }

  @Override
  public String buildKey()
  {
    return this.getGeoEntity().getId();
  }

  public static String getMappings(String parentId)
  {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT");
    sql.append(" gm.id AS id,");
    sql.append(" ge.id AS geoId,");
    sql.append(" COALESCE(gel.default_locale) || ' (' || COALESCE(mdl.default_locale) || ') ' || ge.geo_id AS geoLabel,");
    sql.append(" ou.id AS orgId,");
    sql.append(" ou.dhis2_id || ' (' || oul.dhis2_id || ') ' || ou.code AS orgLabel,");
    sql.append(" gm.confirmed AS confirmed");
    sql.append(" FROM ddms.located_in AS li");
    sql.append(" LEFT JOIN ddms.geo_entity AS ge ON ge.id = li.child_id");
    sql.append(" LEFT JOIN ddms.metadata AS meta ON meta.key_name = ge.type");
    sql.append(" LEFT JOIN ddms.md_type AS md ON md.id = meta.id");
    sql.append(" LEFT JOIN ddms.geo_entity_entity_label AS gel ON ge.entity_label = gel.id");
    sql.append(" LEFT JOIN ddms.metadata_display_label AS mdl ON md.display_label = mdl.id");
    sql.append(" JOIN ddms.geo_map AS gm ON ge.id = gm.geo_entity");
    sql.append(" LEFT OUTER JOIN ddms.org_unit AS ou ON ou.id = gm.org_unit");
    sql.append(" LEFT OUTER JOIN ddms.org_unit_level AS oul ON oul.id = ou.org_unit_level");
    sql.append(" WHERE li.parent_id = '" + parentId + "'");
    sql.append(" ORDER BY gel.default_locale");

    ResultSet resultSet = null;

    try
    {
      try
      {
        resultSet = Database.query(sql.toString());

        JSONArray response = new JSONArray();

        while (resultSet.next())
        {
          JSONObject jObject = new JSONObject();

          jObject.put("id", resultSet.getString("geoId"));
          jObject.put("name", resultSet.getString("geoLabel"));
          jObject.put("mappingId", resultSet.getString("id"));
          jObject.put("orgId", resultSet.getString("orgId"));
          jObject.put("orgLabel", resultSet.getString("orgLabel"));
          jObject.put("confirmed", resultSet.getBoolean("confirmed"));
          jObject.put("hasChildren", true);

          response.put(jObject);
        }

        return response.toString();
      }
      finally
      {
        if (resultSet != null)
        {
          resultSet.close();
        }
      }
    }
    catch (JSONException | SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static String getRoots()
  {
    Earth earth = Earth.getEarthInstance();

    return GeoMap.getMappings(earth.getId());
  }

  public static void main(String[] args)
  {
    mainInRequest();
  }

  @Request
  private static void mainInRequest()
  {
    System.out.println(GeoMap.getRoots());
  }
}
