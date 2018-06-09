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
package dss.vector.solutions.entomology.assay;


public class InvalidFedQuantityProblem extends InvalidFedQuantityProblemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235164946512L;
  
  public InvalidFedQuantityProblem()
  {
    super();
  }
  
  public InvalidFedQuantityProblem(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    
    if (this.getFed() == null || this.getFed().equals(""))
    {
      message = message.replace("[{fed}]", "");
      message = message.replace("{fed}", "");
    }
    else
    {
      message = replace(message, "{fed}", this.getFed());
    }
    
    return message;
  }
  
}
