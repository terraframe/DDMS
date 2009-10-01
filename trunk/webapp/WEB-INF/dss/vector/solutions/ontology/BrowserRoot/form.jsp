<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="term">
    <mjl:command name="dss.vector.solutions.ontology.BrowserRoot.form.openBrowser" 
      action="dss.vector.solutions.ontology.BrowserRootController.openBrowser.mojo" value="Browser" classes="browserOpenBtn"></mjl:command>
    <div id="${item.id}_termName" class="autosize" style="height:15px; margin-left:70px; padding:3px; width:280px; background-color: white; border:1px solid black">
      <div>
      <c:if test="${termName != null}">
        ${termName} (${termId})
      </c:if>
      </div>
    </div>
    <mjl:input type="hidden" param="term" id="${item.id}_term" />
  </mjl:dt>
  <mjl:dt attribute="selectable">
    <mjl:boolean param="selectable" />
  </mjl:dt>
</mjl:component>
