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

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.kaleidoscope.report.RemoteQuery;
import dss.vector.solutions.kaleidoscope.report.RemoteQueryIF;

public class BlockQueryResultSet implements IResultSet
{
  private ClientRequestIF         request;

  private String                  queryId;

  private String                  context;

  private int                     pageSize;

  private int                     currentPage;

  private ComponentQueryResultSet query;

  private int                     maxPage;

  public BlockQueryResultSet(ClientRequestIF request, String queryId, String context)
  {
    this.request = request;
    this.queryId = queryId;
    this.context = context;

    this.pageSize = 40000;
    this.currentPage = 0;
    this.maxPage = 0;
  }

  private synchronized ComponentQueryResultSet getQuery()
  {
    if (this.query == null)
    {
//      this.maxPage = ReportItemDTO.getPageCount(this.request, this.queryId, this.context, this.pageSize);
      this.maxPage = QueryFacade.getPageCount(this.request, this.queryId, this.context, this.pageSize);

      this.nextQuery();
    }

    return this.query;
  }

  private synchronized void nextQuery()
  {
    this.currentPage = this.currentPage + 1;

    String json = QueryFacade.getValuesForReporting(this.request, this.queryId, this.context, this.pageSize, this.currentPage);
    RemoteQueryIF result = RemoteQuery.deserialize(json);

    this.query = new ComponentQueryResultSet(result);
  }

  @Override
  public void close() throws OdaException
  {
    this.getQuery().close();
  }

  @Override
  public int findColumn(String columnName) throws OdaException
  {
    return this.getQuery().findColumn(columnName);
  }

  @Override
  public BigDecimal getBigDecimal(int index) throws OdaException
  {
    return this.getQuery().getBigDecimal(index);
  }

  @Override
  public BigDecimal getBigDecimal(String columnName) throws OdaException
  {
    return this.getQuery().getBigDecimal(columnName);
  }

  @Override
  public IBlob getBlob(int index) throws OdaException
  {
    return this.getQuery().getBlob(index);
  }

  @Override
  public IBlob getBlob(String columnName) throws OdaException
  {
    return this.getQuery().getBlob(columnName);
  }

  @Override
  public boolean getBoolean(int index) throws OdaException
  {
    return this.getQuery().getBoolean(index);
  }

  @Override
  public boolean getBoolean(String columnName) throws OdaException
  {
    return this.getQuery().getBoolean(columnName);
  }

  @Override
  public IClob getClob(int index) throws OdaException
  {
    return this.getQuery().getClob(index);
  }

  @Override
  public IClob getClob(String columnName) throws OdaException
  {
    return this.getQuery().getClob(columnName);
  }

  @Override
  public Date getDate(int index) throws OdaException
  {
    return this.getQuery().getDate(index);
  }

  @Override
  public Date getDate(String columnName) throws OdaException
  {
    return this.getQuery().getDate(columnName);
  }

  @Override
  public double getDouble(int index) throws OdaException
  {
    return this.getQuery().getDouble(index);
  }

  @Override
  public double getDouble(String columnName) throws OdaException
  {
    return this.getQuery().getDouble(columnName);
  }

  @Override
  public int getInt(int index) throws OdaException
  {
    return this.getQuery().getInt(index);
  }

  @Override
  public int getInt(String columnName) throws OdaException
  {
    return this.getQuery().getInt(columnName);
  }

  @Override
  public IResultSetMetaData getMetaData() throws OdaException
  {
    return this.getQuery().getMetaData();
  }

  @Override
  public Object getObject(int index) throws OdaException
  {
    return this.getQuery().getObject(index);
  }

  @Override
  public Object getObject(String columnName) throws OdaException
  {
    return this.getQuery().getObject(columnName);
  }

  @Override
  public int getRow() throws OdaException
  {
    return ( ( this.currentPage - 1 ) * this.pageSize ) + this.getQuery().getRow();
  }

  @Override
  public String getString(int index) throws OdaException
  {
    return this.getQuery().getString(index);
  }

  @Override
  public String getString(String columnName) throws OdaException
  {
    return this.getQuery().getString(columnName);
  }

  @Override
  public Time getTime(int index) throws OdaException
  {
    return this.getQuery().getTime(index);
  }

  @Override
  public Time getTime(String columnName) throws OdaException
  {
    return this.getQuery().getTime(columnName);
  }

  @Override
  public Timestamp getTimestamp(int index) throws OdaException
  {
    return this.getQuery().getTimestamp(index);
  }

  @Override
  public Timestamp getTimestamp(String columnName) throws OdaException
  {
    return this.getQuery().getTimestamp(columnName);
  }

  @Override
  public boolean next() throws OdaException
  {
    if (this.getQuery().next())
    {
      return true;
    }

    if (this.currentPage < this.maxPage)
    {
      this.nextQuery();

      return this.getQuery().next();
    }

    return false;
  }

  @Override
  public void setMaxRows(int index) throws OdaException
  {
    this.getQuery().setMaxRows(index);
  }

  @Override
  public boolean wasNull() throws OdaException
  {
    return this.getQuery().wasNull();
  }
}
