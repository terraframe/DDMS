package dss.vector.solutions.intervention.monitor;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.util.QueryUtil;

public class SurveyPoint extends SurveyPointBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641306732L;

  public SurveyPoint()
  {
    super();
  }

  @Override
  public void apply()
  {
    validateSurveyDate();

    super.apply();
  }

  @Override
  protected String buildKey()
  {
    if (this.getGeoEntity() != null && this.getSurveyDate() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

      return this.getGeoEntity().getGeoId() + "." + format.format(this.getSurveyDate());
    }

    return this.getId();
  }

  @Override
  public void validateSurveyDate()
  {
    if (this.getSurveyDate() != null)
    {
      super.validateSurveyDate();

      Date current = new Date();

      if (current.before(this.getSurveyDate()))
      {
        String msg = "It is impossible to have a survey date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getSurveyDate());
        p.setCurrentDate(current);
        p.setNotification(this, SURVEYDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    // First delete all of the households of this survey point
    List<Household> list = new LinkedList<Household>();
    OIterator<? extends Household> it = this.getAllHouseholds();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next());
      }
    }
    finally
    {
      it.close();
    }

    for (Household household : list)
    {
      household.delete();
    }

    super.delete();
  }

  @Override
  public SurveyPointView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public SurveyPointView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  private SurveyPointView getView()
  {
    SurveyPointView view = new SurveyPointView();

    view.populateView(this);

    return view;
  }

  public static SurveyPointView getView(String id)
  {
    return SurveyPoint.get(id).getView();
  }

  public static SurveyPoint searchByGeoEntityAndDate(GeoEntity geoEntity, Date date)
  {
    SurveyPointQuery query = new SurveyPointQuery(new QueryFactory());
    query.WHERE(query.getSurveyDate().EQ(date));
    query.AND(query.getGeoEntity().EQ(geoEntity));

    OIterator<? extends SurveyPoint> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

    SurveyPointQuery surveyPointQuery = (SurveyPointQuery) queryMap.get(SurveyPoint.CLASS);
    if (surveyPointQuery != null)
    {
      QueryUtil.joinGeoDisplayLabels(valueQuery, SurveyPoint.CLASS, surveyPointQuery);
    }
    HouseholdQuery householdQuery = (HouseholdQuery) queryMap.get(Household.CLASS);
    if (householdQuery != null)
    {
      valueQuery.WHERE(surveyPointQuery.households(householdQuery));

      QueryUtil.joinTermAllpaths(valueQuery, Household.CLASS, householdQuery);
    }

    SurveyedPersonQuery personQuery = (SurveyedPersonQuery) queryMap.get(SurveyedPerson.CLASS);
    if (personQuery != null)
    {
      if (householdQuery == null)
      {
        householdQuery = new HouseholdQuery(queryFactory);
        valueQuery.WHERE(surveyPointQuery.households(householdQuery));
        valueQuery.FROM(householdQuery);
      }

      valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));

      QueryUtil.joinTermAllpaths(valueQuery, SurveyedPerson.CLASS, personQuery);
    }
    
    // Convert Treatments which is relationship between Surveyed Person and Term
    /*
    TermQuery termQuery = (TermQuery) queryMap.get(Term.CLASS);
    try
    {
      
      SelectableSQLCharacter sel = (SelectableSQLCharacter) valueQuery.getSelectable(SurveyedPersonView.RDTRESULT);

      // If TermQuery exists then restrict by inner joins instead of doing left joins
      if(termQuery != null)
      {
        valueQuery.WHERE(personQuery.locations(termQuery));
        String sql = termQuery.getTableAlias()+"."+Term.NAME;
        sel.setSQL(sql);
      }
      else
      {
        String subSelect = QueryUtil.getRelationshipTermSubSelect(SurveyedPersonView.RDTRESULT, SurveyedPerson.CLASS, SurveyedPersonView.CLASS);
        String subSelectName = "rdtResultTermSubSel";

        String sql = subSelectName+".rDTResult_displayLabel";
        sel.setSQL(sql);

        InnerJoinEq join = new InnerJoinEq("id", personTable, personQuery.getTableAlias(), "id", subSelect, subSelectName);
        valueQuery.AND(join);
      }

    }
    catch(QueryException e)
    {
      // RDTResult not included in the query
    }*/

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    


    // Add net selectables
    for (String entityAlias : queryMap.keySet())
    {
      // if(entityAlias.startsWith(HouseholdNet.CLASS))
      // {
      // if(householdQuery == null)
      // {
      // householdQuery = new HouseholdQuery(queryFactory);
      // valueQuery.WHERE(surveyPointQuery.households(householdQuery));
      // valueQuery.FROM(householdQuery);
      // }
      //
      // TermQuery termNetQuery = new TermQuery(queryFactory);
      //
      // String termId = entityAlias.substring(entityAlias.indexOf("_")+1);
      //
      // HouseholdNetQuery householdNetQuery = (HouseholdNetQuery)
      // queryMap.get(entityAlias);
      //
      // valueQuery.AND(householdQuery.nets(householdNetQuery));
      // valueQuery.AND(householdNetQuery.hasChild(termNetQuery));
      // valueQuery.AND(termNetQuery.getId().EQ(termId));
      // }
    }

    /*
     * // Default prevalence addPrevalenceColumn("prevalence", valueQuery,
     * personQuery, null);
     * 
     * // specific prevalences for(RDTResult result : RDTResult.values()) {
     * addPrevalenceColumn("prevalence_"+result.getId(), valueQuery,
     * personQuery, result); }
     */

    return valueQuery;
  }

  /*
   * FIXME check with Marlize/Miguel on proper implementation private static
   * void addPrevalenceColumn(String prevalenceSel, ValueQuery valueQuery,
   * PersonQuery personQuery, RDTResult rdtResult) { try { SelectableSQLDouble
   * prevalence = (SelectableSQLDouble) valueQuery.getSelectable(prevalenceSel);
   * 
   * // shorten the column alias to avoid truncation. if(rdtResult != null) {
   * prevalence.setColumnAlias(rdtResult.name()); }
   * 
   * ValueQuery innerVQ = new ValueQuery(valueQuery.getQueryFactory());
   * 
   * PersonQuery prevalencePQ = new PersonQuery(valueQuery.getQueryFactory());
   * // PersonQuery for Prevalence
   * 
   * // total tested Condition or =
   * OR.get(prevalencePQ.getPerformedRDT().containsAny(RDTResponse.YES),
   * prevalencePQ.getBloodslide().containsAny(BloodslideResponse.DONE));
   * prevalencePQ.WHERE(or);
   * 
   * // total positive if(rdtResult != null) {
   * prevalencePQ.AND(prevalencePQ.getRDTResult().containsAny(rdtResult)); }
   * else {prevalencePQ.AND(prevalencePQ.getRDTResult().containsAny(RDTResult.
   * MALARIAE_POSITIVE, RDTResult.MIXED_POSITIVE, RDTResult.OVALE_POSITIVE,
   * RDTResult.PF_POSITIVE, RDTResult.VIVAX_POSITIVE)); }
   * 
   * innerVQ.SELECT(F.COUNT(prevalencePQ.getId()));
   * 
   * prevalence.setSQL("100 * AVG( ("+innerVQ.getSQL()+" AND "+prevalencePQ.
   * getTableAlias()+".id = "+personQuery.getTableAlias()+".id))");
   * 
   * } catch(QueryException e) { // no precision query } }
   */

  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, config, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, config, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);

    query.getSQL();
    return exporter.exportStream();
  }

  /**
   * Queries Survey points.
   * 
   * @param queryXML
   * @param config
   * @param sortBy
   * @param ascending
   * @param pageNumber
   * @return
   */
  @Authenticate
  public static com.terraframe.mojo.query.ValueQuery querySurvey(String xml, String config, Integer pageNumber, Integer pageSize)
  {
    ValueQuery valueQuery = xmlToValueQuery(xml, config, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    String sql = valueQuery.getSQL();
    return valueQuery;
  }
}
