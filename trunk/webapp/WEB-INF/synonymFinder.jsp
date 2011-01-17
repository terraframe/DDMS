<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/string-1.1" prefix="str"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.geo.UnknownGeoEntityDTO"%>
<%@page import="dss.vector.solutions.geo.GeoSynonymDTO"%>

<c:set var="page_title" value="${!(empty errorMessage) ? 'Synonym_Finder_Fail' : 'Synonym_Finder'}"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<%=Halp.loadTypes(Arrays.asList(new String[] { UnknownGeoEntityDTO.CLASS, GeoSynonymDTO.CLASS }))%>

<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

  var saveSynonym = function(entityName, geoId, selectId)
  {
    var request = new MDSS.Request({
      selectId : selectId,
      onSuccess : function(){

    	// success, so disable the inputs
        document.getElementById(this.selectId).disabled = true;
        document.getElementById(this.selectId+'_save').disabled = true;
      }
    }); 

    Mojo.$.dss.vector.solutions.geo.GeoSynonym.createSynonym(request, entityName, geoId);
  };
  
  var saveSynonymHandler = function(e, selectId)
  {
    var select = document.getElementById(selectId);
    var options = select.options;
    var selected = options[select.selectedIndex]; 
    
    var geoId = selected.value;
    var entityName = document.getElementById(selectId+'_entityName').value;

    saveSynonym(entityName, geoId, selectId);

  };
 
  var selects = YAHOO.util.Selector.query("select.possibleMatches");
  for(var i=0; i<selects.length; i++)
  {
    var select = selects[i];

    YAHOO.util.Event.on(select.id+'_save', 'click', saveSynonymHandler, select.id);
  }

});

</script>

<c:choose>  
	<c:when test="${!(empty errorMessage)}">
		<div class="pageTitle"><mdss:localize key="Synonym_Finder_Fail" /></div>
    ${errorMessage}
  </c:when>
	<c:otherwise>
		<div class="pageTitle"><mdss:localize key="Synonym_Finder" /></div>

		<mdss:localize key="save" var="saveLocalized"></fmt:message>

		<ul>
			<c:forEach var="unknownGeoEntity" items="${unknownGeoEntitys}">
				<li>
				<dl>
					<dt>${unknownGeoEntity.entityTypeMd.displayLabel}<br />
					</dt>
					<dd>${unknownGeoEntity.entityType}<br />
					</dd>
					<dt>${unknownGeoEntity.entityNameMd.displayLabel}<br />
					</dt>
					<dd>${unknownGeoEntity.entityName}<br />
					</dd>
					<dt>${unknownGeoEntity.knownHierarchyMd.displayLabel}<br />
					</dt>
					<dd>${unknownGeoEntity.knownHierarchy}<br />
					</dd>
					<dt>${unknownGeoEntity.synonymsMd.displayLabel}<br />
					</dt>
					<dd><input type="hidden"
						id="${unknownGeoEntity.id}_entityName"
						value="${unknownGeoEntity.entityName}" /> <select
						id="${unknownGeoEntity.id}" class="possibleMatches">

						<str:split var="synonyms" separator=":">${unknownGeoEntity.synonyms}</str:split>

						<c:forEach var="synonym" items="${synonyms}">

							<str:split var="pieces" separator=";">${synonym}</str:split>

							<option value="${pieces[0]}">${pieces[1]} (${pieces[0]})</option>

						</c:forEach>

						<str:split var="siblings" separator=":">${unknownGeoEntity.siblings}</str:split>
						<c:forEach var="sibling" items="${siblings}">

							<str:split var="pieces" separator=";">${sibling}</str:split>

							<option value="${pieces[0]}">${pieces[1]} (${pieces[0]})</option>

						</c:forEach>
					</select> <input type="button" id="${unknownGeoEntity.id}_save"
						class="saveSynonym" value="${saveLocalized}" /></dd>
				</dl>
				</li>
			</c:forEach>
		</ul>
	</c:otherwise>
</c:choose>

<c:set var="back_to_import" scope="request"><mdss:localize key="back_to_import"/></c:set>

<form id="${excelType}.import" name="${excelType}.import" action="excelimport" method="post">
  <input type="hidden" value="${excelType}" name="excelType"/>
  <input type="submit" class="submitButton" name="import.button" value="${back_to_import}"/> <mdss:localize key="post_synonym"/>
</form>

<jsp:include page="/WEB-INF/templates/footer.jsp" />