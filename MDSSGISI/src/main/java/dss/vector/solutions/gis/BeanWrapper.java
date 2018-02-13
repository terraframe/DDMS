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
package dss.vector.solutions.gis;


public class BeanWrapper<T>
{
  private Object   instance;

  public BeanWrapper(Object instance)
  {
    this.instance = instance;
  }

  @SuppressWarnings("unchecked")
  public T get(String attribute)
  {
    String methodName = this.getMethodName(attribute);
    Class<? extends Object> clazz = instance.getClass();

    try
    {
      return (T) clazz.getMethod(methodName).invoke(instance);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public void set(String attribute, T value)
  {
    String methodName = this.setMethodName(attribute);
    Class<? extends Object> clazz = instance.getClass();
    
    try
    {
      clazz.getMethod(methodName, value.getClass()).invoke(instance, value);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private String getMethodName(String attribute)
  {
    return "get" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
  }
  
  private String setMethodName(String attribute)
  {
    return "set" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
  }
}
