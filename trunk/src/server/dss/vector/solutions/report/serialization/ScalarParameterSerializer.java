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
package dss.vector.solutions.report.serialization;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterSelectionChoice;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.eclipse.birt.report.model.api.ScalarParameterHandle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScalarParameterSerializer extends ParameterSerializer
{
  /**
   * This value is read by the client javascript to know what type the parameter is so it knows how to read it.
   */
  public static final String PARAMETER_TYPE_SCALAR = "Scalar";
  
  public static final String OPTIONS = "options";
  
  /**
   * String value of true boolean parameters
   */
  public static final String TRUE             = "True";

  /**
   * String value of false boolean parameters
   */
  public static final String FALSE            = "False";
  
  /**
   * Date format
   */
  public static final String DATE_FORMAT      = "yyyy-MM-dd";

  /**
   * Date time format
   */
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

  /**
   * Time format
   */
  public static final String TIME_FORMAT      = "HH:mm:ss";

  public static final String TEXT_BOX         = "Text Box";

  public static final String LIST_BOX         = "List Box";

  public static final String RADIO_BUTTON     = "Radio Button";

  public static final String CHECK_BOX        = "Check Box";
  
  private IScalarParameterDefn scalar;
  
  public ScalarParameterSerializer(IGetParameterDefinitionTask task, IReportRunnable design, IScalarParameterDefn scalar)
  {
    super(task, design);
    
    this.scalar = scalar;
  }

  /**
   * @return the scalar
   */
  public IScalarParameterDefn getScalar()
  {
    return scalar;
  }

  /**
   * @param scalar the scalar to set
   */
  public void setScalar(IScalarParameterDefn scalar)
  {
    this.scalar = scalar;
  }
  
  public JSONArray optionsToJSON(Object defaultValue, String convertedDefaultValue) throws JSONException
  {
    JSONArray options = new JSONArray();
    
    @SuppressWarnings("unchecked")
    Collection<IParameterSelectionChoice> collection = this.getTask().getSelectionList(scalar.getName());
    if (collection != null)
    {
      if (scalar.getDataType() != IScalarParameterDefn.TYPE_BOOLEAN)
      {
        if (defaultValue != null)
        {
          if (defaultValue.getClass().isArray())
          {
            Object[] values = (Object[]) defaultValue;

            for (int i = 0; i < values.length; i++)
            {
              String defaultValueString = this.getDefaultValue(scalar, values[i]);

              if (!this.containsDefaultValue(collection, defaultValue))
              {
                JSONObject option = new JSONObject();
                option.put("label", defaultValueString);
                option.put("value", defaultValueString);
                
                options.put(option);
              }
            }
          }
          else if (!this.containsDefaultValue(collection, defaultValue))
          {
            JSONObject option = new JSONObject();
            option.put("label", convertedDefaultValue);
            option.put("value", convertedDefaultValue);

            options.put(option);
          }
        }
      }

      for (IParameterSelectionChoice selectionItem : collection)
      {
        String label = selectionItem.getLabel();
        Object value = selectionItem.getValue();

        JSONObject option = new JSONObject();
        option.put("label", label);
        option.put("value", value);

        options.put(option);
      }
    }
    
    return options;
  }
  
  public JSONObject toJSON() throws JSONException
  {
    Object defaultValue = this.getTask().getDefaultValue(scalar);
    String convertedValue = this.getDefaultValue(scalar, defaultValue);
    
    JSONObject json = new JSONObject();
    json.put(ParameterSerializer.PARAMETER_TYPE, PARAMETER_TYPE_SCALAR);
    
    json.put("name", scalar.getName());
    json.put("helpText", scalar.getHelpText());
    json.put("displayName", scalar.getDisplayName());
    json.put("displayFormat", scalar.getDisplayFormat());
    json.put("isHidden", scalar.isHidden());
    json.put("isRequired", scalar.isRequired());
    json.put("isValueConcealed", scalar.isValueConcealed());
    json.put("type", scalar.getControlType());
    json.put("dataType", scalar.getDataType());
    json.put("defaultValue", convertedValue);
    json.put("promptText", scalar.getPromptText());
    json.put("scalarParameterType", scalar.getScalarParameterType());
    json.put("allowNewValues", scalar.allowNewValues());

    ScalarParameterHandle parameterHandle = (ScalarParameterHandle) scalar.getHandle();
    String valueExpr = parameterHandle.getValueExpr();

    json.put("valueExpr", valueExpr);
    
    if (scalar.getControlType() != IScalarParameterDefn.TEXT_BOX)
    {
      json.put(OPTIONS, this.optionsToJSON(defaultValue, convertedValue));
    }
    
    return json;
  }
  
  protected String getDefaultValue(IScalarParameterDefn scalar, Object value)
  {
    if (value != null && value.getClass().isArray())
    {
      Object[] values = (Object[]) value;
      JSONArray array = new JSONArray();

      for (int i = 0; i < values.length; i++)
      {
        array.put(this.getDefaultValue(scalar, values[i]));
      }

      return array.toString();
    }

    if (value instanceof Boolean)
    {
      if (value.equals(true))
      {
        return TRUE;
      }

      return FALSE;
    }
    else if (value instanceof Date)
    {
      if (scalar.getDataType() == IScalarParameterDefn.TYPE_DATE)
      {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format((Date) value);
      }
      else if (scalar.getDataType() == IScalarParameterDefn.TYPE_DATE_TIME)
      {
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        return df.format((Date) value);
      }
      else if (scalar.getDataType() == IScalarParameterDefn.TYPE_TIME)
      {
        DateFormat df = new SimpleDateFormat(TIME_FORMAT);
        return df.format((Date) value);
      }
    }

    if (value != null)
    {
      return value.toString();
    }

    return null;
  }
  
  protected boolean containsDefaultValue(Collection<IParameterSelectionChoice> collection, Object defaultValue)
  {
    for (IParameterSelectionChoice selectionItem : collection)
    {
      Object value = selectionItem.getValue();

      if (value != null && value.equals(defaultValue))
      {
        return true;
      }
    }

    return false;
  }
}
