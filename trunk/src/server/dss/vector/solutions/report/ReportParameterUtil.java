package dss.vector.solutions.report;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.ICascadingParameterGroup;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterDefnBase;
import org.eclipse.birt.report.engine.api.IParameterGroupDefn;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.json.JSONArray;
import org.json.JSONException;

import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.report.serialization.CascadingParameterGroupSerializer;
import dss.vector.solutions.report.serialization.ParameterGroupSerializer;
import dss.vector.solutions.report.serialization.ScalarParameterSerializer;
import dss.vector.solutions.util.BirtEngine;

public class ReportParameterUtil implements Reloadable
{
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
          DateFormat df = new SimpleDateFormat(ScalarParameterSerializer.DATE_FORMAT);
          Date date = df.parse(value);

          return new java.sql.Date(date.getTime());
        }
        else if (param.getDataType() == IScalarParameterDefn.TYPE_DATE_TIME)
        {
          DateFormat df = new SimpleDateFormat(ScalarParameterSerializer.DATE_TIME_FORMAT);
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
          DateFormat df = new SimpleDateFormat(ScalarParameterSerializer.TIME_FORMAT);
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

  /**
   * Reads the BIRT report parameters from the report document.
   * 
   * @param stream The rptdesign file as a stream.
   * @return A JSONArray of report parameters, read from the rptdesign file stream.
   * @throws BirtException
   * @throws JSONException
   */
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

        if (param.getParameterType() == IParameterDefnBase.SCALAR_PARAMETER)
        {
          array.put(new ScalarParameterSerializer(task, design, (IScalarParameterDefn) param).toJSON());
        }
        else if (param.getParameterType() == IParameterDefnBase.CASCADING_PARAMETER_GROUP)
        {
          array.put(new CascadingParameterGroupSerializer(task, design, (ICascadingParameterGroup) param).toJSON());
        }
        else if (param.getParameterType() == IParameterDefnBase.PARAMETER_GROUP)
        {
          array.put(new ParameterGroupSerializer(task, design, (IParameterGroupDefn) param).toJSON());
        }
        else
        {
          throw new UnsupportedOperationException();
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
