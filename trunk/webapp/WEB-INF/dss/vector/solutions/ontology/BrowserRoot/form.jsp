<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="term">
    <div id="${item.id}_termName">${termName}</div>
    <mjl:input type="hidden" param="term" id="${item.id}_term" />
    <mjl:command name="dss.vector.solutions.ontology.BrowserRoot.form.openBrowser" 
      action="dss.vector.solutions.ontology.BrowserRootController.openBrowser.mojo" value="Browser"></mjl:command>
  </mjl:dt>
  <mjl:dt attribute="selectable">
    <mjl:boolean param="selectable" />
  </mjl:dt>
</mjl:component>
