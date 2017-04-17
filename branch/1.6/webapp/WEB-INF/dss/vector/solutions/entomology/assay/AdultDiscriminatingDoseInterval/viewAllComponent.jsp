<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_All_AdultDiscriminatingDoseInterval" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="amount">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="assay">
      <mjl:row>
        ${item.assay.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="intervalTime">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalController.view.mojo">
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
<mjl:commandLink name="AdultDiscriminatingDoseIntervalController.newInstance" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Adult_diagnostic_assay_interveral" />
</mjl:commandLink>
