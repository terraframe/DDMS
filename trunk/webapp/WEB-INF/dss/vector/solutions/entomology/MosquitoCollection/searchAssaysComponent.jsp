<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionPointDTO"%>

<c:set var="page_title" value="Select_Collection"  scope="request"/>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.search" method="POST" id ="searchMosquitoCollections">
  <dl>

    <dt><label><fmt:message key="Enter_Collection_Id" /></label></dt>
    <dd>
      <mjl:input id="collectionInput" param="collectionId" type="text" />
      <mjl:input id="collectionId" param="id" type="hidden" />        
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionController.viewAssays.mojo" name="search.button" id="button.id" value="Select"/>
  </dl>
</mjl:form>

<%=Halp.loadTypes(Arrays.asList(new String[]{AbstractMosquitoCollectionDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{ConcreteMosquitoCollectionDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionDTO.CLASS}))%>
<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionPointDTO.CLASS}))%>

<script type="text/javascript" defer="defer">  
  MDSS.collectionSearch('<%=ConcreteMosquitoCollectionDTO.CLASS%>');
</script>    