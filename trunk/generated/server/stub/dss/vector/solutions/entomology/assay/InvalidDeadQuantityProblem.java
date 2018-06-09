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


public class InvalidDeadQuantityProblem extends InvalidDeadQuantityProblemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235164941912L;
  
  public InvalidDeadQuantityProblem()
  {
    super();
  }
  
  public InvalidDeadQuantityProblem(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  @Override
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    
    if (this.getQuantityDead() == null || this.getQuantityDead().equals(""))
    {
      message = message.replace("[{quantityDead}]", "");
      message = message.replace("{quantityDead}", "");
    }
    else
    {
      message = replace(message, "{quantityDead}", this.getQuantityDead());
    }
    
    if (this.getQuantityTested() == null || this.getQuantityTested().equals(""))
    {
      message = message.replace("[{quantityTested}]", "");
      message = message.replace("{quantityTested}", "");
    }
    else
    {
      message = replace(message, "{quantityTested}", this.getQuantityTested());
    }
    
    return message;
  }
  
}
