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
package dss.vector.solutions.etl.dhis2.exporter;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DuplicateChecker
{
  private static Set<String> mySet;
  
  public static void main(String[] args) throws JSONException, IOException
  {
    String dataStr = FileUtils.readFileToString(new File("/Users/richard1/Downloads/mosquitos-data-raw.json"));
    
    mySet = new HashSet<String>();
    
    JSONObject all = new JSONObject(dataStr);
    
    JSONArray dataValues = all.getJSONArray("dataValues");
    
    for (int i = 0; i < dataValues.length(); ++i)
    {
      JSONObject data = dataValues.getJSONObject(i);
      
      String period = data.getString("period");
      String orgUnit = data.getString("orgUnit");
      String attributeOptionCombo = data.getString("attributeOptionCombo");
      String dataElement = data.getString("dataElement");
//      String value = data.getString("value");
      
      mySet.add(period + attributeOptionCombo + dataElement + orgUnit);
    }
    
    System.out.println("Number of unique data : " + mySet.size());
    System.out.println("Total data : " + dataValues.length());
  }
}
