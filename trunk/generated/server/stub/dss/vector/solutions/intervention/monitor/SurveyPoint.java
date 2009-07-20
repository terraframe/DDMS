package dss.vector.solutions.intervention.monitor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQLInteger;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.geo.generated.GeoEntity;
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
    
    return valueQuery;
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
  public static com.terraframe.mojo.query.ValueQuery querySurvey(String xml, String config,
      Integer pageNumber, Integer pageSize)
  {
    QueryConfig queryConfig = new QueryConfig(config);

    ValueQuery valueQuery = xmlToValueQuery(xml, queryConfig, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    return valueQuery;
  }
}
