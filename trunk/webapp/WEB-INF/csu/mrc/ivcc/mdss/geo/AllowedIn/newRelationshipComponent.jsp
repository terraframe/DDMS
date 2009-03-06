<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="csu.mrc.ivcc.mdss.geo.AllowedIn.form.name" id="csu.mrc.ivcc.mdss.geo.AllowedIn.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Geo Hierarchy
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
        Geo Hierarchy
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
  <mjl:command value="New Instance" action="csu.mrc.ivcc.mdss.geo.AllowedInController.newInstance.mojo" name="csu.mrc.ivcc.mdss.geo.AllowedIn.form.newInstance.button" />
</mjl:form>
