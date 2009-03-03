<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.CollectionTrueSpecie.form.name" id="ivcc.mrc.csu.mdss.entomology.CollectionTrueSpecie.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Abstract Mosquito Collection
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
        True Specie Collection
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
  <mjl:command value="New Instance" action="ivcc.mrc.csu.mdss.entomology.CollectionTrueSpecieController.newInstance.mojo" name="ivcc.mrc.csu.mdss.entomology.CollectionTrueSpecie.form.newInstance.button" />
</mjl:form>
