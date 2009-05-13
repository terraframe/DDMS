<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%><%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.ibm.icu.util.GregorianCalendar"%>
<%@page import="com.ibm.icu.util.Calendar"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.Constants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

//tell firfox to allways check for a new version
response.setHeader("Content-Type","text/html;charset=UTF-8");
response.setHeader("Cache-Control","must-revalidate");
response.setHeader("Expires","0");
session.getId();

//parse the input
String[] types_to_load = request.getQueryString().split("&");

//set last update to far in the future incase something goes wrong
Date  lastUpdate    = new Date();

//-1 means something went wrong
Long  lastUpdatedTimestamp = new Long(-1);

//long etag = 0;

String etagStr="NEW";

try
{
  SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATETIME_FORMAT);
  String newestUpdated = JSONController.getNewestUpdateDate(clientRequest.getSessionId() , types_to_load,true);
  lastUpdate = dateFormat.parse(newestUpdated, new java.text.ParsePosition(0));
  lastUpdatedTimestamp = new Long(lastUpdate.getTime());
  response.setDateHeader("Last-Modified",lastUpdate.getTime());
  etagStr = session.getId() + lastUpdatedTimestamp.toString();

  response.setHeader("Etag", etagStr);
  //parse incoming etag
  //etag = Long.parseLong(request.getHeader("If-None-Match").replace("\"",""));
}
catch(Exception e){}


String cacheControl = request.getHeader("Cache-Control");
boolean refreshWanted = false;
if(cacheControl != null)
{
  refreshWanted = cacheControl.equals("max-age=0");
}

Date  todaysDate    = new Date();

SimpleDateFormat   showDateFormatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

String   outString = "";
outString += "/*================================================================\n";
outString += "This file was created " + showDateFormatter.format(todaysDate)+"\n";
outString += "For Session : " + session.getId()+"\n";
outString += "Newest Type's Update date is " + showDateFormatter.format(lastUpdate)+"\n";
outString += Halp.join((List<String>) Arrays.asList(types_to_load),"\n") +"\n";
outString += "================================================================*/\n";


if ( etagStr.equals(request.getHeader("If-None-Match")))
{
  outString += "Contents are unchanged: send 304 status message \n";
  response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
}
else
{
  out.println(outString);
  out.println(JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));
}
//System.out.println(outString);
%>