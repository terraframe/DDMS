<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.biochemical.GSTTestResult.form.name" id="dss.vector.solutions.entomology.assay.biochemical.GSTTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="mosquito">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_biochemical_GSTTestResult_mosquito}" param="mosquito">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="testResult">
<mjl:input type="text" param="testResult" />
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.biochemical.GSTTestResultController.create.mojo" name="dss.vector.solutions.entomology.assay.biochemical.GSTTestResult.form.create.button" />
</mjl:form>
