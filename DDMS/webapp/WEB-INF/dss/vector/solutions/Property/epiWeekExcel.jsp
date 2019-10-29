<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ page language="java" contentType="application/force-download; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="dss.vector.solutions.surveillance.PeriodType"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.surveillance.PeriodTypeDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.PropertyDTO"%>
<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

GregorianCalendar week = new GregorianCalendar();

Integer year = week.get(Calendar.YEAR);
try
{
 year = Integer.parseInt(request.getParameter("year"));
}
catch(Exception e){}

response.setHeader("Content-Type","application/excel;");
response.setHeader("Content-Disposition:","attachment; filename=\"epiWeeks"+year+".xls\"");


SimpleDateFormat wd = new SimpleDateFormat("EEE");
SimpleDateFormat mf = new SimpleDateFormat("MMM");
SimpleDateFormat df = new SimpleDateFormat("dd");

EpiDateDTO epiDate = EpiDateDTO.getInstanceByPeriod(clientRequest,PeriodTypeDTO.WEEK, 1, year);
week.clear();
week.setTime(epiDate.getStartDate());
%>

<table >
<tr>
  <th ><mdss:localize key="EW"/></th>
  <th ><mdss:localize key="Month"/></th>
  <%
  for (int j = 0; j < 7; j++)
  {
    out.print("<th>" + wd.format(week.getTime()) + "</th>");
    week.add(Calendar.DAY_OF_WEEK, 1);
  }%>
    <th><mdss:localize key="Month"/></th>
    <th><%=year%></th>
</tr>
<%
for (Integer i = 0; i < epiDate.getNumberOfEpiWeeks(); i++)
{
  epiDate = EpiDateDTO.getInstanceByPeriod(clientRequest,PeriodTypeDTO.WEEK, i, year);
  week = new GregorianCalendar();
  week.clear();
  week.setTime(epiDate.getStartDate());
  out.println("<tr>");
  out.print("<td>" + Integer.toString(i+1) + "</td>");
  out.print("<td>" + mf.format(week.getTime()) + "</td>");
  for (int j = 0; j < 7; j++)
  {
    out.print("<td>" + df.format(week.getTime()) + "</td>");
    week.add(Calendar.DAY_OF_WEEK, 1);
  }
  week.add(Calendar.DAY_OF_WEEK, -1);
  out.print("<td>" + mf.format(week.getTime()) + "</td>");
  out.println("</tr>");
}
%>
</table>