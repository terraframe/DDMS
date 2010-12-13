<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Default_Geo_Root" />

<dl>
  <mjl:form id="dss.vector.solutions.DefaultGeoEntity.form.id" name="dss.vector.solutions.DefaultGeoEntity.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="geoEntity">
        ${item.geoEntity.displayString}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.DefaultGeoEntity.form.edit.button" value="Edit" action="dss.vector.solutions.DefaultGeoEntityController.edit.mojo" />
  </mjl:form>
</dl>

<mjl:commandLink action="dss.vector.solutions.PropertyController.viewAll.mojo" name="dss.vector.solutions.Property.viewAll.link" id="viewAll">
  <fmt:message key="View_All" />
</mjl:commandLink>