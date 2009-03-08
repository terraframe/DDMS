<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" >
  <mjl:context action="csu.mrc.ivcc.mdss.entomology.MosquitoCollectionController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="collectionMethod">
      <mjl:header>
        Collection Method
      </mjl:header>
      <mjl:row even ="altRow" odd="odd">
        ${item.collectionMethod.termName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dateCollected">
      <mjl:header>
        Date Collected
      </mjl:header>
      <mjl:row even ="altRow">
        ${item.dateCollected}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header>
        Geo Entity
      </mjl:header>
      <mjl:row even ="altRow">
        ${item.geoEntity.entityName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row >
        <mjl:commandLink display="View" action="csu.mrc.ivcc.mdss.entomology.MosquitoCollectionController.view.mojo" name="view.link">
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
        <mjl:commandLink display="ViewAssays" action="csu.mrc.ivcc.mdss.entomology.MosquitoCollectionController.viewAssays.mojo" name="viewAssays.link">
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
<mjl:commandLink display="Create a new Mosquito Collection" action="csu.mrc.ivcc.mdss.entomology.MosquitoCollectionController.newInstance.mojo" name="MosquitoCollectionController.newInstance" />
