<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="org.apache.taglibs.standard.tag.common.fmt.BundleSupport"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.AttributedCharacterIterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="com.terraframe.mojo.constants.Constants" %>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
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
Map<String,String> roleMap =  clientRequest.getSessionUserRoles();
JSONArray roles = new JSONArray(roleMap.keySet());

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
MDSS.Localized = {
<%
   //Nifty automatic localizer!
   ResourceBundle rb = BundleSupport.getLocalizationContext(pageContext).getResourceBundle();
   for( Enumeration en = rb.getKeys(); en.hasMoreElements(); )
   {
       String key = (String)en.nextElement();
       String value = rb.getString(key);
       String escaped = value.replace("'", "\\'"); // Converts ' to \'
          out.println( "'" + key + "' : '" + escaped + "',");
   }
%>

  // tree widget options (per node)
  Tree: {
    Create: '<fmt:message key="Tree_Create"/>',
    Edit: '<fmt:message key="Tree_Edit"/>',
    Delete: '<fmt:message key="Tree_Delete"/>',
    Select: '<fmt:message key="Tree_Select"/>'
  },

  // Basic choices
  Choice: {
    Yes: '<fmt:message key="Choice_Yes"/>',
    No: '<fmt:message key="Choice_No"/>'
  },

  Query:
  {
    Start_Date: '<fmt:message key="Query_Start_Date" />',
    End_Date: '<fmt:message key="Query_End_Date" />',
    Map: '<fmt:message key="Query_Map" />',
    Run: '<fmt:message key="Query_Run" />',
    Save: '<fmt:message key="Query_Save" />',
    Load: '<fmt:message key="Query_Load" />',
    Refresh: '<fmt:message key="Query_Refresh" />'
  },

  Toggle:
  {
    Show: '<fmt:message key="Toggle_Show" />',
    Hide: '<fmt:message key="Toggle_Hide" />'
  },

  Thematic:
  {
    Layer: '<fmt:message key="Thematic_Layer" />',
    Edit_Default_Style: '<fmt:message key="Thematic_Edit_Default_Style" />',
    Edit_Variable_Styles: '<fmt:message key="Thematic_Edit_Variable_Styles" />'
  },

};



MDSS.localize = function(key){
  var s = MDSS.Localized[key];
  if( !s ) return(  "???" + key + "???"  );
  return s;
}