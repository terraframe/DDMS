<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="dss.vector.solutions.surveillance.PeriodType"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.surveillance.PeriodTypeDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>

<%@page import="dss.vector.solutions.PropertyDTO"%><c:set var="page_title" value="Epi_Week"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

GregorianCalendar week = new GregorianCalendar();

PropertyDTO item = (PropertyDTO) request.getAttribute("item");

Integer testYear = week.get(Calendar.YEAR);
SimpleDateFormat wd = new SimpleDateFormat("EEE");
SimpleDateFormat fullWd = new SimpleDateFormat("EEEEEEEE");
SimpleDateFormat mf = new SimpleDateFormat("MMM");
SimpleDateFormat df = new SimpleDateFormat("dd");


EpiDateDTO epiDate = EpiDateDTO.getInstanceByPeriod(clientRequest,PeriodTypeDTO.WEEK, 1, testYear);

week.clear();
week.setTime(epiDate.getStartDate());



String options = "";
for (int j = 0; j < 7; j++)
{
  options += "<option ≈\""+(j == Integer.parseInt(item.getPropertyValue()) ? "selected=\"selected\"" : "")+"\" value=\"" + j + "\" >";
  options += fullWd.format(week.getTime()) + "</option>";
  week.add(Calendar.DAY_OF_WEEK, 1);
}

request.setAttribute("options",options);
%>
<div class="noprint">
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST" >

  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.propertyNameMd.displayLabel}
        </label>
      </dt>
      <dd>
          ${item.displayLabel}
      </dd>
      <dt>
        <label>
          ${item.descriptionMd.displayLabel}
        </label>
      </dt>
      <dd>
        ${item.description}
      </dd>
      <dt>
        <label>
          ${item.propertyValueMd.displayLabel}
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

      <mjl:command value="Update" action="dss.vector.solutions.PropertyController.create.mojo" name="dss.vector.solutions.Property.form.update.button" classes="submitButton"/>
    </dl>
  </mjl:component>
</mjl:form>
</div>
<br>
<br>
<h2><%=testYear %></h2>
<table class="displayTable">
<tr>
  <th ><fmt:message key="EW"/></th>
  <th ><fmt:message key="Month"/></th>
  <%
  for (int j = 0; j < 7; j++)
  {
    out.print("<th width=\"\">" + wd.format(week.getTime()) + "</th>");
    week.add(Calendar.DAY_OF_WEEK, 1);
  }%>
    <th width=""><fmt:message key="Month"/></th>
</tr>
<%
for (Integer i = 0; i < 52; i++)
{
  epiDate = EpiDateDTO.getInstanceByPeriod(clientRequest,PeriodTypeDTO.WEEK, i, testYear);
  week = new GregorianCalendar();
  week.clear();
  week.setTime(epiDate.getStartDate());
  out.println("<tr class=\"" + (i % 2 == 0 ? "evenRow" : "oddRow") +"\">");
  out.print("<td>" + Integer.toString(i) + "</td>");
  out.print("<td>" + mf.format(week.getTime()) + "</td>");
  for (int j = 0; j < 7; j++)
  {
    out.print("<td>" + df.format(week.getTime()) + "</td>");
    week.add(Calendar.DAY_OF_WEEK, 1);
  }
  out.print("<td>" + mf.format(week.getTime()) + "</td>");
  out.println("</tr>");
}
%>
</table>
<a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a>


<form method="get" action="dss.vector.solutions.PropertyController.viewPage.mojo" style="display: inline;">
<input type="hidden" name="year" value="<%=testYear %>" />
<button type="submit"><fmt:message key="Excel_Export_Header" />
</button>
</form>
