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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<%@page import="dss.vector.solutions.PropertyDTO"%><c:set var="page_title" value="Epi_Week"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  GregorianCalendar week = new GregorianCalendar();

  PropertyDTO item = (PropertyDTO) request.getAttribute("item");

  Integer year = week.get(Calendar.YEAR);

  try
  {
    year = Integer.parseInt(request.getParameter("year"));
  }
  catch (Exception e)
  {
  }
  
  SimpleDateFormat wd = new SimpleDateFormat("EEE", request.getLocale());
  SimpleDateFormat fullWd = new SimpleDateFormat("EEEEEEEE", request.getLocale());
  SimpleDateFormat mf = new SimpleDateFormat("MMM", request.getLocale());
  SimpleDateFormat df = new SimpleDateFormat("dd", request.getLocale());

  week.set(Calendar.DAY_OF_WEEK, 1);

  String options = "";
  for (int j = 0; j < 7; j++)
  {
    options += "<option " + ( j == Integer.parseInt(item.getPropertyValue()) ? "selected=\"selected\"" : "" ) + " value=\"" + j + "\" >";
    options += fullWd.format(week.getTime()) + "</option>";
    week.add(Calendar.DAY_OF_WEEK, 1);
  }

  EpiDateDTO epiDate = EpiDateDTO.getInstanceByPeriod(clientRequest, PeriodTypeDTO.WEEK, 1, year);

  week.clear();
  week.setTime(epiDate.getStartDate());

  request.setAttribute("options", options);
%>
<div class="noprint">
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST" >

  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${configuration.firstDayMd.displayLabel}
        </label>
      </dt>
      <dd>
      <select name="dto.propertyValue">
          ${options}
       </select>
        <mjl:messages attribute="propertyValue">
          <mjl:message />
        </mjl:messages>
      </dd>

      <mdss:localize key="Update" var="Localized_Update" />

      <mjl:command value="${Localized_Update}" action="dss.vector.solutions.PropertyController.create.mojo" name="dss.vector.solutions.Property.form.update.button" classes="submitButton" />
    </dl>
  </mjl:component>
</mjl:form>


<br>

<select name="changeYear"  id="changeYear" >
<%
  for (Integer i = 1970; i <= 2070; i++)
  {
    out.println("<option " + ( year.equals(i) ? "selected=\"selected\"" : "" ) + " value=\"" + i + "\" >" + i + "</option>");
  }
%>
</select>
<script type='text/javascript'>
        selectBox = document.getElementById('changeYear');
        selectBox.onchange = function(){
          location.href="./dss.vector.solutions.PropertyController.newInstance.mojo?year=" + selectBox.value;
        }
</script>
</div>


<h2 class="printOnly"><%=year%></h2>
<table class="displayTable">
<tr>
  <th ><mdss:localize key="EW"/></th>
  <th ><mdss:localize key="Month"/></th>
  <%
    for (int j = 0; j < 7; j++)
    {
      out.print("<th width=\"\">" + wd.format(week.getTime()) + "</th>");
      week.add(Calendar.DAY_OF_WEEK, 1);
    }
  %>
    <th width=""><mdss:localize key="Month"/></th>
</tr>
<%
  for (Integer i = 0; i < epiDate.getNumberOfEpiWeeks(); i++)
  {
    epiDate = EpiDateDTO.getInstanceByPeriod(clientRequest, PeriodTypeDTO.WEEK, i, year);
    week = new GregorianCalendar();
    week.clear();
    week.setTime(epiDate.getStartDate());
    out.println("<tr class=\"" + ( i % 2 == 0 ? "evenRow" : "oddRow" ) + "\">");
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
<br>
<a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a>


<form method="get" action="dss.vector.solutions.PropertyController.viewPage.mojo" style="display: inline;">
<input type="hidden" name="year" value="<%=year%>" />
<button type="submit"><mdss:localize key="Excel_Export_Header" />
</button>
</form>