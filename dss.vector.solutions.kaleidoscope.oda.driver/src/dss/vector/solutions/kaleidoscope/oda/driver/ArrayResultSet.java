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
package dss.vector.solutions.kaleidoscope.oda.driver;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.MdAttributeDateTimeUtil;
import com.runwaysdk.constants.MdAttributeDateUtil;
import com.runwaysdk.constants.MdAttributeDoubleUtil;
import com.runwaysdk.constants.MdAttributeIntegerUtil;
import com.runwaysdk.constants.MdAttributeTimeUtil;

public class ArrayResultSet implements IResultSet
{

  private JSONObject   query;

  /**
   * The current row
   */
  private int          current;

  private List<String> attributeNames;

  public ArrayResultSet(String results)
  {
    try
    {
      this.query = new JSONObject(results);
      this.current = -1;
      this.attributeNames = new LinkedList<String>();

      if (this.query.has("attributeNames"))
      {
        JSONArray names = this.query.getJSONArray("attributeNames");

        for (int i = 0; i < names.length(); i++)
        {
          this.attributeNames.add(names.getString(i));
        }
      }
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }

  private String getAttributeName(int index)
  {
    return this.attributeNames.get( ( index - 1 ));
  }

  private JSONArray getResults() throws JSONException
  {
    return this.query.getJSONArray("results");
  }

  private JSONObject getCurrent()
  {
    try
    {
      if (this.query != null)
      {
        return this.getResults().getJSONObject(this.current);
      }
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }

    return null;
  }

  @Override
  public void close() throws OdaException
  {
    this.query = null;
    this.current = -1;
  }

  @Override
  public int findColumn(String columnName) throws OdaException
  {
    return this.attributeNames.indexOf(columnName);
  }

  @Override
  public BigDecimal getBigDecimal(int index) throws OdaException
  {
    return this.getBigDecimal(this.getAttributeName(index));
  }

  @Override
  public BigDecimal getBigDecimal(String columnName) throws OdaException
  {
    try
    {
      return new BigDecimal(getCurrent().getString(columnName));
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public IBlob getBlob(int index) throws OdaException
  {
    return null;
  }

  @Override
  public IBlob getBlob(String columnName) throws OdaException
  {
    return null;
  }

  @Override
  public boolean getBoolean(int index) throws OdaException
  {
    return this.getBoolean(this.getAttributeName(index));
  }

  @Override
  public boolean getBoolean(String columnName) throws OdaException
  {
    try
    {
      return new Boolean(getCurrent().getString(columnName));
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public IClob getClob(int index) throws OdaException
  {
    return null;
  }

  @Override
  public IClob getClob(String columnName) throws OdaException
  {
    return null;
  }

  @Override
  public Date getDate(int index) throws OdaException
  {
    return this.getDate(this.getAttributeName(index));
  }

  @Override
  public Date getDate(String columnName) throws OdaException
  {
    try
    {
      return new Date(MdAttributeDateUtil.getTypeSafeValue(getCurrent().getString(columnName)).getTime());
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public double getDouble(int index) throws OdaException
  {
    return this.getDouble(this.getAttributeName(index));
  }

  @Override
  public double getDouble(String columnName) throws OdaException
  {
    try
    {
      Double value = MdAttributeDoubleUtil.getTypeSafeValue(getCurrent().getString(columnName));

      return ( value != null ? value.doubleValue() : 0D );
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public int getInt(int index) throws OdaException
  {
    return this.getInt(this.getAttributeName(index));
  }

  @Override
  public int getInt(String columnName) throws OdaException
  {
    try
    {
      Integer value = MdAttributeIntegerUtil.getTypeSafeValue(getCurrent().getString(columnName));

      return ( value != null ? value.intValue() : 0 );
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public IResultSetMetaData getMetaData() throws OdaException
  {
    try
    {
      return new JSONMetaData(this.query.getJSONArray("metadata"));
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public Object getObject(int index) throws OdaException
  {
    return this.getObject(this.getAttributeName(index));
  }

  @Override
  public Object getObject(String columnName) throws OdaException
  {
    try
    {
      return getCurrent().get(columnName);
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public int getRow() throws OdaException
  {
    return ( this.current + 1 );
  }

  @Override
  public String getString(int index) throws OdaException
  {
    return this.getString(this.getAttributeName(index));
  }

  @Override
  public String getString(String columnName) throws OdaException
  {
    try
    {
      return getCurrent().getString(columnName);
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public Time getTime(int index) throws OdaException
  {
    return this.getTime(this.getAttributeName(index));
  }

  @Override
  public Time getTime(String columnName) throws OdaException
  {
    try
    {
      return new Time(MdAttributeTimeUtil.getTypeSafeValue(getCurrent().getString(columnName)).getTime());
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }

  }

  @Override
  public Timestamp getTimestamp(int index) throws OdaException
  {
    return this.getTimestamp(this.getAttributeName(index));
  }

  @Override
  public Timestamp getTimestamp(String columnName) throws OdaException
  {
    try
    {
      return new Timestamp(MdAttributeDateTimeUtil.getTypeSafeValue(getCurrent().getString(columnName)).getTime());
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }

  }

  @Override
  public boolean next() throws OdaException
  {

    try
    {
      this.current++;
      return ( this.getResults().length() > current );
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public void setMaxRows(int index) throws OdaException
  {
    // Do nothing
  }

  @Override
  public boolean wasNull() throws OdaException
  {
    return ( getCurrent() == null );
  }

}
