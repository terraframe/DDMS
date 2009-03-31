<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View All Mosquito Collections"  scope="request"/>
<fmt:setLocale value="<%=request.getLocale()%>" />

<mjl:messages>
  <mjl:message />
</mjl:messages>


<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.MosquitoCollectionController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="collectionMethod">
      <mjl:header>
        Collection Method
      </mjl:header>
      <mjl:row>
        ${item.collectionMethod.termName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dateCollected">
      <mjl:header>
        Date Collected
      </mjl:header>
      <mjl:row>
        <fmt:formatDate value="${item.dateCollected}" dateStyle="SHORT" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header>
        Geo Entity
      </mjl:header>
      <mjl:row>
        ${item.geoEntity.entityName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" name="view.link">
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
        <mjl:commandLink display="ViewAssays" action="dss.vector.solutions.entomology.MosquitoCollectionController.viewAssays.mojo" name="viewAssays.link">
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
<mjl:commandLink display="Create a new Mosquito Collection" action="dss.vector.solutions.entomology.MosquitoCollectionController.newInstance.mojo" name="MosquitoCollectionController.newInstance" />
<mjl:commandLink display="View Mosquito Collection Report" action="dss.vector.solutions.report.ReportController.report.mojo" name="MosquitoCollection.viewReport">
  <mjl:property name="reportName" value="collection.rptdesign" />
</mjl:commandLink>
<%out.flush();%>