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

import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.business.generation.LoaderDecoratorException;
import com.runwaysdk.generation.loader.LoaderDecorator;



public class ContextListenerManager implements ServletContextListener
{
  private static final String helper = "dss.vector.solutions.ContextListenerManagerHelper";
  
  private Object helperInst;
  
  public ContextListenerManager()
  {
    try
    {
      helperInst = LoaderDecorator.load(helper).getConstructor().newInstance();
    }
    catch (InvocationTargetException ite)
    {
      throw new LoaderDecoratorException("InvocationTargetException initializing Context Listeners", ite.getTargetException());
    }
    catch (Exception e)
    {
      throw new LoaderDecoratorException("Exception initializing Context Listeners", e);
    }
  }
  
  public void contextInitialized(ServletContextEvent arg0)
  {
    try
    {
      helperInst.getClass().getMethod("contextInitialized", LoaderDecorator.load("javax.servlet.ServletContextEvent")).invoke(helperInst, arg0);
    }
    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
    {
      throw new LoaderDecoratorException(e);
    }
  }

  public void contextDestroyed(ServletContextEvent arg0)
  {
    try
    {
      helperInst.getClass().getMethod("contextDestroyed", LoaderDecorator.load("javax.servlet.ServletContextEvent")).invoke(helperInst, arg0);
    }
    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
    {
      throw new LoaderDecoratorException(e);
    }
  }
}
