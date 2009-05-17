<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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
request.setAttribute("dateFormatPattern" ,formatter.toPattern());

%>
var locale = "<%=request.getLocale().toString()%>";

MDSS.DateSettings =
{
java_date_format:'${dateFormatPattern}',
db_datetime_format:'<%=Constants.DATETIME_FORMAT%>',
db_date_format:'<%=Constants.DATE_FORMAT%>',
DATE_FIELD_DELIMITER:'/',
DATE_RANGE_DELIMITER:'-',
MONTHS_LONG:<%=months%>,
WEEKDAYS_SHORT:<%=short_days%>}
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
          out.println( "'" + key + "' : '" + rb.getString(key) + "',");
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

  // Delete GeoEntity/GeoHierarchy choices
  Delete: {
    Entity: '<fmt:message key="Delete_Entity" />',
    Universal: '<fmt:message key="Delete_Universal" />',
    Relationship: '<fmt:message key="Delete_Relationship" />'
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