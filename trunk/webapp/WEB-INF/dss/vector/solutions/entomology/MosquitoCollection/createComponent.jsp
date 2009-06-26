<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Create_Mosquito_Collection"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.form.name" id="dss.vector.solutions.entomology.MosquitoCollection.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Create" action="dss.vector.solutions.entomology.MosquitoCollectionController.create.mojo" name="dss.vector.solutions.entomology.MosquitoCollection.form.create.button"
      classes="submitButton" />
  </dl>
</mjl:form>
<div id="cal1Container" class="yui-skin-sam"></div>