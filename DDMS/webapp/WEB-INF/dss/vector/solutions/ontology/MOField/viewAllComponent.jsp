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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="com.runwaysdk.business.ViewDTO"%>
<c:set var="page_title" value="Ontology_Fields" scope="request" />

<script type="text/javascript" src="js/OntologyFields.js"></script>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<dl>
<c:forEach items="${moFields}" var="moField">
 <dt>
   ${moField.mdClassLabel} : ${moField.mdAttributeLabel}<br />
   <button type="button" value="Add Root" id=""></button>
 </dt> 
 <dd>
   <table>
     <tr><th>Term</th><th>Selectable</th><th>Edit</th><th>Delete</th></tr>
   </table>
 </dd>
 
 
 <script type="text/javascript">
 </script>
</c:forEach>
</dl>