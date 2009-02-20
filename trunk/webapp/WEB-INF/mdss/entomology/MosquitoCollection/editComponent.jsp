<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.MosquitoCollection.form.name" id="mdss.entomology.MosquitoCollection.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.collectionMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_MosquitoCollection_collectionMethod}" param="collectionMethod">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_AbstractMosquitoCollection_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.geoId}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="mdss.entomology.MosquitoCollectionController.update.mojo" name="mdss.entomology.MosquitoCollection.form.update.button" />
  <mjl:command value="Delete" action="mdss.entomology.MosquitoCollectionController.delete.mojo" name="mdss.entomology.MosquitoCollection.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.entomology.MosquitoCollectionController.cancel.mojo" name="mdss.entomology.MosquitoCollection.form.cancel.button" />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div> 
