/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with Runway SDK(tm). If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.kaleidoscope.dashboard.condition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import com.runwaysdk.constants.Constants;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

public class DateCondition extends DashboardAttributeCondition implements Reloadable
{
  public static final String CONDITION_TYPE = "DATE_CONDITION";

  public static final String START_DATE     = "startDate";

  public static final String END_DATE       = "endDate";

  private String             startDate;

  private String             endDate;

  public DateCondition(String mdAttributeId, String startDate, String endDate)
  {
    super(mdAttributeId);

    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public void restrictQuery(ValueQuery query, Attribute attr)
  {
    if (startDate != null && startDate.length() > 0)
    {
      DashboardGreaterThanOrEqualCondition condition = new DashboardGreaterThanOrEqualCondition(this.getMdAttributeId(), this.startDate);
      condition.restrictQuery(query, attr);
    }

    if (this.endDate != null && endDate.length() > 0)
    {
      DashboardLessThanOrEqualCondition condition = new DashboardLessThanOrEqualCondition(this.getMdAttributeId(), this.endDate);
      condition.restrictQuery(query, attr);
    }
  }

  @Override
  public JSONObject getJSON()
  {
    try
    {
      SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Session.getCurrentLocale());
      String pattern = format.toPattern();
      pattern = pattern.replace("yy", "yyyy");
      pattern = pattern.replace("yyyyyyyy", "yyyy");      
      
      DateFormat source = new SimpleDateFormat(Constants.DATE_FORMAT);
      DateFormat target = new SimpleDateFormat(pattern);
            
      JSONObject object = new JSONObject();
      object.put(TYPE_KEY, CONDITION_TYPE);
      object.put(MD_ATTRIBUTE_KEY, this.getMdAttributeId());

      if (startDate != null && startDate.length() > 0)
      {
        Date date = source.parse(startDate);

        object.put(START_DATE, target.format(date));
      }

      if (endDate != null && endDate.length() > 0)
      {
        Date date = source.parse(endDate);

        object.put(END_DATE, target.format(date));
      }
      return object;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public List<String> getConditionInformation()
  {
    List<String> messages = new LinkedList<String>();

    if (startDate != null && startDate.length() > 0)
    {
      DashboardGreaterThanOrEqualCondition condition = new DashboardGreaterThanOrEqualCondition(this.getMdAttributeId(), this.startDate);

      messages.addAll(condition.getConditionInformation());
    }

    if (this.endDate != null && endDate.length() > 0)
    {
      DashboardLessThanOrEqualCondition condition = new DashboardLessThanOrEqualCondition(this.getMdAttributeId(), this.endDate);

      messages.addAll(condition.getConditionInformation());
    }

    return messages;
  }

}
