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
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQLCharacter;
import com.terraframe.mojo.query.SelectableSQLInteger;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.NoThematicLayerException;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryUtil;

public class SurveyPoint extends SurveyPointBase implements
    com.terraframe.mojo.generation.loader.Reloadable
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
    if(this.getGeoEntity() != null && this.getSurveyDate() != null)
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

  @Authenticate
  public static ValueQuery xmlToValueQuery(String xml, String config,
      boolean includeGeometry, ThematicLayer thematicLayer)
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

    String dobCriteria = getDobCriteria(queryConfig);

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, queryConfig, thematicLayer, includeGeometry, SurveyPoint.CLASS, SurveyPoint.GEOENTITY);

    SurveyPointQuery surveyPointQuery = (SurveyPointQuery) queryMap.get(SurveyPoint.CLASS);
    HouseholdQuery householdQuery = (HouseholdQuery) queryMap.get(Household.CLASS);
    PersonQuery personQuery = (PersonQuery) queryMap.get(Person.CLASS);
    TermQuery termQuery = (TermQuery) queryMap.get(Term.CLASS);

    if(householdQuery != null)
    {
      valueQuery.WHERE(surveyPointQuery.households(householdQuery));

      String[] houseAttributes = Term.getTermAttributes(Household.CLASS);
      String sql = "(" + QueryUtil.getTermSubSelect(Household.CLASS, houseAttributes) + ")";
      String subSelect = "houseTermSubSel";
      valueQuery.AND(new InnerJoinEq("id","household",householdQuery.getTableAlias(),"id",sql,subSelect));
    }

    String personTable = MdBusiness.getMdBusiness(Person.CLASS).getTableName();
    if(personQuery != null)
    {
      if(householdQuery == null)
      {
        householdQuery = new HouseholdQuery(queryFactory);
        valueQuery.WHERE(surveyPointQuery.households(householdQuery));
        valueQuery.FROM(householdQuery);
      }

      valueQuery.WHERE(householdQuery.persons(personQuery));


      String[] personAttributes = Term.getTermAttributes(Person.CLASS);
      String sql = "(" + QueryUtil.getTermSubSelect(Person.CLASS, personAttributes) + ")";
      String subSelect = "personTermSubSel";
      valueQuery.AND(new InnerJoinEq("id", personTable,personQuery.getTableAlias(),"id",sql,subSelect));
    }

    // Convert RDTResult which is relationship between Person and Term
    try
    {
      SelectableSQLCharacter sel = (SelectableSQLCharacter) valueQuery.getSelectable(PersonView.RDTRESULT);

      // If TermQuery exists then restrict by inner joins instead of doing left joins
      if(termQuery != null)
      {
        valueQuery.WHERE(personQuery.rDTResults(termQuery));
        String sql = termQuery.getTableAlias()+"."+Term.NAME;
        sel.setSQL(sql);
      }
      else
      {
        String subSelect = QueryUtil.getRelationshipTermSubSelect(PersonView.RDTRESULT, Person.CLASS, PersonRDTResult.CLASS);
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
    }

    // Convert Person.DOB into an integer
    try
    {
      SelectableSQLInteger dobSel = (SelectableSQLInteger) valueQuery.getSelectable(Person.DOB);

      String personTableAlias = personQuery.getTableAlias();
      String sql = "EXTRACT(year from AGE(NOW(), "+personTableAlias+".dob))";
      dobSel.setSQL(sql);

      // Check for equals or range criteria on Person.DOB
      if(dobCriteria != null)
      {
        if(dobCriteria.contains("-"))
        {
          String[] range = dobCriteria.split("-");
          if(range.length == 2)
          {
            String range1 = range[0];
            String range2 = range[1];
            if(range1.length() > 0)
            {
              valueQuery.WHERE(dobSel.GE(range1));
            }

            if(range2.length() > 0)
            {
              valueQuery.WHERE(dobSel.LE(range2));
            }
          }
          else
          {
            // Just the GE criteria was specified (e.g., "7-")
            valueQuery.WHERE(dobSel.GE(range[0]));
          }
        }
        else
        {
          // exact value
          valueQuery.WHERE(dobSel.EQ(dobCriteria));
        }
      }
    }
    catch(QueryException e)
    {
      // Person.DOB not included in query.
    }

    QueryUtil.setQueryDates(xml, valueQuery, surveyPointQuery.getSurveyDate());

    // Add net selectables
    for(String entityAlias : queryMap.keySet())
    {
      if(entityAlias.startsWith(HouseholdNet.CLASS))
      {
        if(householdQuery == null)
        {
          householdQuery = new HouseholdQuery(queryFactory);
          valueQuery.WHERE(surveyPointQuery.households(householdQuery));
          valueQuery.FROM(householdQuery);
        }

        TermQuery termNetQuery = new TermQuery(queryFactory);

        String termId = entityAlias.substring(entityAlias.indexOf("_")+1);

        HouseholdNetQuery householdNetQuery = (HouseholdNetQuery) queryMap.get(entityAlias);

        valueQuery.AND(householdQuery.nets(householdNetQuery));
        valueQuery.AND(householdNetQuery.hasChild(termNetQuery));
        valueQuery.AND(termNetQuery.getId().EQ(termId));
      }
    }

   /*
    // Default prevalence
    addPrevalenceColumn("prevalence", valueQuery, personQuery, null);

    // specific prevalences
    for(RDTResult result : RDTResult.values())
    {
      addPrevalenceColumn("prevalence_"+result.getId(), valueQuery, personQuery, result);
    }
    */

    return valueQuery;
  }

  private static String getDobCriteria(JSONObject config)
  {
    String dobCriteriaKey = "dobCriteria";
    if(config.has(dobCriteriaKey) && !config.isNull(dobCriteriaKey))
    {
      try
      {
        return config.getString(dobCriteriaKey);
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }
    else
    {
      return null;
    }
  }

  /* FIXME check with Marlize/Miguel on proper implementation
  private static void addPrevalenceColumn(String prevalenceSel, ValueQuery valueQuery, PersonQuery personQuery, RDTResult rdtResult)
  {
    try
    {
      SelectableSQLDouble prevalence = (SelectableSQLDouble) valueQuery.getSelectable(prevalenceSel);

      // shorten the column alias to avoid truncation.
      if(rdtResult != null)
      {
        prevalence.setColumnAlias(rdtResult.name());
      }

      ValueQuery innerVQ = new ValueQuery(valueQuery.getQueryFactory());

      PersonQuery prevalencePQ = new PersonQuery(valueQuery.getQueryFactory()); // PersonQuery for Prevalence

      // total tested
      Condition or = OR.get(prevalencePQ.getPerformedRDT().containsAny(RDTResponse.YES),
          prevalencePQ.getBloodslide().containsAny(BloodslideResponse.DONE));
      prevalencePQ.WHERE(or);

      // total positive
      if(rdtResult != null)
      {
        prevalencePQ.AND(prevalencePQ.getRDTResult().containsAny(rdtResult));
      }
      else
      {
        prevalencePQ.AND(prevalencePQ.getRDTResult().containsAny(RDTResult.MALARIAE_POSITIVE, RDTResult.MIXED_POSITIVE,
          RDTResult.OVALE_POSITIVE, RDTResult.PF_POSITIVE, RDTResult.VIVAX_POSITIVE));
      }

      innerVQ.SELECT(F.COUNT(prevalencePQ.getId()));

      prevalence.setSQL("100 * AVG( ("+innerVQ.getSQL()+" AND "+prevalencePQ.getTableAlias()+".id = "+personQuery.getTableAlias()+".id))");

    }
    catch(QueryException e)
    {
      // no precision query
    }
  }
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

    ValueQuery query = xmlToValueQuery(queryXML, config, false, null);

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

    ValueQuery query = xmlToValueQuery(queryXML, config, false, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);


    query.getSQL();
    return exporter.exportStream();
  }

  /**
   * Creates a
   *
   * @param xml
   * @return
   */
  @Transaction
  public static String mapQuery(String xml, String config, String[] universalLayers, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot map a query without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ThematicLayer thematicLayer = search.getThematicLayer();

    if (thematicLayer == null || thematicLayer.getGeoHierarchy() == null)
    {
      String error = "Cannot create a map for search [" + search.getQueryName()
          + "] without having selected a thematic layer.";
      NoThematicLayerException ex = new NoThematicLayerException(error);
      throw ex;
    }

    // Update ThematicLayer if the thematic layer type has changed or
    // if one has not yet been defined.
    String thematicLayerType = thematicLayer.getGeoHierarchy().getGeoEntityClass().definesType();
    if (thematicLayer.getGeometryStyle() == null
        || !thematicLayer.getGeoHierarchy().getQualifiedType().equals(thematicLayerType))
    {
      thematicLayer.changeLayerType(thematicLayerType);
    }

    ValueQuery query = xmlToValueQuery(xml, config, true, thematicLayer);

    String layers = MapUtil.generateLayers(universalLayers, query, search, thematicLayer);

    return layers;
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
  public static com.terraframe.mojo.query.ValueQuery querySurvey(String xml, String config,
      Integer pageNumber, Integer pageSize)
  {
    ValueQuery valueQuery = xmlToValueQuery(xml, config, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    String sql = valueQuery.getSQL();
    return valueQuery;
  }
}
