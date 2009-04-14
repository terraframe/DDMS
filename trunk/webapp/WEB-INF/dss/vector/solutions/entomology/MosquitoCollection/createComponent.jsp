<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.form.name" id="dss.vector.solutions.entomology.MosquitoCollection.form.id" method="POST">

  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.collectionMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${MosquitoCollection_collectionMethod}" param="collectionMethod">
          <mjl:option>
            ${current.termName}
          </mjl:option>
        </mjl:select>
         <mjl:messages attribute="collectionMethod">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.dateCollectedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="dateCollected" classes="DatePick" id="dto.dateCollected.id"/>
        <mjl:messages attribute="dateCollected">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.geoEntityMd.displayLabel}
        </label>
      </dt>
      <dd>
          ${item.geoEntity.geoId}
          <mjl:input type="hidden" param="geoEntity" id="dto.geoEntity.id" />
         <mjl:messages attribute="geoEntity">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>


  <mjl:command value="Create" action="dss.vector.solutions.entomology.MosquitoCollectionController.create.mojo" name="dss.vector.solutions.entomology.MosquitoCollection.form.create.button" classes="submitButton"/>
</mjl:form>
<div id="cal1Container" class="yui-skin-sam"></div>