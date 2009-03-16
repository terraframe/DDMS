<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.geo.LocatedIn.form.name" id="dss.vector.solutions.geo.LocatedIn.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Geo Entity
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
        Geo Entity
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
  <mjl:command value="New Instance" action="dss.vector.solutions.geo.LocatedInController.newInstance.mojo" name="dss.vector.solutions.geo.LocatedIn.form.newInstance.button" />
</mjl:form>
