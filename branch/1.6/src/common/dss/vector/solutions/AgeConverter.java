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
