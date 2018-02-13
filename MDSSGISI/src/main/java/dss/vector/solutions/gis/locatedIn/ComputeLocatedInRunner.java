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
package dss.vector.solutions.gis.locatedIn;

import java.util.LinkedList;

import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.session.Request;
import com.sun.tools.javac.util.Pair;

public class ComputeLocatedInRunner implements Runnable, Reloadable
{
  private LinkedList<Pair<String, String>> list;

  private LocatedInBuilder                 builder;

  public ComputeLocatedInRunner(LocatedInBuilder builder)
  {
    this.builder = builder;
    this.list = new LinkedList<Pair<String, String>>();
  }

  @Request
  public void run()
  {
    OIterator<ValueObject> it = builder.deriveLocatedIn();

    try
    {
      while (it.hasNext())
      {
        ValueObject valueObject = it.next();

        String parentId = valueObject.getValue(LocatedInBuilder.PARENT_ID);
        String childId = valueObject.getValue(LocatedInBuilder.CHILD_ID);

        Pair<String, String> pair = new Pair<String, String>(parentId, childId);

        list.add(pair);
      }
    }
    finally
    {
      it.close();
    }
  }

  public LinkedList<Pair<String, String>> getList()
  {
    return list;
  }
}
