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

    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="displayLabel" />
        <mjl:messages attribute="displayLabel">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.activeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="active" />
        <mjl:messages attribute="active">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.startAgeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="startAge" />
        <mjl:messages attribute="startAge">
          <mjl:message />
        </mjl:messages>
      </dd>      
      <dt>
        <label>
          ${item.endAgeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="endAge" />
        <mjl:messages attribute="endAge">
          <mjl:message />
        </mjl:messages>
      </dd>
    </mjl:component>
