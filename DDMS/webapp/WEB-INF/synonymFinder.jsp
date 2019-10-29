<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@page import="dss.vector.solutions.ontology.TermSynonymDTO"%>
<%@page import="dss.vector.solutions.ontology.TermSynonym"%>
<%@page import="dss.vector.solutions.ontology.UnknownTermDTO"%>
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

<%=Halp.loadTypes(Arrays.asList(new String[] { UnknownGeoEntityDTO.CLASS, GeoSynonymDTO.CLASS, UnknownTermDTO.CLASS, TermSynonymDTO.CLASS }))%>

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

  var saveTermSynonym = function(termName, termId, elementId)
  {
    var request = new MDSS.Request({
      elementId : elementId,
      onSuccess : function(){

      // success, so disable the inputs
        document.getElementById(this.elementId+'Display').disabled = true;
        document.getElementById(this.elementId+'_save').disabled = true;
      }
    }); 

    Mojo.$.dss.vector.solutions.ontology.TermSynonym.createTermSynonym(request, termName, termId);
  };
  
  var saveTermSynonymHandler = function(e, elId)
  {
    var el = document.getElementById(elId);
    
    var termId = el.value;
    
    if(termId != null && termId.length > 0) {
      var termName = document.getElementById(elId+'_termName').value;

      saveTermSynonym(termName, termId, elId);      
    }
  };
 
  var terms = YAHOO.util.Selector.query(".termMatches");
  for(var i=0; i<terms.length; i++)
  {
    var term = terms[i];

    YAHOO.util.Event.on(term.id+'_save', 'click', saveTermSynonymHandler, term.id);
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

    <mdss:localize key="save" var="saveLocalized" />

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
    <ul>
      <c:forEach var="unknownTerm" items="${unknownTerms}">
        <li>
        <dl>
          <dt>${unknownTerm.attributeLabelMd.displayLabel}<br />
          </dt>
          <dd>${unknownTerm.attributeLabel}<br />
          </dd>
          <dt>${unknownTerm.termNameMd.displayLabel}<br />
          </dt>
          <dd>${unknownTerm.termName}<br />
          </dd>
          <dt>${unknownTerm.synonymsMd.displayLabel}<br />
          </dt>
          <dd>
            <input type="hidden" id="${unknownTerm.id}_termName" value="${unknownTerm.termName}" />
            
            <mdss:mo param="${unknownTerm.id}" id="${unknownTerm.id}" classes="termMatches" browserClass="${unknownTerm.browserClass}" browserAttribute="${unknownTerm.browserAttribute}" />
                        
            <input type="button" id="${unknownTerm.id}_save" class="saveSynonym" value="${saveLocalized}" />
          </dd>
        </dl>
        </li>
      </c:forEach>
    </ul>    
  </c:otherwise>
</c:choose>

<c:set var="back_to_import" scope="request"><mdss:localize key="back_to_import"/></c:set>

<form id="${excelType}.import" name="${excelType}.import" action="${action}" method="post">
  <input type="hidden" value="${excelType}" name="excelType"/>
  <input type="submit" class="submitButton" name="import.button" value="${back_to_import}"/> <mdss:localize key="post_synonym"/>
</form>

<jsp:include page="/WEB-INF/templates/footer.jsp" />