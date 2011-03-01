package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.SurveyQB;

public class SurveyPoint extends SurveyPointBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641306732L;

  public SurveyPoint()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getGeoEntity() != null && this.getSurveyDate() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Session.getCurrentLocale());

      return this.getClassDisplayLabel() + ": (" + this.getGeoEntity().getLabel() + ", " + format.format(this.getSurveyDate()) + ")";
    }

    return super.toString();
  }

  @Override
  public void apply()
  {
    validateSurveyDate();
    
    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }

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
    return new SurveyQB(xml, config, layer).construct();
  }
}
