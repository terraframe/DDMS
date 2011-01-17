<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.query.EntomologySearchController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="queryName">
      <mjl:header>
        Query Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="queryViewName">
      <mjl:header>
        Query View Name
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="queryXml">
      <mjl:header>
        Query XML
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="thematicLayer">
      <mjl:header>
        Thematic Layer
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.query.EntomologySearchController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.query.EntomologySearchController.newInstance.mojo" name="EntomologySearchController.newInstance"
><mdss:localize key="Create_a_new_Entomology_Query" />
</mjl:commandLink>
