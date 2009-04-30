<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.MDSSUserController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="inactive">
      <mjl:header>
        Inactive
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="locale">
      <mjl:header>
        The user locale
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="password">
      <mjl:header>
        Password
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sessionLimit">
      <mjl:header>
        Session Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="username">
      <mjl:header>
        Username
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.MDSSUserController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new MDSS User" action="dss.vector.solutions.MDSSUserController.newInstance.mojo" name="MDSSUserController.newInstance" />
