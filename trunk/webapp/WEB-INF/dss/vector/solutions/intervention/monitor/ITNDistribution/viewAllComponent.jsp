<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_All_ITNDistribution" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.ITNDistributionController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="distributionDate">
      <mjl:row>
        <fmt:formatDate value="${item.distributionDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="facility">
      <mjl:row>
        ${item.facility}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="person">
      <mjl:row>
        ${item.person}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="batchNumber">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="service">
      <mjl:row>
        ${item.service.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="net">
      <mjl:row>
        ${item.net.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="numberSold">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="currencyReceived">
      <mjl:row>
        <fmt:formatNumber type="currency" currencyCode="${currency}" value="${item.currencyReceived}" />
      </mjl:row>    
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="distributorName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="distributorSurname">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNDistributionController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.concreteId}" name="id" />
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
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNDistributionController.newInstance.mojo" name="ITNDistributionController.newInstance">
  <mjl:property name="person" value="${item.person.id}"/>
  <mjl:property name="distributionDate" value="${distributionDate}"/>
  <mjl:property name="facility" value="${facility}"/>
  <mjl:property name="batchNumber" value="${batchNumber}"/>
  <mdss:localize key="Create_a_new_ITN_Distribution" />
</mjl:commandLink>
