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
package com.runwaysdk.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.runwaysdk.business.SmartException;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.BackupReadException;
import com.runwaysdk.system.metadata.CorruptBackupException;
import com.runwaysdk.system.metadata.CreateBackupException;
import com.runwaysdk.system.metadata.RestoreAppnameException;
import com.terraframe.utf8.UTF8ResourceBundle;

public class Localizer
{
  public static final String DEFAULT_LOCALE = "defaultLocale";

  private static Localizer   instance;

  private ResourceBundle     bundle;

  private Localizer()
  {
    this("localization", getLocale());
  }

  private Localizer(String name, Locale locale)
  {
    this.bundle = UTF8ResourceBundle.getBundle(name, locale);
  }

  private String get(String key)
  {
    try
    {
      return bundle.getString(key);
    }
    catch (Exception e)
    {
      return null;
    }
  }

  private static synchronized Localizer instance()
  {
    if (instance == null)
    {
      instance = new Localizer();
    }

    return instance;
  }

  public static synchronized void setInstance(String name, Locale locale)
  {
    instance = new Localizer(name, locale);
  }

  public static String getMessage(Throwable e)
  {
    Throwable unwrapped = e;
    if (unwrapped instanceof InvocationTargetException)
    {
      unwrapped = e.getCause();
      while (unwrapped instanceof InvocationTargetException)
      {
        unwrapped = e.getCause();
      }
    }
    if (unwrapped == null)
    {
      unwrapped = e;
    }
    
    // This chain serves as a list of supported exception types we can return localized messages from.
    // The reason we have to do this is because runway won't localize the messages for us because we're
    // not at the DTO layer.
    String localized = "";
    try
    {
      if (unwrapped instanceof RestoreAppnameException)
      {
        localized = Localizer.instance().get("RESTORE_APPNAME_EXCEPTION");
        localized = localized.replace("{currentAppname}", ( (RestoreAppnameException) unwrapped ).getCurrentAppname());
        localized = localized.replace("{restoreAppname}", ( (RestoreAppnameException) unwrapped ).getRestoreAppname());
      }
      else if (unwrapped instanceof CorruptBackupException)
      {
        localized = Localizer.instance().get("CORRUPT_BACKUP_EXCEPTION");
        localized = localized.replace("{backupName}", ( (CorruptBackupException) unwrapped ).getBackupName());
      }
      else if (unwrapped instanceof BackupReadException)
      {
        localized = Localizer.instance().get("BACKUP_READ_EXCEPTION");
        localized = localized.replace("{location}", ( (BackupReadException) unwrapped ).getLocation());
      }
      else if (unwrapped instanceof CreateBackupException)
      {
        localized = Localizer.instance().get("CREATE_BACKUP_EXCEPTION");
        localized = localized.replace("{location}", ( (CreateBackupException) unwrapped ).getLocation());
      }
      
      // last resort! because doing this will spool up runway and freeze the ui for a little bit :(
      if (localized.equals("") && unwrapped instanceof SmartException)
      {
        localized = getSmartExceptionMessage((SmartException) unwrapped);
      }
    }
    catch (Throwable t)
    {
      Logger.error("We encountered an error while trying to localize a different error that happened. This is a mess, the user is going to get an error popup with nothing in it :(.", t);
    }
    
    return localized;
  }
  
  @Request
  private static String getSmartExceptionMessage(SmartException e)
  {
    return e.localize(CommonProperties.getDefaultLocale());
  }
  
  public static String getMessage(String key)
  {
    String value = Localizer.instance().get(key);

    if (value == null)
    {
      return key;
    }

    return value;
  }

  public static Locale getLocale()
  {
    return Locale.US;
  }

}
