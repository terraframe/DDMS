<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.CollectionTrueSpecie.form.name" id="mdss.entomology.CollectionTrueSpecie.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="mdss.entomology.CollectionTrueSpecieController.create.mojo" name="mdss.entomology.CollectionTrueSpecie.form.create.button" />
</mjl:form>
