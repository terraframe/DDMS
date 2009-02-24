<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.MosquitoCollection.form.name" id="mdss.ivcc.mrc.csu.entomology.MosquitoCollection.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.collectionMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_MosquitoCollection_collectionMethod}" param="collectionMethod">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_AbstractMosquitoCollection_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.geoId}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="mdss.ivcc.mrc.csu.entomology.MosquitoCollectionController.update.mojo" name="mdss.ivcc.mrc.csu.entomology.MosquitoCollection.form.update.button" />
  <mjl:command value="Delete" action="mdss.ivcc.mrc.csu.entomology.MosquitoCollectionController.delete.mojo" name="mdss.ivcc.mrc.csu.entomology.MosquitoCollection.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.ivcc.mrc.csu.entomology.MosquitoCollectionController.cancel.mojo" name="mdss.ivcc.mrc.csu.entomology.MosquitoCollection.form.cancel.button" />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div> 
