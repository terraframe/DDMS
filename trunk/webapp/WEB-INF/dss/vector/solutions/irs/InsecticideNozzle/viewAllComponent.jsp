<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.irs.InsecticideNozzleController.viewPage.mojo" />
  <mjl:columns>
    <mjl:freeColumn>
      <mjl:header>
        InsecticideBrand
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.irs.InsecticideBrandController.view.mojo" name="parent.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>
        Nozzle
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.irs.NozzleController.view.mojo" name="child.link">
          <mjl:property value="${item.childId}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:attributeColumn attributeName="coverage">
      <mjl:header>
        Coverage
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
      <mjl:header>
        Enabled
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.irs.InsecticideNozzleController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Insecticide Nozzle" action="dss.vector.solutions.irs.InsecticideNozzleController.newRelationship.mojo" name="InsecticideNozzleController.newRelationship" />
