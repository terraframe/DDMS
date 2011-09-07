<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_All_SurveyPoints" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.SurveyPointController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header />
      <mjl:row>
        ${item.geoEntity.displayString} <!--  FIXME use view -->
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="surveyDate">
      <mjl:header />
      <mjl:row>
        <fmt:formatDate value="${item.surveyDate}" pattern="${dateFormatPattern}"  />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyPointController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.id}" name="id" />
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
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyPointController.newInstance.mojo" name="SurveyPointController.newInstance">
<mdss:localize key="Create_a_new_Survey_Point" />
</mjl:commandLink>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.SurveyExcelView" name="excelType"/>
</jsp:include>