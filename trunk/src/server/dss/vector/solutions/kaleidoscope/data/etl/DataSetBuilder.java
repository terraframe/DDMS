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
package dss.vector.solutions.kaleidoscope.data.etl;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;

public class DataSetBuilder implements DataSetBuilderIF, Reloadable
{
  private JSONObject    configuration;

  private SourceContext source;

  private TargetContext target;

  public DataSetBuilder(String _configuration)
  {
    try
    {
      this.configuration = new JSONObject(_configuration);

      this.source = new SourceContext();
      this.target = new TargetContext();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public DataSetBuilder(JSONObject _configuration)
  {
    this.configuration = _configuration;
    this.source = new SourceContext();
    this.target = new TargetContext();
  }

  @Override
  @Transaction
  @AbortIfProblem
  public void build()
  {
    new SourceBuilder(this.configuration, this.source).build();

    new TargetBuilder(this.configuration, this.source, this.target).build();

    this.source.persist();
    this.target.persist();
  }

  @Override
  public SourceContextIF getSourceContext()
  {
    return this.source;
  }

  @Override
  public TargetContextIF getTargetContext()
  {
    return this.target;
  }
}
