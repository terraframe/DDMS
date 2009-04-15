<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.form.name" id="dss.vector.solutions.entomology.MosquitoCollection.form.id" method="POST">

  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="collectionMethod">
<mjl:select var="current" valueAttribute="id" items="${MosquitoCollection_collectionMethod}" param="collectionMethod">
          <mjl:option>
            ${current.termName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="dateCollected">
<mjl:input type="text" param="dateCollected" classes="DatePick" id="dto.dateCollected.id"/>
</mjl:dt>
      <dt>
        <label>
          ${item.geoEntityMd.displayLabel}
        </label>
      </dt>
      <dd>
                   ${item.geoEntity.geoId}
          <mjl:input type="hidden" param="geoEntity" id="dto.geoEntity.id" />
      </dd>
    </dl>
  </mjl:component>


<mjl:command value="Update" action="dss.vector.solutions.entomology.MosquitoCollectionController.update.mojo"
name="dss.vector.solutions.entomology.MosquitoCollection.form.update.button" classes="submitButton"/>
<mjl:command value="Delete" action="dss.vector.solutions.entomology.MosquitoCollectionController.delete.mojo"
name="dss.vector.solutions.entomology.MosquitoCollection.form.delete.button" classes="submitButton"/>
<mjl:command value="Cancel" action="dss.vector.solutions.entomology.MosquitoCollectionController.cancel.mojo"
name="dss.vector.solutions.entomology.MosquitoCollection.form.cancel.button" classes="submitButton"/>
</mjl:form>


<div id="cal1Container" class="yui-skin-sam"></div>