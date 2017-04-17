<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.query.RangeCategoryController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="lowerBound">
      <mjl:header>
        Lower Bound Value
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="upperBound">
      <mjl:header>
        Upper Bound Value
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.query.RangeCategoryController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo" name="RangeCategoryController.newInstance">
<mdss:localize key="Create_a_new_Range_Category" />
</mjl:commandLink>
