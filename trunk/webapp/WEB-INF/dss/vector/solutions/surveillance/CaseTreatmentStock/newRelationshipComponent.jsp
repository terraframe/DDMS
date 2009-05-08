<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.surveillance.CaseTreatmentStock.form.name" id="dss.vector.solutions.surveillance.CaseTreatmentStock.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Age Group
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${parentList}" param="parentId">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label>
        Treatment Grid
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${childList}" param="childId">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
  </dl>
  <mjl:command value="New_Instance" action="dss.vector.solutions.surveillance.CaseTreatmentStockController.newInstance.mojo" name="dss.vector.solutions.surveillance.CaseTreatmentStock.form.newInstance.button" />
</mjl:form>
