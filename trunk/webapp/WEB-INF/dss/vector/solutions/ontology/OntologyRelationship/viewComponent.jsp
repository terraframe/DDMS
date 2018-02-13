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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.OntologyRelationship.form.name" id="dss.vector.solutions.ontology.OntologyRelationship.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="altId">
        ${item.altId}
      </mjl:dt>
      <mjl:dt attribute="comment">
        ${item.comment}
      </mjl:dt>
      <mjl:dt attribute="def">
        ${item.def}
      </mjl:dt>
      <mjl:dt attribute="inverseOf">
        ${item.inverseOf.id}
      </mjl:dt>
      <mjl:dt attribute="inverseOfOnInstanceLevel">
        ${item.inverseOfOnInstanceLevel.id}
      </mjl:dt>
      <mjl:dt attribute="isAntiSymmetric">
        ${item.isAntiSymmetric ? item.isAntiSymmetricMd.positiveDisplayLabel : item.isAntiSymmetricMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="isBuiltIn">
        ${item.isBuiltIn ? item.isBuiltInMd.positiveDisplayLabel : item.isBuiltInMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="isObsolete">
        ${item.isObsolete ? item.isObsoleteMd.positiveDisplayLabel : item.isObsoleteMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="isReflexive">
        ${item.isReflexive ? item.isReflexiveMd.positiveDisplayLabel : item.isReflexiveMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="isTransitive">
        ${item.isTransitive ? item.isTransitiveMd.positiveDisplayLabel : item.isTransitiveMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="keyName">
        ${item.keyName}
      </mjl:dt>
      <mjl:dt attribute="name">
        ${item.name}
      </mjl:dt>
      <mjl:dt attribute="namespace">
        ${item.namespace}
      </mjl:dt>
      <mjl:dt attribute="relationshipId">
        ${item.relationshipId}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.ontology.OntologyRelationshipController.edit.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.edit.button" />
  </mjl:form>
</dl>
<dl>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink action="dss.vector.solutions.ontology.OntologyHasRelationshipController.childQuery.mojo" name="dss.vector.solutions.ontology.OntologyHasRelationship.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.OntologyRelationshipController.viewAll.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
