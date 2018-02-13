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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Update_Country_Image"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<c:set scope="request" var="Upload"><mdss:localize key="Upload"/></c:set>

<form method="post" enctype="multipart/form-data" action="dss.vector.solutions.PropertyController.setFlag.mojo">
  <dl>
    <dt>
      <mdss:localize key="Update_Country_Image"/>
    </dt>
    <dd>
      <input type="file" name="upfile"/> <br />
      <input class="submitButton" type="submit" value="${Upload}" style="margin-left: 0px; top: 0px;" >     
    </dd>
  </dl>
</form>