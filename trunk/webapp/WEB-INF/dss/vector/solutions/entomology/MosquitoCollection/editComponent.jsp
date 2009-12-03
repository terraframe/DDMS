<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_MosquitoCollection" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.entomology.MosquitoCollection.form.id" name="dss.vector.solutions.entomology.MosquitoCollection.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.entomology.MosquitoCollection.form.update.button" value="Update" action="dss.vector.solutions.entomology.MosquitoCollectionController.update.mojo" />
    <mjl:command name="dss.vector.solutions.entomology.MosquitoCollection.form.delete.button" value="Delete" action="dss.vector.solutions.entomology.MosquitoCollectionController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.entomology.MosquitoCollection.form.cancel.button" value="Cancel" action="dss.vector.solutions.entomology.MosquitoCollectionController.cancel.mojo" />
  </mjl:form>
</dl>
