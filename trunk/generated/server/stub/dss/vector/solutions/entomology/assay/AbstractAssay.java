package dss.vector.solutions.entomology.assay;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.constants.MdBusinessInfo;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoin;
import com.terraframe.mojo.query.Join;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableMoment;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.entomology.ConcreteMosquitoCollection;
import dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.mo.AbstractTerm;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryConfig;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractAssay extends AbstractAssayBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543768433L;

  public AbstractAssay()
  {
    super();
  }

  @Override
  public void apply()
  {
    validateTestDate();

    super.apply();
  }

  @Override
  public void validateTestDate()
  {
    if (this.getTestDate() != null)
    {
      super.validateTestDate();

      Date current = new Date();

      if (current.before(this.getTestDate()))
      {
        String msg = "It is impossible to have a test date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getTestDate());
        p.setCurrentDate(current);
        p.setNotification(this, TESTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  /**
   * Returns a JSON String that represents the Assay hierarchy.
   *
   * @return
   */
  public static String getAssayTree()
  {
    try
    {
      MdBusiness root = MdBusiness.getMdBusiness(AbstractTerm.CLASS);

      JSONArray rootChildren = new JSONArray();
      JSONObject rootNode = treeRecurse(root);
      rootChildren.put(rootNode);

      return rootChildren.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Recurses into the AbstractAssay hierarchy.
   *
   * @param parent
   * @return
   * @throws JSONException
   */
  private static JSONObject treeRecurse(MdBusiness parent) throws JSONException
  {
    OIterator<? extends MdBusiness> iter = parent.getAllSubClass();

    JSONObject parentNode = new JSONObject();
    JSONArray children = new JSONArray();

    String type = parent.getPackageName() + "." + parent.getTypeName();
    parentNode.put(MdBusinessInfo.ID, parent.getId());
    parentNode.put(MdBusinessInfo.CLASS, type);
    parentNode.put(MdBusinessInfo.ABSTRACT, parent.getIsAbstract());
    parentNode.put(MdBusinessInfo.DISPLAY_LABEL, parent.getDisplayLabel());
    parentNode.put("children", children);

    try
    {

      while (iter.hasNext())
      {
        MdBusiness child = iter.next();

        JSONObject childNode = treeRecurse(child);

        children.put(childNode);
      }
    }
    finally
    {
      iter.close();
    }

    return parentNode;
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

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, thematicLayer, includeGeometry, selectedUniversals, MosquitoCollection.CLASS, MosquitoCollection.GEOENTITY);

    // join Mosquito with mosquito collection
    MosquitoCollectionQuery collectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);

    // join Mosquito with mosquito collection
    AdultDiscriminatingDoseAssayQuery adultQuery = (AdultDiscriminatingDoseAssayQuery) queryMap.get(AdultDiscriminatingDoseAssay.CLASS);
    if (adultQuery != null)
    {
      valueQuery.WHERE(adultQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }

    LarvaeDiscriminatingDoseAssayQuery larvaeQuery = (LarvaeDiscriminatingDoseAssayQuery) queryMap.get(LarvaeDiscriminatingDoseAssay.CLASS);
    if (larvaeQuery != null)
    {
      valueQuery.WHERE(larvaeQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }

    KnockDownAssayQuery kdQuery = (KnockDownAssayQuery) queryMap.get(KnockDownAssay.CLASS);
    if (kdQuery != null)
    {
      valueQuery.WHERE(kdQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }

    SelectableMoment dateAttribute = collectionQuery.getDateCollected();

    //this ensures that the date attribute is joined correctly
    ConcreteMosquitoCollectionQuery concreteCollectionQuery = (ConcreteMosquitoCollectionQuery) queryMap.get(ConcreteMosquitoCollection.CLASS);
    if(concreteCollectionQuery == null)
    {
      for(Join join: dateAttribute.getJoinStatements())
      {
        valueQuery.WHERE((InnerJoin) join);
      }
    }

    return QueryUtil.setQueryDates(xml,valueQuery,dateAttribute);

  }



   /**
   * Queries for Mosquitos.
   *
   * @param xml
   */
  @Transaction
  public static com.terraframe.mojo.query.ValueQuery queryResistance(String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    ValueQuery valueQuery = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    System.out.println(valueQuery.getSQL());

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
