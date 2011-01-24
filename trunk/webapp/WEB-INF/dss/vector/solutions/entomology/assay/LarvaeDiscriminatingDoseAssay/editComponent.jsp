<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>

<c:set var="page_title" value="Edit_data_for_Larvae_Diagnostic_Dose_Assay"  scope="request"/>


<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.id" method="POST">
  <dl> 
    <%@ include file="form.jsp"%>
  
    <mdss:localize key="Update" var="Localized_Update" />
  
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.update.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.update.button" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.delete.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.cancel.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.cancel.button" />
  </dl>
</mjl:form>