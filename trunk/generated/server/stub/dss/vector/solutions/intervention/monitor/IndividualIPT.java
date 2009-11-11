package dss.vector.solutions.intervention.monitor;

import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.MdAttributeBooleanDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.query.AttributeMoment;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableChar;
import com.terraframe.mojo.query.SelectableNumber;
import com.terraframe.mojo.query.SelectableSQLInteger;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryUtil;

public class IndividualIPT extends IndividualIPTBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253643991663L;

  public IndividualIPT()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    // ITN Community Distribution class has no attributes that can form a unique
    // identifier
    return this.getId();
  }

  public static IndividualIPTView getView(String id)
  {
    return IndividualIPT.get(id).getView();
  }

  public IndividualIPTView getView()
  {
    IndividualIPTView view = new IndividualIPTView();
    view.populateView(this);

    return view;
  }

  @Override
  public IndividualIPTView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public IndividualIPTView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void apply()
  {
    validateNumberOfRecievedITNs();
    validateServiceDate();

    // Set the service date to the last update time
    if (this.getServiceDate() == null)
    {
      this.setServiceDate(new Date());
    }

    super.apply();
  }

  @Override
  public void validateNumberOfRecievedITNs()
  {
    if (this.getNumberOfRecievedITNs() != null && ( this.getRecievedITN() == null || !this.getRecievedITN() ))
    {
      String msg = "Number of nets recieved is not applicable when no nets are recieved.";

      MdAttributeBooleanDAOIF retrievedMd = (MdAttributeBooleanDAOIF) getRecievedITNMd();
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, NUMBEROFRECIEVEDITNS);
      p.setInputAttribute(retrievedMd.getDisplayLabel(locale));
      p.setInputValue(retrievedMd.getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateServiceDate()
  {
    if (this.getServiceDate() != null)
    {
      Date current = new Date();

      if (current.before(this.getServiceDate()))
      {
        String msg = "It is impossible to have a service date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getServiceDate());
        p.setCurrentDate(current);
        p.setNotification(this, SERVICEDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Boolean includeGeometry, ThematicLayer thematicLayer)
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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, thematicLayer, includeGeometry, IndividualIPT.CLASS, IndividualIPT.FACILITY);

    IndividualIPTQuery individualIPTQuery = (IndividualIPTQuery) queryMap.get(IndividualIPT.CLASS);
    IndividualIPTCaseQuery individualIPTCaseQuery = (IndividualIPTCaseQuery) queryMap.get(IndividualIPTCase.CLASS);
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);

    valueQuery.WHERE(individualIPTQuery.getIptCase().EQ(individualIPTCaseQuery.getId()));

    valueQuery.WHERE(personQuery.getIptRecipientDelegate().EQ(individualIPTCaseQuery.getPatient()));

    try
    {
      SelectableSQLInteger dobSel = (SelectableSQLInteger) valueQuery.getSelectable("age");

      String personTableAlias = personQuery.getTableAlias();
      String sql = "EXTRACT(year from AGE(NOW(), " + personTableAlias + ".dateofbirth))";
      dobSel.setSQL(sql);
    }
    catch (QueryException e)
    {
      // Person.DOB not included in query.
    }

    for (Iterator<String> iter = queryConfig.keys(); iter.hasNext();)
    {
      String key = (String) iter.next();
      Pattern pattern = Pattern.compile("^(\\w+)Criteria$");
      Matcher matcher = pattern.matcher(key);
      String attributeName = null;
      if (matcher.find())
      {
        attributeName = matcher.group(1);

        try
        {
          String value = queryConfig.getString(key);
          Selectable sel = valueQuery.getSelectable(attributeName);

          if (value.contains("-"))
          {
            String[] range = value.split("-");
            if (range.length == 2)
            {
              String range1 = range[0];
              String range2 = range[1];
              if (range1.length() > 0)
              {
                valueQuery.WHERE( ( (SelectableNumber) sel ).GE(range1));
              }

              if (range2.length() > 0)
              {
                valueQuery.WHERE( ( (SelectableNumber) sel ).LE(range2));
              }
            }
            else
            {
              // Just the GE criteria was specified (e.g., "7-")
              valueQuery.WHERE( ( (SelectableNumber) sel ).GE(range[0]));
            }
          }
          else
          {
            // exact value
            if (sel instanceof SelectableNumber)
            {
              valueQuery.WHERE(sel.EQ(value));
            }
            if (sel instanceof SelectableChar)
            {
              valueQuery.WHERE(((SelectableChar)sel).LIKE(value));
            }

          }

        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          // e.printStackTrace();
        }
      }
    }

    AttributeMoment dateAttribute = individualIPTQuery.getServiceDate();

    return QueryUtil.setQueryDates(xml, valueQuery, dateAttribute);

  }
}
