<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
         <mjl:select var="current" valueAttribute="id" items="${AbstractMosquitoCollection_geoEntity}" param="geoEntity" >
          <c:choose>
            <c:when test="${current.geoId == item.geoEntity.geoId}">
             <mjl:option selected="selected">
               ${current.geoId}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${current.geoId}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>

<div class="submitButton_bl"></div>
<mjl:command value="Update" action="dss.vector.solutions.entomology.MosquitoCollectionController.update.mojo"
name="dss.vector.solutions.entomology.MosquitoCollection.form.update.button" classes="submitButton"/>
<mjl:command value="Delete" action="dss.vector.solutions.entomology.MosquitoCollectionController.delete.mojo"
name="dss.vector.solutions.entomology.MosquitoCollection.form.delete.button" classes="submitButton"/>
<mjl:command value="Cancel" action="dss.vector.solutions.entomology.MosquitoCollectionController.cancel.mojo"
name="dss.vector.solutions.entomology.MosquitoCollection.form.cancel.button" classes="submitButton"/>
</mjl:form>


<div id="cal1Container" class="yui-skin-sam"></div>