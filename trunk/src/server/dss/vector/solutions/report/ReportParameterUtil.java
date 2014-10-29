package dss.vector.solutions.report;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterDefnBase;
import org.eclipse.birt.report.engine.api.IParameterGroupDefn;
import org.eclipse.birt.report.engine.api.IParameterSelectionChoice;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.eclipse.birt.report.model.api.CascadingParameterGroupHandle;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ScalarParameterHandle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.BirtEngine;

public class ReportParameterUtil implements Reloadable
{
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

  /**
   * String value of true boolean parameters
   */
  public static final String TRUE             = "True";

  /**
   * String value of false boolean parameters
   */
  public static final String FALSE            = "False";

  public Map<String, Object> convertParameters(InputStream stream, Map<String, String> map) throws BirtException
  {
    IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());
    IReportRunnable design = engine.openReportDesign(stream);

    return this.convertParameters(design, map);
  }

  @SuppressWarnings("unchecked")
  public Map<String, Object> convertParameters(IReportRunnable design, Map<String, String> map) throws BirtException
  {
    IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());

    IGetParameterDefinitionTask task = engine.createGetParameterDefinitionTask(design);

    try
    {
      Map<String, Object> parameters = new HashMap<String, Object>(map);
      Collection<IParameterDefnBase> params = (Collection<IParameterDefnBase>) task.getParameterDefns(true);

      for (Entry<String, String> entry : map.entrySet())
      {
        for (IParameterDefnBase param : params)
        {
          if (param instanceof IParameterGroupDefn)
          {
            IParameterGroupDefn group = (IParameterGroupDefn) param;

            ArrayList<IScalarParameterDefn> contents = group.getContents();

            for (IScalarParameterDefn scalar : contents)
            {
              if (entry.getKey().equals(scalar.getName()))
              {
                boolean multivalue = scalar.getScalarParameterType().equals("multi-value");

                parameters.put(entry.getKey(), this.parse(scalar, entry.getValue(), multivalue));
              }
            }
          }
          else if (param instanceof IScalarParameterDefn)
          {
            if (entry.getKey().equals(param.getName()))
            {
              IScalarParameterDefn definition = (IScalarParameterDefn) param;
              boolean multivalue = definition.getScalarParameterType().equals("multi-value");

              parameters.put(entry.getKey(), this.parse(definition, entry.getValue(), multivalue));
            }
          }
        }
      }

      return parameters;
    }
    finally
    {
      task.close();
    }
  }

  private Object parse(IScalarParameterDefn param, String value, boolean multivalue)
  {
    try
    {
      if (multivalue)
      {
        List<Object> values = new LinkedList<Object>();
        JSONArray array = new JSONArray(value);

        for (int i = 0; i < array.length(); i++)
        {
          String sub = array.getString(i);
          values.add(this.parse(param, sub, false));
        }

        return values.toArray(new Object[values.size()]);
      }
      else
      {
        if (param.getDataType() == IScalarParameterDefn.TYPE_BOOLEAN)
        {
          return new Boolean(value);
        }
        else if (param.getDataType() == IScalarParameterDefn.TYPE_DATE)
        {
          DateFormat df = new SimpleDateFormat(DATE_FORMAT);
          Date date = df.parse(value);

          return new java.sql.Date(date.getTime());
        }
        else if (param.getDataType() == IScalarParameterDefn.TYPE_DATE_TIME)
        {
          DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
          Date date = df.parse(value);

          return new java.sql.Timestamp(date.getTime());
        }
        else if (param.getDataType() == IScalarParameterDefn.TYPE_DECIMAL)
        {
          return new Double(value);
        }
        else if (param.getDataType() == IScalarParameterDefn.TYPE_FLOAT)
        {
          return new Float(value);
        }
        else if (param.getDataType() == IScalarParameterDefn.TYPE_INTEGER)
        {
          /*
           *  On drill though reports all numbers come in as a Double
           */
          Double number = new Double(value);

          if ( ( (int) Math.ceil(number) ) == number.intValue())
          {
            return new Integer(number.intValue());
          }

          ReportParameterParseException e = new ReportParameterParseException();
          e.setParameterName(param.getName());
          e.setParameterValue(value);

          throw e;
        }
        else if (param.getDataType() == IScalarParameterDefn.TYPE_TIME)
        {
          DateFormat df = new SimpleDateFormat(TIME_FORMAT);
          Date date = df.parse(value);

          return new java.sql.Time(date.getTime());
        }

        return value;
      }
    }
    catch (Exception e)
    {
      ReportParameterParseException ex = new ReportParameterParseException(e);
      ex.setParameterName(param.getName());
      ex.setParameterValue(value);

      throw ex;
    }
  }

  @SuppressWarnings("unchecked")
  public JSONArray getParameterDefinitions(InputStream stream) throws BirtException, JSONException
  {
    IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());

    // Open a report design
    IReportRunnable design = engine.openReportDesign(stream);

    IGetParameterDefinitionTask task = engine.createGetParameterDefinitionTask(design);

    try
    {
      JSONArray array = new JSONArray();

      Collection<IParameterDefnBase> params = (Collection<IParameterDefnBase>) task.getParameterDefns(true);

      Iterator<IParameterDefnBase> iter = params.iterator();
      while (iter.hasNext())
      {
        IParameterDefnBase param = iter.next();

        if (param instanceof IParameterGroupDefn)
        {
          IParameterGroupDefn group = (IParameterGroupDefn) param;

          ArrayList<IScalarParameterDefn> contents = group.getContents();

          Iterator<IScalarParameterDefn> i2 = contents.iterator();

          while (i2.hasNext())
          {
            IScalarParameterDefn scalar = i2.next();

            array.put(this.getParameterDetails(task, scalar, design, group));
          }
        }
        else
        {
          IScalarParameterDefn scalar = (IScalarParameterDefn) param;
          array.put(this.getParameterDetails(task, scalar, design, null));
        }
      }

      return array;
    }
    finally
    {
      task.close();
    }
  }

  @SuppressWarnings("unchecked")
  private JSONObject getParameterDetails(IGetParameterDefinitionTask task, IScalarParameterDefn scalar, IReportRunnable report, IParameterGroupDefn group) throws JSONException
  {
    Object defaultValue = task.getDefaultValue(scalar);
    String convertedValue = this.getDefaultValue(scalar, defaultValue);

    JSONObject parameter = new JSONObject();
    parameter.put("parameterGroup", ( group == null ? "none" : group.getName() ));
    parameter.put("name", scalar.getName());
    parameter.put("helpText", scalar.getHelpText());
    parameter.put("displayName", scalar.getDisplayName());
    parameter.put("displayFormat", scalar.getDisplayFormat());
    parameter.put("isHidden", scalar.isHidden());
    parameter.put("isRequired", scalar.isRequired());
    parameter.put("isValueConcealed", scalar.isValueConcealed());
    parameter.put("type", scalar.getControlType());
    parameter.put("dataType", scalar.getDataType());
    parameter.put("defaultValue", convertedValue);
    parameter.put("promptText", scalar.getPromptText());
    parameter.put("scalarParameterType", scalar.getScalarParameterType());
    parameter.put("allowNewValues", scalar.allowNewValues());

    ScalarParameterHandle parameterHandle = (ScalarParameterHandle) scalar.getHandle();
    String valueExpr = parameterHandle.getValueExpr();

    parameter.put("valueExpr", valueExpr);

    if (scalar.getControlType() != IScalarParameterDefn.TEXT_BOX)
    {
      DesignElementHandle handle = parameterHandle.getContainer();

      if (handle instanceof CascadingParameterGroupHandle)
      {
        String groupName = handle.getName();
        Object[] values = new Object[] {};

        parameter.put("options", getSelectionListForCascadingGroup(task, groupName, values));
      }
      else
      {
        Collection<IParameterSelectionChoice> collection = task.getSelectionList(scalar.getName());

        if (collection != null)
        {

          JSONArray options = new JSONArray();

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

                  if (!this.containsDefaultValue(collection, defaultValueString))
                  {
                    JSONObject option = new JSONObject();
                    option.put("label", defaultValueString);
                    option.put("value", defaultValueString);

                    options.put(option);
                  }
                }
              }
              else if (!this.containsDefaultValue(collection, convertedValue))
              {
                JSONObject option = new JSONObject();
                option.put("label", convertedValue);
                option.put("value", convertedValue);

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

          parameter.put("options", options);
        }
      }
    }

    return parameter;
  }

  private boolean containsDefaultValue(Collection<IParameterSelectionChoice> collection, String defaultValue)
  {
    for (IParameterSelectionChoice selectionItem : collection)
    {
      Object value = selectionItem.getValue();

      if (value.equals(defaultValue))
      {
        return true;
      }
    }

    return false;
  }

  public String getDefaultValue(IScalarParameterDefn scalar, Object value)
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

  @SuppressWarnings("unchecked")
  public JSONArray getSelectionListForCascadingGroup(IGetParameterDefinitionTask task, String groupName, Object[] values) throws JSONException
  {
    Collection<IParameterSelectionChoice> sList = task.getSelectionListForCascadingGroup(groupName, values);

    JSONArray array = new JSONArray();

    for (IParameterSelectionChoice sI : sList)
    {
      String label = sI.getLabel();
      Object value = sI.getValue();

      Object[] selections = Arrays.copyOf(values, ( values.length + 1 ));

      JSONObject object = new JSONObject();
      object.put("label", label);
      object.put("value", value);
      object.put("children", this.getSelectionListForCascadingGroup(task, groupName, selections));

      array.put(object);
    }

    return array;
  }

  @SuppressWarnings("unchecked")
  public Boolean hasParameterDefinitions(InputStream stream) throws BirtException, JSONException
  {
    IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());

    // Open a report design
    IReportRunnable design = engine.openReportDesign(stream);

    IGetParameterDefinitionTask task = engine.createGetParameterDefinitionTask(design);

    try
    {
      Collection<IParameterDefnBase> params = (Collection<IParameterDefnBase>) task.getParameterDefns(true);

      return ( params.size() > 0 );
    }
    finally
    {
      task.close();
    }
  }

}
