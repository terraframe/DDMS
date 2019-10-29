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
package dss.vector.solutions;

import com.runwaysdk.generation.loader.Reloadable;

public class ToggleUniversalAction implements ConditionalAction, Reloadable
{
  private String   positiveElement;

  private String   negativeElement;

  private String[] universals;

  public ToggleUniversalAction(String positiveElement, String negativeElement, String... universals)
  {
    this.positiveElement = positiveElement;
    this.negativeElement = negativeElement;
    this.universals = universals;
  }

  public String getJavascript()
  {
    StringBuffer buffer = new StringBuffer();

    buffer.append("    if(document.getElementById('" + positiveElement + "').checked === true){\n");
    
    for (String universal : universals)
    {
      buffer.append("      selectSearch.addExtraUniversal('" + universal + "');\n");
    }
    
    buffer.append("    }\n");

    buffer.append("    YAHOO.util.Event.on('" + positiveElement + "', 'click', function(e) {\n");

    for (String universal : universals)
    {
      buffer.append("      selectSearch.addExtraUniversal('" + universal + "');\n");
    }

    buffer.append("      geoSearch.clearCache();\n");
    buffer.append("    });\n");

    buffer.append("\n");
    buffer.append("    YAHOO.util.Event.on('" + negativeElement + "', 'click', function(e) {\n");
    buffer.append("      selectSearch.clearExtraUniversals();\n");
    buffer.append("      geoSearch.clearCache();\n");
    buffer.append("    });\n");

    return buffer.toString();
  }

}
