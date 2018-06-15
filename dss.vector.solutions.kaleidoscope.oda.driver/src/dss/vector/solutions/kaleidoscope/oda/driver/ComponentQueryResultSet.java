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
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

import com.runwaysdk.constants.MdAttributeDateTimeUtil;
import com.runwaysdk.constants.MdAttributeDateUtil;
import com.runwaysdk.constants.MdAttributeDoubleUtil;
import com.runwaysdk.constants.MdAttributeIntegerUtil;
import com.runwaysdk.constants.MdAttributeTimeUtil;

import dss.vector.solutions.kaleidoscope.report.RemoteQueryIF;
import dss.vector.solutions.kaleidoscope.report.RemoteResultIF;

public class ComponentQueryResultSet implements IResultSet
{
  /**
   * Query containing the results and the metadata of the results
   */
  private RemoteQueryIF                      query;

  /**
   * Result set of the query
   */
  private List<? extends RemoteResultIF>     resultSet;

  /**
   * Result set iterator
   */
  private Iterator<? extends RemoteResultIF> iterator;

  /**
   * The current row
   */
  private RemoteResultIF                     current;

  /**
   * Row number of the current row
   */
  private int                                rowNumber;

  private List<String>                       attributeNames;
  
  private Boolean                            wasNull;

  public ComponentQueryResultSet(RemoteQueryIF query)
  {
    this.query = query;
    this.resultSet = this.query.getResultSet();
    this.iterator = this.resultSet.iterator();
    this.attributeNames = new ArrayList<String>(this.query.getAttributeNames());

    this.current = null;
    this.rowNumber = -1;
    this.wasNull = false;
  }

  private String getAttributeName(int index)
  {
    return this.attributeNames.get( ( index - 1 ));
  }

  @Override
  public void close() throws OdaException
  {
    this.query.clearResultSet();
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
    String val = this.current.getValue(columnName);
    
    if (val == null)
    {
      wasNull = true;
      return null;
    }
    else
    {
      wasNull = false;
      return new BigDecimal(val);
    }
  }

  @Override
  public IBlob getBlob(int index) throws OdaException
  {
    wasNull = true;
    return null;
  }

  @Override
  public IBlob getBlob(String columnName) throws OdaException
  {
    wasNull = true;
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
    String val = this.current.getValue(columnName);
    
    if (val == null)
    {
      wasNull = true;
      return false;
    }
    else
    {
      wasNull = false;
      return new Boolean(val);
    }
  }

  @Override
  public IClob getClob(int index) throws OdaException
  {
    wasNull = true;
    return null;
  }

  @Override
  public IClob getClob(String columnName) throws OdaException
  {
    wasNull = true;
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
    java.util.Date value = MdAttributeDateUtil.getTypeSafeValue(this.current.getValue(columnName));

    if (value != null)
    {
      wasNull = false;
      return new Date(value.getTime());
    }

    wasNull = true;
    return null;
  }

  @Override
  public double getDouble(int index) throws OdaException
  {
    return this.getDouble(this.getAttributeName(index));
  }

  @Override
  public double getDouble(String columnName) throws OdaException
  {
    Double value = MdAttributeDoubleUtil.getTypeSafeValue(this.current.getValue(columnName));

    if (value == null)
    {
      wasNull = true;
      return 0d;
    }
    else
    {
      wasNull = false;
      return value.doubleValue();
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
    Integer value = MdAttributeIntegerUtil.getTypeSafeValue(this.current.getValue(columnName));

    if (value == null)
    {
      wasNull = true;
      return Types.NULL;
    }
    else
    {
      wasNull = false;
      return value.intValue();
    }
  }

  @Override
  public IResultSetMetaData getMetaData() throws OdaException
  {
    return new ComponentQueryResultSetMetadaData(this.query);
  }

  @Override
  public Object getObject(int index) throws OdaException
  {
    return this.getObject(this.getAttributeName(index));
  }

  @Override
  public Object getObject(String columnName) throws OdaException
  {
    String value = this.current.getValue(columnName);
    
    if (value == null)
    {
      wasNull = true;
      return null;
    }
    else
    {
      wasNull = false;
      return value;
    }
  }

  @Override
  public int getRow() throws OdaException
  {
    return ( this.rowNumber + 1 );
  }

  @Override
  public String getString(int index) throws OdaException
  {
    return this.getString(this.getAttributeName(index));
  }

  @Override
  public String getString(String columnName) throws OdaException
  {
    String value = this.current.getValue(columnName);
    
    if (value == null)
    {
      wasNull = true;
      return null;
    }
    else
    {
      wasNull = false;
      return value;
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
    java.util.Date value = MdAttributeTimeUtil.getTypeSafeValue(this.current.getValue(columnName));

    if (value != null)
    {
      wasNull = false;
      return new Time(value.getTime());
    }

    wasNull = true;
    return null;
  }

  @Override
  public Timestamp getTimestamp(int index) throws OdaException
  {
    return this.getTimestamp(this.getAttributeName(index));
  }

  @Override
  public Timestamp getTimestamp(String columnName) throws OdaException
  {
    java.util.Date value = MdAttributeDateTimeUtil.getTypeSafeValue(this.current.getValue(columnName));

    if (value != null)
    {
      wasNull = false;
      return new Timestamp(value.getTime());
    }

    wasNull = true;
    return null;
  }

  @Override
  public boolean next() throws OdaException
  {
    wasNull = false;
    
    if (this.iterator.hasNext())
    {
      this.current = this.iterator.next();
      this.rowNumber++;

      return true;
    }

    return false;
  }

  @Override
  public void setMaxRows(int index) throws OdaException
  {
    // Do nothing
  }

  @Override
  public boolean wasNull() throws OdaException
  {
    return wasNull;
  }

}
