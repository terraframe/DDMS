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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_All_ReportItem" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.report.ReportItemController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="reportLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outputFormat">
      <mjl:row>
        <c:forEach items="${item.outputFormatEnumNames}" var="enumName">
          ${item.outputFormatMd.enumItems[enumName]}                  
        </c:forEach>      
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="reportName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.report.ReportItemController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink name="ReportItemController.newInstance" action="dss.vector.solutions.report.ReportItemController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Report_item" />
</mjl:commandLink>
<br />


<mdss:localize key="Title_Upload_A_Resource" var="RPT_RES_Title" />
<div style="padding-top: 40px;">
  <span style="font-size: 18px;">${RPT_RES_Title}</span> <!-- Upload a Resource -->
</div>
<mdss:localize key="Upload" var="Localized_Upload" />
<mjl:form name="dss.vector.solutions.report.ReportItemController.uploadResources.formId" method="POST" enctype="multipart/form-data">
  <mjl:input param="resourcesMFP" type="file" />
  <mjl:command value="${Localized_Upload}" action="dss.vector.solutions.report.ReportItemController.uploadResources.mojo" name="dss.vector.solutions.report.ReportItemController.uploadResources.submitButton" />
</mjl:form>


<c:if test="${not empty resourcesSuccess}">
  <span style="color: green;">${resourcesSuccess}</span>
</c:if>
<c:if test="${not empty resourcesFail}">
  <span style="color: red;">${resourcesFail}</span>
</c:if>
