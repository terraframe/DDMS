<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Province" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Province.form.name" id="dss.vector.solutions.geo.generated.Province.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.ProvinceController.update.mojo" name="dss.vector.solutions.geo.generated.Province.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.ProvinceController.delete.mojo" name="dss.vector.solutions.geo.generated.Province.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.ProvinceController.cancel.mojo" name="dss.vector.solutions.geo.generated.Province.form.cancel.button" />
  </mjl:form>
</dl>
