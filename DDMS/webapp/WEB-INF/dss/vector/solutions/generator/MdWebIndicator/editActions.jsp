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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

  <mdss:localize var="Update_Localize" key="Update" />
  <mdss:localize var="Cancel_Localize" key="Cancel" />
  <c:choose>
    <c:when test="${isComposite}">
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.updateCompositeField.button" value="${Update_Localize}" action="dss.vector.solutions.form.MdFormAdminController.updateCompositeField.mojo" />
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.cancelCompositeField.button" value="${Cancel_Localize}" action="dss.vector.solutions.form.MdFormAdminController.cancelCompositeField.mojo" />
    </c:when>
    <c:otherwise>
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.updateMdField.button" value="${Update_Localize}" action="dss.vector.solutions.form.MdFormAdminController.updateMdField.mojo" />
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.cancelMdField.button" value="${Cancel_Localize}" action="dss.vector.solutions.form.MdFormAdminController.cancelMdField.mojo" />
    </c:otherwise>
  </c:choose>
