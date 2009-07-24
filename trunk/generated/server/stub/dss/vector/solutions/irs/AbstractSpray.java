package dss.vector.solutions.irs;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXParseException;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableSingle;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.query.NoColumnsAddedException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.query.ThematicVariable;
import dss.vector.solutions.util.QueryConfig;

public abstract class AbstractSpray extends AbstractSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597926952L;

  public AbstractSpray()
  {
    super();
  }

  @Override
  public void delete()
  {
    SprayData data = this.getSprayData();

    super.delete();

    try
    {
      data.delete();
    }
    catch(Exception e)
    {
      //The spray data is still being referenced by some other spray so do not delete it
    }
  }

  public abstract SprayStatusView getSprayStatusView();

  public void populateView(AbstractSprayView view)
  {
    SprayData data = this.getSprayData();
    view.setBrand(data.getBrand());
    view.setGeoEntity(data.getGeoEntity());
    view.setSprayDate(data.getSprayDate());

    view.clearSprayMethod();
    view.clearSurfaceType();

    for(SprayMethod method : data.getSprayMethod())
    {
      view.addSprayMethod(method);
    }

    for(SurfaceType type : data.getSurfaceType())
    {
      view.addSurfaceType(type);
    }

    view.setDataId(data.getId());
    view.setSprayId(this.getId());
  }


  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  private static ValueQuery xmlToValueQuery(String xml, String[] selectedUniversals, boolean includeGeometry, ThematicLayer thematicLayer)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    ValueQueryParser valueQueryParser;

    try
    {
      valueQueryParser = new ValueQueryParser(xml, valueQuery);
    }
    catch (QueryException e)
    {
      // Check if the error was because no selectables were added.
      Throwable t = e.getCause();
      if (t != null && t instanceof SAXParseException && t.getMessage().contains("{selectable}"))
      {
        NoColumnsAddedException ex = new NoColumnsAddedException();
        throw ex;
      }
      else
      {
        throw e;
      }
    }

    // include the thematic variable (if applicable).
    if (thematicLayer != null)
    {
      ThematicVariable thematicVariable = thematicLayer.getThematicVariable();
      if (thematicVariable != null)
      {
        String entityAlias = thematicVariable.getEntityAlias();
        String userAlias = thematicVariable.getUserAlias();

        valueQueryParser.setColumnAlias(entityAlias, userAlias, QueryConstants.THEMATIC_DATA_COLUMN);
      }
    }

    // include the geometry of the GeoEntity
    if (includeGeometry)
    {
      thematicLayer.getGeoHierarchy().getGeoEntityClass();
      MdBusiness geoEntityMd = thematicLayer.getGeoHierarchy().getGeoEntityClass();

      MdAttributeGeometry mdAttrGeo = GeoHierarchy.getGeometry(geoEntityMd);

      String attributeName = mdAttrGeo.getAttributeName();

      // FIXME might need a ValueQuery and might need to go after the code below
      String type = geoEntityMd.definesType();
      valueQueryParser.addAttributeSelectable(type, attributeName, "", "");
      valueQueryParser.addAttributeSelectable(type, GeoEntity.ENTITYNAME, "", QueryConstants.ENTITY_NAME_COLUMN);
    }

    List<ValueQuery> leftJoinValueQueries = new LinkedList<ValueQuery>();
    for (String selectedGeoEntityType : selectedUniversals)
    {
      GeoEntityQuery geoEntityQuery = new GeoEntityQuery(queryFactory);

      AllPathsQuery subAllPathsQuery = new AllPathsQuery(queryFactory);
      ValueQuery geoEntityVQ = new ValueQuery(queryFactory);
      MdBusinessDAOIF geoEntityMd = MdBusinessDAO.getMdBusinessDAO(selectedGeoEntityType);

      Selectable selectable1 = geoEntityQuery.getEntityName(geoEntityMd.getTypeName() + "_entityName");
      Selectable selectable2 = geoEntityQuery.getGeoId(geoEntityMd.getTypeName() + "_geoId");

      List<MdBusinessDAOIF> allClasses = geoEntityMd.getAllSubClasses();
      Condition[] geoConditions = new Condition[allClasses.size()];
      for (int i = 0; i < allClasses.size(); i++)
      {
        geoConditions[i] = subAllPathsQuery.getParentUniversal().EQ(allClasses.get(i));
      }

      geoEntityVQ.SELECT(selectable1, selectable2, subAllPathsQuery.getChildGeoEntity("CHILD_ID"));
      geoEntityVQ.WHERE(OR.get(geoConditions));
      geoEntityVQ.AND(subAllPathsQuery.getParentGeoEntity().EQ(geoEntityQuery));

      leftJoinValueQueries.add(geoEntityVQ);

      valueQueryParser.setValueQuery(selectedGeoEntityType, geoEntityVQ);
    }

    Map<String, GeneratedEntityQuery> queryMap = valueQueryParser.parse();

    AllPathsQuery allPathsQuery = (AllPathsQuery) queryMap.get(AllPaths.CLASS);
    MosquitoCollectionQuery collectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);

    if (allPathsQuery != null)
    {
      List<SelectableSingle> leftJoinSelectables = new LinkedList<SelectableSingle>();
      for (ValueQuery leftJoinVQ : leftJoinValueQueries)
      {
        leftJoinSelectables.add(leftJoinVQ.aReference("CHILD_ID"));
      }

      valueQuery.AND(allPathsQuery.getChildGeoEntity().LEFT_JOIN_EQ(leftJoinSelectables.toArray(new SelectableSingle[leftJoinSelectables.size()])));

      // Join Collection to GeoEntity
      valueQuery.AND(collectionQuery.getGeoEntity().EQ(allPathsQuery.getChildGeoEntity()));
    }


    String sql = valueQuery.getSQL();
    System.out.println(sql);
    return valueQuery;
  }

  /**
   * Queries for Mosquitos.
   *
   * @param xml
   */
  @Transaction
  @Authenticate
  public static com.terraframe.mojo.query.ValueQuery queryIRS(String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    ValueQuery valueQuery = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    return valueQuery;
  }

  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
    return exporter.exportStream();
  }


}
