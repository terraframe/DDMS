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

public class RelativeValueProblem extends RelativeValueProblemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 849292072;
  
  public RelativeValueProblem()
  {
    super();
  }
  
  public RelativeValueProblem(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    
    if (this.getRelation() == null || this.getRelation().equals(""))
    {
      message = message.replace(" [{relation}]", "");
      message = message.replace("{relation}", "");
    }
    else
    {
      message = replace(message, "{relation}", this.getRelation());
    }
    
    if (this.getRelativeAttributeLabel() == null || this.getRelativeAttributeLabel().equals(""))
    {
      message = message.replace(" [{relativeAttributeLabel}]", "");
      message = message.replace("{relativeAttributeLabel}", "");
    }
    else
    {
      message = replace(message, "{relativeAttributeLabel}", this.getRelativeAttributeLabel());
    }
    
    return message;
  }
}
