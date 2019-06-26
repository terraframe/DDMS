package com.runwaysdk.constants;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.database.PostgreSQL;

/**
 * Contains static properties for operation of the core with postgres. Login
 * info is specified in database.properties. Typical default values for logging
 * in to postgres are:
 * 
 * <pre>
 * port=5432
 * databaseName=runwaydb
 * user=runway
 * password=runway
 * rootuser=root
 * rootpassword=root
 * rootdatabase=template1
 * </pre>
 * 
 * @author Justin
 */
public class PostgreSQLProperties implements VendorProperties
{
  public static final String NAME         = "Postgresql";

  public static final String VENDOR_CLASS = "com.runwaysdk.constants.PostgreSQL";

  private Set<String>        errorCodes;

  private Set<String>        seriousErrorCodes;

  public PostgreSQLProperties()
  {
    this.errorCodes = new TreeSet<String>(Arrays.asList(new String[] { "1003", "1006", "1007", "8000", "8001", "8003", "8004", "8006", "8007", "9000", "24000", "27000", "28000", "34000", "38000", "38001", "38002", "38003", "38004", "39000", "39001", "39004", "42501", "42723", "42725", "42803", "42846", "42883", "44000", "53000", "53100", "53200", "53300", "54023", "57000", "57014", "58030", "0A000", "0F000", "0F001", "0L000", "0LP01", "0P000", "2202E", "22P04", "22P05", "2B000", "2BP01",
        "2F000", "2F002", "2F003", "2F004", "2F005", "39P01", "39P02", "3D000", "42P03", "42P04", "42P05", "42P06", "42P08", "42P11", "42P12", "42P13", "42P14", "55P02", "57P03", "58P01", "58P02", "F0000", "F0001", "P0000", "P0001", "XX000", "XX001", "XX002" }));

    this.seriousErrorCodes = new TreeSet<String>(Arrays.asList(new String[] { "1004", "1008", "2000", "2001", "3000", "21000", "22000", "22001", "22002", "22003", "22004", "22005", "22007", "22008", "22009", "22010", "22011", "22012", "22015", "22018", "22019", "22020", "22021", "22022", "22023", "22024", "22025", "22026", "22027", "23000", "23001", "23502", "23503", "23514", "25000", "25001", "25002", "25003", "25004", "25005", "25006", "25007", "25008", "26000", "40000", "40001", "40002",
        "40003", "42000", "42601", "42602", "42611", "42622", "42702", "42703", "42704", "42710", "42712", "42804", "42809", "42830", "42939", "54000", "54001", "54011", "55000", "55006", "0B000", "2200B", "2200C", "2200D", "2200F", "2200G", "2201B", "2201E", "2201F", "2201G", "22P01", "22P02", "22P03", "22P06", "25P01", "25P02", "2D000", "3B000", "3B001", "3F000", "40P01", "42P01", "42P02", "42P07", "42P09", "42P10", "42P15", "42P16", "42P17", "42P18", "55P03" }));
  }

  public String getDatabaseClass()
  {
    return PostgreSQL.class.getName();
  }

  public boolean isError(String code)
  {
    return this.errorCodes.contains(code);
  }

  public boolean isSeriousError(String code)
  {
    return this.seriousErrorCodes.contains(code);
  }

  public String getName()
  {
    return NAME;
  }
}
