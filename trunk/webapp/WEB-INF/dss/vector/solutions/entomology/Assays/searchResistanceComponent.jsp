<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="java.util.Arrays"%>

<c:set var="page_title" value="Enter_bioassays" scope="request" />

<jsp:include page="resistanceForm.jsp" />

<h1><mdss:localize key="Import_Export_Diagnostic_Assays"/></h1>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.DiagnosticAssayExcelView" name="excelType"/>
</jsp:include>

<h1><mdss:localize key="Import_Export_Time_Response_Assays"/></h1>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.TimeResponseAssayExcelView" name="excelType"/>
</jsp:include>