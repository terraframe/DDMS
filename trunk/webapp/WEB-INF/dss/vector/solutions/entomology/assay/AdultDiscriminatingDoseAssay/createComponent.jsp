<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>

<%@page import="com.runwaysdk.transport.metadata.AttributeMdDTO"%>


<c:set var="page_title" value="Enter_new_adult_diagnostic_assay_data" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mdss:localize key="Create" var="Localized_Create" />
  
    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.create.mojo"
      name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.create.button" classes="submitButton" />
  </dl>
</mjl:form>

