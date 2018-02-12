/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.geo.generated.GeoEntity;

public class PopulationData extends PopulationDataBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256052008761L;

  public PopulationData()
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
    else if (this.getGeoEntity() != null && this.getYearOfData() != null)
    {
      return this.buildKey();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getGeoEntity().getKey() + " - " + this.getYearOfData();
  }

  public PopulationDataView getView()
  {
    PopulationDataView view = new PopulationDataView();
    view.populateView(this);

    return view;
  }

  @Override
  public PopulationDataView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public PopulationDataView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public void delete()
  {
    super.delete();

    if (!this.getEstimated() && this.getPopulation() != null)
    {
      this.updateFuturePopulations();
    }
  }

  private void updateFuturePopulations()
  {
    List<PopulationData> future = this.getFuturePopulationData();

    // Update all of the future populations until we find data with a population
    // value. We don't want to update the data after another entry, X, with
    // population because the estimated population values after year X are
    // based upon X's population value not this population value.
    for (PopulationData data : future)
    {
      if (data.getEstimated())
      {
        if (data.getGrowthRate() != null)
        {
          data.setPopulation(null);
          data.setEstimated(false);
          data.apply();
        }
        else
        {
          data.delete();
        }
      }
      else
      {
        return;
      }
    }
  }

  private List<PopulationData> getFuturePopulationData()
  {
    List<PopulationData> list = new LinkedList<PopulationData>();

    PopulationDataQuery query = new PopulationDataQuery(new QueryFactory());
    query.WHERE(AND.get(query.getGeoEntity().EQ(this.getGeoEntity()), query.getYearOfData().GT(this.getYearOfData())));
    query.ORDER_BY_ASC(query.getYearOfData());

    list.addAll(query.getIterator().getAll());

    return list;
  }

  @Override
  public void apply()
  {
    this.validateGeoEntity();
    this.validateYearOfData();
    this.validatePopulation();

    super.apply();

    if (!this.getEstimated() && this.getPopulation() != null)
    {
      this.updateFuturePopulations();
    }
  }

  @Override
  public void validatePopulation()
  {
    if (this.getPopulation() != null && this.getPopulation() < 0)
    {
      String msg = "Population cannot be less than 0";

      PopulationProblem p = new PopulationProblem(msg);
      p.setNotification(this, POPULATION);
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateGeoEntity()
  {
    GeoEntity entity = this.getGeoEntity();

    if (entity != null)
    {
      GeoHierarchy geoHierarchy = GeoHierarchy.getGeoHierarchyFromType(entity.getType());

      if (!geoHierarchy.getPopulationAllowed())
      {
        String label = entity.getLabel();

        String msg = "The Geo Entity [" + label + "] does not allow population values";

        GeoEntityPopulationProblem p = new GeoEntityPopulationProblem(msg);
        p.setEntityLabel(label);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateYearOfData()
  {
    if (this.getYearOfData() != null && !PopulationData.isValidYear(this.getYearOfData()))
    {
      Calendar calendar = Calendar.getInstance();

      int max = calendar.get(Calendar.YEAR);
      int min = calendar.getActualMinimum(Calendar.YEAR);

      String msg = "The year [" + this.getYearOfData() + " ] must be between [" + min + "] and [" + max + "]";

      RangeValueProblem p = new RangeValueProblem(msg);
      p.setNotification(this, YEAROFDATA);
      p.setLowerLimit(min);
      p.setUpperLimit(max);
      p.setInvalidValue(this.getYearOfData());
      p.apply();
      p.throwIt();
    }
  }

  public static boolean isValidYear(Integer year)
  {
    Calendar calendar = Calendar.getInstance();

    int max = calendar.get(Calendar.YEAR);
    int min = calendar.getActualMinimum(Calendar.YEAR);

    return ! ( year < min || year > max );
  }

  public static GeoHierarchyQuery getValidGeoHierarchies()
  {
    GeoHierarchyQuery query = new GeoHierarchyQuery(new QueryFactory());
    query.WHERE(query.getPopulationAllowed().EQ(true));
    return query;
  }

  @Transaction
  public static long calculateAnnualPopulation(String geoId, int year)
  {
    // MdssLog.debug("---------" + year + "---------");
    long population = -1L;

    // Get all of the population data records that are for the year we requested
    // or earlier
    // and get them in descending order (most recent first).
    QueryFactory f = new QueryFactory();
    PopulationDataQuery q = new PopulationDataQuery(f);
    q.WHERE(q.getGeoEntity().getGeoId().EQ(geoId).AND(q.getYearOfData().LE(year)));
    q.ORDER_BY(q.getYearOfData(), SortOrder.DESC);

    Stack<PopulationData> data = new Stack<PopulationData>();
    int currentPopulationYear = -1;
    double currentGrowthRate = Double.NaN;
    long currentPopulation = -1L;

    OIterator<? extends PopulationData> i = q.getIterator();
    boolean finished = false;
    try
    {
      while (i.hasNext() && !finished)
      {
        PopulationData pd = i.next();
        // MdssLog.debug("DB " + pd.getGeoEntity().getGeoId() + "\t" +
        // pd.getYearOfData() + "\t" + pd.getPopulation() + "\t" +
        // pd.getGrowthRate());

        // If we have a valid population (estimated or not) for this
        // year, use it
        if (pd.getYearOfData() == year && pd.getPopulation() != null)
        {
          population = pd.getPopulation();
          break;
        }

        if (currentPopulationYear < 0)
        {
          // We haven't gotten a population yet, so we need to find
          // one
          if (pd.getPopulation() != null)
          {
            // We have one, so store it (and the year)
            currentPopulationYear = pd.getYearOfData();
            currentPopulation = pd.getPopulation();
          }
        }

        if (pd.getGrowthRate() != null)
        {
          // If we've already gotten a population, we're done!
          if (currentPopulation >= 0)
          {
            currentGrowthRate = pd.getGrowthRate();
            finished = true;
            break;
          }
          else
          {
            // otherwise, push it on the stack for the calculation
            data.push(pd);
          }
        }
      }
    }
    finally
    {
      i.close();
    }

    if (population >= 0)
    {
      return population;
    }

    // We never found both a population and a growth rate to calculate from
    if (!finished)
    {
      return -1l;
    }

    // Loop over each change in growth rate, calculating (and caching in the DB)
    // the population for each.
    for (PopulationData growthChange : data)
    {
      int thisYear = growthChange.getYearOfData();
      currentPopulation = Math.round(Math.pow(1.0f + currentGrowthRate, thisYear - currentPopulationYear) * currentPopulation);
      currentPopulationYear = thisYear;
      currentGrowthRate = growthChange.getGrowthRate();

      growthChange.setPopulation(currentPopulation);
      growthChange.setEstimated(true);
      growthChange.apply();
    }

    // Calculate the population from the last growth rate available to now
    population = Math.round(Math.pow(1.0f + currentGrowthRate, year - currentPopulationYear) * currentPopulation);

    return population;
  }

  @Transaction
  public static long calculateSeasonalPopulation(String geoId, Date seasonStart, Date seasonEnd)
  {
    return calculatePopulationAt(geoId, ( seasonStart.getTime() + seasonEnd.getTime() ) / 2L);
  }

  @Transaction
  public static long calculateSeasonalPopulation(String geoId, Calendar seasonStart, Calendar seasonEnd)
  {
    return calculatePopulationAt(geoId, ( seasonStart.getTimeInMillis() + seasonEnd.getTimeInMillis() ) / 2L);
  }

  @Transaction
  public static long calculateSeasonalPopulation(String geoId, MalariaSeason season)
  {
    return calculateSeasonalPopulation(geoId, season.getStartDate(), season.getEndDate());
  }

  private static long calculatePopulationAt(String geoId, long timeInMilliseconds)
  {
    Calendar populationDate = getCalendar(timeInMilliseconds);

    // Since the annual populations are effective on July 1, if we're before
    // that we
    // need to last year's and this year's data. Otherwise use this year's and
    // next year's data.
    int priorYear = populationDate.get(Calendar.YEAR);
    if (populationDate.get(Calendar.MONTH) < Calendar.JULY)
    {
      priorYear -= 1;
    }
    Calendar startingPopulationDate = getCalendar(priorYear, Calendar.JULY, 1);
    Calendar endingPopulationDate = getCalendar(priorYear + 1, Calendar.JULY, 1);

    // Get the annual populations for the two dates
    long endingPopulation = calculateAnnualPopulation(geoId, priorYear + 1);
    long startingPopulation = calculateAnnualPopulation(geoId, priorYear);

    // Calculate the fraction of the year's population that we need to use
    float yearFraction = ( (float) timeInMilliseconds - (float) startingPopulationDate.getTimeInMillis() ) / ( (float) endingPopulationDate.getTimeInMillis() - (float) startingPopulationDate.getTimeInMillis() );

    // Return that percentage of the population
    return startingPopulation + Math.round( ( endingPopulation - startingPopulation ) * yearFraction);
  }

  // Return a calendar for a given year/month/day
  public static Calendar getCalendar(int year, int month, int day)
  {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.set(year, month, day);
    return c;
  }

  // Return a calendar for a given millisecond time
  public static Calendar getCalendar(long time)
  {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTimeInMillis(time);
    return c;
  }
}
