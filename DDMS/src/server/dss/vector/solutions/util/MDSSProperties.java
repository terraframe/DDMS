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
package dss.vector.solutions.util;

import java.util.Locale;
import java.util.ResourceBundle;

import com.runwaysdk.business.Business;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.session.Session;
import com.terraframe.utf8.UTF8ResourceBundle;

import dss.vector.solutions.localization.MultiBundle;

/**
 * A wrapper for access to the MDSS.properties bundle, this fixes isolated
 * scenarios where the system would be unable to find the bundle
 * 
 * @author Eric
 */
public class MDSSProperties
{
  private static final String NAME   = "MDSS";

  private static ClassLoader  LOADER = Business.class.getClassLoader();

  public static String getString(String key)
  {
    return MultiBundle.get(key);
  }

  public static Object getObject(String key)
  {
    return UTF8ResourceBundle.getBundle(getBundleName(), Session.getCurrentLocale(), LOADER).getObject(key);
  }

  public static String getString(String key, Locale locale)
  {
    return MultiBundle.get(key, locale);
  }

  public static Object getObject(String key, Locale locale)
  {
    ResourceBundle other = UTF8ResourceBundle.getBundle(getBundleName(), locale, LOADER);
    return other.getObject(key);
  }

  private static String getBundleName()
  {
    MdDimensionDAOIF currentDimension = Session.getCurrentDimension();
    if (currentDimension != null)
    {
      return NAME + "-" + currentDimension.getName();
    }
    else
    {
      return NAME;
    }
  }
}
