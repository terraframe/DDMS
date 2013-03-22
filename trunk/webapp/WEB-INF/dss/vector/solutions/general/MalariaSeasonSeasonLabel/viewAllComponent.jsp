<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_All_MalariaSeasonSeasonLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.MalariaSeasonSeasonLabelController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="dENGUE_defaultLocale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="defaultLocale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="mALARIA_defaultLocale">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.general.MalariaSeasonSeasonLabelController.view.mojo">
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
<mjl:commandLink name="MalariaSeasonSeasonLabelController.newInstance" action="dss.vector.solutions.general.MalariaSeasonSeasonLabelController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Season_Label" />
</mjl:commandLink>
