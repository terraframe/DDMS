<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_All_Malaria_Seasons" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.MalariaSeasonController.viewPage.mojo" />
  <mjl:columns>

    <mjl:attributeColumn attributeName="seasonName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:row>
        <fmt:formatDate value="${item.startDate}" dateStyle="SHORT" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="endDate">
      <mjl:row>
        <fmt:formatDate value="${item.endDate}" dateStyle="SHORT" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
      </mjl:header>
      <mjl:row>
        <mjl:form name="dss.vector.solutions.MalariaSeason.form.name" id="${item.id}" method="POST">
          <mjl:input value="${item.id}" type="hidden" param="id" />
          <mjl:command value="Edit" action="dss.vector.solutions.general.MalariaSeasonController.edit.mojo" name="dss.vector.solutions.MalariaSeason.form.edit.button" />
        </mjl:form>
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
<mjl:commandLink display="Create a new Malaria Season" action="dss.vector.solutions.general.MalariaSeasonController.newInstance.mojo" name="MalariaSeasonController.newInstance" />