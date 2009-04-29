<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.RDTResultMaster.form.name" id="dss.vector.solutions.intervention.RDTResultMaster.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="displayLabel">
      ${item.displayLabel}
</mjl:dt>
    <mjl:dt attribute="enumName">
      ${item.enumName}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.RDTResultMasterController.edit.mojo" name="dss.vector.solutions.intervention.RDTResultMaster.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="com.terraframe.mojo.system.metadata.EnumerationAttributeItemController.childQuery.mojo" name="com.terraframe.mojo.system.metadata.EnumerationAttributeItem.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.RDTResultMasterController.viewAll.mojo" name="dss.vector.solutions.intervention.RDTResultMaster.viewAll.link" />
