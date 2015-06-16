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
<dt>
  <label>${RPT_RES_Title}</label> <!-- Upload a Resource -->
</dt>
<dd>
<mdss:localize key="Upload" var="Localized_Upload" />
<mjl:form name="dss.vector.solutions.report.ReportItemController.uploadResources.formId" method="POST" enctype="multipart/form-data">
  <mjl:input param="resourcesMFP" type="file" />
  <mjl:command value="${Localized_Upload}" action="dss.vector.solutions.report.ReportItemController.uploadResources.mojo" name="dss.vector.solutions.report.ReportItemController.uploadResources.submitButton" />
</mjl:form>
</dd>