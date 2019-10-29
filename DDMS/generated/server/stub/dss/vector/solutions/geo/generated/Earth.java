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
package dss.vector.solutions.geo.generated;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.DeleteEarthException;
import dss.vector.solutions.geo.DuplicateEarthException;

public class Earth extends EarthBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236717064787L;

  public Earth()
  {
    super();
  }

  /**
   * Applies the Earth instance, but throws an exception
   * if the instance is new.
   *
   * @throws DuplicateEarthException if the instance is new.
   */
  @Override
  public void apply()
  {
    if(isNew())
    {
      MdBusiness earthMd = MdBusiness.getMdBusiness(Earth.CLASS);

      String error = "Cannot define more than one instance of Earth.";
      DuplicateEarthException ex = new DuplicateEarthException(error);
      ex.setEarthName(earthMd.getDisplayLabel().getValue());
      throw ex;
    }

    super.apply();
  }

  /**
   * Delete method that always throws an exception. Earth cannot be deleted.
   */
  @Override
  public void delete()
  {
    MdBusiness earthMd = MdBusiness.getMdBusiness(Earth.CLASS);

    String error = "Cannot delete the Earth instance.";
    DeleteEarthException ex = new DeleteEarthException(error);
    ex.setEarthName(earthMd.getDisplayLabel().getValue());
    throw ex;
  }

  /**
   * Returns the one and only instance of Earth.
   *
   * @return
   */
  public static Earth getEarthInstance()
  {
    QueryFactory f = new QueryFactory();
    EarthQuery q = new EarthQuery(f);

    OIterator<? extends Earth> iter = q.getIterator();
    try
    {
      while(iter.hasNext())
      {
        return iter.next(); // There will always be one.
      }

      return null;
    }
    finally
    {
      iter.close();
    }
  }

}
