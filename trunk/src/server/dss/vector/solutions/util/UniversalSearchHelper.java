package dss.vector.solutions.util;

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchQuery;

public class UniversalSearchHelper implements Reloadable
{
  public static void main(String[] args)
  {
    try
    {
      start(args);
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  @Request
  private static void start(String[] args)
  {
    if (args.length > 0 && args[0].equals("-update"))
    {
      UniversalSearchHelper helper = new UniversalSearchHelper();
      helper.updateAllSearches();
    }
    else
    {
      UniversalSearchHelper helper = new UniversalSearchHelper();
      helper.createAllSearches();
    }

  }

  @Transaction
  public void updateAllSearches()
  {
    QueryFactory f = new QueryFactory();
    GeoHierarchyQuery q = new GeoHierarchyQuery(f);

    Condition condition = q.getGeoEntityClass().getKeyName().NE(GeoEntity.CLASS);
    condition = AND.get(condition, q.getGeoEntityClass().getKeyName().NE(Earth.CLASS));

    q.WHERE(condition);

    OIterator<? extends GeoHierarchy> i = q.getIterator();

    try
    {
      while (i.hasNext())
      {
        GeoHierarchy universal = i.next();
        this.updateSearch(universal);
      }
    }
    finally
    {
      i.close();
    }
  }

  @Transaction
  public void createAllSearches()
  {
    QueryFactory f = new QueryFactory();
    GeoHierarchyQuery q = new GeoHierarchyQuery(f);

    Condition condition = q.getGeoEntityClass().getKeyName().NE(GeoEntity.CLASS);
    condition = AND.get(condition, q.getGeoEntityClass().getKeyName().NE(Earth.CLASS));

    q.WHERE(condition);

    OIterator<? extends GeoHierarchy> i = q.getIterator();

    try
    {
      while (i.hasNext())
      {
        GeoHierarchy universal = i.next();
        this.createSearch(universal);
      }
    }
    finally
    {
      i.close();
    }
  }

  @Transaction
  public void updateSearch(GeoHierarchy universal)
  {
    String queryName = this.getQueryName(universal);
    String queryType = GeoHierarchy.getQueryType();

    SavedSearch search = SavedSearch.getSavedSearch(queryName, queryType);

    if (search != null)
    {
      search.setQueryXml(this.getXML(universal));
      search.apply();
    }
  }

  @Transaction
  public void createSearch(GeoHierarchy universal)
  {
    this.deleteSearch(universal);

    SavedSearch search = new SavedSearch();
    search.setQueryName(this.getQueryName(universal));
    search.setQueryType(GeoHierarchy.getQueryType());
    search.setConfig(this.getConfig(universal));
    search.setQueryXml(this.getXML(universal));
    search.apply();
  }

  @Transaction
  public void deleteSearch(GeoHierarchy universal)
  {
    QueryFactory f = new QueryFactory();
    SavedSearchQuery q = new SavedSearchQuery(f);
    q.WHERE(q.getQueryName().EQ(this.getQueryName(universal)));

    OIterator<? extends SavedSearch> i = q.getIterator();

    try
    {
      while (i.hasNext())
      {
        SavedSearch search = i.next();
        search.delete();
      }
    }
    finally
    {
      i.close();
    }
  }

  // JN
  public String getConfig(GeoHierarchy universal)
  {
    String type = universal.getQualifiedType();
    return "{'date_attribute':{},'terms':{},'criteriaEntities':{},'selectedUniversals':{'" + type + "':['" + type + "']}}";
  }

  // JN
  public String getXML(GeoHierarchy universal)
  {
    String universalClass = this.getLongClassName(universal);
    String underscoredUniversalClass = universalClass.replace('.', '_');
    StringBuilder sb = new StringBuilder();
    sb.append("<query>");
    sb.append("	<entities>");
    sb.append("		<entity>");
    sb.append("			<type>" + universalClass + "</type>");
    sb.append("			<alias>" + universalClass + "</alias>");
    sb.append("			<criteria></criteria>");
    sb.append("		</entity>");
    sb.append("	</entities>");
    sb.append("	<select>");
    sb.append("		<selectable>");
    sb.append("			<attribute>");
    sb.append("				<entityAlias>" + universalClass + "</entityAlias>");
    sb.append("				<name>" + GeoEntity.ENTITYLABEL + "</name>");
    sb.append("				<userAlias>" + underscoredUniversalClass + "__entityName</userAlias>");
    sb.append("				<userDisplayLabel></userDisplayLabel>");
    sb.append("			</attribute>");
    sb.append("		</selectable>");
    sb.append("		<selectable>");
    sb.append("			<attribute>");
    sb.append("				<entityAlias>" + universalClass + "</entityAlias>");
    sb.append("				<name>" + GeoEntity.GEOID + "</name>");
    sb.append("				<userAlias>" + underscoredUniversalClass + "__geoId</userAlias>");
    sb.append("				<userDisplayLabel></userDisplayLabel>");
    sb.append("			</attribute>");
    sb.append("		</selectable>");
    sb.append("	</select>");
    sb.append("	<groupby></groupby>");
    sb.append("	<having></having>");
    sb.append("	<orderby></orderby>");
    sb.append("</query>");
    return sb.toString();
  }

  private String getQueryName(GeoHierarchy universal)
  {
    return this.getLongClassName(universal).substring(MDSSInfo.GENERATED_GEO_PACKAGE.length() + 1);
  }

  private String getLongClassName(GeoHierarchy universal)
  {
    return universal.getQualifiedType();
  }
}
