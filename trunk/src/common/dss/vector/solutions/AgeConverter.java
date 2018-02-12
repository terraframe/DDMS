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

import java.util.Calendar;
import java.util.Date;

import com.runwaysdk.generation.loader.Reloadable;

public class AgeConverter implements Reloadable
{
  private Date dateOfBirth;
  
  private Integer age;

  public AgeConverter(Date dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
    
    Calendar today = Calendar.getInstance();
    Calendar dob = Calendar.getInstance();
    
    dob.setTime(this.dateOfBirth);

    this.age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

    if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR))
    {
      this.age = this.age - 1;
    }
  }

  public AgeConverter(Integer age)
  {
    this.age = age;
    
    Calendar today = Calendar.getInstance();
    Calendar dob = Calendar.getInstance();
    dob.set(Calendar.DAY_OF_YEAR, 1);
    dob.set(Calendar.MONTH, Calendar.JANUARY);
    dob.add(Calendar.YEAR, -this.age);

    if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR))
    {
      dob.add(Calendar.YEAR, 1);
    }

    // Must calculate the date of birth from the age
    this.dateOfBirth = dob.getTime();
  }

  public Date getDateOfBirth()
  {
    return dateOfBirth;
  }

  public Integer getAge()
  {
    return age;
  }  
}
