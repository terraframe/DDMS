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
<%@page import="dss.vector.solutions.general.ReportModuleController"%>
<%@page import="dss.vector.solutions.general.ReportModuleViewDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />

<c:set scope="request" var="page_title" value="Report_module_administration" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<!-- <dl>  -->
  <mjl:form id="form.id" name="form.name" method="POST">
    <mjl:component param="view" item="${item}">
<!-- 
      <mjl:dt attribute="moduleName">
        <mjl:select param="moduleName" items="${modules}" var="current" valueAttribute="moduleName" includeBlank="false">
          <mjl:option>
            ${current.moduleName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
 -->    
    </mjl:component>
    <input type="button" id="button.id" value=""  />
  </mjl:form>
<!-- </dl>  -->

<%=Halp.loadTypes(Arrays.asList(new String[]{ReportModuleViewDTO.CLASS, ReportModuleController.CLASS}))%>

<script type="text/javascript">
(function(){
    YAHOO.util.Event.onDOMReady(function(){
      var button = document.getElementById('button.id');    
      button.value = MDSS.localize('Build_database_views');
        
      var buildDatabaseView = function(e) {
        e.preventDefault();
        YAHOO.util.Event.stopEvent(e);

        var params = Mojo.Util.collectFormValues('form.id');
        //var moduleName = params['view.moduleName'][0];
    
        var buildFunction = function(request)
        {
          Mojo.$.dss.vector.solutions.general.ReportModuleController.buildDatabaseViewsMap(request, params);                    
        }

        var pollingFunction = function(request)
        {
          Mojo.$.dss.vector.solutions.general.ReportModuleView.getProgress(request, '');                    
        }

        new MDSS.ProgressRequest(buildFunction, pollingFunction, 'Build_database_views').start();
      };
      
      YAHOO.util.Event.on(button, 'click', buildDatabaseView);    
    });
})();
</script>