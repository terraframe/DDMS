package dss.vector.solutions.etl.dhis2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdLocalStructDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdLocalStructDAO;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntityEntityLabel;

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
    MdLocalStructDAOIF gel = MdLocalStructDAO.getMdLocalStructDAO(GeoEntityEntityLabel.CLASS);
    MdLocalStructDAOIF mdl = MdLocalStructDAO.getMdLocalStructDAO(MetadataDisplayLabel.CLASS);

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT");
    sql.append(" gm.id AS id,");
    sql.append(" ge.id AS geoId,");
    sql.append(" " + localize(gel, "gel") + " || ' (' || " + localize(mdl, "mdl") + " || ') ' || ge.geo_id AS geoLabel,");
    sql.append(" ou.id AS orgId,");
    sql.append(" ou.name || ' (' || oul.name || ') ' || COALESCE(ou.code, '') AS orgLabel,");
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

  public static String localize(MdLocalStructDAOIF mdLocalStruct, String prefix)
  {
    List<String> selectableList = new ArrayList<String>();
    Locale locale = Session.getCurrentLocale();

    String[] localeStringArray;

    MdDimensionDAOIF mdDimensionDAOIF = Session.getCurrentDimension();
    if (mdDimensionDAOIF != null)
    {
      localeStringArray = new String[2];
      localeStringArray[0] = mdDimensionDAOIF.getLocaleAttributeName(locale);
      localeStringArray[1] = locale.toString();
    }
    else
    {
      localeStringArray = new String[1];
      localeStringArray[0] = locale.toString();
    }

    boolean firstIterationComplete = false;
    for (String localeString : localeStringArray)
    {
      for (int i = localeString.length(); i > 0; i = localeString.lastIndexOf('_', i - 1))
      {
        String subLocale = localeString.substring(0, i);
        for (MdAttributeConcreteDAOIF a : mdLocalStruct.definesAttributes())
        {
          if (a.definesAttribute().equalsIgnoreCase(subLocale))
          {
            selectableList.add(a.getColumnName());
          }
        }
      }

      // Check the default for the dimension
      if (mdDimensionDAOIF != null && !firstIterationComplete)
      {
        String dimensionDefaultAttr = mdDimensionDAOIF.getDefaultLocaleAttributeName();
        MdAttributeDAOIF definesDimensionDefault = mdLocalStruct.definesAttribute(dimensionDefaultAttr);
        if (definesDimensionDefault != null)
        {
          selectableList.add(definesDimensionDefault.getColumnName());
        }
      }

      firstIterationComplete = true;
    }
    // And finally, add the default at the very end
    selectableList.add("default_locale");

    StringBuilder sql = new StringBuilder();
    sql.append("COALESCE(" + prefix + "." + selectableList.remove(0));

    for (String selectable : selectableList)
    {
      sql.append(", " + prefix + "." + selectable);
    }

    sql.append(")");

    return sql.toString();
  }

  public static String getRoots()
  {
    Earth earth = Earth.getEarthInstance();

    return GeoMap.getMappings(earth.getId());
  }

}
