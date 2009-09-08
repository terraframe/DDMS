<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Create_LodgesOrResorts" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.LodgesOrResorts.form.name" id="dss.vector.solutions.geo.generated.LodgesOrResorts.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Create" action="dss.vector.solutions.geo.generated.LodgesOrResortsController.create.mojo" name="dss.vector.solutions.geo.generated.LodgesOrResorts.form.create.button" />
  </mjl:form>
</dl>
