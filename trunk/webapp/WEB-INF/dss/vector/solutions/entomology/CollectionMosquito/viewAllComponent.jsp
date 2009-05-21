<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.entomology.CollectionMosquitoController.viewPage.mojo" />
  <mjl:columns>
    <mjl:freeColumn>
      <mjl:header>
        Abstract Mosquito Collection
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.entomology.AbstractMosquitoCollectionController.view.mojo" name="parent.link">
          <mjl:property value="${item.parentId}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>
        Mosquito
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.entomology.MosquitoController.view.mojo" name="child.link">
          <mjl:property value="${item.childId}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.entomology.CollectionMosquitoController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Collection-Mosquito" action="dss.vector.solutions.entomology.CollectionMosquitoController.newRelationship.mojo" name="CollectionMosquitoController.newRelationship" />
