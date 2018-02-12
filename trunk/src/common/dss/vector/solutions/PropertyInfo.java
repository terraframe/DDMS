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

public interface PropertyInfo
{
  public static final String GENERAL_PACKAGE         = "dss.vector.solutions";

  public static final String SYSTEM_PACKAGE          = GENERAL_PACKAGE + ".system";

  public static final String INSTALL_PACKAGE         = SYSTEM_PACKAGE + ".install";

  public static final String EPI_WEEK_PACKAGE        = GENERAL_PACKAGE + ".surveillance.EpiWeek";

  public static final String STANDARDS_PACKAGE       = GENERAL_PACKAGE + ".irs.Standards";

  public static final String RESISTANCE_PACKAGE      = GENERAL_PACKAGE
                                                         + ".entomology.ResistantanceCutOff";

  public static final String GEO_PACKAGE             = GENERAL_PACKAGE + ".geo";

  public static final String MONITOR_PACKAGE         = GENERAL_PACKAGE + ".intervention.monitor";

  public static final String ADULT_DDA_RESISTANCE    = "ADDA Resistant";

  public static final String ADULT_DDA_SUSCEPTIBILE  = "ADDA Susceptible";

  public static final String LARVAE_DDA_RESISTANCE   = "LDDA Resistant";

  public static final String LARVAE_DDA_SUSCEPTIBILE = "LDDA Susceptible";

  public static final String UNIT_NOZZLE_COVERAGE    = "8002";

  public static final String EPI_START               = "epiStart";

  public static final String EPI_START_DAY           = "epiStartWeekDay";

  public static final String NEW_CASE_PERIOD         = "newCasePeriod";

  public static final String COUNTRY_GEO_ID          = "countryGeoId";

  public static final String DATE_FORMAT_SHORT       = "dateFormatShort";

  public static final String DATE_FORMAT             = "dateFormat";

  public static final String SYSTEM_DATE_FORMAT      = "systemDateFormat";

  public static final String SHORT_ID_SEGMENTS       = "SHORT_ID_SEGMENTS";

  public static final String DEFAULT_UNITS           = "sprayedUnitsPerDay";
}
