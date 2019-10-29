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
package dss.vector.solutions.synchronization;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;

public class ImportLogView extends ImportLogViewBase implements Reloadable
{
  private static final long serialVersionUID = -505154499;
  
  public ImportLogView()
  {
    super();
  }
  
  public static ImportLogViewQuery getQuery(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    ImportLogViewQuery query = new ImportLogViewQuery(new QueryFactory(), sortAttribute, isAscending, pageSize, pageNumber);
    return query;
  }
  
}
