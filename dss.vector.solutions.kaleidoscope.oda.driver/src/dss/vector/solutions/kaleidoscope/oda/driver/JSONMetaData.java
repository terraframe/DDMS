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

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONMetaData implements IResultSetMetaData
{

  private JSONArray metadata;

  public JSONMetaData(JSONArray metadata)
  {
    this.metadata = metadata;
  }

  private JSONObject getColumn(int index) throws JSONException
  {
    return this.metadata.getJSONObject(index);
  }

  @Override
  public int getColumnCount() throws OdaException
  {
    return this.metadata.length();
  }

  @Override
  public int getColumnDisplayLength(int index) throws OdaException
  {
    try
    {
      return getColumn(index).getInt("size");
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public String getColumnLabel(int index) throws OdaException
  {
    try
    {
      return getColumn(index).getString("label");
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public String getColumnName(int index) throws OdaException
  {
    try
    {
      return getColumn(index).getString("name");
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public int getColumnType(int index) throws OdaException
  {
    try
    {
      return getColumn(index).getInt("type");
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public String getColumnTypeName(int index) throws OdaException
  {
    int typeCode = this.getColumnType(index);

    return Driver.getNativeDataTypeName(typeCode);
  }

  @Override
  public int getPrecision(int index) throws OdaException
  {
    try
    {
      return getColumn(index).getInt("precision");
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public int getScale(int index) throws OdaException
  {
    try
    {
      return getColumn(index).getInt("scale");
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

  @Override
  public int isNullable(int index) throws OdaException
  {
    try
    {
      return getColumn(index).getInt("required");
    }
    catch (JSONException e)
    {
      throw new OdaException(e);
    }
  }

}
