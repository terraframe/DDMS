<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.name" id="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="endPoint">
<mjl:commandLink display="${item.endPoint.keyName}" action="dss.vector.solutions.mo.LarvaeAgeController.view.mojo" name="dss.vector.solutions.mo.LarvaeAge.form.view.link">
        <mjl:property value="${item.endPoint.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="startPoint">
<mjl:commandLink display="${item.startPoint.keyName}" action="dss.vector.solutions.mo.LarvaeAgeController.view.mojo" name="dss.vector.solutions.mo.LarvaeAge.form.view.link">
        <mjl:property value="${item.startPoint.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.LarvaeAgeRangeController.edit.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.LarvaeAgeRangeController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.viewAll.link" />
