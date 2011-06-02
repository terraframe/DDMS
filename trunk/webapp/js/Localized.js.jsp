
<%@page import="java.text.DecimalFormat"%><%@page import="dss.vector.solutions.util.LocalizationFacadeDTO"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="org.apache.taglibs.standard.tag.common.fmt.BundleSupport"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.AttributedCharacterIterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="com.runwaysdk.constants.Constants" %>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<!--It is very important to set the content type so that FF knows to read in these strings as UTF-8 -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
Locale locale = request.getLocale();
DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
SimpleDateFormat formatter = (SimpleDateFormat)df;
Date today = new Date();
AttributedCharacterIterator aci = df.formatToCharacterIterator(today);

List<String> month_list = new ArrayList<String>(Arrays.asList(formatter.getDateFormatSymbols().getMonths()));
month_list.removeAll(Arrays.asList(""));
JSONArray months = new JSONArray(month_list);

List<String> day_list = new ArrayList<String>(Arrays.asList(formatter.getDateFormatSymbols().getShortWeekdays()));
day_list.removeAll(Arrays.asList(""));
JSONArray short_days = new JSONArray(day_list);

ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
Map<String,String> roleMap =  clientRequest != null ? clientRequest.getSessionUserRoles() : new HashMap<String, String>();
JSONArray roles = new JSONArray(roleMap.keySet());

DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance(locale);

%>

MDSS.user = {
roles:<%=roles%>,
locale : "<%=request.getLocale().toString()%>"
};

MDSS.DateSettings =
{
java_date_format:'<%=Halp.getDateFormatString(request)%>',
db_datetime_format:'<%=Constants.DATETIME_FORMAT%>',
db_date_format:'<%=Constants.DATE_FORMAT%>',
DATE_FIELD_DELIMITER:'/',
DATE_RANGE_DELIMITER:'-',
MONTHS_LONG:<%=months%>,
WEEKDAYS_SHORT:<%=short_days%>,
<%
for (AttributedCharacterIterator.Attribute key : aci.getAllAttributeKeys())
{
  String str = key.toString();
  int pos = aci.getRunLimit(key);
  if(pos > 3) pos = 3;
  if(str.contains("(day of month)"))
  {
    out.println("MDY_MONTH_POSITION:"+pos+",");
  }
  if(str.contains("(month)"))
  {
      out.println("MDY_DAY_POSITION:"+pos+",");
  }
  if(str.contains("(year)"))
  {
    out.println("MDY_YEAR_POSITION:"+pos+",");
  }
}

%>
}

/**
 * Constants used for localization in javascript.
 */
MDSS.Localized = <%= LocalizationFacadeDTO.getAllLocalizedText(clientRequest) %>;

MDSS.FLOAT_PRECISION = 2;

MDSS.getParser = function() {
  try {
    return new MDSS.DecimalParser('<%=format.getDecimalFormatSymbols().getDecimalSeparator()%>', '<%=format.getPositivePrefix()%>', '<%=format.getPositiveSuffix()%>', '<%=format.getNegativePrefix()%>', '<%=format.getNegativeSuffix()%>');
  }
  catch(exception) {
    return null;
  }
}

MDSS.parse = function(parser, number) { 
  if(parser == null) {
    return parseFloat(number);
  }
  
  try {
    return parser.parse(number); 
  }
  catch(exception) {
    return NaN;
  }
}

MDSS.format = function(formatter, number) { 
  if(formatter == null) {
    return number.toFixed(MDSS.FLOAT_PRECISION);
  }
  
  // Format the number to two decimal places.
  formatter.setMaxFractionDigits(MDSS.FLOAT_PRECISION);
  formatter.setMinFractionDigits(MDSS.FLOAT_PRECISION);

  var _number = formatter.format(number);

  return _number;
}

MDSS.parseNumber = Mojo.Util.curry(MDSS.parse, MDSS.getParser());
MDSS.formatNumber = Mojo.Util.curry(MDSS.format, MDSS.getParser());

MDSS.validateNumber = function(oData) {
  var value = MDSS.parseNumber(oData);
  
  if(value == NaN) {
    return undefined;
  }
  
  return oData;
}

MDSS.localize = function(key){
  var s = MDSS.Localized[key];
  if( !s ) return(  "???" + key + "???"  );
  return s;
}