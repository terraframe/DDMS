package dss.vector.solutions.intervention.monitor;

import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQLDouble;
import com.terraframe.mojo.query.SelectableSQLInteger;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.BloodslideResponse;
import dss.vector.solutions.intervention.RDTResponse;
import dss.vector.solutions.intervention.RDTResult;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.NoThematicLayerException;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryConfig;
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

  private static ValueQuery xmlToValueQuery(String xml, QueryConfig queryConfig,
      boolean includeGeometry, ThematicLayer thematicLayer)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    String[] selectedUniversals = queryConfig.getSelectedUniversals();
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, thematicLayer, includeGeometry, selectedUniversals, SurveyPoint.CLASS, SurveyPoint.GEOENTITY);
    
    SurveyPointQuery surveyPointQuery = (SurveyPointQuery) queryMap.get(SurveyPoint.CLASS);
    HouseholdQuery householdQuery = (HouseholdQuery) queryMap.get(Household.CLASS);
    PersonQuery personQuery = (PersonQuery) queryMap.get(Person.CLASS);
    
    if(householdQuery != null)
    {
      valueQuery.WHERE(surveyPointQuery.households(householdQuery));
    }
    
    if(personQuery != null)
    {
      if(householdQuery == null)
      {
        householdQuery = new HouseholdQuery(queryFactory);
        valueQuery.WHERE(surveyPointQuery.households(householdQuery));
        valueQuery.FROM(householdQuery);
      }
      
      valueQuery.WHERE(householdQuery.persons(personQuery));
    }
    
    // Convert Person.DOB into an integer
    try
    {
      SelectableSQLInteger dobSel = (SelectableSQLInteger) valueQuery.getSelectable(Person.DOB);
      
      String personTable = personQuery.getTableAlias();
      String sql = "EXTRACT(year from AGE(NOW(), "+personTable+".dob))";
      dobSel.setSQL(sql);

      valueQuery.FROM("person", personTable);

      // Check for equals or range criteria on Person.DOB
      String dobCriteriaKey = "dobCriteria";
      if(queryConfig.has(dobCriteriaKey) && !queryConfig.isNull(dobCriteriaKey))
      {
        String dobCriteria = queryConfig.getString(dobCriteriaKey);
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
        
        NetQuery netQuery = new NetQuery(queryFactory);
        
        String netName = entityAlias.substring(entityAlias.indexOf("_")+1);
        
        HouseholdNetQuery householdNetQuery = (HouseholdNetQuery) queryMap.get(entityAlias);
        
        valueQuery.AND(householdQuery.nets(householdNetQuery));
        valueQuery.AND(householdNetQuery.hasChild(netQuery));
        valueQuery.AND(netQuery.getNetName().EQ(netName));
      }
    }
    
    
    // Precision query
    try
    {
      SelectableSQLDouble defaultPrecision = (SelectableSQLDouble) valueQuery.getSelectable("prevalence");
      
      ValueQuery innerVQ = new ValueQuery(queryFactory);
      
      PersonQuery precisionPQ = new PersonQuery(queryFactory); // PersonQuery for Prevalence
      
      // total tested
      Condition or = OR.get(precisionPQ.getPerformedRDT().containsAny(RDTResponse.YES),
          precisionPQ.getBloodslide().containsAny(BloodslideResponse.DONE));
      precisionPQ.WHERE(or);
      
      // total positive
      precisionPQ.AND(precisionPQ.getRDTResult().containsAny(RDTResult.MALARIAE_POSITIVE, RDTResult.MIXED_POSITIVE,
          RDTResult.OVALE_POSITIVE, RDTResult.PF_POSITIVE, RDTResult.VIVAX_POSITIVE));
      
      innerVQ.SELECT(F.COUNT(precisionPQ.getId()));
      
      defaultPrecision.setSQL("100 * AVG( ("+innerVQ.getSQL()+" AND "+precisionPQ.getTableAlias()+".id = "+personQuery.getTableAlias()+".id))");
      
      String sql = valueQuery.getSQL();
      sql = null;
    }
    catch(QueryException e)
    {
      // no default precision query
    }
    
    return valueQuery;
  }
  
  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, queryConfig, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, queryConfig, false, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
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
    QueryConfig queryConfig = new QueryConfig(config);

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

    ValueQuery query = xmlToValueQuery(xml, queryConfig, true, thematicLayer);

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
    QueryConfig queryConfig = new QueryConfig(config);

    ValueQuery valueQuery = xmlToValueQuery(xml, queryConfig, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    return valueQuery;
  }
}
