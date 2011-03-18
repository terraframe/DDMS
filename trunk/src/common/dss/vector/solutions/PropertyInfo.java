package dss.vector.solutions;

public interface PropertyInfo
{
  public static final String GENERAL_PACKAGE         = "dss.vector.solutions";

  public static final String SYSTEM_PACKAGE          = GENERAL_PACKAGE + ".system";

  public static final String INSTALL_PACKAGE         = SYSTEM_PACKAGE + ".install";

  public static final String EPI_WEEK_PACKAGE        = GENERAL_PACKAGE + ".surveillance.EpiWeek";

  public static final String STANDARDS_PACKAGE       = GENERAL_PACKAGE + ".irs.Standards";

  public static final String RESISTANCE_PACKAGE      = GENERAL_PACKAGE + ".entomology.ResistantanceCutOff";

  public static final String GEO_PACKAGE             = GENERAL_PACKAGE + ".geo";

  public static final String MONITOR_PACKAGE         = GENERAL_PACKAGE + ".intervention.monitor";

  public static final String ADULT_DDA_RESISTANCE    = "ADDA Resistant";

  public static final String ADULT_DDA_SUSCEPTIBILE  = "ADDA Susceptible";

  public static final String LARVAE_DDA_RESISTANCE   = "LDDA Resistant";

  public static final String LARVAE_DDA_SUSCEPTIBILE = "LDDA Susceptible";

  public static final String UNIT_NOZZLE_COVERAGE    = "8002";

  public static final String EPI_START               = "epiStart";

  public static final String EPI_START_DAY           = "epiStartWeekDay";

  public static final String COUNTRY_GEO_ID          = "countryGeoId";

  public static final String DATE_FORMAT_SHORT       = "dateFormatShort";

  public static final String DATE_FORMAT             = "dateFormat";

  public static final String SYSTEM_DATE_FORMAT      = "systemDateFormat";

  public static final String SHORT_ID_SEGMENTS       = "SHORT_ID_SEGMENTS";

  public static final String DEFAULT_UNITS           = "sprayedUnitsPerDay";
}
