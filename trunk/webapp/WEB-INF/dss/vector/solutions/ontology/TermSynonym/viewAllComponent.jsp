<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_MosquitoCollection" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="abundance">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="collectionDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.collectionDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="collectionId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="collectionMethod">
      <mjl:row>
        ${item.collectionMethod.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="term">
      <mjl:row>
        ${item.term.id}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lifeStage">
      <mjl:row>
        <ul>
          <c:forEach items="${item.lifeStageEnumNames}" var="enumName">
            <li>
              ${item.lifeStageMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lifeStageName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo">
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
<mjl:commandLink name="MosquitoCollectionController.newInstance" action="dss.vector.solutions.entomology.MosquitoCollectionController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Mosquito_Collection" />
</mjl:commandLink>
