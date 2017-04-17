<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Edit_MdWebSingleTermGrid" />

<script type="text/javascript">
YAHOO.util.Event.onDOMReady(function(){
  var id = '${item.id}';
  new dss.vector.solutions.GridFieldAdmin(id).render();
});
</script>



<mjl:messages>
  <mjl:message />
</mjl:messages>
<h2 class="fieldTitle">${item.md.displayLabel}</h2>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebSingleTermGrid.form.id" name="com.runwaysdk.system.metadata.MdWebSingleTermGrid.form.name" method="POST">
<dl>
    <%@include file="form.jsp" %>
    
    <dt>
      <label><mdss:localize key="Fields"/></label> <a href="#" id="gridAvailableFields"><mdss:localize key="Add_field"/></a>      
    </dt>    
    <dd>
      <div class="grid-form-item-row" id="gridFormItemRow">
      </div>
    </dd>
</dl>
  <!-- end fields -->    
    
    <%@include file="../MdWebAttribute/editActions.jsp" %>
  </mjl:form>
