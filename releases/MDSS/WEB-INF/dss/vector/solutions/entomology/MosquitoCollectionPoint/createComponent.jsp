<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.MosquitoCollectionPoint.form.name" id="dss.vector.solutions.entomology.MosquitoCollectionPoint.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="geoEntity">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_ConcreteMosquitoCollection_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="startDate">
<mjl:input type="text" param="startDate" classes="DatePick" id="dto.startDate.id"/>
</mjl:dt>
      <mjl:dt attribute="endDate">
<mjl:input type="text" param="endDate" classes="DatePick" id="dto.endDate.id"/>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.MosquitoCollectionPointController.create.mojo" name="dss.vector.solutions.entomology.MosquitoCollectionPoint.form.create.button" />
</mjl:form>
