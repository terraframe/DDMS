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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.Constants;
import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeMomentDAOIF;
import com.runwaysdk.dataaccess.MdAttributeNumberDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.LocalizationFacade;

public abstract class DashboardPrimitiveCondition extends DashboardAttributeCondition implements Reloadable
{
  public static final String COMPARISONVALUE = "value";

  private String             comparisonValue;

  public abstract String getOperation();

  public DashboardPrimitiveCondition(String mdAttributeId, String comparisonValue)
  {
    super(mdAttributeId);

    this.comparisonValue = comparisonValue;
  }

  private String getLocalizedOperation()
  {
    String operation = this.getOperation();

    return LocalizationFacade.getFromBundles(operation);
  }

  public String getComparisonValue()
  {
    return comparisonValue;
  }

  public void setComparisonValue(String comparisonValue)
  {
    this.comparisonValue = comparisonValue;
  }

  public Date getComparisonValueAsDate()
  {
    DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);

    try
    {
      Date date = format.parse(this.getComparisonValue());

      return date;
    }
    catch (ParseException cause)
    {
      throw new ProgrammingErrorException(cause);
    }
  }

  public Boolean getComparisonValueAsBoolean()
  {
    return new Boolean(this.getComparisonValue());
  }

  @Override
  public JSONObject getJSON()
  {
    try
    {
      JSONObject object = new JSONObject();
      object.put(TYPE_KEY, CONDITION_TYPE);
      object.put(MD_ATTRIBUTE_KEY, this.getMdAttributeId());
      object.put(OPERATION_KEY, this.getOperation());
      object.put(VALUE_KEY, this.getComparisonValue());

      return object;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public List<String> getConditionInformation()
  {
    List<String> messages = new LinkedList<String>();
    MdAttributeConcreteDAOIF mdAttribute = MdAttributeDAO.get(this.getMdAttributeId()).getMdAttributeConcrete();

    if (mdAttribute instanceof MdAttributeNumberDAOIF)
    {
      messages.add(this.handleNumberCondition((MdAttributeNumberDAOIF) mdAttribute));
    }
    else if (mdAttribute instanceof MdAttributeBooleanDAOIF)
    {
      messages.add(this.handleBooleanCondition((MdAttributeBooleanDAOIF) mdAttribute));
    }
    else if (mdAttribute instanceof MdAttributeMomentDAOIF)
    {
      messages.add(this.handleMomentCondition((MdAttributeMomentDAOIF) mdAttribute));
    }
    else if (mdAttribute instanceof MdAttributeCharacterDAOIF)
    {
      messages.add(this.handleCharacterCondition((MdAttributeCharacterDAOIF) mdAttribute));
    }

    return messages;
  }

  private String handleCharacterCondition(MdAttributeCharacterDAOIF _mdAttribute)
  {
    Locale locale = LocalizationFacade.getLocale();

    String localizedLabel = _mdAttribute.getDisplayLabel(locale);
    String localizedOperation = this.getLocalizedOperation();

    return this.handleCondition(localizedLabel, localizedOperation, this.getComparisonValue());
  }

  private String handleMomentCondition(MdAttributeMomentDAOIF _mdAttribute)
  {
    Locale locale = LocalizationFacade.getLocale();

    String localizedLabel = _mdAttribute.getDisplayLabel(locale);
    String localizedOperation = this.getLocalizedOperation();

    Date date = this.getComparisonValueAsDate();
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    String localizedValue = format.format(date);

    return this.handleCondition(localizedLabel, localizedOperation, localizedValue);
  }

  private String handleBooleanCondition(MdAttributeBooleanDAOIF _mdAttribute)
  {
    Locale locale = LocalizationFacade.getLocale();

    String label = _mdAttribute.getDisplayLabel(locale);
    String operation = this.getLocalizedOperation();
    String value = this.getComparisonValue();

    return this.handleCondition(label, operation, value);
  }

  private String handleNumberCondition(MdAttributeNumberDAOIF _mdAttribute)
  {
    String value = this.getComparisonValue();

    Locale locale = LocalizationFacade.getLocale();
    NumberFormat format = NumberFormat.getInstance(locale);

    String label = _mdAttribute.getDisplayLabel(locale);
    String operation = this.getLocalizedOperation();
    String localizedValue = format.format(Double.parseDouble(value));

    return this.handleCondition(label, operation, localizedValue);
  }

}
